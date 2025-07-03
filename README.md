# - IMPRESION POR CONSOLA -
--- Caso de Uso 1: Gesti�n de Universidad (Composici�n) ---
Departamento Ingenieria de Sistemas agregado a la universidad Universidad Nacional
Departamento Ingenieria El�ctrica agregado a la universidad Universidad Nacional
Departamento Matematicas agregado a la universidad Universidad Nacional

Departamentos de Universidad Nacional:
- Ingenieria de Sistemas
- Ingenieria El�ctrica
- Matematicas

Profesor Sofia Rodriguez existe independientemente.

--- Caso de Uso 2: Gesti�n de Profesores (Agregaci�n) ---
Profesor Juanito Gomez contratado en el departamento Ingenieria de Sistemas
Profesor Ricardo Silisali contratado en el departamento Ingenieria de Sistemas
Profesor Romulo Buitrago contratado en el departamento Ingenieria El�ctrica
Profesor Sofia Rodriguez contratado en el departamento Ingenieria El�ctrica

Profesores en Ingenieria de Sistemas:
- Juanito Gomez
- Ricardo Silisali

Profesores en Ingenieria El�ctrica:
- Romulo Buitrago
- Sofia Rodriguez

--- Creaci�n de Cursos y Asignaci�n de Profesores ---
Curso Fundamentos de mec�nica ofrecido por el departamento Ingenieria de Sistemas
Curso C�lculo diferencial ofrecido por el departamento Ingenieria de Sistemas
Curso C�tedra AIEEUN ofrecido por el departamento Ingenieria El�ctrica
Profesor Juanito Gomez asignado al curso Fundamentos de mec�nica
Profesor Juanito Gomez asignado al curso Fundamentos de mec�nica
Profesor Ricardo Silisali asignado al curso C�lculo diferencial
Profesor Ricardo Silisali asignado al curso C�lculo diferencial
Profesor Romulo Buitrago asignado al curso C�tedra AIEEUN
Profesor Romulo Buitrago asignado al curso C�tedra AIEEUN
Profesor de Fundamentos de mec�nica: Juanito Gomez
Profesor de C�lculo diferencial: Ricardo Silisali
Profesor de C�tedra AIEEUN: Romulo Buitrago

--- Caso de Uso 3: Inscripci�n de Estudiantes (Asociaci�n Bidireccional) ---
Estudiante Maria Murillo inscrito en el curso Fundamentos de mec�nica
Estudiante Pedro Posada inscrito en el curso Fundamentos de mec�nica
Estudiante Pedro Posada inscrito en el curso C�lculo diferencial
Estudiante Laura Linero inscrito en el curso C�tedra AIEEUN
Estudiante Maria Murillo inscrito en el curso C�tedra AIEEUN

Estudiantes inscritos en Fundamentos de mec�nica:
- Maria Murillo
- Pedro Posada

Cursos inscritos por Maria Murillo:
- Fundamentos de mec�nica
- C�tedra AIEEUN

Cursos inscritos por Pedro Posada:
- Fundamentos de mec�nica
- C�lculo diferencial

Cursos inscritos por Laura Linero:
- Fundamentos de mec�nica
- C�lculo diferencial

--- Demostrando Desinscripci�n ---
Estudiante Maria Murillo desinscrito del curso Fundamentos de mec�nica

Estudiantes inscritos en Fundamentos de mec�nica despu�s de desinscripci�n de E01:
- Pedro Posada

Cursos inscritos por Maria Murillo despu�s de desinscripci�n:
- C�tedra AIEEUN

--- Caso de Uso 4: Generaci�n de Reportes (Dependencia) ---
Generando reporte para el curso: Fundamentos de mec�nica
--- Reporte del Curso: Fundamentos de mec�nica (CS101) ---
Profesor Asignado: Juanito Gomez
Estudiantes Inscritos (1):
  - Pedro Posada (E02)
----------------------------------------------
Generando reporte para el curso: C�tedra AIEEUN
--- Reporte del Curso: C�tedra AIEEUN (CS201) ---
Profesor Asignado: Romulo Buitrago
Estudiantes Inscritos (2):
  - Laura Linero (E03)
  - Maria Murillo (E01)
----------------------------------------------

--- Reporte del Departamento: Ingenieria de Sistemas ---
Profesores (2):
  - Juanito Gomez (Fundamentos de mec�nica)
  - Ricardo Silisali (C�lculo diferencial)
Cursos Ofrecidos (2):
  - Fundamentos de mec�nica (CS101)
  - C�lculo diferencial (CS102)
-------------------------------------------------

--- Reporte del Estudiante: Pedro Posada (E02) ---
Cursos Inscritos (2):
  - Fundamentos de mec�nica (CS101)
  - C�lculo diferencial (CS102)
--------------------------------------------------

--- Demostrando Agregaci�n (Profesor existe independientemente) ---
Profesor Juanito Gomez despedido del departamento Ingenieria de Sistemas
Profesores en Ingenieria de Sistemas despu�s de despedir a Dr. Juan Perez:
- Ricardo Silisali
Juanito Gomez sigue existiendo? S�, su nombre es Juanito Gomez
Cursos que Juanito Gomez a�n imparte: 
- Fundamentos de mec�nica

--- Demostrando Composici�n (Eliminar Universidad elimina Departamentos) ---
Departamentos de Universidad Nacional antes de eliminar: 3
Departamentos de Universidad Nacional despu�s de 'eliminar' (lista vac�a): 0
------------------------------------------------------------------------
BUILD SUCCESS
