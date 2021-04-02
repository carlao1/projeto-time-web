/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.SocioDao;
import Model.Socio;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Carlos
 */
public class SocioController {
    
    public boolean criar(Map<String, String> input){
        
        
        //LÓGICA DE VALIDAÇÃO AQUI, CHAMADA A FAZER
        
        //LÓGICA DE CRIAÇÃO AQUI (CHAMADA A CONTROLADOR FUTURAMENTE)
        Socio p1 = new Socio();
        
        p1.preencherSocio(
                Integer.parseInt(input.get("idade")), 
                input.get("nome"), 
                input.get("sexo").charAt(0), 
                input.get("cpf"));
        
        SocioDao dao = new SocioDao();
        
        boolean isCreated = dao.create(p1);
        
        if(isCreated){
            //FUTURE: IDEALMENTE, UMA MENSAGEM POPUP OU ALGO ASSIM
            System.out.println("Socio torcedor criado");
        }
        
        
        return isCreated;
    }
    
    public String[][] obterTodos(){
        SocioDao dao = new SocioDao();
        
        ArrayList<Socio> todosSocios = dao.getAll();
        
        String[][] result = new String[todosSocios.size()][];
        
        for(int i = 0; i<todosSocios.size(); i++){
            
            Socio socioAtual = todosSocios.get(i);
            
            result[i] = new String[]{
                
                socioAtual.getNome(),
                String.valueOf(socioAtual.getSexo()),
                String.valueOf(socioAtual.getIdade()), 
                socioAtual.getCpf(),
            };
        }
        
        //System.out.println(result);
        
        return result;
    }
    
    public Socio obter(String id){
        return new Socio();
    }
    
    public Socio atualizar(String id){
        return new Socio();
    }
    
    
    public boolean deletar(String cpf){
        SocioDao dao = new SocioDao();
        return dao.delete(cpf);
    }
}
