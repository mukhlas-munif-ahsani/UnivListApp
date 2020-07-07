package com.example.univlist.TambahData2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.univlist.R;

import java.util.List;

public class TambahDataProgramPilihanAdapter extends RecyclerView.Adapter<TambahDataProgramPilihanAdapter.ViewHolder> {

    private List<String> programPilihan;
    private Context context;

    public TambahDataProgramPilihanAdapter(List<String> programPilihan, Context context) {
        this.programPilihan = programPilihan;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pilihan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String pilihan = programPilihan.get(position);

        holder.pilihan.setText(pilihan);

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
        return programPilihan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView pilihan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pilihan = itemView.findViewById(R.id.item_terpilih);
        }
    }
}
