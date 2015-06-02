package com.bijesh.donateblood.models.ui;

import java.util.Observable;

/**
 * Created by bijesh on 6/2/2015.
 */
public abstract class Operation extends Observable {
    // Operation Ids
    public static enum OperationCode
    {
        SET_INSTALLATION
    }

    // Operation States
    public static final int ERROR = 0;
    public static final int CREATED = 1;
    public static final int SUBMITED = 2;
    public static final int PENDING = 3;
    public static final int FINISHED = 4;

    int id;
    int state;
    protected Object mData;
//    protected View mView = null;


    protected Operation() {
        id = -1;
        state = -1;
    }

    protected Operation(int oprId, int oprState) {
        id = oprId;
        state = oprState;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
        setChanged();
        notifyObservers(this);
    }


    public Object getData() {
        return mData;

    }

    public boolean isInProgress(){
        return (this.getState() == Operation.CREATED || this.getState() == Operation.PENDING || this.getState() == Operation.SUBMITED);
    }

    public boolean isFinished(){
        return (this.getState() == Operation.FINISHED || this.getState() == Operation.ERROR);
    }

    public void setData(Object aData) {
        this.mData = aData;
        setChanged();
        notifyObservers(this);
    }

//    public View getView() {
//        return mView;
//    }
//
//    public void setView(View aView) {
//        mView = aView;
//    }

    abstract public String getInformation();

    abstract public void  setInformation(String message);

    //Method to kill the polling timers
    public void clean(){

    }

    public void onDestroy() {

    }

}
