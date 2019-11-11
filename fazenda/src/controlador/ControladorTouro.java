/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import tela.manutencao.ManutencaoTouro;
import dao.DaoTouro;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import modelo.Touro;
import java.util.List;

import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author T-Gamer
 */
public class ControladorTouro {

public static void inserir(ManutencaoTouro man){
        Touro objeto = new Touro();
        
        objeto.setNome(man.jtfNome.getText());
        objeto.setCod_raca(Integer.parseInt(man.jtfCod_raca.getText()));
        objeto.setCodigo(Integer.parseInt(man.jtfCodigo.getText()));
       
         
        boolean resultado = DaoTouro.inserir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");

        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
}

public static void alterar(ManutencaoTouro man){
        Touro objeto = new Touro();
        //definir todos os atributos
        objeto.setNome(man.jtfNome.getText());
        objeto.setCod_raca(Integer.parseInt(man.jtfCod_raca.getText()));
        objeto.setCodigo(Integer.parseInt(man.jtfCodigo.getText()));
       
        
        boolean resultado = DaoTouro.alterar(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
//fechar a tela da manutenção
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }

 public static void excluir(ManutencaoTouro man){
        Touro objeto = new Touro();
        objeto.setCodigo(Integer.parseInt(man.jtfCodigo.getText())); //só precisa definir a chave primeira
        
        boolean resultado = DaoTouro.excluir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
//fechar a tela da manutenção
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }
 
 public static void atualizarTabela(JTable tabela) {
        DefaultTableModel modelo = new DefaultTableModel();
        //definindo o cabeçalho da tabela
        modelo.addColumn("Código");
        modelo.addColumn("Código da Raça");
        modelo.addColumn("Nome");

        
        List<Touro> resultados = DaoTouro.consultar();
        for (Touro objeto : resultados) {
            Vector linha = new Vector();
            
            //definindo o conteúdo da tabela
            linha.add(objeto.getCodigo());
            linha.add(objeto.getCod_raca());
            linha.add(objeto.getNome());

            
            modelo.addRow(linha); //adicionando a linha na tabela
        }
        tabela.setModel(modelo);
    }
    public static void atualizaCampos(ManutencaoTouro man, int pk){ 
        Touro objeto = DaoTouro.consultar(pk);
        //Definindo os valores do campo na tela (um para cada atributo/campo)
        man.jtfCodigo.setText(objeto.getCodigo().toString());        
        man.jtfCod_raca.setText(objeto.getCod_raca().toString());
        man.jtfNome.setText(objeto.getNome());
       
        
        man.jtfCodigo.setEnabled(false); //desabilitando o campo código
        man.btnAdicionar.setEnabled(false); //desabilitando o botão adicionar
    }
}