package rest;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import controllers.ApplicationController;

public class RestNetworkDAO implements Response.Listener, Response.ErrorListener{
    private static String REST_NETWORK_DAO = "REST_NETWORK_DAO";
    private RestNetworkDaoObjectListener listener;

    public RestNetworkDAO(RestNetworkDaoObjectListener l){
        listener = l;
    }

    public void fetchJsonObject(String uri){

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, uri, null,
                this, this);

        req.setRetryPolicy(new DefaultRetryPolicy(10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        ApplicationController.getInstance().cancelPendingRequests(REST_NETWORK_DAO);
        ApplicationController.getInstance().addToRequestQueue(req, REST_NETWORK_DAO);

    }

    public void fetchString(String url){
        StringRequest req = new StringRequest(Request.Method.POST,url,this,this){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                //headers.put("Authorization", ApplicationController.getInstance().getMyPreferencesManager().getAccessToken());
                return headers;
            }
        };

        req.setRetryPolicy(new DefaultRetryPolicy(10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        ApplicationController.getInstance().cancelPendingRequests(REST_NETWORK_DAO);
        ApplicationController.getInstance().addToRequestQueue(req, REST_NETWORK_DAO);
    }


    public void fetchJsonArray(String uri){

        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, uri, null,
                this, this);

        req.setRetryPolicy(new DefaultRetryPolicy(15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        ApplicationController.getInstance().cancelPendingRequests(REST_NETWORK_DAO);
        ApplicationController.getInstance().addToRequestQueue(req, REST_NETWORK_DAO);

    }

    public void getJsonObject(String uri, HashMap<String, String> mRequestParams){

        REST_NETWORK_DAO = uri;

        Log.d("JSON_OBJECT_PARAMS", mRequestParams.toString());

        final JSONObject jsonObject = new JSONObject(mRequestParams);



        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, uri, jsonObject,
                this, this)
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };

        req.setRetryPolicy(new DefaultRetryPolicy(15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        ApplicationController.getInstance().cancelPendingRequests(REST_NETWORK_DAO);
        ApplicationController.getInstance().addToRequestQueue(req,REST_NETWORK_DAO);
    }

    public void getJsonObject(String uri, JSONObject jsonObject){

        REST_NETWORK_DAO = uri;

        Log.d("JSON_OBJECT_PARAMS", jsonObject.toString());

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, uri, jsonObject,
                this, this)
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };

        req.setRetryPolicy(new DefaultRetryPolicy(15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


        ApplicationController.getInstance().cancelPendingRequests(REST_NETWORK_DAO);
        ApplicationController.getInstance().addToRequestQueue(req,REST_NETWORK_DAO);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        listener.onNetworkDAOError(error);
        Log.d("ERROR", error.toString());

        if(error.networkResponse != null) {
            if (error.networkResponse.statusCode == 401) {
                listener.onAuthFail();
            }
        }

    }

    @Override
    public void onResponse(Object response) {
        Log.d("RESPONSE",response.toString());
        try {
            JSONObject jsonObject = (JSONObject) response;
            listener.onNetworkDAOSuccess(jsonObject);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
