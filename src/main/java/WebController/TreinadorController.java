/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebController;

import Dao.TreinadorDao;
import Model.Treinador;
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
@WebServlet(name = "Treinador", urlPatterns = {"/Treinadores"})
public class TreinadorController extends HttpServlet {
    
    private static String INSERT = "/treinador.jsp";
    private static String LIST = "/listTreinador.jsp";

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
            out.println("<title>Servlet TreinadorController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TreinadorController at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
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
        Treinador p1 = new Treinador();
        
        p1.preencherTreinador(
                Integer.parseInt(request.getParameter("idade")), 
                request.getParameter("nome"), 
                request.getParameter("sexo").charAt(0), 
                request.getParameter("cpf")
        );
        
        //FUTURE: FUNCIONALIDADE DO CONTROLADOR
        TreinadorDao dao = new TreinadorDao();
        
        boolean isCreated = dao.create(p1);
        
        RequestDispatcher view = request.getRequestDispatcher(LIST);
        request.setAttribute("treinadores", this.obterTodos());
        view.forward(request, response);
    }
    
    public ArrayList<Treinador> obterTodos(){
        TreinadorDao dao = new TreinadorDao();
        
        ArrayList<Treinador> todosJogadores = dao.getAll();
        
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
