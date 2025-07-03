package com.relacionesjava.sistemagestionuniversitaria;

import java.util.ArrayList;
import java.util.List;

public class Estudiante {
    private String codigo;
    private String nombre;
    private List<Curso> cursosInscritos; // Asociación 

    public Estudiante(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cursosInscritos = new ArrayList<>();
    }

    // Getters, setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Curso> getCursosInscritos() {
        return cursosInscritos;
    }

    public void setCursosInscritos(List<Curso> cursosInscritos) {
        this.cursosInscritos = cursosInscritos;
    }

    // Métodos para la inscripción de cursos
    public void inscribirCurso(Curso curso) {
        if (!this.cursosInscritos.contains(curso)) {
            this.cursosInscritos.add(curso);
            curso.inscribirEstudiante(this); // Mantiene la bidireccionalidad
        }
    }

    public void desinscribirCurso(Curso curso) {
        if (this.cursosInscritos.remove(curso)) {
            curso.desinscribirEstudiante(this.codigo); // Mantiene la bidireccionalidad
        }
    }
}