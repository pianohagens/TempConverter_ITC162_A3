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
        implements TextView.OnEditorActionListener{

    //define variable for the widgets-input
    private EditText inputNumber;
    private Button clearButton;
    //define variable for the widgets-output
    private TextView displyResult;

    // define the Shared Preferences object
    private SharedPreferences saveInput;

    // define instance variables that should keep
    private String inputString = "";

    @Override
    public void onCreate(Bundle savedInstanceInputValue) {
        super.onCreate(savedInstanceInputValue);
        setContentView(R.layout.activity_main);

        // get reference to the widgets - input
        inputNumber = (EditText) findViewById(R.id.inputNumber);
        clearButton = (Button)findViewById(R.id.clearButton);
        // get reference to the widgets-ouput
        displyResult = (TextView) findViewById(R.id.displyResult);

        //set the listeners
        inputNumber.setOnEditorActionListener(this);
        //get shareprefeences object
        saveInput = getSharedPreferences("saveInput", MODE_PRIVATE);
    }
    @Override
    public void onPause(){
        super.onPause();
        //save the instance variables
        SharedPreferences.Editor editor = saveInput.edit();
        editor.putString("inputString", inputString);
        editor.commit();
    }
    @Override
    public void onResume(){
        super.onResume();
        //get instance values
        inputString = saveInput.getString("inputString", "");
        inputNumber.setText(inputString);
        // to converDegreeThenDisplay
        converDegreeThenDisplay();

    }
    private void converDegreeThenDisplay() {
        //get the inputNumber value
        inputString = inputNumber.getText().toString();
        float turnInputNumberToFloat;
        if (inputString.equals("")){
            turnInputNumberToFloat = 0;
        }
        else{
            turnInputNumberToFloat = Float.parseFloat(inputString);
        }
        // f2c convert into formula  (f-32)*5/9
        float f2c = (turnInputNumberToFloat-32)*5/9;

        // display the result C
        NumberFormat degreeC = NumberFormat.getNumberInstance();
        displyResult.setText(degreeC.format(f2c)+ " \u00b0C");

        // display the instance input F
        NumberFormat degreeF = NumberFormat.getNumberInstance();
        inputNumber.setText(degreeF.format(turnInputNumberToFloat)+ " \u00b0F");
    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        if (actionId == EditorInfo.IME_ACTION_DONE ||
                actionId == EditorInfo.IME_ACTION_UNSPECIFIED){
            converDegreeThenDisplay();
        }
        return false;
    }
    //for the clear button
    public void Clear(View clear){
        inputNumber.setText("");
        displyResult.setText("");
    }
}

