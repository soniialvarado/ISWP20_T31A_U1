package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txtReg=(TextView)findViewById(R.id.lblRegistration);
        txtReg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent (v.getContext(), Registration.class);
        startActivityForResult(intent, 0);
    }
}

