package com.sigipt.dao;

import com.sigipt.config.Conexion;
import com.sigipt.modelo.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    // 1. Listar todos los productos (READ)
    public List<Producto> listar() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM producto";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Producto p = new Producto();
                p.setIdProducto(rs.getInt("id_producto"));
                p.setModelo(rs.getString("modelo"));
                p.setTalla(rs.getString("talla"));
                p.setColor(rs.getString("color"));
                p.setDescripcion(rs.getString("descripcion"));
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        }
        return lista;
    }

    // 2. Insertar un nuevo producto (CREATE)
    public boolean agregar(Producto p) {
        String sql = "INSERT INTO producto (modelo, talla, color, descripcion) VALUES (?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getModelo());
            ps.setString(2, p.getTalla());
            ps.setString(3, p.getColor());
            ps.setString(4, p.getDescripcion());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al agregar producto: " + e.getMessage());
            return false;
        }
    }

    // 3. Buscar un producto por su ID
    public Producto obtenerPorId(int idProducto) {
        Producto p = null;
        String sql = "SELECT * FROM producto WHERE id_producto = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idProducto);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    p = new Producto(
                        rs.getInt("id_producto"),
                        rs.getString("modelo"),
                        rs.getString("talla"),
                        rs.getString("color"),
                        rs.getString("descripcion")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar producto: " + e.getMessage());
        }
        return p;
    }

    // 4. Actualizar un producto existente (UPDATE)
    public boolean actualizar(Producto p) {
        String sql = "UPDATE producto SET modelo = ?, talla = ?, color = ?, descripcion = ? WHERE id_producto = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getModelo());
            ps.setString(2, p.getTalla());
            ps.setString(3, p.getColor());
            ps.setString(4, p.getDescripcion());
            ps.setInt(5, p.getIdProducto());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar producto: " + e.getMessage());
            return false;
        }
    }

    // 5. Eliminar un producto por ID (DELETE)
    public boolean eliminar(int idProducto) {
        String sql = "DELETE FROM producto WHERE id_producto = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idProducto);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
            return false;
        }
    }
}