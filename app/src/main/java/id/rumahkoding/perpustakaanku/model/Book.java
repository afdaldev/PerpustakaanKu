package id.rumahkoding.perpustakaanku.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    private long id;
    private String judulBuku;
    private String isbnBuku;
    private String tahunTerbit;
    private String penerbit;
    private String kategori;
    private String jumlah;
    private String kodeWarna;
    private Float kualitas;
    private String rangkuman;

    public Book(){}

    protected Book(Parcel in) {
        id = in.readLong();
        judulBuku = in.readString();
        isbnBuku = in.readString();
        tahunTerbit = in.readString();
        penerbit = in.readString();
        kategori = in.readString();
        jumlah = in.readString();
        kodeWarna = in.readString();
        kualitas = in.readFloat();
        rangkuman = in.readString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getJudulBuku() {
        return judulBuku;
    }

    public void setJudulBuku(String judulBuku) {
        this.judulBuku = judulBuku;
    }

    public String getIsbnBuku() {
        return isbnBuku;
    }

    public void setIsbnBuku(String isbnBuku) {
        this.isbnBuku = isbnBuku;
    }

    public String getTahunTerbit() {
        return tahunTerbit;
    }

    public void setTahunTerbit(String tahunTerbit) {
        this.tahunTerbit = tahunTerbit;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getKodeWarna() {
        return kodeWarna;
    }

    public void setKodeWarna(String kodeWarna) {
        this.kodeWarna = kodeWarna;
    }

    public Float getKualitas() {
        return kualitas;
    }

    public void setKualitas(Float kualitas) {
        this.kualitas = kualitas;
    }

    public String getRangkuman() {
        return rangkuman;
    }

    public void setRangkuman(String rangkuman) {
        this.rangkuman = rangkuman;
    }

    public static Creator<Book> getCREATOR() {
        return CREATOR;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(judulBuku);
        dest.writeString(isbnBuku);
        dest.writeString(tahunTerbit);
        dest.writeString(penerbit);
        dest.writeString(kategori);
        dest.writeString(jumlah);
        dest.writeString(kodeWarna);
        dest.writeFloat(kualitas);
        dest.writeString(rangkuman);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
