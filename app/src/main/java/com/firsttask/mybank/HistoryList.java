package com.firsttask.mybank;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

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

public class HistoryList extends AppCompatActivity {
    List<Model> modelList_historylist = new ArrayList<>();
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    CustomeAdapterHistory adapter;
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    private ProgressDialog mLoadingBar;

    TextView history_empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_history);
        drawerLayout = findViewById(R.id.drawerLayout);

        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
//        bottomNavigationView =(BottomNavigationView) findViewById(R.id.bottomNavigationView);
//        bottomNavigationView.setSelectedItemId(R.id.id_about);
//
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
//
//        {
//            @Override
//            public boolean onNavigationItemSelected (@NonNull MenuItem item){
//
//                switch (item.getItemId()) {
//                    case R.id.id_home:
//                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                        overridePendingTransition(0, 0);
//                        finish();
//                        return true;
//                    case R.id.id_account:
//                        startActivity(new Intent(getApplicationContext(), Userlist.class));
//                        overridePendingTransition(0, 0);
//                        finish();
//                        return true;
//                    case R.id.id_about:
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
//
        history_empty = findViewById(R.id.empty_text);
        showData();

    }

    private void showData() {
        modelList_historylist.clear();
        mLoadingBar=new ProgressDialog(HistoryList.this);
        mLoadingBar.setTitle("Fetching Details ");
        mLoadingBar.setMessage("please wait...");
        mLoadingBar.show();
        Cursor cursor = new com.firsttask.mybank.DatabaseHelper(this).readtransferdata();

        while (cursor.moveToNext()) {
            String balancefromdb = cursor.getString(4);
            Double balance = Double.parseDouble(balancefromdb);

            NumberFormat nf = NumberFormat.getNumberInstance();
            nf.setGroupingUsed(true);
            nf.setMaximumFractionDigits(2);
            nf.setMinimumFractionDigits(2);
            String price = nf.format(balance);

            Model model = new Model(cursor.getString(2), cursor.getString(3), price, cursor.getString(1), cursor.getString(5));
            modelList_historylist.add(model);
            mLoadingBar.dismiss();
        }

        adapter = new CustomeAdapterHistory(HistoryList.this, modelList_historylist);
        mRecyclerView.setAdapter(adapter);

        if(modelList_historylist.size() == 0){
            history_empty.setVisibility(View.VISIBLE);
        }

    }

}
