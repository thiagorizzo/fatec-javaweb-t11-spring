package com.fatecweb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fatecweb.controllers.interfaces.ICategoriaService;
import com.fatecweb.controllers.interfaces.IProdutoService;
import com.fatecweb.models.Categoria;
import com.fatecweb.models.Produto;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	private final IProdutoService produtoService;
	private final ICategoriaService categoriaService;
	
    @Autowired
    public ProdutoController(IProdutoService produtoService, ICategoriaService categoriaService) {
    	this.produtoService = produtoService;
    	this.categoriaService = categoriaService;
	}	
	
    @PostMapping()
    public ResponseEntity<Produto> createProduto(@RequestParam(name="nome") String nome, @RequestParam(name="preco") float preco, @RequestParam(name="categoria") int idCategoria) throws Exception {
    	Categoria categoria = categoriaService.buscarPeloId(idCategoria);
    	Produto produtoCriado = produtoService.cadastrarProduto(new Produto(nome, preco, categoria));
    	return new ResponseEntity<>(produtoCriado, HttpStatus.CREATED);
    }    
    
    /*@PostMapping()
    public ResponseEntity<Produto> createProduto(@RequestBody Produto produto) throws Exception {
    	Produto produtoCriado = produtoService.cadastrarProduto(produto);
    	return new ResponseEntity<>(produtoCriado, HttpStatus.CREATED);
    }*/
    
    @GetMapping()
    public ResponseEntity<Produto[]> listProdutos() throws Exception {
    	List<Produto> produtos = produtoService.listarProdutos();
        return new ResponseEntity<>(produtos.toArray(new Produto[produtos.size()]), HttpStatus.OK);
    }
    
    @GetMapping(path = "/detalhe")
    public ResponseEntity<Produto> detailProdutos(@RequestParam(name="id") int id) throws Exception {
    	Produto produto = produtoService.detalharProduto(id);
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }        
}
