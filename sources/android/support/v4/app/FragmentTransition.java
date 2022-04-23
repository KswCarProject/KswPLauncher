package android.support.v4.app;

import android.graphics.Rect;
import android.os.Build;
import android.support.v4.app.BackStackRecord;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewCompat;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class FragmentTransition {
    private static final int[] INVERSE_OPS = {0, 3, 0, 1, 5, 4, 7, 6, 9, 8};
    private static final FragmentTransitionImpl PLATFORM_IMPL = (Build.VERSION.SDK_INT >= 21 ? new FragmentTransitionCompat21() : null);
    private static final FragmentTransitionImpl SUPPORT_IMPL = resolveSupportImpl();

    private static FragmentTransitionImpl resolveSupportImpl() {
        try {
            return (FragmentTransitionImpl) Class.forName("android.support.transition.FragmentTransitionSupport").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }

    static void startTransitions(FragmentManagerImpl fragmentManager, ArrayList<BackStackRecord> records, ArrayList<Boolean> isRecordPop, int startIndex, int endIndex, boolean isReordered) {
        if (fragmentManager.mCurState >= 1) {
            SparseArray<FragmentContainerTransition> transitioningFragments = new SparseArray<>();
            for (int i = startIndex; i < endIndex; i++) {
                BackStackRecord record = records.get(i);
                if (isRecordPop.get(i).booleanValue()) {
                    calculatePopFragments(record, transitioningFragments, isReordered);
                } else {
                    calculateFragments(record, transitioningFragments, isReordered);
                }
            }
            if (transitioningFragments.size() != 0) {
                View nonExistentView = new View(fragmentManager.mHost.getContext());
                int numContainers = transitioningFragments.size();
                for (int i2 = 0; i2 < numContainers; i2++) {
                    int containerId = transitioningFragments.keyAt(i2);
                    ArrayMap<String, String> nameOverrides = calculateNameOverrides(containerId, records, isRecordPop, startIndex, endIndex);
                    FragmentContainerTransition containerTransition = transitioningFragments.valueAt(i2);
                    if (isReordered) {
                        configureTransitionsReordered(fragmentManager, containerId, containerTransition, nonExistentView, nameOverrides);
                    } else {
                        configureTransitionsOrdered(fragmentManager, containerId, containerTransition, nonExistentView, nameOverrides);
                    }
                }
            }
        }
    }

    private static ArrayMap<String, String> calculateNameOverrides(int containerId, ArrayList<BackStackRecord> records, ArrayList<Boolean> isRecordPop, int startIndex, int endIndex) {
        ArrayList<String> sources;
        ArrayList<String> targets;
        ArrayMap<String, String> nameOverrides = new ArrayMap<>();
        for (int recordNum = endIndex - 1; recordNum >= startIndex; recordNum--) {
            BackStackRecord record = records.get(recordNum);
            if (record.interactsWith(containerId)) {
                boolean isPop = isRecordPop.get(recordNum).booleanValue();
                if (record.mSharedElementSourceNames != null) {
                    int numSharedElements = record.mSharedElementSourceNames.size();
                    if (isPop) {
                        targets = record.mSharedElementSourceNames;
                        sources = record.mSharedElementTargetNames;
                    } else {
                        sources = record.mSharedElementSourceNames;
                        targets = record.mSharedElementTargetNames;
                    }
                    for (int i = 0; i < numSharedElements; i++) {
                        String sourceName = sources.get(i);
                        String targetName = targets.get(i);
                        String previousTarget = nameOverrides.remove(targetName);
                        if (previousTarget != null) {
                            nameOverrides.put(sourceName, previousTarget);
                        } else {
                            nameOverrides.put(sourceName, targetName);
                        }
                    }
                }
            }
        }
        return nameOverrides;
    }

    /* JADX WARNING: type inference failed for: r2v9, types: [android.view.View] */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0022, code lost:
        r14 = r10.lastIn;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void configureTransitionsReordered(android.support.v4.app.FragmentManagerImpl r23, int r24, android.support.v4.app.FragmentTransition.FragmentContainerTransition r25, android.view.View r26, android.support.v4.util.ArrayMap<java.lang.String, java.lang.String> r27) {
        /*
            r0 = r23
            r10 = r25
            r11 = r26
            r1 = 0
            android.support.v4.app.FragmentContainer r2 = r0.mContainer
            boolean r2 = r2.onHasView()
            if (r2 == 0) goto L_0x001c
            android.support.v4.app.FragmentContainer r2 = r0.mContainer
            r12 = r24
            android.view.View r2 = r2.onFindViewById(r12)
            r1 = r2
            android.view.ViewGroup r1 = (android.view.ViewGroup) r1
            r13 = r1
            goto L_0x001f
        L_0x001c:
            r12 = r24
            r13 = r1
        L_0x001f:
            if (r13 != 0) goto L_0x0022
            return
        L_0x0022:
            android.support.v4.app.Fragment r14 = r10.lastIn
            android.support.v4.app.Fragment r15 = r10.firstOut
            android.support.v4.app.FragmentTransitionImpl r9 = chooseImpl(r15, r14)
            if (r9 != 0) goto L_0x002d
            return
        L_0x002d:
            boolean r8 = r10.lastInIsPop
            boolean r7 = r10.firstOutIsPop
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r6 = r1
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r5 = r1
            java.lang.Object r4 = getEnterTransition(r9, r14, r8)
            java.lang.Object r3 = getExitTransition(r9, r15, r7)
            r1 = r9
            r2 = r13
            r16 = r3
            r3 = r26
            r17 = r4
            r4 = r27
            r18 = r5
            r5 = r25
            r19 = r6
            r6 = r18
            r20 = r7
            r7 = r19
            r21 = r8
            r8 = r17
            r0 = r9
            r9 = r16
            java.lang.Object r9 = configureSharedElementsReordered(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            if (r8 != 0) goto L_0x0070
            if (r9 != 0) goto L_0x0070
            r7 = r16
            if (r7 != 0) goto L_0x0072
            return
        L_0x0070:
            r7 = r16
        L_0x0072:
            r6 = r18
            java.util.ArrayList r5 = configureEnteringExitingViews(r0, r7, r15, r6, r11)
            r4 = r19
            java.util.ArrayList r3 = configureEnteringExitingViews(r0, r8, r14, r4, r11)
            r1 = 4
            setViewVisibility(r3, r1)
            r1 = r0
            r2 = r8
            r16 = r3
            r3 = r7
            r10 = r4
            r4 = r9
            r11 = r5
            r5 = r14
            r6 = r21
            java.lang.Object r6 = mergeTransitions(r1, r2, r3, r4, r5, r6)
            if (r6 == 0) goto L_0x00c7
            replaceHide(r0, r7, r15, r11)
            java.util.ArrayList r17 = r0.prepareSetNameOverridesReordered(r10)
            r1 = r0
            r2 = r6
            r3 = r8
            r4 = r16
            r5 = r7
            r12 = r6
            r6 = r11
            r19 = r7
            r7 = r9
            r22 = r8
            r8 = r10
            r1.scheduleRemoveTargets(r2, r3, r4, r5, r6, r7, r8)
            r0.beginDelayedTransition(r13, r12)
            r2 = r0
            r3 = r13
            r4 = r18
            r5 = r10
            r6 = r17
            r7 = r27
            r2.setNameOverridesReordered(r3, r4, r5, r6, r7)
            r1 = 0
            r2 = r16
            setViewVisibility(r2, r1)
            r1 = r18
            r0.swapSharedElementTargets(r9, r1, r10)
            goto L_0x00d0
        L_0x00c7:
            r12 = r6
            r19 = r7
            r22 = r8
            r2 = r16
            r1 = r18
        L_0x00d0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.FragmentTransition.configureTransitionsReordered(android.support.v4.app.FragmentManagerImpl, int, android.support.v4.app.FragmentTransition$FragmentContainerTransition, android.view.View, android.support.v4.util.ArrayMap):void");
    }

    private static void replaceHide(FragmentTransitionImpl impl, Object exitTransition, Fragment exitingFragment, final ArrayList<View> exitingViews) {
        if (exitingFragment != null && exitTransition != null && exitingFragment.mAdded && exitingFragment.mHidden && exitingFragment.mHiddenChanged) {
            exitingFragment.setHideReplaced(true);
            impl.scheduleHideFragmentView(exitTransition, exitingFragment.getView(), exitingViews);
            OneShotPreDrawListener.add(exitingFragment.mContainer, new Runnable() {
                public void run() {
                    FragmentTransition.setViewVisibility(exitingViews, 4);
                }
            });
        }
    }

    /* JADX WARNING: type inference failed for: r2v8, types: [android.view.View] */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0024, code lost:
        r15 = r10.lastIn;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void configureTransitionsOrdered(android.support.v4.app.FragmentManagerImpl r30, int r31, android.support.v4.app.FragmentTransition.FragmentContainerTransition r32, android.view.View r33, android.support.v4.util.ArrayMap<java.lang.String, java.lang.String> r34) {
        /*
            r0 = r30
            r10 = r32
            r11 = r33
            r12 = r34
            r1 = 0
            android.support.v4.app.FragmentContainer r2 = r0.mContainer
            boolean r2 = r2.onHasView()
            if (r2 == 0) goto L_0x001e
            android.support.v4.app.FragmentContainer r2 = r0.mContainer
            r13 = r31
            android.view.View r2 = r2.onFindViewById(r13)
            r1 = r2
            android.view.ViewGroup r1 = (android.view.ViewGroup) r1
            r14 = r1
            goto L_0x0021
        L_0x001e:
            r13 = r31
            r14 = r1
        L_0x0021:
            if (r14 != 0) goto L_0x0024
            return
        L_0x0024:
            android.support.v4.app.Fragment r15 = r10.lastIn
            android.support.v4.app.Fragment r9 = r10.firstOut
            android.support.v4.app.FragmentTransitionImpl r8 = chooseImpl(r9, r15)
            if (r8 != 0) goto L_0x002f
            return
        L_0x002f:
            boolean r7 = r10.lastInIsPop
            boolean r6 = r10.firstOutIsPop
            java.lang.Object r5 = getEnterTransition(r8, r15, r7)
            java.lang.Object r4 = getExitTransition(r8, r9, r6)
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r3 = r1
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r2 = r1
            r1 = r8
            r24 = r2
            r2 = r14
            r25 = r3
            r3 = r33
            r16 = r4
            r4 = r34
            r26 = r5
            r5 = r32
            r27 = r6
            r6 = r25
            r28 = r7
            r7 = r24
            r0 = r8
            r8 = r26
            r13 = r9
            r9 = r16
            java.lang.Object r29 = configureSharedElementsOrdered(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            r9 = r26
            if (r9 != 0) goto L_0x0074
            if (r29 != 0) goto L_0x0074
            r1 = r16
            if (r1 != 0) goto L_0x0076
            return
        L_0x0074:
            r1 = r16
        L_0x0076:
            r8 = r25
            java.util.ArrayList r25 = configureEnteringExitingViews(r0, r1, r13, r8, r11)
            if (r25 == 0) goto L_0x0088
            boolean r2 = r25.isEmpty()
            if (r2 == 0) goto L_0x0085
            goto L_0x0088
        L_0x0085:
            r26 = r1
            goto L_0x008b
        L_0x0088:
            r4 = 0
            r26 = r4
        L_0x008b:
            r0.addTarget(r9, r11)
            boolean r6 = r10.lastInIsPop
            r1 = r0
            r2 = r9
            r3 = r26
            r4 = r29
            r5 = r15
            java.lang.Object r7 = mergeTransitions(r1, r2, r3, r4, r5, r6)
            if (r7 == 0) goto L_0x00d5
            java.util.ArrayList r19 = new java.util.ArrayList
            r19.<init>()
            r16 = r0
            r17 = r7
            r18 = r9
            r20 = r26
            r21 = r25
            r22 = r29
            r23 = r24
            r16.scheduleRemoveTargets(r17, r18, r19, r20, r21, r22, r23)
            r1 = r0
            r2 = r14
            r3 = r15
            r4 = r33
            r5 = r24
            r6 = r9
            r10 = r7
            r7 = r19
            r16 = r8
            r8 = r26
            r17 = r9
            r9 = r25
            scheduleTargetChange(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            r1 = r24
            r0.setNameOverridesOrdered(r14, r1, r12)
            r0.beginDelayedTransition(r14, r10)
            r0.scheduleNameReset(r14, r1, r12)
            goto L_0x00dc
        L_0x00d5:
            r10 = r7
            r16 = r8
            r17 = r9
            r1 = r24
        L_0x00dc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.FragmentTransition.configureTransitionsOrdered(android.support.v4.app.FragmentManagerImpl, int, android.support.v4.app.FragmentTransition$FragmentContainerTransition, android.view.View, android.support.v4.util.ArrayMap):void");
    }

    private static void scheduleTargetChange(FragmentTransitionImpl impl, ViewGroup sceneRoot, Fragment inFragment, View nonExistentView, ArrayList<View> sharedElementsIn, Object enterTransition, ArrayList<View> enteringViews, Object exitTransition, ArrayList<View> exitingViews) {
        final Object obj = enterTransition;
        final FragmentTransitionImpl fragmentTransitionImpl = impl;
        final View view = nonExistentView;
        final Fragment fragment = inFragment;
        final ArrayList<View> arrayList = sharedElementsIn;
        final ArrayList<View> arrayList2 = enteringViews;
        final ArrayList<View> arrayList3 = exitingViews;
        final Object obj2 = exitTransition;
        ViewGroup viewGroup = sceneRoot;
        OneShotPreDrawListener.add(sceneRoot, new Runnable() {
            public void run() {
                Object obj = obj;
                if (obj != null) {
                    fragmentTransitionImpl.removeTarget(obj, view);
                    arrayList2.addAll(FragmentTransition.configureEnteringExitingViews(fragmentTransitionImpl, obj, fragment, arrayList, view));
                }
                if (arrayList3 != null) {
                    if (obj2 != null) {
                        ArrayList<View> tempExiting = new ArrayList<>();
                        tempExiting.add(view);
                        fragmentTransitionImpl.replaceTargets(obj2, arrayList3, tempExiting);
                    }
                    arrayList3.clear();
                    arrayList3.add(view);
                }
            }
        });
    }

    private static FragmentTransitionImpl chooseImpl(Fragment outFragment, Fragment inFragment) {
        ArrayList<Object> transitions = new ArrayList<>();
        if (outFragment != null) {
            Object exitTransition = outFragment.getExitTransition();
            if (exitTransition != null) {
                transitions.add(exitTransition);
            }
            Object returnTransition = outFragment.getReturnTransition();
            if (returnTransition != null) {
                transitions.add(returnTransition);
            }
            Object sharedReturnTransition = outFragment.getSharedElementReturnTransition();
            if (sharedReturnTransition != null) {
                transitions.add(sharedReturnTransition);
            }
        }
        if (inFragment != null) {
            Object enterTransition = inFragment.getEnterTransition();
            if (enterTransition != null) {
                transitions.add(enterTransition);
            }
            Object reenterTransition = inFragment.getReenterTransition();
            if (reenterTransition != null) {
                transitions.add(reenterTransition);
            }
            Object sharedEnterTransition = inFragment.getSharedElementEnterTransition();
            if (sharedEnterTransition != null) {
                transitions.add(sharedEnterTransition);
            }
        }
        if (transitions.isEmpty()) {
            return null;
        }
        FragmentTransitionImpl fragmentTransitionImpl = PLATFORM_IMPL;
        if (fragmentTransitionImpl != null && canHandleAll(fragmentTransitionImpl, transitions)) {
            return fragmentTransitionImpl;
        }
        FragmentTransitionImpl fragmentTransitionImpl2 = SUPPORT_IMPL;
        if (fragmentTransitionImpl2 != null && canHandleAll(fragmentTransitionImpl2, transitions)) {
            return fragmentTransitionImpl2;
        }
        if (fragmentTransitionImpl == null && fragmentTransitionImpl2 == null) {
            return null;
        }
        throw new IllegalArgumentException("Invalid Transition types");
    }

    private static boolean canHandleAll(FragmentTransitionImpl impl, List<Object> transitions) {
        int size = transitions.size();
        for (int i = 0; i < size; i++) {
            if (!impl.canHandle(transitions.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static Object getSharedElementTransition(FragmentTransitionImpl impl, Fragment inFragment, Fragment outFragment, boolean isPop) {
        Object obj;
        if (inFragment == null || outFragment == null) {
            return null;
        }
        if (isPop) {
            obj = outFragment.getSharedElementReturnTransition();
        } else {
            obj = inFragment.getSharedElementEnterTransition();
        }
        return impl.wrapTransitionInSet(impl.cloneTransition(obj));
    }

    private static Object getEnterTransition(FragmentTransitionImpl impl, Fragment inFragment, boolean isPop) {
        Object obj;
        if (inFragment == null) {
            return null;
        }
        if (isPop) {
            obj = inFragment.getReenterTransition();
        } else {
            obj = inFragment.getEnterTransition();
        }
        return impl.cloneTransition(obj);
    }

    private static Object getExitTransition(FragmentTransitionImpl impl, Fragment outFragment, boolean isPop) {
        Object obj;
        if (outFragment == null) {
            return null;
        }
        if (isPop) {
            obj = outFragment.getReturnTransition();
        } else {
            obj = outFragment.getExitTransition();
        }
        return impl.cloneTransition(obj);
    }

    private static Object configureSharedElementsReordered(FragmentTransitionImpl impl, ViewGroup sceneRoot, View nonExistentView, ArrayMap<String, String> nameOverrides, FragmentContainerTransition fragments, ArrayList<View> sharedElementsOut, ArrayList<View> sharedElementsIn, Object enterTransition, Object exitTransition) {
        Object sharedElementTransition;
        Object sharedElementTransition2;
        Object sharedElementTransition3;
        View epicenterView;
        Rect epicenter;
        ArrayMap<String, View> inSharedElements;
        FragmentTransitionImpl fragmentTransitionImpl = impl;
        View view = nonExistentView;
        ArrayMap<String, String> arrayMap = nameOverrides;
        FragmentContainerTransition fragmentContainerTransition = fragments;
        ArrayList<View> arrayList = sharedElementsOut;
        ArrayList<View> arrayList2 = sharedElementsIn;
        Object obj = enterTransition;
        Fragment inFragment = fragmentContainerTransition.lastIn;
        Fragment outFragment = fragmentContainerTransition.firstOut;
        if (inFragment != null) {
            inFragment.getView().setVisibility(0);
        }
        if (inFragment == null) {
            ViewGroup viewGroup = sceneRoot;
            Fragment fragment = outFragment;
        } else if (outFragment == null) {
            ViewGroup viewGroup2 = sceneRoot;
            Fragment fragment2 = outFragment;
        } else {
            boolean inIsPop = fragmentContainerTransition.lastInIsPop;
            if (nameOverrides.isEmpty()) {
                sharedElementTransition = null;
            } else {
                sharedElementTransition = getSharedElementTransition(fragmentTransitionImpl, inFragment, outFragment, inIsPop);
            }
            ArrayMap<String, View> outSharedElements = captureOutSharedElements(fragmentTransitionImpl, arrayMap, sharedElementTransition, fragmentContainerTransition);
            ArrayMap<String, View> inSharedElements2 = captureInSharedElements(fragmentTransitionImpl, arrayMap, sharedElementTransition, fragmentContainerTransition);
            if (nameOverrides.isEmpty()) {
                if (outSharedElements != null) {
                    outSharedElements.clear();
                }
                if (inSharedElements2 != null) {
                    inSharedElements2.clear();
                }
                sharedElementTransition2 = null;
            } else {
                addSharedElementsWithMatchingNames(arrayList, outSharedElements, nameOverrides.keySet());
                addSharedElementsWithMatchingNames(arrayList2, inSharedElements2, nameOverrides.values());
                sharedElementTransition2 = sharedElementTransition;
            }
            if (obj == null && exitTransition == null && sharedElementTransition2 == null) {
                return null;
            }
            callSharedElementStartEnd(inFragment, outFragment, inIsPop, outSharedElements, true);
            if (sharedElementTransition2 != null) {
                arrayList2.add(view);
                fragmentTransitionImpl.setSharedElementTargets(sharedElementTransition2, view, arrayList);
                sharedElementTransition3 = sharedElementTransition2;
                inSharedElements = inSharedElements2;
                ArrayMap<String, View> arrayMap2 = outSharedElements;
                setOutEpicenter(impl, sharedElementTransition2, exitTransition, outSharedElements, fragmentContainerTransition.firstOutIsPop, fragmentContainerTransition.firstOutTransaction);
                Rect epicenter2 = new Rect();
                View epicenterView2 = getInEpicenterView(inSharedElements, fragmentContainerTransition, obj, inIsPop);
                if (epicenterView2 != null) {
                    fragmentTransitionImpl.setEpicenter(obj, epicenter2);
                }
                epicenter = epicenter2;
                epicenterView = epicenterView2;
            } else {
                sharedElementTransition3 = sharedElementTransition2;
                inSharedElements = inSharedElements2;
                ArrayMap<String, View> arrayMap3 = outSharedElements;
                epicenter = null;
                epicenterView = null;
            }
            final Fragment fragment3 = inFragment;
            final Fragment fragment4 = outFragment;
            final boolean z = inIsPop;
            final ArrayMap<String, View> arrayMap4 = inSharedElements;
            AnonymousClass3 r8 = r0;
            final View view2 = epicenterView;
            boolean z2 = inIsPop;
            final FragmentTransitionImpl fragmentTransitionImpl2 = impl;
            Fragment fragment5 = outFragment;
            final Rect rect = epicenter;
            AnonymousClass3 r0 = new Runnable() {
                public void run() {
                    FragmentTransition.callSharedElementStartEnd(fragment3, fragment4, z, arrayMap4, false);
                    View view = view2;
                    if (view != null) {
                        fragmentTransitionImpl2.getBoundsOnScreen(view, rect);
                    }
                }
            };
            OneShotPreDrawListener.add(sceneRoot, r8);
            return sharedElementTransition3;
        }
        return null;
    }

    private static void addSharedElementsWithMatchingNames(ArrayList<View> views, ArrayMap<String, View> sharedElements, Collection<String> nameOverridesSet) {
        for (int i = sharedElements.size() - 1; i >= 0; i--) {
            View view = sharedElements.valueAt(i);
            if (nameOverridesSet.contains(ViewCompat.getTransitionName(view))) {
                views.add(view);
            }
        }
    }

    private static Object configureSharedElementsOrdered(FragmentTransitionImpl impl, ViewGroup sceneRoot, View nonExistentView, ArrayMap<String, String> nameOverrides, FragmentContainerTransition fragments, ArrayList<View> sharedElementsOut, ArrayList<View> sharedElementsIn, Object enterTransition, Object exitTransition) {
        Object sharedElementTransition;
        Object sharedElementTransition2;
        Rect inEpicenter;
        FragmentTransitionImpl fragmentTransitionImpl = impl;
        FragmentContainerTransition fragmentContainerTransition = fragments;
        ArrayList<View> arrayList = sharedElementsOut;
        Object obj = enterTransition;
        Fragment inFragment = fragmentContainerTransition.lastIn;
        Fragment outFragment = fragmentContainerTransition.firstOut;
        if (inFragment == null) {
            ViewGroup viewGroup = sceneRoot;
            Fragment fragment = outFragment;
            Fragment fragment2 = inFragment;
        } else if (outFragment == null) {
            ViewGroup viewGroup2 = sceneRoot;
            Fragment fragment3 = outFragment;
            Fragment fragment4 = inFragment;
        } else {
            final boolean inIsPop = fragmentContainerTransition.lastInIsPop;
            if (nameOverrides.isEmpty()) {
                sharedElementTransition = null;
            } else {
                sharedElementTransition = getSharedElementTransition(fragmentTransitionImpl, inFragment, outFragment, inIsPop);
            }
            ArrayMap<String, View> outSharedElements = captureOutSharedElements(fragmentTransitionImpl, nameOverrides, sharedElementTransition, fragmentContainerTransition);
            if (nameOverrides.isEmpty()) {
                sharedElementTransition2 = null;
            } else {
                arrayList.addAll(outSharedElements.values());
                sharedElementTransition2 = sharedElementTransition;
            }
            if (obj == null && exitTransition == null && sharedElementTransition2 == null) {
                return null;
            }
            callSharedElementStartEnd(inFragment, outFragment, inIsPop, outSharedElements, true);
            if (sharedElementTransition2 != null) {
                Rect inEpicenter2 = new Rect();
                fragmentTransitionImpl.setSharedElementTargets(sharedElementTransition2, nonExistentView, arrayList);
                ArrayMap<String, View> arrayMap = outSharedElements;
                Rect inEpicenter3 = inEpicenter2;
                setOutEpicenter(impl, sharedElementTransition2, exitTransition, outSharedElements, fragmentContainerTransition.firstOutIsPop, fragmentContainerTransition.firstOutTransaction);
                if (obj != null) {
                    fragmentTransitionImpl.setEpicenter(obj, inEpicenter3);
                }
                inEpicenter = inEpicenter3;
            } else {
                inEpicenter = null;
            }
            final Object finalSharedElementTransition = sharedElementTransition2;
            final FragmentTransitionImpl fragmentTransitionImpl2 = impl;
            final ArrayMap<String, String> arrayMap2 = nameOverrides;
            final FragmentContainerTransition fragmentContainerTransition2 = fragments;
            final ArrayList<View> arrayList2 = sharedElementsIn;
            Object sharedElementTransition3 = sharedElementTransition2;
            final View view = nonExistentView;
            AnonymousClass4 r13 = r0;
            final Fragment fragment5 = inFragment;
            final Fragment fragment6 = outFragment;
            boolean z = inIsPop;
            Fragment fragment7 = outFragment;
            final ArrayList<View> arrayList3 = sharedElementsOut;
            Fragment fragment8 = inFragment;
            final Object obj2 = enterTransition;
            final Rect rect = inEpicenter;
            AnonymousClass4 r0 = new Runnable() {
                public void run() {
                    ArrayMap<String, View> inSharedElements = FragmentTransition.captureInSharedElements(fragmentTransitionImpl2, arrayMap2, finalSharedElementTransition, fragmentContainerTransition2);
                    if (inSharedElements != null) {
                        arrayList2.addAll(inSharedElements.values());
                        arrayList2.add(view);
                    }
                    FragmentTransition.callSharedElementStartEnd(fragment5, fragment6, inIsPop, inSharedElements, false);
                    Object obj = finalSharedElementTransition;
                    if (obj != null) {
                        fragmentTransitionImpl2.swapSharedElementTargets(obj, arrayList3, arrayList2);
                        View inEpicenterView = FragmentTransition.getInEpicenterView(inSharedElements, fragmentContainerTransition2, obj2, inIsPop);
                        if (inEpicenterView != null) {
                            fragmentTransitionImpl2.getBoundsOnScreen(inEpicenterView, rect);
                        }
                    }
                }
            };
            OneShotPreDrawListener.add(sceneRoot, r13);
            return sharedElementTransition3;
        }
        return null;
    }

    private static ArrayMap<String, View> captureOutSharedElements(FragmentTransitionImpl impl, ArrayMap<String, String> nameOverrides, Object sharedElementTransition, FragmentContainerTransition fragments) {
        ArrayList<String> names;
        SharedElementCallback sharedElementCallback;
        if (nameOverrides.isEmpty() || sharedElementTransition == null) {
            nameOverrides.clear();
            return null;
        }
        Fragment outFragment = fragments.firstOut;
        ArrayMap<String, View> outSharedElements = new ArrayMap<>();
        impl.findNamedViews(outSharedElements, outFragment.getView());
        BackStackRecord outTransaction = fragments.firstOutTransaction;
        if (fragments.firstOutIsPop) {
            sharedElementCallback = outFragment.getEnterTransitionCallback();
            names = outTransaction.mSharedElementTargetNames;
        } else {
            sharedElementCallback = outFragment.getExitTransitionCallback();
            names = outTransaction.mSharedElementSourceNames;
        }
        outSharedElements.retainAll(names);
        if (sharedElementCallback != null) {
            sharedElementCallback.onMapSharedElements(names, outSharedElements);
            for (int i = names.size() - 1; i >= 0; i--) {
                String name = names.get(i);
                View view = outSharedElements.get(name);
                if (view == null) {
                    nameOverrides.remove(name);
                } else if (!name.equals(ViewCompat.getTransitionName(view))) {
                    nameOverrides.put(ViewCompat.getTransitionName(view), nameOverrides.remove(name));
                }
            }
        } else {
            nameOverrides.retainAll(outSharedElements.keySet());
        }
        return outSharedElements;
    }

    static ArrayMap<String, View> captureInSharedElements(FragmentTransitionImpl impl, ArrayMap<String, String> nameOverrides, Object sharedElementTransition, FragmentContainerTransition fragments) {
        ArrayList<String> names;
        SharedElementCallback sharedElementCallback;
        String key;
        Fragment inFragment = fragments.lastIn;
        View fragmentView = inFragment.getView();
        if (nameOverrides.isEmpty() || sharedElementTransition == null || fragmentView == null) {
            nameOverrides.clear();
            return null;
        }
        ArrayMap<String, View> inSharedElements = new ArrayMap<>();
        impl.findNamedViews(inSharedElements, fragmentView);
        BackStackRecord inTransaction = fragments.lastInTransaction;
        if (fragments.lastInIsPop) {
            sharedElementCallback = inFragment.getExitTransitionCallback();
            names = inTransaction.mSharedElementSourceNames;
        } else {
            sharedElementCallback = inFragment.getEnterTransitionCallback();
            names = inTransaction.mSharedElementTargetNames;
        }
        if (names != null) {
            inSharedElements.retainAll(names);
            inSharedElements.retainAll(nameOverrides.values());
        }
        if (sharedElementCallback != null) {
            sharedElementCallback.onMapSharedElements(names, inSharedElements);
            for (int i = names.size() - 1; i >= 0; i--) {
                String name = names.get(i);
                View view = inSharedElements.get(name);
                if (view == null) {
                    String key2 = findKeyForValue(nameOverrides, name);
                    if (key2 != null) {
                        nameOverrides.remove(key2);
                    }
                } else if (!name.equals(ViewCompat.getTransitionName(view)) && (key = findKeyForValue(nameOverrides, name)) != null) {
                    nameOverrides.put(key, ViewCompat.getTransitionName(view));
                }
            }
        } else {
            retainValues(nameOverrides, inSharedElements);
        }
        return inSharedElements;
    }

    private static String findKeyForValue(ArrayMap<String, String> map, String value) {
        int numElements = map.size();
        for (int i = 0; i < numElements; i++) {
            if (value.equals(map.valueAt(i))) {
                return map.keyAt(i);
            }
        }
        return null;
    }

    static View getInEpicenterView(ArrayMap<String, View> inSharedElements, FragmentContainerTransition fragments, Object enterTransition, boolean inIsPop) {
        String targetName;
        BackStackRecord inTransaction = fragments.lastInTransaction;
        if (enterTransition == null || inSharedElements == null || inTransaction.mSharedElementSourceNames == null || inTransaction.mSharedElementSourceNames.isEmpty()) {
            return null;
        }
        if (inIsPop) {
            targetName = inTransaction.mSharedElementSourceNames.get(0);
        } else {
            targetName = inTransaction.mSharedElementTargetNames.get(0);
        }
        return inSharedElements.get(targetName);
    }

    private static void setOutEpicenter(FragmentTransitionImpl impl, Object sharedElementTransition, Object exitTransition, ArrayMap<String, View> outSharedElements, boolean outIsPop, BackStackRecord outTransaction) {
        String sourceName;
        if (outTransaction.mSharedElementSourceNames != null && !outTransaction.mSharedElementSourceNames.isEmpty()) {
            if (outIsPop) {
                sourceName = outTransaction.mSharedElementTargetNames.get(0);
            } else {
                sourceName = outTransaction.mSharedElementSourceNames.get(0);
            }
            View outEpicenterView = outSharedElements.get(sourceName);
            impl.setEpicenter(sharedElementTransition, outEpicenterView);
            if (exitTransition != null) {
                impl.setEpicenter(exitTransition, outEpicenterView);
            }
        }
    }

    private static void retainValues(ArrayMap<String, String> nameOverrides, ArrayMap<String, View> namedViews) {
        for (int i = nameOverrides.size() - 1; i >= 0; i--) {
            if (!namedViews.containsKey(nameOverrides.valueAt(i))) {
                nameOverrides.removeAt(i);
            }
        }
    }

    static void callSharedElementStartEnd(Fragment inFragment, Fragment outFragment, boolean isPop, ArrayMap<String, View> sharedElements, boolean isStart) {
        SharedElementCallback sharedElementCallback;
        if (isPop) {
            sharedElementCallback = outFragment.getEnterTransitionCallback();
        } else {
            sharedElementCallback = inFragment.getEnterTransitionCallback();
        }
        if (sharedElementCallback != null) {
            ArrayList<View> views = new ArrayList<>();
            ArrayList<String> names = new ArrayList<>();
            int count = sharedElements == null ? 0 : sharedElements.size();
            for (int i = 0; i < count; i++) {
                names.add(sharedElements.keyAt(i));
                views.add(sharedElements.valueAt(i));
            }
            if (isStart) {
                sharedElementCallback.onSharedElementStart(names, views, (List<View>) null);
            } else {
                sharedElementCallback.onSharedElementEnd(names, views, (List<View>) null);
            }
        }
    }

    static ArrayList<View> configureEnteringExitingViews(FragmentTransitionImpl impl, Object transition, Fragment fragment, ArrayList<View> sharedElements, View nonExistentView) {
        ArrayList<View> viewList = null;
        if (transition != null) {
            viewList = new ArrayList<>();
            View root = fragment.getView();
            if (root != null) {
                impl.captureTransitioningViews(viewList, root);
            }
            if (sharedElements != null) {
                viewList.removeAll(sharedElements);
            }
            if (!viewList.isEmpty()) {
                viewList.add(nonExistentView);
                impl.addTargets(transition, viewList);
            }
        }
        return viewList;
    }

    static void setViewVisibility(ArrayList<View> views, int visibility) {
        if (views != null) {
            for (int i = views.size() - 1; i >= 0; i--) {
                views.get(i).setVisibility(visibility);
            }
        }
    }

    private static Object mergeTransitions(FragmentTransitionImpl impl, Object enterTransition, Object exitTransition, Object sharedElementTransition, Fragment inFragment, boolean isPop) {
        boolean z;
        boolean overlap = true;
        if (!(enterTransition == null || exitTransition == null || inFragment == null)) {
            if (isPop) {
                z = inFragment.getAllowReturnTransitionOverlap();
            } else {
                z = inFragment.getAllowEnterTransitionOverlap();
            }
            overlap = z;
        }
        if (overlap) {
            return impl.mergeTransitionsTogether(exitTransition, enterTransition, sharedElementTransition);
        }
        return impl.mergeTransitionsInSequence(exitTransition, enterTransition, sharedElementTransition);
    }

    public static void calculateFragments(BackStackRecord transaction, SparseArray<FragmentContainerTransition> transitioningFragments, boolean isReordered) {
        int numOps = transaction.mOps.size();
        for (int opNum = 0; opNum < numOps; opNum++) {
            addToFirstInLastOut(transaction, transaction.mOps.get(opNum), transitioningFragments, false, isReordered);
        }
    }

    public static void calculatePopFragments(BackStackRecord transaction, SparseArray<FragmentContainerTransition> transitioningFragments, boolean isReordered) {
        if (transaction.mManager.mContainer.onHasView()) {
            for (int opNum = transaction.mOps.size() - 1; opNum >= 0; opNum--) {
                addToFirstInLastOut(transaction, transaction.mOps.get(opNum), transitioningFragments, true, isReordered);
            }
        }
    }

    static boolean supportsTransition() {
        return (PLATFORM_IMPL == null && SUPPORT_IMPL == null) ? false : true;
    }

    private static void addToFirstInLastOut(BackStackRecord transaction, BackStackRecord.Op op, SparseArray<FragmentContainerTransition> transitioningFragments, boolean isPop, boolean isReorderedTransaction) {
        int containerId;
        boolean wasAdded;
        boolean setFirstOut;
        boolean wasRemoved;
        boolean setLastIn;
        FragmentContainerTransition containerTransition;
        FragmentContainerTransition containerTransition2;
        Fragment fragment;
        FragmentContainerTransition containerTransition3;
        FragmentContainerTransition containerTransition4;
        boolean setLastIn2;
        boolean setFirstOut2;
        boolean setFirstOut3;
        boolean setLastIn3;
        BackStackRecord backStackRecord = transaction;
        BackStackRecord.Op op2 = op;
        SparseArray<FragmentContainerTransition> sparseArray = transitioningFragments;
        boolean z = isPop;
        Fragment fragment2 = op2.fragment;
        if (fragment2 != null && (containerId = fragment2.mContainerId) != 0) {
            boolean z2 = false;
            switch (z ? INVERSE_OPS[op2.cmd] : op2.cmd) {
                case 1:
                case 7:
                    if (isReorderedTransaction) {
                        setLastIn2 = fragment2.mIsNewlyAdded;
                    } else {
                        if (!fragment2.mAdded && !fragment2.mHidden) {
                            z2 = true;
                        }
                        setLastIn2 = z2;
                    }
                    setLastIn = setLastIn2;
                    wasRemoved = false;
                    setFirstOut = false;
                    wasAdded = true;
                    break;
                case 3:
                case 6:
                    if (isReorderedTransaction) {
                        if (!fragment2.mAdded && fragment2.mView != null && fragment2.mView.getVisibility() == 0 && fragment2.mPostponedAlpha >= 0.0f) {
                            z2 = true;
                        }
                        setFirstOut2 = z2;
                    } else {
                        if (fragment2.mAdded && !fragment2.mHidden) {
                            z2 = true;
                        }
                        setFirstOut2 = z2;
                    }
                    setLastIn = false;
                    wasRemoved = true;
                    setFirstOut = setFirstOut2;
                    wasAdded = false;
                    break;
                case 4:
                    if (isReorderedTransaction) {
                        if (fragment2.mHiddenChanged && fragment2.mAdded && fragment2.mHidden) {
                            z2 = true;
                        }
                        setFirstOut3 = z2;
                    } else {
                        if (fragment2.mAdded && !fragment2.mHidden) {
                            z2 = true;
                        }
                        setFirstOut3 = z2;
                    }
                    setLastIn = false;
                    wasRemoved = true;
                    setFirstOut = setFirstOut3;
                    wasAdded = false;
                    break;
                case 5:
                    if (isReorderedTransaction) {
                        if (fragment2.mHiddenChanged && !fragment2.mHidden && fragment2.mAdded) {
                            z2 = true;
                        }
                        setLastIn3 = z2;
                    } else {
                        setLastIn3 = fragment2.mHidden;
                    }
                    setLastIn = setLastIn3;
                    wasRemoved = false;
                    setFirstOut = false;
                    wasAdded = true;
                    break;
                default:
                    setLastIn = false;
                    wasRemoved = false;
                    setFirstOut = false;
                    wasAdded = false;
                    break;
            }
            FragmentContainerTransition containerTransition5 = sparseArray.get(containerId);
            if (setLastIn) {
                FragmentContainerTransition containerTransition6 = ensureContainer(containerTransition5, sparseArray, containerId);
                containerTransition6.lastIn = fragment2;
                containerTransition6.lastInIsPop = z;
                containerTransition6.lastInTransaction = backStackRecord;
                containerTransition = containerTransition6;
            } else {
                containerTransition = containerTransition5;
            }
            if (isReorderedTransaction || !wasAdded) {
                fragment = null;
                containerTransition2 = containerTransition;
            } else {
                if (containerTransition != null && containerTransition.firstOut == fragment2) {
                    containerTransition.firstOut = null;
                }
                FragmentManagerImpl manager = backStackRecord.mManager;
                if (fragment2.mState >= 1 || manager.mCurState < 1 || backStackRecord.mReorderingAllowed) {
                    fragment = null;
                    containerTransition2 = containerTransition;
                } else {
                    manager.makeActive(fragment2);
                    FragmentManagerImpl fragmentManagerImpl = manager;
                    containerTransition2 = containerTransition;
                    fragment = null;
                    manager.moveToState(fragment2, 1, 0, 0, false);
                }
            }
            if (setFirstOut) {
                containerTransition4 = containerTransition2;
                if (containerTransition4 == null || containerTransition4.firstOut == null) {
                    containerTransition3 = ensureContainer(containerTransition4, sparseArray, containerId);
                    containerTransition3.firstOut = fragment2;
                    containerTransition3.firstOutIsPop = z;
                    containerTransition3.firstOutTransaction = backStackRecord;
                    if (!isReorderedTransaction && wasRemoved && containerTransition3 != null && containerTransition3.lastIn == fragment2) {
                        containerTransition3.lastIn = fragment;
                        return;
                    }
                    return;
                }
            } else {
                containerTransition4 = containerTransition2;
            }
            containerTransition3 = containerTransition4;
            if (!isReorderedTransaction) {
            }
        }
    }

    private static FragmentContainerTransition ensureContainer(FragmentContainerTransition containerTransition, SparseArray<FragmentContainerTransition> transitioningFragments, int containerId) {
        if (containerTransition != null) {
            return containerTransition;
        }
        FragmentContainerTransition containerTransition2 = new FragmentContainerTransition();
        transitioningFragments.put(containerId, containerTransition2);
        return containerTransition2;
    }

    static class FragmentContainerTransition {
        public Fragment firstOut;
        public boolean firstOutIsPop;
        public BackStackRecord firstOutTransaction;
        public Fragment lastIn;
        public boolean lastInIsPop;
        public BackStackRecord lastInTransaction;

        FragmentContainerTransition() {
        }
    }

    private FragmentTransition() {
    }
}
