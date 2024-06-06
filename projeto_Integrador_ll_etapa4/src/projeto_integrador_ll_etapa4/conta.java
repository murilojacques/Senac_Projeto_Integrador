/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto_integrador_ll_etapa4;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Murilo
 */

public class conta implements Serializable {
    
    private int id;

    private String nome_usuario = "";
    
    private String endereco ="";
    
    private String login;
    
    private int senha;
    
    private int cpf = 0;
    
    private int cnpj = 0;

    private String tipo = "";

    private float saldo = 0.0f;

    private boolean status;
    
    private String pfpj="";

    int i=0;
   /** 
    public void salvar(conta c){
        
            while(contas[i] != null){
                i++;
            }
            contas[i] =c;
            
    }

    public conta obter(int i){
        int j=0;
        while(j != i){
            j++;
        }
        return t.get(id);
    }**/
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public int getCnpj() {
        return cnpj;
    }

    public void setCnpj(int cnpj) {
        this.cnpj = cnpj;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPfpj() {
        return pfpj;
    }

    public void setPfpj(String pfpj) {
        this.pfpj = pfpj;
    }

    public void criarCon(int id,String t, String n, String login, int senha, String e, int cpf, int cnpj,  String pfpj) {
        
            this.setNome_usuario(n);
        switch (t) {
            case "cc" -> {
                this.setId(id);
                this.setTipo(t);
                this.setStatus(true);
                this.setSaldo(50.0f);
                this.setLogin(login);
                this.setSenha(senha);
                this.setEndereco(e);
                this.setCpf(cpf);
                this.setCnpj(cnpj);
                this.setPfpj(pfpj);
            }
            case "cp" -> {
                this.setId(id);
                this.setTipo(t);
                this.setStatus(true);
                this.setSaldo(150.0f);
                this.setLogin(login);
                this.setSenha(senha);
                this.setEndereco(e);
                this.setCpf(cpf);
                this.setCnpj(cnpj);
                this.setPfpj(pfpj);
            }
            default -> JOptionPane.showMessageDialog(null, "<html>O campo tipo de Conta so aceita os tipos cc [Conta Corrente]\n e cp [Conta Poupanca], por favor verifique se o campo estao corretamente preenchidos</html>");
        }
}

    public void sacar() {
        
    }
    
    
    public void depoitar(){
        
    }
}
