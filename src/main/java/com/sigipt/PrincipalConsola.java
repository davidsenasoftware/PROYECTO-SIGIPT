package com.sigipt;

import com.sigipt.dao.InventarioDAO;
import com.sigipt.dao.ProductoDAO;
import com.sigipt.modelo.Inventario;
import com.sigipt.modelo.Producto;
import java.util.List;
import java.util.Scanner;

public class PrincipalConsola {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductoDAO productoDAO = new ProductoDAO();
        InventarioDAO inventarioDAO = new InventarioDAO();
        int opcion = 0;

        do {
            System.out.println("\n==========================================================");
            System.out.println("   SIGIPT (Nuba Confort S.A.S.) - VALIDACION CRUD CONSOLA   ");
            System.out.println("==========================================================");
            System.out.println("--- MODULO DE PRODUCTOS ---");
            System.out.println("1. Listar productos");
            System.out.println("2. Registrar nuevo producto");
            System.out.println("3. Buscar producto por ID");
            System.out.println("4. Actualizar producto");
            System.out.println("5. Eliminar producto");
            System.out.println("--- MODULO DE INVENTARIO ---");
            System.out.println("6. Listar inventario (Stock y Ubicacion)");
            System.out.println("7. Registrar movimiento / stock");
            System.out.println("----------------------------------------------------------");
            System.out.println("8. Salir del sistema");
            System.out.print("Seleccione una opcion: ");
            
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer

                switch (opcion) {
                    case 1:
                        List<Producto> listaP = productoDAO.listar();
                        System.out.println("\n--- LISTA DE PRODUCTOS ---");
                        if (listaP.isEmpty()) {
                            System.out.println("No hay productos registrados.");
                        } else {
                            for (Producto p : listaP) {
                                System.out.println("ID: " + p.getIdProducto() + 
                                                   " | Modelo: " + p.getModelo() + 
                                                   " | Talla: " + p.getTalla() + 
                                                   " | Color: " + p.getColor());
                            }
                        }
                        break;

                    case 2:
                        System.out.println("\n--- REGISTRAR NUEVO PRODUCTO ---");
                        System.out.print("Modelo: ");
                        String modelo = scanner.nextLine();
                        System.out.print("Talla: ");
                        String talla = scanner.nextLine();
                        System.out.print("Color: ");
                        String color = scanner.nextLine();
                        System.out.print("Descripcion: ");
                        String desc = scanner.nextLine();

                        Producto nuevoP = new Producto(0, modelo, talla, color, desc);
                        productoDAO.agregar(nuevoP);
                        System.out.println("¡Producto registrado con exito en la base de datos!");
                        break;

                    case 3:
                        System.out.println("\n--- BUSCAR PRODUCTO POR ID ---");
                        System.out.print("Ingrese el ID del producto: ");
                        int idBuscar = scanner.nextInt();
                        scanner.nextLine();
                        Producto pBuscado = productoDAO.obtenerPorId(idBuscar);
                        if (pBuscado != null) {
                            System.out.println("Encontrado -> Modelo: " + pBuscado.getModelo() + 
                                               " | Talla: " + pBuscado.getTalla() + 
                                               " | Color: " + pBuscado.getColor() + 
                                               " | Desc: " + pBuscado.getDescripcion());
                        } else {
                            System.out.println("No se encontro ningun producto con ese ID.");
                        }
                        break;

                    case 4:
                        System.out.println("\n--- ACTUALIZAR PRODUCTO ---");
                        System.out.print("Ingrese el ID del producto a actualizar: ");
                        int idAct = scanner.nextInt();
                        scanner.nextLine();
                        Producto pExistente = productoDAO.obtenerPorId(idAct);
                        if (pExistente != null) {
                            System.out.print("Nuevo modelo (" + pExistente.getModelo() + "): ");
                            String nuevoModelo = scanner.nextLine();
                            System.out.print("Nueva talla (" + pExistente.getTalla() + "): ");
                            String nuevaTalla = scanner.nextLine();
                            System.out.print("Nuevo color (" + pExistente.getColor() + "): ");
                            String nuevoColor = scanner.nextLine();
                            System.out.print("Nueva descripcion (" + pExistente.getDescripcion() + "): ");
                            String nuevaDesc = scanner.nextLine();

                            Producto pActualizado = new Producto(idAct, nuevoModelo, nuevaTalla, nuevoColor, nuevaDesc);
                            if (productoDAO.actualizar(pActualizado)) {
                                System.out.println("¡Producto actualizado correctamente!");
                            } else {
                                System.out.println("Error al actualizar el producto.");
                            }
                        } else {
                            System.out.println("El producto con ese ID no existe.");
                        }
                        break;

                    case 5:
                        System.out.println("\n--- ELIMINAR PRODUCTO ---");
                        System.out.print("Ingrese el ID del producto a eliminar: ");
                        int idEliminar = scanner.nextInt();
                        scanner.nextLine();
                        if (productoDAO.eliminar(idEliminar)) {
                            System.out.println("¡Producto eliminado con exito!");
                        } else {
                            System.out.println("No se pudo eliminar (verifique si el ID existe).");
                        }
                        break;

                    case 6:
                        List<Inventario> listaI = inventarioDAO.listar();
                        System.out.println("\n--- ESTADO ACTUAL DEL INVENTARIO ---");
                        if (listaI.isEmpty()) {
                            System.out.println("No hay registros de inventario.");
                        } else {
                            for (Inventario i : listaI) {
                                System.out.println("ID Inv: " + i.getIdInventario() + 
                                                   " | Tipo: " + i.getTipo() + 
                                                   " | Cantidad: " + i.getCantidadDisponible() + 
                                                   " | Ubicacion: " + i.getUbicacion() + 
                                                   " | ID Prod: " + i.getIdProducto());
                            }
                        }
                        break;

                    case 7:
                        System.out.println("\n--- REGISTRAR INVENTARIO / STOCK ---");
                        System.out.print("Tipo de movimiento (Ej. Entrada Bodega): ");
                        String tipo = scanner.nextLine();
                        System.out.print("Cantidad disponible: ");
                        int cantidad = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Ubicacion (Ej. EstanteA-Fila2-Col3): ");
                        String ubicacion = scanner.nextLine();
                        System.out.print("Stock minimo: ");
                        int min = scanner.nextInt();
                        System.out.print("Stock maximo: ");
                        int max = scanner.nextInt();
                        System.out.print("ID del producto asociado: ");
                        int idProd = scanner.nextInt();
                        scanner.nextLine();

                        Inventario nuevoI = new Inventario(0, tipo, cantidad, ubicacion, min, max, idProd);
                        inventarioDAO.registrar(nuevoI);
                        System.out.println("¡Inventario registrado con exito!");
                        break;

                    case 8:
                        System.out.println("Saliendo de la consola de validacion...");
                        break;

                    default:
                        System.out.println("Opcion no valida. Seleccione entre 1 y 8.");
                }
            } else {
                System.out.println("Por favor, ingrese un numero valido.");
                scanner.next();
            }
        } while (opcion != 8);

        scanner.close();
    }
}