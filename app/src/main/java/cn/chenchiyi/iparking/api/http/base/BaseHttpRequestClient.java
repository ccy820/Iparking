package cn.chenchiyi.iparking.api.http.base;


import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.chenchiyi.iparking.api.http.base.BaseResponse;

/**
 * Created by ccy820 on 2017/5/10.
 */

public abstract class BaseHttpRequestClient implements Response.Listener<BaseResponse>,Response.ErrorListener {
    private static final String TAG = "BaseHttpRequestClient";
    //接口回调，外部引入
    private BaseResponseListener mBaseResponseListener;

    private int requesType;
    private String jsonStr;

    //volley的请求对象
    private BaseRequest mBaseRequest = null;
    public  BaseHttpRequestClient(){
        Log.d(TAG, "新建了一个请求");
    }

    //=============================================================抽象方法
    public abstract String setUrl();
    public abstract String setTag();
    protected String setDefaultTag(){
        return this.getClass().getSimpleName();
    }
    public void postValue(Map<String,String> keyValue){}
    public abstract void parseResponse(BaseResponse response, JSONObject json) throws JSONException;
    //=============================================================对外暴露方法

    //get方法
    public void executs(boolean showDialog){
        String url = setUrl();
        mBaseRequest = new BaseRequest(Request.Method.GET,url,this,this);
        Log.d((String) mBaseRequest.getTag(), url);
        //配置
        mBaseRequest.setRetryPolicy(new DefaultRetryPolicy(5 * 1000, 3, 1.0f));
        mBaseRequest.setShouldCache(false);
        mBaseRequest.setTag(this.getClass().getSimpleName());
        postStart();
        VolleyManager.INSTANCE.addQueue(mBaseRequest);
    }

    public void executs(){
        this.executs(false);
    }

    //post方法
    public void post(boolean showDialog){
        Map<String,String> postMap = new HashMap<>();
        postValue(postMap);

        String url = setUrl();
        mBaseRequest = new BaseRequest(Request.Method.POST,url,postMap,this,this);
        mBaseRequest.setTag(setTag());
        try{
            Log.d((String) mBaseRequest.getTag(),url+new String(mBaseRequest.getBody()));
        } catch (AuthFailureError authFailureError) {
            authFailureError.printStackTrace();
        }

        //配置
        mBaseRequest.setRetryPolicy(new DefaultRetryPolicy(5*1000,3,1.0f));
        mBaseRequest.setTag(this.getClass().getSimpleName());
        postStart();
        VolleyManager.INSTANCE.addQueue(mBaseRequest);
    }

    public void post(){
        this.post(true);
    }

    //=============================================================状态回调

    //Volley回调
    @Override
    public void onResponse(BaseResponse response) {
        postSuccess(response);
    }

    //Volley回调
    @Override
    public void onErrorResponse(VolleyError error) {
        postFailure();
    }

    protected void postFailure() {
        BaseResponse response = new BaseResponse();
        response.setRequestType(requesType);
        if(mBaseResponseListener !=null){
            mBaseResponseListener.onFailure(response);
        }
    }


    protected void postStart() {
        BaseResponse response = new BaseResponse();
        response.setRequestType(requesType);
        if(mBaseResponseListener != null){
            mBaseResponseListener.onStart(response);
        }
    }

    private void postSuccess(BaseResponse response) {
        response.setRequestType(requesType);
        if(mBaseResponseListener != null){
            mBaseResponseListener.onSuccess(response);
        }
    }

    //=============================================================请求操作
    public void cancel(){
        if(mBaseRequest != null){
            mBaseRequest.cancel();
        }
        VolleyManager.INSTANCE.stopRequest(this.getClass().getSimpleName());
    }

    //==========================================================Setter/Getter

    public void setmOnResponseListener(BaseResponseListener baseResponseListener) {
        mBaseResponseListener = baseResponseListener;
    }

    public void setRequesType(int requesType) {
        this.requesType = requesType;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }

    public BaseResponseListener getmOnResponseListener() {

        return mBaseResponseListener;
    }

    public int getRequesType() {
        return requesType;
    }

    public String getJsonStr() {
        return jsonStr;
    }
}
