package com.bijesh.donateblood.controllers;

import com.bijesh.donateblood.models.ui.Operation;

/**
 * Created by bijesh on 6/2/2015.
 */
public class InstallationController extends BaseController {

    private static final int INSTALLATION_DATA_MODEL = 0;

    private static InstallationController mInstance = null;

    public static InstallationController instance() {
        if (mInstance == null)
            mInstance = new InstallationController();
        return mInstance;
    }

    // INSTALLTION controller Operation Codes.
    private int SET_INSTALLATION_DATA = Operation.OperationCode.SET_INSTALLATION.ordinal();



}
