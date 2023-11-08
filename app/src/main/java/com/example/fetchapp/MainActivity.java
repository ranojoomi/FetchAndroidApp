package com.example.fetchapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String response;
    private DataFetch dataFetcher = new DataFetch();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView errorTextView = (TextView) findViewById(R.id.errorTextView);

        Thread myThread = new MyThread();
        myThread.start();

        try {
            myThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (response != null) {
            List<Item> allItems = dataFetcher.convertToJSON(response);
            Map<Integer, List<Item>> ItemsGroupedAndSorted = dataFetcher.groupAndSort(allItems);

            for (Map.Entry<Integer, List<Item>> entry : ItemsGroupedAndSorted.entrySet()) {
                int listId = entry.getKey();
                List<Item> itemsForListId = entry.getValue();

                RecyclerView recyclerView = new RecyclerView(this);
                recyclerView.setHasFixedSize(true);
                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(layoutManager);

                RecyclerView.Adapter adapter = new CustomAdapter(itemsForListId);

            }

            mRecyclerView = findViewById(R.id.recyclerView);
            mRecyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(this);
            mAdapter = new CustomAdapter(allItems);

            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            errorTextView.setVisibility(View.VISIBLE);
        }

    }

    private class MyThread extends Thread {
        @Override
        public void run() {
            response = dataFetcher.getDataFromWeb("https://fetch-hiring.s3.amazonaws.com/hiring.json");
        }
    }
}