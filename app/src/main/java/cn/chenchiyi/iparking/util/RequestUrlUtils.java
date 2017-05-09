package cn.chenchiyi.iparking.util;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ccy820 on 2017/5/10.
 */

public class RequestUrlUtils {
    private RequestUrlUtils(){}

    public static class Builder {
        private boolean isHttp = true;

        private String hostUrl;
        private String path;

        private HashMap<String,Object> params;

       public Builder(boolean isHttp){
           this.isHttp = isHttp;
           params = new LinkedHashMap<>();
       }

       public Builder(){
           this(true);
       }

        public Builder setHost(String hostUrl){
            this.hostUrl = hostUrl;
            return this;
        }

        public Builder setPath(String path){
            this.path = path;
            return this;
        }

        public Builder addParams(String key,Object value){
            params.put(key,value);
            return this;
        }

        public String build(){
            String url;
            if(!TextUtils.isEmpty(path)){
                url = hostUrl + path + "?";
            }else {
                url = hostUrl + "?";
            }
            int i = 0;
            Iterator iterator = params.entrySet().iterator();
            while ((iterator.hasNext())){
                Map.Entry<String,Object> entry = (Map.Entry<String, Object>) iterator.next();
                if(entry != null){
                    if(i == 0){
                        url += entry.getKey()+ "=" + entry.getValue();
                    }else {
                        url += "&" + entry.getKey() + "=" + entry.getValue();
                    }
                }
                i++;
            }
            return url;
        }
    }
}
