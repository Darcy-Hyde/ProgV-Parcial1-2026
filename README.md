# Examen Programación V – Sistema de Gestión de Personal

Proyecto base en JSP/Servlets con arquitectura en capas
(**Presentación → Negocio → Datos**) y MySQL.

## Requisitos

- JDK 17 o superior
- Apache Tomcat **10.1** (Jakarta EE 10, paquetes `jakarta.*`)
- MySQL 8
- Maven (incluido en NetBeans, IntelliJ y Eclipse)

## Puesta en marcha

1. Cree la base de datos ejecutando `db/schema.sql` en MySQL
   (crea `rrhh_examen` con tres departamentos de ejemplo).
2. Si su usuario o clave de MySQL no es `root` sin clave, ajústelos en
   `src/main/java/pa/ac/unachi/rrhh/datos/Conexion.java`.
3. Abra la carpeta como proyecto Maven en su IDE y asocie el servidor Tomcat 10.1.
4. Ejecute `mvn clean package` (o *Clean and Build*) y despliegue `target/rrhh.war`.
5. Abra `http://localhost:8080/rrhh/`.

## Estructura

| Capa | Archivos |
|---|---|
| Base de datos | `db/schema.sql`, `db/migracion_examen.sql` |
| Modelo | `modelo/Departamento.java`, `modelo/Dotacion.java` |
| Datos | `datos/Conexion.java`, `datos/DepartamentoDAO.java`, `datos/DotacionDAO.java` |
| Negocio | `negocio/DepartamentoService.java`, `negocio/DotacionService.java`, `negocio/NegocioException.java` |
| Presentación – controlador | `presentacion/DepartamentoServlet.java`, `presentacion/DotacionServlet.java` |
| Presentación – vistas | `departamentos/formulario.jsp`, `departamentos/listar.jsp`, `dotacion/listar.jsp` |

## Datos de ejemplo

| Código | Departamento | Colaboradores |
|---|---|---|
| DEP-001 | Ventas | 12 |
| DEP-002 | Auditoría Interna | 0 |
| DEP-003 | Soporte Técnico | 6 |

Coloque sus tres capturas de pantalla en la carpeta `evidencias/` antes de entregar.
