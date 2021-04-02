/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.PresidenteDao;
import Dao.TreinadorDao;
import Model.Presidente;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Carlos
 */
public class PresidenteController {
    
    public boolean criar(Map<String, String> input){
        
        //LÓGICA DE VALIDAÇÃO AQUI, CHAMADA A FAZER
        
        //LÓGICA DE CRIAÇÃO AQUI (CHAMADA A CONTROLADOR FUTURAMENTE)
        Presidente p1 = new Presidente();
        
        p1.preencherPresidente(
                Integer.parseInt(input.get("idade")), 
                input.get("nome"), 
                input.get("sexo").charAt(0), 
                input.get("cpf")
        );
        
        PresidenteDao dao = new PresidenteDao();
        
        boolean isCreated = dao.create(p1);
        
        if(isCreated){
            //FUTURE: IDEALMENTE, UMA MENSAGEM POPUP OU ALGO ASSIM
            System.out.println("Presidente criado");
        }
        
        return isCreated;
    }
    
    public String[][] obterTodos(){
        PresidenteDao dao = new PresidenteDao();
        
        ArrayList<Presidente> todosPresidentes = dao.getAll();
        
        String[][] result = new String[todosPresidentes.size()][];
        
        for(int i = 0; i<todosPresidentes.size(); i++){
            
            Presidente presAtual = todosPresidentes.get(i);
            
            result[i] = new String[]{
                
                presAtual.getNome(),
                String.valueOf(presAtual.getSexo()),
                String.valueOf(presAtual.getIdade()), 
                presAtual.getCpf(),
            };
        }
        
        //System.out.println(result);
        
        return result;
    }
    
    public Presidente obter(String id){
        return new Presidente();
    }
    
    public Presidente atualizar(String id){
        return new Presidente();
    }
    
    
    public boolean deletar(String cpf){
        PresidenteDao dao = new PresidenteDao();
        return dao.delete(cpf);
    }
    
}
