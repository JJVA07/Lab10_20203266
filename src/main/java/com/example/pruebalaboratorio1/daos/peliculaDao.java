package com.example.pruebalaboratorio1.daos;

import com.example.pruebalaboratorio1.beans.genero;
import com.example.pruebalaboratorio1.beans.pelicula;
import com.example.pruebalaboratorio1.beans.streaming;

import java.sql.*;
import java.util.ArrayList;

public class peliculaDao extends baseDao{

    public ArrayList<pelicula> listarPeliculas() {

        ArrayList<pelicula> listaPeliculas = new ArrayList<>();


        try {
            Connection conn = this.getConnection();
            Statement stmt = conn.createStatement();

            String sql = "SELECT A.*, B.NOMBRE, C.NOMBRESERVICIO FROM  \n" +
                    "(SELECT * FROM PELICULA ) AS A \n" +
                    "INNER JOIN \n" +
                    "(SELECT * FROM GENERO) AS B\n" +
                    "ON A.IDGENERO = B.IDGENERO\n" +
                    "INNER JOIN \n" +
                    "(SELECT * FROM STREAMING) AS C\n" +
                    "ON A.IDSTREAMING = C.IDSTREAMING\n" +
                    "ORDER BY RATING DESC , BOXOFFICE DESC";


            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                pelicula movie = new pelicula();
                genero genero = new genero();
                streaming streamingService = new streaming();
                int idPelicula = rs.getInt(1);
                movie.setIdPelicula(idPelicula);
                String titulo = rs.getString("titulo");
                movie.setTitulo(titulo);
                String director = rs.getString("director");
                movie.setDirector(director);
                int anoPublicacion = rs.getInt("anoPublicacion");
                movie.setAnoPublicacion(anoPublicacion);
                double rating = rs.getDouble("rating");
                movie.setRating(rating);
                double boxOffice = rs.getDouble("boxOffice");
                movie.setBoxOffice(boxOffice);
                int idGenero = rs.getInt("idGenero");
                String nombregenero = rs.getString("nombre");
                genero.setIdGenero(idGenero);
                genero.setNombre(nombregenero);
                String nombreStreaming = rs.getString("nombreServicio");
                int idStreaming = rs.getInt("idStreaming");
                streamingService.setNombreStreaming(nombreStreaming);
                streamingService.setIdStreaming(idStreaming);
                movie.setStreaming(streamingService);
                movie.setGenero(genero);
                movie.setPremioOscar(rs.getBoolean("premioOscar"));
                movie.setDuracion(rs.getString("duracion"));
                boolean validador= validarBorrado(movie);
                movie.setValidadorBorrado(validador);

                listaPeliculas.add(movie);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaPeliculas;
    }

    public ArrayList<pelicula> listarPeliculasFiltradas (int idGenero, int idStreaming) {
        ArrayList<pelicula> listaPeliculasFiltradas= new ArrayList<>();

        try {
            Connection conn = this.getConnection();
            Statement stmt = conn.createStatement();

            String sql = "SELECT A.*, B.NOMBRE, C.NOMBRESERVICIO FROM  \n" +
                    "(SELECT * FROM PELICULA ) AS A \n" +
                    "INNER JOIN \n" +
                    "(SELECT * FROM GENERO WHERE GENERO.idGenero = ?) AS B\n" +
                    "ON A.IDGENERO = B.IDGENERO\n" +
                    "INNER JOIN \n" +
                    "(SELECT * FROM STREAMING WHERE STREAMING.idStreaming = ?) AS C\n" +
                    "ON A.IDSTREAMING = C.IDSTREAMING\n" +
                    "ORDER BY RATING DESC , BOXOFFICE DESC";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idGenero);
            pstmt.setInt(2,idStreaming);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                pelicula movie = new pelicula();
                genero genero = new genero();
                streaming streamingService = new streaming();
                int idPelicula = rs.getInt(1);
                movie.setIdPelicula(idPelicula);
                String titulo = rs.getString("titulo");
                movie.setTitulo(titulo);
                String director = rs.getString("director");
                movie.setDirector(director);
                int anoPublicacion = rs.getInt("anoPublicacion");
                movie.setAnoPublicacion(anoPublicacion);
                double rating = rs.getDouble("rating");
                movie.setRating(rating);
                double boxOffice = rs.getDouble("boxOffice");
                movie.setBoxOffice(boxOffice);
                int idGeneroF = rs.getInt("idGenero");
                String nombregeneroF = rs.getString("nombre");
                genero.setIdGenero(idGeneroF);
                genero.setNombre(nombregeneroF);
                String nombreStreamingF = rs.getString("nombreServicio");
                int idStreamingF = rs.getInt("idStreaming");
                streamingService.setNombreStreaming(nombreStreamingF);
                streamingService.setIdStreaming(idStreamingF);
                movie.setStreaming(streamingService);
                movie.setGenero(genero);
                movie.setPremioOscar(rs.getBoolean("premioOscar"));
                movie.setDuracion(rs.getString("duracion"));
                boolean validador= validarBorrado(movie);
                movie.setValidadorBorrado(validador);

                listaPeliculasFiltradas.add(movie);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return listaPeliculasFiltradas;
    }

    // AGREGAR CAMPOS FALTANTES (GENERO, STREAMING)
    public void editarPelicula(int idPelicula, String titulo, String director, int anoPublicacion, double rating, double boxOffice){
        try {

            try (Connection conn = getConnection();) {
                String sql = "UPDATE PELICULA SET titulo = ?, director = ?, anoPublicacion = ? ," +
                        "rating = ?, boxOffice = ? WHERE IDPELICULA = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, titulo);
                    pstmt.setString(2, director);
                    pstmt.setInt(3, anoPublicacion);
                    pstmt.setDouble(4, rating);
                    pstmt.setDouble(5, boxOffice);
                    pstmt.setInt(6, idPelicula);
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException  e) {
            e.printStackTrace();
        }


    }


    public void borrarPelicula(int idPelicula) {
        String sql = "DELETE FROM protagonistas WHERE idPelicula = ?;";

        // NOTA: PARA BORRAR UNA PELICULA CORRECTAMENTE NO OLVIDAR PRIMERO BORRARLA DE LA TABLA PROTAGONSITAS
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idPelicula);
            pstmt.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql2 = "DELETE FROM pelicula WHERE idPelicula = ?;";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql2)) {
            pstmt.setInt(1, idPelicula);
            pstmt.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


}
