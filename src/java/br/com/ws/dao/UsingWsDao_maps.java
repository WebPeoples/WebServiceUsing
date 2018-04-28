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
import org.json.JSONObject;

/**
 *
 * @author Web People
 */
public class UsingWsDao_maps {
    
    public ArrayList<UsingWsDomain> getValuesToMap(String subcat){
        try{
            Connection connection = getConnection();
            JSONArray js = new JSONArray(subcat);
            double lati, longi;
            String sub;
                JSONObject jso = js.getJSONObject(0);
                lati = jso.getDouble("lati");
                longi = jso.getDouble("longit");
                sub = jso.getString("subcat");
               
            ArrayList<UsingWsDomain> lista = new ArrayList<>();
            StringBuilder sql2 = new StringBuilder();
            StringBuilder sqlFilial = new StringBuilder();
            sqlFilial.append("WITH relacao AS (\n" +
"  SELECT DISTINCT f.icon_logo_maps,\n" +
"                  f.latitude_fil,\n" +
"                  f.longitude_fil,\n" +
"                  f.nr_cnpj_filial,\n" +
"                  CAST('POINT(' + CAST(? AS VARCHAR(20)) + ' ' + CAST(? AS VARCHAR(20)) + ')' AS GEOGRAPHY).STDistance(\n" +
"                  CAST('POINT(' + CAST(f.latitude_fil AS VARCHAR(20)) + ' ' + CAST(f.longitude_fil AS VARCHAR(20)) + ')' AS GEOGRAPHY)) AS distance\n" +
"    FROM _using.produto p\n" +
"         INNER JOIN _using.filiais f ON p.cnpj_filial = f.nr_cnpj_filial\n" +
"   WHERE p.subcat = ? \n" +
")\n" +
"SELECT r.icon_logo_maps,\n" +
"       r.latitude_fil,\n" +
"       r.longitude_fil,\n" +
"       r.nr_cnpj_filial\n" +
"  FROM relacao r\n" +
" WHERE r.distance <= 1500;");
////            sql2.append("select distinct cliente.icon_logo_maps ,cliente.latitude_cli, cliente.longitude_cli, cliente.nr_cpf_cnpj,\n" +
////" (6371 *\n" +
////"        acos(\n" +
////"            cos(radians(?)) *\n" +
////"            cos(radians(cliente.latitude_cli)) *\n" +
////"            cos(radians(?) - radians(cliente.longitude_cli)) +\n" +
////"            sin(radians(?)) *\n" +
////"            sin(radians(cliente.latitude_cli))\n" +
////"        )) AS distancekm from _using.produto  ");
////            sql2.append("inner join _using.cliente on _using.produto.cnpj_cliente = _using.cliente.nr_cpf_cnpj WHERE _using.produto.subcat = ? having COUNT(distancekm) < 7 ;");
           /*  sql2.append("inner join precos_cliente on codigos_produto.cd_produto = precos_cliente.cd_produto ");
            sql2.append("inner join cliente on precos_cliente.nr_cpf_cnpj = cliente.nr_cpf_cnpj where codigos_produto.cd_subcat = ? HAVING distance <= 10");*/
            
            sql2.append("WITH relacao AS (\n" +
"  SELECT DISTINCT f.icon_logo_maps,\n" +
"                  f.latitude_cli,\n" +
"                  f.longitude_cli,\n" +
"                  f.nr_cpf_cnpj,\n" +
"                  CAST('POINT(' + CAST(? AS VARCHAR(20)) + ' ' + CAST(? AS VARCHAR(20)) + ')' AS GEOGRAPHY).STDistance(\n" +
"                  CAST('POINT(' + CAST(f.latitude_cli AS VARCHAR(20)) + ' ' + CAST(f.longitude_cli AS VARCHAR(20)) + ')' AS GEOGRAPHY)) AS distance\n" +
"    FROM _using.produto p\n" +
"         INNER JOIN _using.cliente f ON p.cnpj_cliente = f.nr_cpf_cnpj\n" +
"   WHERE p.subcat = ? \n" +
")\n" +
"SELECT r.icon_logo_maps,\n" +
"       r.latitude_cli,\n" +
"       r.longitude_cli,\n" +
"       r.nr_cpf_cnpj\n" +
"  FROM relacao r\n" +
" WHERE r.distance <= 1500;");
           
//            sqlFilial.append("select distinct filiais.icon_logo_maps ,filiais.latitude_fil, filiais.longitude_fil, filiais.nr_cnpj_filial, \n" +
//" (6371 *\n" +
//"        acos(\n" +
//"            cos(radians(?)) *\n" +
//"            cos(radians(filiais.latitude_fil)) *\n" +
//"            cos(radians(?) - radians(filiais.longitude_fil)) +\n" +
//"            sin(radians(?)) *\n" +
//"            sin(radians(filiais.latitude_fil))\n" +
//"        )) AS distancekm from _using.produto ");
//            sqlFilial.append("inner join _using.filiais on _using.produto.cnpj_filial = _using.filiais.nr_cnpj_filial WHERE _using.produto.subcat = ?  ;");
            /*sqlFilial.append("inner join precos_filial on codigos_produto.cd_produto = precos_filial.cd_produto ");
            sqlFilial.append("inner join filiais on precos_filial.nr_cnpj_filial = filiais.nr_cnpj_filial where codigos_produto.cd_subcat = ? HAVING distance <= 10;");*/
            
            PreparedStatement stmtFilial = connection.prepareStatement(sqlFilial.toString());
            PreparedStatement stmt2 = connection.prepareStatement(sql2.toString());
            
            stmtFilial.setString(1,String.valueOf(lati));
            stmtFilial.setString(2, String.valueOf(longi));
            stmtFilial.setString(3, sub);
            //stmtFilial.setString(4, sub);
            
            System.out.println(lati + " " + longi + " " + sub);
            
            stmt2.setString(1,String.valueOf(lati));
            stmt2.setString(2, String.valueOf(longi));
            stmt2.setString(3, sub);
            //stmt2.setString(4, sub);
            
            ResultSet rsFilial = stmtFilial.executeQuery();
            ResultSet rs2 = stmt2.executeQuery();
           
           while(rs2.next()){
               UsingWsDomain uwd = new UsingWsDomain(); 
               uwd.setIcon_logo_maps(rs2.getString("icon_logo_maps"));
               uwd.setLatitude_cli(rs2.getDouble("latitude_cli"));
               uwd.setLongitu_cli(rs2.getDouble("longitude_cli"));
               uwd.setNr_cpf_cnpj(rs2.getString("nr_cpf_cnpj"));
               lista.add(uwd);    
               System.out.println(rs2.getString("icon_logo_maps"));
           } 
           while(rsFilial.next()){
               UsingWsDomain uwd = new UsingWsDomain(); 
               uwd.setIcon_logo_maps(rsFilial.getString("icon_logo_maps"));
               uwd.setLatitude_cli(rsFilial.getDouble("latitude_fil"));
               uwd.setLongitu_cli(rsFilial.getDouble("longitude_fil"));
               uwd.setNr_cpf_cnpj(rsFilial.getString("nr_cnpj_filial"));
               System.out.println(rsFilial.getString("icon_logo_maps"));
               lista.add(uwd);               
           }
            return lista;
        }catch(SQLException | ClassNotFoundException e){
            System.out.println(e);
            return null;
        }
    }
   
