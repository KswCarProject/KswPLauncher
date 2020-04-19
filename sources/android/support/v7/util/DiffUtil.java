package android.support.v7.util;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DiffUtil {
    private static final Comparator<Snake> SNAKE_COMPARATOR = new Comparator<Snake>() {
        public int compare(Snake o1, Snake o2) {
            int cmpX = o1.x - o2.x;
            return cmpX == 0 ? o1.y - o2.y : cmpX;
        }
    };

    private DiffUtil() {
    }

    @NonNull
    public static DiffResult calculateDiff(@NonNull Callback cb) {
        return calculateDiff(cb, true);
    }

    @NonNull
    public static DiffResult calculateDiff(@NonNull Callback cb, boolean detectMoves) {
        int oldSize = cb.getOldListSize();
        int newSize = cb.getNewListSize();
        List<Snake> snakes = new ArrayList<>();
        List<Range> stack = new ArrayList<>();
        stack.add(new Range(0, oldSize, 0, newSize));
        int max = oldSize + newSize + Math.abs(oldSize - newSize);
        int[] forward = new int[(max * 2)];
        int[] backward = new int[(max * 2)];
        List<Range> rangePool = new ArrayList<>();
        while (true) {
            List<Range> list = rangePool;
            if (!stack.isEmpty()) {
                Range range = stack.remove(stack.size() - 1);
                Snake snake = diffPartial(cb, range.oldListStart, range.oldListEnd, range.newListStart, range.newListEnd, forward, backward, max);
                if (snake != null) {
                    if (snake.size > 0) {
                        snakes.add(snake);
                    }
                    snake.x += range.oldListStart;
                    snake.y += range.newListStart;
                    Range left = list.isEmpty() ? new Range() : list.remove(list.size() - 1);
                    left.oldListStart = range.oldListStart;
                    left.newListStart = range.newListStart;
                    if (snake.reverse) {
                        left.oldListEnd = snake.x;
                        left.newListEnd = snake.y;
                    } else if (snake.removal) {
                        left.oldListEnd = snake.x - 1;
                        left.newListEnd = snake.y;
                    } else {
                        left.oldListEnd = snake.x;
                        left.newListEnd = snake.y - 1;
                    }
                    stack.add(left);
                    Range right = range;
                    if (!snake.reverse) {
                        right.oldListStart = snake.x + snake.size;
                        right.newListStart = snake.y + snake.size;
                    } else if (snake.removal) {
                        right.oldListStart = snake.x + snake.size + 1;
                        right.newListStart = snake.y + snake.size;
                    } else {
                        right.oldListStart = snake.x + snake.size;
                        right.newListStart = snake.y + snake.size + 1;
                    }
                    stack.add(right);
                } else {
                    list.add(range);
                }
                rangePool = list;
            } else {
                Collections.sort(snakes, SNAKE_COMPARATOR);
                List<Range> list2 = list;
                int[] iArr = backward;
                int[] iArr2 = forward;
                return new DiffResult(cb, snakes, forward, backward, detectMoves);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0053, code lost:
        if (r4[(r27 + r13) - 1] < r4[(r27 + r13) + 1]) goto L_0x0060;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00f6, code lost:
        if (r5[(r27 + r3) - 1] < r5[(r27 + r3) + 1]) goto L_0x0103;
     */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00cf A[LOOP:1: B:12:0x0040->B:42:0x00cf, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x015f A[LOOP:3: B:44:0x00df->B:71:0x015f, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x00ae A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x013e A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x008d A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.support.v7.util.DiffUtil.Snake diffPartial(android.support.v7.util.DiffUtil.Callback r20, int r21, int r22, int r23, int r24, int[] r25, int[] r26, int r27) {
        /*
            r0 = r20
            r4 = r25
            r5 = r26
            int r6 = r22 - r21
            int r7 = r24 - r23
            int r8 = r22 - r21
            r9 = 1
            if (r8 < r9) goto L_0x017c
            int r8 = r24 - r23
            if (r8 >= r9) goto L_0x0019
            r18 = r6
            r19 = r7
            goto L_0x0180
        L_0x0019:
            int r8 = r6 - r7
            int r10 = r6 + r7
            int r10 = r10 + r9
            int r10 = r10 / 2
            int r11 = r27 - r10
            int r11 = r11 - r9
            int r12 = r27 + r10
            int r12 = r12 + r9
            r13 = 0
            java.util.Arrays.fill(r4, r11, r12, r13)
            int r11 = r27 - r10
            int r11 = r11 - r9
            int r11 = r11 + r8
            int r12 = r27 + r10
            int r12 = r12 + r9
            int r12 = r12 + r8
            java.util.Arrays.fill(r5, r11, r12, r6)
            int r11 = r8 % 2
            if (r11 == 0) goto L_0x003b
            r11 = r9
            goto L_0x003c
        L_0x003b:
            r11 = r13
        L_0x003c:
            r12 = r13
        L_0x003d:
            if (r12 > r10) goto L_0x0170
            int r13 = -r12
        L_0x0040:
            if (r13 > r12) goto L_0x00d9
            int r9 = -r12
            if (r13 == r9) goto L_0x005f
            if (r13 == r12) goto L_0x0056
            int r9 = r27 + r13
            r15 = 1
            int r9 = r9 - r15
            r9 = r4[r9]
            int r16 = r27 + r13
            int r16 = r16 + 1
            r2 = r4[r16]
            if (r9 >= r2) goto L_0x0057
            goto L_0x0060
        L_0x0056:
            r15 = 1
        L_0x0057:
            int r2 = r27 + r13
            int r2 = r2 - r15
            r2 = r4[r2]
            int r2 = r2 + r15
            r9 = r15
            goto L_0x0066
        L_0x005f:
            r15 = 1
        L_0x0060:
            int r2 = r27 + r13
            int r2 = r2 + r15
            r2 = r4[r2]
            r9 = 0
        L_0x0066:
            int r16 = r2 - r13
        L_0x0069:
            r17 = r16
            if (r2 >= r6) goto L_0x008d
            r3 = r17
            if (r3 >= r7) goto L_0x0088
            r18 = r6
            int r6 = r21 + r2
            r19 = r7
            int r7 = r23 + r3
            boolean r6 = r0.areItemsTheSame(r6, r7)
            if (r6 == 0) goto L_0x0093
            int r2 = r2 + 1
            int r16 = r3 + 1
            r6 = r18
            r7 = r19
            goto L_0x0069
        L_0x0088:
            r18 = r6
            r19 = r7
            goto L_0x0093
        L_0x008d:
            r18 = r6
            r19 = r7
            r3 = r17
        L_0x0093:
            int r6 = r27 + r13
            r4[r6] = r2
            if (r11 == 0) goto L_0x00cf
            int r6 = r8 - r12
            r7 = 1
            int r6 = r6 + r7
            if (r13 < r6) goto L_0x00cf
            int r6 = r8 + r12
            int r6 = r6 - r7
            if (r13 > r6) goto L_0x00cf
            int r6 = r27 + r13
            r6 = r4[r6]
            int r7 = r27 + r13
            r7 = r5[r7]
            if (r6 < r7) goto L_0x00cf
            android.support.v7.util.DiffUtil$Snake r6 = new android.support.v7.util.DiffUtil$Snake
            r6.<init>()
            int r7 = r27 + r13
            r7 = r5[r7]
            r6.x = r7
            int r7 = r6.x
            int r7 = r7 - r13
            r6.y = r7
            int r7 = r27 + r13
            r7 = r4[r7]
            int r15 = r27 + r13
            r15 = r5[r15]
            int r7 = r7 - r15
            r6.size = r7
            r6.removal = r9
            r7 = 0
            r6.reverse = r7
            return r6
        L_0x00cf:
            r7 = 0
            int r13 = r13 + 2
            r6 = r18
            r7 = r19
            r9 = 1
            goto L_0x0040
        L_0x00d9:
            r18 = r6
            r19 = r7
            r7 = 0
            int r2 = -r12
        L_0x00df:
            if (r2 > r12) goto L_0x0165
            int r3 = r2 + r8
            int r6 = r12 + r8
            if (r3 == r6) goto L_0x0102
            int r6 = -r12
            int r6 = r6 + r8
            if (r3 == r6) goto L_0x00f9
            int r6 = r27 + r3
            r15 = 1
            int r6 = r6 - r15
            r6 = r5[r6]
            int r9 = r27 + r3
            int r9 = r9 + r15
            r9 = r5[r9]
            if (r6 >= r9) goto L_0x00fa
            goto L_0x0103
        L_0x00f9:
            r15 = 1
        L_0x00fa:
            int r6 = r27 + r3
            int r6 = r6 + r15
            r6 = r5[r6]
            int r6 = r6 - r15
            r9 = r15
            goto L_0x0109
        L_0x0102:
            r15 = 1
        L_0x0103:
            int r6 = r27 + r3
            int r6 = r6 - r15
            r6 = r5[r6]
            r9 = 0
        L_0x0109:
            int r13 = r6 - r3
        L_0x010c:
            if (r6 <= 0) goto L_0x0125
            if (r13 <= 0) goto L_0x0125
            int r14 = r21 + r6
            r15 = 1
            int r7 = r14 + -1
            int r14 = r23 + r13
            int r1 = r14 + -1
            boolean r1 = r0.areItemsTheSame(r7, r1)
            if (r1 == 0) goto L_0x0125
            int r6 = r6 + -1
            int r13 = r13 + -1
            r7 = 0
            goto L_0x010c
        L_0x0125:
            int r1 = r27 + r3
            r5[r1] = r6
            if (r11 != 0) goto L_0x015f
            int r1 = r2 + r8
            int r7 = -r12
            if (r1 < r7) goto L_0x015f
            int r1 = r2 + r8
            if (r1 > r12) goto L_0x015f
            int r1 = r27 + r3
            r1 = r4[r1]
            int r7 = r27 + r3
            r7 = r5[r7]
            if (r1 < r7) goto L_0x015f
            android.support.v7.util.DiffUtil$Snake r1 = new android.support.v7.util.DiffUtil$Snake
            r1.<init>()
            int r7 = r27 + r3
            r7 = r5[r7]
            r1.x = r7
            int r7 = r1.x
            int r7 = r7 - r3
            r1.y = r7
            int r7 = r27 + r3
            r7 = r4[r7]
            int r14 = r27 + r3
            r14 = r5[r14]
            int r7 = r7 - r14
            r1.size = r7
            r1.removal = r9
            r7 = 1
            r1.reverse = r7
            return r1
        L_0x015f:
            r7 = 1
            int r2 = r2 + 2
            r7 = 0
            goto L_0x00df
        L_0x0165:
            r7 = 1
            int r12 = r12 + 1
            r9 = r7
            r6 = r18
            r7 = r19
            r13 = 0
            goto L_0x003d
        L_0x0170:
            r18 = r6
            r19 = r7
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "DiffUtil hit an unexpected case while trying to calculate the optimal path. Please make sure your data is not changing during the diff calculation."
            r1.<init>(r2)
            throw r1
        L_0x017c:
            r18 = r6
            r19 = r7
        L_0x0180:
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.util.DiffUtil.diffPartial(android.support.v7.util.DiffUtil$Callback, int, int, int, int, int[], int[], int):android.support.v7.util.DiffUtil$Snake");
    }

    public static abstract class Callback {
        public abstract boolean areContentsTheSame(int i, int i2);

        public abstract boolean areItemsTheSame(int i, int i2);

        public abstract int getNewListSize();

        public abstract int getOldListSize();

        @Nullable
        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
            return null;
        }
    }

    public static abstract class ItemCallback<T> {
        public abstract boolean areContentsTheSame(@NonNull T t, @NonNull T t2);

        public abstract boolean areItemsTheSame(@NonNull T t, @NonNull T t2);

        @Nullable
        public Object getChangePayload(@NonNull T t, @NonNull T t2) {
            return null;
        }
    }

    static class Snake {
        boolean removal;
        boolean reverse;
        int size;
        int x;
        int y;

        Snake() {
        }
    }

    static class Range {
        int newListEnd;
        int newListStart;
        int oldListEnd;
        int oldListStart;

        public Range() {
        }

        public Range(int oldListStart2, int oldListEnd2, int newListStart2, int newListEnd2) {
            this.oldListStart = oldListStart2;
            this.oldListEnd = oldListEnd2;
            this.newListStart = newListStart2;
            this.newListEnd = newListEnd2;
        }
    }

    public static class DiffResult {
        private static final int FLAG_CHANGED = 2;
        private static final int FLAG_IGNORE = 16;
        private static final int FLAG_MASK = 31;
        private static final int FLAG_MOVED_CHANGED = 4;
        private static final int FLAG_MOVED_NOT_CHANGED = 8;
        private static final int FLAG_NOT_CHANGED = 1;
        private static final int FLAG_OFFSET = 5;
        public static final int NO_POSITION = -1;
        private final Callback mCallback;
        private final boolean mDetectMoves;
        private final int[] mNewItemStatuses;
        private final int mNewListSize;
        private final int[] mOldItemStatuses;
        private final int mOldListSize;
        private final List<Snake> mSnakes;

        DiffResult(Callback callback, List<Snake> snakes, int[] oldItemStatuses, int[] newItemStatuses, boolean detectMoves) {
            this.mSnakes = snakes;
            this.mOldItemStatuses = oldItemStatuses;
            this.mNewItemStatuses = newItemStatuses;
            Arrays.fill(this.mOldItemStatuses, 0);
            Arrays.fill(this.mNewItemStatuses, 0);
            this.mCallback = callback;
            this.mOldListSize = callback.getOldListSize();
            this.mNewListSize = callback.getNewListSize();
            this.mDetectMoves = detectMoves;
            addRootSnake();
            findMatchingItems();
        }

        private void addRootSnake() {
            Snake firstSnake = this.mSnakes.isEmpty() ? null : this.mSnakes.get(0);
            if (firstSnake == null || firstSnake.x != 0 || firstSnake.y != 0) {
                Snake root = new Snake();
                root.x = 0;
                root.y = 0;
                root.removal = false;
                root.size = 0;
                root.reverse = false;
                this.mSnakes.add(0, root);
            }
        }

        private void findMatchingItems() {
            int posOld = this.mOldListSize;
            int posNew = this.mNewListSize;
            for (int i = this.mSnakes.size() - 1; i >= 0; i--) {
                Snake snake = this.mSnakes.get(i);
                int endX = snake.x + snake.size;
                int endY = snake.y + snake.size;
                if (this.mDetectMoves) {
                    while (posOld > endX) {
                        findAddition(posOld, posNew, i);
                        posOld--;
                    }
                    while (posNew > endY) {
                        findRemoval(posOld, posNew, i);
                        posNew--;
                    }
                }
                for (int j = 0; j < snake.size; j++) {
                    int oldItemPos = snake.x + j;
                    int newItemPos = snake.y + j;
                    int changeFlag = this.mCallback.areContentsTheSame(oldItemPos, newItemPos) ? 1 : 2;
                    this.mOldItemStatuses[oldItemPos] = (newItemPos << 5) | changeFlag;
                    this.mNewItemStatuses[newItemPos] = (oldItemPos << 5) | changeFlag;
                }
                posOld = snake.x;
                posNew = snake.y;
            }
        }

        private void findAddition(int x, int y, int snakeIndex) {
            if (this.mOldItemStatuses[x - 1] == 0) {
                findMatchingItem(x, y, snakeIndex, false);
            }
        }

        private void findRemoval(int x, int y, int snakeIndex) {
            if (this.mNewItemStatuses[y - 1] == 0) {
                findMatchingItem(x, y, snakeIndex, true);
            }
        }

        public int convertOldPositionToNew(@IntRange(from = 0) int oldListPosition) {
            if (oldListPosition < 0 || oldListPosition >= this.mOldItemStatuses.length) {
                throw new IndexOutOfBoundsException("Index out of bounds - passed position = " + oldListPosition + ", old list size = " + this.mOldItemStatuses.length);
            }
            int status = this.mOldItemStatuses[oldListPosition];
            if ((status & 31) == 0) {
                return -1;
            }
            return status >> 5;
        }

        public int convertNewPositionToOld(@IntRange(from = 0) int newListPosition) {
            if (newListPosition < 0 || newListPosition >= this.mNewItemStatuses.length) {
                throw new IndexOutOfBoundsException("Index out of bounds - passed position = " + newListPosition + ", new list size = " + this.mNewItemStatuses.length);
            }
            int status = this.mNewItemStatuses[newListPosition];
            if ((status & 31) == 0) {
                return -1;
            }
            return status >> 5;
        }

        private boolean findMatchingItem(int x, int y, int snakeIndex, boolean removal) {
            int curY;
            int curX;
            int myItemPos;
            if (removal) {
                myItemPos = y - 1;
                curX = x;
                curY = y - 1;
            } else {
                myItemPos = x - 1;
                curX = x - 1;
                curY = y;
            }
            int curY2 = curY;
            int curX2 = curX;
            for (int i = snakeIndex; i >= 0; i--) {
                Snake snake = this.mSnakes.get(i);
                int endX = snake.x + snake.size;
                int endY = snake.y + snake.size;
                int changeFlag = 4;
                if (removal) {
                    for (int pos = curX2 - 1; pos >= endX; pos--) {
                        if (this.mCallback.areItemsTheSame(pos, myItemPos)) {
                            if (this.mCallback.areContentsTheSame(pos, myItemPos)) {
                                changeFlag = 8;
                            }
                            this.mNewItemStatuses[myItemPos] = (pos << 5) | 16;
                            this.mOldItemStatuses[pos] = (myItemPos << 5) | changeFlag;
                            return true;
                        }
                    }
                    continue;
                } else {
                    for (int pos2 = curY2 - 1; pos2 >= endY; pos2--) {
                        if (this.mCallback.areItemsTheSame(myItemPos, pos2)) {
                            if (this.mCallback.areContentsTheSame(myItemPos, pos2)) {
                                changeFlag = 8;
                            }
                            this.mOldItemStatuses[x - 1] = (pos2 << 5) | 16;
                            this.mNewItemStatuses[pos2] = ((x - 1) << 5) | changeFlag;
                            return true;
                        }
                    }
                    continue;
                }
                curX2 = snake.x;
                curY2 = snake.y;
            }
            return false;
        }

        public void dispatchUpdatesTo(@NonNull RecyclerView.Adapter adapter) {
            dispatchUpdatesTo((ListUpdateCallback) new AdapterListUpdateCallback(adapter));
        }

        public void dispatchUpdatesTo(@NonNull ListUpdateCallback updateCallback) {
            BatchingListUpdateCallback batchingCallback;
            int endY;
            int snakeSize;
            BatchingListUpdateCallback batchingListUpdateCallback = updateCallback;
            if (batchingListUpdateCallback instanceof BatchingListUpdateCallback) {
                batchingCallback = (BatchingListUpdateCallback) batchingListUpdateCallback;
            } else {
                batchingCallback = new BatchingListUpdateCallback(batchingListUpdateCallback);
                batchingListUpdateCallback = batchingCallback;
            }
            ListUpdateCallback updateCallback2 = batchingListUpdateCallback;
            BatchingListUpdateCallback batchingCallback2 = batchingCallback;
            ArrayList arrayList = new ArrayList();
            int posOld = this.mOldListSize;
            int posNew = this.mNewListSize;
            int snakeIndex = this.mSnakes.size() - 1;
            int posOld2 = posOld;
            int posNew2 = posNew;
            while (true) {
                int snakeIndex2 = snakeIndex;
                if (snakeIndex2 >= 0) {
                    Snake snake = this.mSnakes.get(snakeIndex2);
                    int snakeSize2 = snake.size;
                    int endX = snake.x + snakeSize2;
                    int endY2 = snake.y + snakeSize2;
                    if (endX < posOld2) {
                        endY = endY2;
                        dispatchRemovals(arrayList, batchingCallback2, endX, posOld2 - endX, endX);
                    } else {
                        endY = endY2;
                    }
                    if (endY < posNew2) {
                        int i = endX;
                        snakeSize = snakeSize2;
                        dispatchAdditions(arrayList, batchingCallback2, endX, posNew2 - endY, endY);
                    } else {
                        snakeSize = snakeSize2;
                    }
                    int i2 = snakeSize - 1;
                    while (true) {
                        int i3 = i2;
                        if (i3 < 0) {
                            break;
                        }
                        if ((this.mOldItemStatuses[snake.x + i3] & 31) == 2) {
                            batchingCallback2.onChanged(snake.x + i3, 1, this.mCallback.getChangePayload(snake.x + i3, snake.y + i3));
                        }
                        i2 = i3 - 1;
                    }
                    posOld2 = snake.x;
                    posNew2 = snake.y;
                    snakeIndex = snakeIndex2 - 1;
                } else {
                    batchingCallback2.dispatchLastEvent();
                    return;
                }
            }
        }

        private static PostponedUpdate removePostponedUpdate(List<PostponedUpdate> updates, int pos, boolean removal) {
            for (int i = updates.size() - 1; i >= 0; i--) {
                PostponedUpdate update = updates.get(i);
                if (update.posInOwnerList == pos && update.removal == removal) {
                    updates.remove(i);
                    for (int j = i; j < updates.size(); j++) {
                        updates.get(j).currentPos += removal ? 1 : -1;
                    }
                    return update;
                }
            }
            return null;
        }

        private void dispatchAdditions(List<PostponedUpdate> postponedUpdates, ListUpdateCallback updateCallback, int start, int count, int globalIndex) {
            if (!this.mDetectMoves) {
                updateCallback.onInserted(start, count);
                return;
            }
            for (int i = count - 1; i >= 0; i--) {
                int status = this.mNewItemStatuses[globalIndex + i] & 31;
                if (status == 0) {
                    updateCallback.onInserted(start, 1);
                    for (PostponedUpdate update : postponedUpdates) {
                        update.currentPos++;
                    }
                } else if (status == 4 || status == 8) {
                    int pos = this.mNewItemStatuses[globalIndex + i] >> 5;
                    updateCallback.onMoved(removePostponedUpdate(postponedUpdates, pos, true).currentPos, start);
                    if (status == 4) {
                        updateCallback.onChanged(start, 1, this.mCallback.getChangePayload(pos, globalIndex + i));
                    }
                } else if (status == 16) {
                    postponedUpdates.add(new PostponedUpdate(globalIndex + i, start, false));
                } else {
                    throw new IllegalStateException("unknown flag for pos " + (globalIndex + i) + " " + Long.toBinaryString((long) status));
                }
            }
        }

        private void dispatchRemovals(List<PostponedUpdate> postponedUpdates, ListUpdateCallback updateCallback, int start, int count, int globalIndex) {
            if (!this.mDetectMoves) {
                updateCallback.onRemoved(start, count);
                return;
            }
            for (int i = count - 1; i >= 0; i--) {
                int status = this.mOldItemStatuses[globalIndex + i] & 31;
                if (status == 0) {
                    updateCallback.onRemoved(start + i, 1);
                    for (PostponedUpdate update : postponedUpdates) {
                        update.currentPos--;
                    }
                } else if (status == 4 || status == 8) {
                    int pos = this.mOldItemStatuses[globalIndex + i] >> 5;
                    PostponedUpdate update2 = removePostponedUpdate(postponedUpdates, pos, false);
                    updateCallback.onMoved(start + i, update2.currentPos - 1);
                    if (status == 4) {
                        updateCallback.onChanged(update2.currentPos - 1, 1, this.mCallback.getChangePayload(globalIndex + i, pos));
                    }
                } else if (status == 16) {
                    postponedUpdates.add(new PostponedUpdate(globalIndex + i, start + i, true));
                } else {
                    throw new IllegalStateException("unknown flag for pos " + (globalIndex + i) + " " + Long.toBinaryString((long) status));
                }
            }
        }

        /* access modifiers changed from: package-private */
        @VisibleForTesting
        public List<Snake> getSnakes() {
            return this.mSnakes;
        }
    }

    private static class PostponedUpdate {
        int currentPos;
        int posInOwnerList;
        boolean removal;

        public PostponedUpdate(int posInOwnerList2, int currentPos2, boolean removal2) {
            this.posInOwnerList = posInOwnerList2;
            this.currentPos = currentPos2;
            this.removal = removal2;
        }
    }
}
