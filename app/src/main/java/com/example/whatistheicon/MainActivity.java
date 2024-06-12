package com.example.whatistheicon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnTakePicture;
    private Button btnSelectIcon, btnAddIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTakePicture = findViewById(R.id.btnTakePicture);
        btnSelectIcon = findViewById(R.id.btnSelectIcon);
        btnAddIcon = findViewById(R.id.btnAddIconMainActivity);

        btnSelectIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoSelectIcon();
            }
        });
//        btnAddIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                gotoAddIcon();
//            }
//        });
    }

//    private void gotoAddIcon() {
//        Intent intent = new Intent(MainActivity.this, AddIconSignActivity.class);
//        startActivity(intent);
//    }

    public void takePicture() {
        // Add your code for taking a picture here
        Toast.makeText(this, "Taking a picture...", Toast.LENGTH_SHORT).show();
    }

    public void gotoSelectIcon() {
        Intent intent = new Intent(MainActivity.this, IconSelectionActivity.class);
        startActivity(intent);
    }
}