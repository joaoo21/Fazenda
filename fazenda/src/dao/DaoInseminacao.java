/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Inseminacao;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Avell
 */
public class DaoInseminacao {
     public static boolean inserir(Inseminacao objeto) {
        String sql = "INSERT INTO Inseminacao (data_inseminacao, observacao, situacao, cod_vaca, cod_touro) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setDate(1, Date.valueOf(objeto.getData()));
            ps.setString(2, objeto.getObservacao());
            ps.setInt(3, objeto.getSituacao());
            ps.setInt(4, objeto.getVaca().getCodigo());
            if (objeto.getTouro() == null){
                ps.setNull(5, Types.INTEGER);
            }else{
            ps.setInt(5, objeto.getTouro().getCodigo());
            };
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
       public static boolean alterar(Inseminacao objeto) {
        String sql = "UPDATE Inseminacao SET data_inseminacao = ?, observacao = ?, situacao = ?, cod_vaca = ?, cod_touro = ? WHERE codigo=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setDate(1, Date.valueOf(objeto.getData()));
            ps.setString(2, objeto.getObservacao());
            ps.setInt(3, objeto.getSituacao());
            ps.setInt(4, objeto.getVaca().getCodigo());
            if (objeto.getTouro() == null){
                ps.setNull(5, Types.INTEGER);
            }else{
            ps.setInt(5, objeto.getTouro().getCodigo());
            };
            ps.setInt(6, objeto.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
         public static boolean excluir(Inseminacao objeto) {
        String sql = "DELETE FROM Inseminacao WHERE codigo=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, objeto.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
         
    public static List<Inseminacao> consultar() {
        List<Inseminacao> resultados = new ArrayList<>();
        //editar o SQL conforme a entidade
        String sql = "SELECT codigo, data_inseminacao, observacao, situacao, cod_vaca, cod_touro FROM Inseminacao";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Inseminacao objeto = new Inseminacao();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setCodigo(rs.getInt("codigo"));
                objeto.setData(rs.getDate("data_inseminacao").toLocalDate());
                objeto.setObservacao(rs.getString("observacao"));
                objeto.setSituacao(rs.getInt("situacao"));
                objeto.setVaca(DaoVaca.consultar(rs.getInt("cod_vaca")));
                objeto.setTouro(DaoTouro.consultar(rs.getInt("cod_touro")));
                
                resultados.add(objeto);//não mexa nesse, ele adiciona o objeto na lista
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
}
    
   public static Inseminacao consultar(int primaryKey) {
        //editar o SQL conforme a entidade
        String sql = "SELECT codigo, data_inseminacao, observacao, situacao, cod_vaca, cod_touro FROM Inseminacao WHERE codigo=?";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, primaryKey);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Inseminacao objeto = new Inseminacao();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setCodigo(rs.getInt("codigo"));
                objeto.setData(rs.getDate("data_inseminacao").toLocalDate());
                objeto.setObservacao(rs.getString("observacao"));
                objeto.setSituacao(rs.getInt("situacao"));
                objeto.setVaca(DaoVaca.consultar(rs.getInt("cod_vaca")));
                objeto.setTouro(DaoTouro.consultar(rs.getInt("cod_touro")));
                return objeto;//não mexa nesse, ele adiciona o objeto na lista
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }  
}
