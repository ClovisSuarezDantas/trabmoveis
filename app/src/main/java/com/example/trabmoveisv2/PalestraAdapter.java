package com.example.trabmoveisv2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class PalestraAdapter extends BaseAdapter {

    private Context context;
    private List<Palestra> listaPalestras;
    private static final String TAG = "PalestraAdapter";

    public PalestraAdapter(Context context, List<Palestra> listaPalestras) {
        this.context = context;
        this.listaPalestras = listaPalestras;
    }

    @Override
    public int getCount() {
        return listaPalestras.size();
    }

    @Override
    public Object getItem(int position) {
        return listaPalestras.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_palestra, parent, false);
        }

        Palestra palestra = listaPalestras.get(position);

        TextView titulo = convertView.findViewById(R.id.nome_palestra);
        TextView data = convertView.findViewById(R.id.data_palestra);
        TextView horarios = convertView.findViewById(R.id.horarios_palestra);

        titulo.setText(palestra.getTitulo());
        data.setText(palestra.getData());
        horarios.setText(palestra.getHorarios());

        return convertView;
    }
}
