package com.example.univlist.ListUnivFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.univlist.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.firebase.ui.firestore.paging.FirestorePagingAdapter;
import com.firebase.ui.firestore.paging.FirestorePagingOptions;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.List;

public class ListUnivAdapter extends FirestoreRecyclerAdapter<ListUnivModel, ListUnivAdapter.Holder>{

    private OnItemClickListener onItemClickListener;
    private List<ListUnivModel> listUnivModels;

    public void setOnItemClickListener( OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ListUnivAdapter(@NonNull FirestoreRecyclerOptions<ListUnivModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ListUnivAdapter.Holder holder, int position, @NonNull ListUnivModel model) {
        holder.univName.setText(model.getUnivId());
        holder.univDaerah.setText(model.getDaerah());
        holder.univ = model.getUnivId();
    }

    @NonNull
    @Override
    public ListUnivAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_univ, parent, false);
        return new Holder(view);
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView univName;
        private TextView univDaerah;
        private CardView univCard;

        private String univ;
        public Holder(@NonNull View itemView) {
            super(itemView);

            univName = itemView.findViewById(R.id.univ_name);
            univDaerah = itemView.findViewById(R.id.univ_daerah);
            univCard = itemView.findViewById(R.id.univ_item);

            univCard.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int posision = getAdapterPosition();
                onItemClickListener.onItemClick(univ, posision);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(String univ, int position);
    }
}
