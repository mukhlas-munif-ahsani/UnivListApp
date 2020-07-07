package com.example.univlist.SoshumFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.univlist.R;

import java.util.List;

public class SoshumListAdapter extends RecyclerView.Adapter<SoshumListAdapter.SoshumViewHolder> {

    private List<SoshumListModel> soshumListModels;
    private OnListItemCliked onListItemCliked;

    public SoshumListAdapter(OnListItemCliked onListItemCliked){
        this.onListItemCliked = onListItemCliked;
    }

    public void setSoshumListModels(List<SoshumListModel> soshumListModels) {
        this.soshumListModels = soshumListModels;
    }

    @NonNull
    @Override
    public SoshumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_prodi, parent, false);
        return new SoshumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SoshumViewHolder holder, int position) {
        holder.mSoshumName.setText(soshumListModels.get(position).getSoshum_id());
        holder.mSoshumNumber.setText(String.valueOf(soshumListModels.get(position).getUniv().size()));

        holder.prodi = soshumListModels.get(position).getSoshum_id();
        holder.kategori = soshumListModels.get(position).getKategori();
    }

    @Override
    public int getItemCount() {
        if (soshumListModels == null){
            return 0;
        } else {
            return soshumListModels.size();
        }
    }

    public class SoshumViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mSoshumName;
        private TextView mSoshumNumber;
        private CardView mSoshumItem;

        private String prodi;
        private String kategori;

        public SoshumViewHolder(@NonNull View itemView) {
            super(itemView);

            mSoshumName = itemView.findViewById(R.id.prodi_name);
            mSoshumNumber = itemView.findViewById(R.id.saintek_num);
            mSoshumItem = itemView.findViewById(R.id.saintek_item);

            mSoshumItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onListItemCliked.onItemCliked(prodi, kategori, getAdapterPosition());
        }
    }

    public interface OnListItemCliked{
        void onItemCliked(String prodi, String kategori, int position);
    }
}
