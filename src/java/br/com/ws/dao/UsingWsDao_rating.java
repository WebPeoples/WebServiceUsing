/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ws.dao;

import br.com.ws.domain.UsingWsDomain;
import java.sql.Connection;
import java.util.List;
import br.com.ws.Factory.Connect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;

/**
 *
 * @author Web People
 */
public class UsingWsDao_rating extends Connect{
      public ArrayList<UsingWsDomain> getFotoProdutoActivity(String cd_link_foto_1){
          try{
              Connection connection = getConnection();
              ArrayList<UsingWsDomain> lista = new ArrayList<>();
              String sql = "select distinct produto.cd_link_foto_2,produto.cd_link_foto_3,produto.cd_link_foto_4,"
                    + "produto.cd_link_foto_5 from _using.produto inner join _using.cliente on _using.produto.cnpj_cliente"
                      + " = _using.cliente.nr_cpf_cnpj where cd_link_foto_1 = ?";
              PreparedStatement stmt = connection.prepareStatement(sql);
              stmt.setString(1, cd_link_foto_1);
              ResultSet rs = stmt.executeQuery();
              
              while(rs.next()){
                  UsingWsDomain uwd = new UsingWsDomain();
                  uwd.setCd_link_foto_2(rs.getString("cd_link_foto_2"));
                  uwd.setCd_link_foto_3(rs.getString("cd_link_foto_3"));
                  uwd.setCd_link_foto_4(rs.getString("cd_link_foto_4"));
                  uwd.setCd_link_foto_5(rs.getString("cd_link_foto_5"));
                  lista.add(uwd);
              }
              
              return lista;
          
          } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsingWsDao_rating.class.getName()).log(Level.SEVERE, null, ex);
            return null;
          }      
      }
      
     
      
        public List<String> getRating(String nr_cpf_cnpj){
            
            try {
                Connection connection = getConnection();
                JSONArray js = new JSONArray(nr_cpf_cnpj);
                
                String sql = "SELECT count(cd_user), SUM(rating), rating FROM _using.rating_cliente where nr_cpf_cnpj = ? group by rating;";
                String sqlFilial = "SELECT count(cd_user), SUM(rating), rating FROM _using.rating_filial where nr_cnpj_filial = ? group by rating;";
                PreparedStatement stmt = connection.prepareStatement(sql);
                PreparedStatement stmtFilial = connection.prepareStatement(sqlFilial);
                
                stmt.setString(1, js.getString(0));
                System.out.println("getRating"+js.getString(0));
                stmtFilial.setString(1, js.getString(0));
                
                ResultSet rs = stmt.executeQuery();
                ResultSet rsFilial = stmtFilial.executeQuery();
                
                List<String> lista = new ArrayList<>();
                
                
                if(rs.next() ){
                    if (rs.getFloat(2) != 0.0) {
                        System.out.println("rating cliente");
                        System.out.println(rs.getInt(1) + " s ");
                        lista.add(String.valueOf(rs.getInt(1)));
                        lista.add(String.valueOf(rs.getFloat(2)));
                        return lista;                       
                    }
                }else if(rsFilial.next()){
                
                if (rsFilial.getFloat(2) != 0.0){
                        System.out.println("rating filial");
                        lista.add(String.valueOf(rsFilial.getInt(1)));
                        lista.add(String.valueOf(rsFilial.getFloat(2)));
                        return lista;
                    }
                }
               
                return null;
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(UsingWsDao_rating.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        
        public String setRating(String nr_cpf_cnpj){
        
        try {
            Connection connection = getConnection();
            JSONArray js = new JSONArray(nr_cpf_cnpj);
            
            String retorno = "f";
            int isInsertCliente = 1;
            int isInsertFilial = 1;
            
            String select = "SELECT * from _using.rating_cliente where nr_cpf_cnpj = ? and cd_user = ?;";
            PreparedStatement stmt = connection.prepareStatement(select);
            
            String selectFilial = "SELECT * from _using.rating_filial where nr_cnpj_filial = ? and cd_user = ?;";
            PreparedStatement stmtFilial = connection.prepareStatement(selectFilial);
            
            stmt.setString(1, js.getString(0));
            stmt.setString(2, js.getString(2));
            stmtFilial.setString(1, js.getString(0));
            stmtFilial.setString(2, js.getString(2));
            
            ResultSet rs = stmt.executeQuery();
            ResultSet rsFilial = stmtFilial.executeQuery();
            
            if(rs.next()){
                   
                System.out.println("achou em rating_cliente");
                String updateCliente = "Update _using.rating_cliente set rating = ? where cd_user = ?;";

                PreparedStatement stmtCliente = connection.prepareStatement(updateCliente);

                stmtCliente.setFloat(1, (float) js.getDouble(1));
                stmtCliente.setString(2, js.getString(2));
                
                stmtCliente.execute();
                retorno = "update cliente";
                       
            }else if(rsFilial.next()){
                String updateFilial = "Update _using.rating_filial set rating = ? where cd_user = ?;";
                System.out.println("achou em rating_filial");
                PreparedStatement stmtCliente = connection.prepareStatement(updateFilial);

                stmtCliente.setFloat(1, (float) js.getDouble(1));
                stmtCliente.setString(2, js.getString(2));
                stmtCliente.execute();
                retorno = "update filial";
            }else {
                retorno = setInsertRating(nr_cpf_cnpj); 
            }
            return retorno;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsingWsDao_rating.class.getName()).log(Level.SEVERE, null, ex);
            return "Erro: " + ex;
        }
        
        }
        
        public String setInsertRating(String nr_cpf_cnpj){
          JSONArray js = new JSONArray(nr_cpf_cnpj);
            Connection connection;
        try {
            connection = getConnection();
            
             String insertCliente = "INSERT INTO _using.rating_cliente values(?, CURRENT_TIMESTAMP, ?, ?);";
                    PreparedStatement stmtInCliente = connection.prepareStatement(insertCliente);
                    stmtInCliente.setString(1, js.getString(2));
                    stmtInCliente.setString(2, js.getString(0));
                    stmtInCliente.setFloat(3, (float) js.getDouble(1));

                    stmtInCliente.execute();
                     
                     return "insert cliente";
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsingWsDao_rating.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
          
             connection = getConnection();

                       String insertFilial = "INSERT INTO _using.rating_filial values(?, CURRENT_TIMESTAMP, ?, ?);";
                       PreparedStatement stmtInFilial = connection.prepareStatement(insertFilial);
                       stmtInFilial.setString(1, js.getString(2));
                       stmtInFilial.setString(2, js.getString(0));
                       stmtInFilial.setFloat(3, (float) js.getDouble(1));
                       
                       stmtInFilial.execute();
                      
            
            return "insert filial";
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UsingWsDao_rating.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        }
}