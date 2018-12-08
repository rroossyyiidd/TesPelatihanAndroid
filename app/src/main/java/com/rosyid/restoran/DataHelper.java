package com.rosyid.restoran;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "restoran.db";
    private static final int DATABASE_VERSION = 2;

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table datamenu(no integer primary key autoincrement not null, jenis text null, nama text null, penjelasan text null, harga text null);";
        Log.d("Data", "onCreate" + sql);
        db.execSQL(sql);

        sql = "INSERT INTO datamenu (no, jenis, nama, penjelasan, harga) VALUES ('1','Minuman','Kopi Hitam', 'Kopi Hitam dengan dibuat dengan teknik espresso, dimana biji kopi yang digunakan yaitu berasal dari Aceh Gayo jenis Arabika, disajikan dengan gula terpisah', '10000');";
        Log.d("Data", "onCreate" + sql);
        db.execSQL(sql);

        sql = "INSERT INTO datamenu (no, jenis, nama, penjelasan, harga) VALUES ('2','Minuman','Cappuccino', 'Paduan kopi dengan buih susu kental serta menggunakan sirup karamel, dimana biji kopi yang digunakan berasal dari Gunung Puntang Jawa Barat jenis Robusta', '20000');";
        Log.d("Data", "onCreate" + sql);
        db.execSQL(sql);

        sql = "INSERT INTO datamenu (no, jenis, nama, penjelasan, harga) VALUES ('3','Minuman','Sparkling Tea', 'Minuman Teh yang menggunakan daun teh dari pegunungan daerah Garut dengan tambahan sari melati asli dan gula merah alami', '15000');";
        Log.d("Data", "onCreate" + sql);
        db.execSQL(sql);

        sql = "INSERT INTO datamenu (no, jenis, nama, penjelasan, harga) VALUES ('4','Makanan','Batagor', 'Baso dan Tahuh Goreng dengan sajian bumbu kacang dan kecap khas Bandung', '25000');";
        Log.d("Data", "onCreate" + sql);
        db.execSQL(sql);

        sql = "INSERT INTO datamenu (no, jenis, nama, penjelasan, harga) VALUES ('5','Makanan','Cireng', 'Makanan ringan berupa tepung kanji goreng isi bshsn dasar tempe fermentasi yang disebut oncom, disajikan dengan bumbu kacang pedas', '10000');";
        Log.d("Data", "onCreate" + sql);
        db.execSQL(sql);

        sql = "INSERT INTO datamenu (no, jenis, nama, penjelasan, harga) VALUES ('6','Makanan','Nasi goreng', 'Nasi goreng ayam yang disajikan dengan telur mata sapi disertai satai ayam', '10000');";
        Log.d("Data", "onCreate" + sql);
        db.execSQL(sql);

        sql = "INSERT INTO datamenu (no, jenis, nama, penjelasan, harga) VALUES ('7','Dessert','Cheese Cake', 'Kue Tart 1 slice dengan bahan utama creame cheese dengan topping buah buahan asli seperti anggur, jeruk, kiwi', '40000');";
        Log.d("Data", "onCreate" + sql);
        db.execSQL(sql);

        sql = "INSERT INTO datamenu (no, jenis, nama, penjelasan, harga) VALUES ('8','Dessert','Black salad', 'Potongan buah buah segar yang terdiri dari Pepaya, Jambu, melon dan mangga disajikan dengan bumbu rujak kacang pedas dan gula merah', '30000');";
        Log.d("Data", "onCreate" + sql);
        db.execSQL(sql);

        sql = "create table datapesanan(id integer primary key autoincrement not null, tanggal text null, nomeja text null, jam text null, harga text null);";
        Log.d("Data", "onCreate" + sql);
        db.execSQL(sql);

        sql = "create table detailpesanan(id integer primary key autoincrement not null, id_menu text null, id_pesanan text null);";
        Log.d("Data", "onCreate" + sql);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
