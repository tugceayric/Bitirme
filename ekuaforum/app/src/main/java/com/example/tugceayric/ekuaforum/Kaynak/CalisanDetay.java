package com.example.tugceayric.ekuaforum.Kaynak;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Tugce Ayric on 21.03.2018.
 */

public class CalisanDetay implements Serializable {
    @SerializedName("id")
    public int id;
    @SerializedName("uzmanlik")
    public String uzmanlik;

}
