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
            /*categories.add(new Category("ALL","", R.color.classColor1));
            categories.add(new Category("2D","27", R.color.classColor1));
            categories.add(new Category("3D","28",R.color.classColor2));
            categories.add(new Category("Creepy","25",R.color.classColor3));
            categories.add(new Category("Entertainment","22",R.color.classColor4));
            categories.add(new Category("Funny","24",R.color.classColor1));
            categories.add(new Category("Games","29",R.color.classColor2));
            categories.add(new Category("Memes","21",R.color.classColor3));
            categories.add(new Category("Overlay","31",R.color.classColor4));
            categories.add(new Category("Sound","34",R.color.classColor1));*/
            categories.add(new Category("ALL","", R.color.classColor1));
            categories.add(new Category("Android","2",R.color.classColor2));
            categories.add(new Category("IOS","3",R.color.classColor2));
            categories.add(new Category("Desktop","4",R.color.classColor2));
            categories.add(new Category("Web","5",R.color.classColor2));
        }
    }
}
