package com.relacionesjava.sistemagestionuniversitaria;

public class Main {

    public static void main(String[] args) {
        //Instancia para la dependencia GeneradorReporte
        GeneradorReporte generadorReporte = new GeneradorReporte();

        // Caso de Uso 1: Gestión de Universidad (Composición)
        System.out.println("--- Caso de Uso 1: Gestión de Universidad (Composición) ---");

        Universidad uniNacional = new Universidad("Universidad Nacional");
        uniNacional.agregarDepartamento("Ingenieria de Sistemas");
        uniNacional.agregarDepartamento("Ingenieria Eléctrica");
        uniNacional.agregarDepartamento("Matematicas");

        System.out.println("\nDepartamentos de " + uniNacional.getNombre() + ":");
        uniNacional.getDepartamentos().forEach(d -> System.out.println("- " + d.getNombre()));

        // Crear un profesor antes de cualquier departamento para demostrar independencia
        Profesor profIndependiente = new Profesor("Sofia Rodriguez", "Circuitos I");
        System.out.println("\nProfesor " + profIndependiente.getNombre() + " existe independientemente.");

        // Caso de Uso 2: Gestión de Profesores (Agregación) 
        System.out.println("\n--- Caso de Uso 2: Gestión de Profesores (Agregación) ---");
        
        Departamento depSistemas = uniNacional.getDepartamentos().get(0); // Ingenieria de Sistemas
        Departamento depElectrica = uniNacional.getDepartamentos().get(1); // Ingenieria Eléctrica

        Profesor prof1 = new Profesor("Juanito Gomez", "Fundamentos de mecánica");
        Profesor prof2 = new Profesor("Ricardo Silisali", "Cálculo diferencial");
        Profesor prof3 = new Profesor("Romulo Buitrago", "Cátedra AAIEUN");

        depSistemas.contratarProfesor(prof1);
        depSistemas.contratarProfesor(prof2);
        depElectrica.contratarProfesor(prof3);
        depElectrica.contratarProfesor(profIndependiente); // Profesor puede pertenecer a multiples departamentos 

        System.out.println("\nProfesores en " + depSistemas.getNombre() + ":");
        depSistemas.getProfesores().forEach(p -> System.out.println("- " + p.getNombre()));
        System.out.println("\nProfesores en " + depElectrica.getNombre() + ":");
        depElectrica.getProfesores().forEach(p -> System.out.println("- " + p.getNombre()));

        // Crear cursos y asignar profesores
        System.out.println("\n--- Creación de Cursos y Asignación de Profesores ---");
        
        Curso cursoFM = new Curso("CS101", "Fundamentos de mecánica");
        Curso cursoCD = new Curso("CS102", "Cálculo diferencial");
        Curso cursoCatedra = new Curso("CS201", "Cátedra AIEEUN");

        depSistemas.ofrecerCurso(cursoFM);
        depSistemas.ofrecerCurso(cursoCD);
        depElectrica.ofrecerCurso(cursoCatedra);

        cursoFM.asignarProfesor(prof1);
        cursoCD.asignarProfesor(prof2);
        cursoCatedra.asignarProfesor(prof3);

        System.out.println("Profesor de " + cursoFM.getNombre() + ": " + (cursoFM.getProfesor() != null ? cursoFM.getProfesor().getNombre() : "N/A"));
        System.out.println("Profesor de " + cursoCD.getNombre() + ": " + (cursoCD.getProfesor() != null ? cursoCD.getProfesor().getNombre() : "N/A"));
        System.out.println("Profesor de " + cursoCatedra.getNombre() + ": " + (cursoCatedra.getProfesor() != null ? cursoCatedra.getProfesor().getNombre() : "N/A"));

        // Caso de Uso 3: Inscripción de Estudiantes (Asociación Bidireccional)
        System.out.println("\n--- Caso de Uso 3: Inscripción de Estudiantes (Asociación Bidireccional) ---");
        Estudiante est1 = new Estudiante("E01", "Maria Murillo");
        Estudiante est2 = new Estudiante("E02", "Pedro Posada");
        Estudiante est3 = new Estudiante("E03", "Laura Linero");

        cursoFM.inscribirEstudiante(est1);
        // Estudiante puede estar en múltiples cursos
        cursoFM.inscribirEstudiante(est2);
        cursoCD.inscribirEstudiante(est2); 
        cursoCatedra.inscribirEstudiante(est3);
        cursoCatedra.inscribirEstudiante(est1);

        System.out.println("\nEstudiantes inscritos en " + cursoFM.getNombre() + ":");
        cursoFM.getEstudiantes().forEach(e -> System.out.println("- " + e.getNombre()));
        System.out.println("\nCursos inscritos por " + est1.getNombre() + ":");
        est1.getCursosInscritos().forEach(c -> System.out.println("- " + c.getNombre()));
        System.out.println("\nCursos inscritos por " + est2.getNombre() + ":");
        est2.getCursosInscritos().forEach(c -> System.out.println("- " + c.getNombre()));
        System.out.println("\nCursos inscritos por " + est3.getNombre() + ":");
        est2.getCursosInscritos().forEach(c -> System.out.println("- " + c.getNombre()));

        // Demostrar desinscripción
        System.out.println("\n--- Demostrando Desinscripción ---");
        
        cursoFM.desinscribirEstudiante("E01");
        System.out.println("\nEstudiantes inscritos en " + cursoFM.getNombre() + " después de desinscripción de E01:");
        cursoFM.getEstudiantes().forEach(e -> System.out.println("- " + e.getNombre()));
        System.out.println("\nCursos inscritos por " + est1.getNombre() + " después de desinscripción:");
        est1.getCursosInscritos().forEach(c -> System.out.println("- " + c.getNombre()));

        // Caso de Uso 4: Generación de Reportes (Dependencia)
        System.out.println("\n--- Caso de Uso 4: Generación de Reportes (Dependencia) ---");
        
        cursoFM.generarReporte(generadorReporte);
        cursoCatedra.generarReporte(generadorReporte);

        generadorReporte.generarReporteDepartamento(depSistemas);
        generadorReporte.generarReporteEstudiante(est2);

        // Demostrar Agregación: Profesor existe independientemente del Departamento 
        System.out.println("\n--- Demostrando Agregación (Profesor existe independientemente) ---");
        depSistemas.despedirProfesor("Juanito Gomez");
        System.out.println("Profesores en " + depSistemas.getNombre() + " después de despedir a Dr. Juan Perez:");
        depSistemas.getProfesores().forEach(p -> System.out.println("- " + p.getNombre()));
        System.out.println("Juanito Gomez sigue existiendo? " + (prof1 != null ? "Sí, su nombre es " + prof1.getNombre() : "No"));
        System.out.println("Cursos que Juanito Gomez aún imparte: ");
        prof1.getCursosImpartidos().forEach(c -> System.out.println("- " + c.getNombre()));

        // Demostrar Composición: Al eliminar la universidad, se eliminan sus departamentos
        System.out.println("\n--- Demostrando Composición (Eliminar Universidad elimina Departamentos) ---");
        System.out.println("Departamentos de " + uniNacional.getNombre() + " antes de eliminar: " + uniNacional.getDepartamentos().size());
        
        // Simulando la eliminación al limpiar la lista de departamentos de la universidad.
        uniNacional.getDepartamentos().clear();
        System.out.println("Departamentos de " + uniNacional.getNombre() + " después de 'eliminar' (lista vacía): " + uniNacional.getDepartamentos().size());
        // Si elimináramos universidadNacional, sus departamentos contenidos también serían elegibles para el GC.

    }
}
