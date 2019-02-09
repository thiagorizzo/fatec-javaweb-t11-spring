package com.fatecweb.repository;

// DESIGN PATTERN SINGLETON - apenas 1 instância por classe

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexaoBD {
    
    private Connection connection;
    
    private static ConexaoBD conexao;
    
    // Construtor privado
    private ConexaoBD() throws ClassNotFoundException, SQLException
    { 
        Class.forName("com.mysql.jdbc.Driver");
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetoweb?serverTimezone=UTC", "root", "");
    }
    
    public static ConexaoBD getInstance() throws ClassNotFoundException, SQLException {
        if (conexao == null)
            conexao = new ConexaoBD();
        
        return conexao;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public static ConexaoBD getConexao() {
        return conexao;
    }

    public static void setConexao(ConexaoBD conexao) {
        ConexaoBD.conexao = conexao;
    }
}
