package android.support.constraint.solver;

import android.support.constraint.solver.ArrayRow;
import io.reactivex.annotations.SchedulerSupport;
import java.util.Arrays;

public class SolverVariableValues implements ArrayRow.ArrayRowVariables {
    private static final boolean DEBUG = false;
    private static final boolean HASH = true;
    private static float epsilon = 0.001f;
    private int HASH_SIZE = 16;
    private final int NONE = -1;
    private int SIZE = 16;
    int head = -1;
    int[] keys = new int[16];
    protected final Cache mCache;
    int mCount = 0;
    private final ArrayRow mRow;
    int[] next = new int[16];
    int[] nextKeys = new int[16];
    int[] previous = new int[16];
    float[] values = new float[16];
    int[] variables = new int[16];

    SolverVariableValues(ArrayRow row, Cache cache) {
        this.mRow = row;
        this.mCache = cache;
        clear();
    }

    public int getCurrentSize() {
        return this.mCount;
    }

    public SolverVariable getVariable(int index) {
        int count = this.mCount;
        if (count == 0) {
            return null;
        }
        int j = this.head;
        for (int i = 0; i < count; i++) {
            if (i == index && j != -1) {
                return this.mCache.mIndexedVariables[this.variables[j]];
            }
            j = this.next[j];
            if (j == -1) {
                break;
            }
        }
        return null;
    }

    public float getVariableValue(int index) {
        int count = this.mCount;
        int j = this.head;
        for (int i = 0; i < count; i++) {
            if (i == index) {
                return this.values[j];
            }
            j = this.next[j];
            if (j == -1) {
                return 0.0f;
            }
        }
        return 0.0f;
    }

    public boolean contains(SolverVariable variable) {
        if (indexOf(variable) != -1) {
            return HASH;
        }
        return false;
    }

    public int indexOf(SolverVariable variable) {
        int[] iArr;
        if (this.mCount == 0) {
            return -1;
        }
        int id = variable.id;
        int key = this.keys[id % this.HASH_SIZE];
        if (key == -1) {
            return -1;
        }
        if (this.variables[key] == id) {
            return key;
        }
        while (true) {
            iArr = this.nextKeys;
            if (iArr[key] != -1 && this.variables[iArr[key]] != id) {
                key = iArr[key];
            }
        }
        if (iArr[key] != -1 && this.variables[iArr[key]] == id) {
            return iArr[key];
        }
        return -1;
    }

    public float get(SolverVariable variable) {
        int index = indexOf(variable);
        if (index != -1) {
            return this.values[index];
        }
        return 0.0f;
    }

    public void display() {
        int count = this.mCount;
        System.out.print("{ ");
        for (int i = 0; i < count; i++) {
            SolverVariable v = getVariable(i);
            if (v != null) {
                System.out.print(v + " = " + getVariableValue(i) + " ");
            }
        }
        System.out.println(" }");
    }

    public String toString() {
        String str;
        String str2;
        String str3 = hashCode() + " { ";
        int count = this.mCount;
        for (int i = 0; i < count; i++) {
            SolverVariable v = getVariable(i);
            if (v != null) {
                int index = indexOf(v);
                String str4 = (str3 + v + " = " + getVariableValue(i) + " ") + "[p: ";
                if (this.previous[index] != -1) {
                    str = str4 + this.mCache.mIndexedVariables[this.variables[this.previous[index]]];
                } else {
                    str = str4 + SchedulerSupport.NONE;
                }
                String str5 = str + ", n: ";
                if (this.next[index] != -1) {
                    str2 = str5 + this.mCache.mIndexedVariables[this.variables[this.next[index]]];
                } else {
                    str2 = str5 + SchedulerSupport.NONE;
                }
                str3 = str2 + "]";
            }
        }
        return str3 + " }";
    }

