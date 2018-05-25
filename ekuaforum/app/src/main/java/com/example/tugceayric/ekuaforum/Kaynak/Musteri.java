package com.example.tugceayric.ekuaforum.Kaynak;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Tugce Ayric on 21.03.2018.
 */

public class Musteri implements Serializable {
    @SerializedName("id")
    public int id;
    @SerializedName("ad")
    public String ad;
    @SerializedName("soyad")
    public String soyad;
    @SerializedName("telefon")
    public long telefon;
    @SerializedName("sifre")
    public String sifre;
    @SerializedName("mail")
    public String mail;
    @SerializedName("randevuListe")
    public List<Randevu> randevuListe;

}
