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
import modelo.Producao;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Avell
 */
public class DaoProducao {
     public static boolean inserir(Producao objeto) {
        String sql = "INSERT INTO Producao_leite (turno, data, total, obs, cod_pessoa, cod_vaca) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, objeto.getTurno());
            ps.setDate(2, Date.valueOf(objeto.getData()));
            ps.setInt(3, objeto.getTotal());
            ps.setString(4, objeto.getObservacao());
            ps.setInt(5, objeto.getPessoaVaca().getCodigo());
            ps.setInt(6, objeto.getVacaProducao().getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
       public static boolean alterar(Producao objeto) {
        String sql = "UPDATE Producao_leite SET turno = ?, data = ?, total = ?, obs = ?, cod_pessoa = ?, cod_vaca = ? WHERE codigo=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, objeto.getTurno());
            ps.setDate(2, Date.valueOf(objeto.getData()));
            ps.setInt(3, objeto.getTotal());
            ps.setString(4, objeto.getObservacao());
            ps.setInt(5, objeto.getPessoaVaca().getCodigo());
            ps.setInt(6, objeto.getVacaProducao().getCodigo());
            ps.setInt(7, objeto.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
         public static boolean excluir(Producao objeto) {
        String sql = "DELETE FROM Producao_leite WHERE codigo=?";
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
         
    public static List<Producao> consultar() {
        List<Producao> resultados = new ArrayList<>();
        //editar o SQL conforme a entidade
        String sql = "SELECT codigo, turno, data, total, obs, cod_pessoa, cod_vaca FROM Producao_leite";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Producao objeto = new Producao();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setCodigo(rs.getInt("codigo"));
                objeto.setTurno(rs.getInt("turno"));
                objeto.setData(rs.getDate("data").toLocalDate());
                objeto.setTotal(rs.getInt("total"));
                objeto.setObservacao(rs.getString("obs"));
                objeto.setPessoaVaca(DaoPessoa.consultar(rs.getInt("cod_pessoa")));
                objeto.setVacaProducao(DaoVaca.consultar(rs.getInt("cod_vaca")));
                
                resultados.add(objeto);//não mexa nesse, ele adiciona o objeto na lista
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
}
    
   public static Producao consultar(int primaryKey) {
        //editar o SQL conforme a entidade
        String sql = "SELECT codigo, turno, data, total, obs, cod_pessoa, cod_vaca FROM Producao_leite WHERE codigo=?";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, primaryKey);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Producao objeto = new Producao();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setCodigo(rs.getInt("codigo"));
                objeto.setTurno(rs.getInt("turno"));
                objeto.setData(rs.getDate("data").toLocalDate());
                objeto.setTotal(rs.getInt("total"));
                objeto.setObservacao(rs.getString("obs"));
                objeto.setPessoaVaca(DaoPessoa.consultar(rs.getInt("cod_pessoa")));
                objeto.setVacaProducao(DaoVaca.consultar(rs.getInt("cod_vaca")));
                return objeto;//não mexa nesse, ele adiciona o objeto na lista
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }  
}
