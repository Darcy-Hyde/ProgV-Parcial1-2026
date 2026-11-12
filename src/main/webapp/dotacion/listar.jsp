<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de Dotación</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
<%@ include file="/WEB-INF/jspf/menu.jspf" %>
<main>
    <h2>Gestión de Dotación</h2>
    <c:if test="${not empty error}"><div class="error"><c:out value="${error}"/></div></c:if>
    <c:if test="${not empty mensaje}"><div class="mensaje"><c:out value="${mensaje}"/></div></c:if>

    <table>
        <thead>
        <tr>
            <th>Código</th>
            <th>Departamento</th>
            <th class="numero">Colaboradores</th>
            <%-- Parte 6.3: Columna de lectura --%>
            <th class="numero">Dotación Máxima</th>
            <th>Registrar movimiento</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="t" items="${dotaciones}">
            <tr>
                <td><c:out value="${t.codigoDepartamento}"/></td>
                <td><c:out value="${t.nombreDepartamento}"/></td>
                <td class="numero">${t.cantidad}</td>
                <td class="numero">${t.dotacionMaximaDepartamento}</td>
                <td>
                    <form class="en-linea" method="post" action="${pageContext.request.contextPath}/dotacion">
                        <input type="hidden" name="departamentoId" value="${t.departamentoId}">
                        <input type="number" name="cantidad" min="1" step="1" value="1" required>
                        <button class="boton" type="submit" name="accion" value="alta">Alta</button>
                        <button class="boton secundario" type="submit" name="accion" value="baja">Baja</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty dotaciones}">
            <tr><td colspan="5">No hay departamentos registrados.</td></tr>
        </c:if>
        </tbody>
    </table>
</main>
</body>
</html>