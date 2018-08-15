package com.porject.cmu.mobileapplicationdesignandimplementation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.porject.cmu.mobileapplicationdesignandimplementation.cardUtil.CardModel;
import com.porject.cmu.mobileapplicationdesignandimplementation.cardUtil.CardsAdapter;

import org.json.JSONException;
import org.json.JSONObject;

public class StoreItemsActivity extends AppCompatActivity implements View.OnClickListener {

    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_items);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        })*/;
        qrScan = new IntentIntegrator(this);
        fab.setOnClickListener(this);

        ListView lvCards = (ListView) findViewById(R.id.list_cards);
        CardsAdapter adapter = new CardsAdapter(this);
        lvCards.setAdapter(adapter);
        adapter.addAll(new CardModel(R.drawable.nike_shirt, R.string.shirt1, R.string.shirt1_sub),
                new CardModel(R.drawable.nike_shirt1, R.string.shirt2, R.string.shirt2_sub),
                new CardModel(R.drawable.nike_shirt2, R.string.shirt3, R.string.shirt3_sub),
                new CardModel(R.drawable.nike_shirt3, R.string.shirt4, R.string.shirt4_sub),
                new CardModel(R.drawable.nike_pant4, R.string.pants5, R.string.pants5_sub),
                new CardModel(R.drawable.nike_cap5, R.string.cap6, R.string.cap6_sub));

    }

    @Override
    public void onClick(View view) {
        qrScan.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                try {
                    Intent intent = new Intent(this, ItemDataActivity.class);
                    intent.putExtra("itemName",result.getContents().toString());
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
