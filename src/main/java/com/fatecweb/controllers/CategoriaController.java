package com.fatecweb.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatecweb.controllers.interfaces.ICategoriaService;
import com.fatecweb.models.Categoria;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	private ICategoriaService categoriaService;
	
    @CrossOrigin(origins = "")	
	@GetMapping
	public ResponseEntity listCategoria() {
		try {
			List<Categoria> categorias = new ArrayList<Categoria>();
			Iterable<Categoria> result = categoriaService.listar();
			result.forEach(c -> categorias.add(c));
			return new ResponseEntity(categorias, HttpStatus.OK);
		} catch(Exception ex) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(ex.getMessage());
		}
	}
}
