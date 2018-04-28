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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Web People
 */
public class UsingWsDao_promocoes {
     public ArrayList<UsingWsDomain> getPromoList(String cd_link_foto_1){
          try{
              Connection connection = getConnection();
              ArrayList<UsingWsDomain> lista = new ArrayList<>();
              StringBuilder sql = new StringBuilder();
              sql.append("select produto.cd_link_foto_1, cliente.nm_razao_social, cliente.longitude_cli, cliente.latitude_cli,");
              sql.append("cliente.link_logotipo_cliente, preco, descricao_prod, produto.nm_produto"
                      + " from _using.produto inner join _using.cliente on _using.produto.cnpj_cliente = _using.cliente.nr_cpf_cnpj WHERE produto.if_promocao = 'S'; ");
              /*sql.append("precos_cliente.cd_produto = produto.cd_produto inner join cliente on precos_cliente.nr_cpf_cnpj = cliente.nr_cpf_cnpj ");
              sql.append("where st_promo_cli = ? order by precos_cliente.vl_preco_cli asc");*/
              PreparedStatement stmt = connection.prepareStatement(sql.toString());
//              stmt.setString(1, cd_link_foto_1);
              ResultSet rs = stmt.executeQuery();
              
              while(rs.next()){
                  UsingWsDomain uwd = new UsingWsDomain();
                  uwd.setCd_link_foto_1(rs.getString("cd_link_foto_1"));
                  uwd.setNm_razao_social(rs.getString("nm_razao_social"));
                  uwd.setLatitude_cli(rs.getDouble("latitude_cli"));
                  uwd.setLongitu_cli(rs.getDouble("longitude_cli"));
                  uwd.setLink_logotipo_cliente(rs.getString("link_logotipo_cliente"));
                  uwd.setVl_preco_cli(rs.getString("preco"));
                  uwd.setDc_completa_prod(rs.getString("descricao_prod"));
                  uwd.setNm_produto(rs.getString("nm_produto"));
                  lista.add(uwd);
              }
              
              return lista;
          
          } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsingWsDao_rating.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
      }
      
      public ArrayList<UsingWsDomain> getPromoListFilial(String cd_link_foto_1){
          try{
              Connection connection = getConnection();
              ArrayList<UsingWsDomain> lista = new ArrayList<>();
              StringBuilder sql = new StringBuilder();
              sql.append("select produto.cd_link_foto_1, filiais.longitude_fil, filiais.latitude_fil, filiais.link_logotipo_filial, preco,"
                      + "  descricao_prod, produto.nm_produto from _using.produto inner join _using.filiais on _using.filiais.nr_cnpj_filial = _using.produto.cnpj_filial WHERE if_promocao = 'S'; ");
             /* sql.append("inner join produto on precos_filial.cd_produto = produto.cd_produto ");
              sql.append("inner join filiais on precos_filial.nr_cnpj_filial = filiais.nr_cnpj_filial  ");
              sql.append("where st_promo_fil = 's' order by precos_filial.vl_preco_fil asc");*/
              PreparedStatement stmt = connection.prepareStatement(sql.toString());
              
              ResultSet rs = stmt.executeQuery();
              
              while(rs.next()){
                  UsingWsDomain uwd = new UsingWsDomain();
                  uwd.setCd_link_foto_1(rs.getString("cd_link_foto_1"));
                  uwd.setLatitude_cli(rs.getDouble("latitude_fil"));
                  uwd.setLongitu_cli(rs.getDouble("longitude_fil"));
                  uwd.setLink_logotipo_cliente(rs.getString("link_logotipo_filial"));
                  uwd.setVl_preco_cli(rs.getString("preco"));
                  uwd.setDc_completa_prod(rs.getString("descricao_prod"));
                  uwd.setNm_produto(rs.getString("nm_produto"));
                  lista.add(uwd);
              }
              
              return lista;
          
          } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsingWsDao_rating.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
      }
      
