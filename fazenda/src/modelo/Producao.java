/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Avell
 */
public class Producao {
private Integer codigo;
private Integer turno;
private LocalDate data;
private Integer total;
private Pessoa pessoaVaca;
private Vaca VacaProducao;
private String observacao;

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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Pessoa getPessoaVaca() {
        return pessoaVaca;
    }

    public void setPessoaVaca(Pessoa pessoaVaca) {
        this.pessoaVaca = pessoaVaca;
    }

    public Vaca getVacaProducao() {
        return VacaProducao;
    }

    public void setVacaProducao(Vaca VacaProducao) {
        this.VacaProducao = VacaProducao;
    }


    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Producao other = (Producao) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Producao{" + "turno=" + turno + ", data=" + data + ", VacaProducao=" + VacaProducao + '}';
    }


}
