/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatecweb.services;

import org.springframework.stereotype.Service;

import com.fatecweb.controllers.interfaces.IUsuarioService;
import com.fatecweb.models.Usuario;
import com.fatecweb.repository.UsuarioRepository;

@Service
public class UsuarioService implements IUsuarioService {
    
    UsuarioRepository usuarioRepository;

    public UsuarioService() throws Exception {
        this.usuarioRepository = new UsuarioRepository();
    }
    
    public Usuario verificar(String nome, String senha) throws Exception {
        return usuarioRepository.check(nome, senha);
    }
}
