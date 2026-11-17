package com.sigipt.controlador;

import com.sigipt.dao.ProductoDAO;
import com.sigipt.modelo.Producto;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProductoServlet", urlPatterns = {"/ProductoServlet"})
public class ProductoServlet extends HttpServlet {

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
                Producto productoEditar = productoDAO.obtenerPorId(idEditar);
                request.setAttribute("productoEditar", productoEditar);
                listarProductos(request, response);
                break;

            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                productoDAO.eliminar(idEliminar);
                response.sendRedirect("ProductoServlet?accion=listar");
                break;

            case "listar":
            default:
                listarProductos(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String idStr = request.getParameter("idProducto");
        String modelo = request.getParameter("modelo");
        String talla = request.getParameter("talla");
        String color = request.getParameter("color");
        String descripcion = request.getParameter("descripcion");

        if (idStr == null || idStr.isEmpty() || idStr.equals("0")) {
            // Registrar nuevo producto
            Producto nuevo = new Producto(modelo, talla, color, descripcion);
            productoDAO.agregar(nuevo);
        } else {
            // Actualizar producto existente
            int idProducto = Integer.parseInt(idStr);
            Producto actualizado = new Producto(idProducto, modelo, talla, color, descripcion);
            productoDAO.actualizar(actualizado);
        }

        response.sendRedirect("ProductoServlet?accion=listar");
    }

    private void listarProductos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Producto> listaProductos = productoDAO.listar();
        request.setAttribute("listaProductos", listaProductos);
        request.getRequestDispatcher("productos.jsp").forward(request, response);
    }
}