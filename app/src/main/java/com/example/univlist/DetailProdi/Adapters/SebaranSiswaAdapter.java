package com.example.univlist.DetailProdi.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.univlist.R;

import java.util.List;

public class SebaranSiswaAdapter extends RecyclerView.Adapter<SebaranSiswaAdapter.ViewHolder> {

    private List<Integer> mSebaranTahun;
    private List<Integer> mSebaranPendaftar;
    private List<Integer> mSebaranDiterima;
    private Context context;

    public SebaranSiswaAdapter(List<Integer> mSebaranTahun, List<Integer> mSebaranPendaftar, List<Integer> mSebaranDiterima, Context context) {
        this.mSebaranTahun = mSebaranTahun;
        this.mSebaranPendaftar = mSebaranPendaftar;
        this.mSebaranDiterima = mSebaranDiterima;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sebaran_siswa_tahun, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Integer tahun = mSebaranTahun.get(position);
        Integer pendaftar = mSebaranPendaftar.get(position);
        Integer diterima = mSebaranDiterima.get(position);

        holder.tahun.setText(tahun.toString());
        holder.pendaftar.setText(pendaftar.toString());
        holder.diterima.setText(diterima.toString());

        Integer persen = (diterima*100)/pendaftar;
        holder.persen.setText(persen.toString());

        if (position % 2 == 1) {
            // Set a background color for ListView regular row/item
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.white));
        } else {
            // Set the background color for alternate row/item
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.grey_50));
        }
    }

    @Override
    public int getItemCount() {
        return mSebaranTahun.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tahun, pendaftar, diterima, persen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tahun = itemView.findViewById(R.id.tahun);
            pendaftar = itemView.findViewById(R.id.pendaftar);
            diterima = itemView.findViewById(R.id.diterima);
            persen = itemView.findViewById(R.id.persen);

        }
    }
}
