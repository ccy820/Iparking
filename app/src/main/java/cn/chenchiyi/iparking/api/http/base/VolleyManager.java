package cn.chenchiyi.iparking.api.http.base;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import cn.chenchiyi.iparking.api.Iparking;

/**
 * Created by ccy820 on 2017/5/10.
 */

public enum VolleyManager {
    INSTANCE;

    //volley队列
    private RequestQueue mRequestQueue = null;

    public void  initQueue(){
        mRequestQueue = Volley.newRequestQueue(Iparking.APP_CONTEXT);
    }

    public void addQueue(Request request){
        mRequestQueue.add(request);
    }
    public void stopRequest(Object tag){
        if(tag!=null){
            mRequestQueue.cancelAll(tag);
        }
    }
}
