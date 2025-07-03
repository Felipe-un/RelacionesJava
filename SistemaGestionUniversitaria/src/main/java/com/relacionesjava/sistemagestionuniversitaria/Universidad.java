package com.relacionesjava.sistemagestionuniversitaria;

import java.util.ArrayList;
import java.util.List;

public class Universidad {
    private String nombre;
    private List<Departamento> departamentos; // Composición fuerte 

    public Universidad(String nombre) {
        this.nombre = nombre;
        this.departamentos = new ArrayList<>();
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    // Métodos para gestionar departamentos
    public void agregarDepartamento(String nombre) {
        Departamento departamento = new Departamento(nombre);
        this.departamentos.add(departamento);
        System.out.println("Departamento " + nombre + " agregado a la universidad " + this.nombre);
    }
    
    // Demuestra composición: al eliminar la Universidad se eliminan sus Departamentos
    public void eliminarDepartamento(String nombre) {
        departamentos.removeIf(d -> d.getNombre().equals(nombre));
        System.out.println("Departamento " + nombre + " eliminado de la universidad " + this.nombre);

    }

    public List<Profesor> obtenerTodosProfesores() { 
        List<Profesor> todosProfesores = new ArrayList<>();
        for (Departamento d : departamentos) {
            todosProfesores.addAll(d.getProfesores());
        }
        return todosProfesores;
    }
}