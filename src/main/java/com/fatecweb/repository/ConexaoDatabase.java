/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatecweb.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDatabase {

    private static Connection conexao;
    
    private ConexaoDatabase() { };
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (conexao == null) {
            Class.forName("com.mysql.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetoweb", "root", "exhumed3");
        }
        
        return conexao;
    }
}    

