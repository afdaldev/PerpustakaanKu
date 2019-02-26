package id.rumahkoding.perpustakaanku.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "Perpustakaan", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE BOOK (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "judul_buku TEXT, " +
                "isbn_buku TEXT, " +
                "tahun_terbit TEXT, " +
                "penerbit TEXT, " +
                "kategori TEXT, " +
                "jumlah TEXT, " +
                "kode_warna TEXT, " +
                "kualitas TEXT, " +
                "rangkuman TEXT) ";
        db.execSQL(sql);
                
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
