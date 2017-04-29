package com.example.phobia.final_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private EditText passidEditText, passwordEditText;
    private String jsonrespone,passidString,passwordString;
    private String datauser, message;
    private Button okButton, cancleButton;
    private TextView registerTextView;
    private Boolean status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindwidget();
        buttoncontoller();

    }//main method

    private void buttoncontoller() {
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passidString = passidEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();
                if (passidString.equals("") || passwordString.equals("")) {
                    Toast.makeText(MainActivity.this, "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_LONG).show();
                } else {

                    myconfig myconfig = new myconfig();
                    get_data get_data = new get_data(MainActivity.this);
                    try {

                        get_data.execute(myconfig.getLogin()
                                + "user" + passidString + "&" + "password" + passwordString
                        );
                        jsonrespone = get_data.get().toString();
                        JSONObject jsonObject = new JSONObject(jsonrespone);
                        datauser = jsonObject.getString("data_user");
                        message = jsonObject.getString("message");
                        status = jsonObject.getBoolean("status");
                        if (status == true) {

                            Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainActivity.this,register.class);
                            intent.putExtra("data_user",datauser);
                            startActivity(intent);
                        } else {

                            Toast.makeText(MainActivity.this, message,Toast.LENGTH_LONG).show();
                        }

                        Log.d("login", "login==>" + jsonrespone);

                    } catch (Exception e) {

                        Log.d("login", "login==>" +toString());
                    }

                }
                registerTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this,register.class);
                        startActivity(intent);
                    }
                });
            }
        });
        cancleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passidEditText.getText().clear();
                passwordEditText.getText().clear();
            }
        });
    }
    private void bindwidget() {
        passidEditText = (EditText) findViewById(R.id.passid);
        passwordEditText = (EditText) findViewById(R.id.password);
        registerTextView = (TextView) findViewById(R.id.register);
        okButton = (Button) findViewById(R.id.okButton);
        cancleButton = (Button) findViewById(R.id.cancelButton);
    }
}//main class