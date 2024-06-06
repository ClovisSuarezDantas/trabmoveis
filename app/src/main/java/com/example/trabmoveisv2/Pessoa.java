package com.example.trabmoveisv2;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Pessoa implements Serializable {
    private int id;
    @SerializedName("nome")
    private String nome;

    @SerializedName("bio")
    private String biografia;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public String getBiografia() {
        return biografia;
    }
}
