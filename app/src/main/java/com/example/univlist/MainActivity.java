package com.example.univlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.univlist.SaintekFragment.SaintekFragment;
import com.example.univlist.SoshumFragment.SoshumFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private SmartTabLayout mSmartTabLayout;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        toolbar = findViewById(R.id.Toolbar);
////        getSupportActionBar().setElevation(0);
////        getSupportActionBar().setTitle("Daftar Universitas");
//        setSupportActionBar(toolbar);
//        toolbar.setElevation(0);
//
//        mViewPager = findViewById(R.id.viewpager_profile_desa);
//        mSmartTabLayout = findViewById(R.id.viewpagertab_daftar_jurusan);
//
//        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
//            MainActivity.this.getSupportFragmentManager(), FragmentPagerItems.with(this)
//                .add("SAINTEK", SaintekFragment.class)
//                .add("SOSHUM", SoshumFragment.class)
//                .create()
//        );
//
//        mViewPager.setAdapter(adapter);
//        mSmartTabLayout.setViewPager(mViewPager);

    }
}
