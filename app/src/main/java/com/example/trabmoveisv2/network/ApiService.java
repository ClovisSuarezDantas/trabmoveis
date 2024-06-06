package com.example.trabmoveisv2.network;

import com.example.trabmoveisv2.Minicurso;
import com.example.trabmoveisv2.Palestra;
import com.example.trabmoveisv2.Pessoa;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("palestras.json")
    Call<List<Palestra>> obterPalestras();

    @GET("minicursos.json")
    Call<List<Minicurso>> obterMinicursos();

    @GET("pessoas.json")
    Call<List<Pessoa>> obterPessoas();

    @GET("pessoas/{id}.json")
    Call<Pessoa> obterPessoa(@Path("id") int id);
}
