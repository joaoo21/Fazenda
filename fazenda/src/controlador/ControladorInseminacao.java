/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import dao.DaoInseminacao;
import dao.DaoTouro;
import dao.DaoVaca;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import javax.swing.JOptionPane;
import modelo.Inseminacao;
import tela.manutencao.ManutencaoInseminacao;
import java.util.List;

import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Touro;
import modelo.Vaca;
/**
 *
 * @author Avell
 */
public class ControladorInseminacao {
        public static void inserir(ManutencaoInseminacao man){
        Inseminacao objeto = new Inseminacao();
        objeto.setVaca((Vaca) man.comVaca.getSelectedItem());
        objeto.setSituacao(man.comSituacao.getSelectedIndex());
        objeto.setData(LocalDate.parse(man.jtfData.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setTouro((Touro)man.comTouro.getSelectedItem());
        objeto.setObservacao(man.jtfObservacao.getText());
        
        
        boolean resultado = DaoInseminacao.inserir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
            if (man.listagem != null) {
     atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
}
    public static void alterar(ManutencaoInseminacao man){
        Inseminacao objeto = new Inseminacao();
        //definir todos os atributos
        objeto.setCodigo(Integer.parseInt(man.jtfCodigo.getText()));
    objeto.setVaca((Vaca) man.comVaca.getSelectedItem());
        objeto.setSituacao(man.comSituacao.getSelectedIndex());
        objeto.setData(LocalDate.parse(man.jtfData.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setTouro((Touro)man.comTouro.getSelectedItem());
        objeto.setObservacao(man.jtfObservacao.getText());

        
        boolean resultado = DaoInseminacao.alterar(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
            if (man.listagem != null) {
     atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }
     public static void excluir(ManutencaoInseminacao man){
        Inseminacao objeto = new Inseminacao();
        objeto.setCodigo(Integer.parseInt(man.jtfCodigo.getText())); //só precisa definir a chave primeira
        
        boolean resultado = DaoInseminacao.excluir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
            if (man.listagem != null) {
     atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }
     public static void atualizarTabela(JTable tabela) {
        DefaultTableModel modelo = new DefaultTableModel();
        //definindo o cabeçalho da tabela
        modelo.addColumn("Código");
        modelo.addColumn("Data da inseminação");
        modelo.addColumn("Situação");
        modelo.addColumn("Vaca");
        modelo.addColumn("Touro");
        modelo.addColumn("Observações");
        List<Inseminacao> resultados = DaoInseminacao.consultar();
        for (Inseminacao objeto : resultados) {
            Vector linha = new Vector();
            
            //definindo o conteúdo da tabela
            linha.add(objeto.getCodigo());
            linha.add(objeto.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            linha.add(objeto.getSituacao());
            linha.add(objeto.getVaca());
            linha.add(objeto.getTouro());
            linha.add(objeto.getObservacao());
            modelo.addRow(linha); //adicionando a linha na tabela
        }
        tabela.setModel(modelo);
    }
     public static void atualizaCampos(ManutencaoInseminacao man, int pk){ 
        Inseminacao objeto = DaoInseminacao.consultar(pk);
        //Definindo os valores do campo na tela (um para cada atributo/campo)
        man.jtfCodigo.setText(objeto.getCodigo().toString());
        man.jtfObservacao.setText(objeto.getObservacao());
        man.jtfData.setText(objeto.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        
       
        Date date =  java.sql.Date.valueOf(objeto.getData().plusMonths(9));
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
        String strDate = dateFormat.format(date);  
        
        man.jtfPrevisao.setText(strDate);
        
        man.comTouro.setSelectedItem(objeto.getTouro());
        man.comVaca.setSelectedItem(objeto.getVaca());
        man.comSituacao.setSelectedIndex(objeto.getSituacao());
        
        man.jtfCodigo.setEnabled(false);
        man.jtfPrevisao.setEnabled(false);//desabilitando o campo código
        man.btnAdicionar.setEnabled(false); //desabilitando o botão adicionar
    }
     
     public static void atualizaComboVaca(ManutencaoInseminacao man) {
        DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel(DaoVaca.consultar().toArray());
        man.comVaca.setModel(defaultComboBoxModel);
}
     
     public static void atualizaComboTouro(ManutencaoInseminacao man) {
        DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel(DaoTouro.consultar().toArray());
        man.comTouro.setModel(defaultComboBoxModel);
        man.comTouro.addItem(null);
}
}
