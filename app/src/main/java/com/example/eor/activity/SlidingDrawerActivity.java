package com.example.eor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.eor.R;
import com.example.eor.fragment.SettingsFragment;
import com.example.eor.dao.User_DAO;
import com.example.eor.model.UserCredentials_Model;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class SlidingDrawerActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private boolean doubleBackToExitPressedOnce = false;
    private NavController navController;
    public static String USER_ID = "U0013";
    private NavigationView navigationView;
    private TextView header_name,header_email;
    private ImageView header_image;
    public static UserCredentials_Model loggedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sliding_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getIntent().getStringExtra("user_id")!=null) USER_ID = getIntent().getStringExtra("user_id");
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_profile, R.id.nav_posts,
                R.id.nav_history, R.id.nav_saved ,R.id.nav_inbox, R.id.nav_social)
                .setDrawerLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        header_name = header.findViewById(R.id.header_name);
        header_email = header.findViewById(R.id.header_email);
        header_image = header.findViewById(R.id.header_image);
        setDetails();
    }

    private void setDetails() {
        User_DAO user_dao = new User_DAO();
        loggedUser = user_dao.getUser(USER_ID);
        String nameFormat = loggedUser.getUser_fname()+" "+loggedUser.getUser_lname();
        header_name.setText(nameFormat);
        header_email.setText(loggedUser.getUser_email());
        if(loggedUser.getUser_email().equals("premraval.pr@gmail.com")) header_image.setImageResource(R.drawable.prem_1);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(getApplicationContext(), SettingsFragment.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if(Objects.requireNonNull(
                Objects.requireNonNull(
                        navController.getCurrentDestination()
                ).getLabel()
        ).toString().equals("Home")){
            if (doubleBackToExitPressedOnce) {
                Intent intent = new Intent(this, FirstActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("Exit me", true);
                startActivity(intent);
                finish();
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }

        else{

            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sliding_drawer, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}
