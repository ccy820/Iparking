package cn.chenchiyi.iparking.api.http.base;

/**
 * Created by ccy820 on 2017/5/10.
 */

public interface BaseResponseListener {
    void onStart(BaseResponse response);
    void onFailure(BaseResponse response);
    void onSuccess(BaseResponse response);
}
