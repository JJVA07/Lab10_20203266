

<%@page import="java.util.ArrayList"%>
<%@page import="com.example.pruebalaboratorio1.beans.actor"%>
<%@ page import="com.example.pruebalaboratorio1.beans.pelicula" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    //Job job = (Job) request.getAttribute("job");
    ArrayList<actor> lista = (ArrayList) request.getAttribute("listaActores");
    pelicula movie = (pelicula) request.getAttribute("pelicula");
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><%=movie.getTitulo()%></title>
</head>
<body>

<h1>Actores de: <%=movie.getTitulo()%></h1>
<table border="1">
    <tr>
        <th>idActor</th>
        <th>Nombre</th>
        <th>Apellido</th>
        <th>Ano Nacimiento</th>
        <th>Ganador Premio Oscar</th>

    </tr>
    <%
        for (actor actuador : lista) {
    %>
    <tr>
        <td><%=actuador.getIdActor()%></td>
        <td><%=actuador.getNombre()%></td>
        <td><%=actuador.getApellido()%></td>
        <td><%=actuador.getAnoNacimiento()%></td>
        <td><%=actuador.isPremioOscar()%></td>


    </tr>

    <%
        }
    %>

</table>
</body>
</html>
