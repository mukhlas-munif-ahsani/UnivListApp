package com.example.univlist.SaintekFragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.univlist.HomeFragmentDirections;
import com.example.univlist.ListUnivFragment.ListUnivActivity;
import com.example.univlist.R;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SaintekFragment extends Fragment implements SaintekListAdapter.OnListItemCliked {

    private RecyclerView mListView;
    private SaintekListViewModel saintekListViewModel;

    private SwipeRefreshLayout swLayout;

    private SaintekListAdapter adapter;
    private ProgressBar listProgress;

    private Animation fadeInAnim;
    private Animation fadeOutAnim;

    private NavController navController;

    public SaintekFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saintek, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
//        swLayout = view.findViewById(R.id.swlayout);
        mListView = view.findViewById(R.id.list_view);
        listProgress = view.findViewById(R.id.list_progress);
        fadeInAnim = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        fadeOutAnim = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);

        adapter = new SaintekListAdapter(this);
        mListView.setLayoutManager(new LinearLayoutManager(getContext()));
        mListView.setHasFixedSize(true);
        mListView.setAdapter(adapter);

//        swipeRefresh();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        saintekListViewModel = new ViewModelProvider(getActivity()).get(SaintekListViewModel.class);
        saintekListViewModel.getSaintekListModelData().observe(getViewLifecycleOwner(), new Observer<List<SaintekListModel>>() {
            @Override
            public void onChanged(List<SaintekListModel> saintekListModels) {

                mListView.startAnimation(fadeInAnim);
                listProgress.startAnimation(fadeOutAnim);
                listProgress.setVisibility(View.INVISIBLE);

                adapter.setSaintekListModels(saintekListModels);
                adapter.notifyDataSetChanged();
                mListView.setAdapter(adapter);
            }
        });
    }

    private void swipeRefresh() {
        // Mengeset properti warna yang berputar pada SwipeRefreshLayout
        swLayout.setColorSchemeResources(R.color.blue_300, R.color.blue_700);

        // Mengeset listener yang akan dijalankan saat layar di refresh/swipe
        swLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                // Handler untuk menjalankan jeda selama 5 detik
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        // Berhenti berputar/refreshing
                        swLayout.setRefreshing(false);

                    }
                }, 1000);
            }
        });
    }

    @Override
    public void onItemCliked(String prodi, String kategori, int position) {
        Toast.makeText(getActivity(), prodi + kategori, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getActivity(), ListUnivActivity.class);
        intent.putExtra("prodi", prodi);
        intent.putExtra("kategori", kategori);
        startActivity(intent);
    }

}
