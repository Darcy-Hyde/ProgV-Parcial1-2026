<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%-- Punto extra: Solución al bug del ID en modo creación tras error de validación --%>
<c:set var="esEdicion" value="${not empty departamento && departamento.id > 0}"/>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>${esEdicion ? 'Editar' : 'Nuevo'} departamento</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
<%@ include file="/WEB-INF/jspf/menu.jspf" %>
<main>
    <h2>${esEdicion ? 'Editar' : 'Nuevo'} departamento</h2>
    <c:if test="${not empty error}"><div class="error"><c:out value="${error}"/></div></c:if>

    <form method="post" action="${pageContext.request.contextPath}/departamentos">
        <input type="hidden" name="accion" value="guardar">
        <c:if test="${esEdicion}">
            <input type="hidden" name="id" value="${departamento.id}">
        </c:if>

        <label for="codigo">Código</label>
        <input type="text" id="codigo" name="codigo" maxlength="10" placeholder="DEP-000"
               value="<c:out value='${departamento.codigo}'/>" required>

        <label for="nombre">Nombre</label>
        <input type="text" id="nombre" name="nombre" maxlength="100"
               value="<c:out value='${departamento.nombre}'/>" required>

        <%-- Parte 6.1: Salario Base y Dotación Máxima --%>
        <label for="salarioBase">Salario base</label>
        <input type="number" id="salarioBase" name="salarioBase" min="0.01" step="0.01"
               value="<c:out value='${departamento.salarioBase}'/>" required>

        <label for="dotacionMaxima">Dotación máxima</label>
        <input type="number" id="dotacionMaxima" name="dotacionMaxima" min="1" step="1"
               value="<c:out value='${departamento.dotacionMaxima}'/>" required>

        <div class="acciones">
            <button class="boton" type="submit">Guardar</button>
            <a class="boton secundario" href="${pageContext.request.contextPath}/departamentos">Cancelar</a>
        </div>
    </form>
</main>
</body>
</html>