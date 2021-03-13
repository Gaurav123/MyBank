package com.firsttask.mybank;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class Userlist extends AppCompatActivity {
    List<Model> modelList_showlist = new ArrayList<>();
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    CustomeAdapterUserlist adapter;
    DrawerLayout drawerLayout;
    String phonenumber;
    private ProgressDialog mLoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_allusers);
        drawerLayout = findViewById(R.id.drawerLayout);

        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);//java42

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startEnterAnimation();
            }
        }, 2000);


//        BottomNavigationView bottomNavigationView =(BottomNavigationView) findViewById(R.id.bottomNavigationView);
//        bottomNavigationView.setSelectedItemId(R.id.id_account);
//
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//
//                switch (item.getItemId()) {
//                    case R.id.id_home:
//                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                        overridePendingTransition(0, 0);
//                        finish();
//                        return true;
//                    case R.id.id_account:
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


        showData();
    }
    private void startEnterAnimation() {
        mRecyclerView.startAnimation(AnimationUtils.loadAnimation(Userlist.this, R.anim.anim_from_top));
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showData() {
        modelList_showlist.clear();
        mLoadingBar=new ProgressDialog(Userlist.this);
        mLoadingBar.setTitle("Fetching Details ");
        mLoadingBar.setMessage("please wait...");
        mLoadingBar.show();
        Cursor cursor = new DatabaseHelper(this).readalldata();
        while(cursor.moveToNext()){
            String balancefromdb = cursor.getString(2);
            Double balance = Double.parseDouble(balancefromdb);

            NumberFormat nf = NumberFormat.getNumberInstance();
            nf.setGroupingUsed(true);
            nf.setMaximumFractionDigits(2);
            nf.setMinimumFractionDigits(2);
            String price = nf.format(balance);

            Model model = new Model(cursor.getString(0), cursor.getString(1), price);
            modelList_showlist.add(model);
            mLoadingBar.dismiss();
        }

        adapter = new CustomeAdapterUserlist(Userlist.this, modelList_showlist);
        mRecyclerView.setAdapter(adapter);

    }

    public void nextActivity(int position) {
        phonenumber = modelList_showlist.get(position).getPhoneno();
        Intent intent = new Intent(Userlist.this, Userdata.class);
        intent.putExtra("phonenumber",phonenumber);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_history, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.action_history){
            startActivity(new Intent(Userlist.this, HistoryList.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
