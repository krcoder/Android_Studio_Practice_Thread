package com.example.a90424.threadhandler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SubActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);
    }

    public void onBtnBack(View view){
        finish();
    }
}
