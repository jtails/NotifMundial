package view.dao;

import java.net.URI;
import java.net.URISyntaxException;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.sql.DataSource;

public class ConnectionManager {
   
    private static InitialContext ic=null;
    private static Context initContext;
    private static Context envContext;
    private static DataSource ds=null;
    private static Connection con;
   
    static{
        try {
            /*ic= new InitialContext();
            ds=(DataSource)ic.lookup("jdbc/notifmundial");
            getConnection();*/
                 
            //Tomcat
            initContext = new InitialContext();
            envContext  = (Context)initContext.lookup("java:/comp/env");
            ds = (DataSource)envContext.lookup("jdbc/notifmundial");
            getConnection();
            
        } catch (Exception e) {
          e.printStackTrace();   
        }
    }
    
    public static void getConnection(){
        if(con==null){
            try {
                con = ds.getConnection();
            } catch (Exception e) {
                e.printStackTrace();
            } 
        }
    }
    

  //Heroku
  /*public static void getConnection(){
    URI dbUri=null;
    try {
        dbUri = new URI(System.getenv("DATABASE_URL"));
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
        System.out.println(dbUrl);
        con=DriverManager.getConnection(dbUrl, username, password);
    } 
    catch (Exception e) {
        e.printStackTrace();
    }
  }*/
    
    public static List<Map> executeQuery(String query){
        //Connection con=null;
        Statement st= null;
        ResultSet rs=null;
        List<Map> result=null;
        try {
            if(con==null)
                getConnection();
            st=con.createStatement();
            rs=st.executeQuery(query);
            result=new ArrayList<Map>();
            while(rs.next()){
                Map map=new HashMap();
                //Obtenemos los Datos sin PK
                for(int i=1;i<=rs.getMetaData().getColumnCount();i++){
                    map.put(rs.getMetaData().getColumnName(i),rs.getString(i));    
                }
                result.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            //closeConnection(con);
        }
        return result;
    }
    
    
    public static boolean execute(String query){
        //Connection con=null;
        Statement st= null;
        boolean status=false;
        try {
            if(con==null)
                getConnection();
            st=con.createStatement();
            st.execute(query);
            status=true;
        } catch (SQLException e) {
            e.printStackTrace();
            status=false;
        }finally{
            //closeConnection(con);
        }
        return status;
    }
    
    public static void closeConnection(Connection con){
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void startTransaction(){
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void commit(){
        try {
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void rollback(){
        try {
            con.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