      public ArrayList<UsingWsDomain> getPromoListFilter(String categoria){
          try{
              Connection connection = getConnection();
              ArrayList<UsingWsDomain> lista = new ArrayList<>();
              StringBuilder sql = new StringBuilder();
              sql.append("select produto.cd_link_foto_1, cliente.nm_razao_social, cliente.longitude_cli, cliente.latitude_cli,");
              sql.append("cliente.link_logotipo_cliente, preco, descricao_prod, produto.nm_produto from _using.produto ");
              sql.append("inner join _using.cliente on _using.produto.cnpj_cliente = _using.cliente.nr_cpf_cnpj WHERE if_promocao = 'S' and produto.cd_cat = ?");
              /*sql.append("inner join codigos_produto on produto.cd_produto = codigos_produto.cd_produto ");
              sql.append("inner join cliente on precos_cliente.nr_cpf_cnpj = cliente.nr_cpf_cnpj ");
              sql.append("where st_promo_cli = 'S' and codigos_produto.cd_cat = ? order by precos_cliente.vl_preco_cli");*/
              PreparedStatement stmt = connection.prepareStatement(sql.toString());
              stmt.setString(1, categoria);
              ResultSet rs = stmt.executeQuery();
              
              while(rs.next()){
                  UsingWsDomain uwd = new UsingWsDomain();
                  uwd.setCd_link_foto_1(rs.getString("cd_link_foto_1"));
                  uwd.setNm_razao_social(rs.getString("nm_razao_social"));
                  uwd.setLatitude_cli(rs.getDouble("latitude_cli"));
                  uwd.setLongitu_cli(rs.getDouble("longitude_cli"));
                  uwd.setLink_logotipo_cliente(rs.getString("link_logotipo_cliente"));
                  uwd.setVl_preco_cli(rs.getString("preco"));
                  uwd.setDc_completa_prod(rs.getString("descricao_prod"));
                  uwd.setNm_produto(rs.getString("nm_produto"));
                  lista.add(uwd);
              }
              
              return lista;
          
          } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsingWsDao_rating.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
      }
      
      public ArrayList<UsingWsDomain> getPromoListFilterFilial(String categoria){
          try{
              Connection connection = getConnection();
              ArrayList<UsingWsDomain> lista = new ArrayList<>();
              StringBuilder sql = new StringBuilder();
              sql.append("select produto.cd_link_foto_1, filiais.longitude_fil, filiais.latitude_fil,");
              sql.append("filiais.link_logotipo_filial, preco, descricao_prod, produto.nm_produto from _using.produto ");
              sql.append("inner join _using.filiais on _using.produto.cnpj_filial = _using.filiais.nr_cnpj_filial WHERE if_promocao = 'S' and produto.cd_cat = ?");
              /*sql.append("inner join codigos_produto on produto.cd_produto = codigos_produto.cd_produto ");
              sql.append("inner join filiais on precos_filial.nr_cnpj_filial = filiais.nr_cnpj_filial ");
              sql.append("where st_promo_fil = 'S' and codigos_produto.cd_cat = ? order by precos_filial.vl_preco_fil");*/
              PreparedStatement stmt = connection.prepareStatement(sql.toString());
              stmt.setString(1, categoria);
              ResultSet rs = stmt.executeQuery();
              
              while(rs.next()){
                  UsingWsDomain uwd = new UsingWsDomain();
                  uwd.setCd_link_foto_1(rs.getString("cd_link_foto_1"));
                  uwd.setLatitude_cli(rs.getDouble("latitude_fil"));
                  uwd.setLongitu_cli(rs.getDouble("longitude_fil"));
                  uwd.setLink_logotipo_cliente(rs.getString("link_logotipo_filial"));
                  uwd.setVl_preco_cli(rs.getString("preco"));
                  uwd.setDc_completa_prod(rs.getString("descricao_prod"));
                  uwd.setNm_produto(rs.getString("nm_produto"));
                  lista.add(uwd);
              }
              
              return lista;
          
          } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsingWsDao_rating.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
      }
}
