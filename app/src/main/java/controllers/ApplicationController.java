package controllers;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


//@ReportsCrashes(
//        //formUri = "https://{myusername}.cloudant.com/acra-{myapp}/_design/acra-storage/_update/report",
//        //formUri = "http://www.bihmapa.com/Android/test/fr/acra_child.php",
//        formUri = "https://collector.tracepot.com/388ec5d4",
//        reportType = HttpSender.Type.JSON,
//        httpMethod = HttpSender.Method.POST,
//        formUriBasicAuthLogin = "GENERATED_USERNAME_WITH_WRITE_PERMISSIONS",
//        formUriBasicAuthPassword = "GENERATED_PASSWORD",
//        //formKey = "", // This is required for backward compatibility but not used
//        customReportContent = {
//                ReportField.APP_VERSION_CODE,
//                ReportField.APP_VERSION_NAME,
//                ReportField.ANDROID_VERSION,
//                ReportField.PACKAGE_NAME,
//                ReportField.REPORT_ID,
//                ReportField.BUILD,
//                ReportField.STACK_TRACE
//        },
//        mode = ReportingInteractionMode.TOAST,
//        resToastText = R.string.t_crash
//)
public class ApplicationController extends Application {

    private RequestQueue mRequestQueue;
    private static ApplicationController mInstance;
    public static final String TAG = "DVC_Application";
    private MyPreferencesManager myPreferencesManager;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;


    }

    public MyPreferencesManager getMyPreferencesManager() {
        if (myPreferencesManager == null) {
            myPreferencesManager = new MyPreferencesManager(this);
        }

        return myPreferencesManager;
    }


    public static synchronized ApplicationController getInstance() {
        return mInstance;
    }

    public RequestQueue getmRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }


    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getmRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getmRequestQueue().add(req);
    }

    public void cancelPendingRequests(String tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

}
