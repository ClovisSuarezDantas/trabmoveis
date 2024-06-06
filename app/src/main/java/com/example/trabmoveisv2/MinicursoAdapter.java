package com.example.trabmoveisv2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MinicursoAdapter extends BaseAdapter {

    private Context context;
    private List<Minicurso> listaMinicursos;
    private static final String TAG = "MinicursoAdapter";

    public MinicursoAdapter(Context context, List<Minicurso> listaMinicursos) {
        this.context = context;
        this.listaMinicursos = listaMinicursos;
    }

    @Override
    public int getCount() {
        return listaMinicursos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaMinicursos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_minicurso, parent, false);
        }

        Minicurso minicurso = listaMinicursos.get(position);

        TextView titulo = convertView.findViewById(R.id.nome_minicurso);
        TextView data = convertView.findViewById(R.id.data_minicurso);
        TextView horarios = convertView.findViewById(R.id.horarios_minicurso);

        titulo.setText(minicurso.getTitulo());
        data.setText(minicurso.getData());
        horarios.setText(minicurso.getHorarios());

        return convertView;
    }
}
