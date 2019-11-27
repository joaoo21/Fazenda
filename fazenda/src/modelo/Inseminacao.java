/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.time.LocalDate;

/**
 *
 * @author Administrador
 */
public class Inseminacao {
    
    private Integer codigo;
    private LocalDate data_inseminacao;
    private String obs;
    private LocalDate data_parto;
    private Integer situacao;
    private Touro cod_touro;
    private Raca cod_raca;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public LocalDate getData_inseminacao() {
        return data_inseminacao;
    }

    public void setData_inseminacao(LocalDate data_inseminacao) {
        this.data_inseminacao = data_inseminacao;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public LocalDate getData_parto() {
        return data_parto;
    }

    public void setData_parto(LocalDate data_parto) {
        this.data_parto = data_parto;
    }

    public Integer getSituacao() {
        return situacao;
    }

    public void setSituacao(Integer situacao) {
        this.situacao = situacao;
    }

    public Touro getCod_touro() {
        return cod_touro;
    }

    public void setCod_touro(Touro cod_touro) {
        this.cod_touro = cod_touro;
    }

    public Raca getCod_raca() {
        return cod_raca;
    }

    public void setCod_raca(Raca cod_raca) {
        this.cod_raca = cod_raca;
    }

    @Override
    public String toString() {
        return "Inseminacao{" + "codigo=" + codigo + '}';
    }
    
    
    
}
