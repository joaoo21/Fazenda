/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import dao.DaoRaca;
import dao.DaoVaca;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import modelo.Vaca;
import tela.manutencao.ManutencaoVaca;
import java.util.List;

import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Raca;
/**
 *
 * @author Avell
 */
public class ControladorVaca {
        public static void inserir(ManutencaoVaca man){
        Vaca objeto = new Vaca();
        objeto.setOrigem(man.comOrigem.getSelectedIndex());
        objeto.setSituacao(man.comSituacao.getSelectedIndex());
        objeto.setNascimento(LocalDate.parse(man.jtfNascimento.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setRacaVaca((Raca)man.comRaca.getSelectedItem());
        objeto.setObservacao(man.jtfObservacao.getText());
        objeto.setMaeVaca((Vaca)man.comMae.getSelectedItem());
        
        
        boolean resultado = DaoVaca.inserir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
            if (man.listagem != null) {
     atualizarTabela(man.listagem.tabela);
     //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
}
    public static void alterar(ManutencaoVaca man){
        Vaca objeto = new Vaca();
        //definir todos os atributos
        objeto.setCodigo(Integer.parseInt(man.jtfCodigo.getText()));
    objeto.setOrigem(man.comOrigem.getSelectedIndex());
        objeto.setSituacao(man.comSituacao.getSelectedIndex());
        objeto.setNascimento(LocalDate.parse(man.jtfNascimento.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setRacaVaca((Raca)man.comRaca.getSelectedItem());
        objeto.setObservacao(man.jtfObservacao.getText());
        objeto.setMaeVaca((Vaca)man.comMae.getSelectedItem());

        
        boolean resultado = DaoVaca.alterar(objeto);
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
     public static void excluir(ManutencaoVaca man){
        Vaca objeto = new Vaca();
        objeto.setCodigo(Integer.parseInt(man.jtfCodigo.getText())); //só precisa definir a chave primeira
        
        boolean resultado = DaoVaca.excluir(objeto);
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
        modelo.addColumn("Brinco");
        modelo.addColumn("Origem");
        modelo.addColumn("Situação");
        modelo.addColumn("Nascimento");
        modelo.addColumn("Raça");
        modelo.addColumn("Observaçao");
        modelo.addColumn("Mãe");
        List<Vaca> resultados = DaoVaca.consultar();
        for (Vaca objeto : resultados) {
            Vector linha = new Vector();
            
            //definindo o conteúdo da tabela
            linha.add(objeto.getCodigo());
            linha.add(objeto.getOrigem());
            linha.add(objeto.getSituacao());
            linha.add(objeto.getNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            linha.add(objeto.getRacaVaca());
            linha.add(objeto.getObservacao());
            linha.add(objeto.getMaeVaca());
            modelo.addRow(linha); //adicionando a linha na tabela
        }
        tabela.setModel(modelo);
    }
     public static void atualizaCampos(ManutencaoVaca man, int pk){ 
        Vaca objeto = DaoVaca.consultar(pk);
        //Definindo os valores do campo na tela (um para cada atributo/campo)
        man.jtfCodigo.setText(objeto.getCodigo().toString());
        man.jtfObservacao.setText(objeto.getObservacao());
        man.jtfNascimento.setText(objeto.getNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        man.comRaca.setSelectedItem(objeto.getRacaVaca());
        man.comMae.setSelectedItem(objeto.getMaeVaca());
        man.comOrigem.setSelectedItem(objeto.getOrigem());
        man.comSituacao.setSelectedItem(objeto.getSituacao());
        
        man.jtfCodigo.setEnabled(false); //desabilitando o campo código
        man.btnAdicionar.setEnabled(false); //desabilitando o botão adicionar
    }
     
     public static void atualizaComboRaca(ManutencaoVaca man) {
        DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel(DaoRaca.consultar().toArray());
        man.comRaca.setModel(defaultComboBoxModel);
}
          public static void atualizaComboMae(ManutencaoVaca man) {
        DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel(DaoVaca.consultar().toArray());
        man.comMae.setModel(defaultComboBoxModel);
        man.comMae.addItem(null);
}
}
