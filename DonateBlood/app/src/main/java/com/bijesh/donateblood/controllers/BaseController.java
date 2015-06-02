package com.bijesh.donateblood.controllers;


import com.bijesh.donateblood.AppConstants;
import com.bijesh.donateblood.httpwrapper.RestClient;
import com.bijesh.donateblood.httpwrapper.RestClientFactory;
import com.bijesh.donateblood.httpwrapper.impl.DonateBloodHttpRequest;
import com.bijesh.donateblood.httpwrapper.impl.Response;
import com.bijesh.donateblood.models.ui.BaseModel;
import com.bijesh.donateblood.models.ui.Operation;
import com.bijesh.donateblood.models.ui.OperationsModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by bijesh on 6/2/2015.
 */
public class BaseController implements RestClient.ResponseListener,
        RestClient.ErrorListener {

    ArrayList<BaseModel> mModels = new ArrayList<BaseModel>();
    ArrayList<UIInterface> mUIs =  new ArrayList<UIInterface>();
    OperationsModel mOperationsModel = OperationsModel.instance();


    public BaseModel getModel(int index) {
        if (index < mModels.size())
            return mModels.get(index);
        return null;
    }

    public boolean register(UIInterface aUI) {
        if (aUI != null && !mUIs.contains(aUI)) {
            mUIs.add(aUI);
            return true;
        }
        return false;
    }

    public boolean unregister(UIInterface aUI) {
        if (aUI == null || mUIs.size() == 0 || mUIs.contains(aUI)) {
            return false;
        }
        mUIs.remove(aUI);
        return true;
    }

    public void onError(Operation opr) {
        for( UIInterface aUI : mUIs) {
            aUI.onError(opr);
        }
    }

    public void onProgress(Operation opr) {
        for( UIInterface aUI : mUIs) {
            aUI.onProgress(opr);
        }
    }

    public void onSuccess(Operation opr) {
        for( UIInterface aUI : mUIs) {
            aUI.onSuccess(opr);
        }
    }

    public String getHost(){
        // Read the URl details.
        return "";//Config.getInstance(null).getHost();
    }



    public void submit(RestClient.HttpRequest aHttpRequest) {
        RestClientFactory.getDefaultRestClient().addRequest(aHttpRequest, RestResponseHandlerThread.getHandler());
    }

    public void cancel(RestClient.HttpRequest aHttpRequest) {
        RestClientFactory.getDefaultRestClient().cancelRequest(aHttpRequest);
    }

    public void cancelAll(){
        RestClientFactory.getDefaultRestClient().cancelAll();
    }

    public RestClient.HttpRequest createRequest() {
        DonateBloodHttpRequest aHttpRequest = new DonateBloodHttpRequest();
        aHttpRequest.setErrorListener(this);
        aHttpRequest.setResponseListener(this);
        return aHttpRequest;
    }

    public void clearAllModels() {
        for (BaseModel baseModel : mModels) {
            baseModel.clear();
        }
    }

    @Override
    public void onError(RestClient.HttpResponse aData, RestClient.HttpRequest aReq) {
        Operation opr = ((OperationsModel) mOperationsModel).getOperation(aReq);

        if (opr == null) {
            return;
        }
        opr.setState(Operation.ERROR);
        onError(opr);

    }

    public void handleErrorForOperation(Response aResponse, Operation aOperation){

    }


    public void handleResponseForOperation(Response aResponse, Operation aOperation){

    }


    @Override
    public void onResponse(RestClient.HttpResponse aResponse, RestClient.HttpRequest aReq) {
        try {
            Operation opr = ((OperationsModel) mOperationsModel).getOperation(aReq);

            if (opr == null) {
                return;
            }

            if (aResponse == null || aResponse.getDataAsString().isEmpty()) {
                if (opr != null) {
                    //((UnifiedVehicleStatusOperationData)opr.getData()).setMessage("Fetch recent vehicle status Success");
                    opr.setState(Operation.ERROR);
                    onError(opr);
                }
            }

            opr.setState(Operation.FINISHED);
            JSONObject ljson = new JSONObject(aResponse.getDataAsString());
            Response lResponse = new Response();
            lResponse.setData(ljson.getJSONObject("Data"));
            lResponse.setHttpStatusCode(aResponse.getHttpStatus());
            lResponse.setResponseStatus(ljson.getJSONObject("Response").getString("ResponseStatus"));
            lResponse.setResponseCode(ljson.getJSONObject("Response").getInt("ResponseCode"));
            lResponse.setResponseDescription(ljson.getJSONObject("Response").getString("ResponseDescription"));
            if(lResponse.getResponseCode() != AppConstants.REQUEST_SUCCESS){
                // this is error .
                handleErrorForOperation(lResponse,opr);
                return;
            }
            handleResponseForOperation(lResponse, opr);

        } catch( JSONException e ){

        }
    }
}
