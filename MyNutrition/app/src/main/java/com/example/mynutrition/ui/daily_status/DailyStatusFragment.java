package com.example.mynutrition.ui.daily_status;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.mynutrition.R;

import java.util.ArrayList;
import java.util.List;

public class DailyStatusFragment extends Fragment {


    private com.anychart.AnyChartView pieChart;
    private Spinner drop_list;
    private EditText food_name;
    private List<String> items ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_daily_status, container, false);
        food_name = root.findViewById(R.id.food_name);
        pieChart = root.findViewById(R.id.pie_chart);
        drop_list = root.findViewById(R.id.drop_list);
        items = new ArrayList<String>();
        items.add(getString(R.string.carbs));
        items.add(getString(R.string.vegetable));
        items.add(getString(R.string.fruits));
        items.add(getString(R.string.protain));
        items.add(getString(R.string.fat));
        items.add(getString(R.string.snacks));
        items.add(getString(R.string.sweet_drinks));
        items.add(getString(R.string.candy));

// Create an ArrayAdapter using the string array and a default spinner layout
ArrayAdapter <Integer>adapter = new ArrayAdapter (this.getContext(), android.R.layout.simple_spinner_dropdown_item,items);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        drop_list.setAdapter(adapter);
        return root;

        
    }
}