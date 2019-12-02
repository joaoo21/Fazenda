/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.time.LocalDate;

/**
 *
 * @author Avell
 */
public class Manejo {
    private Integer codigo;
    private LocalDate data;
    private String observacao;
    private Vaca VacaManejo;
    private Integer codigoManejo;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return "Manejo{" + "codigo=" + codigo + ", data=" + data + '}';
    }

    public Vaca getVacaManejo() {
        return VacaManejo;
    }

    public void setVacaManejo(Vaca VacaManejo) {
        this.VacaManejo = VacaManejo;
    }

    public Integer getCodigoManejo() {
        return codigoManejo;
    }

    public void setCodigoManejo(Integer codigoManejo) {
        this.codigoManejo = codigoManejo;
    }



    
    
}
