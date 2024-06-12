package com.example.whatistheicon;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.firestore.DocumentSnapshot;
        import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
        import java.util.List;

public class page_listview extends AppCompatActivity {

    private ListView listView;
    private ItemAdapter adapter;
    private List<Item> itemList;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_listview);

        listView = findViewById(R.id.listView);
        itemList = new ArrayList<>();
        adapter = new ItemAdapter(this, itemList);
        listView.setAdapter(adapter);

        firestore = FirebaseFirestore.getInstance();

        // Replace 'items' with the actual collection name in your Firestore
        firestore.collection("icon_signs").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                String title = document.getString("description");
                                String imageUrl = document.getString("icon");
                                Item item = new Item(title, imageUrl);
                                itemList.add(item);
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}
