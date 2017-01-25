package anugerah.gmi.com.pemuda;

import android.app.Activity;
import android.app.DownloadManager;
import android.net.Uri;
import android.os.Bundle;

import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.OkHttpDownloader;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import anugerah.gmi.com.adapter.NormalViewAdapter;
import anugerah.gmi.com.model.LogosViewModel;
import anugerah.gmi.com.model.NormalViewModel;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LogosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LogosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LogosFragment extends Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View view;
    private GridView logosGridView;

    List<LogosViewModel> items = new ArrayList<>();

    FragmentManager fragmentManager = null;

    //private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LogosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LogosFragment newInstance(String param1, String param2) {
        LogosFragment fragment = new LogosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public LogosFragment() {
        // Required empty public constructor
    }

    public void setFragmentManager(FragmentManager fragmentManager){
        this.fragmentManager = fragmentManager;
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        view = (ViewGroup) inflater.inflate(R.layout.fragment_logos_block, container, false);

        getAllDataMagazine();

            GridView gridview = (GridView) view.findViewById(R.id.gridview);
            this.logosGridView = gridview;
            //logosGridView.setAdapter(new ImageAdapter(this, items));

            logosGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {
                    PdfReaderFragment pdfViewFragment = new PdfReaderFragment();

                    // In case this activity was started with special instructions from an Intent,
                    // pass the Intent's extras to the fragment as arguments
                    pdfViewFragment.setArguments(getArguments());
                    pdfViewFragment.setUrl(items.get(position).getContentUrl());
                    //pdfViewFragment.downloadPDF();
                    fragmentManager.beginTransaction()
                            .replace(R.id.homeFragment, pdfViewFragment).commit();
                }
            });



        return view;
    }


    public void getAllDataMagazine(){
        try {

            String url = "http://p3mianugerah.org/logos_access.php";

            RequestQueue queue = Volley.newRequestQueue(getContext());

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
                                    LogosViewModel in = new LogosViewModel(obj);
                                    items.add(in);

                                }
                                logosGridView.setAdapter(new ImageAdapter(LogosFragment.this, items));
                                //logosGridView.setAdapter(new NormalViewAdapter(getContext(), R.layout.activity_normal_list, items));
                            }catch(Exception ee){
                                //items.add("ERROR in parsing");
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    LogosViewModel mod = new LogosViewModel();
                    mod.setName("gagal");
                    items.add(mod);
                }
            });
// Add the request to the RequestQueue.
            queue.add(stringRequest);

        }catch (Exception ee){
            //System.out.println(ee.getMessage());
            //String eas = ee.getMessage();
            //System.out.println(eas);
            // mess ="Tidak ada koneksi internet";// ee.getMessage().substring(0,200);
            LogosViewModel mod = new LogosViewModel();
            mod.setName("gagal");
            items.add(mod);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
