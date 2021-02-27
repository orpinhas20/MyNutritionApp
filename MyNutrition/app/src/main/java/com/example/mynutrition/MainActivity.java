package com.example.mynutrition;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.example.mynutrition.ui.daily_status.DailyStatusFragment;
import com.example.mynutrition.ui.food_tracking.FoodTrackingFragment;
import com.example.mynutrition.ui.contact.ContactFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseUser;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    private DailyStatusFragment dailyStatusFragment;
    private FoodTrackingFragment foodTrackingFragment;
    private ContactFragment contactFragment;
    private Fragment activeFragment;
    private FragmentManager fragmentManager;
    private BottomNavigationView navView;
    private Toolbar toolbar;
    private FirebaseUser user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        initBottomBar();
        user = App.getInstance().getFbUser();

    }

    private void initBottomBar() {
        navView = findViewById(R.id.nav_view);
        fragmentManager = getSupportFragmentManager();
        dailyStatusFragment = new DailyStatusFragment();
        foodTrackingFragment = new FoodTrackingFragment();
        contactFragment = new ContactFragment();
        updateTitle(R.id.navigation_daily_status);
        activeFragment = dailyStatusFragment;
        fragmentManager.beginTransaction().add(R.id.nav_host_fragment, dailyStatusFragment, "1").commit();
        fragmentManager.beginTransaction().add(R.id.nav_host_fragment, foodTrackingFragment, "2").hide(foodTrackingFragment).commit();
        fragmentManager.beginTransaction().add(R.id.nav_host_fragment, contactFragment, "3").hide(contactFragment).commit();
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_daily_status:
                        fragmentManager.beginTransaction().hide(activeFragment).show(dailyStatusFragment).commit();
                        activeFragment = dailyStatusFragment;
                        updateTitle(item.getItemId());
                        return true;
                    case R.id.navigation_food_tracking:
                        fragmentManager.beginTransaction().hide(activeFragment).show(foodTrackingFragment).commit();
                        activeFragment = foodTrackingFragment;
                        updateTitle(item.getItemId());
                        return true;
                    case R.id.navigation_contact:
                        fragmentManager.beginTransaction().hide(activeFragment).show(contactFragment).commit();
                        activeFragment = contactFragment;
                        updateTitle(item.getItemId());
                        return true;
                }
                return false;
            }
        });
    }

    private void updateTitle(int id) {
        if (id == R.id.navigation_daily_status) {
            getSupportActionBar().setTitle(R.string.title_daily_status);
        } else if (id == R.id.navigation_food_tracking) {
            getSupportActionBar().setTitle(R.string.title_food_tracking);
        } else if (id == R.id.navigation_contact) {
            getSupportActionBar().setTitle(R.string.title_contact);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}