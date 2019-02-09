/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatecweb.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.fatecweb.models.Categoria;
import com.fatecweb.models.Produto;

/**
 *
 * @author MAQ01LAB04
 */
public class CategoriaRepository {
    ConexaoBD conexaoBD;
    
    public CategoriaRepository() throws ClassNotFoundException, SQLException {
        conexaoBD = ConexaoBD.getInstance();
    }
    
    public List<Categoria> getAll() throws Exception {
        Connection connection = conexaoBD.getConnection();
        
        List<Categoria> categorias = new ArrayList<Categoria>();
        
        PreparedStatement ps = connection
                .prepareStatement("SELECT * FROM Categoria");
        
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            categorias.add(new Categoria(id, nome));
        }
        
        return categorias;
    }
    
    public Categoria getById(int idCategoria) throws SQLException {
        Connection connection = conexaoBD.getConnection();
        
        Categoria categoria = null;
        
        PreparedStatement ps = connection
                .prepareStatement("SELECT * FROM Categoria WHERE id = ?");
        ps.setInt(1, idCategoria);
        
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            
            categoria = new Categoria(idCategoria, nome);
        }
        
        return categoria;
    }
}
