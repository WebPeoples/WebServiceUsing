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
import org.json.JSONArray;

/**
 *
 * @author Web People
 */
public class UsingWsDao_lojas {
    public ArrayList<UsingWsDomain> getInfoLojas(String cpf_cnpj){
        try{
            Connection connection = getConnection();
            JSONArray js = new JSONArray(cpf_cnpj);
            
            ArrayList<UsingWsDomain> lista = new ArrayList<>();
            StringBuilder sql2 = new StringBuilder();
            sql2.append("select distinct cliente.nr_telefone1_cli, cliente.nm_email,cliente.cd_cep_cli, cliente.cd_estado_cli,cliente.nm_cidade_cli, ");
            sql2.append("cliente.nm_razao_social, cliente.nome_rua, cliente.dc_expediente_cli, cliente.link_logotipo_cliente, cliente.link_web from _using.cliente ");
            sql2.append(" where cliente.nr_cpf_cnpj = ?;");
            PreparedStatement stmt2 = connection.prepareStatement(sql2.toString());
            System.out.println(js);
            stmt2.setString(1, js.getString(0));
            ResultSet rs2 = stmt2.executeQuery();
           
           while(rs2.next()){
               UsingWsDomain uwd = new UsingWsDomain(); 
               uwd.setNr_telefone1_cli(rs2.getString("nr_telefone1_cli"));
               uwd.setNm_email(rs2.getString("nm_email"));
               uwd.setCd_cep_cli(rs2.getString("cd_cep_cli"));
               uwd.setCd_estado_cli(rs2.getString("cd_estado_cli"));
               uwd.setCd_cidade_cli(rs2.getString("nm_cidade_cli"));
               uwd.setRazao_social("nm_razao_social");
               uwd.setNm_rua(rs2.getString("nome_rua"));
               uwd.setLink_logotipo_cliente(rs2.getString("link_logotipo_cliente"));
               uwd.setLink_web(rs2.getString("link_web"));
               uwd.setDias_abertura_cli(rs2.getString("dc_expediente_cli"));
               
               lista.add(uwd);               
           }
            return lista;
        }catch(SQLException | ClassNotFoundException e){
            System.out.println(e);
            return null;
        }
    }
      
      public ArrayList<UsingWsDomain> getInfoFilial(String cpf_cnpj){
        try{
            Connection connection = getConnection();
            ArrayList<UsingWsDomain> lista = new ArrayList<>();
            JSONArray js = new JSONArray(cpf_cnpj);
            StringBuilder sql2 = new StringBuilder();
            sql2.append("select filiais.nr_telefone1_fil, filiais.nm_email_fil, filiais.nr_cep_fil, filiais.cd_estado_fil, filiais.nome_rua, ");
            sql2.append("filiais.nr_numero_fil, filiais.link_logotipo_filial, filiais.link_web_fil, filiais.nm_cidade_fil, filiais.dc_expediente_fil from _using.filiais ");
            sql2.append("where filiais.nr_cnpj_filial = ?");
            PreparedStatement stmt2 = connection.prepareStatement(sql2.toString());
            stmt2.setString(1, js.getString(0));
            ResultSet rs2 = stmt2.executeQuery();
           
           while(rs2.next()){
               UsingWsDomain uwd = new UsingWsDomain(); 
               uwd.setNr_telefone1_cli(rs2.getString("nr_telefone1_fil"));
               uwd.setNm_email(rs2.getString("nm_email_fil"));
               uwd.setCd_cep_cli(rs2.getString("nr_cep_fil"));
               uwd.setCd_estado_cli(rs2.getString("cd_estado_fil"));
               uwd.setCd_cidade_cli(rs2.getString("nm_cidade_fil"));
               uwd.setNm_rua(rs2.getString("nome_rua"));
               uwd.setNr_numero(rs2.getInt("nr_numero_fil"));
               uwd.setLink_logotipo_cliente(rs2.getString("link_logotipo_filial"));
               uwd.setLink_web(rs2.getString("link_web_fil"));
               uwd.setDias_abertura_cli(rs2.getString("dc_expediente_fil"));
               lista.add(uwd);               
           }
            return lista;
        }catch(SQLException | ClassNotFoundException e){
            System.out.println(e);
            return null;
        }
    }
      
