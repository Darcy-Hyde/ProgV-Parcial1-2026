<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Departamentos</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
<%@ include file="/WEB-INF/jspf/menu.jspf" %>
<main>
    <h2>Departamentos</h2>
    <c:if test="${not empty error}"><div class="error">${error}</div></c:if>

    <a class="boton" href="${pageContext.request.contextPath}/departamentos?accion=nuevo">Nuevo departamento</a>

    <table>
        <thead>
        <tr>
            <th>Código</th>
            <th>Nombre</th>
            <%-- Parte 6.2:Columnas de listado --%>
            <th class="numero">Salario Base</th>
            <th class="numero">Dotación Máxima</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="d" items="${departamentos}">
            <tr>
                <td><c:out value="${d.codigo}"/></td>
                <td><c:out value="${d.nombre}"/></td>
                <td class="numero">
                    <fmt:formatNumber value="${d.salarioBase}" type="currency" currencySymbol="B/. " minFractionDigits="2" maxFractionDigits="2"/>
                </td>
                <td class="numero">${d.dotacionMaxima}</td>
                <td>
                    <a class="boton secundario" href="${pageContext.request.contextPath}/departamentos?accion=editar&id=${d.id}">Editar</a>
                    <form class="en-linea" method="post" action="${pageContext.request.contextPath}/departamentos"
                          onsubmit="return confirm('¿Eliminar el departamento ${d.codigo}?');">
                        <input type="hidden" name="accion" value="eliminar">
                        <input type="hidden" name="id" value="${d.id}">
                        <button class="boton peligro" type="submit">Eliminar</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty departamentos}">
            <tr><td colspan="5">No hay departamentos registrados.</td></tr>
        </c:if>
        </tbody>
    </table>
</main>
</body>
</html>

