/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import dao.DaoLactacao;
import dao.DaoVaca;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import modelo.Lactacao;
import tela.manutencao.ManutencaoLactacao;
import java.util.List;

import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Vaca;
/**
 *
 * @author Avell
 */
public class ControladorLactacao {
        public static void inserir(ManutencaoLactacao man){
        Lactacao objeto = new Lactacao();
        objeto.setInicio(LocalDate.parse(man.jtfInicio.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        if(man.jtfFim.getText() == ""){
        objeto.setFim(LocalDate.parse(man.jtfFim.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }
        objeto.setVaca((Vaca) man.comVaca.getSelectedItem());
        objeto.setObservacao(man.jtfObservacao.getText());

        
        boolean resultado = DaoLactacao.inserir(objeto);
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
    public static void alterar(ManutencaoLactacao man){
        Lactacao objeto = new Lactacao();
        //definir todos os atributos
        objeto.setCodigo(Integer.parseInt(man.jtfCodigo.getText()));
        objeto.setInicio(LocalDate.parse(man.jtfInicio.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        if(man.jtfFim.getText() == ""){
        objeto.setFim(LocalDate.parse(man.jtfFim.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }
        objeto.setVaca((Vaca) man.comVaca.getSelectedItem());
        objeto.setObservacao(man.jtfObservacao.getText());

        
        boolean resultado = DaoLactacao.alterar(objeto);
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
     public static void excluir(ManutencaoLactacao man){
        Lactacao objeto = new Lactacao();
        objeto.setCodigo(Integer.parseInt(man.jtfCodigo.getText())); //só precisa definir a chave primeira
        
        boolean resultado = DaoLactacao.excluir(objeto);
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
        modelo.addColumn("Início");
        modelo.addColumn("Fim");
        modelo.addColumn("Observação");
        modelo.addColumn("Vaca");
        List<Lactacao> resultados = DaoLactacao.consultar();
        for (Lactacao objeto : resultados) {
            Vector linha = new Vector();
            
            //definindo o conteúdo da tabela
            linha.add(objeto.getCodigo());
            linha.add(objeto.getInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            if(objeto.getFim() == null){
            linha.add(null);}
                else{
            linha.add(objeto.getFim().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                }
            linha.add(objeto.getObservacao());
            linha.add(objeto.getVaca());
            modelo.addRow(linha); //adicionando a linha na tabela
        }
        tabela.setModel(modelo);
    }
     public static void atualizaCampos(ManutencaoLactacao man, int pk){ 
        Lactacao objeto = DaoLactacao.consultar(pk);
        //Definindo os valores do campo na tela (um para cada atributo/campo)
        man.jtfCodigo.setText(objeto.getCodigo().toString());
        man.jtfInicio.setText(objeto.getInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        if(objeto.getFim() != null){
        man.jtfFim.setText(objeto.getFim().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));}
        man.jtfObservacao.setText(objeto.getObservacao());
        man.comVaca.setSelectedItem(objeto.getVaca());
        
        man.jtfCodigo.setEnabled(false); //desabilitando o campo código
        man.btnAdicionar.setEnabled(false); //desabilitando o botão adicionar
    }
     
     public static void atualizaComboVaca(ManutencaoLactacao man) {
        DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel(DaoVaca.consultar().toArray());
        man.comVaca.setModel(defaultComboBoxModel);
}
}
