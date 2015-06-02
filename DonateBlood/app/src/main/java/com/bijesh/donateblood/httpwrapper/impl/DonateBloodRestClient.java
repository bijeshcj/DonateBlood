package com.bijesh.donateblood.httpwrapper.impl;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bijesh.donateblood.httpwrapper.RestClient;

import java.util.Map;

/**
 * Created by bijesh on 6/2/2015.
 */
public class DonateBloodRestClient implements RestClient {

    private static final String TAG = DonateBloodRestClient.class.getCanonicalName();
    private static DonateBloodRestClient mInstance;
    private RequestQueue mRequestQueue;
    private Handler mHandler;

    private DonateBloodRestClient(){

    }

    public static DonateBloodRestClient getInstance(){
        if(mInstance == null){
            mInstance = new DonateBloodRestClient();
        }
        return mInstance;
    }

    /**
     * Initialize Volley parameters.
     */
    public void init(Context aContext) {
        mRequestQueue = Volley.newRequestQueue(aContext);
        mHandler = new Handler(Looper.getMainLooper());
        Log.d(TAG, "Volley initialized...");
    }






    @Override
    public void cancelRequest(HttpRequest aRequest) {
        mRequestQueue.cancelAll(aRequest.getTag());
    }

    @Override
    public void addRequest(HttpRequest aRequest, Handler aHandler) {
        CustomRequest Req = getCustomRequestFor(aRequest, aHandler);
        // This tag will be used when cancelling the requests.
        Req.setTag(aRequest.getTag());
        mRequestQueue.add(Req);
    }

    @Override
    public void addRequest(Request request) {
        mRequestQueue.add(request);
    }

    @Override
    public void cancelAll() {
        mRequestQueue.cancelAll(new RequestQueue.RequestFilter() {
            @Override
            public boolean apply(Request<?> request) {
                return true;
            }
        });
    }




    /**
     * do the conversion from http to volley request
     *
     * @param aRequest
     * @param aHandler
     * @return
     */
    private CustomRequest getCustomRequestFor(HttpRequest aRequest,
                                              Handler aHandler) {
        int lRequestMapped = mapToVolleyMethodCode(aRequest.getMethod());
        RequestComposite lMap = new RequestComposite();
        lMap.setHttpReq(aRequest);
        Handler lHandler = aHandler == null ? mHandler : aHandler;
        Response.Listener<String> lListener = getListenerForRequest(lMap, lHandler);
        Response.ErrorListener lErrorListener = getErrorListenerForRequest(lMap,
                lHandler);
        CustomRequest lReq = new CustomRequest(lRequestMapped,
                aRequest.getUrl(), lListener, lErrorListener, aRequest);
        // update the reference in the map.
        lMap.setVolleyReq(lReq);
        return lReq;
    }





    private Response.Listener<String> getListenerForRequest(final RequestComposite aMapper,
                                                            final Handler aHandler) {
        return new Response.Listener<String>() {

            @Override
            public void onResponse(final String response) {
                aHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        aMapper.getVolleyReq().getClientRequest().getResponseListener()
                                .onResponse(new DonateBloodHttpResponse(aMapper.getVolleyReq().getResponse()), aMapper.getHttpReq());
                    }
                });
            }
        };
    }


    private Response.ErrorListener getErrorListenerForRequest(
            final RequestComposite aMapper, final Handler aHandler) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError error) {
                aHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (error.networkResponse != null) {
                            aMapper.getVolleyReq().getClientRequest().getErrorListener().onError(new DonateBloodHttpResponse(error.networkResponse), aMapper.getHttpReq());
                        } else {
                            // check that this does not crash.
                            error.printStackTrace();
                            aMapper.getVolleyReq().getClientRequest().getErrorListener().onError(new DonateBloodHttpResponse(null), aMapper.getHttpReq());
                        }

                    }
                });
            }
        };
    }


    private int mapToVolleyMethodCode(int lRequestMethodCode) {
        int lRequestMapped = Request.Method.GET;
        switch (lRequestMethodCode) {
            case RestClient.DELETE: {
                lRequestMapped = Request.Method.DELETE;
                break;
            }
            case RestClient.GET: {
                lRequestMapped = Request.Method.GET;
                break;
            }
            case RestClient.POST: {
                lRequestMapped = Request.Method.POST;
                break;
            }
            case RestClient.PUT: {
                lRequestMapped = Request.Method.PUT;
                break;
            }
            default:
                throw new IllegalArgumentException();
        }
        return lRequestMapped;
    }



    // Override to provide custom methods.
    // for now we are dealing with only stringRequest.
    public static class CustomRequest extends StringRequest {
        // We need to keep a reference to this as we will give it back
        // in the callback.
        private HttpRequest httpClientRequest;
        // store response headers/
        private NetworkResponse response;

        // constructor.
        public CustomRequest(int method, String url, Response.Listener<String> listener,
                             Response.ErrorListener errorListener, HttpRequest aClientRequest) {
            super(method, url, listener, errorListener);
            // we are maintaining the cache in the application
            // no need to set this to true.
            this.setShouldCache(Boolean.FALSE);
            // set the retry policy here.
            setRetryPolicy(new DefaultRetryPolicy(30000,0,0));
            httpClientRequest = aClientRequest;
        }

        /*
         * Override to provide custom body type, if using default form parameter
         * body type use the base class implementation. (non-Javadoc)
         *
         * @see com.android.volley.Request#getBody()
         */
        @Override
        public byte[] getBody() throws AuthFailureError {
            if (httpClientRequest != null) {
                // send our custom body data here.
                return httpClientRequest.getData().getBytes();
            }
            return super.getBody();
        }

        /**
         * we may have to pass extra custom headers for the request.
         */
        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            return httpClientRequest.getHeaders();
        }

        /**
         * Need to check whether it reaches here in case of Errors
         */
        @Override
        protected Response<String> parseNetworkResponse(
                NetworkResponse aResponse) {
            response = aResponse;
            return super.parseNetworkResponse(aResponse);
        }

        public HttpRequest getClientRequest() {
            return httpClientRequest;
        }

        public NetworkResponse getResponse() {
            return response;
        }

    }


    // store the mapping between volley request and http request.
    private static class RequestComposite {
        private CustomRequest volleyReq;
        private HttpRequest httpReq;

        public CustomRequest getVolleyReq() {
            return volleyReq;
        }

        public void setVolleyReq(CustomRequest volleyReq) {
            this.volleyReq = volleyReq;
        }

        public HttpRequest getHttpReq() {
            return httpReq;
        }

        public void setHttpReq(HttpRequest httpReq) {
            this.httpReq = httpReq;
        }

    }


}
