package com.example.mynutrition.ui.daily_status;

import android.os.Bundle;
import android.util.Log;
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

import com.example.mynutrition.App;
import com.example.mynutrition.R;
import com.example.mynutrition.data.Food;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DailyStatusFragment extends Fragment {

    private FirebaseFirestore db;
    private AnyChartView pieChart;
    private List<DataEntry> data ;
    private Pie pie;
    private FirebaseUser user;
    private List<Food> foodList = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_daily_status, container, false);
        pieChart = (AnyChartView) root.findViewById(R.id.pie_chart);
        pie = AnyChart.pie();
        db = App.getInstance().getDb();
        user = App.getInstance().getFbUser();
        data = new ArrayList<>();
        getDataForPieChart();

        return root;
    }

    private void getDataForPieChart() {
        String collectionName = user.getPhoneNumber();
        db.collection(collectionName).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot response) {
                if(response.isEmpty()){
                    Log.d("pttt","List is empty");
                return;
                }
                else{
                     foodList = response.toObjects(Food.class);
                        setDateForPieChart();
                }

            }
        });
    }
    private void setDateForPieChart() {
        int fruitsCounter = 0;
        int vegetableCounter = 0;
        int carbsCounter = 0;
        int protainCounter = 0;
        int fatCounter = 0;
        int snacksCounter = 0;
        int sweet_drinksCounter = 0;
        int candyCounter = 0;

        for (int i=0 ;i< foodList.size();i++){
            Log.d("pttt","On Success "+ foodList.get(i).getFoodType());
            switch (foodList.get(i).getFoodType()) {

                case "fruits":
                    fruitsCounter++;
                    break;

                case "vegetables":
                    vegetableCounter++;
                    break;

                case "carbs":
                    carbsCounter++;
                    break;

                case "protain":
                    protainCounter++;
                    break;

                case "fat":
                    fatCounter++;
                    break;

                case "snacks":
                    snacksCounter++;
                    break;

                case "sweet drinks":
                    sweet_drinksCounter++;
                    break;

                case "candy":
                    candyCounter++;
                    break;
            }

        }
        data.add(new ValueDataEntry("fruits",fruitsCounter));
        data.add(new ValueDataEntry("vegetables",vegetableCounter));
        data.add(new ValueDataEntry("carbs",carbsCounter));
        data.add(new ValueDataEntry("protain",protainCounter));
        data.add(new ValueDataEntry("fat",fatCounter));
        data.add(new ValueDataEntry("sweet drinks",sweet_drinksCounter));
        data.add(new ValueDataEntry("candy",candyCounter));
        data.add(new ValueDataEntry("snacks",snacksCounter));

        pie.title("Today Food Diagram: ");
        pie.data(data);
        pieChart.setChart(pie);
    }
}