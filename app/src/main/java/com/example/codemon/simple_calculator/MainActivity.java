package com.example.codemon.simple_calculator;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codemon.simple_calculator.R;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "op";

    Button[] digits = new Button[10];

    Button add, sub, mul, div, equalto, clear;

    TextView etnum;

    public String str1 = "";
    public String str2 = "";
    public String operation = "";
    public boolean help = false;
    public int freq = 0;


    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        digits[0] = findViewById(R.id.zero);
        digits[1] = findViewById(R.id.one);
        digits[2] = findViewById(R.id.two);
        digits[3] = findViewById(R.id.three);
        digits[4] = findViewById(R.id.four);
        digits[5] = findViewById(R.id.five);
        digits[6] = findViewById(R.id.six);
        digits[7] = findViewById(R.id.seven);
        digits[8] = findViewById(R.id.eight);
        digits[9] = findViewById(R.id.nine);
        add = findViewById(R.id.add);
        sub = findViewById(R.id.sub);
        mul = findViewById(R.id.prod);
        div = findViewById(R.id.div);
        equalto = findViewById(R.id.equals);
        etnum = findViewById(R.id.etnum);
        clear = findViewById(R.id.clear);
        try {
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (str1 == "") {
                        return;
                    }

                    help = true;
                    if (freq >= 1) {
                        setTextView();
                        str2 = "";
                    }
                    else
                    {
                        etnum.setText("");
                    }
                    operation = "+";
                    freq++;
                }
            });

            sub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (str1 == "") {
                        return;
                    }
                    help = true;
                    if (freq >= 1) {
                        setTextView();
                        str2 = "";
                    }
                    else
                    {
                        etnum.setText("");
                    }
                    operation = "-";
                    freq++;
                        }
            });

            mul.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (str1 == "") {
                        return;
                    }
                    help = true;
                    if (freq >= 1) {
                        setTextView();
                        str2 = "";
                    }
                    else
                    {
                        etnum.setText("");
                    }
                    freq++;
                    operation = "*";
                   }
            });

            div.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (str1 == "") {
                        return;
                    }

                    help = true;
                    if (freq >= 1) {
                        setTextView();
                        str2 = "";
                    }
                    else
                    {
                        etnum.setText("");
                    }
                    freq++;
                    operation = "/";
                   }
            });


            equalto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    help = false;
                    if (str1 == "" || str2 == "") {
                        str1 = "";
                        str2 = "";
                        return;
                    }
                    Log.d(TAG, " " + str1 + " " + operation + " " + str2);
                    Double num1 = Double.parseDouble(str1);
                    Double num2 = Double.parseDouble(str2);
                    if (operation == "+") {
                        str1 = "" + (num1 + num2);
                        str2 = "";
                        help = true;
                        setTextInSize(num1, num2);
                    } else if (operation == "-") {
                        str1 = "" + (num1 - num2);
                        str2 = "";
                        help = true;
                        setTextInSize(num1, num2);
                    } else if (operation == "*") {
                        str1 = "" + (num1 * num2);
                        str2 = "";
                        help = true;
                        setTextInSize(num1, num2);
                    } else if (operation == "/") {
                        str1 = "" + (num1 / num2);
                        str2 = "";
                        help = true;
                        setTextInSize(num1, num2);
                    } else {
                        str1 = "";
                        str2 = "";
                        help = false;
                        Toast.makeText(MainActivity.this, "Invalid Operation performed", Toast.LENGTH_SHORT).show();
                    }
                    freq = 0;
                }
            });

            clear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    help = false;
                    operation = "";
                    etnum.setText("");
                    str1 = "";
                    str2 = "";
                    freq = 0;
                }
            });


            for (int i = 0; i < 10; i++) {
                final String finalI = "" + i;
                digits[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!help) {
                            str1 = str1 + finalI;
                            setTextInSize(str1);
                        } else {
                            str2 = str2 + finalI;
                            setTextInSize(str2);
                        }
                    }
                });
            }
        }
        catch(Exception e){
            Toast.makeText(this, "Invalid Operation", Toast.LENGTH_SHORT).show();
        }
    }

    private void setTextView() {
        try {
            if (operation == "+") {
                str1 = "" + (Integer.parseInt(str1) + Integer.parseInt(str2));
            } else if (operation == "-") {
                str1 = "" + (Integer.parseInt(str1) - Integer.parseInt(str2));
            } else if (operation == "*") {
                str1 = "" + (Integer.parseInt(str1) * Integer.parseInt(str2));
            } else if (operation == "/") {
                str1 = "" + (Double.parseDouble(str1) / Integer.parseInt(str2));
            }
            etnum.setText("");
        }
        catch (Exception e){
            Toast.makeText(this, "Invalid operation", Toast.LENGTH_SHORT).show();
        }
    }

    private void setTextInSize(String str)
    {
        try {
            if (str.length() < 14)
                etnum.setText(str);
            else
                etnum.setText(str.substring(0, 14));
        }
        catch(Exception e){
            Toast.makeText(this, "Invalid operation", Toast.LENGTH_SHORT).show();
        }
    }


    private  void setTextInSize(double num1,double num2){
        try {
            if (operation == "+") {
                if (num1 + num2 == (int) (num1 + num2)) {
                    setTextInSize(String.format("%.0f", num1 + num2));
                } else {
                    setTextInSize(String.format("%.2f", num1 + num2));
                }
            } else if (operation == "-") {
                if (num1 - num2 == (int) (num1 - num2)) {
                    setTextInSize(String.format("%.0f", num1 - num2));
                } else {
                    setTextInSize(String.format("%.2f", num1 - num2));
                }
            } else if (operation == "*") {
                if (num1 * num2 == (int) (num1 * num2)) {
                    setTextInSize(String.format("%.0f", num1 * num2));
                } else {
                    setTextInSize(String.format("%.2f", num1 * num2));
                }
            } else if (operation == "/") {
                if (num1 / num2 == (int) (num1 / num2)) {
                    setTextInSize(String.format("%.0f", num1 / num2));
                } else {
                    setTextInSize(String.format("%.2f", num1 / num2));
                }
            }
        }
        catch (Exception e){
            Toast.makeText(this, "Invalid operation", Toast.LENGTH_SHORT).show();
        }
    }
}
