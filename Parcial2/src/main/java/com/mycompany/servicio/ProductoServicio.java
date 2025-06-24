// Archivo: com/miempresa/servicio/ProductoServicio.java (modificado)
package com.mycompany.servicio;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.mycompany.conexion.FirebaseConexion;
import com.mycompany.modelo.Producto;
import com.mycompany.interfaces.CrudOperaciones; // ¡Importamos nuestra interfaz!

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

// Ahora ProductoServicio implementa CrudOperaciones para Productos
public class ProductoServicio implements CrudOperaciones<Producto> {

    private DatabaseReference productosRef;
    private Gson gson;

    public ProductoServicio() {
        FirebaseDatabase database = FirebaseConexion.getDatabase();
        productosRef = database.getReference("productos");
        gson = new Gson();
    }

    // --- Implementación de los métodos de la interfaz ---

    @Override // Buena práctica para indicar que se sobrescribe un método de una interfaz/clase padre
    public void crear(Producto producto) {
        // Tu código existente de crearProducto
        if (producto.getId() == null || producto.getId().isEmpty()) {
            producto.setId(productosRef.push().getKey());
        }
        Map<String, Object> productoMap = new HashMap<>();
        productoMap.put("nombre", producto.getNombre());
        productoMap.put("precio", producto.getPrecio());
        productoMap.put("stock", producto.getStock());

        productosRef.child(producto.getId()).setValue(productoMap, (databaseError, databaseReference) -> {
            if (databaseError != null) {
                System.err.println("Error al crear producto: " + databaseError.getMessage());
            } else {
                System.out.println("Producto creado exitosamente con ID: " + producto.getId());
            }
        });
    }

    @Override
    public List<Producto> leerTodos() {
        // Tu código existente de obtenerTodosLosProductos
        final List<Producto> productos = new ArrayList<>();
        final CountDownLatch latch = new CountDownLatch(1);

        productosRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Producto producto = snapshot.getValue(Producto.class);
                        if (producto != null) {
                            producto.setId(snapshot.getKey());
                            productos.add(producto);
                        }
                    }
                }
                latch.countDown();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.err.println("Error al leer productos: " + databaseError.getMessage());
                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Operación interrumpida: " + e.getMessage());
        }
        return productos;
    }

    @Override
    public void actualizar(Producto producto) {
        // Tu código existente de actualizarProducto
        if (producto.getId() == null || producto.getId().isEmpty()) {
            System.err.println("No se puede actualizar un producto sin ID.");
            return;
        }
        Map<String, Object> productoUpdates = new HashMap<>();
        productoUpdates.put("nombre", producto.getNombre());
        productoUpdates.put("precio", producto.getPrecio());
        productoUpdates.put("stock", producto.getStock());

        productosRef.child(producto.getId()).updateChildren(productoUpdates, (databaseError, databaseReference) -> {
            if (databaseError != null) {
                System.err.println("Error al actualizar producto: " + databaseError.getMessage());
            } else {
                System.out.println("Producto actualizado exitosamente con ID: " + producto.getId());
            }
        });
    }

    @Override
    public void eliminar(String idProducto) {
        // Tu código existente de eliminarProducto
        productosRef.child(idProducto).removeValue((databaseError, databaseReference) -> {
            if (databaseError != null) {
                System.err.println("Error al eliminar producto: " + databaseError.getMessage());
            } else {
                System.out.println("Producto eliminado exitosamente con ID: " + idProducto);
            }
        });
    }
}