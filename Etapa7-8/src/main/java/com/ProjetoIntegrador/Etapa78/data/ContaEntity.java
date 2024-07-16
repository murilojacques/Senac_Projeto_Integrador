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
@Table(name="Conta", uniqueConstraints = {
    @UniqueConstraint(columnNames = "id")})
public class ContaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nome_usuario;
    
    private String endereco;
    
    private String login;
    
    private int senha;
    
    private int cpf;
    
    private int cnpj;

    private String tipo;

    private int saldo;

    private boolean status;
    
    private String pfpj;
}
