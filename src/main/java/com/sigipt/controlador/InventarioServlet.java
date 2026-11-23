package com.sigipt.controlador;

import com.sigipt.dao.InventarioDAO;
import com.sigipt.dao.ProductoDAO;
import com.sigipt.modelo.Inventario;
import com.sigipt.modelo.Producto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "InventarioServlet", urlPatterns = {"/InventarioServlet"})
public class InventarioServlet extends HttpServlet {

    private final InventarioDAO inventarioDAO = new InventarioDAO();
    private final ProductoDAO productoDAO = new ProductoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion == null) {
            accion = "listar";
        }

        switch (accion) {
            case "editar":
                int idEditar = Integer.parseInt(request.getParameter("id"));
                Inventario inventarioEditar = inventarioDAO.obtenerPorId(idEditar);
                request.setAttribute("inventarioEditar", inventarioEditar);
                listarInventario(request, response);
                break;

            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                inventarioDAO.eliminar(idEliminar);
                response.sendRedirect("InventarioServlet?accion=listar");
                break;

            case "listar":
            default:
                listarInventario(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String idStr = request.getParameter("idInventario");
        String tipo = request.getParameter("tipo");
        int cantidadDisponible = Integer.parseInt(request.getParameter("cantidadDisponible"));
        String ubicacion = request.getParameter("ubicacion");
        int stockMinimo = Integer.parseInt(request.getParameter("stockMinimo"));
        int stockMaximo = Integer.parseInt(request.getParameter("stockMaximo"));
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));

        if (idStr == null || idStr.isEmpty() || idStr.equals("0")) {
            // Registrar nuevo inventario
            Inventario nuevo = new Inventario(tipo, cantidadDisponible, ubicacion, stockMinimo, stockMaximo, idProducto);
            inventarioDAO.agregar(nuevo);
        } else {
            // Actualizar inventario existente
            int idInventario = Integer.parseInt(idStr);
            Inventario actualizado = new Inventario(idInventario, tipo, cantidadDisponible, ubicacion, stockMinimo, stockMaximo, idProducto);
            inventarioDAO.actualizar(actualizado);
        }

        response.sendRedirect("InventarioServlet?accion=listar");
    }

    private void listarInventario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Inventario> listaInventario = inventarioDAO.listar();
        List<Producto> listaProductos = productoDAO.listar();
        request.setAttribute("listaInventario", listaInventario);
        request.setAttribute("listaProductos", listaProductos);
        request.getRequestDispatcher("inventario.jsp").forward(request, response);
    }
}
