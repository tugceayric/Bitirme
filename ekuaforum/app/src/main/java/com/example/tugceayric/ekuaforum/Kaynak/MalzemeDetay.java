package com.example.tugceayric.ekuaforum.Kaynak;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Tugce Ayric on 22.03.2018.
 */

public class MalzemeDetay implements Serializable {
    @SerializedName("id")
    public int id;
    @SerializedName("renk")
    public String renk;
    @SerializedName("malzemem")
    public List<Malzeme>malzemem;
}
