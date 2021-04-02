/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class Persistencia {
    
    private static Connection conn = null;
    
    private static final String CONNURL = "jdbc:mysql://localhost:3306/dbprojetotime?useTimezone=true&serverTimezone=UTC";
    private static final String USER = "testuserteam";
    private static final String PASSWORD = "1234";
    
    private Persistencia() {
        try{
            
            //Class.forName("com.mysql.jdbc.Driver");
            
            //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            
            //Class.forName("org.gjt.mm.mysql.Driver"); //NAO TENHO IDEIA
            ////conn = (Connection) DriverManager.getConnection(CONNURL, USER, PASSWORD);
            
            //NECESSARIA ESSA MUDANCA, PORQUE O PAYARA DA PROBLEMA NO DEPLOY SE USAR DIRETO O DRIVER
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("jdbc/dbprojetotime"); //CONFIGURADO DO POOL DO PAYARA
            conn = ds.getConnection();
            
            //System.out.println(conn);
            
        }/*catch(ClassNotFoundException e) {
            System.out.println("Nao foi possivel encontrar o Driver especificado" + e);
        }*/
        catch(SQLException ex) {
            System.out.println("Nao foi possivel encontrar o banco de dados, ERRO: " + ex);
        /*} catch (ClassNotFoundException ex) {
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);*/
        } catch (NamingException ex) {
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Connection conexao() {
        if( conn == null) {
            Persistencia p1 = new Persistencia();
        }
        return conn;
    }
    
}
