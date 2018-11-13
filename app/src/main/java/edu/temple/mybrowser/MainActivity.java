package edu.temple.mybrowser;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ViewPagerAdapter mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
    public static final String homepage = "http://www.google.com";
    private static final String TAG = "MainActiviy";
    private String http = "http://";
    Button goButton, backButton, nextButton;
    private EditText urlDisplay;
    ViewPager mViewPager;
    private int pageCount = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        urlDisplay = (EditText) findViewById(R.id.URLinput);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setAdapter(mViewPagerAdapter);
        displayURL();
        setupToolbar();
        setupButtons();


        //Navigate to the user entered URL
        View.OnClickListener go_ocl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //urlDisplay = (EditText) findViewById(R.id.URLinput);
                String urlString = urlDisplay.getText().toString();
                if ( !urlString.contains(http) ){
                    urlString = http.concat(urlString);
                }
                mViewPagerAdapter.viewPagerArrayList.get(mViewPager.getCurrentItem()).fragNewUrl(urlString);
                displayURL();
            }
        };

        //Navigate to previous URL
        View.OnClickListener back_ocl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPagerAdapter.viewPagerArrayList.get(mViewPager.getCurrentItem()).fragBackUrl();
                displayURL();
            }
        };

        //Navigate to next URL
        View.OnClickListener next_ocl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPagerAdapter.viewPagerArrayList.get(mViewPager.getCurrentItem()).fragNextUrl();
                displayURL();
            }
        };

        goButton.setOnClickListener(go_ocl);
        backButton.setOnClickListener(back_ocl);
        nextButton.setOnClickListener(next_ocl);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            //Create new fragment for webviewing
            case R.id.action_add:
                mViewPagerAdapter.createFragment();
                mViewPager.setCurrentItem(++pageCount);
                displayURL();
                break;
            // Navigate to previous Web Fragment
            case R.id.action_back:
                mViewPager.setCurrentItem(mViewPager.getCurrentItem()-1);
                displayURL();
                break;
            // Navigate to next Web Fragment
            case R.id.action_next:
                mViewPager.setCurrentItem(mViewPager.getCurrentItem()+1);
                displayURL();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //Inserts  buttons onto the AppBar
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_bar, menu);
        return true;
    }

    //Displays the most recently visited URL of the currently viewed fragment
    public void displayURL(){
        urlDisplay.setText(mViewPagerAdapter.viewPagerArrayList.get(mViewPager.getCurrentItem()).currentURL);
    }

    //Setup toolbar
    public void setupToolbar(){
        Toolbar toolbar;
        toolbar = (Toolbar)findViewById(R.id.my_toolbar);
        toolbar.setTitle("Web Browser Project");
        setSupportActionBar(toolbar);
    }

    //Initialize buttons
    public void setupButtons(){
        goButton = (Button) findViewById(R.id.go);
        nextButton = (Button) findViewById(R.id.next);
        backButton = (Button) findViewById(R.id.back);
    }


}