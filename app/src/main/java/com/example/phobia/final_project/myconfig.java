package com.example.phobia.final_project;

/**
 * Created by Phobia on 4/26/2017.
 */

public class myconfig {
    private String login = "http://10.5.20.82:8081/Final_Project/catarogy/technician/login.php";
    private String register_customer = "http://10.5.20.82:8081/Final_Project/catarogy/costomer/register_C.php";
    private String register_technician = "http://10.5.20.82:8081/Final_Project/catarogy/technician/register_T.php";

    public String getRegister_technician() {
        return register_technician;
    }

    public String getRegister_customer() {
        return register_customer;
    }

    public String getLogin() {
        return login;
    }
}
