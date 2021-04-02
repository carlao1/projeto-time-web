package Model;

import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Carlos
 */
public class Jogador extends Pessoa {
    private String cpf;
    
    
    
    public Jogador(){
        super.setIdade(0);
        super.setNome("");
        super.setSexo(' ');
        this.cpf = "000.000.000.00";
    }
     
    public void preencher(){
        Scanner leitor = new Scanner(System.in);
        System.out.println("***** prenchendo jogador*******");
        System.out.println("informe o nome\n");
        super.setNome(leitor.next());
        System.out.println("informe o sexo\n");
        super.setSexo(leitor.next().charAt(0));
        System.out.println("informe a idade\n");
        super.setIdade(leitor.nextInt());
        System.out.println("informe o cpf\n");
        this.setCpf(leitor.next());
           
    }
    
    public void preencherJogador(int idade, String nome, char sexo, String cpf){
    
        //CRIAR UM MÉTODO PRA PREENCHER EH OPCIONAL, POREM BOA PRATICA.
        this.setIdade(idade);
        this.setNome(nome);
        this.setSexo(sexo);
        this.setCpf(cpf);
        
        //MENSAGEM APENAS DEMONSTRATIVA, NAO PRECISA NO PROGRAMA FINAL
        System.out.println("JOGADOR PREENCHIDO COM SUCESSO");
        
    }
    
    @Override
    public void imprimir(){
        System.out.println("***** imprimindo JOGADOR*******");
        super.imprimir();
        System.out.println("cpf" + this.getCpf());
        System.out.println("*************");
    
    }
    
    
    public void copiar(Jogador outro){
       super.setNome(outro.getNome());
       super.setSexo(outro.getSexo());
       super.setIdade(outro.getIdade());
       this.setCpf(outro.getCpf());
       
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    
}
