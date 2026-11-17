<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SIGIPT - Nuba Confort S.A.S.</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f6f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .contenedor {
            background: #ffffff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            text-align: center;
            max-width: 500px;
            width: 90%;
        }
        h1 {
            color: #1e293b;
            margin-bottom: 10px;
        }
        p {
            color: #64748b;
            margin-bottom: 30px;
        }
        .menu-botones {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }
        a.btn {
            display: block;
            padding: 15px;
            background-color: #2563eb;
            color: white;
            text-decoration: none;
            border-radius: 6px;
            font-weight: bold;
            font-size: 16px;
            transition: background-color 0.2s;
        }
        a.btn:hover {
            background-color: #1d4ed8;
        }
        a.btn-secundario {
            background-color: #0f766e;
        }
        a.btn-secundario:hover {
            background-color: #115e59;
        }
    </style>
</head>
<body>
    <div class="contenedor">
        <h1>SIGIPT</h1>
        <p>Sistema de Información de Gestión de Inventario y Producción Textil<br><strong>Nuba Confort S.A.S.</strong></p>
        
        <div class="menu-botones">
            <a href="ProductoServlet?accion=listar" class="btn">Gestión de Productos</a>
            <a href="InventarioServlet?accion=listar" class="btn btn-secundario">Control de Inventario</a>
        </div>
    </div>
</body>
</html>