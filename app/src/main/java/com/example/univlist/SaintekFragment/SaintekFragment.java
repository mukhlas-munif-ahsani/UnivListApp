package com.example.univlist.SaintekFragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.univlist.HomeFragment;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
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

        setHasOptionsMenu(true);
//        swipeRefresh();
    }

    public void coba(String safd) {
        Toast.makeText(getActivity(), safd, Toast.LENGTH_LONG).show();
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
        //Toast.makeText(getActivity(), prodi + kategori, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getActivity(), ListUnivActivity.class);
        intent.putExtra("prodi", prodi);
        intent.putExtra("kategori", kategori);
        startActivity(intent);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.dot_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //adapter.getFilter().filter(query);
                //Toast.makeText(getActivity(), query, Toast.LENGTH_LONG).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        searchItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                HomeFragment.getInstance().showTab();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                HomeFragment.getInstance().hideTab();
                return true;
            }
        });


        //return true;
        //super.onCreateOptionsMenu(menu, inflater);
    }
}
