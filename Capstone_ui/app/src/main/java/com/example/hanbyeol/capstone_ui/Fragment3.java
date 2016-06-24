package com.example.hanbyeol.capstone_ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment3 extends Fragment {

//    private static Fragment3 UniqueFragment3;
//    private Fragment3(){ }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_fragment3, container, false);
        return view;
    }

//    public static synchronized Fragment3 getInstance(){
//        if(UniqueFragment3 == null)
//            UniqueFragment3 = new Fragment3();
//        return UniqueFragment3;
//    }

}
