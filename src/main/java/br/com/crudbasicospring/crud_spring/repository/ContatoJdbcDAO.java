package br.com.crudbasicospring.crud_spring.repository;

import br.com.crudbasicospring.crud_spring.model.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ContatoJdbcDAO {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void salvarLivro(Livro livro) {
        String sql = "INSERT INTO livros (titulo, autor, ano, genero, editora, numero_paginas) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(
                sql,
                livro.getTitulo(),
                livro.getAutor(),
                livro.getAno(),
                livro.getGenero(),
                livro.getEditora(),
                livro.getNumeroPaginas()
        );
    }

    public void buscarLivro (int id) {
        String sql = "SELECT * FROM livros WHERE id = ?";
        jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
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

    public void atualizarLivro (Livro livro) {
        String sql = "UPDATE livros SET titulo = ?, autor = ?, ano = ?, genero = ?, editora = ?, numero_paginas = ? WHERE id = ?";
        jdbcTemplate.update(
                sql,
                livro.getTitulo(),
                livro.getAutor(),
                livro.getAno(),
                livro.getGenero(),
                livro.getEditora(),
                livro.getNumeroPaginas(),
                livro.getId()
        );
    }

    public void deletarLivro (int id) {
        String sql = "DELETE FROM livros WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

}
