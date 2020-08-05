package com.example.univlist.PendidikanFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.univlist.R;
import com.example.univlist.SaintekFragment.SaintekListAdapter;
import com.example.univlist.SaintekFragment.SaintekListModel;

import java.util.ArrayList;
import java.util.List;

public class PendidikanAdapter extends RecyclerView.Adapter<PendidikanAdapter.PendidikanViewHolder> implements Filterable {

    private List<PendidikanListModel> pendidikanListModels;
    private List<PendidikanListModel> pendidikanListModelsFull;
    private OnListItemCliked onListItemCliked;

    public PendidikanAdapter(OnListItemCliked onListItemCliked){
        this.onListItemCliked = onListItemCliked;
    }

    public void setPendidikanListModels(List<PendidikanListModel> pendidikanListModels){
        this.pendidikanListModels = pendidikanListModels;
        pendidikanListModelsFull = new ArrayList<>(pendidikanListModels);
    }

    @NonNull
    @Override
    public PendidikanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_prodi, parent, false);
        return new PendidikanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PendidikanViewHolder holder, int position) {
        holder.mPendidikanName.setText(pendidikanListModels.get(position).getSaintek_id());
        holder.mPendidikanNumber.setText(String.valueOf(pendidikanListModels.get(position).getUniv().size()));

        holder.prodi = pendidikanListModels.get(position).getSaintek_id();
        holder.kategori = pendidikanListModels.get(position).getKategori();
        Log.d(holder.TAG, "Quiztions " + " : " + pendidikanListModels.get(position).getSaintek_id());
    }

    @Override
    public int getItemCount() {
        if (pendidikanListModels == null){
            return 0;
        } else {
            return pendidikanListModels.size();
        }
    }

    public class PendidikanViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private static final String TAG = "QUIZ_FRAGMENT_LOG";

        private TextView mPendidikanName;
        private TextView mPendidikanNumber;
        private CardView mPendidikanItem;

        private String prodi;
        private String kategori;

        public PendidikanViewHolder(@NonNull View itemView) {
            super(itemView);

            mPendidikanName= itemView.findViewById(R.id.prodi_name);
            mPendidikanNumber = itemView.findViewById(R.id.saintek_num);
            mPendidikanItem = itemView.findViewById(R.id.saintek_item);

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

    @Override
    public Filter getFilter() {
        return pendidikanFilter;
    }

    private Filter pendidikanFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<PendidikanListModel> filterredList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0){
                filterredList.addAll(pendidikanListModelsFull);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for (PendidikanListModel item : pendidikanListModelsFull){
                    if (item.getSaintek_id().toLowerCase().contains(filterPattern)){
                        filterredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filterredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            pendidikanListModels.clear();
            pendidikanListModels.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };
}
