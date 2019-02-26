package id.rumahkoding.perpustakaanku.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

import id.rumahkoding.perpustakaanku.model.Book;

public class BookAdapter extends ArrayAdapter<Book> {


    public BookAdapter(Context context, int resource, List<Book> objects) {
        super(context, resource, objects);
    }
}
