# Nombre : Jeremi González Perera.
# Curso 1º DAM.
# Asignatura : Programación.
# Fecha : 09/03/2026.

# Gestor de Notas con Usuarios (Java Swing)

## Descripción
Aplicación de escritorio desarrollada en Java que permite gestionar notas de forma visual y organizada. El proyecto está construido siguiendo el patrón de arquitectura **MVC (Modelo-Vista-Controlador)** y ofrece un entorno multiusuario seguro con persistencia de datos en ficheros locales.

### Características Principales:
* **Autenticación Segura:** Sistema de registro e inicio de sesión. Las contraseñas no se guardan en texto plano, sino que utilizan encriptación **SHA-256**.
* **Persistencia de Datos:** Cada usuario tiene su propio espacio. Los datos se guardan de forma organizada en una carpeta `data/` generada automáticamente.
* **CRUD Completo:** Permite crear, leer, editar y eliminar notas fácilmente.
* **Auto-guardado:** Las notas se guardan automáticamente tras cualquier modificación para evitar pérdida de datos.
* **Buscador en Tiempo Real:** Filtrado interactivo de notas por título mientras el usuario escribe.
* **Exportación:** Opción para exportar todas las notas de un usuario a un único archivo `.txt` en la ruta deseada.
* **Interfaz Moderna:** Diseño basado en Java Swing utilizando el Look & Feel *Nimbus* y una UX cuidada (botones que se activan/desactivan según el contexto, cursores interactivos y alertas visuales).

## Cómo ejecutar el proyecto

### Prerrequisitos
* Tener instalado **Java Development Kit (JDK) 8** o superior.
* Un entorno de desarrollo (IDE) como IntelliJ IDEA, Eclipse, o VS Code.

## Estructura del proyecto

📁 GestorDeNotas/
├── 📁 data/                  
│   ├── 📁 notas/             
│   └── 📁 usuarios/          
│
└── 📁 src/
    ├── 📁 App/
    │   └── 📄 Main.java      
    │
    ├── 📁 Controller/        
    │   ├── 📄 LoginController.java
    │   └── 📄 MainController.java
    │
    ├── 📁 Model/             
    │   ├── 📄 Nota.java
    │   └── 📄 Usuario.java
    │
    ├── 📁 Service/           
    │   ├── 📄 AuthService.java
    │   └── 📄 NotaService.java
    │
    ├── 📁 Utils/             
    │   └── 📄 SecurityUtils.java 
    │
    └── 📁 View/             
        ├── 📄 LoginView.java
        └── 📄 MainView.java