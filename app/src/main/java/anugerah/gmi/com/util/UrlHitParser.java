package anugerah.gmi.com.util;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import anugerah.gmi.com.adapter.NormalViewAdapter;
import anugerah.gmi.com.model.GeneralModel;
import anugerah.gmi.com.model.NormalViewModel;
import anugerah.gmi.com.pemuda.R;

/**
 * Created by christian.simon on 04/11/2016.
 */
public class UrlHitParser {

    private String url = "http://p3mianugerah.org/db_access.php";
    private List<GeneralModel> items ;

/*
    public void parseUrlToList(String url, Context context, int layoutId, List<GeneralModel> models, ){

       this.url = url;
        this.items = models;

        try {



            RequestQueue queue = Volley.newRequestQueue(context);

// Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            //items.add("Response is: " + response.toString());
                            try {
                                final JSONArray objects = new JSONArray(response.toString());
                                for(int i = 0 ; i < objects.length(); i++){
                                    JSONObject obj = objects.getJSONObject(i);
                                    NormalViewModel in = new NormalViewModel(obj);
                                    items.add(in);

                                }
                                listView.setAdapter(new NormalViewAdapter(context, layoutId, items));
                            }catch(Exception ee){
                                //items.add("ERROR in parsing");
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    NormalViewModel mod = new NormalViewModel();
                    mod.setTitle("gagal");items.add(mod);
                }
            });
// Add the request to the RequestQueue.
            queue.add(stringRequest);

        }catch (Exception ee){
            //System.out.println(ee.getMessage());
            //String eas = ee.getMessage();
            //System.out.println(eas);
            // mess ="Tidak ada koneksi internet";// ee.getMessage().substring(0,200);
            NormalViewModel mod = new NormalViewModel();
            mod.setTitle("gagal");
            items.add(mod);
        }
    }*/
}
