/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebController;

import Dao.PresidenteDao;
import Model.Presidente;
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
@WebServlet(name = "Presidente", urlPatterns = {"/Presidentes"})
public class PresidenteController extends HttpServlet {
    
    private static String INSERT = "/presidente.jsp";
    private static String LIST = "/listPresidente.jsp";

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
            out.println("<title>Servlet PresidenteController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PresidenteController at " + request.getContextPath() + "</h1>");
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
        
        boolean result = false;
        
        //result = this.criar(request);
        
        //LÓGICA DE CRIAÇÃO AQUI (CHAMADA A CONTROLADOR FUTURAMENTE)
        Presidente p1 = new Presidente();
        
        p1.preencherPresidente(
                Integer.parseInt(request.getParameter("idade")), 
                request.getParameter("nome"), 
                request.getParameter("sexo").charAt(0), 
                request.getParameter("cpf")
        );
        
        //FUTURE: FUNCIONALIDADE DO CONTROLADOR
        PresidenteDao dao = new PresidenteDao();
        
        boolean isCreated = dao.create(p1);
        
        RequestDispatcher view = request.getRequestDispatcher(LIST);
        request.setAttribute("presidentes", this.obterTodos());
        view.forward(request, response);
        
    }
    
    
    public boolean criar(HttpServletRequest request){
        
        
        return false;
    }
    
    public ArrayList<Presidente> obterTodos(){
        PresidenteDao dao = new PresidenteDao();
        
        ArrayList<Presidente> todosJogadores = dao.getAll();
        
        return todosJogadores;
        
        //String[][] result = new String[todosJogadores.size()][];
        
        /*for(int i = 0; i<todosJogadores.size(); i++){
            
            Jogador jogAtual = todosJogadores.get(i);
            
            result[i] = new String[]{
                
                jogAtual.getNome(),
                String.valueOf(jogAtual.getSexo()),
                String.valueOf(jogAtual.getIdade()), 
                jogAtual.getCpf(),
            };
        }*/
        
        //System.out.println(result);
        
        //return result;
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
