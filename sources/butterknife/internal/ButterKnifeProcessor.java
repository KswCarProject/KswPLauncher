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
import butterknife.internal.ListenerClass;
import com.ibm.icu.text.PluralRules;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
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
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import kotlin.text.Typography;

public final class ButterKnifeProcessor extends AbstractProcessor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final List<Class<? extends Annotation>> LISTENERS = Arrays.asList(new Class[]{OnCheckedChanged.class, OnClick.class, OnEditorAction.class, OnFocusChange.class, OnItemClick.class, OnItemLongClick.class, OnItemSelected.class, OnLongClick.class, OnPageChange.class, OnTextChanged.class, OnTouch.class});
    private static final String LIST_TYPE = List.class.getCanonicalName();
    public static final String SUFFIX = "$$ViewInjector";
    static final String VIEW_TYPE = "android.view.View";
    private Elements elementUtils;
    private Filer filer;
    private Types typeUtils;

    public synchronized void init(ProcessingEnvironment env) {
        ButterKnifeProcessor.super.init(env);
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

    public boolean process(Set<? extends TypeElement> set, RoundEnvironment env) {
        for (Map.Entry<TypeElement, ViewInjector> entry : findAndParseTargets(env).entrySet()) {
            TypeElement typeElement = entry.getKey();
            ViewInjector viewInjector = entry.getValue();
            try {
                Writer writer = this.filer.createSourceFile(viewInjector.getFqcn(), new Element[]{typeElement}).openWriter();
                writer.write(viewInjector.brewJava());
                writer.flush();
                writer.close();
            } catch (IOException e) {
                error(typeElement, "Unable to write injector for type %s: %s", typeElement, e.getMessage());
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
        if (!enclosingElement.getModifiers().contains(Modifier.PRIVATE)) {
            return hasError;
        }
        error(enclosingElement, "@%s %s may not be contained in private classes. (%s.%s)", annotationClass.getSimpleName(), targetThing, enclosingElement.getQualifiedName(), element.getSimpleName());
        return true;
    }

    private void parseInjectView(Element element, Map<TypeElement, ViewInjector> targetClassMap, Set<String> erasedTargetNames) {
        boolean hasError = false;
        TypeElement enclosingElement = element.getEnclosingElement();
        TypeVariable elementType = element.asType();
        if (elementType instanceof TypeVariable) {
            elementType = elementType.getUpperBound();
        }
        boolean required = true;
        if (!isSubtypeOfType(elementType, VIEW_TYPE)) {
            error(element, "@InjectView fields must extend from View. (%s.%s)", enclosingElement.getQualifiedName(), element.getSimpleName());
            hasError = true;
        }
        boolean hasError2 = hasError | isValidForGeneratedCode(InjectView.class, "fields", element);
        if (element.getAnnotation(InjectViews.class) != null) {
            error(element, "Only one of @InjectView and @InjectViews is allowed. (%s.%s)", enclosingElement.getQualifiedName(), element.getSimpleName());
            hasError2 = true;
        }
        if (!hasError2) {
            String name = element.getSimpleName().toString();
            int id = ((InjectView) element.getAnnotation(InjectView.class)).value();
            String type = elementType.toString();
            if (element.getAnnotation(Optional.class) != null) {
                required = false;
            }
            getOrCreateTargetClass(targetClassMap, enclosingElement).addView(id, new ViewBinding(name, type, required));
            erasedTargetNames.add(enclosingElement.toString());
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: javax.lang.model.type.ArrayType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v0, resolved type: javax.lang.model.type.TypeMirror} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v2, resolved type: javax.lang.model.type.TypeMirror} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v3, resolved type: javax.lang.model.type.TypeMirror} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v14, resolved type: javax.lang.model.type.DeclaredType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v4, resolved type: javax.lang.model.type.TypeMirror} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v5, resolved type: javax.lang.model.type.TypeMirror} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v6, resolved type: javax.lang.model.type.TypeMirror} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v16, resolved type: javax.lang.model.type.ArrayType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v7, resolved type: javax.lang.model.type.TypeMirror} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void parseInjectViews(javax.lang.model.element.Element r17, java.util.Map<javax.lang.model.element.TypeElement, butterknife.internal.ViewInjector> r18, java.util.Set<java.lang.String> r19) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = 0
            javax.lang.model.element.Element r3 = r17.getEnclosingElement()
            javax.lang.model.element.TypeElement r3 = (javax.lang.model.element.TypeElement) r3
            javax.lang.model.type.TypeMirror r4 = r17.asType()
            java.lang.String r5 = r0.doubleErasure(r4)
            r6 = 0
            r7 = 0
            javax.lang.model.type.TypeKind r8 = r4.getKind()
            javax.lang.model.type.TypeKind r9 = javax.lang.model.type.TypeKind.ARRAY
            r10 = 2
            r11 = 1
            r12 = 0
            if (r8 != r9) goto L_0x002a
            r8 = r4
            javax.lang.model.type.ArrayType r8 = (javax.lang.model.type.ArrayType) r8
            javax.lang.model.type.TypeMirror r6 = r8.getComponentType()
            butterknife.internal.CollectionBinding$Kind r7 = butterknife.internal.CollectionBinding.Kind.ARRAY
            goto L_0x0072
        L_0x002a:
            java.lang.String r8 = LIST_TYPE
            boolean r8 = r8.equals(r5)
            if (r8 == 0) goto L_0x005e
            r8 = r4
            javax.lang.model.type.DeclaredType r8 = (javax.lang.model.type.DeclaredType) r8
            java.util.List r9 = r8.getTypeArguments()
            int r13 = r9.size()
            if (r13 == r11) goto L_0x0054
            java.lang.Object[] r13 = new java.lang.Object[r10]
            javax.lang.model.element.Name r14 = r3.getQualifiedName()
            r13[r12] = r14
            javax.lang.model.element.Name r14 = r17.getSimpleName()
            r13[r11] = r14
            java.lang.String r14 = "@InjectViews List must have a generic component. (%s.%s)"
            r0.error(r1, r14, r13)
            r2 = 1
            goto L_0x005b
        L_0x0054:
            java.lang.Object r13 = r9.get(r12)
            r6 = r13
            javax.lang.model.type.TypeMirror r6 = (javax.lang.model.type.TypeMirror) r6
        L_0x005b:
            butterknife.internal.CollectionBinding$Kind r7 = butterknife.internal.CollectionBinding.Kind.LIST
            goto L_0x0072
        L_0x005e:
            java.lang.Object[] r8 = new java.lang.Object[r10]
            javax.lang.model.element.Name r9 = r3.getQualifiedName()
            r8[r12] = r9
            javax.lang.model.element.Name r9 = r17.getSimpleName()
            r8[r11] = r9
            java.lang.String r9 = "@InjectViews must be a List or array. (%s.%s)"
            r0.error(r1, r9, r8)
            r2 = 1
        L_0x0072:
            boolean r8 = r6 instanceof javax.lang.model.type.TypeVariable
            if (r8 == 0) goto L_0x007d
            r8 = r6
            javax.lang.model.type.TypeVariable r8 = (javax.lang.model.type.TypeVariable) r8
            javax.lang.model.type.TypeMirror r6 = r8.getUpperBound()
        L_0x007d:
            if (r6 == 0) goto L_0x009b
            java.lang.String r8 = "android.view.View"
            boolean r8 = r0.isSubtypeOfType(r6, r8)
            if (r8 != 0) goto L_0x009b
            java.lang.Object[] r8 = new java.lang.Object[r10]
            javax.lang.model.element.Name r9 = r3.getQualifiedName()
            r8[r12] = r9
            javax.lang.model.element.Name r9 = r17.getSimpleName()
            r8[r11] = r9
            java.lang.String r9 = "@InjectViews type must extend from View. (%s.%s)"
            r0.error(r1, r9, r8)
            r2 = 1
        L_0x009b:
            java.lang.Class<butterknife.InjectViews> r8 = butterknife.InjectViews.class
            java.lang.String r9 = "fields"
            boolean r8 = r0.isValidForGeneratedCode(r8, r9, r1)
            r2 = r2 | r8
            if (r2 == 0) goto L_0x00a7
            return
        L_0x00a7:
            javax.lang.model.element.Name r8 = r17.getSimpleName()
            java.lang.String r8 = r8.toString()
            java.lang.Class<butterknife.InjectViews> r9 = butterknife.InjectViews.class
            java.lang.annotation.Annotation r9 = r1.getAnnotation(r9)
            butterknife.InjectViews r9 = (butterknife.InjectViews) r9
            int[] r9 = r9.value()
            int r13 = r9.length
            if (r13 != 0) goto L_0x00d2
            java.lang.Object[] r10 = new java.lang.Object[r10]
            javax.lang.model.element.Name r13 = r3.getQualifiedName()
            r10[r12] = r13
            javax.lang.model.element.Name r12 = r17.getSimpleName()
            r10[r11] = r12
            java.lang.String r11 = "@InjectViews must specify at least one ID. (%s.%s)"
            r0.error(r1, r11, r10)
            return
        L_0x00d2:
            if (r6 == 0) goto L_0x00fa
            java.lang.String r10 = r6.toString()
            java.lang.Class<butterknife.Optional> r13 = butterknife.Optional.class
            java.lang.annotation.Annotation r13 = r1.getAnnotation(r13)
            if (r13 != 0) goto L_0x00e1
            goto L_0x00e2
        L_0x00e1:
            r11 = r12
        L_0x00e2:
            r12 = r18
            butterknife.internal.ViewInjector r13 = r0.getOrCreateTargetClass(r12, r3)
            butterknife.internal.CollectionBinding r14 = new butterknife.internal.CollectionBinding
            r14.<init>(r8, r10, r7, r11)
            r13.addCollection(r9, r14)
            java.lang.String r15 = r3.toString()
            r0 = r19
            r0.add(r15)
            return
        L_0x00fa:
            r12 = r18
            r0 = r19
            java.lang.AssertionError r10 = new java.lang.AssertionError
            r10.<init>()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: butterknife.internal.ButterKnifeProcessor.parseInjectViews(javax.lang.model.element.Element, java.util.Map, java.util.Set):void");
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
        Class<? extends Annotation> cls = annotationClass;
        Element element2 = element;
        if (!(element2 instanceof ExecutableElement) || element.getKind() != ElementKind.METHOD) {
            Set<String> set = erasedTargetNames;
            Element element3 = element2;
            Map<TypeElement, ViewInjector> map = targetClassMap;
            throw new IllegalStateException(String.format("@%s annotation must be on a method.", new Object[]{annotationClass.getSimpleName()}));
        }
        ExecutableElement executableElement = (ExecutableElement) element2;
        TypeElement enclosingElement = element.getEnclosingElement();
        Annotation annotation = element2.getAnnotation(cls);
        Method annotationValue = cls.getDeclaredMethod("value", new Class[0]);
        if (annotationValue.getReturnType() == int[].class) {
            int[] ids = (int[]) annotationValue.invoke(annotation, new Object[0]);
            String name = executableElement.getSimpleName().toString();
            boolean required = element2.getAnnotation(Optional.class) == null;
            boolean hasError = isValidForGeneratedCode(cls, "methods", element2);
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            for (int id : ids) {
                if (!linkedHashSet.add(Integer.valueOf(id))) {
                    error(element2, "@%s annotation for method contains duplicate ID %d. (%s.%s)", annotationClass.getSimpleName(), Integer.valueOf(id), enclosingElement.getQualifiedName(), element.getSimpleName());
                    hasError = true;
                }
            }
            ListenerClass listener = (ListenerClass) cls.getAnnotation(ListenerClass.class);
            if (listener != null) {
                ListenerMethod[] methods = listener.method();
                if (methods.length <= 1) {
                    if (methods.length != 1) {
                        Method annotationCallback = cls.getDeclaredMethod("callback", new Class[0]);
                        Enum<?> callback = (Enum) annotationCallback.invoke(annotation, new Object[0]);
                        Method method2 = annotationCallback;
                        method = (ListenerMethod) callback.getDeclaringClass().getField(callback.name()).getAnnotation(ListenerMethod.class);
                        if (method == null) {
                            throw new IllegalStateException(String.format("No @%s defined on @%s's %s.%s.", new Object[]{ListenerMethod.class.getSimpleName(), annotationClass.getSimpleName(), callback.getDeclaringClass().getSimpleName(), callback.name()}));
                        }
                    } else if (listener.callbacks() == ListenerClass.NONE.class) {
                        method = methods[0];
                        Method method3 = annotationValue;
                    } else {
                        Method method4 = annotationValue;
                        throw new IllegalStateException(String.format("Both method() and callback() defined on @%s.", new Object[]{annotationClass.getSimpleName()}));
                    }
                    List<? extends VariableElement> methodParameters = executableElement.getParameters();
                    Annotation annotation2 = annotation;
                    if (methodParameters.size() > method.parameters().length) {
                        error(element2, "@%s methods can have at most %s parameter(s). (%s.%s)", annotationClass.getSimpleName(), Integer.valueOf(method.parameters().length), enclosingElement.getQualifiedName(), element.getSimpleName());
                        hasError = true;
                    }
                    TypeVariable returnType = executableElement.getReturnType();
                    if (returnType instanceof TypeVariable) {
                        returnType = returnType.getUpperBound();
                    }
                    TypeMirror typeMirror = returnType;
                    if (!returnType.toString().equals(method.returnType())) {
                        error(element2, "@%s methods must have a '%s' return type. (%s.%s)", annotationClass.getSimpleName(), method.returnType(), enclosingElement.getQualifiedName(), element.getSimpleName());
                        hasError = true;
                    }
                    if (!hasError) {
                        Parameter[] parameters = Parameter.NONE;
                        if (!methodParameters.isEmpty()) {
                            parameters = new Parameter[methodParameters.size()];
                            boolean z = hasError;
                            BitSet methodParameterUsed = new BitSet(methodParameters.size());
                            String[] parameterTypes2 = method.parameters();
                            LinkedHashSet linkedHashSet2 = linkedHashSet;
                            int i = 0;
                            while (true) {
                                ListenerMethod[] methods2 = methods;
                                if (i >= methodParameters.size()) {
                                    Parameter[] parameterArr = parameters;
                                    String[] strArr = parameterTypes2;
                                    break;
                                }
                                VariableElement methodParameter = (VariableElement) methodParameters.get(i);
                                TypeVariable methodParameterType = methodParameter.asType();
                                VariableElement variableElement = methodParameter;
                                if (methodParameterType instanceof TypeVariable) {
                                    methodParameterType = methodParameterType.getUpperBound();
                                }
                                int j = 0;
                                while (true) {
                                    if (j >= parameterTypes2.length) {
                                        parameterTypes = parameterTypes2;
                                        break;
                                    } else if (!methodParameterUsed.get(j) && isSubtypeOfType(methodParameterType, parameterTypes2[j])) {
                                        parameterTypes = parameterTypes2;
                                        parameters[i] = new Parameter(j, methodParameterType.toString());
                                        methodParameterUsed.set(j);
                                        break;
                                    } else {
                                        j++;
                                        Element element4 = element;
                                        parameterTypes2 = parameterTypes2;
                                    }
                                }
                                if (parameters[i] == null) {
                                    StringBuilder builder = new StringBuilder();
                                    builder.append("Unable to match @").append(annotationClass.getSimpleName()).append(" method arguments. (").append(enclosingElement.getQualifiedName()).append('.').append(element.getSimpleName()).append(')');
                                    int j2 = 0;
                                    while (j2 < parameters.length) {
                                        Parameter parameter = parameters[j2];
                                        TypeMirror methodParameterType2 = methodParameterType;
                                        Parameter[] parameters2 = parameters;
                                        List<? extends VariableElement> methodParameters2 = methodParameters;
                                        builder.append("\n\n  Parameter #").append(j2 + 1).append(PluralRules.KEYWORD_RULE_SEPARATOR).append(((VariableElement) methodParameters.get(j2)).asType().toString()).append("\n    ");
                                        if (parameter == null) {
                                            builder.append("did not match any listener parameters");
                                        } else {
                                            builder.append("matched listener parameter #").append(parameter.getListenerPosition() + 1).append(PluralRules.KEYWORD_RULE_SEPARATOR).append(parameter.getType());
                                        }
                                        j2++;
                                        methodParameterType = methodParameterType2;
                                        parameters = parameters2;
                                        methodParameters = methodParameters2;
                                    }
                                    List<? extends VariableElement> list = methodParameters;
                                    Parameter[] parameterArr2 = parameters;
                                    builder.append("\n\nMethods may have up to ").append(method.parameters().length).append(" parameter(s):\n");
                                    for (String parameterType : method.parameters()) {
                                        builder.append("\n  ").append(parameterType);
                                    }
                                    builder.append("\n\nThese may be listed in any order but will be searched for from top to bottom.");
                                    error(executableElement, builder.toString(), new Object[0]);
                                    return;
                                }
                                List<? extends VariableElement> list2 = methodParameters;
                                Parameter[] parameterArr3 = parameters;
                                i++;
                                Class<? extends Annotation> cls2 = annotationClass;
                                Element element5 = element;
                                methods = methods2;
                                parameterTypes2 = parameterTypes;
                            }
                        } else {
                            boolean z2 = hasError;
                            LinkedHashSet linkedHashSet3 = linkedHashSet;
                            ListenerMethod[] listenerMethodArr = methods;
                        }
                        ListenerBinding binding = new ListenerBinding(name, Arrays.asList(parameters), required);
                        ViewInjector viewInjector = getOrCreateTargetClass(targetClassMap, enclosingElement);
                        int length = ids.length;
                        int i2 = 0;
                        while (i2 < length) {
                            int id2 = ids[i2];
                            if (!viewInjector.addListener(id2, listener, method, binding)) {
                                error(element, "Multiple @%s methods declared for ID %s in %s.", annotationClass.getSimpleName(), Integer.valueOf(id2), enclosingElement.getQualifiedName());
                                return;
                            } else {
                                Element element6 = element;
                                i2++;
                            }
                        }
                        Element element7 = element;
                        erasedTargetNames.add(enclosingElement.toString());
                        return;
                    }
                    return;
                }
                throw new IllegalStateException(String.format("Multiple listener methods specified on @%s.", new Object[]{annotationClass.getSimpleName()}));
            }
            throw new IllegalStateException(String.format("No @%s defined on @%s.", new Object[]{ListenerClass.class.getSimpleName(), annotationClass.getSimpleName()}));
        }
        throw new IllegalStateException(String.format("@%s annotation value() type not int[].", new Object[]{annotationClass}));
    }

    private boolean isSubtypeOfType(TypeMirror typeMirror, String otherType) {
        if (otherType.equals(typeMirror.toString())) {
            return true;
        }
        if (!(typeMirror instanceof DeclaredType)) {
            return false;
        }
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
        if (!(asElement instanceof TypeElement)) {
            return false;
        }
        TypeElement typeElement = asElement;
        if (isSubtypeOfType(typeElement.getSuperclass(), otherType)) {
            return true;
        }
        for (TypeMirror interfaceType : typeElement.getInterfaces()) {
            if (isSubtypeOfType(interfaceType, otherType)) {
                return true;
            }
        }
        return false;
    }

    private ViewInjector getOrCreateTargetClass(Map<TypeElement, ViewInjector> targetClassMap, TypeElement enclosingElement) {
        ViewInjector viewInjector = targetClassMap.get(enclosingElement);
        if (viewInjector != null) {
            return viewInjector;
        }
        String targetType = enclosingElement.getQualifiedName().toString();
        String classPackage = getPackageName(enclosingElement);
        ViewInjector viewInjector2 = new ViewInjector(classPackage, getClassName(enclosingElement, classPackage) + SUFFIX, targetType);
        targetClassMap.put(enclosingElement, viewInjector2);
        return viewInjector2;
    }

    private static String getClassName(TypeElement type, String packageName) {
        return type.getQualifiedName().toString().substring(packageName.length() + 1).replace('.', '$');
    }

    private String findParentFqcn(TypeElement typeElement, Set<String> parents) {
        do {
            DeclaredType superclass = typeElement.getSuperclass();
            if (superclass.getKind() == TypeKind.NONE) {
                return null;
            }
            typeElement = superclass.asElement();
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
