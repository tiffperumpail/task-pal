package com.taskpal.taskpal;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Button addTaskButton;
    private ImageButton sunButton;
    private Button menuButton;
    private TextView nameText;
    private TextView monthText2;
    private TextView dayText;
    private TextView timeText1;
    private TextView timeText2;
    private TextView timeText3;
    private TextView timeText4;
    private TextView timeText5;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();
        addTaskButton = findViewById(R.id.addTaskButton);
        sunButton = findViewById(R.id.sunButton);
        menuButton = findViewById(R.id.menuButton);
        nameText = findViewById(R.id.nameText);
        monthText2 = findViewById(R.id.monthText2);
        dayText = findViewById(R.id.dayText);
        timeText1 = findViewById(R.id.timeText1);
        timeText2 = findViewById(R.id.timeText2);
        timeText3 = findViewById(R.id.timeText3);
        timeText4 = findViewById(R.id.timeText4);
        timeText5 = findViewById(R.id.timeText5);
        drawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();

                        //Add code here to change the UI based on the selected input
                        switch (menuItem.getItemId()) {
                            case R.id.nav_add_task:
                                startActivity(new Intent(MainActivity.this, NewTaskActivity.class));
                                return true;
                            case R.id.nav_preferences:
                                startActivity(new Intent(MainActivity.this, PreferencesActivity.class));
                                return true;
                            case R.id.nav_calendar:
                                startActivity(new Intent(MainActivity.this, CalendarActivity.class));
                                return true;
                            default:
                                return true;
                        }
                    }
                }
        );

        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NewTaskActivity.class));
            }
        });

        sunButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CalendarActivity.class));
            }
        });

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.END);
            }
        });
    }

    public void checkPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED
                ){

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_CALENDAR, Manifest.permission.READ_CALENDAR},
                    123);
        }
    }
}
