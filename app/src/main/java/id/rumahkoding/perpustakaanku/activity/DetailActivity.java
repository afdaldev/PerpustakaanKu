package id.rumahkoding.perpustakaanku.activity;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import id.rumahkoding.perpustakaanku.R;
import id.rumahkoding.perpustakaanku.model.Book;

public class DetailActivity extends AppCompatActivity {

    private ImageView imgDetailBuku;
    private RatingBar detailRatingBar;
    private TextView tvDetailJudulBuku, tvDetailIsbn, tvDetailTahunTerbit, tvDetailPenerbit, tvDetailKategori, tvDetailJumlah, tvDetailRangkuman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (getSupportActionBar() != null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imgDetailBuku = findViewById(R.id.img_detail_buku);
        detailRatingBar = findViewById(R.id.detail_ratingBar);
        detailRatingBar.setEnabled(false);
        tvDetailJudulBuku = findViewById(R.id.detail_judul_buku);
        tvDetailIsbn = findViewById(R.id.detail_isbn);
        tvDetailTahunTerbit = findViewById(R.id.detail_tahun_terbit);
        tvDetailPenerbit = findViewById(R.id.detail_penerbit);
        tvDetailKategori = findViewById(R.id.detail_kategori);
        tvDetailJumlah = findViewById(R.id.detail_jumlah);
        tvDetailRangkuman = findViewById(R.id.detail_rangkuman);

        Book book = getIntent().getParcelableExtra("book");
        imgDetailBuku.setBackgroundColor(Color.parseColor(book.getKodeWarna()));
        detailRatingBar.setRating(Float.parseFloat(String.format(String.valueOf(book.getKualitas()))));
        tvDetailJudulBuku.setText(String.format(book.getJudulBuku()));
        tvDetailIsbn.setText(String.format(book.getIsbnBuku()));
        tvDetailTahunTerbit.setText(String.format(book.getTahunTerbit()));
        tvDetailPenerbit.setText(String.format(book.getPenerbit()));
        tvDetailKategori.setText(String.format(book.getKategori()));
        tvDetailJumlah.setText(String.format(book.getJumlah()));
        tvDetailRangkuman.setText(String.format(book.getRangkuman()));
        Log.d("Rangkuman", book.getRangkuman());

        //BorderTextView
        ShapeDrawable sd = new ShapeDrawable();
        sd.setShape(new RectShape());
        sd.getPaint().setColor(Color.parseColor(book.getKodeWarna()));
        sd.getPaint().setStrokeWidth(10);
        sd.getPaint().setStyle(Paint.Style.STROKE);
        tvDetailJudulBuku.setBackground(sd);
        tvDetailIsbn.setBackground(sd);
        tvDetailTahunTerbit.setBackground(sd);
        tvDetailPenerbit.setBackground(sd);
        tvDetailKategori.setBackground(sd);
        tvDetailJumlah.setBackground(sd);
        tvDetailRangkuman.setBackground(sd);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
