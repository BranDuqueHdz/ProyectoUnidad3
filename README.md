# ProyectoUnidad3
## Biblioteca Digital - Java Swing + MySQL

Esta es una aplicación de escritorio desarrollada en Java con Swing que simula el funcionamiento de una biblioteca digital. El sistema permite gestionar libros, usuarios y préstamos de forma concurrente, controlando la disponibilidad de ejemplares y aplicando penalizaciones en caso de retraso.

------------------------------------------------------------------

## Características principales

- Gestión de usuarios: registro y administración de los lectores.
  
- Gestión de libros: agregar, listar y actualizar stock de libros.
  
- Préstamos de libros:
      - Crear un préstamo seleccionando usuario y libro.
      -Marcar préstamos como devueltos.
      - Control de stock en tiempo real (evita que dos usuarios tomen el mismo libro al mismo tiempo).

- Penalizaciones: cálculo automático de penalización en caso de devoluciones con atraso.
  
- Visualización de datos:
      - Lista de préstamos activos y devueltos.
      - Historial de penalizaciones.

- Concurrencia controlada mediante sincronización en los métodos de actualización de stock.

------------------------------------------------------------------

## Tecnologías utilizadas

- Java 17+

- Swing (para la interfaz gráfica de usuario)

- MySQL (base de datos relacional para usuarios, libros y préstamos)

- JDBC (conexión entre Java y la base de datos)

- POO (Programación Orientada a Objetos)

- Manejo de concurrencia con synchronized
  
------------------------------------------------------------------

## Estructura del proyecto
src/
- Main.java             # Clase principal, ejecuta la aplicación
- db.java               # Clase de conexión a la base de datos
- models/
  - Libro.java      # Modelo de datos: representa un libro
  - Usuario.java    # Modelo de datos: representa un usuario
  - Prestamo.java   # Modelo de datos: representa un préstamo
- dao/
  - LibrosDAO.java  # Acceso a datos de libros, control de stock
  - UsuarioDAO.java # Acceso a datos de usuarios
  - PrestamosDAO.java # Manejo de préstamos y penalizaciones
- ui/
  - PanelCatalogo.java    # Catálogo de libros
  - PanelUsuario.java     # Área de usuario (préstamos y devoluciones)
  - PanelAdmin.java       # Panel de administración (control general)

------------------------------------------------------------------

# Interfaz de usuario

La aplicación está dividida en tres secciones principales:

- Catálogo de Libros: muestra todos los libros disponibles y su stock.

- Área de Usuario: permite a los usuarios solicitar y devolver libros.

- Panel de Administración: muestra todos los préstamos activos, devoluciones, historial de penalizaciones y estadísticas generales.

------------------------------------------------------------------

# ¿Cómo ejecutar?

- Asegúrate de tener instalado Java JDK 17 o superior y MySQL.

- Clona este repositorio o descarga el código fuente.

- Crea la base de datos en MySQL con las tablas usuario, libro y prestamo.

- Configura tu conexión en la clase db.java con tu usuario, contraseña y nombre de base de datos.

- Compila y ejecuta el archivo Main.java:
  - javac Main.java
  - java Main
  
------------------------------------------------------------------

# Futuras mejoras

- Implementar autenticación de usuarios con roles (admin / lector).

- Reportes exportables (PDF o Excel) sobre préstamos y penalizaciones.

- Notificaciones de devolución próxima mediante correo electrónico.
