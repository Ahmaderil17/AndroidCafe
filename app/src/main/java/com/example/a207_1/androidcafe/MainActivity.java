package com.example.a207_1.androidcafe;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements FragmentCommunicator {

    private boolean netOk;

    private boolean isDualPane = false;
    private FragmentDetail fragmentDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // just info tentang network
        netOk = NetworkHelper.hasNetworkAccess(this);
        Toast.makeText(this, "Network OK: " + netOk, Toast.LENGTH_LONG).show();

        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentDetail = (FragmentDetail) fragmentManager.findFragmentById(R.id.fragmentD);

        View fragmenDView = findViewById(R.id.fragmentD);
        isDualPane = fragmenDView != null && fragmenDView.getVisibility() == View.VISIBLE;
    }

    @Override
    public void displayDetail(Menu menu) {

        if (isDualPane) { //Tablet
            fragmentDetail.displayData(menu);

        } else { // Smarthphone
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("menu", menu);

            startActivity(intent);
        }
    }
}
