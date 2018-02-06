package com.example.junaid.cameral;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.junaid.cameral.adapter.ContactAdapter;
import com.example.junaid.cameral.db.DatabaseHandler;
import com.example.junaid.cameral.model.ContactPhoto;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class ListDisplay extends AppCompatActivity {
    ListView listView;
    ContactAdapter imageAdapter;
    ArrayList<ContactPhoto> dataList = new ArrayList<ContactPhoto>();
    DatabaseHandler databaseHandler;

    byte[] imageName;
    String textNameTitle;
    String imageId;
    Bitmap theImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_display);

        // find id
        listView = (ListView) findViewById(R.id.list);

        getData();

        getListView();
    }



    private void getData() {
        /**
         * create DatabaseHandler object
         */
        /**
         * create DatabaseHandler object
         */
        databaseHandler = new DatabaseHandler(this);
        /**
         * Reading and getting all records from database
         */
        List<ContactPhoto> contacts = databaseHandler.getAllContacts();
        for (ContactPhoto cn : contacts) {
            String log = "Id:" + cn.getId()
                    + " Name: " + cn.getName()
                    + " ,Image: " + cn.getPhoto();

            // Writing Contacts to log
            Log.d("Result: ", log);
            // add contacts data in arrayList
            dataList.add(cn);

        }
    }



    private void getListView() {
        /**
         * Set Data base Item into listview
         */
        imageAdapter = new ContactAdapter(this, R.layout.row, dataList);
        listView.setAdapter(imageAdapter);




        /**
         * go to next activity for detail image
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                imageId = dataList.get(position).getId();
                textNameTitle =  dataList.get(position).getName();
                imageName = dataList.get(position).getPhoto();

                Log.d("Before Send:****", imageName + "-" + imageId);
                // convert byte to bitmap
                ByteArrayInputStream imageStream = new ByteArrayInputStream(imageName);
                theImage = BitmapFactory.decodeStream(imageStream);

                Intent intent = new Intent(ListDisplay.this, DisplayActivity.class);
                intent.putExtra("imageid", imageId);
                intent.putExtra("imageName", theImage);
                intent.putExtra("textNameTitle", textNameTitle);
                startActivity(intent);

            }
        });
    }


}



