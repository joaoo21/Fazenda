/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.time.LocalDate;



/**
 *
 * @author T-Gamer
 */
public class Vaca {
    
    private Integer brinco;
    private Integer situacao;
    private Integer origem;
    private LocalDate nascimento;
    private String obs;
    private Integer cod_raca;
    private Integer brinco_mae;

    public Integer getBrinco() {
        return brinco;
    }

    public void setBrinco(Integer brinco) {
        this.brinco = brinco;
    }

    public Integer getSituacao() {
        return situacao;
    }

    public void setSituacao(Integer situacao) {
        this.situacao = situacao;
    }

    public Integer getOrigem() {
        return origem;
    }

    public void setOrigem(Integer origem) {
        this.origem = origem;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Integer getCod_raca() {
        return cod_raca;
    }

    public void setCod_raca(Integer cod_raca) {
        this.cod_raca = cod_raca;
    }

    public Integer getBrinco_mae() {
        return brinco_mae;
    }

    public void setBrinco_mae(Integer brinco_mae) {
        this.brinco_mae = brinco_mae;
    }

    @Override
    public String toString() {
        return "Vaca{" + "brinco=" + brinco + '}';
    }
    
    
}
