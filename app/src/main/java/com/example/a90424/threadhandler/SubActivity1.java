package com.example.a90424.threadhandler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SubActivity1 extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub1);

        textView = (TextView)findViewById(R.id.textView5);

        Intent intent = getIntent();
        int data1 = intent.getIntExtra("key1",0);
        double data2 = intent.getDoubleExtra("key2",0);
        String data3 = intent.getStringExtra("key3");

        textView.setText("key1 : "+data1+"\n");
        textView.append("key2 : "+data2+"\n");
        textView.append("key3 : "+data3+"\n");



    }

    public void onBtnBack(View view){
        Intent intent = new Intent();
        intent.putExtra("recKey1",12);
        intent.putExtra("recKey2",55.05);
        intent.putExtra("reckey3","powertext");
        setResult(RESULT_OK, intent);



        finish();
    }
}
