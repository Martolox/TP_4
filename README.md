# TP 4 - Layers

- Dependencias:
    - JUnit5
    - OpenCSV
    - Jakarta Mail
    - Derby DB
- Visualización de dependencias:
    - Uso de Jdeps: `jdeps -verbose:package -p database -p model -p ui`
- Mock emisor de mail
- Uso de MailTrap
- Lectura de archivo .txt
- Lectura de archivo .csv

## 1. Agregar Participante
- Refactorización en capas del código dado.
    - Uso de UI
    - Uso de BD Derby
    - Clase auxiliar SetupBD
- El usuario puede registrar un nombre, número de teléfono y elegir país de orígen.

## 2. Mail de Felicitaciones
Se escribe un programa que cargua un conjunto de empleados de un archivo de texto y envía por email un saludo de feliz cumpleaños a quienes cumplan años el día corriente.
- Uso de MailTrap
- Tests unitarios con covertura superior al 60%.
- Se procesa la carga de empleados de un archivo TXT.


## 3. Programa incompleto de Concursos e Inscriptos
Un programa con métodos incompletos con una única claseque mezcla UI, lógica de negocios y acceso a datos.
- El archivo inscriptos.txt tiene formato:
    - apellido, nombre, teléfono, email, idconcurso
    - Young, Angus, 4444-898789, angus@acdc.com, 1
    - Johnson, Brian, 7789-658987, brian@acdc.com, 2
- El archivo concursos.txt tiene formato:
    - idconcurso, nombre, fechaInicioInscripcion, fechaFinInscripcion
    - 1, concurso x, 2020/06/01, 2020/07/01 
