package anugerah.gmi.com.pemuda;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.coolerfall.download.DownloadCallback;
import com.coolerfall.download.DownloadManager;
import com.coolerfall.download.DownloadRequest;
import com.coolerfall.download.Logger;
import com.coolerfall.download.OkHttpDownloader;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import anugerah.gmi.com.model.LogosViewModel;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.github.barteksc.pdfviewer.scroll.ScrollHandle;
/**
 * Created by christian.simon on 12/11/2016.
 */
public class PdfReaderFragment extends Fragment implements OnPageChangeListener, OnLoadCompleteListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private final static int REQUEST_CODE = 42;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View view;
    private GridView logosGridView;
    private String url;
    private String uriStorage;
    List<LogosViewModel> items = new ArrayList<>();

    FragmentManager fragmentManager = null;
    Logger logger = new Logger() {
        @Override
        public void log(String s) {
            System.out.print(s);
        }
    };

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
    public static PdfReaderFragment newInstance(String param1, String param2) {
        PdfReaderFragment fragment = new PdfReaderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public PdfReaderFragment() {
        // Required empty public constructor
    }

    public void setUrl(String url){
        this.url = url;
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

    public void downloadPDF(){
        try {
            OkHttpClient client = new OkHttpClient.Builder().build();
            DownloadManager downloadManager  =
                    new DownloadManager.Builder().context(getContext())
                            .downloader(OkHttpDownloader.create())
                            .threadPoolSize(2)
                            .logger(logger)
                            .build();
            int urlPartLength = url.split("/").length;
            String destPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) +
                    url.split("/")[urlPartLength - 1];
            this.uriStorage = destPath;
            DownloadRequest request = new DownloadRequest.Builder()
                    .url(this.url)
                    .retryTime(5)
                    .retryInterval(2, TimeUnit.SECONDS)
                    .progressInterval(1, TimeUnit.SECONDS)
                    .priority(com.coolerfall.download.Priority.HIGH)
                    .allowedNetworkTypes(DownloadRequest.NETWORK_WIFI)
                    .destinationFilePath(destPath)
                    .downloadCallback(new DownloadCallback() {
                        @Override
                        public void onStart(int downloadId, long totalBytes) {

                        }

                        @Override
                        public void onRetry(int downloadId) {

                        }

                        @Override
                        public void onProgress(int downloadId, long bytesWritten, long totalBytes) {
                            String sss = String.valueOf(bytesWritten);
                        }

                        @Override
                        public void onSuccess(int downloadId, String filePath) {

                        }

                        @Override
                        public void onFailure(int downloadId, int statusCode, String errMsg) {

                        }
                    })
                    .build();

            int downloadId = downloadManager.add(request);

        }catch(Exception ex){
            String ss = ex.getMessage();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        view = (ViewGroup) inflater.inflate(R.layout.fragment_pdf_reader, container, false);

        downloadPDF();

        PDFView pdfViewer = (PDFView) view.findViewById(R.id.pdfViewer);

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");

        pdfViewer.fromUri(Uri.parse(uriStorage)).defaultPage(10)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(this.getContext()))
                .load();
        //this.getContext().startActivityForResult(intent, REQUEST_CODE);
        //logosGridView.setAdapter(new ImageAdapter(this, items));


        return view;
    }

    Integer pageNumber = 0;

    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        //setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
    }

    @Override
    public void loadComplete(int nbPages) {

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

