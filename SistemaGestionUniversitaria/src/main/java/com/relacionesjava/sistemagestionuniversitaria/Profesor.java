package com.relacionesjava.sistemagestionuniversitaria;

import java.util.ArrayList;
import java.util.List;

public class Profesor {
    private String nombre;
    private String especialidad;
    private List<Curso> cursosImpartidos; // Asociación bidireccional

    public Profesor(String nombre, String especialidad) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.cursosImpartidos = new ArrayList<>();
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public List<Curso> getCursosImpartidos() {
        return cursosImpartidos;
    }

    public void setCursosImpartidos(List<Curso> cursosImpartidos) {
        this.cursosImpartidos = cursosImpartidos;
    }

    // Métodos para asignar/desasignar cursos
    public void asignarCurso(Curso curso) {
        if (!this.cursosImpartidos.contains(curso)) {
            this.cursosImpartidos.add(curso);
            curso.setProfesor(this); // Mantiene la bidireccionalidad
            System.out.println("Profesor " + this.nombre + " asignado al curso " + curso.getNombre());
        }
    }

    public void desasignarCurso(Curso curso) {
        if (this.cursosImpartidos.remove(curso)) {
            if (curso.getProfesor() == this) {
                curso.setProfesor(null); // Mantiene la bidireccionalidad
            }
            System.out.println("Profesor " + this.nombre + " desasignado del curso " + curso.getNombre());
        }
    }
}