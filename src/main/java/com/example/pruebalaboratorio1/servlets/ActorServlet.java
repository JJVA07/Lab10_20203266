package com.example.pruebalaboratorio1.servlets;

import com.example.pruebalaboratorio1.beans.actor;
import com.example.pruebalaboratorio1.beans.pelicula;
import com.example.pruebalaboratorio1.daos.actorDao;
import com.example.pruebalaboratorio1.daos.peliculaDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "actor-servlet", value = "/listaActores")
public class ActorServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        int idPelicula = Integer.parseInt(request.getParameter("idPelicula"));
        actorDao actorDao = new actorDao();
        ArrayList<actor> listaActores = actorDao.listarActores(idPelicula);
        request.setAttribute("listaActores", listaActores);

        peliculaDao peliculaDao = new peliculaDao();
        ArrayList<pelicula> listaPeliculas = peliculaDao.listarPeliculas();
        pelicula movie = (pelicula) listaPeliculas.get(idPelicula-1);
        request.setAttribute("pelicula", movie);

        RequestDispatcher view = request.getRequestDispatcher("actores.jsp");
        view.forward(request,response);
    }
}
