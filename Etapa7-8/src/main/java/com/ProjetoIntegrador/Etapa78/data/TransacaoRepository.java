/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ProjetoIntegrador.Etapa78.data;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Murilo
 */
@Repository
public interface TransacaoRepository extends JpaRepository<TransacaoEntity, Integer>{
    List<TransacaoEntity> findByContaOrigemId(int contaOrigemId);
}
