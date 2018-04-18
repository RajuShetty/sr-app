package com.wordpress.utils;

import com.wordpress.R;
import com.wordpress.modals.Category;

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
            categories.add(new Category("Android","2",R.color.classColor2));
            categories.add(new Category("IOS","3",R.color.classColor2));
            categories.add(new Category("Desktop","5",R.color.classColor2));
            categories.add(new Category("Web","4",R.color.classColor2));
        }
    }
}
