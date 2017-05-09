package cn.chenchiyi.iparking;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.chenchiyi.iparking.api.http.base.BaseResponse;

import cn.chenchiyi.iparking.api.http.base.BaseResponseListener;
import cn.chenchiyi.iparking.api.http.request.LoginRequest;
import cn.chenchiyi.iparking.api.http.request.SignupRequest;
import cn.chenchiyi.iparking.util.DeviceUtils;
import cn.chenchiyi.iparking.util.MD5Utils;

/**
 * Created by ccy820 on 2017/5/10.
 */

public class LoginActivity extends AppCompatActivity implements BaseResponseListener {

    EditText Login_username;
    EditText Login_password1;
    EditText Login_password2;
    Button Login_login_btn;
    TextView Login_exchange_btn;

    String regEx = "[a-zA-Z0-9]";

    //状态码，true表示当前在登录页，false表示当前在注册页
    boolean LoginOrSignup = true;
    String deviceId;

    //请求
    LoginRequest mLoginRequest;
    SignupRequest mSignupRequest;

    //SharePreference
    SharedPreferences.Editor ShareEditor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //获取设备ID
        deviceId = DeviceUtils.getDeviceId(this);
        ShareEditor = getSharedPreferences("Iparking",MODE_PRIVATE).edit();

        initWeight();

        //Login请求初始化
        mLoginRequest = new LoginRequest();
        mLoginRequest.setmOnResponseListener(this);
        mLoginRequest.setRequesType(0);
        //signup请求初始化
        mSignupRequest = new SignupRequest();
        mSignupRequest.setmOnResponseListener(this);
        mSignupRequest.setRequesType(1);

        initOnClickListener();
    }

    private void initOnClickListener() {
        Login_exchange_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LoginOrSignup){
                    Login_password2.setVisibility(View.VISIBLE);
                    Login_login_btn.setText("注册");
                    Login_exchange_btn.setText("转到登录>>>");
                    LoginOrSignup = false;
                }else {
                    Login_password2.setVisibility(View.GONE);
                    Login_login_btn.setText("登录");
                    Login_exchange_btn.setText("转到注册>>>");
                    LoginOrSignup = true;
                }
            }
        });

        Login_login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = String.valueOf(Login_username.getText());
                String password = String.valueOf(Login_password1.getText());
                String password2 = String.valueOf(Login_password2.getText());
                if(LoginOrSignup){
                    //登录
                    if(checkUsername(username,regEx,20) && checkPassword(password,regEx,20)){
                        //发送登录请求
                        mLoginRequest.username = username;
                        mLoginRequest.password = MD5Utils.getMD5(password);
                        mLoginRequest.deviceId = deviceId;
                        mLoginRequest.post();
                        Toast.makeText(getApplication(),"登录成功",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    //注册
                    if(!password.equals(password2)){
                        Toast.makeText(getApplication(),"两次的输入的密码必须一致",Toast.LENGTH_SHORT).show();
                    }else if (checkUsername(username,regEx,20) && checkPassword(password,regEx,20)){
                        //发送注册请求
                        mSignupRequest.username = username;
                        mSignupRequest.password = MD5Utils.getMD5(password);
                        mSignupRequest.password2 = MD5Utils.getMD5(password2);
                        mSignupRequest.deviceId = deviceId;
                        mSignupRequest.post();
                        Toast.makeText(getApplication(), "注册成功", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean checkUsername(String username , String regEx , int maxLength){
        if (username.equals("")){
            Toast.makeText(getApplication(), "用户名不能为空", Toast.LENGTH_SHORT).show();
        }else if (username.length()>maxLength) {
            Toast.makeText(getApplication(), "用户名不能超过" + maxLength + "字符", Toast.LENGTH_SHORT).show();
        }else if (!fitString(username,regEx)){
            Toast.makeText(getApplication(), "用户名只能为英文字母与数字", Toast.LENGTH_SHORT).show();
        }else {
            return true;
        }
        return false;
    }

    private boolean fitString(String str, String regEx) {
        return str.replaceAll(regEx, "").length() == 0;
    }

    private boolean checkPassword(String username , String regEx , int maxLength){
        if (username.equals("")){
            Toast.makeText(getApplication(), "密码不能为空", Toast.LENGTH_SHORT).show();
        }else if (username.length()>maxLength) {
            Toast.makeText(getApplication(), "密码不能超过" + maxLength + "字符", Toast.LENGTH_SHORT).show();
        }else if (!fitString(username,regEx)){
            Toast.makeText(getApplication(), "密码只能为英文字母与数字", Toast.LENGTH_SHORT).show();
        }else {
            return true;
        }
        return false;
    }

    private void initWeight() {
        Login_username = (EditText) findViewById(R.id.Login_username);
        Login_password1 = (EditText) findViewById(R.id.Login_password1);
        Login_password2 = (EditText) findViewById(R.id.Login_password2);
        Login_login_btn = (Button) findViewById(R.id.Login_login_btn);
        Login_exchange_btn = (TextView) findViewById(R.id.Login_exchange_btn);
    }

    @Override
    public void onStart(BaseResponse response) {

    }

    @Override
    public void onFailure(BaseResponse response) {
        Toast.makeText(this, "请求失败", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess(BaseResponse response) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            //返回键监听
            setResult(404);
            finish();
        }
        return false;
    }
}