      public ArrayList<UsingWsDomain> getValuesToMapLocalActivity(){
        try{
            Connection connection = getConnection();
            ArrayList<UsingWsDomain> lista = new ArrayList<>();
            StringBuilder sql2 = new StringBuilder();
            sql2.append("select distinct cliente.icon_logo_maps, cliente.latitude_cli, cliente.longitude_cli, cliente.nr_cpf_cnpj from _using.produto ");
            sql2.append("inner join _using.cliente on _using.produto.cnpj_cliente = cliente.nr_cpf_cnpj ");
            //sql2.append("inner join cliente on precos_cliente.nr_cpf_cnpj = cliente.nr_cpf_cnpj ");
            
             StringBuilder sqlFilial = new StringBuilder();
            sqlFilial.append("select distinct filiais.icon_logo_maps ,filiais.latitude_fil, filiais.longitude_fil, filiais.nr_cnpj_filial from _using.produto ");
            sqlFilial.append("inner join _using.filiais on _using.produto.cnpj_filial = filiais.nr_cnpj_filial ");
            //sqlFilial.append("inner join filiais on precos_filial.nr_cnpj_filial = filiais.nr_cnpj_filial");
            
            PreparedStatement stmt2 = connection.prepareStatement(sql2.toString());
            PreparedStatement stmtFilial = connection.prepareStatement(sqlFilial.toString());
            ResultSet rs2 = stmt2.executeQuery();
            ResultSet rsFilial = stmtFilial.executeQuery();
           
           while(rs2.next()){
               UsingWsDomain uwd = new UsingWsDomain(); 
               uwd.setIcon_logo_maps(rs2.getString("icon_logo_maps"));
               uwd.setLatitude_cli(rs2.getDouble("latitude_cli"));
               uwd.setLongitu_cli(rs2.getDouble("longitude_cli"));
               uwd.setNr_cpf_cnpj(rs2.getString("nr_cpf_cnpj"));
               lista.add(uwd);               
           }
           
           while(rsFilial.next()){
               UsingWsDomain uwd = new UsingWsDomain(); 
               uwd.setIcon_logo_maps(rsFilial.getString("icon_logo_maps"));
               uwd.setLatitude_cli(rsFilial.getDouble("latitude_fil"));
               uwd.setLongitu_cli(rsFilial.getDouble("longitude_fil"));
               uwd.setNr_cpf_cnpj(rsFilial.getString("nr_cnpj_filial"));
               lista.add(uwd);               
           }
            return lista;
        }catch(SQLException | ClassNotFoundException e){
            System.out.println(e);
            return null;
        }
    }
    
      
    public List<UsingWsDomain> getEspecialidades(String subcat){
        try{
            Connection connection = getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("select distinct nm_especialidade_sub_cat, nm_especialidade_sub_cat from _using.especialidade_sub_cat where cd_subcat = ?");   
            PreparedStatement stmt = connection.prepareStatement(sql.toString());   
            stmt.setString(1, subcat);   
            ResultSet rs = stmt.executeQuery();   
            List<UsingWsDomain> lista = new ArrayList<>();
            while(rs.next()){
                UsingWsDomain uwd = new UsingWsDomain();       
                    uwd.setCd_especialidade_sub_cat(rs.getString("nm_especialidade_sub_cat"));
                    uwd.setNm_especialidade_sub_cat(rs.getString("nm_especialidade_sub_cat"));          
                    lista.add(uwd);
            }   
            return lista;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UsingWsDao_rating.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            return null;
        }
    }
    
