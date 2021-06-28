package com.example.to_do_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity3 extends AppCompatActivity {

    Button btn01;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        btn01 = findViewById(R.id.btn01);
        startButton();

        Intent intent = getIntent();
        ExampleItem exampleItem = intent.getParcelableExtra("Example Item");

        String imageRes = exampleItem.getImageResource();
        String line1 = exampleItem.getLine1();
        String line2 = exampleItem.getLine2();

//            ImageView imageView = findViewById(R.id.imageView);
//            imageView.setImageResource(getImageId(this,imageRes));

        TextView textView1 = findViewById(R.id.textView_name);
        textView1.setText(line1);

        TextView textView2 = findViewById(R.id.textView_text);
        textView2.setText(line2);


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

    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }

}