package anugerah.gmi.com.pemuda;

import android.animation.ValueAnimator;
import android.app.VoiceInteractor;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.ksoichiro.android.observablescrollview.ObservableListView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.nineoldandroids.view.ViewHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.android.volley.toolbox.JsonObjectRequest;
import com.onesignal.OneSignal;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import anugerah.gmi.com.adapter.NormalViewAdapter;
import anugerah.gmi.com.model.NormalViewModel;


/*
public class MainActivity extends HomeActivity<ObservableScrollView> implements ObservableScrollViewCallbacks {


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected ObservableScrollView createScrollable() {
        ObservableScrollView scrollView = (ObservableScrollView) findViewById(R.id.scroll);
        scrollView.setScrollViewCallbacks(this);
        return scrollView;
    }
}*/
public class MainActivity extends FragmentActivity implements
        HomeFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OneSignal.startInit(this).init();
        setContentView(R.layout.activity_main);

        createBarActions();

        if (findViewById(R.id.homeFragment) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create an instance of ExampleFragment
            HomeFragment homeFragment = new HomeFragment();

            // In case this activity was started with special instructions from an Intent,
            // pass the Intent's extras to the fragment as arguments
            homeFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.homeFragment, homeFragment).commit();
        }


    }

private void createBarActions(){
    BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
    bottomBar.setOnTabSelectListener(
            new OnTabSelectListener() {
                @Override
                public void onTabSelected(@IdRes int tabId) {
                    if (tabId == R.id.tab_logos) {
                        // The tab with id R.id.tab_favorites was selected,
                        // change your content accordingly.
                        LogosFragment logosFragment = new LogosFragment();

                        // In case this activity was started with special instructions from an Intent,
                        // pass the Intent's extras to the fragment as arguments
                        logosFragment.setArguments(getIntent().getExtras());
                        logosFragment.setFragmentManager(getSupportFragmentManager());
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.homeFragment, logosFragment).commit();

                    }else if(tabId == R.id.tab_general){
                        HomeFragment homeFragment = new HomeFragment();

                        // In case this activity was started with special instructions from an Intent,
                        // pass the Intent's extras to the fragment as arguments
                        homeFragment.setArguments(getIntent().getExtras());
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.homeFragment, homeFragment).commit();

                    }
                    else if(tabId == R.id.tab_about){
                        AboutFragment aboutFragment = new AboutFragment();

                        // In case this activity was started with special instructions from an Intent,
                        // pass the Intent's extras to the fragment as arguments
                        aboutFragment.setArguments(getIntent().getExtras());
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.homeFragment, aboutFragment).commit();

                    }
                }

            });
}

    public void onFragmentSelected(int position) {

    }




 /*   @Override
    protected ObservableListView createScrollable() {
        ObservableListView listView = (ObservableListView) findViewById(R.id.scroll);
        listView.setScrollViewCallbacks(this);
        setDummyDataWithHeader(listView, mFlexibleSpaceImageHeight);
        return listView;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void updateViews(int scrollY, boolean animated) {
        super.updateViews(scrollY, animated);

        // Translate list background
        ViewHelper.setTranslationY(mListBackgroundView, ViewHelper.getTranslationY(mHeader));
    }*/




}
