package com.fubaisum.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.fubaisum.sample.model.ComplexItem;
import com.fubaisum.sample.model.ContentItem;
import com.fubaisum.sample.model.ImageItem;
import com.fubaisum.sample.model.Item;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        List<Item> items = new ArrayList<>();
        items.add(new ContentItem());
        items.add(new ImageItem());
        items.add(new ComplexItem());
        items.add(new ComplexItem());
        items.add(new ImageItem());
        items.add(new ContentItem());

        mainAdapter = new MainAdapter(this, items);
        recyclerView.setAdapter(mainAdapter);
    }

}
