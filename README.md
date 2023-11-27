# Libreriav2
Autor: Luis González González
https://github.com/LuisGonzalez2002/Libreriav2.git

Contexto del problema: En una biblioteca pública grande, existen miles de libros y cientos de lectores que buscan pedir prestados, devolver y renovar estos libros. Además, los bibliotecarios deben ser capaces de agregar nuevos libros al sistema, eliminar libros obsoletos o dañados, y realizar un seguimiento de los préstamos de libros. Para manejar estas tareas de forma eficiente y segura, necesitamos desarrollar un Sistema de Gestión de Bibliotecas (LMS por sus siglas en inglés) que use Hibernate y JPA para interactuar con una base de datos SQL y que pueda manejar solicitudes concurrentes de manera segura.

Requisitos técnicos:

Diseñar e implementar un modelo de datos para la biblioteca. Esto debe incluir clases para libros, lectores, préstamos, y cualquier otra entidad que considere necesaria.
Utilice Hibernate y JPA para mapear sus clases de dominio a las tablas de la base de datos.
Proporcione una API que permita a los clientes (bibliotecarios y lectores) realizar las operaciones básicas de la biblioteca, como buscar libros, pedir prestados libros, devolver libros, renovar préstamos, agregar nuevos libros y eliminar libros obsoletos.(https://www.nigmacode.com/java/crear-api-rest-con-spring/)
Implemente el control de concurrencia para evitar condiciones de carrera, por ejemplo, dos lectores que intentan pedir prestado el mismo libro al mismo tiempo.
Implemente auditoría y control de versiones para realizar un seguimiento de quién hace qué y cuándo en el sistema.
Utilice una caché para mejorar el rendimiento de las operaciones comunes, como buscar libros.
Utilice pruebas unitarias e integración para verificar el correcto funcionamiento de su aplicación.
Entregables:

Código fuente de la aplicación.
Diagrama de la base de datos y las clases de dominio.
Un informe que describa el diseño de su aplicación, cómo maneja la concurrencia y cómo interactúa con la base de datos.(https://es.overleaf.com/)
Pruebas unitarias e integración, junto con un informe de los resultados de las pruebas.
Documentación del usuario sobre cómo utilizar la API de la aplicación.(https://docs.github.com/es/get-started/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax)
Una reflexión sobre los desafíos que encontró durante el desarrollo de la aplicación y cómo los superó.
Criterios de evaluación:

Correcta implementación de la concurrencia y el manejo de datos con Hibernate y JPA.
Complejidad y robustez de las pruebas realizadas.
Claridad y calidad del código, del diagrama de la base de datos y de la documentación.
Reflexión crítica sobre el proceso de desarrollo.
Funcionalidad y facilidad de uso de la aplicación.
