package com.example.mynutrition;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mynutrition.ui.daily_status.DailyStatusFragment;
import com.example.mynutrition.ui.diet_dictionary.DietDictionaryFragment;
import com.example.mynutrition.ui.food_tracking.FoodTrackingFragment;
import com.example.mynutrition.ui.contact.ContactFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    DailyStatusFragment dailyStatusFragment;
    DietDictionaryFragment dietDictionaryFragment;
    FoodTrackingFragment foodTrackingFragment;
    ContactFragment contactFragment;
    Fragment activeFragment;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initBottomBar();
    }

    private void initBottomBar() {
        BottomNavigationView navView = findViewById(R.id.nav_view);
        final FragmentManager fragmentManager = getSupportFragmentManager();
        dietDictionaryFragment = new DietDictionaryFragment();
        dailyStatusFragment = new DailyStatusFragment();
        foodTrackingFragment = new FoodTrackingFragment();
        contactFragment = new ContactFragment();
        activeFragment = dietDictionaryFragment;
        updateTile(R.id.navigation_daily_status);

        fragmentManager.beginTransaction().add(R.id.nav_host_fragment, contactFragment, "3").hide(contactFragment).commit();
        fragmentManager.beginTransaction().add(R.id.nav_host_fragment, foodTrackingFragment, "3").hide(foodTrackingFragment).commit();
        fragmentManager.beginTransaction().add(R.id.nav_host_fragment, dietDictionaryFragment, "2").hide(dailyStatusFragment).commit();
        fragmentManager.beginTransaction().add(R.id.nav_host_fragment, dailyStatusFragment, "1").commit();

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_daily_status:
                        fragmentManager.beginTransaction().hide(activeFragment).show(dailyStatusFragment).commit();
                        activeFragment = dailyStatusFragment;
                        updateTile(item.getItemId());
                        return true;
                    case R.id.navigation_diet_dictionary:
                        fragmentManager.beginTransaction().hide(activeFragment).show(dietDictionaryFragment).commit();
                        activeFragment = dietDictionaryFragment;
                        updateTile(item.getItemId());
                        return true;
                    case R.id.navigation_food_tracking:
                        fragmentManager.beginTransaction().hide(activeFragment).show(foodTrackingFragment).commit();
                        activeFragment = foodTrackingFragment;
                        updateTile(item.getItemId());
                        return true;
                    case R.id.navigation_contact:
                        fragmentManager.beginTransaction().hide(activeFragment).show(contactFragment).commit();
                        activeFragment = contactFragment;
                        updateTile(item.getItemId());
                        return true;
                }
                return false;
            }
        });
    }

    private void updateTile(int id) {
        if (id == R.id.navigation_daily_status) {
            getSupportActionBar().setTitle(R.string.title_daily_status);
        } else if (id == R.id.navigation_diet_dictionary) {
            getSupportActionBar().setTitle(R.string.title_diet_dictionary);
        } else if (id == R.id.navigation_food_tracking) {
            getSupportActionBar().setTitle(R.string.title_food_tracking);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            openSettingsPage();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openSettingsPage() {
        Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
    }

}