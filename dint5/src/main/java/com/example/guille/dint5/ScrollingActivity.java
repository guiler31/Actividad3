package com.example.guille.dint5;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class ScrollingActivity extends AppCompatActivity {

    ScrollingActivityEvents events;
    FloatingActionButton fab,fab1,fab2,fab3;
    Animation show_fab1,hide_fab1,show_fab2,hide_fab2,show_fab3,hide_fab3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        events=new ScrollingActivityEvents(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(events);

        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab1.setOnClickListener(events);

        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(events);

        fab3 = (FloatingActionButton) findViewById(R.id.fab3);
        fab3.setOnClickListener(events);

        show_fab1 = AnimationUtils.loadAnimation(this,R.anim.fab1_show);
        hide_fab1 = AnimationUtils.loadAnimation(this,R.anim.fab1_hide);

        show_fab2 = AnimationUtils.loadAnimation(this,R.anim.fab2_show);
        hide_fab2 = AnimationUtils.loadAnimation(this,R.anim.fab2_hide);

        show_fab3 = AnimationUtils.loadAnimation(this,R.anim.fab3_show);
        hide_fab3 = AnimationUtils.loadAnimation(this,R.anim.fab3_hide);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showFabs(){


        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams)fab1.getLayoutParams();
        layoutParams.rightMargin+=(int)(fab1.getWidth()*1.8);
        fab1.setLayoutParams(layoutParams);
        fab1.startAnimation(show_fab1);
        fab1.setClickable(true);

        CoordinatorLayout.LayoutParams layoutParams2 = (CoordinatorLayout.LayoutParams)fab2.getLayoutParams();
        layoutParams2.rightMargin+=(int)(fab2.getWidth()*3.2);
        fab2.setLayoutParams(layoutParams2);
        fab2.startAnimation(show_fab2);
        fab2.setClickable(true);

        CoordinatorLayout.LayoutParams layoutParams3 = (CoordinatorLayout.LayoutParams)fab3.getLayoutParams();
        layoutParams3.rightMargin+=(int)(fab3.getWidth()*4.6);
        fab3.setLayoutParams(layoutParams3);
        fab3.startAnimation(show_fab3);
        fab3.setClickable(true);
    }

    public void hideFabs(){
        //Log.v("scrollingActivity", "hideFabs");

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams)fab1.getLayoutParams();
        layoutParams.rightMargin-=(int)(fab1.getWidth()*1.8);
        fab1.setLayoutParams(layoutParams);
        fab1.startAnimation(hide_fab1);
        fab1.setClickable(false);

        CoordinatorLayout.LayoutParams layoutParams2 = (CoordinatorLayout.LayoutParams)fab2.getLayoutParams();
        layoutParams2.rightMargin-=(int)(fab2.getWidth()*3.2);
        fab2.setLayoutParams(layoutParams2);
        fab2.startAnimation(hide_fab2);
        fab2.setClickable(false);

        CoordinatorLayout.LayoutParams layoutParams3 = (CoordinatorLayout.LayoutParams)fab3.getLayoutParams();
        layoutParams3.rightMargin-=(int)(fab3.getWidth()*4.6);
        fab3.setLayoutParams(layoutParams3);
        fab3.startAnimation(hide_fab3);
        fab3.setClickable(false);
    }
}

