package com.tiooooo.myviewmodel;

import androidx.lifecycle.ViewModel;

 class MainViewModel extends ViewModel {

    int result = 0;
    void calculate(String width, String length, String height){
        result = Integer.parseInt(width)*Integer.parseInt(length)*Integer.parseInt(height);
    }
}
