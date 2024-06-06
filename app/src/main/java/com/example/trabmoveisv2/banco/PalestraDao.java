package com.example.trabmoveisv2.banco;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.trabmoveisv2.Palestra;

import java.util.List;

@Dao
public interface PalestraDao {
    @Insert
    void inserir(Palestra palestra);

    @Query("SELECT * FROM palestras")
    List<Palestra> obterTodas();

    @Query("SELECT * FROM palestras WHERE id = :id LIMIT 1")
    Palestra obterPorId(int id);

    @Query("SELECT * FROM palestras WHERE data LIKE :data")
    List<Palestra> buscarPorData(String data);
}
