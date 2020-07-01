package com.example.my_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class loginActivity extends AppCompatActivity {
    TextView idTextView;
    TextView passwordTextView;
    Intent intent;
    final String USERNAME = "athmane";
    final String PASSWORD = "athmane";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button auth = (Button) findViewById(R.id.auth);
        idTextView = (TextView) findViewById(R.id.identifiants);
        passwordTextView = (TextView) findViewById(R.id.password);
        intent = new Intent(this, QuizActivity.class);

        auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    //Verifie si le text est vide
                if(idTextView.getText().toString().isEmpty()) {
                    idTextView.setError(getString(R.string.err_username_empty)); ;
                }
                    //Verifie si le text est vide
                if (passwordTextView.getText().toString().isEmpty()){
                    passwordTextView.setError(getString(R.string.err_password_empty));
                }

                if(!idTextView.getText().toString().isEmpty() && !idTextView.getText().toString().equals(USERNAME)) {
                    idTextView.setError(getString(R.string.err_username_wrong)); ;
                }

                if (!passwordTextView.getText().toString().isEmpty() && !passwordTextView.getText().toString().equals(PASSWORD)){
                    passwordTextView.setError(getString(R.string.err_password_wrong));
                }

                if (passwordTextView.getText().toString().equals(PASSWORD) && idTextView.getText().toString().equals(USERNAME)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("username", idTextView.getText().toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

                idTextView.addTextChangedListener(new TextWatcher()  {
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                        @Override
                        public void afterTextChanged(Editable s)  {
                            String id = (String) idTextView.getText().toString();
                            if (id.isEmpty()) {
                                idTextView.setError(getString(R.string.err_username_empty));
                            } else {
                                idTextView.setError(null);
                            }
                        }
                    });

                passwordTextView.addTextChangedListener(new TextWatcher()  {
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void afterTextChanged(Editable s)  {
                        String pass = (String) passwordTextView.getText().toString();
                        if (pass.isEmpty()) {
                            passwordTextView.setError(getString(R.string.err_password_empty));
                        } else {
                            passwordTextView.setError(null);
                        }
                    }
                });
            }
        });
    }

}