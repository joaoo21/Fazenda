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
import modelo.Vaca;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author T-Gamer
 */
public class DaoVaca {
     public static boolean inserir(Vaca objeto) {
        String sql = "INSERT INTO vaca (brinco, situacao, origem, nascimento, obs, cod_raca, brinco_mae) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, objeto.getBrinco());
            ps.setInt(2, objeto.getSituacao());
            ps.setInt(3, objeto.getOrigem());
            ps.setDate(4, Date.valueOf(objeto.getNascimento()));
            ps.setString(5, objeto.getObs());
            ps.setInt(6, objeto.getCod_raca());
            ps.setInt(7, objeto.getBrinco_mae());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
     
       public static boolean alterar(Vaca objeto) {
        String sql = "UPDATE vaca SET brinco_mae = ?, situacao = ?, origem = ?, nascimento = ?, obs = ?, cod_raca = ? WHERE brinco=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            
            ps.setInt(1, objeto.getBrinco_mae());            
            ps.setInt(2, objeto.getSituacao());
            ps.setInt(3, objeto.getOrigem());
            ps.setDate(4, Date.valueOf(objeto.getNascimento()));
            ps.setString(5, objeto.getObs());
            ps.setInt(6, objeto.getCod_raca());
            ps.setInt(7, objeto.getBrinco());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
       
         public static boolean excluir(Vaca objeto) {
        String sql = "DELETE FROM vaca WHERE brinco=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, objeto.getBrinco());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
         
         public static List<Vaca> consultar() {
        List<Vaca> resultados = new ArrayList<>();
        //editar o SQL conforme a entidade
        String sql = "SELECT brinco, situacao, origem, nascimento, obs, cod_raca, brinco_mae FROM vaca";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vaca objeto = new Vaca();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setBrinco(rs.getInt("brinco"));
                objeto.setSituacao(rs.getInt("situacao"));
                objeto.setOrigem(rs.getInt("origem"));
                objeto.setNascimento(rs.getDate("nascimento").toLocalDate());
                objeto.setObs(rs.getString("obs"));
                objeto.setCod_raca(rs.getInt("cod_raca"));
                objeto.setBrinco_mae(rs.getInt("brinco_mae"));
                
                resultados.add(objeto);//não mexa nesse, ele adiciona o objeto na lista
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
}
         
         public static Vaca consultar(int primaryKey) {
        //editar o SQL conforme a entidade
        String sql = "SELECT brinco, situacao, origem, nascimento, obs, cod_raca, brinco_mae FROM vaca";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, primaryKey);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vaca objeto = new Vaca();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setBrinco(rs.getInt("brinco"));
                objeto.setSituacao(rs.getInt("situacao"));
                objeto.setOrigem(rs.getInt("origem"));
                objeto.setNascimento(rs.getDate("nascimento").toLocalDate());
                objeto.setObs(rs.getString("obs"));
                objeto.setCod_raca(rs.getInt("cod_raca"));
                objeto.setBrinco_mae(rs.getInt("brinco_mae"));
                return objeto;//não mexa nesse, ele adiciona o objeto na lista
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    } 
}
