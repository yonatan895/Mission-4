package com.example.mission_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = findViewById(R.id.main);
        Button b = findViewById(R.id.btn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SalaryActivity.class);
                startActivityForResult(intent,2);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2 ) {
            String avg = data.getStringExtra("avg");
            ArrayList<String> list = data.getStringArrayListExtra("LIST");
            TextView txt_view1 = new TextView(getApplicationContext());
            TextView txt_view2 = new TextView(getApplicationContext());
            txt_view1.setText("List of paychecks:\n ");
            txt_view2.setText("Average salary:\n " + avg);
            for(int i = 0; i < list.size(); i++) {
                txt_view1.append(list.get(i) + " , ");
            }
            layout.addView(txt_view1);
            layout.addView(txt_view2);






        }
    }
}