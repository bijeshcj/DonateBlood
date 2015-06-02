package com.bijesh.donateblood.controllers;

import com.bijesh.donateblood.models.ui.Operation;

/**
 * Created by bijesh on 6/2/2015.
 */
public interface UIInterface {
    public void onProgress(Operation opr);
    public void onError(Operation opr);
    public void onSuccess(Operation opr);
}
