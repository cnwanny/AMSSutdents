package com.wanny.amssutdents.framework_ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wanny.amssutdents.R;
import com.wanny.amssutdents.amsstudent_business.BodyReq;
import com.wanny.amssutdents.amsstudent_business.login_mvp.LoginEntity;
import com.wanny.amssutdents.amsstudent_business.login_mvp.LoginImpl;
import com.wanny.amssutdents.amsstudent_business.login_mvp.LoginPresenter;
import com.wanny.amssutdents.framework_mvpbasic.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * NAme : ${Name}
 * Author: wanny(**飞)
 * Time: 2018/2/23 10:16
 */

public class LoginActivity extends MvpActivity<LoginPresenter> implements LoginImpl {


    @BindView(R.id.clos_image)
    ImageView closImage;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.textView)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_view);
        ButterKnife.bind(this);
    }



    @OnClick(R.id.textView)
    void startLogin(View view){
        BodyReq bodyReq = new BodyReq();
        bodyReq.setMac("04:e6:76:c3:74:32");
        bodyReq.setStudentNumber("20171212001");
        mvpPresenter.login(bodyReq,"正在登录");
    }




    @Override
    public void success(LoginEntity s) {
        if(s.isStatus()){
            Toast.makeText(mContext,s.getMessage(),Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(LoginActivity.this , AllApplicationActivity.class);
        startActivity(intent);
    }

    @Override
    public void fail(int code, String errorMessage) {

    }

    @Override
    public void loadIng(String title) {

    }

    @Override
    public void hide() {

    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }
}
