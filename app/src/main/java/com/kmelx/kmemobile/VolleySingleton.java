package com.kmelx.kmemobile;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by jonathan on 5/05/16.
 */

public class VolleySingleton {

    private static VolleySingleton instance = null;
    private RequestQueue mRequestQueue;
    private VolleySingleton()
    {
        mRequestQueue = Volley.newRequestQueue(Myapplication.getAppContext());
    }

    public static VolleySingleton getInstance()
    {
        if (instance ==null)
        {
            instance = new VolleySingleton();
        }
        return instance;
    }


    public RequestQueue getmRequestQueue() {
        return mRequestQueue;
    }

}