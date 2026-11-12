package pa.ac.unachi.rrhh.presentacion;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import pa.ac.unachi.rrhh.negocio.DotacionService;
import pa.ac.unachi.rrhh.negocio.NegocioException;

/**
 * Controlador de la dotación de personal: listado, altas y bajas.
 */
@WebServlet(name = "DotacionServlet", urlPatterns = {"/dotacion"})
public class DotacionServlet extends HttpServlet {

    private final DotacionService service = new DotacionService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Object mensaje = request.getSession().getAttribute("mensaje");
        if (mensaje != null) {
            request.setAttribute("mensaje", mensaje);
            request.getSession().removeAttribute("mensaje");
        }
        listar(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        try {
            int departamentoId = Integer.parseInt(request.getParameter("departamentoId"));
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            if ("alta".equals(accion)) {
                service.registrarAlta(departamentoId, cantidad);
                request.getSession().setAttribute("mensaje", "Alta registrada: " + cantidad + " colaborador(es).");
            } else if ("baja".equals(accion)) {
                service.registrarBaja(departamentoId, cantidad);
                request.getSession().setAttribute("mensaje", "Baja registrada: " + cantidad + " colaborador(es).");
            }
            response.sendRedirect(request.getContextPath() + "/dotacion");
        } catch (NumberFormatException e) {
            request.setAttribute("error", "La cantidad debe ser un número entero.");
            listar(request, response);
        } catch (NegocioException e) {
            request.setAttribute("error", e.getMessage());
            listar(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error de base de datos", e);
        }
    }

    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setAttribute("dotaciones", service.listar());
        } catch (SQLException e) {
            throw new ServletException("Error de base de datos", e);
        }
        request.getRequestDispatcher("/dotacion/listar.jsp").forward(request, response);
    }
}
