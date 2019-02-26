package id.rumahkoding.perpustakaanku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import id.rumahkoding.perpustakaanku.database.DatabaseManager;
import id.rumahkoding.perpustakaanku.model.Book;

public class FormActivity extends AppCompatActivity implements RatingBar.OnRatingBarChangeListener {

    private TextView tv_jumlah;
    private EditText edt_judulBuku, edt_isbnBuku, edt_kodeWarna, edt_rangkuman;
    private Spinner spinner_tahunTerbit, spinner_penerbit;
    private RadioGroup radioGroup_kategori;
    private SeekBar seekBar_jumlah;
    private RatingBar ratingBar_kualitas;
    private DatabaseManager databaseManager;
    private int seekBarValues;
    private float ratingValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        tv_jumlah = findViewById(R.id.tv_jumlah);
        edt_judulBuku = findViewById(R.id.edt_judul_buku);
        edt_isbnBuku = findViewById(R.id.edt_isbn_buku);
        edt_kodeWarna = findViewById(R.id.edt_kode_warna);
        edt_rangkuman = findViewById(R.id.edt_kode_warna);
        spinner_tahunTerbit = findViewById(R.id.spinner_tahun_terbit);
        spinner_penerbit = findViewById(R.id.spinner_penerbit);
        radioGroup_kategori = findViewById(R.id.radio_group_kategori);
        seekBar_jumlah = findViewById(R.id.seekbar_jumlah);
        final RatingBar ratingBar = findViewById(R.id.rating_bar_kualitas);
        ratingBar.setOnRatingBarChangeListener(this);

        seekBar_jumlah.setMax(15);
        seekBar_jumlah.setProgress(0);
        seekBar_jumlah.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String seekBarValue = String.valueOf(progress);
                tv_jumlah.setText(seekBarValue);
                seekBarValues = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        databaseManager = new DatabaseManager(this);
        databaseManager.open();

        long id = getIntent().getLongExtra("id", 0);
        Book book = null;
        if (id > 0){
            book = databaseManager.getBookById(id);
        }
        if (book != null){
            edt_judulBuku.setText(book.getJudulBuku());
            edt_isbnBuku.setText(book.getIsbnBuku());
            edt_kodeWarna.setText(book.getKodeWarna());
            edt_rangkuman.setText(book.getRangkuman());

            ArrayAdapter adapterTahunTerbit = (ArrayAdapter) spinner_tahunTerbit.getAdapter();
            int positionTahunTerbit = adapterTahunTerbit.getPosition(book.getTahunTerbit());
            spinner_tahunTerbit.setSelection(positionTahunTerbit);

            ArrayAdapter adapterPenerbit = (ArrayAdapter) spinner_penerbit .getAdapter();
            int positionPenerbit = adapterPenerbit.getPosition(book.getPenerbit());
            spinner_penerbit.setSelection(positionPenerbit);

            String kategori = book.getKategori();
            if (kategori.equals("Buku Agama")){
                radioGroup_kategori.check(R.id.radio_button_buku_agama);
            }else if (kategori.equals("Buku Komputer")){
                radioGroup_kategori.check(R.id.radio_button_buku_komputer);
            }else if (kategori.equals("Novel")){
                radioGroup_kategori.check(R.id.radio_button_novel);
            }else if (kategori.equals(R.id.radio_button_lain_lain));


        }

        ArrayList<String> years = new ArrayList<>();
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1900; i <= thisYear; i++){
            years.add(Integer.toString(i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, years);
        Spinner spinnerYear = findViewById(R.id.spinner_tahun_terbit);
        spinnerYear.setAdapter(adapter);

    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        Toast.makeText(this, "Rating : "+ rating, Toast.LENGTH_SHORT).show();
        ratingValue = rating;
    }

    //Validasi Form
    private boolean isValidForm(){
        boolean isValid = false;

        if (edt_judulBuku.getText().toString().isEmpty()){
            edt_judulBuku.setError("Judul Buku Harus Diisi");
            return false;
        }
        if (edt_isbnBuku.getText().toString().isEmpty()){
            edt_isbnBuku.setError("ISBN harus Diisi");
            return false;
        }
        if (edt_kodeWarna.getText().toString().isEmpty()){
            edt_kodeWarna.setError("Warna Harus Diisi");
            return false;
        }
        if (edt_rangkuman.getText().toString().isEmpty()){
            edt_rangkuman.setError("Rangkuman Harus Diisi");
            return false;
        }
        isValid = true;
        return isValid;
    }

    //Tambah Buku
    private void tambahBuku(){
        boolean isValid = isValidForm();
        String judulBuku = edt_judulBuku.getText().toString();
        String isbnBuku = edt_isbnBuku.getText().toString();
        String kodeWarna = edt_kodeWarna.getText().toString();
        String rangkuman = edt_rangkuman.getText().toString();
        int kategoriCek = radioGroup_kategori.getCheckedRadioButtonId();

        String tahunTerbit = spinner_tahunTerbit.getSelectedItem().toString();
        String penerbit = spinner_penerbit.getSelectedItem().toString();

        String kategori = "";

        if (kategoriCek == R.id.radio_button_buku_agama){
            kategori = "Buku Agama";
        }else if (kategoriCek == R.id.radio_button_buku_komputer){
            kategori = "Buku Komputer";
        }else if (kategoriCek == R.id.radio_button_novel){
            kategori = "Novel";
        }else if (kategoriCek == R.id.radio_button_lain_lain){
            kategori = "Lain-lain";
        }else {
            return;
        }

        Book book = new Book();
        book.setJudulBuku(judulBuku);
        book.setIsbnBuku(isbnBuku);
        book.setTahunTerbit(tahunTerbit);
        book.setPenerbit(penerbit);
        book.setKategori(kategori);
        book.setJumlah(String.valueOf(seekBarValues));
        book.setKodeWarna(kodeWarna);
        book.setKualitas(ratingValue);
        book.setRangkuman(rangkuman);

        if (!isValid){
            isValidForm();
        }else {
            long intentId = getIntent().getLongExtra("id", 0);
            if (intentId > 0){
                book.setId(intentId);
                databaseManager.updateBook(book);
                Toast.makeText(this, "Data Berhasil di Update", Toast.LENGTH_SHORT).show();
                finish();
            }else {
                long id = databaseManager.tambahBuku(book);
                if (id == -1){
                    Toast.makeText(this, "Data Gagal Disimpan", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "Data Berhasil Disimpan dengan id "+id, Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.form_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.save){
            tambahBuku();
        }
        return super.onOptionsItemSelected(item);
    }
}
