package id.rumahkoding.perpustakaanku.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import id.rumahkoding.perpustakaanku.R;
import id.rumahkoding.perpustakaanku.adapter.RecyclerViewAdapter;
import id.rumahkoding.perpustakaanku.database.DatabaseManager;
import id.rumahkoding.perpustakaanku.model.Book;

public class ListActivity extends AppCompatActivity {

    private List<Book>dataList;
    private RecyclerView bookRecyclerView;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        bookRecyclerView = findViewById(R.id.recycler_view_list_book);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        bookRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getBookData();
    }

    private void getBookData(){
        DatabaseManager dbManager = new DatabaseManager(this);
        dbManager.open();
        dataList = dbManager.getAllBook();
        adapter = new RecyclerViewAdapter(this, dataList);
        bookRecyclerView.setAdapter(adapter);
        dbManager.close();
        Log.d("id", String.valueOf(dataList.size()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add){
            Intent intent = new Intent(this, FormActivity.class);
            startActivity(intent);
        }
        if (id == R.id.delete){
            new AlertDialog.Builder(this)
                    .setTitle("Hapus Semua Data")
                    .setMessage("Anda Yakin ?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DatabaseManager dbManager = new DatabaseManager(ListActivity.this);
                            dbManager.open();
                            dbManager.deleteAll();
                            dataList.clear();
                            adapter.notifyDataSetChanged();
                            Toast.makeText(ListActivity.this, "Data Berhasil di Hapus !", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
