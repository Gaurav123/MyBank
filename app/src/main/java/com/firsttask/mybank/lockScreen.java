package com.firsttask.mybank;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.hanks.passcodeview.PasscodeView;

public class lockScreen extends AppCompatActivity {
    PasscodeView passcodeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //int pass=4;
       setContentView(R.layout.activity_lock_screen);
        passcodeView=(PasscodeView) findViewById(R.id.passcodeview);
        passcodeView.setPasscodeLength(4)
                .setLocalPasscode("8283")
                .setListener(new PasscodeView.PasscodeViewListener() {
                    @Override
                    public void onFail() {
                        Toast.makeText(getApplicationContext(),"Password Is Wrong",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(String number) {
                        Intent into= new Intent(lockScreen.this,MainActivity.class);
                        startActivity(into);

                    }
                });
    }
}