package com.example.to_do_list;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity2 extends AppCompatActivity {
    int SELECT_IMAGE_CODE = 1;
    Button btn_back, buttonInsert, btn_clear;
    ImageView up_image;
    EditText line1,line2;
    private  String url_address;
    private String timeDate;




    ArrayList<ExampleItem> mExampleList;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        this.setTitle("Добавить задачу");



        setInsertButton();
        loadData();
        buildRecyclerView();
        Btn_back();
        ImageAll();
        getDate();

    }

    private void getDate() {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        timeDate = (dateFormat.format(date));
    }


    private void Btn_back() {
        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }


    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<ExampleItem>>() {}.getType();
        mExampleList = gson.fromJson(json, type);
        if (mExampleList == null) {
            mExampleList = new ArrayList<>();
        }

    }


    private void buildRecyclerView() {
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(mExampleList);
    }



    private void setInsertButton() {
        buttonInsert = findViewById(R.id.button_insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                line1 = findViewById(R.id.edittext_line_1);
                line2 = findViewById(R.id.edittext_line_2);
                insertItem(url_address, line1.getText().toString(), line2.getText().toString(),timeDate);



            }
        });
    }

    private void insertItem(String url_address, String line1, String line2, String timeDate ) {
        mExampleList.add(new ExampleItem(url_address, line1, line2, timeDate));
        mAdapter.notifyItemInserted(mExampleList.size());
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mExampleList);
        editor.putString("task list", json);
        editor.apply();
    }






    //--------------------------------------------------------------------//


    private void ImageAll() {
        up_image = findViewById(R.id.up_image);
        up_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Title"), SELECT_IMAGE_CODE);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECT_IMAGE_CODE && resultCode == RESULT_OK && data != null) {

            Uri imageUri = data.getData();

            try {
                Bitmap uri = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);

                up_image.setImageBitmap(uri);

                url_address = imageUri.toString();


            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }
}