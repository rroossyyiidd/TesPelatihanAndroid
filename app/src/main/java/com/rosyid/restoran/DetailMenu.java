package com.rosyid.restoran;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailMenu extends AppCompatActivity {

    protected Cursor cursor;
    TextView nama, harga, penjelasan, jenis;
    DataHelper dataHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_menu);

        nama = (TextView) findViewById(R.id.namaMenu);
        harga = (TextView) findViewById(R.id.harga);
        penjelasan = (TextView) findViewById(R.id.penjelasan);
        jenis = (TextView) findViewById(R.id.jenis);

        dataHelper = new DataHelper(this);
        SQLiteDatabase db = dataHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM datamenu WHERE nama = '" + getIntent().getStringExtra("nama") + "'", null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);

            jenis.setText(cursor.getString(1).toString());
            nama.setText(cursor.getString(2).toString());
            penjelasan.setText(cursor.getString(3).toString());
            harga.setText(cursor.getString(4).toString());
        }
    }
}
