package butterknife.internal;

import butterknife.InjectView;
import butterknife.InjectViews;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnFocusChange;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;
import butterknife.OnItemSelected;
import butterknife.OnLongClick;
import butterknife.OnPageChange;
import butterknife.OnTextChanged;
import butterknife.OnTouch;
import butterknife.Optional;
import butterknife.internal.CollectionBinding;
import butterknife.internal.ListenerClass;
import com.ibm.icu.text.PluralRules;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.BitSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import kotlin.text.Typography;

/* loaded from: classes.dex */
public final class ButterKnifeProcessor extends AbstractProcessor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final String SUFFIX = "$$ViewInjector";
    static final String VIEW_TYPE = "android.view.View";
    private Elements elementUtils;
    private Filer filer;
    private Types typeUtils;
    private static final String LIST_TYPE = List.class.getCanonicalName();
    private static final List<Class<? extends Annotation>> LISTENERS = Arrays.asList(OnCheckedChanged.class, OnClick.class, OnEditorAction.class, OnFocusChange.class, OnItemClick.class, OnItemLongClick.class, OnItemSelected.class, OnLongClick.class, OnPageChange.class, OnTextChanged.class, OnTouch.class);

    public synchronized void init(ProcessingEnvironment env) {
        super.init(env);
        this.elementUtils = env.getElementUtils();
        this.typeUtils = env.getTypeUtils();
        this.filer = env.getFiler();
    }

    public Set<String> getSupportedAnnotationTypes() {
        Set<String> supportTypes = new LinkedHashSet<>();
        supportTypes.add(InjectView.class.getCanonicalName());
        supportTypes.add(InjectViews.class.getCanonicalName());
        for (Class<? extends Annotation> listener : LISTENERS) {
            supportTypes.add(listener.getCanonicalName());
        }
        return supportTypes;
    }

    public boolean process(Set<? extends TypeElement> elements, RoundEnvironment env) {
        Map<TypeElement, ViewInjector> targetClassMap = findAndParseTargets(env);
        for (Map.Entry<TypeElement, ViewInjector> entry : targetClassMap.entrySet()) {
            TypeElement key = entry.getKey();
            ViewInjector viewInjector = entry.getValue();
            try {
                JavaFileObject jfo = this.filer.createSourceFile(viewInjector.getFqcn(), new Element[]{key});
                Writer writer = jfo.openWriter();
                writer.write(viewInjector.brewJava());
                writer.flush();
                writer.close();
            } catch (IOException e) {
                error(key, "Unable to write injector for type %s: %s", key, e.getMessage());
            }
        }
        return true;
    }

    private Map<TypeElement, ViewInjector> findAndParseTargets(RoundEnvironment env) {
        Map<TypeElement, ViewInjector> targetClassMap = new LinkedHashMap<>();
        Set<String> erasedTargetNames = new LinkedHashSet<>();
        for (Element element : env.getElementsAnnotatedWith(InjectView.class)) {
            try {
                parseInjectView(element, targetClassMap, erasedTargetNames);
            } catch (Exception e) {
                StringWriter stackTrace = new StringWriter();
                e.printStackTrace(new PrintWriter(stackTrace));
                error(element, "Unable to generate view injector for @InjectView.\n\n%s", stackTrace);
            }
        }
        for (Element element2 : env.getElementsAnnotatedWith(InjectViews.class)) {
            try {
                parseInjectViews(element2, targetClassMap, erasedTargetNames);
            } catch (Exception e2) {
                StringWriter stackTrace2 = new StringWriter();
                e2.printStackTrace(new PrintWriter(stackTrace2));
                error(element2, "Unable to generate view injector for @InjectViews.\n\n%s", stackTrace2);
            }
        }
        for (Class<? extends Annotation> listener : LISTENERS) {
            findAndParseListener(env, listener, targetClassMap, erasedTargetNames);
        }
        for (Map.Entry<TypeElement, ViewInjector> entry : targetClassMap.entrySet()) {
            String parentClassFqcn = findParentFqcn(entry.getKey(), erasedTargetNames);
            if (parentClassFqcn != null) {
                entry.getValue().setParentInjector(parentClassFqcn + SUFFIX);
            }
        }
        return targetClassMap;
    }

    private boolean isValidForGeneratedCode(Class<? extends Annotation> annotationClass, String targetThing, Element element) {
        boolean hasError = false;
        TypeElement enclosingElement = element.getEnclosingElement();
        Set<Modifier> modifiers = element.getModifiers();
        if (modifiers.contains(Modifier.PRIVATE) || modifiers.contains(Modifier.STATIC)) {
            error(element, "@%s %s must not be private or static. (%s.%s)", annotationClass.getSimpleName(), targetThing, enclosingElement.getQualifiedName(), element.getSimpleName());
            hasError = true;
        }
        if (enclosingElement.getKind() != ElementKind.CLASS) {
            error(enclosingElement, "@%s %s may only be contained in classes. (%s.%s)", annotationClass.getSimpleName(), targetThing, enclosingElement.getQualifiedName(), element.getSimpleName());
            hasError = true;
        }
        if (enclosingElement.getModifiers().contains(Modifier.PRIVATE)) {
            error(enclosingElement, "@%s %s may not be contained in private classes. (%s.%s)", annotationClass.getSimpleName(), targetThing, enclosingElement.getQualifiedName(), element.getSimpleName());
            return true;
        }
        return hasError;
    }

    private void parseInjectView(Element element, Map<TypeElement, ViewInjector> targetClassMap, Set<String> erasedTargetNames) {
        boolean hasError = false;
        TypeElement enclosingElement = (TypeElement) element.getEnclosingElement();
        TypeMirror elementType = element.asType();
        if (elementType instanceof TypeVariable) {
            TypeVariable typeVariable = (TypeVariable) elementType;
            elementType = typeVariable.getUpperBound();
        }
        if (!isSubtypeOfType(elementType, VIEW_TYPE)) {
            error(element, "@InjectView fields must extend from View. (%s.%s)", enclosingElement.getQualifiedName(), element.getSimpleName());
            hasError = true;
        }
        boolean hasError2 = hasError | isValidForGeneratedCode(InjectView.class, "fields", element);
        if (element.getAnnotation(InjectViews.class) != null) {
            error(element, "Only one of @InjectView and @InjectViews is allowed. (%s.%s)", enclosingElement.getQualifiedName(), element.getSimpleName());
            hasError2 = true;
        }
        if (hasError2) {
            return;
        }
        String name = element.getSimpleName().toString();
        int id = ((InjectView) element.getAnnotation(InjectView.class)).value();
        String type = elementType.toString();
        boolean required = element.getAnnotation(Optional.class) == null;
        ViewInjector viewInjector = getOrCreateTargetClass(targetClassMap, enclosingElement);
        ViewBinding binding = new ViewBinding(name, type, required);
        viewInjector.addView(id, binding);
        erasedTargetNames.add(enclosingElement.toString());
    }

    private void parseInjectViews(Element element, Map<TypeElement, ViewInjector> targetClassMap, Set<String> erasedTargetNames) {
        boolean hasError = false;
        TypeElement enclosingElement = (TypeElement) element.getEnclosingElement();
        ArrayType asType = element.asType();
        String erasedType = doubleErasure(asType);
        TypeMirror viewType = null;
        CollectionBinding.Kind kind = null;
        if (asType.getKind() == TypeKind.ARRAY) {
            ArrayType arrayType = asType;
            viewType = arrayType.getComponentType();
            kind = CollectionBinding.Kind.ARRAY;
        } else if (!LIST_TYPE.equals(erasedType)) {
            error(element, "@InjectViews must be a List or array. (%s.%s)", enclosingElement.getQualifiedName(), element.getSimpleName());
            hasError = true;
        } else {
            DeclaredType declaredType = (DeclaredType) asType;
            List<? extends TypeMirror> typeArguments = declaredType.getTypeArguments();
            if (typeArguments.size() != 1) {
                error(element, "@InjectViews List must have a generic component. (%s.%s)", enclosingElement.getQualifiedName(), element.getSimpleName());
                hasError = true;
            } else {
                viewType = typeArguments.get(0);
            }
            kind = CollectionBinding.Kind.LIST;
        }
        if (viewType instanceof TypeVariable) {
            TypeVariable typeVariable = viewType;
            viewType = typeVariable.getUpperBound();
        }
        if (viewType != null && !isSubtypeOfType(viewType, VIEW_TYPE)) {
            error(element, "@InjectViews type must extend from View. (%s.%s)", enclosingElement.getQualifiedName(), element.getSimpleName());
            hasError = true;
        }
        if (hasError | isValidForGeneratedCode(InjectViews.class, "fields", element)) {
            return;
        }
        String name = element.getSimpleName().toString();
        int[] ids = ((InjectViews) element.getAnnotation(InjectViews.class)).value();
        if (ids.length == 0) {
            error(element, "@InjectViews must specify at least one ID. (%s.%s)", enclosingElement.getQualifiedName(), element.getSimpleName());
        } else if (viewType == null) {
            throw new AssertionError();
        } else {
            String type = viewType.toString();
            boolean required = element.getAnnotation(Optional.class) == null;
            ViewInjector viewInjector = getOrCreateTargetClass(targetClassMap, enclosingElement);
            CollectionBinding binding = new CollectionBinding(name, type, kind, required);
            viewInjector.addCollection(ids, binding);
            erasedTargetNames.add(enclosingElement.toString());
        }
    }

    private String doubleErasure(TypeMirror elementType) {
        String name = this.typeUtils.erasure(elementType).toString();
        int typeParamStart = name.indexOf(60);
        if (typeParamStart != -1) {
            return name.substring(0, typeParamStart);
        }
        return name;
    }

    private void findAndParseListener(RoundEnvironment env, Class<? extends Annotation> annotationClass, Map<TypeElement, ViewInjector> targetClassMap, Set<String> erasedTargetNames) {
        for (Element element : env.getElementsAnnotatedWith(annotationClass)) {
            try {
                parseListenerAnnotation(annotationClass, element, targetClassMap, erasedTargetNames);
            } catch (Exception e) {
                StringWriter stackTrace = new StringWriter();
                e.printStackTrace(new PrintWriter(stackTrace));
                error(element, "Unable to generate view injector for @%s.\n\n%s", annotationClass.getSimpleName(), stackTrace.toString());
            }
        }
    }

    private void parseListenerAnnotation(Class<? extends Annotation> annotationClass, Element element, Map<TypeElement, ViewInjector> targetClassMap, Set<String> erasedTargetNames) throws Exception {
        ListenerMethod method;
        String[] parameterTypes;
        String[] parameters;
        if (!(element instanceof ExecutableElement) || element.getKind() != ElementKind.METHOD) {
            throw new IllegalStateException(String.format("@%s annotation must be on a method.", annotationClass.getSimpleName()));
        }
        ExecutableElement executableElement = (ExecutableElement) element;
        TypeElement enclosingElement = (TypeElement) element.getEnclosingElement();
        Annotation annotation = element.getAnnotation(annotationClass);
        Method annotationValue = annotationClass.getDeclaredMethod("value", new Class[0]);
        if (annotationValue.getReturnType() != int[].class) {
            throw new IllegalStateException(String.format("@%s annotation value() type not int[].", annotationClass));
        }
        int[] ids = (int[]) annotationValue.invoke(annotation, new Object[0]);
        String name = executableElement.getSimpleName().toString();
        boolean required = element.getAnnotation(Optional.class) == null;
        boolean hasError = isValidForGeneratedCode(annotationClass, "methods", element);
        Set<Integer> seenIds = new LinkedHashSet<>();
        for (int id : ids) {
            if (!seenIds.add(Integer.valueOf(id))) {
                error(element, "@%s annotation for method contains duplicate ID %d. (%s.%s)", annotationClass.getSimpleName(), Integer.valueOf(id), enclosingElement.getQualifiedName(), element.getSimpleName());
                hasError = true;
            }
        }
        ListenerClass listener = (ListenerClass) annotationClass.getAnnotation(ListenerClass.class);
        if (listener == null) {
            throw new IllegalStateException(String.format("No @%s defined on @%s.", ListenerClass.class.getSimpleName(), annotationClass.getSimpleName()));
        }
        ListenerMethod[] methods = listener.method();
        if (methods.length > 1) {
            throw new IllegalStateException(String.format("Multiple listener methods specified on @%s.", annotationClass.getSimpleName()));
        }
        if (methods.length == 1) {
            if (listener.callbacks() != ListenerClass.NONE.class) {
                throw new IllegalStateException(String.format("Both method() and callback() defined on @%s.", annotationClass.getSimpleName()));
            }
            method = methods[0];
        } else {
            Method annotationCallback = annotationClass.getDeclaredMethod("callback", new Class[0]);
            Enum<?> callback = (Enum) annotationCallback.invoke(annotation, new Object[0]);
            Field callbackField = callback.getDeclaringClass().getField(callback.name());
            method = (ListenerMethod) callbackField.getAnnotation(ListenerMethod.class);
            if (method == null) {
                throw new IllegalStateException(String.format("No @%s defined on @%s's %s.%s.", ListenerMethod.class.getSimpleName(), annotationClass.getSimpleName(), callback.getDeclaringClass().getSimpleName(), callback.name()));
            }
        }
        List<? extends VariableElement> methodParameters = executableElement.getParameters();
        if (methodParameters.size() > method.parameters().length) {
            error(element, "@%s methods can have at most %s parameter(s). (%s.%s)", annotationClass.getSimpleName(), Integer.valueOf(method.parameters().length), enclosingElement.getQualifiedName(), element.getSimpleName());
            hasError = true;
        }
        TypeVariable returnType = executableElement.getReturnType();
        if (returnType instanceof TypeVariable) {
            TypeVariable typeVariable = returnType;
            returnType = typeVariable.getUpperBound();
        }
        if (!returnType.toString().equals(method.returnType())) {
            error(element, "@%s methods must have a '%s' return type. (%s.%s)", annotationClass.getSimpleName(), method.returnType(), enclosingElement.getQualifiedName(), element.getSimpleName());
            hasError = true;
        }
        if (hasError) {
            return;
        }
        Parameter[] parameters2 = Parameter.NONE;
        if (!methodParameters.isEmpty()) {
            parameters2 = new Parameter[methodParameters.size()];
            BitSet methodParameterUsed = new BitSet(methodParameters.size());
            String[] parameterTypes2 = method.parameters();
            int i = 0;
            while (true) {
                ListenerMethod[] methods2 = methods;
                if (i >= methodParameters.size()) {
                    break;
                }
                VariableElement methodParameter = (VariableElement) methodParameters.get(i);
                TypeVariable methodParameterType = methodParameter.asType();
                if (methodParameterType instanceof TypeVariable) {
                    TypeVariable typeVariable2 = methodParameterType;
                    methodParameterType = typeVariable2.getUpperBound();
                }
                int j = 0;
                while (true) {
                    if (j < parameterTypes2.length) {
                        if (methodParameterUsed.get(j) || !isSubtypeOfType(methodParameterType, parameterTypes2[j])) {
                            j++;
                            parameterTypes2 = parameterTypes2;
                        } else {
                            parameterTypes = parameterTypes2;
                            parameters2[i] = new Parameter(j, methodParameterType.toString());
                            methodParameterUsed.set(j);
                            break;
                        }
                    } else {
                        parameterTypes = parameterTypes2;
                        break;
                    }
                }
                if (parameters2[i] != null) {
                    i++;
                    methods = methods2;
                    parameterTypes2 = parameterTypes;
                } else {
                    StringBuilder builder = new StringBuilder();
                    builder.append("Unable to match @").append(annotationClass.getSimpleName()).append(" method arguments. (").append((CharSequence) enclosingElement.getQualifiedName()).append('.').append((CharSequence) element.getSimpleName()).append(')');
                    int j2 = 0;
                    while (j2 < parameters2.length) {
                        Parameter parameter = parameters2[j2];
                        TypeMirror methodParameterType2 = methodParameterType;
                        Parameter[] parameters3 = parameters2;
                        List<? extends VariableElement> methodParameters2 = methodParameters;
                        builder.append("\n\n  Parameter #").append(j2 + 1).append(PluralRules.KEYWORD_RULE_SEPARATOR).append(((VariableElement) methodParameters.get(j2)).asType().toString()).append("\n    ");
                        if (parameter == null) {
                            builder.append("did not match any listener parameters");
                        } else {
                            builder.append("matched listener parameter #").append(parameter.getListenerPosition() + 1).append(PluralRules.KEYWORD_RULE_SEPARATOR).append(parameter.getType());
                        }
                        j2++;
                        methodParameterType = methodParameterType2;
                        parameters2 = parameters3;
                        methodParameters = methodParameters2;
                    }
                    builder.append("\n\nMethods may have up to ").append(method.parameters().length).append(" parameter(s):\n");
                    for (String parameterType : method.parameters()) {
                        builder.append("\n  ").append(parameterType);
                    }
                    builder.append("\n\nThese may be listed in any order but will be searched for from top to bottom.");
                    error(executableElement, builder.toString(), new Object[0]);
                    return;
                }
            }
        }
        ListenerBinding binding = new ListenerBinding(name, Arrays.asList(parameters2), required);
        ViewInjector viewInjector = getOrCreateTargetClass(targetClassMap, enclosingElement);
        for (int id2 : ids) {
            if (!viewInjector.addListener(id2, listener, method, binding)) {
                error(element, "Multiple @%s methods declared for ID %s in %s.", annotationClass.getSimpleName(), Integer.valueOf(id2), enclosingElement.getQualifiedName());
                return;
            }
        }
        erasedTargetNames.add(enclosingElement.toString());
    }

    private boolean isSubtypeOfType(TypeMirror typeMirror, String otherType) {
        if (otherType.equals(typeMirror.toString())) {
            return true;
        }
        if (typeMirror instanceof DeclaredType) {
            DeclaredType declaredType = (DeclaredType) typeMirror;
            List<? extends TypeMirror> typeArguments = declaredType.getTypeArguments();
            if (typeArguments.size() > 0) {
                StringBuilder typeString = new StringBuilder(declaredType.asElement().toString());
                typeString.append(Typography.less);
                for (int i = 0; i < typeArguments.size(); i++) {
                    if (i > 0) {
                        typeString.append(',');
                    }
                    typeString.append('?');
                }
                typeString.append(Typography.greater);
                if (typeString.toString().equals(otherType)) {
                    return true;
                }
            }
            TypeElement asElement = declaredType.asElement();
            if (asElement instanceof TypeElement) {
                TypeElement typeElement = asElement;
                TypeMirror superType = typeElement.getSuperclass();
                if (isSubtypeOfType(superType, otherType)) {
                    return true;
                }
                for (TypeMirror interfaceType : typeElement.getInterfaces()) {
                    if (isSubtypeOfType(interfaceType, otherType)) {
                        return true;
                    }
                }
                return false;
            }
            return false;
        }
        return false;
    }

    private ViewInjector getOrCreateTargetClass(Map<TypeElement, ViewInjector> targetClassMap, TypeElement enclosingElement) {
        ViewInjector viewInjector = targetClassMap.get(enclosingElement);
        if (viewInjector == null) {
            String targetType = enclosingElement.getQualifiedName().toString();
            String classPackage = getPackageName(enclosingElement);
            String className = getClassName(enclosingElement, classPackage) + SUFFIX;
            ViewInjector viewInjector2 = new ViewInjector(classPackage, className, targetType);
            targetClassMap.put(enclosingElement, viewInjector2);
            return viewInjector2;
        }
        return viewInjector;
    }

    private static String getClassName(TypeElement type, String packageName) {
        int packageLen = packageName.length() + 1;
        return type.getQualifiedName().toString().substring(packageLen).replace('.', '$');
    }

    private String findParentFqcn(TypeElement typeElement, Set<String> parents) {
        do {
            DeclaredType superclass = typeElement.getSuperclass();
            if (superclass.getKind() == TypeKind.NONE) {
                return null;
            }
            typeElement = (TypeElement) superclass.asElement();
        } while (!parents.contains(typeElement.toString()));
        String packageName = getPackageName(typeElement);
        return packageName + "." + getClassName(typeElement, packageName);
    }

    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    private void error(Element element, String message, Object... args) {
        if (args.length > 0) {
            message = String.format(message, args);
        }
        this.processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, message, element);
    }

    private String getPackageName(TypeElement type) {
        return this.elementUtils.getPackageOf(type).getQualifiedName().toString();
    }
}
