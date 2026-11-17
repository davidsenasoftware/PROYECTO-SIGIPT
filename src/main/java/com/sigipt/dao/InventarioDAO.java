package com.sigipt.dao;

import com.sigipt.config.Conexion;
import com.sigipt.modelo.Inventario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventarioDAO {

    // 1. Listar todos los registros de inventario (READ)
    public List<Inventario> listar() {
        List<Inventario> lista = new ArrayList<>();
        String sql = "SELECT * FROM inventario";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Inventario inv = new Inventario();
                inv.setIdInventario(rs.getInt("id_inventario"));
                inv.setTipo(rs.getString("tipo"));
                inv.setCantidadDisponible(rs.getInt("cantidad_disponible"));
                inv.setUbicacion(rs.getString("ubicacion"));
                inv.setStockMinimo(rs.getInt("stock_minimo"));
                inv.setStockMaximo(rs.getInt("stock_maximo"));
                inv.setIdProducto(rs.getInt("id_producto"));
                lista.add(inv);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar inventario: " + e.getMessage());
        }
        return lista;
    }

    // 2. Insertar un nuevo registro de inventario (CREATE) - Alias 'agregar'
    public boolean agregar(Inventario inv) {
        String sql = "INSERT INTO inventario (tipo, cantidad_disponible, ubicacion, stock_minimo, stock_maximo, id_producto) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, inv.getTipo());
            ps.setInt(2, inv.getCantidadDisponible());
            ps.setString(3, inv.getUbicacion());
            ps.setInt(4, inv.getStockMinimo());
            ps.setInt(5, inv.getStockMaximo());
            ps.setInt(6, inv.getIdProducto());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al agregar inventario: " + e.getMessage());
            return false;
        }
    }

    // Método de compatibilidad para el menú de consola
    public boolean registrar(Inventario inv) {
        return agregar(inv);
    }

    // 3. Buscar un registro de inventario por su ID
    public Inventario obtenerPorId(int idInventario) {
        Inventario inv = null;
        String sql = "SELECT * FROM inventario WHERE id_inventario = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idInventario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    inv = new Inventario(
                        rs.getInt("id_inventario"),
                        rs.getString("tipo"),
                        rs.getInt("cantidad_disponible"),
                        rs.getString("ubicacion"),
                        rs.getInt("stock_minimo"),
                        rs.getInt("stock_maximo"),
                        rs.getInt("id_producto")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar inventario: " + e.getMessage());
        }
        return inv;
    }

    // 4. Actualizar un registro de inventario existente (UPDATE)
    public boolean actualizar(Inventario inv) {
        String sql = "UPDATE inventario SET tipo = ?, cantidad_disponible = ?, ubicacion = ?, stock_minimo = ?, stock_maximo = ?, id_producto = ? WHERE id_inventario = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, inv.getTipo());
            ps.setInt(2, inv.getCantidadDisponible());
            ps.setString(3, inv.getUbicacion());
            ps.setInt(4, inv.getStockMinimo());
            ps.setInt(5, inv.getStockMaximo());
            ps.setInt(6, inv.getIdProducto());
            ps.setInt(7, inv.getIdInventario());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar inventario: " + e.getMessage());
            return false;
        }
    }

    // 5. Eliminar un registro de inventario por ID (DELETE)
    public boolean eliminar(int idInventario) {
        String sql = "DELETE FROM inventario WHERE id_inventario = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idInventario);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar inventario: " + e.getMessage());
            return false;
        }
    }
}