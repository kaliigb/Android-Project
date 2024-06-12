package com.example.whatistheicon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class IconSelectionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private ArrayList<IconSign> signs;
    private SpinnerAdapter adapter;
    private FirebaseServices fbs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icon_selection);

        spinner = findViewById(R.id.spIcons);
        signs = new ArrayList<>();
        fbs = FirebaseServices.getInstance();

        // Set the activity as the listener for spinner item selection
        spinner.setOnItemSelectedListener(this);

        getSigns();
    }

    private void getSigns() {
        fbs.getFire().collection("icon_signs").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot dataSnapshot: queryDocumentSnapshots.getDocuments()){
                    IconSign is = dataSnapshot.toObject(IconSign.class);
                    signs.add(is);
                }
                adapter = new IconSpinnerAdapter(getApplicationContext(), signs);
                spinner.setAdapter(adapter);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Handle failure
            }
        });
    }

    // Implement the spinner item selection callback methods
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        // Get the selected IconSign object from the ArrayList
        IconSign selectedIconSign = signs.get(position);

        // Access the properties of the selected IconSign
        String symbol = selectedIconSign.getDescription();
        String description = selectedIconSign.getDescription();
        String descriptionModified = description.trim().replace(" ", "+");
        // Display the information using a Toast
        if(!(symbol.equals("Select"))){
            String message = "Symbol: " + symbol + "\nDescription: " + descriptionModified;
            String LinkDescriptionIcon ="https://dashboardsymbols.com/?s=";
            LinkDescriptionIcon+=symbol;
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(LinkDescriptionIcon));
            startActivity(intent);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Do nothing when no item is selected
    }
}
