package com.example.login;

import android.content.Context;
import android.widget.Toast;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

import static android.widget.Toast.makeText;

public class ConnectionMySQL {
    private static Connection conn;
    // Librería de MySQL
    private static final String driver = "com.mysql.jdbc.Driver";
    // Nombre de usuario
    public static final String username = "ukzh1ihfgqveji83";
    // Clave de usuario
    public static final String password = "mAW7DJmnbMJ5SpWkiVod";
    public static final String url = "jdbc:mysql://ukzh1ihfgqveji83:mAW7DJmnbMJ5SpWkiVod@bepe8mrmfxtgprejel0c-mysql.services.clever-cloud.com:3306/bepe8mrmfxtgprejel0c";
    public ConnectionMySQL(Context context){
        conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            if(conn!=null){
                Toast se_conecto = Toast.makeText(context, "Se conecto", 20);
            }
            else{
                Toast se_conecto = Toast.makeText(context, "No conecto", 20);
            }
        }catch (ClassNotFoundException | SQLException e) {
        }
    }
    public Connection getConnection(){
        return conn;
    }
    public void Desconectar(){
        conn=null;
        if(conn==null){
            System.out.println("Conexion terminada");
        }
    }
}
