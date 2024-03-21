# Proyecto de Adopción de Perros (🚧 Versión Alfa)

Este proyecto es una plataforma web para facilitar la adopción de perros, donde los usuarios pueden publicar perros disponibles para adopción y contactar con otros usuarios interesados.

## Tecnologías Utilizadas

- Spring Boot
- Spring Security
- Thymeleaf
- Bootstrap
- JavaScript
- PostgreSQL
- JPA

## Estructura del Proyecto

El proyecto sigue una arquitectura MVC (Modelo-Vista-Controlador) con Spring Boot. La estructura de paquetes es la siguiente:

- `src/main/java`: Contiene las clases Java del proyecto, incluyendo los controladores, servicios y entidades.
- `src/main/resources`: Contiene los archivos de configuración y las plantillas HTML.
- `src/main/resources/static`: Contiene los recursos estáticos como CSS, JavaScript e imágenes.

## Funcionalidades Principales

- Registro y autenticación de usuarios.
  - ![login.gif](https://github.com/justin-campos/Adopt-Pet/blob/master/src/main/resources/static/css/login.gif?raw=true)
- Publicación de perros disponibles para adopción.
- Visualización de publicaciones de otros usuarios.
  - ![post.jpeg](https://github.com/justin-campos/Adopt-Pet/blob/master/src/main/resources/static/css/post.jpeg?raw=true)
- Contacto entre usuarios a través de WhatsApp.
- Gestión de información personal y configuración de privacidad.
  - ![post.gif](https://github.com/justin-campos/Adopt-Pet/blob/master/src/main/resources/static/css/post.gif?raw=true)
- Administración de registros de usuarios por parte del usuario administrador.
  - ![screen1.png](https://github.com/justin-campos/Adopt-Pet/blob/master/src/main/resources/static/css/screen1.png?raw=true)

## Instalación y Ejecución

Para ejecutar el proyecto localmente, sigue estos pasos:

1. Clona el repositorio: `git clone https://github.com/justin-campos/Adopt-Pet.git`
2. Importa el proyecto en tu IDE y configura la base de datos PostgreSQL.
3. Ejecuta la aplicación Spring Boot.

