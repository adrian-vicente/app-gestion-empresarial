# App Gestión Empresarial - Autónomo o Pyme

Aplicación web desarrollada con Java 21 y Angular 21.

Te permite tener gestionada y centralizada toda la facturación de tu empresa.

---

### Dependencias 

* `jjwt-api`
* `jjwt-impl`
* `jjwt-jackson`
* `spring-boot-starter-data-jpa`
* `spring-boot-starter-security`
* `spring-boot-starter-validation`
* `mysql-connector-j`

---

## 📌 Características principales

### 🔐 Autenticación

El sistema dispone de un módulo de autenticación basado en JWT que permite el uso de tokens para iniciar sesión además de tener endpoints protegidos.

---

### 👥 Gestión de usuarios

La aplicación permite consultar información de usuarios y aplicar diferentes niveles de acceso según el rol.

Funciones disponibles:

* Obtener todos los usuarios.
* Obtener un usuario por ID.
* Obtener un usuario por nombre.
* Obtener usuarios activos.
* Obtener usuarios filtrados por rol.
* Obtener información del usuario autenticado.

Los endpoints de administración están protegidos mediante autorización basada en roles, incluyendo operaciones exclusivas para usuarios con el rol `ADMIN`.

Ejemplos:

```http
GET /api/usuario/obtener
GET /api/usuario/obtener/{id}
GET /api/usuario/me
GET /api/usuario/{nombre}
GET /api/usuario/obtener/activos
GET /api/usuario/obtener/rol
```

---

## 🔒 Seguridad

La aplicación utiliza **Spring Security** junto con **JWT** para proteger los recursos de la API.

El proyecto incluye:

* Filtro personalizado de autenticación JWT.
* Configuración centralizada de Spring Security.
* Control de acceso basado en roles.
* Gestión personalizada de respuestas de autenticación.
* Gestión personalizada de acceso denegado.
* Validación de tokens.
* Refresh Tokens.

La autorización se realiza mediante anotaciones como:

```java
@PreAuthorize("hasRole('ADMIN')")
```
---

## ⚙️ Requisitos

Antes de ejecutar el proyecto necesitas tener instalado:

* **JDK 21**
* **Maven** (también se incluye Maven Wrapper)
* **MySQL**
* Un IDE compatible con proyectos Java, como IntelliJ IDEA, Eclipse o VS Code.

---

También es recomendable configurar las claves utilizadas para la generación y validación de los tokens JWT mediante variables de entorno o un fichero de configuración que no se suba al repositorio.

---

## 📋 Estado del proyecto

🚧 **En desarrollo**

El proyecto continúa evolucionando y se irán incorporando nuevas funcionalidades relacionadas con la gestión empresarial, seguridad, usuarios y otros módulos de la aplicación.

---

## 👨‍💻 Autor

**Adrián Vicente**

GitHub: [@adrian-vicente](https://github.com/adrian-vicente)

Repositorio: [app-gestion-empresarial](https://github.com/adrian-vicente/app-gestion-empresarial)

Perfil de Linkedin: [@adrian-vicente](https://www.linkedin.com/in/adrian-vicente-vera-b73429202/)

---

## 📄 Licencia

Este proyecto se encuentra actualmente en desarrollo.