    public void clear() {
        int count = this.mCount;
        for (int i = 0; i < count; i++) {
            SolverVariable v = getVariable(i);
            if (v != null) {
                v.removeFromRow(this.mRow);
            }
        }
        for (int i2 = 0; i2 < this.SIZE; i2++) {
            this.variables[i2] = -1;
            this.nextKeys[i2] = -1;
        }
        for (int i3 = 0; i3 < this.HASH_SIZE; i3++) {
            this.keys[i3] = -1;
        }
        this.mCount = 0;
        this.head = -1;
    }

    private void increaseSize() {
        int size = this.SIZE * 2;
        this.variables = Arrays.copyOf(this.variables, size);
        this.values = Arrays.copyOf(this.values, size);
        this.previous = Arrays.copyOf(this.previous, size);
        this.next = Arrays.copyOf(this.next, size);
        this.nextKeys = Arrays.copyOf(this.nextKeys, size);
        for (int i = this.SIZE; i < size; i++) {
            this.variables[i] = -1;
            this.nextKeys[i] = -1;
        }
        this.SIZE = size;
    }

    private void addToHashMap(SolverVariable variable, int index) {
        int[] iArr;
        int hash = variable.id % this.HASH_SIZE;
        int[] iArr2 = this.keys;
        int key = iArr2[hash];
        if (key == -1) {
            iArr2[hash] = index;
        } else {
            while (true) {
                iArr = this.nextKeys;
                if (iArr[key] == -1) {
                    break;
                }
                key = iArr[key];
            }
            iArr[key] = index;
        }
        this.nextKeys[index] = -1;
    }

    private void displayHash() {
        for (int i = 0; i < this.HASH_SIZE; i++) {
            if (this.keys[i] != -1) {
                String str = hashCode() + " hash [" + i + "] => ";
                int key = this.keys[i];
                boolean done = false;
                while (!done) {
                    str = str + " " + this.variables[key];
                    int[] iArr = this.nextKeys;
                    if (iArr[key] != -1) {
                        key = iArr[key];
                    } else {
                        done = HASH;
                    }
                }
                System.out.println(str);
            }
        }
    }

    private void removeFromHashMap(SolverVariable variable) {
        int[] iArr;
        int hash = variable.id % this.HASH_SIZE;
        int key = this.keys[hash];
        if (key != -1) {
            int id = variable.id;
            if (this.variables[key] == id) {
                int[] iArr2 = this.keys;
                int[] iArr3 = this.nextKeys;
                iArr2[hash] = iArr3[key];
                iArr3[key] = -1;
                return;
            }
            while (true) {
                iArr = this.nextKeys;
                if (iArr[key] == -1 || this.variables[iArr[key]] == id) {
                    int currentKey = iArr[key];
                } else {
                    key = iArr[key];
                }
            }
            int currentKey2 = iArr[key];
            if (currentKey2 != -1 && this.variables[currentKey2] == id) {
                iArr[key] = iArr[currentKey2];
                iArr[currentKey2] = -1;
            }
        }
    }

    private void addVariable(int index, SolverVariable variable, float value) {
        this.variables[index] = variable.id;
        this.values[index] = value;
        this.previous[index] = -1;
        this.next[index] = -1;
        variable.addToRow(this.mRow);
        variable.usageInRowCount++;
        this.mCount++;
    }

    private int findEmptySlot() {
        for (int i = 0; i < this.SIZE; i++) {
            if (this.variables[i] == -1) {
                return i;
            }
        }
        return -1;
    }

    private void insertVariable(int index, SolverVariable variable, float value) {
        int availableSlot = findEmptySlot();
        addVariable(availableSlot, variable, value);
        if (index != -1) {
            this.previous[availableSlot] = index;
            int[] iArr = this.next;
            iArr[availableSlot] = iArr[index];
            iArr[index] = availableSlot;
        } else {
            this.previous[availableSlot] = -1;
            if (this.mCount > 0) {
                this.next[availableSlot] = this.head;
                this.head = availableSlot;
            } else {
                this.next[availableSlot] = -1;
            }
        }
        int[] iArr2 = this.next;
        if (iArr2[availableSlot] != -1) {
            this.previous[iArr2[availableSlot]] = availableSlot;
        }
        addToHashMap(variable, availableSlot);
    }

