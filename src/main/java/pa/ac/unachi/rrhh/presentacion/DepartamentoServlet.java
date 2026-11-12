package pa.ac.unachi.rrhh.presentacion;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import pa.ac.unachi.rrhh.modelo.Departamento;
import pa.ac.unachi.rrhh.negocio.DepartamentoService;
import pa.ac.unachi.rrhh.negocio.NegocioException;

/**
 * Controlador de departamentos. Solo lee parámetros, llama al Service
 * y decide la vista. No escribe SQL ni valida reglas de negocio.
 */
@WebServlet(name = "DepartamentoServlet", urlPatterns = {"/departamentos"})
public class DepartamentoServlet extends HttpServlet {

    private final DepartamentoService service = new DepartamentoService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = valor(request.getParameter("accion"), "listar");
        try {
            switch (accion) {
                case "nuevo" -> mostrarFormulario(request, response);
                case "editar" -> {
                    int id = Integer.parseInt(request.getParameter("id"));
                    request.setAttribute("departamento", service.buscar(id));
                    mostrarFormulario(request, response);
                }
                default -> listar(request, response);
            }
        } catch (NegocioException | NumberFormatException e) {
            request.setAttribute("error", "No se pudo abrir el departamento solicitado.");
            listarSinError(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error de base de datos", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = valor(request.getParameter("accion"), "");
        try {
            switch (accion) {
                case "guardar" -> guardar(request, response);
                case "eliminar" -> {
                    service.eliminar(Integer.parseInt(request.getParameter("id")));
                    response.sendRedirect(request.getContextPath() + "/departamentos");
                }
                default -> response.sendRedirect(request.getContextPath() + "/departamentos");
            }
        } catch (SQLException e) {
            throw new ServletException("Error de base de datos", e);
        }
    }

    private void guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String idParam = request.getParameter("id");
        String salarioBaseParam = request.getParameter("salarioBase");
        String dotacionMaximaParam = request.getParameter("dotacionMaxima");

        Departamento d = new Departamento();
        d.setCodigo(valor(request.getParameter("codigo"), "").trim().toUpperCase());
        d.setNombre(valor(request.getParameter("nombre"), "").trim());
        

        // Parte 5.1: Lectura y conversión con captura explícita de NumberFormatException
        try {
            if (salarioBaseParam != null && !salarioBaseParam.isBlank()) {
                d.setSalarioBase(new BigDecimal(salarioBaseParam.trim()));
            }
            if (dotacionMaximaParam != null && !dotacionMaximaParam.isBlank()) {
                d.setDotacionMaxima(Integer.parseInt(dotacionMaximaParam.trim()));
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Los campos salario base y dotación máxima deben ser valores numéricos válidos.");
            request.setAttribute("departamento", d);
            mostrarFormulario(request, response);
            return;
        }

        try {
            if (idParam == null || idParam.isBlank()) {
                service.crear(d);
            } else {
                d.setId(Integer.parseInt(idParam));
                service.actualizar(d);
            }
            response.sendRedirect(request.getContextPath() + "/departamentos");
        } catch (NegocioException e) {
            
        	//Parte 5.2: Volver al formulario conservando lo que escribió el usuario
            request.setAttribute("error", e.getMessage());
            request.setAttribute("departamento", d);
            mostrarFormulario(request, response);
        }
    }

    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        request.setAttribute("departamentos", service.listar());
        request.getRequestDispatcher("/departamentos/listar.jsp").forward(request, response);
    }

    private void listarSinError(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            listar(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error de base de datos", e);
        }
    }

    private void mostrarFormulario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/departamentos/formulario.jsp").forward(request, response);
    }

    private static String valor(String texto, String porDefecto) {
        return texto == null ? porDefecto : texto;
    }
}
