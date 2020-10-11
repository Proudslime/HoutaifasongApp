package com.example.houtaifasongapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.et_1);
        textView = findViewById(R.id.tv_1);
        btnStart = findViewById(R.id.btnStart);

        final Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.arg1 == 1){
                    textView.setText("是素数");
                }
                else {
                    textView.setText("不是素数");
                }
            }
        };

        final Runnable myWorker = new Runnable(){
            @Override
            public void run() {
                String s = editText.getText().toString();
                Message msg = new Message();
                if(s != null){
                    int number = Integer.parseInt(editText.getText().toString());
                    if(IsPrime(number)){
                        msg.arg1 = 1;
                        handler.sendMessage(msg);
                    } else {
                        msg.arg1 = 0;
                        handler.sendMessage(msg);
                    }
                }
            }
        };

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread workThread = new Thread(null,myWorker,"WorkTHread");
                workThread.start();
            }
        });
    }

    public boolean IsPrime(int x) {
        boolean b = true;
        if (x == 1 || x % 2 == 0 && x != 2){
            b = false;
        } else {
            for (int i = 3; i < Math.sqrt(x); i += 2){
                if (x % i == 0){
                    b = false;
                    break;
                }
            }
        }
        return b;
    }
}