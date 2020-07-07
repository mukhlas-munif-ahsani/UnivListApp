package com.example.univlist.TambahData4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.univlist.R;

import java.util.List;

public class Tambah4Adapter extends RecyclerView.Adapter<Tambah4Adapter.ViewHolder> {

    private List<String> mTahunJurusan;
    private List<String> mSekolahJurusan;
    private List<String> mDiterimaJurusan;
    private Context context;

    public Tambah4Adapter(List<String> mTahunJurusan, List<String> mSekolahJurusan, List<String> mDiterimaJurusan, Context context) {
        this.mTahunJurusan = mTahunJurusan;
        this.mSekolahJurusan = mSekolahJurusan;
        this.mDiterimaJurusan = mDiterimaJurusan;
        this.context = context;
    }

    @NonNull
    @Override
    public Tambah4Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sebaran_siswa_jurusan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Tambah4Adapter.ViewHolder holder, int position) {
        String sekolah = mSekolahJurusan.get(position);
        String tahun = mTahunJurusan.get(position);
        String diterima = mDiterimaJurusan.get(position);

        holder.jurusan.setText(sekolah);
        holder.tahun.setText(tahun);
        holder.diterima.setText(diterima);

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
        return mSekolahJurusan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView jurusan, tahun, diterima;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            jurusan = itemView.findViewById(R.id.sekolah_jurusan);
            tahun = itemView.findViewById(R.id.tahun_jurusan);
            diterima = itemView.findViewById(R.id.diterima_jurusan);
        }
    }
}
