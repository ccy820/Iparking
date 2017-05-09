package cn.chenchiyi.iparking.api.http.request;


import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import cn.chenchiyi.iparking.api.Iparking;
import cn.chenchiyi.iparking.api.http.base.BaseHttpRequestClient;
import cn.chenchiyi.iparking.api.http.base.BaseResponse;
import cn.chenchiyi.iparking.api.model.UserEntity;
import cn.chenchiyi.iparking.util.JSONUtils;
import cn.chenchiyi.iparking.util.RequestUrlUtils;

/**
 * Created by ccy820 on 2017/5/10.
 */

public class LoginRequest extends BaseHttpRequestClient {

    public String username;
    public String password;
    public String deviceId;

    @Override
    public String setUrl() {
        return new RequestUrlUtils.Builder()
                .setHost(Iparking.HOST)
                .setPath("/userLogin")
                .build();
    }

    @Override
    public String setTag() {
        return null;
    }

    @Override
    public void parseResponse(BaseResponse response, JSONObject json) throws JSONException {
        if(response.getStatus()==200){
            UserEntity info = JSONUtils.toObject(json.optString("data"),UserEntity.class);
            response.setData(info);
        }
    }


    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(BaseResponse response) {

    }

    @Override
    public void postValue(Map<String, String> keyValue) {
        keyValue.put("username",username);
        keyValue.put("password",password);
        keyValue.put("deviceid",deviceId);
    }
}
