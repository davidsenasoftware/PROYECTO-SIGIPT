<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sigipt.modelo.Producto" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de Productos - SIGIPT</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f6f9;
            margin: 0;
            padding: 20px;
            color: #1e293b;
        }
        .contenedor {
            max-width: 1000px;
            margin: 0 auto;
            background: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
        }
        .encabezado {
            display: flex;
            justify-content: space-between;
            align-items: center;
            border-bottom: 2px solid #e2e8f0;
            padding-bottom: 15px;
            margin-bottom: 25px;
        }
        .btn-volver {
            text-decoration: none;
            background-color: #64748b;
            color: white;
            padding: 8px 15px;
            border-radius: 5px;
            font-size: 14px;
        }
        .formulario-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 15px;
            background-color: #f8fafc;
            padding: 20px;
            border-radius: 8px;
            border: 1px solid #e2e8f0;
            margin-bottom: 30px;
        }
        .campo {
            display: flex;
            flex-direction: column;
        }
        label {
            font-weight: 600;
            margin-bottom: 5px;
            font-size: 14px;
        }
        input[type="text"] {
            padding: 8px 10px;
            border: 1px solid #cbd5e1;
            border-radius: 5px;
            font-size: 14px;
        }
        .acciones-form {
            grid-column: 1 / -1;
            display: flex;
            gap: 10px;
        }
        button.btn-guardar {
            background-color: #2563eb;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            font-weight: bold;
            cursor: pointer;
        }
        button.btn-guardar:hover {
            background-color: #1d4ed8;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }
        th, td {
            border: 1px solid #e2e8f0;
            padding: 12px;
            text-align: left;
            font-size: 14px;
        }
        th {
            background-color: #1e293b;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f8fafc;
        }
        .btn-editar {
            background-color: #f59e0b;
            color: white;
            padding: 5px 10px;
            text-decoration: none;
            border-radius: 4px;
            font-size: 13px;
        }
        .btn-eliminar {
            background-color: #dc2626;
            color: white;
            padding: 5px 10px;
            text-decoration: none;
            border-radius: 4px;
            font-size: 13px;
        }
    </style>
</head>
<body>
    <%
        Producto productoEditar = (Producto) request.getAttribute("productoEditar");
        boolean editando = (productoEditar != null);
    %>

    <div class="contenedor">
        <div class="encabezado">
            <h2>Módulo de Productos - SIGIPT</h2>
            <a href="index.jsp" class="btn-volver">← Volver al Inicio</a>
        </div>

        <form action="ProductoServlet" method="POST" class="formulario-grid">
            <input type="hidden" name="idProducto" value="<%= editando ? productoEditar.getIdProducto() : "0" %>">

            <div class="campo">
                <label>Modelo:</label>
                <input type="text" name="modelo" required placeholder="Ej. Camiseta Polo"
                       value="<%= editando ? productoEditar.getModelo() : "" %>">
            </div>

            <div class="campo">
                <label>Talla:</label>
                <input type="text" name="talla" required placeholder="Ej. S, M, L, XL"
                       value="<%= editando ? productoEditar.getTalla() : "" %>">
            </div>

            <div class="campo">
                <label>Color:</label>
                <input type="text" name="color" required placeholder="Ej. Azul Marino"
                       value="<%= editando ? productoEditar.getColor() : "" %>">
            </div>

            <div class="campo">
                <label>Descripción:</label>
                <input type="text" name="descripcion" required placeholder="Ej. Algodón 100%"
                       value="<%= editando ? productoEditar.getDescripcion() : "" %>">
            </div>

            <div class="acciones-form">
                <button type="submit" class="btn-guardar">
                    <%= editando ? "Actualizar Producto" : "Registrar Producto" %>
                </button>
                <% if (editando) { %>
                    <a href="ProductoServlet?accion=listar" class="btn-volver">Cancelar</a>
                <% } %>
            </div>
        </form>

        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Modelo</th>
                    <th>Talla</th>
                    <th>Color</th>
                    <th>Descripción</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Producto> lista = (List<Producto>) request.getAttribute("listaProductos");
                    if (lista != null && !lista.isEmpty()) {
                        for (Producto p : lista) {
                %>
                <tr>
                    <td><%= p.getIdProducto() %></td>
                    <td><%= p.getModelo() %></td>
                    <td><%= p.getTalla() %></td>
                    <td><%= p.getColor() %></td>
                    <td><%= p.getDescripcion() %></td>
                    <td>
                        <a href="ProductoServlet?accion=editar&id=<%= p.getIdProducto() %>" class="btn-editar">Editar</a>
                        <a href="ProductoServlet?accion=eliminar&id=<%= p.getIdProducto() %>" class="btn-eliminar"
                           onclick="return confirm('¿Seguro que deseas eliminar este producto?');">Eliminar</a>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="6" style="text-align:center;">No hay productos registrados todavía.</td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>