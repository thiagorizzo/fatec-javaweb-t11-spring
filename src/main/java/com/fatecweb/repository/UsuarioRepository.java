/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatecweb.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.fatecweb.models.Categoria;
import com.fatecweb.models.Produto;
import com.fatecweb.models.Usuario;

/**
 *
 * @author FATECRP
 */
public class UsuarioRepository {
     ConexaoBD conexaoBD;
    
    public UsuarioRepository() throws Exception {
        conexaoBD = ConexaoBD.getInstance();
    }
    
    public Usuario check(String name, String password) throws Exception {
        Usuario usuario = null;
        
        PreparedStatement ps = conexaoBD.getConnection().prepareStatement("SELECT * FROM Usuario WHERE nome = ? AND senha = ?");
        ps.setString(1, name);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();

        if (rs.next())
        {
            int id = rs.getInt("id");
            String nome = rs.getString("nome");

            usuario = new Usuario(nome, id);
        }
        
        return usuario;
    }
}
