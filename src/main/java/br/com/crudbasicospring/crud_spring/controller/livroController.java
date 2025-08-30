package br.com.crudbasicospring.crud_spring.controller;

import br.com.crudbasicospring.crud_spring.model.Livro;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/livro")
public class livroController {

    ArrayList<Livro> listaDeLivros = new ArrayList<>();
    private int nextId = 1;

    @GetMapping("/teste")
    public void teste () {
        Livro l1 = new Livro(nextId++, "teste", "autor", 1920, "terror", "local", 90);
        listaDeLivros.add(l1);
    }


    @GetMapping
    public ArrayList<Livro> listar() {
        return listaDeLivros;
    }

    @GetMapping("/{id}")
    public Livro buscarLivroId(@PathVariable int id) {
        for (Livro livro : listaDeLivros) {
            if (livro.getId() == id) {
                return livro;
            }
        }
        return null;
    }
    @PostMapping
    public Livro adicionarLivro(@RequestBody Livro l) {
        l.setId(nextId++);
        listaDeLivros.add(l);
        return l;
    }

    @PutMapping("/{id}")
    public Livro atualizarLivroId(@PathVariable int id, @RequestBody Livro l) {
        for (Livro livro : listaDeLivros) {
            if (livro.getId() == id) {
                livro.setTitulo(l.getTitulo());
                livro.setAutor(l.getAutor());
                return livro;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void removerLivroId(@PathVariable int id) {
        listaDeLivros.removeIf(l -> l.getId() == id);
    }


}
