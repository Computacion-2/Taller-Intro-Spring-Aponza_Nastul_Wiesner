<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sistema Discográfico</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; text-align: center; }
        .menu-container { display: flex; justify-content: center; gap: 30px; margin-top: 50px; }
        .menu-card {
            padding: 40px;
            border: 2px solid #555;
            border-radius: 10px;
            text-decoration: none;
            color: white;
            background-color: #333;
            font-size: 20px;
            font-weight: bold;
            transition: 0.3s;
        }
        .menu-card:hover { background-color: #555; transform: scale(1.05); }
    </style>
</head>
<body style="background-color: #1e1e1e; color: white;">
<h1>Gestión de Discográfica</h1>
<h2>Panel de Control Principal</h2>

<div class="menu-container">
    <a href="${pageContext.request.contextPath}/artists" class="menu-card">Gestión de Artistas</a>
    <a href="${pageContext.request.contextPath}/tracks" class="menu-card">Gestión de Canciones</a>
</div>
</body>
</html>