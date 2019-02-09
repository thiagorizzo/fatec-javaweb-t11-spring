/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatecweb.controllers.interfaces;

import java.util.List;
import com.fatecweb.models.Categoria;

/**
 *
 * @author MAQ01LAB04
 */
public interface ICategoriaService {
    Categoria buscarPeloId(int idCategoria) throws Exception;
    List<Categoria> listar() throws Exception;
}
