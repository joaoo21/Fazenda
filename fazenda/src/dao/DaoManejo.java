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
import modelo.Manejo;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Avell
 */
public class DaoManejo {
     public static boolean inserir(Manejo objeto) {
        String sql = "INSERT INTO Manejo (data, observacao) VALUES (?, ?)";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setDate(1, Date.valueOf(objeto.getData()));
            ps.setString(2, objeto.getObservacao());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
       public static boolean alterar(Manejo objeto) {
        String sql = "UPDATE Manejo SET data = ?, observacao = ? WHERE codigo=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setDate(1, Date.valueOf(objeto.getData()));
            ps.setString(2, objeto.getObservacao());
            ps.setInt(3, objeto.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
         public static boolean excluir(Manejo objeto) {
        String sql = "DELETE FROM Manejo WHERE codigo=?";
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
         
    public static List<Manejo> consultar() {
        List<Manejo> resultados = new ArrayList<>();
        //editar o SQL conforme a entidade
        String sql = "SELECT codigo, data, observacao FROM Manejo";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Manejo objeto = new Manejo();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setCodigo(rs.getInt("codigo"));
                objeto.setData(rs.getDate("data").toLocalDate());
                objeto.setObservacao(rs.getString("observacao"));
                
                resultados.add(objeto);//não mexa nesse, ele adiciona o objeto na lista
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
}
    
   public static Manejo consultar(int primaryKey) {
        //editar o SQL conforme a entidade
        String sql = "SELECT codigo, data, observacao FROM Manejo WHERE codigo=?";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, primaryKey);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Manejo objeto = new Manejo();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setCodigo(rs.getInt("codigo"));
                objeto.setData(rs.getDate("data").toLocalDate());
                objeto.setObservacao(rs.getString("observacao"));
                return objeto;//não mexa nesse, ele adiciona o objeto na lista
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }  
   
   
        public static boolean inserirVaca(Manejo objeto) {
        String sql = "INSERT INTO manejo_vaca (cod_vaca, cod_manejo) VALUES (?, ?)";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, objeto.getVacaManejo().getCodigo());
            ps.setInt(2, objeto.getCodigoManejo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
         public static boolean excluirVaca(Manejo objeto) {
        String sql = "DELETE FROM manejo_vaca WHERE cod_vaca=? AND cod_manejo=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, objeto.getVacaManejo().getCodigo());
            ps.setInt(2, objeto.getCodigoManejo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
         
    public static List<Manejo> consultarVaca() {
        List<Manejo> resultados = new ArrayList<>();
        //editar o SQL conforme a entidade
        String sql = "SELECT cod_vaca, cod_manejo FROM manejo_vaca";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Manejo objeto = new Manejo();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setVacaManejo(DaoVaca.consultar(rs.getInt("cod_vaca")));
                objeto.setCodigoManejo(rs.getInt("cod_manejo"));
                
                resultados.add(objeto);//não mexa nesse, ele adiciona o objeto na lista
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
}
    
   public static Manejo consultarVaca(int primaryKey) {
        //editar o SQL conforme a entidade
        String sql = "SELECT cod_vaca, cod_manejo FROM manejo_vaca";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, primaryKey);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Manejo objeto = new Manejo();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setVacaManejo(DaoVaca.consultar(rs.getInt("cod_vaca")));
                objeto.setCodigoManejo(rs.getInt("cod_manejo"));
                return objeto;//não mexa nesse, ele adiciona o objeto na lista
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }  
}

