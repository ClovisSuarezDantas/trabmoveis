package com.example.trabmoveisv2;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "palestras")
public class Palestra implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @SerializedName("nome")
    private String titulo;

    @SerializedName("descricao")
    private String descricao;

    @SerializedName("data")
    private String data;

    @SerializedName("hora")
    private String horarios;

    @SerializedName("palestrante_id")
    private int palestranteId;

    // Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorarios() {
        return horarios;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }

    public int getPalestranteId() {
        return palestranteId;
    }

    public void setPalestranteId(int palestranteId) {
        this.palestranteId = palestranteId;
    }
}
