package com.example.to_do_list;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity3 extends AppCompatActivity {

    private Button btn01;
            TextView textView1, textView2, textView3;
            ImageView imageView;
            String line1, line2, timeDate;
            Intent intent;
            ExampleItem exampleItem;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        this.setTitle("Просмотр задач");
        btn01 = findViewById(R.id.btn01);
        startButton();

        intent = getIntent();
        exampleItem = intent.getParcelableExtra("Example Item");

        line1 = exampleItem.getLine1();
        line2 = exampleItem.getLine2();
        timeDate = exampleItem.getTimeDate();

        imageView = findViewById(R.id.imageView);

            try {
                String imageRes = exampleItem.getImageResource();
                imageView.setImageURI(Uri.parse(imageRes));
            } catch (Exception ignored) {

            }

        textView1 = findViewById(R.id.textView_name);
        textView1.setText(line1);

        textView2 = findViewById(R.id.textView_text);
        textView2.setText(line2);

        textView3 = findViewById(R.id.timeDate);
        textView3.setText(timeDate);

    }

    private void startButton() {
        btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackActivity();
            }

            private void BackActivity() {
                Intent intent = new Intent(MainActivity3.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}