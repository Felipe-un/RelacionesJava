package com.relacionesjava.sistemagestionuniversitaria;

import java.util.ArrayList;
import java.util.List;

public class Curso {
    private String codigo;
    private String nombre;
    private Profesor profesor; // Asociación
    private List<Estudiante> estudiantes; // Asociación 

    public Curso(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.estudiantes = new ArrayList<>();
    }

    // Getters y Setters
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

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    // Método que demuestra dependencia
    public void generarReporte(GeneradorReporte generador) { // 
        // Dependencia: Curso depende de GeneradorReporte
        System.out.println("Generando reporte para el curso: " + this.nombre);
        generador.generarReporteCurso(this);
    }

    // Métodos para la inscripción de estudiantes 
    public void inscribirEstudiante(Estudiante estudiante) { // 
        if (!this.estudiantes.contains(estudiante)) {
            this.estudiantes.add(estudiante);
            estudiante.getCursosInscritos().add(this); // Mantiene la bidireccionalidad
            System.out.println("Estudiante " + estudiante.getNombre() + " inscrito en el curso " + this.nombre);
        }
    }

    public void desinscribirEstudiante(String codigoEstudiante) {
        Estudiante estudianteDesinscrito = null;
        for (Estudiante e : this.estudiantes) {
            if (e.getCodigo().equals(codigoEstudiante)) {
                estudianteDesinscrito = e;
                break;
            }
        }
        if (estudianteDesinscrito != null) {
            this.estudiantes.remove(estudianteDesinscrito);
            estudianteDesinscrito.getCursosInscritos().remove(this); // Mantiene la bidireccionalidad
            System.out.println("Estudiante " + estudianteDesinscrito.getNombre() + " desinscrito del curso " + this.nombre);
        }
    }

    // Método para asignar profesor (ya en Profesor, pero agregado aquí para completar) 
    public void asignarProfesor(Profesor profesor) {
        if (this.profesor != null) {
            this.profesor.desasignarCurso(this); // Eliminar del profesor anterior
        }
        this.setProfesor(profesor);
        if (profesor != null) {
            profesor.asignarCurso(this); // Establecer bidireccionalidad
        }
        System.out.println("Profesor " + (profesor != null ? profesor.getNombre() : "N/A") + " asignado al curso " + this.nombre);
    }
}
