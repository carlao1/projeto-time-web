/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import DB.Persistencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import Model.Jogador;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author Carlos
 */
public class JogadorDao {
    
    public JogadorDao(){
        //Persistencia persist
        
    }
    
    //CREATE
    public boolean create(Jogador jogador1){
    
        String stmt = "INSERT INTO jogadores ( nome, sexo, idade, cpf ) VALUES (?, ?, ?, ?) ";
        //Connection conn = Persistencia.conexao();
        
        Connection conn = null; //ESSE EH O TAL DO DOIDO UAI
        
        try{
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("jdbc/dbprojetotime"); //CONFIGURADO DO PAYARA
            conn = ds.getConnection();
        }catch(Exception e){
            e.printStackTrace();
        } 
        
        try{
            
            PreparedStatement ps = conn.prepareStatement(stmt);
            
            ps.setString(1, jogador1.getNome());
            ps.setString(2, String.valueOf(jogador1.getSexo())); //LEMBRAR DE PARSEAR DEPOIS
            ps.setInt(3, jogador1.getIdade()); //Deve ser int no banco tambem
            ps.setString(4, jogador1.getCpf());
            
            boolean res = ps.execute();
            
            
            //System.out.println(res);
            //System.out.println(ps.getUpdateCount());
            
            //CHECAR O QUE VEM COMO RESPOSTA E FAZER O RETURN BASEADO NISSO
            
            //TODO: VERIFICAR SE EXISTE UMA FORMA 'MELHOR'
            
            if(ps.getUpdateCount() > 0 && res == false){
                return true;
            }
            
            
        }catch(Exception e){
            
            //TODO: TRATAMENTO MELHOR DE ERRO
        
            e.printStackTrace();
            
        }
        
        return false;
        
    }
    
    //READ*
    public ArrayList<Jogador> getAll(){ //Consulta pra Retornar Todos jogadores
        
        
        String stmt = "SELECT * FROM jogadores";
        Connection conn = Persistencia.conexao();
        
        ArrayList<Jogador> resJogadores = new ArrayList<Jogador>();
        
        try{
            
            
            PreparedStatement ps = conn.prepareStatement(stmt);
            boolean res = ps.execute();
            
            //System.out.println(res);
            
            if(res){
                
                ResultSet rsJogadores = ps.getResultSet();
                
                //System.out.println(rsJogadores);
                
                //Tratar isso para um arraylist e retornar
                
                while(rsJogadores.next()){
                    //PARSING DOS RESULTADOS
                
                    //System.out.println(rsJogadores.getString("nome"));
                    Jogador novoJogador = new Jogador();
                    
                    novoJogador.preencherJogador(
                            
                            rsJogadores.getInt("idade"), 
                            rsJogadores.getString("nome"), 
                            rsJogadores.getString("sexo").charAt(0), 
                            rsJogadores.getString("cpf")
                        );
                    
                    resJogadores.add(novoJogador);
                    
                }
                
                return resJogadores;
                
            }
            
            
            //conn.prepareStatement();
            
        }catch(Exception e){
        
            e.printStackTrace();
            
        }
    
        return resJogadores;
        
    }
    
    //READ
    public Jogador getOne(int id){ //Retorna um jogador especifico 
        //Ver o tipo do id depois, deve ser int mesmo mas se for
        //diferente muda depois
        
        String stmt = "SELECT * FROM jogadores AS j WHERE j.id = ? LIMIT 1";
        Connection conn = Persistencia.conexao();
        
        try{

            PreparedStatement ps = conn.prepareStatement(stmt);
            
            ps.setInt(1, id);
            
            ResultSet rsJogador = ps.executeQuery();
            
            rsJogador.next();
            
            //COM O BANCO PRONTO:            
            //rsJogador.getString("nome"); //e afins
            
            //lembrar de converter a string 'sexo' em char denovo
            
            Jogador novoJogador = new Jogador();
                    
            novoJogador.preencherJogador(
                    rsJogador.getInt("idade"), 
                    rsJogador.getString("nome"), 
                    rsJogador.getString("sexo").charAt(0), 
                    rsJogador.getString("cpf")
                );
            
            return novoJogador;
            
            //conn.prepareStatement();
            
        }catch(Exception e){
        
            e.printStackTrace();
            
        }
    
        return null;
    }
    
    //UPDATE
    public Jogador update(int id, Jogador dadosJogadorUpdate){ //Atualiza um 
        //acho que deveria retornar boolean, mas ai vai da preferencia de cada um
        
        String stmt = "UPDATE jogadores SET nome = ?, sexo = ?, idade = ?, cpf = ? WHERE id = ?";
        
        Connection conn = Persistencia.conexao();
        
        try{
            
            PreparedStatement ps = conn.prepareStatement(stmt);
            
            ps.setString(1, dadosJogadorUpdate.getNome());
            ps.setString(2, String.valueOf(dadosJogadorUpdate.getSexo()));
            ps.setInt(3, dadosJogadorUpdate.getIdade());
            ps.setString(4, dadosJogadorUpdate.getCpf());
            
            ps.setInt(5, id);
            
            int rcount = ps.executeUpdate();
            
            if (rcount > 0){
                
                Jogador jogadorAtual = this.getOne(id);
                
                return jogadorAtual; 
                //FAZER UM SELECT COM O ID DO ATUALIZADO, SENAO, RETORNAR BOOLEAN
                //SE NAO ME ENGANO O JDBC O PROPRIO UPDATE RETORNA, VOU LER SOBRE DEPOIS
                //NA DUVIDA DEIXA ASSIM POR ENQUANTO
            }
            
            return null;
            
        }catch(Exception e){
        
            e.printStackTrace();
            
        }
        
        return null;
    }
    
    //DELETE
    public boolean delete(String cpf) {
        
        String stmt = "DELETE FROM jogadores WHERE cpf = ?";
        Connection conn = Persistencia.conexao();
        
        try{
            
            PreparedStatement ps = conn.prepareStatement(stmt);
            ps.setString(1, cpf);
            
            System.out.println(ps);
            boolean res = ps.execute();
            
            return res; //TODO: DEPENDENDO DO BANCO, CONSERTAR
            
        }catch(Exception e){
        
            e.printStackTrace();
            
        }
        
        return false;
    }
    
    
    
}
