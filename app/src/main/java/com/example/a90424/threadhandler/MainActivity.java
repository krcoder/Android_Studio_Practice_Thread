package com.example.a90424.threadhandler;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView;
    TextView textView2;
    TextView textView3;
    Button button, button2, button3, button_start;
    Message msg;
    long start;

    Boolean isRunning = true;
    ThreadClass tc = new ThreadClass();
    HandlerClass hc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        textView2 = (TextView)findViewById(R.id.textView2);
        textView3 = (TextView)findViewById(R.id.textView6);
        button = (Button)findViewById(R.id.button);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button_start = (Button)findViewById(R.id.button_start);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

        hc = new HandlerClass();

        tc.start();





    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){

            int data1 = data.getIntExtra("reckey1", 0);
            double data2 = data.getDoubleExtra("reckey2", 0);
            String data3 = data.getStringExtra("reckey3");

            textView3.setText("recKey1 : "+data1+"\n");
            textView3.append("recKey2 : "+data2+"\n");
            textView3.append("recKey3 : "+data3+"\n");
        }

    }

    @Override
    public void onClick(View view) {
        if(view == button){
            long t = System.currentTimeMillis();
            Date date = new Date(t);
            SimpleDateFormat sdfNow = new SimpleDateFormat(":HH시 :mm분 :ss.SS초: :");
            String strNow = sdfNow.format(date);

            textView2.setText(strNow);
        }

        else if(view == button2){
            Intent intent1 = new Intent(this, SubActivity1.class);
            intent1.putExtra("key1", 100);
            intent1.putExtra("key2", 12.34);
            intent1.putExtra("key3", "안녕하세요");

            startActivity(intent1);

            startActivityForResult(intent1, 1);


        }
        else if(view == button3){
            Intent intent2 = new Intent(this, SubActivity2.class);
            startActivity(intent2);

        }
        else if(view == button_start){
            start = System.currentTimeMillis();

        }
    }


    class HandlerClass extends Handler{
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            if(msg.what == 0){
                long t = System.currentTimeMillis() - start;
                Date date = new Date(t);
                SimpleDateFormat sdfNow = new SimpleDateFormat(":HH시 :mm분 :ss.SS초 :");
                String strNow = sdfNow.format(date);
                textView.setText(strNow);

            }
            else if(msg.what == 1){

                textView.setText("b"+msg.obj);
                textView2.setText("");
                }
            else{

            }

        }
    }


    public void onGetTime(View view){
        long t = System.currentTimeMillis();
        textView2.setText(""+t);
    }




    class ThreadClass extends Thread{
        public void run(){
            while(isRunning){
                SystemClock.sleep(10);
                long t = System.currentTimeMillis();

                //Log.d("msg","현재시간 : " + t);
                //textView.setText("");

                hc.sendEmptyMessage(0);
                /*
                Message msg = new Message();
                msg.what = 1; //TIME NOTIFICATION
                msg.obj = t;

                hc.sendMessage(msg);
                */
            }
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

        isRunning = false;
    }
}
