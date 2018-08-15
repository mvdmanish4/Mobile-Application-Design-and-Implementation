package com.porject.cmu.mobileapplicationdesignandimplementation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.porject.cmu.mobileapplicationdesignandimplementation.pojo.ItemDetails;
import com.porject.cmu.mobileapplicationdesignandimplementation.pojo.ShopDetails;

public class ItemDataActivity extends AppCompatActivity {

    private String ItemData = "[\n" +
            "{\n" +
            " \"Name\":\"Nike Cotton Polo\",\n" +
            " \"Price\":\"$34\",\n" +
            " \"Sizes\":\"M,XL and XXL\",\n" +
            " \"ColorMaterial\":\"Black & Cotton\",\n" +
            " \"Discount\":\"Buy 1, Get 1\"\n" +
            "},\n" +
            "{\n" +
            " \"Name\":\"Casual T-Shirt\",\n" +
            " \"Price\":\"$45\",\n" +
            " \"Sizes\":\"XL and XXL\",\n" +
            " \"ColorMaterial\":\"Grey & 100% Cotton\",\n" +
            " \"Discount\":\"Upto 30% off\"\n" +
            "}\n" +
            "]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String value = intent.getStringExtra("itemName");

        ImageView logoImage = findViewById(R.id.header);
        TextView nameText = findViewById(R.id.title);
        TextView prizeText = findViewById(R.id.cameraType);
        TextView sizeText = findViewById(R.id.EmailType);
        TextView colorMaterialText = findViewById(R.id.AddressType);
        TextView discountText = findViewById(R.id.BusinessHoursType);

        Gson gson = new Gson();
        ItemDetails[] enums = gson.fromJson(ItemData, ItemDetails[].class);
        for (int i = 0; i < enums.length; i++) {
            if (enums[i].getName().equals(value)) {
                nameText.setText(enums[i].getName());
                prizeText.setText(enums[i].getPrice());
                sizeText.setText(enums[i].getSizes());
                colorMaterialText.setText(enums[i].getColorMaterial());
                discountText.setText(enums[i].getDiscount());
                if (enums[i].getName().equals("Nike Cotton Polo")) logoImage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nike_shirt2));
                else logoImage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nike_shirt1));
            }
        }

    }

    public void openItemDetails(View view){
        Intent intent = new Intent(ItemDataActivity.this, WebViewActivity.class);
        intent.putExtra("webURL","https://paytm.com/paytmwallet");
        startActivity(intent);
    }

}
