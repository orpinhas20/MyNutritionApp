package com.example.mynutrition.ui.diet_dictionary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.mynutrition.R;

public class DietDictionaryFragment extends Fragment {

    private TextView textView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_diet_dictionary, container, false);
        textView = root.findViewById(R.id.text_dietDictionary);
        textView.setText("Diet Dictionary Fragment");

        return root;
    }
}