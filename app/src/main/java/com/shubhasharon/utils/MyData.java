package com.shubhasharon.utils;

import com.shubhasharon.R;
import com.shubhasharon.modals.Category;

import java.util.ArrayList;

/**
 * Created by mac on 2/16/18.
 */

public class MyData {
    public static boolean isSet=false;
    public static ArrayList<Category> categories = new ArrayList<>();
    public static void addCategories(){
        if(!isSet){
            categories.add(new Category("ALL","", R.color.classColor1));
            categories.add(new Category("Snacks","24",R.color.classColor2));
            categories.add(new Category("Tea time","131",R.color.classColor2));
            categories.add(new Category("Sweets","23",R.color.classColor2));
            categories.add(new Category("Snacks","132",R.color.classColor2));
        }
    }
}
