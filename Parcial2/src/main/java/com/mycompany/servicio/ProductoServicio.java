package com.mycompany.servicio;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.conexion.FirebaseConexion;
import com.mycompany.modelo.Producto;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch; // Para operaciones asíncronas

public class ProductoServicio {

    private DatabaseReference productosRef;
    private Gson gson; // Para manejar JSON

    public ProductoServicio() {
        FirebaseDatabase database = FirebaseConexion.getDatabase();
        productosRef = database.getReference("productos"); // 'productos' será el nodo raíz en Firebase
        gson = new Gson();
    }

    // --- C (Create): Crear un nuevo producto ---
    public void crearProducto(Producto producto) {
        // Generar un ID único si el producto no lo tiene
        if (producto.getId() == null || producto.getId().isEmpty()) {
            producto.setId(productosRef.push().getKey());
        }
        // Convertir el objeto Producto a JSON (Map)
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

    // --- R (Read): Leer todos los productos ---
    public List<Producto> obtenerTodosLosProductos() {
        final List<Producto> productos = new ArrayList<>();
        final CountDownLatch latch = new CountDownLatch(1); // Para esperar la respuesta asíncrona

        productosRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Firebase devuelve un Map<String, Object> donde el String es el ID
                    // y Object es el objeto JSON del producto.
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        // Convertir el DataSnapshot a un objeto Producto
                        Producto producto = snapshot.getValue(Producto.class);
                        // Asignar el ID del snapshot al objeto Producto
                        if (producto != null) {
                            producto.setId(snapshot.getKey());
                            productos.add(producto);
                        }
                    }
                }
                latch.countDown(); // Indicar que la operación ha terminado
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.err.println("Error al leer productos: " + databaseError.getMessage());
                latch.countDown();
            }
        });

        try {
            latch.await(); // Esperar hasta que la operación asíncrona complete
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Operación interrumpida: " + e.getMessage());
        }
        return productos;
    }

    // --- U (Update): Actualizar un producto existente ---
    public void actualizarProducto(Producto producto) {
        if (producto.getId() == null || producto.getId().isEmpty()) {
            System.err.println("No se puede actualizar un producto sin ID.");
            return;
        }

        // Convertir el objeto Producto a JSON (Map)
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

    // --- D (Delete): Eliminar un producto ---
    public void eliminarProducto(String idProducto) {
        productosRef.child(idProducto).removeValue((databaseError, databaseReference) -> {
            if (databaseError != null) {
                System.err.println("Error al eliminar producto: " + databaseError.getMessage());
            } else {
                System.out.println("Producto eliminado exitosamente con ID: " + idProducto);
            }
        });
    }
}