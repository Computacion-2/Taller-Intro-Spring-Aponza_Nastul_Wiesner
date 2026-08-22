<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Detalles del Artista</title>
    <style>
        body { font-family: "Space Mono", monospace; margin: 40px; }
        .card { background: #f9f9f9; padding: 20px; border: 1px solid #ccc; max-width: 500px; }
        ul { line-height: 1.6; }
    </style>
</head>
<body>

<c:if test="${not empty artist}">
    <h1>Detalles: ${artist.name}</h1>

    <div class="card">
        <p><b>ID del Sistema:</b> ${artist.id}</p>
        <p><b>Nacionalidad:</b> ${artist.nationality}</p>

        <h3>Discografía (Tracks Asociados)</h3>
        <c:if test="${empty artist.tracks}">
            <p>Este artista aún no tiene canciones registradas.</p>
        </c:if>
        <ul>
            <c:forEach var="track" items="${artist.tracks}">
                <li><b>${track.title}</b> - ${track.albumTitle} <i>(${track.genre}, ${track.duration} seg)</i></li>
            </c:forEach>
        </ul>
    </div>
</c:if>

<c:if test="${empty artist}">
    <h2 style="color: rgb(107 20 232 / 0.73);">Artista no encontrado en la base de datos.</h2>
</c:if>

<br>
<a href="${pageContext.request.contextPath}/artists">⬅ Volver al listado principal</a>

</body>
</html>