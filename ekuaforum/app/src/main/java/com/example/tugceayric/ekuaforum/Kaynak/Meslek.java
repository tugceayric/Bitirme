package com.example.tugceayric.ekuaforum.Kaynak;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Tugce Ayric on 28.03.2018.
 */

public class Meslek implements Serializable {
    @SerializedName("id")
    public int id;
    @SerializedName("ad")
    public String ad;
    @SerializedName("cdetay")
    public List<CalisanDetay> cdetay;
}