    public void put(SolverVariable variable, float value) {
        float f = epsilon;
        if (value > (-f) && value < f) {
            remove(variable, HASH);
        } else if (this.mCount == 0) {
            addVariable(0, variable, value);
            addToHashMap(variable, 0);
            this.head = 0;
        } else {
            int index = indexOf(variable);
            if (index != -1) {
                this.values[index] = value;
                return;
            }
            if (this.mCount + 1 >= this.SIZE) {
                increaseSize();
            }
            int count = this.mCount;
            int previousItem = -1;
            int j = this.head;
            for (int i = 0; i < count; i++) {
                if (this.variables[j] == variable.id) {
                    this.values[j] = value;
                    return;
                }
                if (this.variables[j] < variable.id) {
                    previousItem = j;
                }
                j = this.next[j];
                if (j == -1) {
                    break;
                }
            }
            insertVariable(previousItem, variable, value);
        }
    }

    public int sizeInBytes() {
        return 0;
    }

    public float remove(SolverVariable v, boolean removeFromDefinition) {
        int index = indexOf(v);
        if (index == -1) {
            return 0.0f;
        }
        removeFromHashMap(v);
        float value = this.values[index];
        if (this.head == index) {
            this.head = this.next[index];
        }
        this.variables[index] = -1;
        int[] iArr = this.previous;
        if (iArr[index] != -1) {
            int[] iArr2 = this.next;
            iArr2[iArr[index]] = iArr2[index];
        }
        int[] iArr3 = this.next;
        if (iArr3[index] != -1) {
            iArr[iArr3[index]] = iArr[index];
        }
        this.mCount--;
        v.usageInRowCount--;
        if (removeFromDefinition) {
            v.removeFromRow(this.mRow);
        }
        return value;
    }

    public void add(SolverVariable v, float value, boolean removeFromDefinition) {
        float f = epsilon;
        if (value <= (-f) || value >= f) {
            int index = indexOf(v);
            if (index == -1) {
                put(v, value);
                return;
            }
            float[] fArr = this.values;
            fArr[index] = fArr[index] + value;
            float f2 = fArr[index];
            float f3 = epsilon;
            if (f2 > (-f3) && fArr[index] < f3) {
                fArr[index] = 0.0f;
                remove(v, removeFromDefinition);
            }
        }
    }

    public float use(ArrayRow def, boolean removeFromDefinition) {
        float value = get(def.variable);
        remove(def.variable, removeFromDefinition);
        SolverVariableValues definition = (SolverVariableValues) def.variables;
        int definitionSize = definition.getCurrentSize();
        int i = definition.head;
        int j = 0;
        int i2 = 0;
        while (j < definitionSize) {
            if (definition.variables[i2] != -1) {
                add(this.mCache.mIndexedVariables[definition.variables[i2]], definition.values[i2] * value, removeFromDefinition);
                j++;
            }
            i2++;
        }
        return value;
    }

    public void invert() {
        int count = this.mCount;
        int j = this.head;
        int i = 0;
        while (i < count) {
            float[] fArr = this.values;
            fArr[j] = fArr[j] * -1.0f;
            j = this.next[j];
            if (j != -1) {
                i++;
            } else {
                return;
            }
        }
    }

    public void divideByAmount(float amount) {
        int count = this.mCount;
        int j = this.head;
        int i = 0;
        while (i < count) {
            float[] fArr = this.values;
            fArr[j] = fArr[j] / amount;
            j = this.next[j];
            if (j != -1) {
                i++;
            } else {
                return;
            }
        }
    }
}
