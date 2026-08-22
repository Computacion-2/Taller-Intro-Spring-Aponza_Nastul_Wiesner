<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestión de Canciones</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        table { border-collapse: collapse; width: 100%; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        .form-section { background: #f9f9f9; padding: 15px; margin-bottom: 20px; border: 1px solid #ccc; }
        select { padding: 5px; min-width: 150px; }
    </style>
</head>
<body>
<h1>Directorio de Canciones (Tracks)</h1>


<div class="form-section">
    <h3>Registrar Nueva Canción</h3>
    <form action="${pageContext.request.contextPath}/tracks" method="POST">
        <input type="hidden" name="action" value="create">
        <input type="text" name="title" placeholder="Título" required>
        <input type="text" name="genre" placeholder="Género" required>
        <input type="number" name="duration" placeholder="Duración (seg)" required>
        <input type="text" name="albumTitle" placeholder="Álbum" required>

        <p style="margin-bottom: 5px; font-size: 14px;"><b>Asignar Artistas (Mantén Ctrl para elegir varios):</b></p>
        <select name="artistIds" multiple required size="5">
            <c:forEach var="artist" items="${artists}">
                <option value="${artist.id}">${artist.name}</option>
            </c:forEach>
        </select>
        <br><br>
        <button type="submit">Crear Track</button>
    </form>
</div>

<h2>Lista de Tracks Registrados</h2>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Título</th>
        <th>Género</th>
        <th>Duración</th>
        <th>Álbum</th>
        <th>Artistas</th>
        <th>Acciones</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="track" items="${tracks}">
        <tr>
            <td>${track.id}</td>
            <td>${track.title}</td>
            <td>${track.genre}</td>
            <td>${track.duration}s</td>
            <td>${track.albumTitle}</td>
            <td>
                <c:forEach var="a" items="${track.artists}">
                    • ${a.name}<br>
                </c:forEach>
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/tracks" method="POST" style="margin: 0;">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="id" value="${track.id}">
                    <button type="submit" style="color: red;">Eliminar</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<br>
<a href="${pageContext.request.contextPath}/artists">⬅ Volver a Gestión de Artistas</a>
</body>
</html>