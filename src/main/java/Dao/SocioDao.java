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
import Model.Socio;

/**
 *
 * @author Carlos
 */
public class SocioDao {
    
    public SocioDao(){
        //Persistencia persist
        
    }
    
    public boolean create(Socio socio1){
        
        //FUTURE: PASSAR COMO PARAMETRO, TALVEZ UMA ALTERNATIVA 
    
        String stmt = "INSERT INTO socios (nome, sexo, idade, cpf) VALUES (?, ?, ?, ?) ";
        Connection conn = Persistencia.conexao();
        
        try{
            
            PreparedStatement ps = conn.prepareStatement(stmt);
            
            ps.setString(1, socio1.getNome());
            ps.setString(2, String.valueOf(socio1.getSexo())); //LEMBRAR DE PARSEAR DEPOIS
            ps.setInt(3, socio1.getIdade()); //Deve ser int no banco tambem
            ps.setString(4, socio1.getCpf());
            
            boolean res = ps.execute();
            
            //System.out.println(ps.getUpdateCount());
            
            //CHECAR O QUE VEM COMO RESPOSTA E FAZER O RETURN BASEADO NISSO
            
            if(ps.getUpdateCount() > 0 && res == false){
                return true;
            }
            
        }catch(Exception e){
            
            //TODO: TRATAMENTO MELHOR DE ERRO
        
            e.printStackTrace();
            
        }
        
        return false;
        
    }
    
    public ArrayList<Socio> getAll(){ //Consulta pra Retornar Todos os socioes
        
        
        String stmt = "SELECT * FROM socios";
        Connection conn = Persistencia.conexao();
        
        ArrayList<Socio> resJogadores = new ArrayList<Socio>();
        
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
                    Socio novoJogador = new Socio();
                    
                    novoJogador.preencherSocio(
                            
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
    
        return null;
        
    }
    
    public Socio getOne(int id){ //Retorna um socio especifico 
        //Ver o tipo do id depois, deve ser int mesmo mas se for
        //diferente muda depois
        
        String stmt = "SELECT * FROM socios AS j WHERE j.id = ? LIMIT 1";
        Connection conn = Persistencia.conexao();
        
        try{

            PreparedStatement ps = conn.prepareStatement(stmt);
            
            ps.setInt(1, id);
            
            ResultSet rsJogador = ps.executeQuery();
            
            //COM O BANCO PRONTO:
            
            //rs.getString("nome"); //e afins
            
            //lembrar de converter a string 'sexo' em char denovo
            
            return null;
            
            //conn.prepareStatement();
            
        }catch(Exception e){
        
            e.printStackTrace();
            
        }
    
        return null;
    }
    
    public Socio update(int id, Socio dadosSocioUpdate){ //Atualiza um 
        //acho que deveria retornar boolean, mas ai vai da preferencia de cada um
        
        String stmt = "UPDATE socios SET nome = ?, sexo = ?, idade = ?, cpf = ? WHERE id = ?";
        
        Connection conn = Persistencia.conexao();
        
        try{
            
            PreparedStatement ps = conn.prepareStatement(stmt);
            
            ps.setString(1, dadosSocioUpdate.getNome());
            ps.setString(2, String.valueOf(dadosSocioUpdate.getSexo()));
            ps.setInt(3, dadosSocioUpdate.getIdade());
            ps.setString(4, dadosSocioUpdate.getCpf());
            
            ps.setInt(5, id);
            
            int rcount = ps.executeUpdate();
            
            if (rcount > 0){
                
                Socio socioAtual = this.getOne(id);
                
                return socioAtual; 
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
    
    public boolean delete(String cpf) {
        
        String stmt = "DELETE FROM socios WHERE cpf = ?";
        Connection conn = Persistencia.conexao();
        
        try{
            
            PreparedStatement ps = conn.prepareStatement(stmt);
            ps.setString(1, cpf);
            
            boolean res = ps.execute();
            
            return res; //TODO: DEPENDENDO DO BANCO, CONSERTAR
            
        }catch(Exception e){
        
            e.printStackTrace();
            
        }
        
        return false;
    }
    
    
    
}
