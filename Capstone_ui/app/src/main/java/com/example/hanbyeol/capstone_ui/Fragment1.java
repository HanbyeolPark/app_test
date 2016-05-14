package com.example.hanbyeol.capstone_ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Fragment1 extends Fragment {

    Adapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content_fragment1, container, false);
        ViewPager viewPager = (ViewPager) (view.findViewById(R.id.main_viewpager));
        if (viewPager != null) {
            setupViewPager(viewPager);
        }

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.main_tabs);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        //get information about tabs from server
        //make Fragments as number of categories

        Bundle bundle = getArguments();
        String[][] data = (String[][])bundle.getSerializable("parsedData");

        adapter = new Adapter(getFragmentManager());

        for(int i=0; i< data.length; i++){
            adapter.addFragment(new Fragment1_(data[i][1]), data[i][0]);
        }
        viewPager.setAdapter(adapter);

    }

}