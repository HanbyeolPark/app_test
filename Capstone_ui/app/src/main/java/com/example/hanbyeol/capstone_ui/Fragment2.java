package com.example.hanbyeol.capstone_ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment2 extends Fragment {

//    private static Fragment2 UniqueFragment2;
//    private Fragment2(){ }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_fragment2, container, false);
        return view;
    }

//    public static synchronized Fragment2 getInstance(){
//        if(UniqueFragment2 == null)
//            UniqueFragment2 = new Fragment2();
//        return UniqueFragment2;
//    }

}
