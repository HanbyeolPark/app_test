package com.example.hanbyeol.capstone_ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

/**
 * Created by Hanbyeol on 2016-03-29.
 */
public class Fragment4 extends Fragment {

//    private static Fragment4 UniqueFragment4;
//    private Fragment4(){ }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_fragment4, container, false);
        return view;
    }

//    public static synchronized Fragment4 getInstance(){
//        if(UniqueFragment4 == null)
//            UniqueFragment4 = new Fragment4();
//        return UniqueFragment4;
//    }
}
