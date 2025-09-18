package br.com.crudbasicospring.crud_spring.repository;

import br.com.crudbasicospring.crud_spring.model.Livro;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LivroRepository {

    private final JdbcTemplate jdbc;

    public LivroRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Livro> findAll() {
        return jdbc.query("SELECT * FROM livro", new BeanPropertyRowMapper<>(Livro.class));
    }

    public Livro findById(Integer id) {
        return jdbc.queryForObject("SELECT * FROM livro WHERE id = ?",
                new BeanPropertyRowMapper<>(Livro.class), id);
    }

    public List<Livro> findByNome(String nome) {
        return jdbc.query("SELECT * FROM livro WHERE titulo LIKE ?",
                new BeanPropertyRowMapper<>(Livro.class), "%" + nome + "%");
    }

    public int save(Livro livro) {
        return jdbc.update("INSERT INTO livro (titulo, autor, ano, genero, editora, numero_paginas) VALUES (?,?,?,?,?,?)",
                livro.getTitulo(), livro.getAutor(), livro.getAno(), livro.getGenero(), livro.getEditora(), livro.getNumeroPaginas());
    }

    public int update(Integer id, Livro livro) {
        return jdbc.update("UPDATE livro SET titulo=?, autor=?, ano=?, genero=?, editora=?, numero_paginas=? WHERE id=?",
                livro.getTitulo(), livro.getAutor(), livro.getAno(), livro.getGenero(), livro.getEditora(), livro.getNumeroPaginas(), id);
    }

    public int delete(Integer id) {
        return jdbc.update("DELETE FROM livro WHERE id=?", id);
    }
}