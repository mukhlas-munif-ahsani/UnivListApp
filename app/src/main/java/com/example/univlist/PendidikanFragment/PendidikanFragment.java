package com.example.univlist.PendidikanFragment;

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

import com.example.univlist.HomeFragment;
import com.example.univlist.ListUnivFragment.ListUnivActivity;
import com.example.univlist.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PendidikanFragment extends Fragment implements PendidikanAdapter.OnListItemCliked{

    private RecyclerView mListView;
    private PendidikanViewModel pendidikanViewModel;

    private SwipeRefreshLayout swLayout;

    private PendidikanAdapter adapter;
    private ProgressBar listProgress;

    private Animation fadeInAnim;
    private Animation fadeOutAnim;

    private NavController navController;

    public PendidikanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pendidikan, container, false);
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

        adapter = new PendidikanAdapter(this);
        mListView.setLayoutManager(new LinearLayoutManager(getContext()));
        mListView.setHasFixedSize(true);
        mListView.setAdapter(adapter);

        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        pendidikanViewModel = new ViewModelProvider(getActivity()).get(PendidikanViewModel.class);
        pendidikanViewModel.getPendidikanListModelData().observe(getViewLifecycleOwner(), new Observer<List<PendidikanListModel>>() {
            @Override
            public void onChanged(List<PendidikanListModel> saintekListModels) {

                mListView.startAnimation(fadeInAnim);
                listProgress.startAnimation(fadeOutAnim);
                listProgress.setVisibility(View.INVISIBLE);

                adapter.setPendidikanListModels(saintekListModels);
                adapter.notifyDataSetChanged();
                mListView.setAdapter(adapter);
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
