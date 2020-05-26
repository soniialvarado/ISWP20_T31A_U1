package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ConnectionMySQL SQL=new ConnectionMySQL(this);
    Connection con;
    TextView txtEmail;
    TextView txtPass;
    TextView txtReg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtReg=findViewById(R.id.lblRegistration);
        txtReg.setOnClickListener(this);
        txtEmail=findViewById(R.id.txtEmail);
        txtPass=findViewById(R.id.txtPass);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnLog://Si se presiona ingresar
                boolean band=login();
                if (band){
                    Toast toast = Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    Toast toast = Toast.makeText(this, "Usuario incorrecto", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;

            case R.id.lblRegistration: //En caso de que seleccione el texto de registrarse
                    Intent intent = new Intent (v.getContext(), Registration.class);
                    startActivityForResult(intent, 0);
                break;

            default:
                break;
        }
    }
    public boolean login() {
        try {
            con = null;
            con =  SQL.getConnection();
            ResultSet res;
            try (PreparedStatement ps = con.prepareStatement("SELECT * FROM passenger WHERE email=? AND password=?;")) {
                ps.setString(1, txtEmail.getText().toString());
                ps.setString(2, txtPass.getText().toString());
                res = ps.executeQuery();
            }
            if (res.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Toast toast = Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT);
            toast.show();
        } catch (NullPointerException ex) {
            Toast toast = Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT);
            toast.show();
        } catch (IllegalStateException ex) {
            Toast toast = Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT);
            toast.show();
        }
        return false;
    }

}

