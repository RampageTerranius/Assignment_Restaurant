package com.example.tyler.assignmentrestaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_LoginPage extends AppCompatActivity {
    private List <Login> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__login_page);

        users = new ArrayList();
        XMLHandle xmlLogin = new XMLLogin();
        xmlLogin.setContext(this);
        users = xmlLogin.parseAsset("User.xml");
        if (xmlLogin.getError() != "")
            Log.d(MainActivity_LoginPage.class.getSimpleName(), xmlLogin.getError());
    }

    public void ClickHandle(View view) {
        switch(view.getId()) {
            case R.id.button_Login:
                EditText lUser = (EditText) findViewById(R.id.et_Login);
                EditText lPass = (EditText) findViewById(R.id.et_Password);
                for (Login u : users) {
                    if (u.getUser().equals(lUser.getText().toString())) {
                        if (u.getPassword().equals(lPass.getText().toString()))
                        {
                            ((TextView) findViewById(R.id.tv_loginText)).setText(R.string.LoginPage_CorrectLoginDetails);
                            openMenuActivity(lUser.getText().toString());
                            return;
                        }
                    }
                    else
                        ((TextView) findViewById(R.id.tv_loginText)).setText(R.string.LoginPage_IncorrectLoginDetails);
                }
                break;

            case R.id.et_Login:
                EditText l = (EditText) view;
                l.setText("");
                break;

            case R.id.et_Password:
                EditText p = (EditText) view;
                p.setText("");
                break;
        }
    }

    public void openMenuActivity(String user) {
        Intent intent = new Intent(this, MenuOrder.class);

        intent.putExtra("data", user);

        startActivity(intent);
    }
}
