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
    private Button okButton, cancelButton;
    private ImageView imagelogo, logoid, logopass;
    private TextView register;
    private EditText passidEditText, passwordEditText;
    private String passidString,passwordString,jsonrespone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindwidget();
        buttoncontoller();

    }//main method

    private void buttoncontoller() {

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, activity_register.class);
                startActivity(intent);
            }
        });
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passidString = passidEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();
                if (passidString.equals("") || passwordString.equals("")) {
                    Toast.makeText(MainActivity.this, "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_LONG).show();
                }
                else {

                    myconfig myconfig = new myconfig();
                    get_data get_data = new get_data(MainActivity.this);
                    try {
                        get_data.execute(myconfig.getLogin()
                        );
                        jsonrespone = get_data.get().toString();
                        JSONObject jsonObject = new JSONObject(jsonrespone);

                    } catch (Exception e) {
                        Log.d("login", "login==>" + jsonrespone);
                    }
                }
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void bindwidget() {
        okButton = (Button) findViewById(R.id.ok);
        cancelButton = (Button) findViewById(R.id.cancel);
        imagelogo = (ImageView) findViewById(R.id.imagelogo);
        logoid = (ImageView) findViewById(R.id.logoid);
        logopass = (ImageView) findViewById(R.id.logopass);
        register = (TextView) findViewById(R.id.register);
        passidEditText = (EditText) findViewById(R.id.passid);
        passwordEditText = (EditText) findViewById(R.id.password);
    }
}//main class