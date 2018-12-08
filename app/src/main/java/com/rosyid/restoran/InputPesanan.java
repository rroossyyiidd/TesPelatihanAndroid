package com.rosyid.restoran;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InputPesanan extends AppCompatActivity {

    EditText tanggal, nomeja, jam;
    Button tambahMenu;
    DataHelper dataHelper;
    String validasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_pesanan);

        tanggal = (EditText) findViewById(R.id.tanggalPesan);
        nomeja = (EditText) findViewById(R.id.nomorMeja);
        jam = (EditText) findViewById(R.id.jamPesan);

        dataHelper = new DataHelper(this);

        tambahMenu = (Button) findViewById(R.id.btnTambahMenu);
        tambahMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dataHelper.getWritableDatabase();

                validasi = tanggal.getText().toString();
                validasi = nomeja.getText().toString();
                validasi = jam.getText().toString();

                if (validasi.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Masih ada field yang belum terisi", Toast.LENGTH_SHORT).show();
                } else {
                    db.execSQL("insert into datapesanan('tanggal', 'nomeja', 'jam') values('" +
                            tanggal.getText().toString() + "','" + nomeja.getText().toString() + "','" + jam.getText().toString() + "')");

                    Toast.makeText(getApplicationContext(), "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), TambahMenu.class);
                    i.putExtra("jam", jam.getText().toString());
                    startActivity(i);
                    finish();
                }

                //DataMahasiswa.da.RefreshList();
            }
        });
    }
}
