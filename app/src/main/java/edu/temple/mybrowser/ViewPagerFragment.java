package edu.temple.mybrowser;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class ViewPagerFragment extends Fragment {
    private static final String URL_KEY = "url_key";
    private static final String TAG = "ViewPagerFRAGMENT";
    public WebView webView;
    public String currentURL;



    public ViewPagerFragment() {
        // Required empty public constructor
    }


    public static ViewPagerFragment newInstance(String urlString) {
        ViewPagerFragment fragment = new ViewPagerFragment();
        Bundle args = new Bundle();
        args.putString(URL_KEY, urlString);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            if(currentURL == null) {
                currentURL = getArguments().getString(URL_KEY);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_webview, container, false);

        webView = (WebView) v.findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                currentURL = url;
                ((MainActivity)getActivity()).displayURL();
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(currentURL);
        ((MainActivity)getActivity()).displayURL();
        return v;
    }

    //Called when "Go" is clicked from main activity
    public void fragNewUrl(String url) {
        webView.loadUrl(url);
        currentURL = url;
    }

    //Called when "Back" is clicked from main activity
    public void fragBackUrl() {
        webView.goBack();
        currentURL = webView.getUrl();
    }

    //Called when "Next" is clicked from main activity
    public void fragNextUrl() {
        webView.goForward();
        currentURL = webView.getUrl();
    }
}