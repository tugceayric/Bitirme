package com.example.tugceayric.ekuaforum.Kaynak;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Tugce Ayric on 22.03.2018.
 */

public class Model implements Serializable {
    @SerializedName("id")
    public int id;
    @SerializedName("ad")
    public String ad;
}
