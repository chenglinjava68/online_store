package com.yuan.models;

/**
 * @author joryun ON 2017/10/22.
 */
public enum SexType {
    not(0), man(1), woman(2);

    private int value;

    SexType(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
