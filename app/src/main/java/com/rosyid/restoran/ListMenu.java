package com.rosyid.restoran;

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
import android.widget.ListView;

public class ListMenu extends AppCompatActivity {

    String[] daftar, idMenu;
    ListView listView;
    Menu menu;
    DataHelper dataHelper;
    protected Cursor cursor;
    public static ListMenu lm;
    String idPesanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_menu);



        lm = this;
        dataHelper = new DataHelper(this);
        RefreshList();
    }

    public void RefreshList() {
        SQLiteDatabase db = dataHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM datamenu", null);
        daftar = new String[cursor.getCount()];
        idMenu = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(2).toString();
            idMenu[cc] = cursor.getString(0);
        }

        listView = (ListView) findViewById(R.id.pilihanMenu);
        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        listView.setSelected(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String selection = daftar[position];
                final String menuDipilih = idMenu[position];
                final CharSequence[] dialogItem = {"Lihat Detail menu", "Tambahkan ke pesanan"};

                AlertDialog.Builder builder = new AlertDialog.Builder(ListMenu.this);
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
                                idPesanan = getIntent().getStringExtra("idPesanan");
                                SQLiteDatabase db = dataHelper.getWritableDatabase();
                                db.execSQL("INSERT INTO detailpesanan(id_menu, id_pesanan) VALUES ('"+menuDipilih+"','"+idPesanan+"')");
                                TambahMenu.tm.ListItemPesan(idPesanan);
                                finish();
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
