package com.example.mission_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class SalaryActivity extends AppCompatActivity{
    LinearLayout layout;
    double avg = 0;
    double sum = 0;
    ArrayList<EditText> editList = new ArrayList<>();
    ArrayList<String> list = new ArrayList<>();
    int cnt = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary);

        layout = findViewById(R.id.salary);
        for(int i = 1; i <=6; i++) {
            EditText edit = new EditText(getApplicationContext());
            edit.setWidth(200);
            edit.setHeight(200);
            edit.setId(i);
            edit.setInputType(InputType.TYPE_CLASS_NUMBER);
            layout.addView(edit);
            editList.add(edit);

        }
        Button b1 = new Button(getApplicationContext());
        b1.setText("Submit");
        Button b2 = new Button(getApplicationContext());
        b2.setText("Cancel");
        b2.setBackgroundColor(Color.RED);
        layout.addView(b1);
        layout.addView(b2);
        b1.setEnabled(false);



       for(EditText edit : editList) {
            edit.addTextChangedListener(new TextWatcher() {
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    if(edit.toString().trim().length() != 0) {
                        cnt++;
                        b1.setEnabled(cnt == 6); //Enable submit when the user has entered all 6 salaries.
                    }

                }
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }


 /* This Try and Catch methods are useless, because of Override.
    They are still here because I get a bunch of errors when I try to remove them.
         */


        try {
            b1.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v){
                    for(int i = 0; i < 6; i++) {
                        String text = String.valueOf(editList.get(i).getText());
                        list.add(text);
                        int num = Integer.parseInt(text);
                        sum += num;
                    }

                    avg = sum/6;
                    String txt_avg = String.valueOf(avg);
                    Intent intent = new Intent();
                    intent.putExtra("avg", txt_avg);
                    intent.putStringArrayListExtra("LIST", list);
                    setResult(2, intent);
                    finish();
                }

            });
        }catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Missing a number!", Toast.LENGTH_SHORT).show();
        }

        b2.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
               finish(); // If user presses cancel, it will destroy the activity.

           }

        });
    }
}