    public ArrayList<UsingWsDomain> getLojasByEspec(String ValuesChecked){
    
        try {
            Connection connection = getConnection();
            JSONArray js = new JSONArray(ValuesChecked);
            System.out.println(js);
            StringBuilder sql = new StringBuilder();
            
            sql.append("WITH relacao AS (\n" +
"  SELECT DISTINCT f.icon_logo_maps,\n" +
"                  f.latitude_cli,\n" +
"                  f.longitude_cli,\n" +
"                  f.nr_cpf_cnpj,"
                    + "f.nm_razao_social,"
                    + "f.link_logotipo_cliente,"
                    + "f.nome_rua,"
                    + "f.numero_local,"
                    + "f.cd_cep_cli,"
                    + "f.nm_cidade_cli,\n" +
"                  CAST('POINT(' + CAST(? AS VARCHAR(20)) + ' ' + CAST(? AS VARCHAR(20)) + ')' AS GEOGRAPHY).STDistance(\n" +
"                  CAST('POINT(' + CAST(f.latitude_cli AS VARCHAR(20)) + ' ' + CAST(f.longitude_cli AS VARCHAR(20)) + ')' AS GEOGRAPHY)) AS distance\n" +
"    FROM _using.produto p\n" +
"         INNER JOIN _using.cliente f ON p.cnpj_cliente = f.nr_cpf_cnpj\n" +
"   WHERE p.cd_especialidade_sub_cat in (?");
             for (int i = 2; i < js.length()-1; i++){
                sql.append(",?");
            }
             sql.append("))\n" +
"SELECT r.icon_logo_maps,\n" +
"       r.latitude_cli,\n" +
"       r.longitude_cli,\n" +
"       r.nr_cpf_cnpj,"
                     + "r.nm_razao_social,"
                     + "r.link_logotipo_cliente,"
                     + "r.nome_rua,"
                     + "r.numero_local,"
                     + "r.cd_cep_cli,"
                     + "r.nm_cidade_cli \n" +
"  FROM relacao r\n" +
" WHERE r.distance <= 8300;");
            //sql.append("inner join _using.cliente on _using.produto.cnpj_cliente = _using.cliente.nr_cpf_cnpj where _using.produto.nome_especialidade  in (?");
            /*sql.append("inner join precos_cliente on produto.cd_produto = precos_cliente.cd_produto ");
            sql.append("inner join cliente on precos_cliente.nr_cpf_cnpj = cliente.nr_cpf_cnpj where codigos_produto.cd_especialidade_sub_cat  in (?");*/
           
            //sql.append(") HAVING distance <= 10;");
            System.out.println(sql.toString());
            System.out.println(js.length());
            PreparedStatement stmt = connection.prepareStatement(sql.toString());
            stmt.setDouble(1, Double.parseDouble(js.getString(0)));
            stmt.setDouble(2, Double.parseDouble(js.getString(1)));
            //stmt.setDouble(3, Double.parseDouble(js.getString(0)));
            int b = 2;
            int c = 2;
            int a = js.length() - 2;
            for(int i =0 ; i< a; i++){
                System.out.println("STMT " + (i+3) + js.getString(c++));
                stmt.setString(i+3, js.getString(b++));
            }
            
            ResultSet rs = stmt.executeQuery();
            ArrayList<UsingWsDomain> lista = new ArrayList<>();
            while(rs.next()){
            UsingWsDomain uwd = new UsingWsDomain(); 
            uwd.setIcon_logo_maps(rs.getString("icon_logo_maps"));
            uwd.setLatitude_cli(rs.getDouble("latitude_cli"));
            uwd.setLongitu_cli(rs.getDouble("longitude_cli"));
            uwd.setNr_cpf_cnpj(rs.getString("nr_cpf_cnpj"));
            uwd.setNm_razao_social(rs.getString("nm_razao_social"));
            uwd.setLink_logotipo_cliente(rs.getString("link_logotipo_cliente"));
            uwd.setNm_rua("nome_rua");
            uwd.setNr_numero(rs.getInt("numero_local"));
            uwd.setCd_cep_cli(rs.getString("cd_cep_cli"));
            uwd.setCd_cidade_cli(rs.getString("nm_cidade_cli"));
            uwd.setDistanceInKm(calculateDistance(Double.parseDouble(js.getString(0)), Double.parseDouble(js.getString(1)),
                    rs.getDouble("latitude_cli"), rs.getDouble("longitude_cli")));

            lista.add(uwd);
            }
            
            StringBuilder sqlFilial = new StringBuilder();
            
            sqlFilial.append("WITH relacao AS (\n" +
"  SELECT DISTINCT f.icon_logo_maps,\n" +
"                  f.latitude_fil,\n" +
"                  f.longitude_fil,\n" +
"                  f.nr_cnpj_filial,"
                    + "c.nm_razao_social,"
                    + "f.link_logotipo_filial,"
                    + "f.nome_rua,"
                    + "f.nr_numero_fil,"
                    + "f.nr_cep_fil,"
                    + "f.nm_cidade_fil,\n" +
"                  CAST('POINT(' + CAST(? AS VARCHAR(20)) + ' ' + CAST(? AS VARCHAR(20)) + ')' AS GEOGRAPHY).STDistance(\n" +
"                  CAST('POINT(' + CAST(f.latitude_fil AS VARCHAR(20)) + ' ' + CAST(f.longitude_fil AS VARCHAR(20)) + ')' AS GEOGRAPHY)) AS distance\n" +
"    FROM _using.produto p\n" +
"         INNER JOIN _using.filiais f ON p.cnpj_filial = f.nr_cnpj_filial\n" 
                    + "   INNER JOIN _using.cliente c ON f.nr_cpf_cnpj = c.nr_cpf_cnpj " +
"   WHERE p.cd_especialidade_sub_cat in (? \n" );
            for (int i = 2; i < js.length()-1; i++){
                sqlFilial.append(",?");
            }
                    sqlFilial.append(
"))\n" +
"SELECT r.icon_logo_maps,\n" +
"       r.latitude_fil,\n" +
"       r.longitude_fil,\n" +
"       r.nr_cnpj_filial,\n" +
          "r.nm_razao_social,"
                     + "r.link_logotipo_filial,"
                     + "r.nome_rua,"
                     + "r.nr_numero_fil,"
                     + "r.nr_cep_fil,"
                     + "r.nm_cidade_fil \n" +
"  FROM relacao r\n" +
" WHERE r.distance <= 8300;");
            //sqlFilial.append("inner join filiais on produto.cnpj_filial = filiais.nr_cnpj_filial WHERE produto.nome_especialidade  in (?");
          /* sqlFilial.append("inner join precos_filial on produto.cd_produto = precos_filial.cd_produto ");
            sqlFilial.append("inner join filiais on precos_filial.nr_cnpj_filial = filiais.nr_cnpj_filial where codigos_produto.cd_especialidade_sub_cat  in (?");*/
            
            //sqlFilial.append(") HAVING distance <= 10;");
            System.out.println(sqlFilial.toString());
            PreparedStatement stmtFilial = connection.prepareStatement(sqlFilial.toString());
            stmtFilial.setDouble(1, Double.parseDouble(js.getString(0)));
            stmtFilial.setDouble(2, Double.parseDouble(js.getString(1)));
            stmtFilial.setDouble(3, Double.parseDouble(js.getString(0)));
            int bFilial = 2;
            int cFilial = 2;
            int aFilial = js.length() - 2;
            for(int i =0 ; i< aFilial; i++){
                System.out.println("STMT " + (i+3) + js.getString(cFilial++));
                stmtFilial.setString(i+3, js.getString(bFilial++));
            }
            
            ResultSet rsFilial = stmtFilial.executeQuery();
            
            while(rsFilial.next()){
            UsingWsDomain uwd = new UsingWsDomain();
               uwd.setLatitude_cli(rsFilial.getDouble("latitude_fil"));
               uwd.setLongitu_cli(rsFilial.getDouble("longitude_fil"));
               uwd.setNr_cpf_cnpj(rsFilial.getString("nr_cnpj_filial"));
               uwd.setNm_razao_social(rsFilial.getString("nm_razao_social"));
               uwd.setLink_logotipo_cliente(rsFilial.getString("link_logotipo_filial"));
               uwd.setDistanceInKm(calculateDistance( Double.parseDouble(js.getString(0)), Double.parseDouble(js.getString(1)),
                    rsFilial.getDouble("latitude_fil"), rsFilial.getDouble("longitude_fil")));
               uwd.setNm_rua(rsFilial.getString("nome_rua"));
               uwd.setCd_cidade_cli(rsFilial.getString("nm_cidade_fil"));
               uwd.setNr_numero(rsFilial.getShort("nr_numero_fil"));
               uwd.setCd_cep_cli(rsFilial.getString("nr_cep_fil"));
               lista.add(uwd);               
            }  
            return lista;   
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsingWsDao_rating.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
     public ArrayList<UsingWsDomain> PesquisaSemMapa(String subcat){
        try{
            Connection connection = getConnection();
            JSONArray js = new JSONArray(subcat);
            double lati, longi;
            String sub;
                JSONObject jso = js.getJSONObject(0);
                lati = jso.getDouble("lati");
                longi = jso.getDouble("longit");
                sub = jso.getString("subcat");
               
            ArrayList<UsingWsDomain> lista = new ArrayList<>();
            StringBuilder sql2 = new StringBuilder();
            StringBuilder sqlFilial = new StringBuilder();
            sqlFilial.append("WITH relacao AS (\n" +
                "  SELECT DISTINCT \n" +
                "                  f.latitude_fil,\n" +
                "                  f.longitude_fil,\n" +
                "                  f.nr_cnpj_filial,"
                    + "            c.nm_razao_social,"
                    + "            f.link_logotipo_filial,"
                    + "            f.nome_rua,"
                    + "            f.nr_numero_fil,"
                    + "            f.nm_cidade_fil,"
                    + "            f.nr_cep_fil,\n" +
                "                  CAST('POINT(' + CAST(? AS VARCHAR(20)) + ' ' + CAST(? AS VARCHAR(20)) + ')' AS GEOGRAPHY).STDistance(\n" +
                "                  CAST('POINT(' + CAST(f.latitude_fil AS VARCHAR(20)) + ' ' + CAST(f.longitude_fil AS VARCHAR(20)) + ')' AS GEOGRAPHY)) AS distance\n" +
                "    FROM _using.produto p\n" +
                "         INNER JOIN _using.filiais f ON p.cnpj_filial = f.nr_cnpj_filial\n"
                    + "   INNER JOIN _using.cliente c ON f.nr_cpf_cnpj = c.nr_cpf_cnpj " +
                "   WHERE p.subcat = ? \n" +
                ")\n" +
                "SELECT \n" +
                "       r.latitude_fil,\n" +
                "       r.longitude_fil,\n" +
                "       r.nr_cnpj_filial,\n"
                    + " r.nm_razao_social,"
                    + " r.link_logotipo_filial,"
                    + " r.nome_rua,"
                    + " r.nr_numero_fil,"
                    + " r.nm_cidade_fil,"
                    + " r.nr_cep_fil " +
                "  FROM relacao r\n" +
                " WHERE r.distance <= 8300;");
////            sql2.append("select distinct cliente.icon_logo_maps ,cliente.latitude_cli, cliente.longitude_cli, cliente.nr_cpf_cnpj,\n" +
////" (6371 *\n" +
////"        acos(\n" +
////"            cos(radians(?)) *\n" +
////"            cos(radians(cliente.latitude_cli)) *\n" +
////"            cos(radians(?) - radians(cliente.longitude_cli)) +\n" +
////"            sin(radians(?)) *\n" +
////"            sin(radians(cliente.latitude_cli))\n" +
////"        )) AS distancekm from _using.produto  ");
////            sql2.append("inner join _using.cliente on _using.produto.cnpj_cliente = _using.cliente.nr_cpf_cnpj WHERE _using.produto.subcat = ? having COUNT(distancekm) < 7 ;");
           /*  sql2.append("inner join precos_cliente on codigos_produto.cd_produto = precos_cliente.cd_produto ");
            sql2.append("inner join cliente on precos_cliente.nr_cpf_cnpj = cliente.nr_cpf_cnpj where codigos_produto.cd_subcat = ? HAVING distance <= 10");*/
            
            sql2.append("WITH relacao AS (\n" +
                "  SELECT DISTINCT \n" +
                "                  f.latitude_cli,\n" +
                "                  f.longitude_cli,\n" +
                "                  f.nr_cpf_cnpj,"
                    + "            f.nm_razao_social,"
                    + "            f.link_logotipo_cliente,"
                    + "            f.nome_rua,"
                    + "            f.numero_local,"
                    + "            f.nm_cidade_cli,"
                    + "            f.cd_cep_cli,\n" +
                "                  CAST('POINT(' + CAST(? AS VARCHAR(20)) + ' ' + CAST(? AS VARCHAR(20)) + ')' AS GEOGRAPHY).STDistance(\n" +
                "                  CAST('POINT(' + CAST(f.latitude_cli AS VARCHAR(20)) + ' ' + CAST(f.longitude_cli AS VARCHAR(20)) + ')' AS GEOGRAPHY)) AS distance\n" +
                "    FROM _using.produto p\n" +
                "         INNER JOIN _using.cliente f ON p.cnpj_cliente = f.nr_cpf_cnpj\n" +
                "   WHERE p.subcat = ? \n" +
                ")\n" +
                "SELECT \n" +
                "       r.latitude_cli,\n" +
                "       r.longitude_cli,\n" +
                "       r.nr_cpf_cnpj,"
                    + " r.nm_razao_social,"
                    + " r.link_logotipo_cliente,"
                    + " r.nome_rua,"
                    + " r.numero_local,"
                    + " r.nm_cidade_cli,"
                    + " r.cd_cep_cli\n" +
                "  FROM relacao r\n" +
                " WHERE r.distance <= 8300;");            
            PreparedStatement stmtFilial = connection.prepareStatement(sqlFilial.toString());
            PreparedStatement stmt2 = connection.prepareStatement(sql2.toString());
            
            stmtFilial.setString(1,String.valueOf(lati));
            stmtFilial.setString(2, String.valueOf(longi));
            stmtFilial.setString(3, sub);
            //stmtFilial.setString(4, sub);
            
            System.out.println(lati + " " + longi + " " + sub);
            
            stmt2.setString(1,String.valueOf(lati));
            stmt2.setString(2, String.valueOf(longi));
            stmt2.setString(3, sub);
            //stmt2.setString(4, sub);
            
            ResultSet rsFilial = stmtFilial.executeQuery();
            ResultSet rs2 = stmt2.executeQuery();
           
           while(rs2.next()){
               UsingWsDomain uwd = new UsingWsDomain();
               uwd.setLatitude_cli(rs2.getDouble("latitude_cli"));
               uwd.setLongitu_cli(rs2.getDouble("longitude_cli"));
               uwd.setNr_cpf_cnpj(rs2.getString("nr_cpf_cnpj"));
               uwd.setNm_razao_social(rs2.getString("nm_razao_social"));
               uwd.setLink_logotipo_cliente(rs2.getString("link_logotipo_cliente"));
               uwd.setDistanceInKm(calculateDistance( rs2.getDouble("latitude_cli"), rs2.getDouble("longitude_cli"), lati, longi));
               
               uwd.setNm_rua(rs2.getString("nome_rua"));
               uwd.setCd_cidade_cli(rs2.getString("nm_cidade_cli"));
               uwd.setCd_cep_cli(rs2.getString("cd_cep_cli"));
               uwd.setNr_numero(rs2.getInt("numero_local"));
               
               System.out.println("Rua: " + rs2.getString("nome_rua") + " Numero: " + rs2.getInt("numero_local"));
               lista.add(uwd);    
               
           } 
           while(rsFilial.next()){
               UsingWsDomain uwd = new UsingWsDomain();
               uwd.setLatitude_cli(rsFilial.getDouble("latitude_fil"));
               uwd.setLongitu_cli(rsFilial.getDouble("longitude_fil"));
               uwd.setNr_cpf_cnpj(rsFilial.getString("nr_cnpj_filial"));
               uwd.setNm_razao_social(rsFilial.getString("nm_razao_social"));
               uwd.setLink_logotipo_cliente(rsFilial.getString("link_logotipo_filial"));
               uwd.setDistanceInKm(calculateDistance( rsFilial.getDouble("latitude_fil"), rsFilial.getDouble("longitude_fil"), lati, longi));
               uwd.setNm_rua(rsFilial.getString("nome_rua"));
               uwd.setCd_cidade_cli(rsFilial.getString("nm_cidade_fil"));
               uwd.setNr_numero(rsFilial.getShort("nr_numero_fil"));
               uwd.setCd_cep_cli(rsFilial.getString("nr_cep_fil"));
               lista.add(uwd);               
           }
            return lista;
        }catch(SQLException | ClassNotFoundException e){
            System.out.println(e);
            return null;
        }
    }
     
     
     public double calculateDistance(double latStart, double longiStart, double latEnd, double longiEnd){
         
         String sql = "declare @geo1 geography = geography::Point(?, ?, 4326),\n" +
"        @geo2 geography = geography::Point(?, ?, 4326)\n" +
"\n" +
"select @geo1.STDistance(@geo2)";
         
        try {
            Connection conn = getConnection();
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, latStart);
            stmt.setDouble(2, longiStart);
            stmt.setDouble(3, latEnd);
            stmt.setDouble(4, longiEnd);
            
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
            
                double km = rs.getDouble(1)/1000;
                return km;
                
            }else{
            return 0;
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsingWsDao_maps.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
     
        
     }
    
}
