package com.relacionesjava.sistemagestionuniversitaria;

import java.util.ArrayList;
import java.util.List;

public class Departamento {
    private String nombre;
    private List<Profesor> profesores; // Agregación 
    private List<Curso> cursos;

    public Departamento(String nombre) {
        this.nombre = nombre;
        this.profesores = new ArrayList<>();
        this.cursos = new ArrayList<>();
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Profesor> getProfesores() {
        return profesores;
    }

    public void setProfesores(List<Profesor> profesores) {
        this.profesores = profesores;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    // Métodos para gestionar profesores y cursos
    public void contratarProfesor(Profesor profesor) {
        this.profesores.add(profesor);
        System.out.println("Profesor " + profesor.getNombre() + " contratado en el departamento " + this.nombre);
    }

    // Demuestra agregación: al eliminar el Departamento no se eliminan sus Profesores
    public void despedirProfesor(String nombre) {
        profesores.removeIf(p -> p.getNombre().equals(nombre));
        System.out.println("Profesor " + nombre + " despedido del departamento " + this.nombre);
    }

    public void ofrecerCurso(Curso curso) {
        this.cursos.add(curso);
        System.out.println("Curso " + curso.getNombre() + " ofrecido por el departamento " + this.nombre);
    }
}