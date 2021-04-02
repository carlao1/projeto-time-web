/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.TreinadorDao;
import Model.Treinador;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Carlos
 */
public class TreinadorController {
    public boolean criar(Map<String, String> input){
        
        
        
       //LÓGICA DE VALIDAÇÃO AQUI, CHAMADA A FAZER
        
        //LÓGICA DE CRIAÇÃO AQUI (CHAMADA A CONTROLADOR FUTURAMENTE)
        Treinador p1 = new Treinador();
        
        p1.preencherTreinador( 
                Integer.parseInt(input.get("idade")), 
                input.get("nome"), 
                input.get("sexo").charAt(0), 
                input.get("cpf")
        );
        
        TreinadorDao dao = new TreinadorDao();
        
        boolean isCreated = dao.create(p1);
        
        if(isCreated){
            //FUTURE: IDEALMENTE, UMA MENSAGEM POPUP OU ALGO ASSIM
            System.out.println("Treinador criado");
        }
        
        return isCreated;
    }
    
    public String[][] obterTodos(){
        TreinadorDao dao = new TreinadorDao();
        
        ArrayList<Treinador> todosTreinadores = dao.getAll();
        
        String[][] result = new String[todosTreinadores.size()][];
        
        for(int i = 0; i<todosTreinadores.size(); i++){
            
            Treinador jogAtual = todosTreinadores.get(i);
            
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
    
    public Treinador obter(String id){
        return new Treinador();
    }
    
    public Treinador atualizar(String id){
        return new Treinador();
    }
    
    
    public boolean deletar(String cpf){
        TreinadorDao dao = new TreinadorDao();
        return dao.delete(cpf);
    }
}
