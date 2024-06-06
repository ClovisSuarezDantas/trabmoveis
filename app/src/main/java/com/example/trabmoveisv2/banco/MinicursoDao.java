package com.example.trabmoveisv2.banco;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.trabmoveisv2.Minicurso;

import java.util.List;

@Dao
public interface MinicursoDao {
    @Insert
    void inserir(Minicurso minicurso);

    @Query("SELECT * FROM minicursos")
    List<Minicurso> obterTodos();

    @Query("SELECT * FROM minicursos WHERE id = :id LIMIT 1")
    Minicurso obterPorId(int id);

    @Query("SELECT * FROM minicursos WHERE data LIKE :data")
    List<Minicurso> buscarPorData(String data);
}
