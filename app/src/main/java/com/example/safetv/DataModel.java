package com.example.safetv;

public class DataModel {
    private String judul,kategori, thumbnailURL, namaakun,  photoURL;

//    public DataModel(){};
//
//    public DataModel(String judul,String thumbnailURL,String namaakun,String  photoURL){
//        this.judul= judul;
//        this.thumbnailURL= thumbnailURL;
//        this.namaakun= namaakun;
//        this.photoURL= photoURL;
//    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori= kategori;
    }

    public String getThumbnailURL(){
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL){
        this.thumbnailURL = thumbnailURL;
    }

    public String getNamaakun() {
        return namaakun;
    }

    public void setNamaakun(String namaakun) {
        this.namaakun = namaakun;
    }

    public String getPhotoURL(){
        return photoURL;
    }

    public void setPhotoURL(String photoURL){
        this.photoURL = photoURL;
    }



}
