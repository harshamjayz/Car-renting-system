/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.clickme.rac.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Harsha madushan
 */
public class DBConnection {
    
    private static DBConnection dbConnection;
    private Connection connection;
    
    private DBConnection() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        File file = new File("resource/application.properties");
        FileReader fileReader = new FileReader(file);
        Properties property = new Properties();
        property.load(fileReader);
                
        String ip =property.getProperty("ip");
        String port = property.getProperty("port");
        String db = property.getProperty("db");
        String username = property.getProperty("username");
        String password = property.getProperty("password");
        
        String url = "jdbc:mysql://" + ip + ":"+ port +"/" + db;
        
        connection = DriverManager.getConnection(url,username,password);
    }
    
    public static DBConnection getInstance() throws Exception{
        if (dbConnection == null){
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }
    
    public Connection getConnection(){
        return connection;
    }
    
}
