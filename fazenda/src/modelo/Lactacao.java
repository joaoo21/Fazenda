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
public class Lactacao {
    private Integer codigo;
    private LocalDate inicio;
    private LocalDate fim;
    private String observacao;
    private Vaca Vaca;

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

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }


    @Override
    public String toString() {
        return "Lactacao{" + "inicio=" + inicio + ", Vaca=" + Vaca + '}';
    }

    public Vaca getVaca() {
        return Vaca;
    }

    public void setVaca(Vaca Vaca) {
        this.Vaca = Vaca;
    }

    
    
}
