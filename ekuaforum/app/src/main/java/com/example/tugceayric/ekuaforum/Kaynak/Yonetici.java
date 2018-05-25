package com.example.tugceayric.ekuaforum.Kaynak;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Tugce Ayric on 22.03.2018.
 */

public class Yonetici implements Serializable {
    @SerializedName("id")
    public int id;
    @SerializedName("kullaniciadi")
    public String kullaniciadi;
    @SerializedName("sifre")
    public String sifre;

}
