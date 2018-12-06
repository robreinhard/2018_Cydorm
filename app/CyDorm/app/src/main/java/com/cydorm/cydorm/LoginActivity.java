package com.cydorm.cydorm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        EditText userText = (EditText) (findViewById(R.id.userNameLoginEditText));
        EditText passText = (EditText) (findViewById(R.id.passwordLoginEditText));
        Button LoginButton = (Button) (findViewById(R.id.loginButton));

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServerSessionSingleton.getInstance().login(
                        userText.getText().toString(),
                        passText.getText().toString());

                //Is logged in logic
                if(ServerSessionSingleton.getInstance().getUser() != null) {
                    finish();
                }
            }
        });
    }
}
