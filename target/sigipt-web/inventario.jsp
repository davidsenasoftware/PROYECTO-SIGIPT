<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sigipt.modelo.Inventario" %>
<%@ page import="com.sigipt.modelo.Producto" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Control de Inventario - SIGIPT</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f6f9;
            margin: 0;
            padding: 20px;
            color: #1e293b;
        }
        .contenedor {
            max-width: 1100px;
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
            grid-template-columns: repeat(auto-fit, minmax(180px, 1fr));
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
        input[type="text"], input[type="number"], select {
            padding: 8px 10px;
            border: 1px solid #cbd5e1;
            border-radius: 5px;
            font-size: 14px;
            background-color: white;
        }
        .acciones-form {
            grid-column: 1 / -1;
            display: flex;
            gap: 10px;
        }
        button.btn-guardar {
            background-color: #0f766e;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            font-weight: bold;
            cursor: pointer;
        }
        button.btn-guardar:hover {
            background-color: #115e59;
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
        Inventario inventarioEditar = (Inventario) request.getAttribute("inventarioEditar");
        boolean editando = (inventarioEditar != null);
        List<Producto> listaProductos = (List<Producto>) request.getAttribute("listaProductos");
    %>

    <div class="contenedor">
        <div class="encabezado">
            <h2>Módulo de Inventario - SIGIPT</h2>
            <a href="index.jsp" class="btn-volver">← Volver al Inicio</a>
        </div>

        <form action="InventarioServlet" method="POST" class="formulario-grid">
            <input type="hidden" name="idInventario" value="<%= editando ? inventarioEditar.getIdInventario() : "0" %>">

            <div class="campo">
                <label>Producto Asociado:</label>
                <select name="idProducto" required>
                    <option value="">-- Seleccione un producto --</option>
                    <%
                        if (listaProductos != null) {
                            for (Producto p : listaProductos) {
                                boolean seleccionado = editando && (p.getIdProducto() == inventarioEditar.getIdProducto());
                    %>
                    <option value="<%= p.getIdProducto() %>" <%= seleccionado ? "selected" : "" %>>
                        <%= p.getIdProducto() %> - <%= p.getModelo() %> (<%= p.getTalla() %> / <%= p.getColor() %>)
                    </option>
                    <%
                            }
                        }
                    %>
                </select>
            </div>

            <div class="campo">
                <label>Tipo de Inventario:</label>
                <input type="text" name="tipo" required placeholder="Ej. Producto Terminado"
                       value="<%= editando ? inventarioEditar.getTipo() : "" %>">
            </div>

            <div class="campo">
                <label>Cantidad Disponible:</label>
                <input type="number" name="cantidadDisponible" min="0" required
                       value="<%= editando ? inventarioEditar.getCantidadDisponible() : "" %>">
            </div>

            <div class="campo">
                <label>Ubicación (Bodega):</label>
                <input type="text" name="ubicacion" required placeholder="Ej. Estante A-1"
                       value="<%= editando ? inventarioEditar.getUbicacion() : "" %>">
            </div>

            <div class="campo">
                <label>Stock Mínimo:</label>
                <input type="number" name="stockMinimo" min="0" required
                       value="<%= editando ? inventarioEditar.getStockMinimo() : "" %>">
            </div>

            <div class="campo">
                <label>Stock Máximo:</label>
                <input type="number" name="stockMaximo" min="1" required
                       value="<%= editando ? inventarioEditar.getStockMaximo() : "" %>">
            </div>

            <div class="acciones-form">
                <button type="submit" class="btn-guardar">
                    <%= editando ? "Actualizar Inventario" : "Registrar Inventario" %>
                </button>
                <% if (editando) { %>
                    <a href="InventarioServlet?accion=listar" class="btn-volver">Cancelar</a>
                <% } %>
            </div>
        </form>

        <table>
            <thead>
                <tr>
                    <th>ID Inv.</th>
                    <th>ID Prod.</th>
                    <th>Tipo</th>
                    <th>Cant. Disponible</th>
                    <th>Ubicación</th>
                    <th>Stock Mín.</th>
                    <th>Stock Máx.</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Inventario> lista = (List<Inventario>) request.getAttribute("listaInventario");
                    if (lista != null && !lista.isEmpty()) {
                        for (Inventario inv : lista) {
                %>
                <tr>
                    <td><%= inv.getIdInventario() %></td>
                    <td><%= inv.getIdProducto() %></td>
                    <td><%= inv.getTipo() %></td>
                    <td><%= inv.getCantidadDisponible() %></td>
                    <td><%= inv.getUbicacion() %></td>
                    <td><%= inv.getStockMinimo() %></td>
                    <td><%= inv.getStockMaximo() %></td>
                    <td>
                        <a href="InventarioServlet?accion=editar&id=<%= inv.getIdInventario() %>" class="btn-editar">Editar</a>
                        <a href="InventarioServlet?accion=eliminar&id=<%= inv.getIdInventario() %>" class="btn-eliminar"
                           onclick="return confirm('¿Seguro que deseas eliminar este registro de inventario?');">Eliminar</a>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="8" style="text-align:center;">No hay registros en el inventario todavía.</td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>