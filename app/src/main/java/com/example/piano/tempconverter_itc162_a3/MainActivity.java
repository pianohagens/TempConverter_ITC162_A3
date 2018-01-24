package com.example.piano.tempconverter_itc162_a3;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity
        implements TextView.OnEditorActionListener, View.OnClickListener {

    //define variable for the widgets
    private EditText inputNumber;
    private Button adding;
    private Button decreasing;
    private TextView faTextView;
    private TextView ceTextView;
    private TextView displyResult;


    // define the Shared Preferences object
    private SharedPreferences saveinput;

    // define instance variables that should keep
    private String inputString = "";
    private float needToConvertDgree = .01f;

    @Override
    public void onCreate(Bundle savedInstanceInputValue) {
        super.onCreate(savedInstanceInputValue);
        setContentView(R.layout.activity_main);

        // get reference to the widgets
        inputNumber = (EditText) findViewById(R.id.inputNumber);
        adding = (Button) findViewById(R.id.adding);
        decreasing = (Button) findViewById(R.id.decreasing);
        faTextView = (TextView) findViewById(R.id.faTextView);
        ceTextView = (TextView) findViewById(R.id.ceTextView);
        displyResult = (TextView) findViewById(R.id.displyResult);

        //set the listeners
        inputNumber.setOnEditorActionListener(this);
        adding.setOnClickListener(this);
        decreasing.setOnClickListener(this);

        //get shareprefeences object
        saveinput = getSharedPreferences("saveinput", MODE_PRIVATE);

    }
    @Override
    public void onPause(){
        //save the instance variables
        SharedPreferences.Editor editor = saveinput.edit();
        editor.putString("inputString", inputString);
        editor.putFloat("needToConvertDgree", needToConvertDgree);
        editor.commit();

        super.onPause();
    }
    @Override
    public void onResume() {
        super.onResume();

        //get the instance variables
        inputString = saveinput.getString("inputString", "");
        needToConvertDgree = saveinput.getFloat("needToConvertDgree", 0.01f);

        //set the inputNumber on its widget
        inputNumber.setText(inputString);

        // calculate and display
        converDegreeThenDisplay();

    }

    private void converDegreeThenDisplay() {
        //get the inputNumber
        inputString = inputNumber.getText().toString();
        float turnInputNumberToFloat;
        if (inputString.equals("")){
            turnInputNumberToFloat = 0;
        }
        else{
            turnInputNumberToFloat = Float.parseFloat(inputString);
        }

        // f2c convert  + 32 )*5/9 formula
        float f2c = (turnInputNumberToFloat-32)*5/9;

        // display the result C
        NumberFormat degreeC = NumberFormat.getNumberInstance();
        displyResult.setText(degreeC.format(f2c)+ " \u00b0C");

        // display the instance input F
        NumberFormat degreeF = NumberFormat.getNumberInstance();
        inputNumber.setText(degreeF.format(needToConvertDgree)+ " \u00b0F");
    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        if (actionId == EditorInfo.IME_ACTION_DONE ||
                actionId == EditorInfo.IME_ACTION_UNSPECIFIED){
            converDegreeThenDisplay();
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.adding:
                needToConvertDgree = needToConvertDgree + 0.01f;
                converDegreeThenDisplay();
                break;

            case R.id.decreasing:
                needToConvertDgree = needToConvertDgree - 0.01f;
                converDegreeThenDisplay();
                break;
        }

    }
}

