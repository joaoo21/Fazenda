/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author T-Gamer
 */
public class Touro {
    
    private Integer codigo;
    private String nome;
    private Integer cod_raca;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCod_raca() {
        return cod_raca;
    }

    public void setCod_raca(Integer cod_raca) {
        this.cod_raca = cod_raca;
    }

    @Override
    public String toString() {
        return "Touro{" + "nome=" + nome + '}';
    }
    
    
}
