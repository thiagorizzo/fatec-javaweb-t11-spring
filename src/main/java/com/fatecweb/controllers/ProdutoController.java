package com.fatecweb.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	private IProdutoService produtoService;
	private ICategoriaService categoriaService;
	
    @Autowired
    public ProdutoController(IProdutoService produtoService, ICategoriaService categoriaService) {
    	this.produtoService = produtoService;
    	this.categoriaService = categoriaService;
	}

    // "{ 'id': 1, 'preco': 3.5, 'categoria': { "
    @PostMapping(path = "/create")
    public ResponseEntity<Produto> createProdutoBody(@RequestBody Produto produto) throws Exception {
		return null;
	}
	
    @PostMapping()
    public ResponseEntity createProduto(@RequestParam(name="nome") String nome, @RequestParam(name="preco") float preco, @RequestParam(name="categoria") int idCategoria) {
    	try {
	    	Optional<Categoria> optionalCategoria = categoriaService.buscarPeloId(idCategoria);
	    	if (optionalCategoria.isPresent()) {
	    		Categoria categoria = optionalCategoria.get();
	    		Produto produtoCriado = produtoService.cadastrarProduto(new Produto(nome, preco, categoria));
	    		return new ResponseEntity<Produto>(produtoCriado, HttpStatus.CREATED);
	    	} else
	    		return ResponseEntity
	    				.status(HttpStatus.INTERNAL_SERVER_ERROR)
	    	            .body("Categoria não encontrada.");
    	} catch(Exception ex) {
    		return ResponseEntity
    				.status(HttpStatus.INTERNAL_SERVER_ERROR)
    	            .body(ex.getMessage());
    	}
    }    
    
    /*@PostMapping()
    public ResponseEntity<Produto> createProduto(@RequestBody Produto produto) throws Exception {
    	Produto produtoCriado = produtoService.cadastrarProduto(produto);
    	return new ResponseEntity<>(produtoCriado, HttpStatus.CREATED);
    }*/
    
    @CrossOrigin(origins = "http://localhost:9002")    
    @GetMapping()
    public ResponseEntity listProdutos() throws Exception {
    	try {
	    	List<Produto> produtos = produtoService.listarProdutos();
	        return new ResponseEntity<>(produtos.toArray(new Produto[produtos.size()]), HttpStatus.OK);
    	} catch(Exception ex) {
    		return ResponseEntity
    				.status(HttpStatus.INTERNAL_SERVER_ERROR)
    	            .body(ex.getMessage());
    	}	        
    }
    
    // http://localhost:8080/produto/categoria/1
    
    //@RequestMapping(value="/categoria/{categoria}", method=RequestMethod.GET)
    @GetMapping(path = "/categoria/{categoria}")
    public ResponseEntity<Produto[]> listProdutosCategoria(@PathVariable("categoria") int idCategoria) throws Exception {
    	List<Produto> produtos = produtoService.listarProdutosCategoria(idCategoria);
        return new ResponseEntity<>(produtos.toArray(new Produto[produtos.size()]), HttpStatus.OK);
    }    
    
    // http://localhost:8080/produto/detalhe?id=3
    
    @GetMapping(path = "/detalhe")
    public ResponseEntity detailProdutos(@RequestParam(name="id") int id) throws Exception {
    	try {
	    	Optional<Produto> optionalProduto = produtoService.detalharProduto(id);
	    	if (optionalProduto.isPresent())
	    	{
	    		Produto produto = optionalProduto.get();
	            return new ResponseEntity<>(produto, HttpStatus.OK);
	    	}
			else
	    		return ResponseEntity
	    				.status(HttpStatus.INTERNAL_SERVER_ERROR)
	    	            .body("produto não encontrado.");
    	} catch(Exception ex) {
    		return ResponseEntity
    				.status(HttpStatus.INTERNAL_SERVER_ERROR)
    	            .body(ex.getMessage());
    	}
    }        
}
