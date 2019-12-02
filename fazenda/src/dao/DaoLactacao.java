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
import modelo.Lactacao;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Avell
 */
public class DaoLactacao {
     public static boolean inserir(Lactacao objeto) {
        String sql = "INSERT INTO lactacao (inicio, fim, observacao, cod_vaca) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setDate(1, Date.valueOf(objeto.getInicio()));
            if (objeto.getFim() == null){
                ps.setNull(2, Types.DATE);
            }else{
            ps.setDate(2, Date.valueOf(objeto.getFim()));
            };
            ps.setString(3, objeto.getObservacao());
            ps.setInt(4, objeto.getVaca().getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
       public static boolean alterar(Lactacao objeto) {
        String sql = "UPDATE lactacao SET inicio = ?, fim = ?, observacao = ?, cod_vaca = ? WHERE codigo=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setDate(1, Date.valueOf(objeto.getInicio()));
            if (objeto.getFim() == null){
                ps.setNull(2, Types.DATE);
            }else{
            ps.setDate(2, Date.valueOf(objeto.getFim()));
            };
            ps.setString(3, objeto.getObservacao());
            ps.setInt(4, objeto.getVaca().getCodigo());
            ps.setInt(5, objeto.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
         public static boolean excluir(Lactacao objeto) {
        String sql = "DELETE FROM lactacao WHERE codigo=?";
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
         
    public static List<Lactacao> consultar() {
        List<Lactacao> resultados = new ArrayList<>();
        //editar o SQL conforme a entidade
        String sql = "SELECT codigo, inicio, fim, observacao, cod_vaca FROM lactacao";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Lactacao objeto = new Lactacao();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setCodigo(rs.getInt("codigo"));
                objeto.setInicio(rs.getDate("inicio").toLocalDate());
                if(rs.getDate("fim") != null){
                objeto.setFim(rs.getDate("fim").toLocalDate());}
                else{
                objeto.setFim(null);
                }
                objeto.setObservacao(rs.getString("observacao"));
                objeto.setVaca(DaoVaca.consultar(rs.getInt("cod_vaca")));
                
                resultados.add(objeto);//não mexa nesse, ele adiciona o objeto na lista
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
}
    
   public static Lactacao consultar(int primaryKey) {
        //editar o SQL conforme a entidade
        String sql = "SELECT codigo, inicio, fim, observacao, cod_vaca FROM lactacao WHERE codigo=?";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, primaryKey);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Lactacao objeto = new Lactacao();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setCodigo(rs.getInt("codigo"));
                objeto.setInicio(rs.getDate("inicio").toLocalDate());
                if(rs.getDate("fim") != null){
                objeto.setFim(rs.getDate("fim").toLocalDate());}
                else{
                objeto.setFim(null);
                }
                objeto.setObservacao(rs.getString("observacao"));
                objeto.setVaca(DaoVaca.consultar(rs.getInt("cod_vaca")));
                return objeto;//não mexa nesse, ele adiciona o objeto na lista
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }  
}
