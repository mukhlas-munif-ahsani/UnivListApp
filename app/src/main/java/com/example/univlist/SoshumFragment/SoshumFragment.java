package com.example.univlist.SoshumFragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.univlist.ListUnivFragment.ListUnivActivity;
import com.example.univlist.R;
import com.example.univlist.SaintekFragment.SaintekListAdapter;
import com.example.univlist.SaintekFragment.SaintekListModel;
import com.example.univlist.SaintekFragment.SaintekListViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SoshumFragment extends Fragment implements SoshumListAdapter.OnListItemCliked {

    private RecyclerView mListView;
    private SoshumListViewModel soshumListViewModel;

    private SoshumListAdapter adapter;
    private ProgressBar listProgress;

    private Animation fadeInAnim;
    private Animation fadeOutAnim;

    public SoshumFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_soshum, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mListView = view.findViewById(R.id.list_view);
        listProgress = view.findViewById(R.id.list_progress);
        fadeInAnim = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        fadeOutAnim = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);

        adapter = new SoshumListAdapter(this);
        mListView.setLayoutManager(new LinearLayoutManager(getContext()));
        mListView.setHasFixedSize(true);
        mListView.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        soshumListViewModel = new ViewModelProvider(getActivity()).get(SoshumListViewModel.class);
        soshumListViewModel.getSoshumListModelData().observe(getViewLifecycleOwner(), new Observer<List<SoshumListModel>>() {
            @Override
            public void onChanged(List<SoshumListModel> soshumListModels) {

                mListView.startAnimation(fadeInAnim);
                listProgress.startAnimation(fadeOutAnim);
                listProgress.setVisibility(View.INVISIBLE);

                adapter.setSoshumListModels(soshumListModels);
                adapter.notifyDataSetChanged();
                mListView.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onItemCliked(String prodi, String kategori, int position) {
        Intent intent = new Intent(getActivity(), ListUnivActivity.class);
        intent.putExtra("prodi", prodi);
        intent.putExtra("kategori", kategori);
        startActivity(intent);
    }
}
