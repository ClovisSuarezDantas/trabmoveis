package com.example.trabmoveisv2;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trabmoveisv2.banco.AppDatabase;
import com.example.trabmoveisv2.network.ApiService;
import com.example.trabmoveisv2.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MinicursosActivity extends AppCompatActivity {

    private List<Minicurso> listaMinicursos;
    private MinicursoAdapter adapter;
    private AppDatabase db;
    private static final String TAG = "MinicursosActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minicursos);

        db = AppDatabase.getDatabase(this);

        listaMinicursos = new ArrayList<>();

        ListView listView = findViewById(R.id.lista_minicursos);
        adapter = new MinicursoAdapter(this, listaMinicursos);
        listView.setAdapter(adapter);

        buscarMinicursosDaAPI();

        EditText buscarData = findViewById(R.id.buscar_data_minicurso);
        buscarData.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filtrarPorData(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Minicurso minicurso = (Minicurso) parent.getItemAtPosition(position);
            Intent intent = new Intent(MinicursosActivity.this, DetalhesMinicursoActivity.class);
            intent.putExtra("minicurso", minicurso);
            startActivity(intent);
        });
    }

    private void buscarMinicursosDoBancoDeDados() {
        listaMinicursos.clear();
        listaMinicursos.addAll(db.minicursoDao().obterTodos());
        adapter.notifyDataSetChanged();
    }

    private void buscarMinicursosDaAPI() {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<List<Minicurso>> call = apiService.obterMinicursos();
        call.enqueue(new Callback<List<Minicurso>>() {
            @Override
            public void onResponse(Call<List<Minicurso>> call, Response<List<Minicurso>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d(TAG, "Minicursos recebidos: " + response.body().toString());
                    for (Minicurso minicurso : response.body()) {
                        // Verifica se o minicurso já existe no banco de dados
                        if (db.minicursoDao().obterPorId(minicurso.getId()) == null) {
                            db.minicursoDao().inserir(minicurso);
                        }
                    }
                    buscarMinicursosDoBancoDeDados();
                } else {
                    buscarMinicursosDoBancoDeDados();
                }
            }

            @Override
            public void onFailure(Call<List<Minicurso>> call, Throwable t) {
                buscarMinicursosDoBancoDeDados();
            }
        });
    }

    private void filtrarPorData(String data) {
        listaMinicursos.clear();
        listaMinicursos.addAll(db.minicursoDao().buscarPorData("%" + data + "%"));
        adapter.notifyDataSetChanged();
    }
}
