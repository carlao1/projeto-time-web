/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebController;

import Dao.SocioDao;
import Model.Socio;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Luiz
 */
@WebServlet(name = "Socio", urlPatterns = {"/Socios"})
public class SocioController extends HttpServlet {
    
    private static String INSERT = "/socio.jsp";
    private static String LIST = "/listSocio.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SocioController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SocioController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
        
        forward = this.INSERT;

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Socio p1 = new Socio();
        
        p1.preencherSocio(
                Integer.parseInt(request.getParameter("idade")), 
                request.getParameter("nome"), 
                request.getParameter("sexo").charAt(0), 
                request.getParameter("cpf")
        );
        
        //FUTURE: FUNCIONALIDADE DO CONTROLADOR
        SocioDao dao = new SocioDao();
        
        boolean isCreated = dao.create(p1);
        
        RequestDispatcher view = request.getRequestDispatcher(LIST);
        request.setAttribute("socios", this.obterTodos());
        view.forward(request, response);
    }
    
    public ArrayList<Socio> obterTodos(){
        SocioDao dao = new SocioDao();
        
        ArrayList<Socio> todosJogadores = dao.getAll();
        
        return todosJogadores;
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
