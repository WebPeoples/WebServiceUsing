package br.com.ws.dao;

import static br.com.ws.Factory.Connect.getConnection;
import com.google.gson.Gson;
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
public class UsingWsDao_user {
    public String cadastroUser(String dataUser){
          try{
              Connection connection = getConnection();
              JSONArray js = new JSONArray(dataUser);
              
              String cd_user = js.getString(2);
              int arroba = cd_user.indexOf("@");
              System.out.println(js);
              
              String sql = "INSERT INTO _using.usuario_app (cd_user, nm_user,nr_telefone1_usr, senha, email_user, cd_status_usr) VALUES(?,?,?,?,?,'A');";
              PreparedStatement stmt = connection.prepareStatement(sql);
              
              stmt.setString(1, cd_user.substring(0, arroba));
              stmt.setString(2, js.getString(0));
                  stmt.setDouble(3, Double.parseDouble(js.getString(1)));
              stmt.setString(4, js.getString(3));
              stmt.setString(5, js.getString(2));
              stmt.execute();
              
              return js.getString(0) + " cadastrado com sucesso.";
              
              
          } catch (ClassNotFoundException | SQLException ex) {
            return "Erro\n" + ex;
        } 
      }
      
        public String login(String dataUser){
          try{
              Connection connection = getConnection();
              JSONArray js = new JSONArray(dataUser);           
              
              String sql = "Select nm_user, cd_user, email_user, senha from _using.usuario_app where email_user = ? and senha =?;";
              PreparedStatement stmt = connection.prepareStatement(sql);
              
              stmt.setString(1, js.getString(0));
              stmt.setString(2, js.getString(1));
             
              ResultSet rs = stmt.executeQuery();
              
                  Gson g = new Gson();
                  List<String> lista = new ArrayList<>();
              if(rs.next()){
                  lista.add(rs.getString("email_user"));
                  lista.add(rs.getString("nm_user"));                  
                  lista.add(rs.getString("cd_user"));                  

              }
              
              return g.toJson(lista);              
          } catch (ClassNotFoundException | SQLException ex) {
           
            return "f";
        } 
           
      }
        
        public String regiterToken(String token){  
            try {
                Connection conn = getConnection();
                JSONArray js = new JSONArray(token);


		String queryForToken = "SELECT * FROM _using.token WHERE token = ? ";

		PreparedStatement stmtQueryForToken = conn.prepareStatement(queryForToken);

		stmtQueryForToken.setString(1,js.getString(0));

		ResultSet rsQueryForToken = stmtQueryForToken.executeQuery();

			if(rsQueryForToken.next()){
				String updateToken = "UPDATE _using.token SET cd_user = ? WHERE token = ? ";
				PreparedStatement stmtUpdateToken = conn.prepareStatement(updateToken);
				stmtUpdateToken.setString(1,js.getString(1));
                                stmtUpdateToken.setString(2,js.getString(0));
				stmtUpdateToken.executeUpdate();
				  return "ALTERADO COM SUCESSO";
			}else{
				String sql = "INSERT INTO _using.token (token, cd_user) VALUES(?,?)";
				PreparedStatement stmt = conn.prepareStatement(sql);                
		                stmt.setString(1, js.getString(0));
                		stmt.setString(2, js.getString(1));
		                stmt.executeUpdate();
				return "INSERIDO COM SUCESSO";
			}
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(UsingWsDao_user.class.getName()).log(Level.SEVERE, null, ex);
                return "NÃO FOI POSSÍVEL REGISTRAR O TOKEN DO USUÁRIO";
            }
        }

}
        
       
