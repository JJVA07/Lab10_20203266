package com.example.pruebalaboratorio1.beans;

public class pelicula {

    private int idPelicula;
    private String titulo;

    private String director;
    private int anoPublicacion;
    private Double rating;
    private double boxOffice;
    private genero genero;
    private streaming streaming;
    private String duracion;
    private boolean premioOscar;
    private boolean validadorBorrado;


    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getAnoPublicacion() {
        return anoPublicacion;
    }

    public void setAnoPublicacion(int anoPublicacion) {
        this.anoPublicacion = anoPublicacion;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public double getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(double boxOffice) {
        this.boxOffice = boxOffice;
    }

    public com.example.pruebalaboratorio1.beans.genero getGenero() {
        return genero;
    }

    public void setGenero(com.example.pruebalaboratorio1.beans.genero genero) {
        this.genero = genero;
    }

    public com.example.pruebalaboratorio1.beans.streaming getStreaming() {
        return streaming;
    }

    public void setStreaming(com.example.pruebalaboratorio1.beans.streaming streaming) {
        this.streaming = streaming;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public boolean isPremioOscar() {
        return premioOscar;
    }

    public void setPremioOscar(boolean premioOscar) {
        this.premioOscar = premioOscar;
    }

    // private boolean validadorBorrado;


    public boolean isValidadorBorrado() {
        return validadorBorrado;
    }

    public void setValidadorBorrado(boolean validadorBorrado) {
        this.validadorBorrado = validadorBorrado;
    }
}
