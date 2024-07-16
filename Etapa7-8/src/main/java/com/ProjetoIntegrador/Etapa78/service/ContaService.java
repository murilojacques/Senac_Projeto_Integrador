/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProjetoIntegrador.Etapa78.service;

import com.ProjetoIntegrador.Etapa78.data.ContaEntity;
import com.ProjetoIntegrador.Etapa78.data.ContaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Murilo
 */
@Service
public class ContaService {
     @Autowired
    ContaRepository contaRepository;
    
    
    public ContaEntity cadastrarConta(ContaEntity con){
        con.setId(null);
        contaRepository.save(con);
        return con;
    }
    
    
    
    public List<ContaEntity> ListarContas(){
        List<ContaEntity> contas = contaRepository.findAll();
        return contas;
    }
    
    
    public ContaEntity ConfirmarLogin(String login, int senha){
        ContaEntity conta;
        conta = contaRepository.findByLoginAndSenha(login, senha);
        return conta;
    }
    
    public void AtualizarStatus(ContaEntity conta){
        System.out.println(conta.isStatus());
        if(conta.isStatus() == true){
            conta.setStatus(false);
            System.out.println(conta.isStatus());
            contaRepository.save(conta);
        }
        else{
            conta.setStatus(true);
            System.out.println(conta.isStatus());
            contaRepository.save(conta);
        }
    }
    
    
    public void AtualizarSaldo(Integer id, ContaEntity conta){
            ContaEntity c = BuscarContaById(id);
            c.setSaldo(conta.getSaldo());
            contaRepository.save(conta);  
    }
    
    
    
    public ContaEntity BuscarContaById(Integer id){
        return contaRepository.findById(id).orElse(null);
    }
    
    
    public void DeletarConta(int id){
        contaRepository.deleteById(id);
    }
}
