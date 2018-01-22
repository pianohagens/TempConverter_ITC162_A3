package com.example.piano.tempconverter_itc162_a3;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView faTextView;
    TextView ceTextView;
    TextView displyResult;
    EditText inputNumber;
    Button convert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void add(View v)
    {
        ceTextView =(TextView)findViewById(R.id.ceTextView);
        faTextView =(TextView)findViewById(R.id.faTextView);
        displyResult=(TextView)findViewById(R.id.displyResult);
        inputNumber=(EditText)findViewById(R.id.inputNumber);

        //get value from edit text box and convert into double
        double a=Double.parseDouble(String.valueOf(inputNumber.getText()));

        //check which faTextView is checked
        if(inputNumber.isChecked())
        {
            //display conversion
            displyResult.setText(f2c(a)+" degree C");
            //faTextView.setChecked(false);
            inputNumber.setChecked(true);
        }
    }
    //Fahrenhiet to Celcius method
    private double f2c(double faTextView)
    {
        return (faTextView-32)*5/9;
    }
}

