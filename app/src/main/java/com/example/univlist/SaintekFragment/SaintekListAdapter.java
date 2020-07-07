package com.example.univlist.SaintekFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.univlist.R;

import java.util.List;

public class SaintekListAdapter extends RecyclerView.Adapter<SaintekListAdapter.SaintekViewHolder> {

    private List<SaintekListModel> saintekListModels;
    private OnListItemCliked onListItemCliked;

    public SaintekListAdapter(OnListItemCliked onListItemCliked){
        this.onListItemCliked = onListItemCliked;
    }

    public void setSaintekListModels(List<SaintekListModel> saintekListModels){
        this.saintekListModels = saintekListModels;
    }

    @NonNull
    @Override
    public SaintekViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_prodi, parent, false);
        return new SaintekViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SaintekViewHolder holder, int position) {
        holder.mSaintekName.setText(saintekListModels.get(position).getSaintek_id());
        holder.mSaintekNumber.setText(String.valueOf(saintekListModels.get(position).getUniv().size()));

        holder.prodi = saintekListModels.get(position).getSaintek_id();
        holder.kategori = saintekListModels.get(position).getKategori();
        Log.d(holder.TAG, "Quiztions " + " : " + saintekListModels.get(position).getSaintek_id());
    }

    @Override
    public int getItemCount() {
       if (saintekListModels == null){
           return 0;
       } else {
           return saintekListModels.size();
       }
    }

    public class SaintekViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private static final String TAG = "QUIZ_FRAGMENT_LOG";

        private TextView mSaintekName;
        private TextView mSaintekNumber;
        private CardView mSaintekItem;

        private String prodi;
        private String kategori;

        public SaintekViewHolder(@NonNull View itemView) {
            super(itemView);

            mSaintekName = itemView.findViewById(R.id.prodi_name);
            mSaintekNumber = itemView.findViewById(R.id.saintek_num);
            mSaintekItem = itemView.findViewById(R.id.saintek_item);

            itemView.setOnClickListener(this);

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
