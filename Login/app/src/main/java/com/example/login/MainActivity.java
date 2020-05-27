package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.entidades.Passenger;
import com.example.menu.User;
import com.example.registro.Registration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, Response.Listener<JSONObject>,Response.ErrorListener{
    TextView txtUser;
    TextView txtPass;
    TextView txtReg;
    Button btnLog;
    ProgressDialog progreso;
    RequestQueue request;
    Boolean exist=false;
    JsonObjectRequest jsonObjectRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtReg=findViewById(R.id.lblRegistration);
        txtReg.setOnClickListener(this);
        txtUser=findViewById(R.id.txtUser);
        txtPass=findViewById(R.id.txtPass);
        btnLog=findViewById(R.id.btnLog);
        request= Volley.newRequestQueue(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnLog://Si se presiona ingresar
                login();
                if(exist){
                    Intent intent = new Intent(v.getContext(), User.class);
                    startActivityForResult(intent, 0);
                    progreso.hide();
                }
                else if(!exist){
                    Toast toast=Toast.makeText(this,"Alguno de los datos es incorrecto",Toast.LENGTH_SHORT);
                }
                break;
            case R.id.lblRegistration: //En caso de que seleccione el texto de registrarse
                Intent intent = new Intent(v.getContext(), Registration.class);
                startActivityForResult(intent, 0);
                break;
            default:
                break;
        }
    }
    public void login() {
        progreso=new ProgressDialog(this);
        progreso.setMessage("Cargando...");
        progreso.show();
        String ip="https://ingsoftu1.000webhostapp.com/";
        String url=ip+"login_usuario.php?user="+txtUser.getText().toString() +"&pass="+txtPass.getText().toString();
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this,"No se pudo registrar",Toast.LENGTH_SHORT).show();
        Log.i("ERROR",error.toString());

    }

    @Override
    public void onResponse(JSONObject response) {
        Passenger me =new Passenger();
        JSONArray json=response.optJSONArray("usuario");
        JSONObject jsonObject=null;
        try{
            jsonObject=json.getJSONObject(0);
            me.setName(jsonObject.optString("name"));
            me.setEmail(jsonObject.optString("email"));
            me.setLast_name(jsonObject.optString("last_name"));
            me.setUser(jsonObject.optString("user"));
            me.setPass(jsonObject.optString("pass"));
            me.setId(jsonObject.getInt("id"));
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        if(me.getId()!=0){
            exist=true;
            Toast.makeText(this,"Bienvenido "+me.getName(),Toast.LENGTH_SHORT).show();
        }
        else{
            exist=false;
        }

    }
}

