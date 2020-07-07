package com.example.univlist;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.univlist.SaintekFragment.SaintekFragment;
import com.example.univlist.SoshumFragment.SoshumFragment;
import com.example.univlist.TambahData1.view.TambahDataActivity1;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private ViewPager mViewPager;
    private SmartTabLayout mSmartTabLayout;

    private Toolbar toolbar;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toolbar = view.findViewById(R.id.Toolbar);
//        getSupportActionBar().setElevation(0);
//        getSupportActionBar().setTitle("Daftar Universitas");
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setElevation(0);

        toolbar.setTitle("Daftar Jurusan");

        mViewPager = view.findViewById(R.id.viewpager_jurusan);
        mSmartTabLayout = view.findViewById(R.id.viewpagertab_daftar_jurusan);

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getActivity().getSupportFragmentManager(), FragmentPagerItems.with(getContext())
                .add("SAINTEK", SaintekFragment.class)
                .add("SOSHUM", SoshumFragment.class)
                .create()
        );

        mViewPager.setAdapter(adapter);
        mSmartTabLayout.setViewPager(mViewPager);

        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.dot_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                //Toast.makeText(getActivity(), "Tambah item", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), TambahDataActivity1.class);
                startActivity(intent);
                break;
            case R.id.developer_talk:
                Toast.makeText(getActivity(), "Alahamdulillah selesai walau masih banyak kekurangan", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
