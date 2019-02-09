/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatecweb.services;

import com.fatecweb.controllers.interfaces.ICategoriaService;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fatecweb.models.Categoria;
import com.fatecweb.repository.CategoriaRepository;

@Service
public class CategoriaService implements ICategoriaService {
    
    CategoriaRepository categoriaRepository;
    
    public CategoriaService() throws Exception {
        categoriaRepository = new CategoriaRepository();
    }
    
    @Override
    public Categoria buscarPeloId(int idCategoria) throws Exception {
        return categoriaRepository.getById(idCategoria);
    }

    @Override
    public List<Categoria> listar() throws Exception {
        return categoriaRepository.getAll();
    }
}
