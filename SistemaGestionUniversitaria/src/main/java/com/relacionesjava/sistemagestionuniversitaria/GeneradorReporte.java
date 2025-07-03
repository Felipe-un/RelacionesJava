package com.relacionesjava.sistemagestionuniversitaria;

public class GeneradorReporte {

    public void generarReporteCurso(Curso curso) {
        System.out.println("--- Reporte del Curso: " + curso.getNombre() + " (" + curso.getCodigo() + ") ---");
        System.out.println("Profesor Asignado: " + (curso.getProfesor() != null ? curso.getProfesor().getNombre() : "N/A"));
        System.out.println("Estudiantes Inscritos (" + curso.getEstudiantes().size() + "):");
        if (curso.getEstudiantes().isEmpty()) {
            System.out.println("  No hay estudiantes inscritos.");
        } else {
            for (Estudiante estudiante : curso.getEstudiantes()) {
                System.out.println("  - " + estudiante.getNombre() + " (" + estudiante.getCodigo() + ")");
            }
        }
        System.out.println("----------------------------------------------");
    }

    public void generarReporteDepartamento(Departamento departamento) {
        System.out.println("\n--- Reporte del Departamento: " + departamento.getNombre() + " ---");
        System.out.println("Profesores (" + departamento.getProfesores().size() + "):");
        if (departamento.getProfesores().isEmpty()) {
            System.out.println("  No hay profesores en este departamento.");
        } else {
            for (Profesor profesor : departamento.getProfesores()) {
                System.out.println("  - " + profesor.getNombre() + " (" + profesor.getEspecialidad() + ")");
            }
        }
        System.out.println("Cursos Ofrecidos (" + departamento.getCursos().size() + "):");
        if (departamento.getCursos().isEmpty()) {
            System.out.println("  No hay cursos ofrecidos por este departamento.");
        } else {
            for (Curso curso : departamento.getCursos()) {
                System.out.println("  - " + curso.getNombre() + " (" + curso.getCodigo() + ")");
            }
        }
        System.out.println("-------------------------------------------------");
    }

    public void generarReporteEstudiante(Estudiante estudiante) {
        System.out.println("\n--- Reporte del Estudiante: " + estudiante.getNombre() + " (" + estudiante.getCodigo() + ") ---");
        System.out.println("Cursos Inscritos (" + estudiante.getCursosInscritos().size() + "):");
        if (estudiante.getCursosInscritos().isEmpty()) {
            System.out.println("  No está inscrito en ningún curso.");
        } else {
            for (Curso curso : estudiante.getCursosInscritos()) {
                System.out.println("  - " + curso.getNombre() + " (" + curso.getCodigo() + ")");
            }
        }
        System.out.println("--------------------------------------------------");
    }
}