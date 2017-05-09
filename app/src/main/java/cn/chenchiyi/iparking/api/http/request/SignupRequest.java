package cn.chenchiyi.iparking.api.http.request;

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

public class SignupRequest extends BaseHttpRequestClient{

    public String username;
    public String password;
    public String password2;
    public String deviceId;
    @Override
    public String setUrl() {
        return new RequestUrlUtils.Builder()
                .setHost(Iparking.HOST)
                .setPath("/userSignup")
                .build();
    }

    @Override
    public String setTag() {
        return null;
    }

    @Override
    public void parseResponse(BaseResponse response, JSONObject json) throws JSONException {
        if (response.getStatus()==200){
            UserEntity info= JSONUtils.toObject(json.optString("data"), UserEntity.class);
            response.setData(info);
        }
    }

    @Override
    public void postValue(Map<String, String> keyValue) {
        keyValue.put("username",username);
        keyValue.put("password",password);
        keyValue.put("password2",password2);
        keyValue.put("deviceid",deviceId);
    }
}
