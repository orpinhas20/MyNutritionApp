package com.example.mynutrition.ui.daily_status;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import com.example.mynutrition.App;
import com.example.mynutrition.Constant;
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
    private List<Food> foodList ;
    private BroadcastReceiver mUpdateUiReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            getDataForPieChart();
        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_daily_status, container, false);
        pieChart = root.findViewById(R.id.pie_chart);
        db = App.getInstance().getDb();
        user = App.getInstance().getFbUser();
        getDataForPieChart();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mUpdateUiReceiver,
                new IntentFilter(Constant.ACTION_UPDATE_UI));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(mUpdateUiReceiver);
    }

    private void getDataForPieChart() {
        foodList = new ArrayList<>();
        String collectionName = user.getPhoneNumber();
        db.collection(collectionName).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot response) {
                if(response.isEmpty()){
                    Toast.makeText(getContext(), "No foods founded from today", Toast.LENGTH_SHORT).show();
                return;
                }
                else{
                     foodList = response.toObjects(Food.class);
                     getActivity().runOnUiThread(new Runnable() {
                         @Override
                         public void run() {
                             setDateForPieChart();
                         }
                     });

                }

            }
        });
    }
    private void setDateForPieChart() {
        data = new ArrayList<>();
        int fruitsCounter = 0;
        int vegetableCounter = 0;
        int carbsCounter = 0;
        int protainCounter = 0;
        int fatCounter = 0;
        int snacksCounter = 0;
        int sweet_drinksCounter = 0;
        int candyCounter = 0;

        for (int i = 0; i < foodList.size(); i++) {
            if (DateUtils.isToday(foodList.get(i).getTimeStamp())) {
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
        }

            data.add(new ValueDataEntry("fruits", fruitsCounter));
            data.add(new ValueDataEntry("vegetables", vegetableCounter));
            data.add(new ValueDataEntry("carbs", carbsCounter));
            data.add(new ValueDataEntry("protain", protainCounter));
            data.add(new ValueDataEntry("fat", fatCounter));
            data.add(new ValueDataEntry("sweet drinks", sweet_drinksCounter));
            data.add(new ValueDataEntry("candy", candyCounter));
            data.add(new ValueDataEntry("snacks", snacksCounter));

            pie = AnyChart.pie();
            pie.title("Today Food plate: ");
            pie.data(data);
            pieChart.setChart(pie);
        }


}