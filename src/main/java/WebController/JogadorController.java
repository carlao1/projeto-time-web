package WebController;



import Dao.JogadorDao;
import Model.Jogador;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "Jogador", urlPatterns = {"/Jogadores"})
public class JogadorController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String INSERT = "/jogador.jsp";
    private static String LIST = "/listJogador.jsp";
    //private UserDao dao;

    public JogadorController() {
        super();
        //dao = new UserDao(); //modell
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");

        /*if (action.equalsIgnoreCase("delete")){
            int userId = Integer.parseInt(request.getParameter("userId"));
            dao.deleteUser(userId);
            forward = LIST_USER;
            request.setAttribute("users", dao.getAllUsers());    
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int userId = Integer.parseInt(request.getParameter("userId"));
            User user = dao.getUserById(userId);
            request.setAttribute("user", user);
        } else if (action.equalsIgnoreCase("listUser")){
            forward = LIST_USER;
            request.setAttribute("users", dao.getAllUsers());
        } else {
            forward = INSERT_OR_EDIT;
        }*/
        
        forward = this.INSERT;

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //String qqr = request.getParameter("nome");
        
        /*if (action.equalsIgnoreCase("delete")){
            int userId = Integer.parseInt(request.getParameter("userId"));
            dao.deleteUser(userId);
            forward = LIST_USER;
            request.setAttribute("users", dao.getAllUsers());    
        }
        
          */
        
        boolean result = false;
        
        //result = this.criar(request);
        
        //LÓGICA DE CRIAÇÃO AQUI (CHAMADA A CONTROLADOR FUTURAMENTE)
        Jogador p1 = new Jogador();
        
        p1.preencherJogador(
                Integer.parseInt(request.getParameter("idade")), 
                request.getParameter("nome"), 
                request.getParameter("sexo").charAt(0), 
                request.getParameter("cpf")
        );
        
        //FUTURE: FUNCIONALIDADE DO CONTROLADOR
        JogadorDao dao = new JogadorDao();
        
        boolean isCreated = dao.create(p1);
        
        
        /*try{
            
            result = this.criar(request);
            
        }catch (Exception e) {
            e.printStackTrace();
        }*/
        
        //System.out.println(qqr);
        /*User user = new User();
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        try {
            Date dob=null;
            String teste = request.getParameter("dob");
            System.out.println(teste);
            if(request.getParameter("dob")!=null){
                dob = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("dob"));
            }
            else{
                dob = null;
            }
                
            user.setDob(dob);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setEmail(request.getParameter("email"));
        String userid = request.getParameter("userid");
        if(userid == null || userid.isEmpty())
        {
            dao.addUser(user);
        }
        else
        {
            user.setUserid(Integer.parseInt(userid));
            dao.updateUser(user);
        }*/
        RequestDispatcher view = request.getRequestDispatcher(LIST);
        request.setAttribute("jogadores", this.obterTodos());
        view.forward(request, response);
    }
    
    
    public boolean criar(HttpServletRequest request){
        
        
        //LÓGICA DE VALIDAÇÃO AQUI, CHAMADA A FAZER
        
        //LÓGICA DE CRIAÇÃO AQUI (CHAMADA A CONTROLADOR FUTURAMENTE)
        Jogador p1 = new Jogador();
        
        p1.preencherJogador(
                Integer.parseInt(request.getParameter("idade")), 
                request.getParameter("nome"), 
                request.getParameter("sexo").charAt(0), 
                request.getParameter("cpf")
        );
        
        //FUTURE: FUNCIONALIDADE DO CONTROLADOR
        JogadorDao dao = new JogadorDao();
        
        boolean isCreated = dao.create(p1);
        
        if(isCreated){
            //FUTURE: IDEALMENTE, UMA MENSAGEM POPUP OU ALGO ASSIM
            System.out.println("Jogador criado");
            
        }
        
        return isCreated;
    }
    
    public ArrayList<Jogador> obterTodos(){
        JogadorDao dao = new JogadorDao();
        
        ArrayList<Jogador> todosJogadores = dao.getAll();
        
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
    
    
}

/*
<thead>
            <tr>
                <th>User Id</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>DOB</th>
                <th>Email</th>
                <th colspan=2>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td><c:out value="${user.userid}" /></td>
                </tr>
            </c:forEach>
        </tbody>
*/