package com.example.pruebalaboratorio1.daos;

import com.example.pruebalaboratorio1.beans.pelicula;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class baseDao {

    public Connection getConnection() throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        String user = "root";
        String pass = "root";
        String url = "jdbc:mysql://localhost:3306/mydb?serverTimezone=America/Lima";

        return DriverManager.getConnection(url, user, pass);
    }

    public boolean validarBorrado(pelicula movie) {
        boolean validador = false;
        String[] duracionCompleta = movie.getDuracion().split("m");
        int duracion = Integer.parseInt(duracionCompleta[0]);
        if(duracion>120 && !movie.isPremioOscar()){
            validador = true;
        }

        return validador;
    }
}

