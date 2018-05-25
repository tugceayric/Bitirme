package com.example.tugceayric.ekuaforum.Kaynak;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Tugce Ayric on 22.03.2018.
 */

public class Randevu implements Serializable {
    @SerializedName("id")
    public int id;
    @SerializedName("tarih")
    public Date tarih;
    @SerializedName("musteri")
    public Musteri musteri;
    @SerializedName("calisan")
    public Calisan calisan;
    @SerializedName("islemListe")
    public List<Islem> islemListe;

}