      public ArrayList<UsingWsDomain> getListaProduto(String cpf_cnpj){
        try{
            Connection connection = getConnection();
            JSONArray js = new JSONArray(cpf_cnpj);
            ArrayList<UsingWsDomain> lista = new ArrayList<>();
            StringBuilder sql2 = new StringBuilder();
            sql2.append("select distinct produto.nm_produto, produto.cd_link_foto_1,produto.cd_link_foto_2,produto.cd_link_foto_3,produto.cd_link_foto_4,"
                    + "produto.cd_link_foto_5, preco, descricao_prod, if_promocao, precoStrike, porcentagemPromo from _using.produto WHERE _using.produto.cnpj_cliente = ? AND _using.produto.subcat = ? order by cd_link_foto_1;");
            /*sql2.append("inner join codigos_produto on produto.cd_produto = codigos_produto.cd_produto ");
            sql2.append("inner join precos_cliente on produto.cd_produto = precos_cliente.cd_produto ");
            sql2.append("inner join cliente on precos_cliente.nr_cpf_cnpj = cliente.nr_cpf_cnpj where precos_cliente.nr_cpf_cnpj = ? and codigos_produto.cd_subcat = ? ");
            sql2.append("order by cd_link_foto_1;");*/
            PreparedStatement stmt2 = connection.prepareStatement(sql2.toString());
            stmt2.setString(1, js.getString(1));
            stmt2.setString(2, js.getString(0));
            ResultSet rs2 = stmt2.executeQuery();
           
           while(rs2.next()){
               UsingWsDomain uwd = new UsingWsDomain(); 
               uwd.setNm_produto(rs2.getString("nm_produto"));
               uwd.setCd_link_foto_1(rs2.getString("cd_link_foto_1"));
               uwd.setVl_preco_cli(rs2.getString("preco"));
               uwd.setDc_completa_prod(rs2.getString("descricao_prod"));
               uwd.setIf_promocao(rs2.getString("if_promocao"));
               uwd.setPrecoStrike(rs2.getString("precoStrike"));
                uwd.setPorcentagemPromo(rs2.getString("porcentagemPromo"));
               lista.add(uwd);               
           }
            return lista;
        }catch(SQLException | ClassNotFoundException e){
            System.out.println(e);
            return null;
        }
    }
      
