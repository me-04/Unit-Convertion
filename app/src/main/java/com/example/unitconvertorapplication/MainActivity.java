package com.example.unitconvertorapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private Spinner sourceUnitSpinner;
    private Spinner targetUnitSpinner;
    private EditText sourceValueEditText;
    private EditText resultEditText;
    private Button convertButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize UI elements
        sourceUnitSpinner=findViewById(R.id.sourceUnitSpinner);
        targetUnitSpinner=findViewById(R.id.targetUnitSpinner);
        sourceValueEditText=findViewById(R.id.sourceValueEditText);
        resultEditText=findViewById(R.id.resultEditText);
        convertButton=findViewById(R.id.convertButton);

        //set up unit spinner
        //adapter is used in android to display an array of data. Here its a CharSequence, meaning a spinner or listview
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.length_units, android.R.layout.simple_spinner_dropdown_item);

        sourceUnitSpinner.setAdapter(adapter);
        targetUnitSpinner.setAdapter(adapter);

        //set up conversion logic
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performConversion();
            }
        });
    }

    private void performConversion(){
        //get user input
        String sourceValueStr=sourceValueEditText.getText().toString();
        double sourceValue;
        try{
            sourceValue=Double.parseDouble(sourceValueStr);
        }
        catch(NumberFormatException e){
            resultEditText.setText(R.string.invalid_input);
            return;
        }

        //get selected units
        String sourceUnit=sourceUnitSpinner.getSelectedItem().toString();
        String targetUnit=targetUnitSpinner.getSelectedItem().toString();

        //perform calculations
        double convertedValue=convert(sourceUnit,targetUnit,sourceValue);

        //update the UI with the result
        resultEditText.setText(String.valueOf(convertedValue));
    }

    public static double convert(String sourceUnit, String targetUnit, double value){
        if (sourceUnit.equals("Meter") && targetUnit.equals("Centimeter")) {
            return value*100;
        } else if(sourceUnit.equals("Centimeter") && targetUnit.equals("Meter")){
            return value/100;
        }else{
            return value;
        }
    }
}



