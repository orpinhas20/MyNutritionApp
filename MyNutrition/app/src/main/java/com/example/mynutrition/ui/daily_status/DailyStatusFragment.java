package com.example.mynutrition.ui.daily_status;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import com.example.mynutrition.R;

import java.util.ArrayList;
import java.util.List;

public class DailyStatusFragment extends Fragment {


    private AnyChartView pieChart;
    private List<DataEntry> data ;
    private Pie pie;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_daily_status, container, false);
        pieChart = (AnyChartView) root.findViewById(R.id.pie_chart);
        pie = AnyChart.pie();
        data = new ArrayList<>();
        data.add(new ValueDataEntry("fruit",1));
        data.add(new ValueDataEntry("fruit",1));
        data.add(new ValueDataEntry("snacks",1));
        pie.title("Today Food Diagram: ");
        pie.data(data);
        pieChart.setChart(pie);



        return root;


    }
}