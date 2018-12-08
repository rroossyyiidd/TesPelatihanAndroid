package com.rosyid.restoran;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnPelayan, btnDapur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPelayan = (Button) findViewById(R.id.btnPelayan);
        btnDapur = (Button) findViewById(R.id.btnDapur);

        btnPelayan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), HalamanPelayan.class);
                startActivity(i);
            }
        });

        btnDapur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), HalamanDapur.class);
                startActivity(i);
            }
        });

    }
}
