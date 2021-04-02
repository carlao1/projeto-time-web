/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.JogadorDao;
import java.util.Map;
import javax.swing.JFrame;
import Model.Jogador;
import java.util.ArrayList;

/**
 *
 * @author Carlos
 */
public class JogadorController {
    
    //Vou fazer em portugues mesmo, mas nao eh o recomendavel
    public boolean criar(Map<String, String> input){
        
        
        
        /*String nome = this.inputNomeJogador.getText();
        String cpf = this.inputCPFJogador.getText();
        char sexo = 'M';//ATENCAO: FALTANDO SEXO NOS CAMPOS
        int idade = Integer.parseInt(this.inputIdadeJogador.getText());*/
        
        //LÓGICA DE VALIDAÇÃO AQUI, CHAMADA A FAZER
        
        //LÓGICA DE CRIAÇÃO AQUI (CHAMADA A CONTROLADOR FUTURAMENTE)
        Jogador p1 = new Jogador();
        
        p1.preencherJogador(
                Integer.parseInt(input.get("idade")), 
                input.get("nome"), 
                input.get("sexo").charAt(0), 
                input.get("cpf"));
        
        //FUTURE: FUNCIONALIDADE DO CONTROLADOR
        JogadorDao dao = new JogadorDao();
        
        boolean isCreated = dao.create(p1);
        
        if(isCreated){
            //FUTURE: IDEALMENTE, UMA MENSAGEM POPUP OU ALGO ASSIM
            System.out.println("Jogador criado");
            
        }
        
        return isCreated;
    }
    
    public Jogador obter(String id){
        return new Jogador();
    }
    
    public String[][] obterTodos(){
        JogadorDao dao = new JogadorDao();
        
        ArrayList<Jogador> todosJogadores = dao.getAll();
        
        String[][] result = new String[todosJogadores.size()][];
        
        for(int i = 0; i<todosJogadores.size(); i++){
            
            Jogador jogAtual = todosJogadores.get(i);
            
            result[i] = new String[]{
                
                jogAtual.getNome(),
                String.valueOf(jogAtual.getSexo()),
                String.valueOf(jogAtual.getIdade()), 
                jogAtual.getCpf(),
            };
        }
        
        //System.out.println(result);
        
        return result;
    }
    
    public Jogador atualizar(String id){
        return new Jogador();
    }
    
    
    public boolean deletar(String cpf){
        
        JogadorDao dao = new JogadorDao();
        return dao.delete(cpf);
        
    }
    
    
    
    
}
