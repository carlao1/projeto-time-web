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
import Model.Treinador;

/**
 *
 * @author Carlos
 */
public class TreinadorDao {
    
    public TreinadorDao(){
        //Persistencia persist
        
    }
    
    public boolean create(Treinador treinador1){
    
        String stmt = "INSERT INTO treinadores (nome, sexo, idade, cpf) VALUES (?, ?, ?, ?) ";
        Connection conn = Persistencia.conexao();
        
        try{
            
            PreparedStatement ps = conn.prepareStatement(stmt);
            
            ps.setString(1, treinador1.getNome());
            ps.setString(2, String.valueOf(treinador1.getSexo())); //LEMBRAR DE PARSEAR DEPOIS
            ps.setInt(3, treinador1.getIdade()); //Deve ser int no banco tambem
            ps.setString(4, treinador1.getCpf());
            
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
    
    public ArrayList<Treinador> getAll(){ //Consulta pra Retornar Todos os treinadores
        
        
        String stmt = "SELECT * FROM treinadores";
        Connection conn = Persistencia.conexao();
        
        ArrayList<Treinador> resTreinadores = new ArrayList<Treinador>();
        
        try{

            PreparedStatement ps = conn.prepareStatement(stmt);
            
            ResultSet rsTreinadores = ps.executeQuery();
            
            while(rsTreinadores.next()){
                    //PARSING DOS RESULTADOS
                
                    //System.out.println(rsJogadores.getString("nome"));
                    Treinador novoTreinador = new Treinador();
                    
                    novoTreinador.preencherTreinador(rsTreinadores.getInt("idade"), 
                            rsTreinadores.getString("nome"), 
                            rsTreinadores.getString("sexo").charAt(0), 
                            rsTreinadores.getString("cpf")
                        );
                    
                    resTreinadores.add(novoTreinador);
                    
                }
                
                return resTreinadores;
            
            //conn.prepareStatement();
            
        }catch(Exception e){
        
            e.printStackTrace();
            
        }
    
        return null;
        
    }
    
    public Treinador getOne(int id){ //Retorna um treinador especifico 
        //Ver o tipo do id depois, deve ser int mesmo mas se for
        //diferente muda depois
        
        String stmt = "SELECT * FROM treinadores AS j WHERE j.id = ? LIMIT 1";
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
    
    public Treinador update(int id, Treinador dadosTreinadorUpdate){ //Atualiza um 
        //acho que deveria retornar boolean, mas ai vai da preferencia de cada um
        
        String stmt = "UPDATE treinadores SET nome = ?, sexo = ?, idade = ?, cpf = ? WHERE id = ?";
        
        Connection conn = Persistencia.conexao();
        
        try{
            
            PreparedStatement ps = conn.prepareStatement(stmt);
            
            ps.setString(1, dadosTreinadorUpdate.getNome());
            ps.setString(2, String.valueOf(dadosTreinadorUpdate.getSexo()));
            ps.setInt(3, dadosTreinadorUpdate.getIdade());
            ps.setString(4, dadosTreinadorUpdate.getCpf());
            
            ps.setInt(5, id);
            
            int rcount = ps.executeUpdate();
            
            if (rcount > 0){
                
                Treinador treinadorAtual = this.getOne(id);
                
                return treinadorAtual; 
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
        
        String stmt = "DELETE FROM treinadores WHERE cpf = ?";
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
