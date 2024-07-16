/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProjetoIntegrador.Etapa78.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

/**
 *
 * @author Murilo
 */
@Data
@Entity
@Table(name="Transacao", uniqueConstraints = {
    @UniqueConstraint(columnNames = "id")})
public class TransacaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private int contaOrigemId;
    
    private int conta_destino_id;
    
    private String alteracao_conta;
    
    private int valor;
}
