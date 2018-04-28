/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ws.Factory;
    
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Goku
 */
public class Connect {

    private static final String URL = "jdbc:mysql://localhost:3306/_using?zeroDateTimeBehavior=convertToNull";
    private static final String USER = "root";
    private static final String SENHA = "";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

//    public static Connection getConnection() throws ClassNotFoundException {
//        Connection connection = null;
//
//        try {
//            Class.forName(DRIVER);
//
//            connection = DriverManager.getConnection(URL, USER, SENHA);
//            String conexao;
//            conexao = "Conectado com sucesso em :" + URL;
//            System.out.println(conexao);
//            return connection;
//
//        } catch (SQLException e) {
//
//            e.printStackTrace();
//            return null;
//        }
//
//    }
    
    public static Connection getConnection() throws ClassNotFoundException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=_using;user=sa;password=sparda";
        try {
            Connection conn = DriverManager.getConnection(url);
            System.out.println("CONECTADO COM SUCESSO.");
            return conn;
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
//    public static String getData(){
//        
//        String sql = "select * from _using.usuario_app";
//        
//        try {
//            Connection conn = getConnection();
//            
//            Statement statement = conn.createStatement();
//            
//            ResultSet rs = statement.executeQuery(sql);
//            String result = null;
//            while(rs.next()){
//                result = rs.getString("cd_user");
//                
//            }
//            
//            return result;
//            
//        } catch (ClassNotFoundException | SQLException ex) {
//            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
//            return "Deu merda";
//        }
//        
//    }

//    public static void main(String[] args) throws ClassNotFoundException {
//        System.out.println(getData()); 
//    }

}
