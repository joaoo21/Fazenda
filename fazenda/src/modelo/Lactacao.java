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
public class Lactacao {
    private Integer codigo;
    private LocalDate inicio;
    private LocalDate fim;
    private Vaca cod_vaca;
    private String obs;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFim() {
        return fim;
    }

    public void setFim(LocalDate fim) {
        this.fim = fim;
    }

    public Vaca getCod_vaca() {
        return cod_vaca;
    }

    public void setCod_vaca(Vaca cod_vaca) {
        this.cod_vaca = cod_vaca;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    @Override
    public String toString() {
        return "Lactacao{" + "codigo=" + codigo + '}';
    }
    
    
}
