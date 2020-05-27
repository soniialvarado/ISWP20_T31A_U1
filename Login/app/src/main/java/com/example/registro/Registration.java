package com.example.registro;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.login.R;

import org.json.JSONObject;

public class Registration extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener, View.OnClickListener {

    EditText txtName,txtLastName,txtEmail,txtPass,txtUser;
    Button btnReg;
    ProgressDialog progreso;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        txtName=findViewById(R.id.txtName);
        txtLastName=findViewById(R.id.txtLastName);
        txtEmail=findViewById(R.id.txtUser);
        txtPass=findViewById(R.id.txtPass);
        txtUser=findViewById(R.id.txtUser);
        btnReg=findViewById(R.id.btnReg);
        request = Volley.newRequestQueue(this);
        btnReg.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnReg: //En caso de que seleccione el texto de registrars
                cargarWebServ();
                break;

            default:
                break;
        }
    }
    private void cargarWebServ(){
        if(!txtLastName.getText().toString().equals("") && !txtEmail.getText().toString().equals("")
        && !txtName.getText().toString().equals("") && !txtLastName.getText().toString().equals("")
        && !txtUser.getText().toString().equals("")){
            progreso=new ProgressDialog(this);
            progreso.setMessage("Cargando...");
            progreso.show();
            String ip="https://ingsoftu1.000webhostapp.com/";
            String url=ip+"registrar_usuario.php?name="+txtName.getText().toString()+
                    "&last_name="+txtLastName.getText().toString()+
                    "&user="+txtUser.getText().toString()+"&pass="+txtPass.getText().toString()+
                    "&email="+txtEmail.getText().toString();
            url=url.replace(" ","%20");
            jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
            request.add(jsonObjectRequest);
            progreso.hide();
        }
        else {
            Toast.makeText(this,"Todos los campos deben estar llenos",Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this,"No se pudo registrar",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(this,"Se ha registrado exitosamente",Toast.LENGTH_SHORT).show();

    }
}
