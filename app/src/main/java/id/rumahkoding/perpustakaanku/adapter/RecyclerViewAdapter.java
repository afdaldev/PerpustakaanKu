package id.rumahkoding.perpustakaanku.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import id.rumahkoding.perpustakaanku.activity.DetailActivity;
import id.rumahkoding.perpustakaanku.R;
import id.rumahkoding.perpustakaanku.model.Book;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Book> dataList;
    private Context context;

    public RecyclerViewAdapter(Context context, List<Book>dataList){
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.book_item, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        String ratingValue = String.valueOf(dataList.get(i).getKualitas());

        viewHolder.tvjudulBuku.setText(dataList.get(i).getJudulBuku());
        viewHolder.tvkategori.setText(dataList.get(i).getKategori());
        viewHolder.tvpenerbit.setText(dataList.get(i).getPenerbit());
        viewHolder.imgBook.setBackgroundColor(Color.parseColor(dataList.get(i).getKodeWarna()));
        viewHolder.ratingBar.setRating(Float.parseFloat(ratingValue));
        Log.d("id", String.valueOf(dataList.get(i).getKualitas()));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView tvjudulBuku, tvkategori, tvpenerbit;
        private ImageView imgBook;
        private RatingBar ratingBar;

        public ViewHolder(View itemView) {
            super(itemView);
            tvjudulBuku = itemView.findViewById(R.id.text_view_judul_buku);
            tvkategori = itemView.findViewById(R.id.text_view_kategori);
            tvpenerbit = itemView.findViewById(R.id.text_view_penerbit);
            imgBook = itemView.findViewById(R.id.img_book);
            ratingBar = itemView.findViewById(R.id.rating_bar_list_book);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Book book = dataList.get(position);

            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("book", book);
            context.startActivity(intent);
        }
    }
}