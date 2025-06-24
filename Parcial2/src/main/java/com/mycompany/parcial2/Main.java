package com.mycompany.parcial2; // O el nombre de su paquete principal

import com.mycompany.conexion.FirebaseConexion;
import com.mycompany.modelo.Producto;
import com.mycompany.servicio.ProductoServicio;
import my.company.vista.ProductoGUI;

import java.util.List;
import java.util.UUID; // Para generar IDs únicos si no se usa Firebase push()


public class Main {

    public static void main(String[] args) {
        // Asegúrate de que Firebase se inicialice antes de crear la GUI
        // Ya lo tenemos en el constructor de ProductoGUI, pero es buena práctica
        // asegurarlo en el punto de entrada principal si solo se inicializa una vez.
        // FirebaseConexion.inicializarFirebase();

        java.awt.EventQueue.invokeLater(() -> {
            new ProductoGUI().setVisible(true);
        });
    }
}