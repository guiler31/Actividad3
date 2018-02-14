package com.example.guille.dint5;

import android.view.View;


public class ScrollingActivityEvents implements View.OnClickListener{
    ScrollingActivity scrollingActivity;
    boolean blFabsVisible=false;

    public ScrollingActivityEvents( ScrollingActivity scrollingActivity){
       this.scrollingActivity=scrollingActivity;
    }

    @Override
    public void onClick(View v) {
        if (v.getId()== R.id.fab){
            if (!blFabsVisible){
                scrollingActivity.showFabs();
            }else{
                scrollingActivity.hideFabs();
            }
            blFabsVisible=!blFabsVisible;
        }
    }
}
