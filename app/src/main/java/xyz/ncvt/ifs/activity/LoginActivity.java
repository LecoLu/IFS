package xyz.ncvt.ifs.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import xyz.ncvt.ifs.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText mEtLoginUsername;
    private EditText mEtLoginPwd;
    private Button mBtnLogin;
    private TextView mTvLoginForget;
    private TextView mTvLoginIpSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        mEtLoginUsername = (EditText) findViewById(R.id.et_login_username);
        mEtLoginPwd = (EditText) findViewById(R.id.et_login_pwd);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mTvLoginForget = (TextView) findViewById(R.id.tv_login_forget);
        mTvLoginIpSetting = (TextView) findViewById(R.id.tv_login_ip_setting);

        mBtnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String username = mEtLoginUsername.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }

        String pwd = mEtLoginPwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);


    }
}
