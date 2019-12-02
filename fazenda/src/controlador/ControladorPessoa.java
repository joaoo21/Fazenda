/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import dao.DaoPessoa;
import javax.swing.JOptionPane;
import modelo.Pessoa;
import tela.manutencao.ManutencaoPessoa;
import java.util.List;

import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Avell
 */
public class ControladorPessoa {
        public static void inserir(ManutencaoPessoa man){
        Pessoa objeto = new Pessoa();
        objeto.setNome(man.jtfNome.getText());
        objeto.setEmail(man.jtfEmail.getText());
        objeto.setUsuario(man.jtfUsuario.getText());
        objeto.setSenha(man.jtfSenha.getText());
        
        boolean resultado = DaoPessoa.inserir(objeto);
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
    public static void alterar(ManutencaoPessoa man){
        Pessoa objeto = new Pessoa();
        //definir todos os atributos
        objeto.setCodigo(Integer.parseInt(man.jtfCodigo.getText()));
        objeto.setNome(man.jtfNome.getText());
        objeto.setEmail(man.jtfEmail.getText());
        objeto.setUsuario(man.jtfUsuario.getText());
        objeto.setSenha(man.jtfSenha.getText());

        
        boolean resultado = DaoPessoa.alterar(objeto);
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
     public static void excluir(ManutencaoPessoa man){
        Pessoa objeto = new Pessoa();
        objeto.setCodigo(Integer.parseInt(man.jtfCodigo.getText())); //só precisa definir a chave primeira
        
        boolean resultado = DaoPessoa.excluir(objeto);
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
        modelo.addColumn("Nome");
        modelo.addColumn("Email");
        modelo.addColumn("Usuario");
        modelo.addColumn("Senha");
        List<Pessoa> resultados = DaoPessoa.consultar();
        for (Pessoa objeto : resultados) {
            Vector linha = new Vector();
            
            //definindo o conteúdo da tabela
            linha.add(objeto.getCodigo());
            linha.add(objeto.getNome());
            linha.add(objeto.getEmail());
            linha.add(objeto.getUsuario());
            linha.add(objeto.getSenha());
            modelo.addRow(linha); //adicionando a linha na tabela
        }
        tabela.setModel(modelo);
    }
     public static void atualizaCampos(ManutencaoPessoa man, int pk){ 
        Pessoa objeto = DaoPessoa.consultar(pk);
        //Definindo os valores do campo na tela (um para cada atributo/campo)
        man.jtfCodigo.setText(objeto.getCodigo().toString());
        man.jtfNome.setText(objeto.getNome());
        man.jtfEmail.setText(objeto.getEmail());
        man.jtfUsuario.setText(objeto.getUsuario());
        
        
        man.jtfCodigo.setEnabled(false); //desabilitando o campo código
        man.btnAdicionar.setEnabled(false); //desabilitando o botão adicionar
    }
     
}
