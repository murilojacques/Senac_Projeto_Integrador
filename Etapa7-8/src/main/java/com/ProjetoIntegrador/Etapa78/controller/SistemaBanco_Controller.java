/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProjetoIntegrador.Etapa78.controller;

import com.ProjetoIntegrador.Etapa78.data.ContaEntity;
import com.ProjetoIntegrador.Etapa78.data.TransacaoEntity;
import com.ProjetoIntegrador.Etapa78.service.TransacaoService;
import com.ProjetoIntegrador.Etapa78.service.ContaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Murilo
 */
@Controller
public class SistemaBanco_Controller {
   
    @Autowired
    ContaService contaService;
    
    @Autowired
    TransacaoService transacaoService;
    
    private int id;
    private Integer i;
    
    
    @GetMapping("/PagLogin")
    public String PagLogin(Model model){
        
        model.addAttribute("conta", new ContaEntity());
        return "PagLogin";
    }
    
    @PostMapping("/ConfirmarLogin")
    public String ConfirmarLogin(@ModelAttribute("conta") ContaEntity conta, Model model){
        
        if(conta.getLogin().equals("abc") && conta.getSenha() == 124){
            List<ContaEntity> contas = contaService.ListarContas();
            model.addAttribute("Contas", contas);
            return "ListaContas";
        }
        
        
        ContaEntity c = contaService.ConfirmarLogin(conta.getLogin(), conta.getSenha());
        if(c != null){
        id = c.getId();
        //Adicionar os Model 
        List<TransacaoEntity> transacoes = transacaoService.ListarTransacoesPorId(id);
        model.addAttribute("Transacoes", transacoes);
        model.addAttribute("Transacao", new TransacaoEntity());
        model.addAttribute("V", new ContaEntity());
        return "PagCentral";
        }
        
        
        model.addAttribute("conta", new ContaEntity());
        model.addAttribute("msg", "Conta não Encontrada");
        return "PagLogin";
    }
    
    
    
    
    
    @GetMapping("/PagCriarConta")
    public String PagCriarConta(Model model){
        
        model.addAttribute("conta", new ContaEntity());
        return "PagCriarConta";
    }
    
    @PostMapping("/cadastrarConta")
    public String CadastrarConta(@ModelAttribute("conta") ContaEntity conta){
        
        ContaEntity c = contaService.ConfirmarLogin(conta.getLogin(), conta.getSenha());
         
        if(c != null){
          return "PagCriarConta";  
        }else{
            
            if(conta.getTipo().equals("cc")){
            conta.setSaldo(50);
            }
            else{
            conta.setSaldo(150);
            }
        conta.setId(null);
        conta.setStatus(true);
        contaService.cadastrarConta(conta);
        }
        
        return "PagLogin";
    }
    
    @GetMapping("/PagCentral")
    public String PagCentral(Model model){
        List<TransacaoEntity> transacoes = transacaoService.ListarTransacoesPorId(id);
        model.addAttribute("Transacoes", transacoes);
        model.addAttribute("Transacao", new TransacaoEntity());
        model.addAttribute("V", new ContaEntity());
        return "PagCentral";
    }
    
    
    @GetMapping("/PagTransacao")
    public String PagTransacao(Model model){
        List<TransacaoEntity> transacoes = transacaoService.ListarTransacoesPorId(id);
        model.addAttribute("Transacoes", transacoes);
        model.addAttribute("Transacao", new TransacaoEntity());
        return "PagTransacao";
    }
    
    
    
    
    
    
    
    
    
    @GetMapping("/MudarStatus")
    public String MudarStatus(Model model){
        ContaEntity conta = contaService.BuscarContaById(id);
        contaService.AtualizarStatus(conta);
        model.addAttribute("V", new ContaEntity());
        return "PagCentral";
    }
    
    @GetMapping("/deletarConta")
    public String DeletarConta(){
        contaService.DeletarConta(id);
        return "PagLogin";
    }
    
    
    @RequestMapping(value="/Sacar")
    public String Sacar(@ModelAttribute("V") ContaEntity v, Model model){
        ContaEntity conta = contaService.BuscarContaById(id);
        i = v.getSaldo();
        if(conta.getSaldo() >= i && conta.isStatus()){
            conta.setSaldo(conta.getSaldo() - i);
            contaService.AtualizarSaldo(id, conta);
            List<TransacaoEntity> transacoes = transacaoService.ListarTransacoesPorId(id);
            model.addAttribute("Transacoes", transacoes);
            model.addAttribute("Transacao", new TransacaoEntity());
            return "PagCentral";
        }
        else{
            model.addAttribute("msg1", "O Status da sua Conta esta como Fechada");
            return "PagCentral";
        }
        
    }
    
    
    @PostMapping("/Depositar")
    public String Depositar(@ModelAttribute("V") ContaEntity v, Model model){
        ContaEntity conta = contaService.BuscarContaById(id);
        if(conta.isStatus()){
            i = v.getSaldo();
            conta.setSaldo(conta.getSaldo() + i);
            contaService.AtualizarSaldo(id, conta); 
            List<TransacaoEntity> transacoes = transacaoService.ListarTransacoesPorId(id);
            model.addAttribute("Transacoes", transacoes);
            model.addAttribute("Transacao", new TransacaoEntity());
            return "PagCentral";
        }
        else{
            model.addAttribute("msg1", "O Status da sua Conta esta como Fechada");
            return "PagCentral";
        }
    }
    
    
    
    
    
    
    
    
    @PostMapping("/RealizarTransacao")
    public String RealizarTransacao(@ModelAttribute("Transacao") TransacaoEntity transacao, Model model){
        
        ContaEntity contaDestino = contaService.BuscarContaById(transacao.getConta_destino_id());
        ContaEntity contaAtual = contaService.BuscarContaById(id);
        
        
        if(contaDestino.getId() != null && contaDestino.getId() != id){
            
            if(contaAtual.getSaldo() >= transacao.getValor()){
                contaAtual.setSaldo(contaAtual.getSaldo() - transacao.getValor());
                contaService.AtualizarSaldo(id, contaAtual);
                
                contaDestino.setSaldo(contaDestino.getSaldo() + transacao.getValor());
                contaService.AtualizarSaldo(contaDestino.getId(), contaDestino);
                
                transacaoService.cadastrarTransacao(transacao, id);
            }
            else{
                List<TransacaoEntity> transacoes = transacaoService.ListarTransacoesPorId(id);
                model.addAttribute("Transacoes", transacoes);
                model.addAttribute("Transacao", new TransacaoEntity());
                model.addAttribute("msg", "Saldo Insuficiente");
                model.addAttribute("V", new ContaEntity());
                return "PagCentral";
            }

            
        }
        else{
            List<TransacaoEntity> transacoes = transacaoService.ListarTransacoesPorId(id);
            model.addAttribute("Transacoes", transacoes);
            model.addAttribute("Transacao", new TransacaoEntity());
            model.addAttribute("msg", "Não é possivel realizar Transacoes para a mesma Conta");
            model.addAttribute("V", new ContaEntity());
            return "PagCentral";
        }
       
       List<TransacaoEntity> transacoes = transacaoService.ListarTransacoesPorId(id);
       model.addAttribute("Transacoes", transacoes);
       model.addAttribute("Transacao", new TransacaoEntity());
       
       model.addAttribute("V", new ContaEntity());
       return "PagCentral"; 
    }
    
    
    
    
    
    @GetMapping("/ListaContas")
    public String PagListaConta(Model model){
        List<ContaEntity> contas = contaService.ListarContas();
        model.addAttribute("Contas", contas);
        return "ListaContas";
    }
}
