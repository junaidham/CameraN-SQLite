package com.example.junaid.cameral;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.junaid.cameral.db.DatabaseHandler;
import com.example.junaid.cameral.model.ContactPhoto;

public class DisplayActivity extends AppCompatActivity {

    private ImageView imageDetail;
    TextView tvId,tvTitle;

    Button btnDelete;
    String imageId;
    String textNameTitle;
    Bitmap theImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        // find view id
        initView();

        // getting intent data from search and previous screen
        getIntentStringExtra();

        //set the intent data
        setData();

        // onclick
        onClickListner();


    }

    private void onClickListner() {

        /**
         * Deleting records from database
         */
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create DatabaseHandler object
                DatabaseHandler db = new DatabaseHandler(DisplayActivity.this);

                Log.d("Delete Image: ", "Deleting.....");
                db.deleteContact(new ContactPhoto(imageId));
                // /after deleting data go to main page
                Intent i = new Intent(DisplayActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void setData() {

        tvId.setText(imageId);
        tvTitle.setText(textNameTitle);
        imageDetail.setImageBitmap(theImage);
//        imageDetail.setImageBitmap(theImage);
    }

    private void getIntentStringExtra() {
        Intent intent = getIntent();

        imageId = intent.getStringExtra("imageid");
        textNameTitle = intent.getStringExtra("textNameTitle");
        theImage = (Bitmap) intent.getParcelableExtra("imageName");

        Log.d("Image ID:****", String.valueOf(imageId));
    }

    private void initView() {
        imageDetail = findViewById(R.id.imgPhoto);
        tvId = findViewById(R.id.tvId);
        tvTitle = findViewById(R.id.tvTitle);

        btnDelete = findViewById(R.id.btnDelete);
    }
}
