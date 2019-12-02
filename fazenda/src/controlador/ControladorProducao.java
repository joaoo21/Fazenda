/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import dao.DaoPessoa;
import dao.DaoProducao;
import dao.DaoVaca;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import modelo.Producao;
import tela.manutencao.ManutencaoProducao;
import java.util.List;

import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Pessoa;
import modelo.Vaca;
/**
 *
 * @author Avell
 */
public class ControladorProducao {
        public static void inserir(ManutencaoProducao man){
        Producao objeto = new Producao();
        objeto.setTurno(man.comTurno.getSelectedIndex());
        objeto.setData(LocalDate.parse(man.jtfData.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setTotal(Integer.parseInt(man.jtfTotal.getText()));
        objeto.setObservacao(man.jtfObservacao.getText());
        objeto.setVacaProducao((Vaca) man.comVaca.getSelectedItem());
        objeto.setPessoaVaca((Pessoa) man.comPessoa.getSelectedItem());
        
        boolean resultado = DaoProducao.inserir(objeto);
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
    public static void alterar(ManutencaoProducao man){
        Producao objeto = new Producao();
        //definir todos os atributos
        objeto.setCodigo(Integer.parseInt(man.jtfCodigo.getText()));
        objeto.setTurno(man.comTurno.getSelectedIndex());
        objeto.setData(LocalDate.parse(man.jtfData.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setTotal(Integer.parseInt(man.jtfTotal.getText()));
        objeto.setObservacao(man.jtfObservacao.getText());
        objeto.setVacaProducao((Vaca) man.comVaca.getSelectedItem());
        objeto.setPessoaVaca((Pessoa) man.comPessoa.getSelectedItem());

        
        boolean resultado = DaoProducao.alterar(objeto);
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
     public static void excluir(ManutencaoProducao man){
        Producao objeto = new Producao();
        objeto.setCodigo(Integer.parseInt(man.jtfCodigo.getText())); //só precisa definir a chave primeira
        
        boolean resultado = DaoProducao.excluir(objeto);
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
        modelo.addColumn("Codigo");
        modelo.addColumn("Turno");
        modelo.addColumn("Data");
        modelo.addColumn("Total");
        modelo.addColumn("Observação");
        modelo.addColumn("Pessoa");
        modelo.addColumn("Vaca");
        List<Producao> resultados = DaoProducao.consultar();
        for (Producao objeto : resultados) {
            Vector linha = new Vector();
            
            //definindo o conteúdo da tabela
            linha.add(objeto.getCodigo());
            linha.add(objeto.getTurno());
            linha.add(objeto.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            linha.add(objeto.getTotal());
            linha.add(objeto.getObservacao());
            linha.add(objeto.getPessoaVaca());
            linha.add(objeto.getVacaProducao());
            modelo.addRow(linha); //adicionando a linha na tabela
        }
        tabela.setModel(modelo);
    }
     public static void atualizaCampos(ManutencaoProducao man, int pk){ 
        Producao objeto = DaoProducao.consultar(pk);
        //Definindo os valores do campo na tela (um para cada atributo/campo)
        man.jtfCodigo.setText(objeto.getCodigo().toString());
        man.jtfData.setText(objeto.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        man.jtfTotal.setText(objeto.getTotal().toString());
        man.jtfObservacao.setText(objeto.getObservacao());
        man.comVaca.setSelectedItem(objeto.getPessoaVaca());
        man.comPessoa.setSelectedItem(objeto.getVacaProducao());
        man.comTurno.setSelectedIndex(objeto.getTurno());
        
        man.jtfCodigo.setEnabled(false); //desabilitando o campo código
        man.btnAdicionar.setEnabled(false); //desabilitando o botão adicionar
    }
     
     public static void atualizaComboVaca(ManutencaoProducao man) {
        DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel(DaoVaca.consultar().toArray());
        man.comVaca.setModel(defaultComboBoxModel);
}
     public static void atualizaComboPessoa(ManutencaoProducao man) {
        DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel(DaoPessoa.consultar().toArray());
        man.comPessoa.setModel(defaultComboBoxModel);
}
}
