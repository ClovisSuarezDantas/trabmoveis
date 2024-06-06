package com.example.trabmoveisv2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetalhesPalestraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_palestra);

        Palestra palestra = (Palestra) getIntent().getSerializableExtra("palestra");

        TextView titulo = findViewById(R.id.titulo_palestra);
        TextView descricao = findViewById(R.id.descricao_palestra);
        Button verPalestrante = findViewById(R.id.botao_ver_palestrante);

        titulo.setText(palestra.getTitulo());
        descricao.setText(palestra.getDescricao());

        verPalestrante.setOnClickListener(v -> {
            Intent intent = new Intent(DetalhesPalestraActivity.this, DetalhesPalestranteActivity.class);
            intent.putExtra("palestrante_id", palestra.getPalestranteId());
            startActivity(intent);
        });
    }
}
