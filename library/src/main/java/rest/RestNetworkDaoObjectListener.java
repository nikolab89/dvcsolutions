package rest;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

public interface RestNetworkDaoObjectListener {
    void onNetworkDAOSuccess(JSONObject response) throws JSONException;
    void onNetworkDAOError(VolleyError error);
    void onAuthFail();
}
