package com.example.trabmoveisv2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetalhesMinicursoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_minicurso);

        Minicurso minicurso = (Minicurso) getIntent().getSerializableExtra("minicurso");

        TextView titulo = findViewById(R.id.nome_minicurso);
        TextView descricao = findViewById(R.id.descricao_minicurso);
        Button verInstrutor = findViewById(R.id.botao_ver_instrutor);

        titulo.setText(minicurso.getTitulo());
        descricao.setText(minicurso.getDescricao());

        verInstrutor.setOnClickListener(v -> {
            Intent intent = new Intent(DetalhesMinicursoActivity.this, DetalhesInstrutorActivity.class);
            intent.putExtra("instrutor_id", minicurso.getInstrutorId());
            startActivity(intent);
        });
    }
}
