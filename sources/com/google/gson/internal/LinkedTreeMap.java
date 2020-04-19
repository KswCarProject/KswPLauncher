package com.google.gson.internal;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public final class LinkedTreeMap<K, V> extends AbstractMap<K, V> implements Serializable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Comparator<Comparable> NATURAL_ORDER = new Comparator<Comparable>() {
        public int compare(Comparable a, Comparable b) {
            return a.compareTo(b);
        }
    };
    Comparator<? super K> comparator;
    private LinkedTreeMap<K, V>.EntrySet entrySet;
    final Node<K, V> header;
    private LinkedTreeMap<K, V>.KeySet keySet;
    int modCount;
    Node<K, V> root;
    int size;

    public LinkedTreeMap() {
        this(NATURAL_ORDER);
    }

    public LinkedTreeMap(Comparator<? super K> comparator2) {
        this.size = 0;
        this.modCount = 0;
        this.header = new Node<>();
        this.comparator = comparator2 != null ? comparator2 : NATURAL_ORDER;
    }

    public int size() {
        return this.size;
    }

    public V get(Object key) {
        Node<K, V> node = findByObject(key);
        if (node != null) {
            return node.value;
        }
        return null;
    }

    public boolean containsKey(Object key) {
        return findByObject(key) != null;
    }

    public V put(K key, V value) {
        if (key != null) {
            Node<K, V> created = find(key, true);
            V result = created.value;
            created.value = value;
            return result;
        }
        throw new NullPointerException("key == null");
    }

    public void clear() {
        this.root = null;
        this.size = 0;
        this.modCount++;
        Node<K, V> header2 = this.header;
        header2.prev = header2;
        header2.next = header2;
    }

    public V remove(Object key) {
        Node<K, V> node = removeInternalByKey(key);
        if (node != null) {
            return node.value;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public Node<K, V> find(K key, boolean create) {
        Node<K, V> created;
        Comparator<? super K> comparator2 = this.comparator;
        Node<K, V> nearest = this.root;
        int comparison = 0;
        if (nearest != null) {
            Comparable<Object> comparableKey = comparator2 == NATURAL_ORDER ? key : null;
            while (true) {
                comparison = comparableKey != null ? comparableKey.compareTo(nearest.key) : comparator2.compare(key, nearest.key);
                if (comparison == 0) {
                    return nearest;
                }
                Node<K, V> child = comparison < 0 ? nearest.left : nearest.right;
                if (child == null) {
                    break;
                }
                nearest = child;
            }
        }
        if (!create) {
            return null;
        }
        Node<K, V> header2 = this.header;
        if (nearest != null) {
            created = new Node<>(nearest, key, header2, header2.prev);
            if (comparison < 0) {
                nearest.left = created;
            } else {
                nearest.right = created;
            }
            rebalance(nearest, true);
        } else if (comparator2 != NATURAL_ORDER || (key instanceof Comparable)) {
            created = new Node<>(nearest, key, header2, header2.prev);
            this.root = created;
        } else {
            throw new ClassCastException(key.getClass().getName() + " is not Comparable");
        }
        this.size++;
        this.modCount++;
        return created;
    }

    /* access modifiers changed from: package-private */
    public Node<K, V> findByObject(Object key) {
        if (key == null) {
            return null;
        }
        try {
            return find(key, false);
        } catch (ClassCastException e) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public Node<K, V> findByEntry(Map.Entry<?, ?> entry) {
        Node<K, V> mine = findByObject(entry.getKey());
        if (mine != null && equal(mine.value, entry.getValue())) {
            return mine;
        }
        return null;
    }

    private boolean equal(Object a, Object b) {
        return a == b || (a != null && a.equals(b));
    }

    /* access modifiers changed from: package-private */
    public void removeInternal(Node<K, V> node, boolean unlink) {
        if (unlink) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        Node<K, V> left = node.left;
        Node<K, V> right = node.right;
        Node<K, V> originalParent = node.parent;
        if (left == null || right == null) {
            if (left != null) {
                replaceInParent(node, left);
                node.left = null;
            } else if (right != null) {
                replaceInParent(node, right);
                node.right = null;
            } else {
                replaceInParent(node, (Node<K, V>) null);
            }
            rebalance(originalParent, false);
            this.size--;
            this.modCount++;
            return;
        }
        Node<K, V> adjacent = left.height > right.height ? left.last() : right.first();
        removeInternal(adjacent, false);
        int leftHeight = 0;
        Node<K, V> left2 = node.left;
        if (left2 != null) {
            leftHeight = left2.height;
            adjacent.left = left2;
            left2.parent = adjacent;
            node.left = null;
        }
        int rightHeight = 0;
        Node<K, V> right2 = node.right;
        if (right2 != null) {
            rightHeight = right2.height;
            adjacent.right = right2;
            right2.parent = adjacent;
            node.right = null;
        }
        adjacent.height = Math.max(leftHeight, rightHeight) + 1;
        replaceInParent(node, adjacent);
    }

    /* access modifiers changed from: package-private */
    public Node<K, V> removeInternalByKey(Object key) {
        Node<K, V> node = findByObject(key);
        if (node != null) {
            removeInternal(node, true);
        }
        return node;
    }

    private void replaceInParent(Node<K, V> node, Node<K, V> replacement) {
        Node<K, V> parent = node.parent;
        node.parent = null;
        if (replacement != null) {
            replacement.parent = parent;
        }
        if (parent == null) {
            this.root = replacement;
        } else if (parent.left == node) {
            parent.left = replacement;
        } else {
            parent.right = replacement;
        }
    }

    private void rebalance(Node<K, V> unbalanced, boolean insert) {
        for (Node<K, V> node = unbalanced; node != null; node = node.parent) {
            Node<K, V> left = node.left;
            Node<K, V> right = node.right;
            int rightLeftHeight = 0;
            int leftHeight = left != null ? left.height : 0;
            int rightHeight = right != null ? right.height : 0;
            int delta = leftHeight - rightHeight;
            if (delta == -2) {
                Node<K, V> rightLeft = right.left;
                Node<K, V> rightRight = right.right;
                int rightRightHeight = rightRight != null ? rightRight.height : 0;
                if (rightLeft != null) {
                    rightLeftHeight = rightLeft.height;
                }
                int rightDelta = rightLeftHeight - rightRightHeight;
                if (rightDelta == -1 || (rightDelta == 0 && !insert)) {
                    rotateLeft(node);
                } else {
                    rotateRight(right);
                    rotateLeft(node);
                }
                if (insert) {
                    return;
                }
            } else if (delta == 2) {
                Node<K, V> leftLeft = left.left;
                Node<K, V> leftRight = left.right;
                int leftRightHeight = leftRight != null ? leftRight.height : 0;
                if (leftLeft != null) {
                    rightLeftHeight = leftLeft.height;
                }
                int leftDelta = rightLeftHeight - leftRightHeight;
                if (leftDelta == 1 || (leftDelta == 0 && !insert)) {
                    rotateRight(node);
                } else {
                    rotateLeft(left);
                    rotateRight(node);
                }
                if (insert) {
                    return;
                }
            } else if (delta == 0) {
                node.height = leftHeight + 1;
                if (insert) {
                    return;
                }
            } else {
                node.height = Math.max(leftHeight, rightHeight) + 1;
                if (!insert) {
                    return;
                }
            }
        }
    }

    private void rotateLeft(Node<K, V> root2) {
        Node<K, V> left = root2.left;
        Node<K, V> pivot = root2.right;
        Node<K, V> pivotLeft = pivot.left;
        Node<K, V> pivotRight = pivot.right;
        root2.right = pivotLeft;
        if (pivotLeft != null) {
            pivotLeft.parent = root2;
        }
        replaceInParent(root2, pivot);
        pivot.left = root2;
        root2.parent = pivot;
        int i = 0;
        root2.height = Math.max(left != null ? left.height : 0, pivotLeft != null ? pivotLeft.height : 0) + 1;
        int i2 = root2.height;
        if (pivotRight != null) {
            i = pivotRight.height;
        }
        pivot.height = Math.max(i2, i) + 1;
    }

    private void rotateRight(Node<K, V> root2) {
        Node<K, V> pivot = root2.left;
        Node<K, V> right = root2.right;
        Node<K, V> pivotLeft = pivot.left;
        Node<K, V> pivotRight = pivot.right;
        root2.left = pivotRight;
        if (pivotRight != null) {
            pivotRight.parent = root2;
        }
        replaceInParent(root2, pivot);
        pivot.right = root2;
        root2.parent = pivot;
        int i = 0;
        root2.height = Math.max(right != null ? right.height : 0, pivotRight != null ? pivotRight.height : 0) + 1;
        int i2 = root2.height;
        if (pivotLeft != null) {
            i = pivotLeft.height;
        }
        pivot.height = Math.max(i2, i) + 1;
    }

    public Set<Map.Entry<K, V>> entrySet() {
        LinkedTreeMap<K, V>.EntrySet result = this.entrySet;
        if (result != null) {
            return result;
        }
        LinkedTreeMap<K, V>.EntrySet entrySet2 = new EntrySet();
        this.entrySet = entrySet2;
        return entrySet2;
    }

    public Set<K> keySet() {
        LinkedTreeMap<K, V>.KeySet result = this.keySet;
        if (result != null) {
            return result;
        }
        LinkedTreeMap<K, V>.KeySet keySet2 = new KeySet();
        this.keySet = keySet2;
        return keySet2;
    }

    static final class Node<K, V> implements Map.Entry<K, V> {
        int height;
        final K key;
        Node<K, V> left;
        Node<K, V> next;
        Node<K, V> parent;
        Node<K, V> prev;
        Node<K, V> right;
        V value;

        Node() {
            this.key = null;
            this.prev = this;
            this.next = this;
        }

        Node(Node<K, V> parent2, K key2, Node<K, V> next2, Node<K, V> prev2) {
            this.parent = parent2;
            this.key = key2;
            this.height = 1;
            this.next = next2;
            this.prev = prev2;
            prev2.next = this;
            next2.prev = this;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        public V setValue(V value2) {
            V oldValue = this.value;
            this.value = value2;
            return oldValue;
        }

        /* JADX WARNING: Removed duplicated region for block: B:14:0x0036 A[ORIG_RETURN, RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean equals(java.lang.Object r5) {
            /*
                r4 = this;
                boolean r0 = r5 instanceof java.util.Map.Entry
                r1 = 0
                if (r0 == 0) goto L_0x0039
                r0 = r5
                java.util.Map$Entry r0 = (java.util.Map.Entry) r0
                K r2 = r4.key
                if (r2 != 0) goto L_0x0013
                java.lang.Object r2 = r0.getKey()
                if (r2 != 0) goto L_0x0038
                goto L_0x001f
            L_0x0013:
                K r2 = r4.key
                java.lang.Object r3 = r0.getKey()
                boolean r2 = r2.equals(r3)
                if (r2 == 0) goto L_0x0038
            L_0x001f:
                V r2 = r4.value
                if (r2 != 0) goto L_0x002a
                java.lang.Object r2 = r0.getValue()
                if (r2 != 0) goto L_0x0038
                goto L_0x0036
            L_0x002a:
                V r2 = r4.value
                java.lang.Object r3 = r0.getValue()
                boolean r2 = r2.equals(r3)
                if (r2 == 0) goto L_0x0038
            L_0x0036:
                r1 = 1
            L_0x0038:
                return r1
            L_0x0039:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.LinkedTreeMap.Node.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            int i = 0;
            int hashCode = this.key == null ? 0 : this.key.hashCode();
            if (this.value != null) {
                i = this.value.hashCode();
            }
            return hashCode ^ i;
        }

        public String toString() {
            return this.key + "=" + this.value;
        }

        public Node<K, V> first() {
            Node<K, V> node = this;
            Node<K, V> child = node.left;
            while (child != null) {
                node = child;
                child = node.left;
            }
            return node;
        }

        public Node<K, V> last() {
            Node<K, V> node = this;
            Node<K, V> child = node.right;
            while (child != null) {
                node = child;
                child = node.right;
            }
            return node;
        }
    }

    private abstract class LinkedTreeMapIterator<T> implements Iterator<T> {
        int expectedModCount;
        Node<K, V> lastReturned;
        Node<K, V> next;

        private LinkedTreeMapIterator() {
            this.next = LinkedTreeMap.this.header.next;
            this.lastReturned = null;
            this.expectedModCount = LinkedTreeMap.this.modCount;
        }

        public final boolean hasNext() {
            return this.next != LinkedTreeMap.this.header;
        }

        /* access modifiers changed from: package-private */
        public final Node<K, V> nextNode() {
            Node<K, V> e = this.next;
            if (e == LinkedTreeMap.this.header) {
                throw new NoSuchElementException();
            } else if (LinkedTreeMap.this.modCount == this.expectedModCount) {
                this.next = e.next;
                this.lastReturned = e;
                return e;
            } else {
                throw new ConcurrentModificationException();
            }
        }

        public final void remove() {
            if (this.lastReturned != null) {
                LinkedTreeMap.this.removeInternal(this.lastReturned, true);
                this.lastReturned = null;
                this.expectedModCount = LinkedTreeMap.this.modCount;
                return;
            }
            throw new IllegalStateException();
        }
    }

    class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        EntrySet() {
        }

        public int size() {
            return LinkedTreeMap.this.size;
        }

        public Iterator<Map.Entry<K, V>> iterator() {
            return new LinkedTreeMap<K, V>.LinkedTreeMapIterator<Map.Entry<K, V>>() {
                {
                    LinkedTreeMap linkedTreeMap = LinkedTreeMap.this;
                }

                public Map.Entry<K, V> next() {
                    return nextNode();
                }
            };
        }

        public boolean contains(Object o) {
            return (o instanceof Map.Entry) && LinkedTreeMap.this.findByEntry((Map.Entry) o) != null;
        }

        public boolean remove(Object o) {
            Node<K, V> node;
            if (!(o instanceof Map.Entry) || (node = LinkedTreeMap.this.findByEntry((Map.Entry) o)) == null) {
                return false;
            }
            LinkedTreeMap.this.removeInternal(node, true);
            return true;
        }

        public void clear() {
            LinkedTreeMap.this.clear();
        }
    }

    class KeySet extends AbstractSet<K> {
        KeySet() {
        }

        public int size() {
            return LinkedTreeMap.this.size;
        }

        public Iterator<K> iterator() {
            return new LinkedTreeMap<K, V>.LinkedTreeMapIterator<K>() {
                {
                    LinkedTreeMap linkedTreeMap = LinkedTreeMap.this;
                }

                public K next() {
                    return nextNode().key;
                }
            };
        }

        public boolean contains(Object o) {
            return LinkedTreeMap.this.containsKey(o);
        }

        public boolean remove(Object key) {
            return LinkedTreeMap.this.removeInternalByKey(key) != null;
        }

        public void clear() {
            LinkedTreeMap.this.clear();
        }
    }

    private Object writeReplace() throws ObjectStreamException {
        return new LinkedHashMap(this);
    }
}
