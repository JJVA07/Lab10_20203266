package com.example.pruebalaboratorio1.daos;

import com.example.pruebalaboratorio1.beans.genero;
import com.example.pruebalaboratorio1.beans.streaming;
import com.example.pruebalaboratorio1.beans.pelicula;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class listasDao extends baseDao{

    public ArrayList<genero> listarGeneros() {

        ArrayList<genero> listaGeneros = new ArrayList<>();
        String sql = "SELECT * FROM genero;";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()){
                genero gen = new genero();
                gen.setIdGenero(rs.getInt(1));
                gen.setNombre(rs.getString(2));
                listaGeneros.add(gen);

            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaGeneros;
    }

    public ArrayList<streaming> listarStraming() {

        ArrayList<streaming> listaStreaming = new ArrayList<>();

        String sql = "SELECT * FROM streaming;";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()){
                streaming streamingService = new streaming();
                streamingService.setIdStreaming(rs.getInt(1));
                streamingService.setNombreStreaming(rs.getString(2));
                listaStreaming.add(streamingService);

            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaStreaming;
    }
}
