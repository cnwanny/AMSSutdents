package com.wanny.amssutdents.framework_ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.wanny.amssutdents.framework_care.ActivityStackManager;
import com.wanny.amssutdents.framework_mvpbasic.MvpActivity;
import com.wanny.amssutdents.framework_utils.MacOperate;
import com.wanny.amssutdents.framework_utils.PreferenceUtil;

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
        if(!TextUtils.isEmpty(editText.getText())){
            BodyReq bodyReq = new BodyReq();
            bodyReq.setMac(MacOperate.getMac(mContext));
            bodyReq.setStudentNumber(editText.getText().toString());
            mvpPresenter.login(bodyReq,"正在登录");
        }else{
            Toast.makeText(mContext,"请输入学号",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void success(LoginEntity s) {
        Toast.makeText(mContext,s.getMessage(),Toast.LENGTH_SHORT).show();
        if(s.isStatus()){
            PreferenceUtil.getInstance(mContext).saveString("StudentId",editText.getText().toString());
            Intent intent = new Intent(LoginActivity.this , AllApplicationActivity.class);
            startActivity(intent);
        }
    }


    @OnClick(R.id.clos_image)
    void closeActivity(View view) {
        ActivityStackManager.getInstance().exitActivity(mActivity);
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
