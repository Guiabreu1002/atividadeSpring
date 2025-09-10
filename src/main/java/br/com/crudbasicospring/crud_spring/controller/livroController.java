package br.com.crudbasicospring.crud_spring.controller;

import br.com.crudbasicospring.crud_spring.model.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/livro")
public class livroController {


    @Autowired
    private JdbcTemplate jdbcTemplate;
    private Livro[] listaDeLivros;

    @GetMapping("/livro")
    public void teste () {
        String sql = "SELECT * FROM livros WHERE id = ?";
        jdbcTemplate.queryForObject(sql, new Object[]{1}, (rs, rowNum) -> {
            Livro livro = new Livro();
            livro.setId(rs.getInt("id"));
            livro.setTitulo(rs.getString("titulo"));
            livro.setAutor(rs.getString("autor"));
            livro.setAno(rs.getDate("ano").toLocalDate().getYear());
            livro.setGenero(rs.getString("genero"));
            livro.setEditora(rs.getString("editora"));
            livro.setNumeroPaginas(rs.getInt("numero_paginas"));
            return livro;
        });
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
    @PostMapping("/livro")
    public void adicionarLivro(@RequestBody Livro l) {
        String sql = "INSERT INTO livros (titulo, autor, ano, genero, editora, numero_paginas) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(
                sql,
                l.getTitulo(),
                l.getAutor(),
                l.getAno(),
                l.getGenero(),
                l.getEditora(),
                l.getNumeroPaginas()
        );
    }

    @PutMapping("/livro")
    public Livro atualizarLivro(@PathVariable int id, @RequestBody Livro l) {
        for (Livro livro : listaDeLivros) {
            if (livro.getId() == id) {
                livro.setTitulo(l.getTitulo());
                livro.setAutor(l.getAutor());
                livro.setAno(l.getAno());
                livro.setGenero(l.getGenero());
                livro.setEditora(l.getEditora());
                livro.setNumeroPaginas(l.getNumeroPaginas());
                return livro;
            }
        }
        return null;
    }

    @DeleteMapping("/livro/{id}")
    public void removerLivroId(@PathVariable int id) {
        String sql = "DELETE FROM livros WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }


}
