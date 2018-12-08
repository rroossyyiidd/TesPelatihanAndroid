package com.rosyid.restoran;

import android.app.LauncherActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class TambahMenu extends AppCompatActivity {

    String[] daftar, idDetailMenu;
    ListView listView;
    Menu menu;
    DataHelper dataHelper;
    protected Cursor cursor;
    public static TambahMenu tm;
    TextView tanggal, nomeja, jam;
    Button tambahItem, simpanPesanan;
    String idPesanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_menu);

        tanggal = (TextView) findViewById(R.id.tanggalPesan1);
        nomeja = (TextView) findViewById(R.id.nomorMeja1);
        jam = (TextView) findViewById(R.id.jamPesan1);

        tambahItem = (Button) findViewById(R.id.btnTambahItem);

        dataHelper = new DataHelper(this);
        SQLiteDatabase db = dataHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM datapesanan WHERE jam = '" + getIntent().getStringExtra("jam") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);

            idPesanan = cursor.getString(0);
            tanggal.setText("Tanggal pesan: " + cursor.getString(1));
            nomeja.setText("Nomor meja: " + cursor.getString(2));
            jam.setText("Jam pesan: " + cursor.getString(2));
        }

        tambahItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ListMenu.class);
                i.putExtra("idPesanan", idPesanan);
                startActivity(i);
            }
        });

        simpanPesanan = (Button) findViewById(R.id.btnSimpanPesanan);
        simpanPesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dataHelper.getWritableDatabase();

            }
        });

        tm = this;
        ListItemPesan(idPesanan);
    }

    public void ListItemPesan(final String val) {
        SQLiteDatabase db = dataHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT detailpesanan.id, datamenu.nama FROM detailpesanan JOIN datamenu ON detailpesanan.id = datamenu.no WHERE detailpesanan.id_pesanan = '" + val + "'", null);
        daftar = new String[cursor.getCount()];
        idDetailMenu = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            idDetailMenu[cc] = cursor.getString(0).toString();
            daftar[cc] = cursor.getString(1).toString();
        }

        listView = (ListView) findViewById(R.id.listItemMenu);
        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        listView.setSelected(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String selection = daftar[position];
                final String idDetail = idDetailMenu[position];
                final CharSequence[] dialogItem = {"Lihat Detail menu","Hapus dari daftar"};

                AlertDialog.Builder builder = new AlertDialog.Builder(TambahMenu.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Intent i = new Intent(getApplicationContext(), DetailMenu.class);
                                Log.d("data extra: ", selection);
                                i.putExtra("nama", selection);
                                startActivity(i);
                                break;
                            case 1:
                                SQLiteDatabase db = dataHelper.getWritableDatabase();
                                db.execSQL("DELETE from detailpesanan where id = '" + idDetail + "'");
                                ListItemPesan(val);
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter) listView.getAdapter()).notifyDataSetInvalidated();
    }
}
