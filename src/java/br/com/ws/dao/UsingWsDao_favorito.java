/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ws.dao;

import static br.com.ws.Factory.Connect.getConnection;
import br.com.ws.domain.UsingWsDomain;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;

/**
 *
 * @author Web People
 */
public class UsingWsDao_favorito {
    
        
        public String clienteFavorito(String dataUser){
        try {
            Connection connection = getConnection();
            JSONArray js = new JSONArray(dataUser);
            String sql = "select distinct * from _using.cliente_favorito where cd_user = ? and nr_cpf_cnpj = ?;";
            String sqlFilial = "select distinct * from _using.filial_favorito where cd_user = ? and nr_cnpj_filial = ?;";
            PreparedStatement stmt = connection.prepareStatement(sql);
            PreparedStatement stmt2 = connection.prepareStatement(sqlFilial);
            stmt.setString(1, js.getString(0));
            stmt.setString(2, js.getString(1));
            
            stmt2.setString(1, js.getString(0));
            stmt2.setString(2, js.getString(1));
            
            ResultSet rs = stmt.executeQuery();
            ResultSet rs2 = stmt2.executeQuery();
            if (rs.next()) {
                return "t";
            }
            if (rs2.next()) {
                return "t";
            }
            
            return "f";
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsingWsDao_rating.class.getName()).log(Level.SEVERE, null, ex);
            return "Erro: " + ex;
        }
        }
        
         public String favoritar(String dataUser){
        try {
            Connection connection = getConnection();
            JSONArray js = new JSONArray(dataUser);
            String sql = "select distinct nm_razao_social from _using.cliente where nr_cpf_cnpj = ?;";
            String sqlFilial = "select distinct nr_cnpj_filial from _using.filiais where nr_cnpj_filial = ?;";
            PreparedStatement stmt = connection.prepareStatement(sql);
            PreparedStatement stmt2 = connection.prepareStatement(sqlFilial);
            stmt.setString(1, js.getString(1));
           
            stmt2.setString(1, js.getString(1));
            
            ResultSet rs = stmt.executeQuery();
            ResultSet rs2 = stmt2.executeQuery();
            if (rs.next()) {
                String insert = "INSERT INTO _using.cliente_favorito(cd_user, nr_cpf_cnpj) VALUES(?, ?);";
                PreparedStatement insertStmt = connection.prepareStatement(insert);
                insertStmt.setString(1, js.getString(0));
                insertStmt.setString(2, js.getString(1));
                
                insertStmt.execute();
                return "cliente";
            }else if (rs2.next()) {
                String insert = "INSERT INTO _using.filial_favorito (cd_user, nr_cnpj_filial) VALUES(?, ?);";
                PreparedStatement insertStmt = connection.prepareStatement(insert);
                insertStmt.setString(1, js.getString(0));
                insertStmt.setString(2, js.getString(1));
                
                insertStmt.execute();
                return "filial";
            }
            
            return "nothing";
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsingWsDao_rating.class.getName()).log(Level.SEVERE, null, ex);
            return "Erro: " + ex;
        }
        }
         
          public String deletarFavorito(String dataUser){
        try {
            Connection connection = getConnection();
            JSONArray js = new JSONArray(dataUser);
            String sql = "select distinct nm_razao_social FROM _using.cliente where nr_cpf_cnpj = ?;";
            String sqlFilial = "select distinct nr_cnpj_filial FROM _using.filiais where nr_cnpj_filial = ?;";
            PreparedStatement stmt = connection.prepareStatement(sql);
            PreparedStatement stmt2 = connection.prepareStatement(sqlFilial);
            stmt.setString(1, js.getString(1));
           
            stmt2.setString(1, js.getString(1));
            
            ResultSet rs = stmt.executeQuery();
            ResultSet rs2 = stmt2.executeQuery();
            if (rs.next()) {
                String insert = "DELETE FROM _using.cliente_favorito WHERE nr_cpf_cnpj = ?;";
                PreparedStatement insertStmt = connection.prepareStatement(insert);
                insertStmt.setString(1, js.getString(1));
                
                insertStmt.execute();
                return "cliente";
            }else if (rs2.next()) {
                String insert = "DELETE FROM _using.filial_favorito WHERE nr_cnpj_filial = ?;";
                PreparedStatement insertStmt = connection.prepareStatement(insert);
                insertStmt.setString(1, js.getString(1));
                
                insertStmt.execute();
                return "filial";
            }
            
            return "nothing";
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsingWsDao_rating.class.getName()).log(Level.SEVERE, null, ex);
            return "Erro: " + ex;
        }
        }
          
           public List<UsingWsDomain> selectClientesFavoritos(String dataUser){
        try {
            Connection connection = getConnection();
            
            String sql = "SELECT cliente.link_logotipo_cliente, cliente.nr_cpf_cnpj FROM _using.cliente \n" +
            "INNER JOIN _using.cliente_favorito ON _using.cliente.nr_cpf_cnpj = _using.cliente_favorito.nr_cpf_cnpj\n" +
            "WHERE cd_user = ?";
            
            PreparedStatement stmt = connection.prepareStatement(sql);
           
            stmt.setString(1, dataUser);
           
           
            
            ResultSet rs = stmt.executeQuery();
            List<UsingWsDomain> lista = new ArrayList<>();
            while (rs.next()) {
                UsingWsDomain u = new UsingWsDomain();
                
                u.setNr_cpf_cnpj(rs.getString("nr_cpf_cnpj"));
                u.setLink_logotipo_cliente(rs.getString("link_logotipo_cliente"));
                lista.add(u);
            }
            
            return lista;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsingWsDao_rating.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        }
           public List<UsingWsDomain> selectFiliaisFavoritos(String dataUser){
        try {
            Connection connection = getConnection();
            
            String sql = "SELECT filiais.link_logotipo_filial, filiais.nr_cnpj_filial FROM _using.filiais \n" +
            "INNER JOIN _using.filial_favorito ON _using.filiais.nr_cnpj_filial = _using.filial_favorito.nr_cnpj_filial\n" +
            "WHERE cd_user = ?";
            
            PreparedStatement stmt = connection.prepareStatement(sql);
           
            stmt.setString(1, dataUser);
           
           
            
            ResultSet rs = stmt.executeQuery();
            List<UsingWsDomain> lista = new ArrayList<>();
            while (rs.next()) {
                UsingWsDomain u = new UsingWsDomain();
                
                u.setNr_cpf_cnpj(rs.getString("nr_cnpj_filial"));
                u.setLink_logotipo_cliente(rs.getString("link_logotipo_filial"));
                lista.add(u);
            }
            
            return lista;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsingWsDao_rating.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        }
           
            public List<UsingWsDomain> favFilter(String dataUser){
        try {
            Connection connection = getConnection();
            
            JSONArray js = new JSONArray(dataUser);
            
            String sql = "SELECT distinct cliente.link_logotipo_cliente, cliente.nr_cpf_cnpj FROM _using.produto\n" +
            "INNER JOIN _using.cliente ON _using.produto.cnpj_cliente = _using.cliente.nr_cpf_cnpj "
            + " INNER JOIN _using.cliente_favorito ON _using.cliente.nr_cpf_cnpj = _using.cliente_favorito.nr_cpf_cnpj WHERE cd_user = ? AND _using.produto.cd_cat = ?;\n" +
/*"inner join codigos_produto on produto.cd_produto = codigos_produto.cd_produto\n" +
"inner join cliente on precos_cliente.nr_cpf_cnpj = cliente.nr_cpf_cnpj\n" +
"inner join cliente_favorito on cliente.nr_cpf_cnpj = cliente_favorito.nr_cpf_cnpj\n" +
"where cd_user = ? and codigos_produto.cd_cat = ?;*/"";
            
            PreparedStatement stmt = connection.prepareStatement(sql);
           
            stmt.setString(1, js.getString(0));
            stmt.setString(2, js.getString(1));
           
           
            
            ResultSet rs = stmt.executeQuery();
            List<UsingWsDomain> lista = new ArrayList<>();
            while (rs.next()) {
                UsingWsDomain u = new UsingWsDomain();
                System.out.println(rs.getString("link_logotipo_cliente"));
                u.setNr_cpf_cnpj(rs.getString("nr_cpf_cnpj"));
                u.setLink_logotipo_cliente(rs.getString("link_logotipo_cliente"));
                lista.add(u);
            }
            
            return lista;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsingWsDao_rating.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        }
            public List<UsingWsDomain> favFilialFilter(String dataUser){
        try {
            Connection connection = getConnection();
            
            JSONArray js = new JSONArray(dataUser);
            
            String sql = "SELECT distinct filiais.link_logotipo_filial, filiais.nr_cnpj_filial FROM _using.produto \n" +
            "INNER JOIN _using.filiais on _using.produto.cnpj_filial = _using.filiais.nr_cnpj_filial"
            + " INNER JOIN _using.filial_favorito on _using.filiais.nr_cnpj_filial = _using.filial_favorito.nr_cnpj_filial WHERE cd_user = ? and _using.produto.cd_cat = ?;\n";
/*"inner join codigos_produto on produto.cd_produto = codigos_produto.cd_produto\n" +
"inner join filiais on precos_filial.nr_cnpj_filial = filiais.nr_cnpj_filial \n" +
"inner join filial_favorito on filiais.nr_cnpj_filial = filial_favorito.nr_cnpj_filial \n" +
"where cd_user = ? and codigos_produto.cd_cat = ?;"*/
            PreparedStatement stmt = connection.prepareStatement(sql);
           
            stmt.setString(1, js.getString(0));
            stmt.setString(2, js.getString(1));
           
            ResultSet rs = stmt.executeQuery();
            List<UsingWsDomain> lista = new ArrayList<>();
            while (rs.next()) {
                UsingWsDomain u = new UsingWsDomain();
                System.out.println(rs.getString("link_logotipo_filial"));
                u.setNr_cpf_cnpj(rs.getString("nr_cnpj_filial"));
                u.setLink_logotipo_cliente(rs.getString("link_logotipo_filial"));
                lista.add(u);
            }
            
            return lista;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsingWsDao_rating.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        }
            
}
