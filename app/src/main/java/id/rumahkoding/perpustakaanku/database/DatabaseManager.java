package id.rumahkoding.perpustakaanku.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import id.rumahkoding.perpustakaanku.model.Book;

public class DatabaseManager {

    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public DatabaseManager(Context context){
        dbHelper = new DatabaseHelper(context);
    }

    public void open(){
        db = dbHelper.getWritableDatabase();
    }

    public void close(){
        db.close();
        dbHelper.close();
    }

    public long tambahBuku(Book book){
        ContentValues cv = new ContentValues();
        cv.put("judul_buku", book.getJudulBuku());
        cv.put("isbn_buku", book.getIsbnBuku());
        cv.put("tahun_terbit", book.getTahunTerbit());
        cv.put("penerbit", book.getPenerbit());
        cv.put("kategori", book.getKategori());
        cv.put("jumlah", book.getJumlah());
        cv.put("kode_warna", book.getKodeWarna());
        cv.put("kualitas", book.getKualitas());
        cv.put("rangkuman", book.getRangkuman());
        return db.insert("BOOK", null, cv);
    }

    public Book getBookById(long id){
        String sql = "SELECT * FROM BOOK WHERE id = ? ";
        Cursor cursor = db.rawQuery(sql, new String[]{Long.toString(id)});
        cursor.moveToFirst();
        Book book = new Book();
        book.setId(cursor.getLong(0));
        book.setJudulBuku(cursor.getString(1));
        book.setIsbnBuku(cursor.getString(2));
        book.setTahunTerbit(cursor.getString(3));
        book.setPenerbit(cursor.getString(4));
        book.setKategori(cursor.getString(5));
        book.setJumlah(cursor.getString(6));
        book.setKodeWarna(cursor.getString(7));
        book.setKualitas(cursor.getFloat(8));
        book.setRangkuman(cursor.getString(9));
        return book;
    }

    public List<Book>getAllBook(){
        String sql = "SELECT * FROM book ORDER BY judul_buku DESC";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        List<Book>books = new ArrayList<>();
        while (!cursor.isAfterLast()){
            Book book = new Book();
            book.setId(cursor.getLong(0));
            book.setJudulBuku(cursor.getString(1));
            book.setIsbnBuku(cursor.getString(2));
            book.setTahunTerbit(cursor.getString(3));
            book.setPenerbit(cursor.getString(4));
            book.setKategori(cursor.getString(5));
            book.setJumlah(cursor.getString(6));
            book.setKodeWarna(cursor.getString(7));
            book.setKualitas(cursor.getFloat(8));
            book.setRangkuman(cursor.getString(9));
            books.add(book);
            cursor.moveToNext();
        }
        return books;
    }

    public long deleteAll(){
        return db.delete("BOOK",null, null);
    }

    public long deleteStudentById(long id){
        return db.delete("BOOK", "id=?",
                new String[]{Long.toString(id)});
    }

    public long updateBook(Book book){
        ContentValues cv = new ContentValues();
        cv.put("judul_buku", book.getJudulBuku());
        cv.put("isbn_buku", book.getIsbnBuku());
        cv.put("tahun_terbit", book.getTahunTerbit());
        cv.put("penerbit", book.getPenerbit());
        cv.put("kategori", book.getKategori());
        cv.put("jumlah", book.getJumlah());
        cv.put("kode_warna", book.getKodeWarna());
        cv.put("kualitas", book.getKualitas());
        cv.put("rangkuman", book.getRangkuman());
        return db.update("BOOK", cv, "id=?",
                new String[]{Long.toString(book.getId())});
    }


}
