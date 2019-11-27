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
public class Producao {
    private Integer codigo;
    private Integer turno;
    private Double total;
    private LocalDate data;
    private String obs;
    private Pessoa cod_pessoa;
    private Vaca cod_vaca;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getTurno() {
        return turno;
    }

    public void setTurno(Integer turno) {
        this.turno = turno;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Pessoa getCod_pessoa() {
        return cod_pessoa;
    }

    public void setCod_pessoa(Pessoa cod_pessoa) {
        this.cod_pessoa = cod_pessoa;
    }

    public Vaca getCod_vaca() {
        return cod_vaca;
    }

    public void setCod_vaca(Vaca cod_vaca) {
        this.cod_vaca = cod_vaca;
    }

    @Override
    public String toString() {
        return "Producao{" + "codigo=" + codigo + '}';
    }
    
    
}
    