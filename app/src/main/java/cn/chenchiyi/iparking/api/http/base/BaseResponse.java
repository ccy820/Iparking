package cn.chenchiyi.iparking.api.http.base;

import java.util.ArrayList;

/**
 * Created by ccy820 on 2017/5/10.
 */

public class BaseResponse {

    //请求识别码
    private int status;
    //多个请求分区码
    private int requestType;
    //json返回信息->String
    private String jsonStr;
    //消息
    private String msg;
    //jsonArray保存
    private ArrayList<Object> datas = new ArrayList<>();
    //jsonObject保存
    private Object data;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setRequestType(int requestType) {
        this.requestType = requestType;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setDatas(ArrayList<Object> datas) {
        this.datas = datas;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getStatus() {

        return status;
    }

    public int getRequestType() {
        return requestType;
    }

    public String getJsonStr() {
        return jsonStr;
    }

    public String getMsg() {
        return msg;
    }

    public ArrayList<Object> getDatas() {
        return datas;
    }

    public Object getData() {
        return data;
    }
}
