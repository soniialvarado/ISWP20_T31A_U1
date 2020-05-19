package com.example.login;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
public class ConnectionMySQL {
    private static Connection conn;
    // Librería de MySQL
    private static final String driver = "com.mysql.jdbc.Driver";
    // Nombre de usuario
    public static final String username = "root";
    // Clave de usuario
    public static final String password = "";
    public static final String url = "jdbc:mysql://localhost:3306/gym";
    public ConnectionMySQL(){
        conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            if(conn!=null){

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
