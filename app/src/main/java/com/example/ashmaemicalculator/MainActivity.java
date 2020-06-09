package com.example.ashmaemicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText p;
    private EditText i;
    private EditText m;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        p =(EditText) findViewById(R.id.principal);
        i = (EditText) findViewById(R.id.interest);
        m = (EditText) findViewById(R.id.months);
        result = (TextView) findViewById(R.id.result);
        Button calculateEMI = (Button) findViewById(R.id.btn1);
        calculateEMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Str1 = p.getText().toString();
                String Str2 = i.getText().toString();
                String Str3 = m.getText().toString();
                if (TextUtils.isEmpty(Str1)){
                    p.setError("Enter Principal Amount");
                    p.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(Str2)){
                    i.setError("Enter Interest Amount");
                    i.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(Str3)){
                    m.setError("Enter Number of Months");
                    m.requestFocus();
                    return;
                }
                float p = Float.parseFloat(Str1);
                float i = Float.parseFloat(Str2);
                float m = Float.parseFloat(Str3);
                float Principle = calPric(p);
                float Interest = calInt(i);
                float Months = calMon(m);
                float fir = calFir(Interest,Months);
                float sec = calSec(Principle,Interest,fir);
                float third = calThird(fir);
                float emi = calEMI(sec,third);

                result.setText(String.valueOf(emi));

            }
        });

    }
    public float calPric(float p){
        return p;
    }
    public float calInt(float i){
        return (i/12/100);
    }
    public float calMon(float m){
        return (m);
    }
    public float calFir(float interest ,float months){
        return (float)(Math.pow(1 + interest,months));
    }
    public float calSec(float principle, float interest, float fir){
        return (principle * interest * fir);
    }
    public float calThird(float fir){
        return (fir-1);
    }
    public float calEMI(float sec, float third){
        return (sec/third);
    }

}
