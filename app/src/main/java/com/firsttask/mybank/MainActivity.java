package com.firsttask.mybank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private Button btnViewUsers,btnViewUsers2,btnViewUsers3,btnViewUsers4;
    ViewPager vp;
    TabLayout tl;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnViewUsers = findViewById(R.id.btnViewUsers);
        btnViewUsers2 = findViewById(R.id.btnViewUsers1);
        btnViewUsers3 = findViewById(R.id.btnViewUsers3);
        btnViewUsers4 = findViewById(R.id.btnViewUsers4);


        btnViewUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });
        btnViewUsers2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Userlist.class));
            }
        });
        btnViewUsers3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, blankpage.class));
            }
        });
        btnViewUsers4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, blankpage.class));
            }
        });


//        drawerLayout = findViewById(R.id.drawerLayout);

//       bottomNavigationView =(BottomNavigationView) findViewById(R.id.bottomNavigationView);
//        bottomNavigationView.setSelectedItemId(R.id.id_home);
//
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
//
//        {
//            @Override
//            public boolean onNavigationItemSelected (@NonNull MenuItem item){
//
//                switch (item.getItemId()) {
//                    case R.id.id_home:
//                        return true;
//                    case R.id.id_account:
//                        startActivity(new Intent(getApplicationContext(), Userlist.class));
//                        overridePendingTransition(0, 0);
//                        finish();
//                        return true;
//                    case R.id.id_about:
//                        startActivity(new Intent(getApplicationContext(), HistoryList.class));
//                        overridePendingTransition(0, 0);
//                        finish();
//                        return true;
//                }
//
//                return false;
//            }
//        });
//
//
//    }
//
//    public void onBackPressed() {
//        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//            drawerLayout.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
   }
}
