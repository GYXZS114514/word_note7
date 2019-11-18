package com.example.a30259.word_note;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a30259.word_note.R;

/**
 * Created by 30259 on 2019/11/13.
 */

public class LeftFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.left_fragment,container,false);
        return view;
    }
}
