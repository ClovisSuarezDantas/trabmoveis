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

public class AgendaActivity extends AppCompatActivity {

    private List<Palestra> listaPalestras;
    private PalestraAdapter adapter;
    private AppDatabase db;
    private static final String TAG = "AgendaActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        db = AppDatabase.getDatabase(this);

        listaPalestras = new ArrayList<>();

        ListView listView = findViewById(R.id.lista_palestras);
        adapter = new PalestraAdapter(this, listaPalestras);
        listView.setAdapter(adapter);

        buscarPalestrasDaAPI();

        EditText buscarData = findViewById(R.id.buscar_data);
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
            Palestra palestra = (Palestra) parent.getItemAtPosition(position);
            Intent intent = new Intent(AgendaActivity.this, DetalhesPalestraActivity.class);
            intent.putExtra("palestra", palestra);
            startActivity(intent);
        });
    }

    private void buscarPalestrasDoBancoDeDados() {
        listaPalestras.clear();
        listaPalestras.addAll(db.palestraDao().obterTodas());
        adapter.notifyDataSetChanged();
    }

    private void buscarPalestrasDaAPI() {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<List<Palestra>> call = apiService.obterPalestras();
        call.enqueue(new Callback<List<Palestra>>() {
            @Override
            public void onResponse(Call<List<Palestra>> call, Response<List<Palestra>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d(TAG, "Palestras recebidas: " + response.body().toString());
                    for (Palestra palestra : response.body()) {
                        // Verifica se a palestra já existe no banco de dados
                        if (db.palestraDao().obterPorId(palestra.getId()) == null) {
                            db.palestraDao().inserir(palestra);
                        }
                    }
                    buscarPalestrasDoBancoDeDados();
                } else {
                    buscarPalestrasDoBancoDeDados();
                }
            }

            @Override
            public void onFailure(Call<List<Palestra>> call, Throwable t) {
                buscarPalestrasDoBancoDeDados();
            }
        });
    }

    private void filtrarPorData(String data) {
        listaPalestras.clear();
        listaPalestras.addAll(db.palestraDao().buscarPorData("%" + data + "%"));
        adapter.notifyDataSetChanged();
    }
}
