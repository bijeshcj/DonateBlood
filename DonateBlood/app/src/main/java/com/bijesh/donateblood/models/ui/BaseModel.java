package com.bijesh.donateblood.models.ui;

import java.util.Observable;

/**
 * Created by bijesh on 6/2/2015.
 */
public class BaseModel extends Observable {
    Object mData;

    public Object getData() { return mData; }

    public void clear() {
        mData = null;
    }
}