    public ArrayList<UsingWsDomain> getFilialListaProduto(String cpf_cnpj){
        try{
            Connection connection = getConnection();
            ArrayList<UsingWsDomain> lista = new ArrayList<>();
            JSONArray js = new JSONArray(cpf_cnpj);
            System.out.println(js);
            StringBuilder sql2 = new StringBuilder();
            sql2.append("select produto.nm_produto, produto.cd_link_foto_1, preco, descricao_prod, if_promocao, precoStrike, porcentagemPromo from _using.produto "
                    + "WHERE _using.produto.cnpj_filial = ? AND _using.produto.subcat = ? order by cd_link_foto_1;");
            /*sql2.append("inner join codigos_produto on produto.cd_produto = codigos_produto.cd_produto ");
            sql2.append("inner join precos_filial on produto.cd_produto = precos_filial.cd_produto ");
            sql2.append("inner join filiais on precos_filial.nr_cnpj_filial = filiais.nr_cnpj_filial where precos_filial.nr_cnpj_filial = ? and codigos_produto.cd_subcat = ? ");
            sql2.append("order by cd_link_foto_1;");*/
            PreparedStatement stmt2 = connection.prepareStatement(sql2.toString());
            stmt2.setString(1, js.getString(1));
            stmt2.setString(2, js.getString(0));
            ResultSet rs2 = stmt2.executeQuery();
            
           
           while(rs2.next()){
               UsingWsDomain uwd = new UsingWsDomain(); 
               uwd.setNm_produto(rs2.getString("nm_produto"));
               uwd.setCd_link_foto_1(rs2.getString("cd_link_foto_1"));
               uwd.setVl_preco_cli(rs2.getString("preco"));
               uwd.setDc_completa_prod(rs2.getString("descricao_prod"));
               uwd.setIf_promocao(rs2.getString("if_promocao"));
               uwd.setPrecoStrike(rs2.getString("precoStrike"));
                uwd.setPorcentagemPromo(rs2.getString("porcentagemPromo"));
               lista.add(uwd);               
           }
           
            return lista;
        }catch(SQLException | ClassNotFoundException e){
            System.out.println(e);
            return null;
        }
    }
    public ArrayList<UsingWsDomain> getLocalListaProduto(String cpf_cnpj){
        try{
            Connection connection = getConnection();
            ArrayList<UsingWsDomain> lista = new ArrayList<>();
            JSONArray js = new JSONArray(cpf_cnpj);
            
            StringBuilder sql2 = new StringBuilder();
            sql2.append("select distinct produto.nm_produto, produto.cd_link_foto_1,produto.cd_link_foto_2,produto.cd_link_foto_3,produto.cd_link_foto_4,"
                    + "produto.cd_link_foto_5, preco, descricao_prod,if_promocao,precoStrike, porcentagemPromo from _using.produto WHERE produto.cnpj_cliente = ? order by cd_link_foto_1; ");
            /*sql2.append("inner join codigos_produto on produto.cd_produto = codigos_produto.cd_produto ");
            sql2.append("inner join precos_cliente on produto.cd_produto = precos_cliente.cd_produto ");
            sql2.append("inner join cliente on precos_cliente.nr_cpf_cnpj = cliente.nr_cpf_cnpj where precos_cliente.nr_cpf_cnpj = ? ");
            sql2.append("order by cd_link_foto_1;");*/
            PreparedStatement stmt2 = connection.prepareStatement(sql2.toString());
            stmt2.setString(1,js.getString(0));
            ResultSet rs2 = stmt2.executeQuery();
           
           while(rs2.next()){
               UsingWsDomain uwd = new UsingWsDomain(); 
               System.out.println(rs2.getString("nm_produto"));
               uwd.setNm_produto(rs2.getString("nm_produto"));
               uwd.setCd_link_foto_1(rs2.getString("cd_link_foto_1"));
               uwd.setVl_preco_cli(rs2.getString("preco"));
               uwd.setDc_completa_prod(rs2.getString("descricao_prod"));
               uwd.setIf_promocao(rs2.getString("if_promocao"));
               uwd.setPrecoStrike(rs2.getString("precoStrike"));
                uwd.setPorcentagemPromo(rs2.getString("porcentagemPromo"));
               lista.add(uwd);               
           }
            return lista;
        }catch(SQLException | ClassNotFoundException e){
            System.out.println(e);
            return null;
        }
    }
     public ArrayList<UsingWsDomain> getLocalListaProdutoFilial(String cpf_cnpj){
        try{
            Connection connection = getConnection();
            ArrayList<UsingWsDomain> lista = new ArrayList<>();
            JSONArray js = new JSONArray(cpf_cnpj);
            
            StringBuilder sql2 = new StringBuilder();
            sql2.append("select produto.nm_produto, produto.cd_link_foto_1, produto.preco, produto.descricao_prod, if_promocao, precoStrike, porcentagemPromo from _using.produto"
                    + " WHERE produto.cnpj_filial = ? order by cd_link_foto_1;");
           /* sql2.append("inner join codigos_produto on produto.cd_produto = codigos_produto.cd_produto ");
            sql2.append("inner join precos_filial on produto.cd_produto = precos_filial.cd_produto ");
            sql2.append("inner join filiais on precos_filial.nr_cnpj_filial = filiais.nr_cnpj_filial where precos_filial.nr_cnpj_filial = ? ");
            sql2.append("order by cd_link_foto_1;");*/
            PreparedStatement stmt2 = connection.prepareStatement(sql2.toString());
            stmt2.setString(1,js.getString(0));
            ResultSet rs2 = stmt2.executeQuery();
           
           while(rs2.next()){
               UsingWsDomain uwd = new UsingWsDomain(); 
               System.out.println(rs2.getString("nm_produto"));
               uwd.setNm_produto(rs2.getString("nm_produto"));
               uwd.setCd_link_foto_1(rs2.getString("cd_link_foto_1"));
               uwd.setVl_preco_cli(rs2.getString("preco"));
               uwd.setDc_completa_prod(rs2.getString("descricao_prod"));
                uwd.setIf_promocao(rs2.getString("if_promocao"));
                uwd.setPrecoStrike(rs2.getString("precoStrike"));
                uwd.setPorcentagemPromo(rs2.getString("porcentagemPromo"));
               lista.add(uwd);               
           }
            return lista;
        }catch(SQLException | ClassNotFoundException e){
            System.out.println(e);
            return null;
        }
    }
}
