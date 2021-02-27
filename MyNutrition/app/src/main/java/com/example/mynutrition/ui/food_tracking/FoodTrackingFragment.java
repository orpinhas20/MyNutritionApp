package com.example.mynutrition.ui.food_tracking;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.mynutrition.App;
import com.example.mynutrition.R;
import com.example.mynutrition.data.Food;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FoodTrackingFragment extends Fragment {

    private FirebaseFirestore db;
    private FirebaseUser user;
    private Spinner drop_list;
    private EditText food_name;
    private Button BTN_next;
    private List<String> items;
    private String userFoodName;
    private String userFoodType;
    private Map<String, String> data;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_food_tracking, container, false);
        food_name = root.findViewById(R.id.food_name);
        drop_list = root.findViewById(R.id.drop_list);
        BTN_next = root.findViewById(R.id.BTN_next);

        items = new ArrayList<String>();
        items.add(getString(R.string.carbs));
        items.add(getString(R.string.vegetable));
        items.add(getString(R.string.fruits));
        items.add(getString(R.string.protain));
        items.add(getString(R.string.fat));
        items.add(getString(R.string.snacks));
        items.add(getString(R.string.sweet_drinks));
        items.add(getString(R.string.candy));
        db = App.getInstance().getDb();
        user = App.getInstance().getFbUser();


// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<Integer> adapter = new ArrayAdapter(this.getContext(), android.R.layout.simple_spinner_dropdown_item, items);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        drop_list.setAdapter(adapter);
        BTN_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFood();
            }
        });


        return root;
    }

    public void saveFood() {
        userFoodName = food_name.getText().toString();
        userFoodType = drop_list.getSelectedItem().toString();
        Food food = new Food(userFoodName, userFoodType);
        if (userFoodType.isEmpty() || userFoodName.isEmpty()) {
            Toast.makeText(this.getContext(), "PLEASE COMPLETE ALL FIELDS", Toast.LENGTH_SHORT).show();
            return;
        }

        db.collection(user.getPhoneNumber()).add(food).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if (task.isSuccessful())
                    Log.d("pttt", "success");
                else {
                    Log.d("pttt", "error complete task");
                }
            }
        });
    }
}