package com.example.safetv;

public class DataModel {
    private String id,judul,kategori, thumbnailURL, namaakun,  photoURL, videoURL;

//    public DataModel(){};
//
//    public DataModel(String judul,String thumbnailURL,String namaakun,String  photoURL){
//        this.judul= judul;
//        this.thumbnailURL= thumbnailURL;
//        this.namaakun= namaakun;
//        this.photoURL= photoURL;
//    }
    public String getID() {
    return id;
}

    public void setID(String id) {
        this.id = id;
    }

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

    public String getVideoURL(){
        return videoURL;
    }

    public void setVideoURL(String videoURL){
        this.videoURL = videoURL;
    }



}
