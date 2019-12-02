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
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Avell
 */
public class DaoVaca {
     public static boolean inserir(Vaca objeto) {
        String sql = "INSERT INTO Vaca (origem, situacao, nascimento, observacao, cod_raca, brinco_mae) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, objeto.getOrigem());
            ps.setInt(2, objeto.getSituacao());
            ps.setDate(3, Date.valueOf(objeto.getNascimento()));
            ps.setString(4, objeto.getObservacao());
            ps.setInt(5, objeto.getRacaVaca().getCodigo());
            if (objeto.getMaeVaca() == null){
                ps.setNull(6, Types.INTEGER);
            }else{
            ps.setInt(6, objeto.getMaeVaca().getCodigo());
            };
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
       public static boolean alterar(Vaca objeto) {
        String sql = "UPDATE Vaca SET origem = ?, situacao = ?, nascimento = ?, observacao = ?, cod_raca = ?, brinco_mae = ? WHERE brinco=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, objeto.getOrigem());
            ps.setInt(2, objeto.getSituacao());
            ps.setDate(3, Date.valueOf(objeto.getNascimento()));
            ps.setString(4, objeto.getObservacao());
            ps.setInt(5, objeto.getRacaVaca().getCodigo());
            if (objeto.getMaeVaca() == null){
                ps.setNull(6, Types.INTEGER);
            }else{
            ps.setInt(6, objeto.getMaeVaca().getCodigo());
            };
            ps.setInt(7, objeto.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
         public static boolean excluir(Vaca objeto) {
        String sql = "DELETE FROM Vaca WHERE brinco=?";
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
         
    public static List<Vaca> consultar() {
        List<Vaca> resultados = new ArrayList<>();
        //editar o SQL conforme a entidade
        String sql = "SELECT brinco, origem, situacao, nascimento, observacao, cod_raca, brinco_mae FROM Vaca";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vaca objeto = new Vaca();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setCodigo(rs.getInt("brinco"));
                objeto.setOrigem(rs.getInt("origem"));
                objeto.setSituacao(rs.getInt("situacao"));
                objeto.setNascimento(rs.getDate("nascimento").toLocalDate());
                objeto.setObservacao(rs.getString("observacao"));
                objeto.setRacaVaca(DaoRaca.consultar(rs.getInt("cod_raca")));
                objeto.setMaeVaca(DaoVaca.consultar(rs.getInt("brinco_mae")));
                
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
        String sql = "SELECT brinco, origem, situacao, nascimento, observacao, cod_raca, brinco_mae FROM Vaca WHERE brinco=?";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, primaryKey);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vaca objeto = new Vaca();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setCodigo(rs.getInt("brinco"));
                objeto.setOrigem(rs.getInt("origem"));
                objeto.setSituacao(rs.getInt("situacao"));
                objeto.setNascimento(rs.getDate("nascimento").toLocalDate());
                objeto.setObservacao(rs.getString("observacao"));
                objeto.setRacaVaca(DaoRaca.consultar(rs.getInt("cod_raca")));
                objeto.setMaeVaca(DaoVaca.consultar(rs.getInt("brinco_mae")));
                return objeto;//não mexa nesse, ele adiciona o objeto na lista
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }  
}
