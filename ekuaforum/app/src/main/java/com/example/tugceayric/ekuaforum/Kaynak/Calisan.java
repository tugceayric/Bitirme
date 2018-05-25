package com.example.tugceayric.ekuaforum.Kaynak;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Tugce Ayric on 21.03.2018.
 */

public class Calisan implements Serializable {
    @SerializedName("id")
    public int id;
    @SerializedName("ad")
    public String ad;
    @SerializedName("soyad")
    public String soyad;
    @SerializedName("adres")
    public  String adres;
    @SerializedName("sifre")
    public String sifre;
    @SerializedName("tc")
    public long tc;
    @SerializedName("telefon")
    public long telefon;
    @SerializedName("meslek")
    public String meslek;
    @SerializedName("randevuListe")
    public List<Randevu> randevuListe;


}
