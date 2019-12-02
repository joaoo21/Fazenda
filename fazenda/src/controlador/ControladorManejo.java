/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import dao.DaoManejo;
import dao.DaoVaca;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import modelo.Manejo;
import tela.manutencao.ManutencaoManejo;
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
public class ControladorManejo {
        public static void inserir(ManutencaoManejo man){
        Manejo objeto = new Manejo();
        objeto.setData(LocalDate.parse(man.jtfData.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setObservacao(man.jtfObservacao.getText());
        
        boolean resultado = DaoManejo.inserir(objeto);
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
    public static void alterar(ManutencaoManejo man){
        Manejo objeto = new Manejo();
        //definir todos os atributos
        objeto.setCodigo(Integer.parseInt(man.jtfCodigo.getText()));
        objeto.setData(LocalDate.parse(man.jtfData.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setObservacao(man.jtfObservacao.getText());
        
        boolean resultado = DaoManejo.alterar(objeto);
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
     public static void excluir(ManutencaoManejo man){
        Manejo objeto = new Manejo();
        objeto.setCodigo(Integer.parseInt(man.jtfCodigo.getText())); //só precisa definir a chave primeira
        
        boolean resultado = DaoManejo.excluir(objeto);
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
        modelo.addColumn("Data");
        modelo.addColumn("Observação");
        List<Manejo> resultados = DaoManejo.consultar();
        for (Manejo objeto : resultados) {
            Vector linha = new Vector();
            
            //definindo o conteúdo da tabela
            linha.add(objeto.getCodigo());
            linha.add(objeto.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            linha.add(objeto.getObservacao());
            modelo.addRow(linha); //adicionando a linha na tabela
        }
        tabela.setModel(modelo);
    }
     public static void atualizaCampos(ManutencaoManejo man, int pk){ 
        Manejo objeto = DaoManejo.consultar(pk);
        //Definindo os valores do campo na tela (um para cada atributo/campo)
        man.jtfCodigo.setText(objeto.getCodigo().toString());
        man.jtfData.setText(objeto.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        man.jtfObservacao.setText(objeto.getObservacao());
        
        man.jtfCodigo.setEnabled(false); //desabilitando o campo código
        man.btnAdicionar.setEnabled(false); //desabilitando o botão adicionar
    }
     
     
     
     
             public static void inserirVaca(ManutencaoManejo man){
        Manejo objeto = new Manejo();
        objeto.setCodigoManejo(Integer.parseInt(man.jtfCodigo.getText()));
        objeto.setVacaManejo((Vaca)man.comVaca.getSelectedItem());
        
        boolean resultado = DaoManejo.inserirVaca(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
         atualizarTabelaVaca(man.tabelaVaca);}}
             
                  
             public static void atualizarTabelaVaca(JTable tabelaVaca) {
        DefaultTableModel modelo = new DefaultTableModel();
        //definindo o cabeçalho da tabela
        modelo.addColumn("Manejo");
        modelo.addColumn("Vaca");
        List<Manejo> resultados = DaoManejo.consultarVaca();
        for (Manejo objeto : resultados) {
            Vector linha = new Vector();
            
            //definindo o conteúdo da tabela
            linha.add(objeto.getCodigoManejo());
            linha.add(objeto.getVacaManejo());
            modelo.addRow(linha); //adicionando a linha na tabela
        }
        tabelaVaca.setModel(modelo);
    }
             
             public static void atualizaComboVaca(ManutencaoManejo man) {
        DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel(DaoVaca.consultar().toArray());
        man.comVaca.setModel(defaultComboBoxModel);
}

    public static void excluirVaca(ManutencaoManejo man) {
        Manejo objeto = new Manejo();
        objeto.setVacaManejo((Vaca)man.comVaca.getSelectedItem());
        objeto.setCodigoManejo(Integer.parseInt(man.jtfCodigo.getText()));
 
        boolean resultado = DaoManejo.excluirVaca(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
         atualizarTabelaVaca(man.tabelaVaca);}}



}
