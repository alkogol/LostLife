package org.application.model;

public class ArrayPairOfIndexes {
    final private int column;
    final private int field;

    public ArrayPairOfIndexes(int column, int field) {
        this.column = column;
        this.field = field;
    }

    public int getColumn() {
        return column;
    }

    public int getField() {
        return field;
    }
    public boolean isUnbound(){
        return column==-1&&field==-1;
    }
}
