package com.example.trabmoveisv2;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trabmoveisv2.network.ApiService;
import com.example.trabmoveisv2.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalhesPalestranteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_palestrante);

        int palestranteId = getIntent().getIntExtra("palestrante_id", -1);

        TextView nome = findViewById(R.id.nome_palestrante);
        TextView biografia = findViewById(R.id.biografia_palestrante);

        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<Pessoa> call = apiService.obterPessoa(palestranteId);
        call.enqueue(new Callback<Pessoa>() {
            @Override
            public void onResponse(Call<Pessoa> call, Response<Pessoa> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Pessoa pessoa = response.body();
                    nome.setText(pessoa.getNome());
                    biografia.setText(pessoa.getBiografia());
                }
            }

            @Override
            public void onFailure(Call<Pessoa> call, Throwable t) {

            }
        });
    }
}
