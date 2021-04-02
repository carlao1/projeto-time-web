/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Carlos
 */
public class Pessoa {
    private String nome;
    private char sexo;
    private int idade;
    
    /**
     *
     */
    public Pessoa(){
        this.nome = "";
        this.sexo = ' ';
        this.idade = 0;
    }
    
    
    public void imprimir(){
        System.out.println("***** imprimindo Pessoa *******");
        System.out.println("nome:" + this.getNome());
        System.out.println("sexo" + this.getSexo());
        System.out.println("idade" + this.getIdade());
        System.out.println("*************");
    
    }
    

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the sexo
     */
    public char getSexo() {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    /**
     * @return the idade
     */
    public int getIdade() {
        return idade;
    }

    /**
     * @param idade the idade to set
     */
    public void setIdade(int idade) {
        this.idade = idade;
    }
    
    
    
}
