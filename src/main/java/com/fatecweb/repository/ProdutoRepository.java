/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatecweb.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.fatecweb.models.Categoria;
import com.fatecweb.models.Produto;

/**
 *
 * @author MAQLAB
 */
public class ProdutoRepository {
    ConexaoBD conexaoBD;
    
    List<Produto> produtos = new ArrayList<Produto>();

    public ProdutoRepository() throws Exception {
        conexaoBD = ConexaoBD.getInstance();
    }
    
    public Produto getById(int idProduto) throws Exception {
        Produto produto = null;
        
        PreparedStatement ps = conexaoBD.getConnection().prepareStatement("SELECT c.nome as nomeCategoria, c.id as idCategoria, p.*"
                + " FROM produto p JOIN categoria c ON p.idCategoria = c.id WHERE p.id = ?");
        ps.setInt(1, idProduto);
        ResultSet rs = ps.executeQuery();

        if (rs.next())
        {
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            float preco = rs.getFloat("preco");

            int idCategoria = rs.getInt("idCategoria");
            String nomeCategoria = rs.getString("nomeCategoria");

            Categoria categoriaProduto = new Categoria(idCategoria, nomeCategoria);

            produto = new Produto(id, nome, preco, categoriaProduto);
        }
        
        return produto;
    }

    public List<Produto> getAll() throws Exception {
        List<Produto> produtos = new ArrayList<Produto>();
        
        PreparedStatement ps = conexaoBD.getConnection().prepareStatement(
                "SELECT c.nome as nomeCategoria, c.id as idCategoria, p.*"
                + " FROM produto p JOIN categoria c ON p.idCategoria = c.id");
        ResultSet rs = ps.executeQuery();

        while(rs.next())
        {
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            float preco = rs.getFloat("preco");

            int idCategoria = rs.getInt("idCategoria");
            String nomeCategoria = rs.getString("nomeCategoria");

            Categoria categoriaProduto = new Categoria(idCategoria, nomeCategoria);
            Produto produto = new Produto(id, nome, preco, categoriaProduto);

            produtos.add(produto);
        }
        
        return produtos;
    }

    public Produto insert(Produto produto) throws Exception {
        PreparedStatement ps = conexaoBD.getConnection().prepareStatement(
                "INSERT INTO Produto(nome, preco, idCategoria) VALUES (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);
                
        ps.setString(1, produto.getNome());
        ps.setDouble(2, produto.getPreco());
        ps.setInt(3, produto.getCategoria().getId());
        
        ps.executeUpdate();
        
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            int idProduto = rs.getInt(1);
            produto.setId(idProduto);
        }
        
        return produto;
    }
}
