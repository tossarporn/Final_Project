package com.example.phobia.final_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class register extends AppCompatActivity {
    private Button backButton;
    private EditText idEditText, passEditText;
    private TextView customerTextView, technicianTextView;
    private String idString, passString;
    private String jsonrespone;
    private String message,datauser;
    private int customer = 1;
    private int technician= 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        bindwidget();
        buttoncontroller();
    }//main class

    private void buttoncontroller() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idEditText.getText().clear();
                passEditText.getText().clear();
                Intent intent = new Intent(register.this, MainActivity.class);
                startActivity(intent);
            }
        });
        //choose customer
        customerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idString = idEditText.getText().toString().trim();
                passString = passEditText.getText().toString().trim();
                if (idString.equals("") || passString.equals("")) {
                    Toast.makeText(register.this,"กรุณากรอกข้อมูลให้ครบ",Toast.LENGTH_LONG).show();
                } else {

                    myconfig myconfig = new myconfig();
                    get_data get_data = new get_data(register.this);

                    try {
                        get_data.execute(myconfig.getRegister_customer()
                                + "user" + idString + "&"
                                + "password" + passString +"&"
                                +"status" + customer
                        );
                        jsonrespone = get_data.get().toString();
                        JSONObject jsonObject = new JSONObject(jsonrespone);
                        customer = jsonObject.getInt("status");
                        message = jsonObject.getString("message");
                        datauser = jsonObject.getString("data_user");
                        if (customer == 1) {
                            Toast.makeText(register.this, message, Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(register.this, MainActivity.class);
                            intent.putExtra("data_user", datauser);
                            startActivity(intent);
                        } else {
                            Toast.makeText(register.this,message,Toast.LENGTH_LONG).show();
                        }
                        Log.d("register_c", "register_C==>" + jsonrespone);
                    } catch (Exception e) {
                        Log.d("register_c", "register_c==>" + e.toString());
                    }
                }
            }
        });//end
        //choose  technician
        technicianTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idString = idEditText.getText().toString().trim();
                passString = passEditText.getText().toString().trim();
                if (idString.equals("") || passString.equals("")) {
                    Toast.makeText(register.this,"กรุณากรอกข้อมุลให้ครบ",Toast.LENGTH_LONG).show();
                } else {

                    myconfig myconfig = new myconfig();
                    get_data get_data = new get_data(register.this);
                    try {
                        get_data.execute(myconfig.getRegister_technician()
                                + "user" + idString + "&"
                                + "password" + passString + "&"
                                + "status" + technician
                        );
                        jsonrespone = get_data.get().toString();
                        JSONObject jsonObject = new JSONObject(jsonrespone);
                        technician = jsonObject.getInt("status");
                        message = jsonObject.getString("message");
                        datauser = jsonObject.getString("data_user");

                        if (technician == 2) {
                            Toast.makeText(register.this,message,Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(register.this,MainActivity.class);
                            intent.putExtra("data_user", datauser);
                            startActivity(intent);
                        }
                        Log.d("register_technician", "register_technician==>" + jsonrespone);

                    } catch (Exception e) {
                        Log.d("register_technician", "register_technician==>" + e.toString());
                    }
                }
            }
        });
    }//end
        //widget
    private void bindwidget() {
        idEditText = (EditText) findViewById(R.id.passid);
        passEditText = (EditText) findViewById(R.id.password);
        backButton = (Button) findViewById(R.id.back);
        customerTextView = (TextView) findViewById(R.id.customer);
        technicianTextView = (TextView) findViewById(R.id.technician);
    }//end
}//main method
