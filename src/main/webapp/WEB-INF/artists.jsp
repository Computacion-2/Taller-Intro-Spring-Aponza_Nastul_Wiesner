<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestión de Artistas</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        table { border-collapse: collapse; width: 100%; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        .form-section { background: #f9f9f9; padding: 15px; margin-bottom: 20px; border: 1px solid #ccc; }
    </style>
</head>
<body>
<h1>Directorio de Artistas</h1>

<!-- Sección de Búsqueda -->
<div class="form-section">
    <h3>Buscar Artista</h3>
    <form action="${pageContext.request.contextPath}/artists" method="GET">
        <input type="hidden" name="action" value="search">
        <input type="text" name="name" placeholder="Ingrese el nombre exacto" required>
        <button type="submit">Buscar y Ver Detalles</button>
    </form>
</div>

<!-- Sección de Creación -->
<div class="form-section">
    <h3>Registrar Nuevo Artista</h3>
    <form action="${pageContext.request.contextPath}/artists" method="POST">
        <input type="hidden" name="action" value="create">
        <input type="text" name="name" placeholder="Nombre" required>
        <input type="text" name="nationality" placeholder="Nacionalidad" required>
        <button type="submit">Crear Artista</button>
    </form>
</div>

<!-- Listado de Artistas -->
<h2>Lista de Artistas Registrados</h2>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Nacionalidad</th>
        <th>Acciones</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="artist" items="${artists}">
        <tr>
            <td>${artist.id}</td>
            <td>${artist.name}</td>
            <td>${artist.nationality}</td>
            <td>
                <!-- Formulario para eliminar -->
                <form action="${pageContext.request.contextPath}/artists" method="POST" style="margin: 0;">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="id" value="${artist.id}">
                    <button type="submit" style="color: red;">Eliminar</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>