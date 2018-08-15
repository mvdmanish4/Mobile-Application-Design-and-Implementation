package com.porject.cmu.mobileapplicationdesignandimplementation;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.porject.cmu.mobileapplicationdesignandimplementation.pojo.ShopDetails;

public class StoreDataActivity extends AppCompatActivity {

    private String shopData = "[\n" +
            "{\n" +
            " \"Name\":\"Nike Running\",\n" +
            " \"Phone\":\"+1 650-326-6957\",\n" +
            " \"Email\":\"customersupport@nike.com\",\n" +
            " \"Address\":\"6 Stanford Shopping Center #6-A, Palo Alto, CA 94304, USA\",\n" +
            " \"Details\":\"Chain retailer selling a range of Nike athletic footwear, apparel & accessories\",\n" +
            " \"Website\":\"https://www.nike.com/us/en_us/retail/en/nike-running-stanford\",\n" +
            " \"ImgSrc\":\"nike_back.jpg\",\n" +
            " \"WorkingHours\":\"Mon-Fri: 10AM - 9PM, Sat-Sun : 10AM - 11PM\"\n" +
            "},\n" +
            "{\n" +
            " \"Name\":\"Puma\",\n" +
            " \"Phone\":\"+1 408-262-5700\",\n" +
            " \"Email\":\"customersupport@puma.com\",\n" +
            " \"Address\":\"447 Great Mall Dr #195, Milpitas, CA 95035, USA\",\n" +
            " \"Details\":\"Retail location for the brand's own athletic shoes & apparel, backpacks & other accessories.\",\n" +
            " \"Website\":\"https://us.puma.com/en/us/home\",\n" +
            " \"ImgSrc\":\"puma_back.jpg\",\n" +
            " \"WorkingHours\":\"Mon-Fri: 10AM - 9PM, Sat-Sun : 10AM - 11PM\"\n" +
            "},\n" +
            "{\n" +
            " \"Name\":\"Adidas Outlet Store\",\n" +
            " \"Phone\":\"(408) 946-8519\",\n" +
            " \"Email\":\"customersupport@adidas.com\",\n" +
            " \"Address\":\"556 Great Mall Dr, Milpitas, CA 95035\",\n" +
            " \"Details\":\"Retail location for the brand's own athletic shoes & apparel, backpacks & other accessories.\",\n" +
            " \"Website\":\"https://www.adidas.com/us\",\n" +
            " \"ImgSrc\":\"adidas_back.jpg\",\n" +
            " \"WorkingHours\":\"Mon-Fri: 10AM - 9PM, Sat-Sun : 10AM - 11PM\"\n" +
            "}\n" +
            "]";
    private ShopDetails[] enums;
    private String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_data);

        Intent intent = getIntent();
        value = intent.getStringExtra("shopName");
        ImageView logoImage = findViewById(R.id.header);
        TextView nameText = findViewById(R.id.title);
        TextView phoneText = findViewById(R.id.cameraType);
        TextView emailText = findViewById(R.id.EmailType);
        TextView addressText = findViewById(R.id.AddressType);
        TextView detailsText = findViewById(R.id.description);
        TextView businessHoursText = findViewById(R.id.BusinessHoursType);
        Gson gson = new Gson();
        enums = gson.fromJson(shopData, ShopDetails[].class);
        for (int i = 0; i < enums.length; i++) {
            if (enums[i].getName().equals(value)) {
                nameText.setText(enums[i].getName());
                phoneText.setText(enums[i].getPhone());
                emailText.setText(enums[i].getEmail());
                addressText.setText(enums[i].getAddress());
                detailsText.setText(enums[i].getDetails());
                businessHoursText.setText(enums[i].getWorkingHours());
                if(enums[i].getName().equals("Adidas Outlet Store")) logoImage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.adidas_back));
                else if(enums[i].getName().equals("Puma")) logoImage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.puma_back));
                else logoImage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nike_back));
                break;
            }
        }
    }

    public void openWebsite(View view){
        Intent intent = new Intent(this, WebViewActivity.class);
        for (int i = 0; i < enums.length; i++) {
            if (enums[i].getName().equals(value)) {
                intent.putExtra("webURL",enums[i].getWebsite());
                break;
            }
        }
        startActivity(intent);
    }

    public void openItemDetails(View view) {
        Intent intent = new Intent(this, StoreItemsActivity.class);
        //intent.putExtra("shopName","Nike");
        startActivity(intent);
    }

    public void phoneCall(View view) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        for (int i = 0; i < enums.length; i++) {
            if (enums[i].getName().equals(value)) {
                intent.setData(Uri.parse("tel:"+enums[i].getPhone()));
                break;
            }
        }
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(intent);
    }

    public void sendEmail(View view) {
        Intent i = new Intent(Intent.ACTION_SENDTO);
        i.setType("message/rfc822");
        i.setData(Uri.parse("mailto:"));
        for (int j = 0; j < enums.length; j++) {
            if (enums[j].getName().equals(value)) {
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{enums[j].getEmail()});
                break;
            }
        }
        i.putExtra(Intent.EXTRA_SUBJECT, "Query");
        i.putExtra(Intent.EXTRA_TEXT   , "{Add Body}");
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(StoreDataActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    public void storeVisit(View view){
        Snackbar.make(view, "Store Check-in!", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
