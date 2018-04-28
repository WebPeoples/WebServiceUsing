/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ws.dao;

import static br.com.ws.Factory.Connect.getConnection;
import br.com.ws.domain.usingCadastroDomain;
import br.com.ws.domain.usingDomain;
import br.com.ws.domain.usingLojaProdutosDomain;
import br.com.ws.domain.usingMapsDomain;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Web People
 */
public class usingDAO {
    
     public ArrayList<usingDomain> listarLojas() throws SQLException {

        try {
            ArrayList<usingDomain> lista = new ArrayList<usingDomain>();
            String sql = null;
            Connection connection = getConnection();
            PreparedStatement stmt = null;

            sql = "select * FROM lojas;";

            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                usingDomain hb = new usingDomain();
               
                hb.setFoto(rs.getString("loja"));

                lista.add(hb);
            }

            return lista;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }
    
    public ArrayList<usingDomain> listar() throws SQLException {

        try {
            ArrayList<usingDomain> lista = new ArrayList<usingDomain>();
            String sql = null;
            Connection connection = getConnection();
            PreparedStatement stmt = null;

            sql = "select * FROM foto;";

            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                usingDomain hb = new usingDomain();
               
                hb.setFoto(rs.getString("foto"));

                lista.add(hb);
            }

            return lista;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<usingMapsDomain> newGetLojas(String cat){
        
        try {
            String sql;
            Connection connection = getConnection();
            PreparedStatement stmt;

            sql = "select * FROM busca_detalhe Where cod_subcat = ?";

            stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, cat);
            
            ResultSet rs = stmt.executeQuery();
           
            List<String> lista = new ArrayList<>();
            
            while(rs.next()){
                int a = 0;
                lista.add(rs.getString("cod_busca"));            
            }
            
            for (int i = 0; i < lista.size(); i++) {
                
                System.out.println("Código da lista: " + lista.get(i));
            }
            
            
            StringBuilder loja_mapsBuilder = new StringBuilder();
            
            loja_mapsBuilder.append("select nome, icone, lat, longi from lojas_maps where cod_busca in (?");
            
            for (int i = 0; i < lista.size()-1; i++) {
                loja_mapsBuilder.append(",?");
            }
            
            loja_mapsBuilder.append(");");
            
            System.out.println("StringBuilder: " + loja_mapsBuilder);
            PreparedStatement stmt_maps = connection.prepareStatement(loja_mapsBuilder.toString());
            
            for (int i = 1; i < lista.size()+1; i++) {
                //System.out.println(i+1);
                stmt_maps.setString(i, lista.get(i-1));                
                System.out.println("Referenciando os parâmetros da busca em loja_maps: " + lista.get(i-1));
            }
            
            ResultSet rs_maps = stmt_maps.executeQuery();

            List<usingMapsDomain> lista_maps = new ArrayList<>();
            while (rs_maps.next()){
            usingMapsDomain ud = new usingMapsDomain();
                System.out.println(rs_maps.getString("nome"));
                ud.setNome(rs_maps.getString("nome"));
                ud.setIcone(rs_maps.getString("icone"));
                ud.setLat(rs_maps.getDouble("lat"));
                ud.setLongi(rs_maps.getDouble("longi"));
                
                lista_maps.add(ud);
                
            }
            
            for (int i = 0; i < lista_maps.size(); i++) {
                System.out.println("Lista_maps: " + lista_maps.get(i).getNome());
                System.out.println("Lista_maps: " + lista_maps.get(i).getIcone());
                System.out.println("Lista_maps: " + lista_maps.get(i).getCod_busca());
            }
            
            return lista_maps;

        } catch (ClassNotFoundException| SQLException e ) {
            System.out.println(e);
            return null;
        }    
    }
    
    
    public List<usingLojaProdutosDomain> getProdutos(String cod_produto){
    
        try{
        
        Connection connection = getConnection();
        
        String sql = " select * from produto where cod_produtos = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        
        stmt.setString(1, cod_produto);
        
        ResultSet rs = stmt.executeQuery();
        
        List<usingLojaProdutosDomain> lista = new ArrayList<>();
        
        while(rs.next()){        
            usingLojaProdutosDomain ulp = new usingLojaProdutosDomain();
            
            ulp.setPath(rs.getString("path"));
            ulp.setPreco(rs.getDouble("preco"));
            ulp.setNome(rs.getString("nome"));
            ulp.setDesc_produto(rs.getString("desc_produto"));
            
            lista.add(ulp);
        
        }
        
        return lista;
        
        } catch (ClassNotFoundException | SQLException ex) {
             Logger.getLogger(usingDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
         }
    }
    
    public List<usingLojaProdutosDomain> getLojaInfo(String cod_produto){
    
        try{
        
        Connection connection = getConnection();
        
        String sql = " select * from loja where cod_produtos = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        
        stmt.setString(1, cod_produto);
        
        ResultSet rs = stmt.executeQuery();
        
        List<usingLojaProdutosDomain> lista = new ArrayList<>();
        
        while(rs.next()){
        
            usingLojaProdutosDomain ulp = new usingLojaProdutosDomain();
            
            ulp.setLogo(rs.getString("logo"));
            
            lista.add(ulp);
        
        }
        
        return lista;
        
        } catch (ClassNotFoundException | SQLException ex) {
             Logger.getLogger(usingDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
         }
    }
    
    public List<usingMapsDomain> btLocal(){
        try{
        Connection connection = getConnection();
        
        String sql = " select * from lojas_maps";
        PreparedStatement stmt = connection.prepareStatement(sql);
        
        ResultSet rs = stmt.executeQuery();
        
        List<usingMapsDomain> lista = new ArrayList<>();
        
        while(rs.next()){        
            usingMapsDomain ulp = new usingMapsDomain();
            
            ulp.setNome(rs.getString("nome"));
            ulp.setIcone(rs.getString("icone"));
            ulp.setLat(rs.getDouble("lat"));
            ulp.setLongi(rs.getDouble("longi"));
            
            lista.add(ulp);
        }
        return lista;
        } catch (ClassNotFoundException | SQLException ex) {
             Logger.getLogger(usingDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
         }
    }
    
    
    public void cadastro(usingCadastroDomain c){
    
        try{
                Connection connection= getConnection();
                
                String sql = "insert into user (cpf, nome, data_nasc, telefone, celular, cep, pais,"
                        + "email, senha) values(?,?,?,?,?,?,?,?,?)";
                
                PreparedStatement stmt = connection.prepareStatement(sql);
                
                stmt.setInt(1, c.getCpf());
                stmt.setString(2, c.getNome());
                stmt.setDate(3, c.getData_nasc());
                stmt.setInt(4, c.getTelefone());
                stmt.setInt(5, c.getCelular());
                stmt.setInt(6, c.getCep());
                stmt.setString(7, c.getPais());
                stmt.setString(8, c.getEmail());
                stmt.setString(9, c.getSenha());
                
                stmt.execute();
      
        } catch (ClassNotFoundException | SQLException ex) {
             Logger.getLogger(usingDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
    
    
    }
    
}
