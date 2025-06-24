/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.conexion;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseConexion {

    private static FirebaseDatabase database;

    public static void inicializarFirebase() {
        try {
            FileInputStream serviceAccount = new FileInputStream("C:\\Users\\pipe_\\Downloads\\testjava-4819a-firebase-adminsdk-fbsvc-3dba2cf929.json"); // ¡CAMBIA ESTO!

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://testjava-4819a-default-rtdb.firebaseio.com/") // ¡CAMBIA ESTO!
                    .build();

            FirebaseApp.initializeApp(options);
            database = FirebaseDatabase.getInstance();
            System.out.println("Firebase inicializado correctamente.");

        } catch (IOException e) {
            System.err.println("Error al inicializar Firebase: " + e.getMessage());
        }
    }

    public static FirebaseDatabase getDatabase() {
        if (database == null) {
            inicializarFirebase(); // Asegura que se inicialice si aún no lo ha hecho
        }
        return database;
    } 
}
