package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import java.sql.Connection;
import android.os.Bundle;

public class Registration extends AppCompatActivity {

    ConnectionMySQL SQL=new ConnectionMySQL();
    Connection con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }
}
