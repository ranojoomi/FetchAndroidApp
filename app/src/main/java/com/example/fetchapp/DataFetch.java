package com.example.fetchapp;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DataFetch {

    public String getDataFromWeb(String link) {
        try {
            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {

                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String lineInput;

                while ((lineInput = br.readLine()) != null) {
                    sb.append(lineInput);
                }
                br.close();
                return sb.toString();
            } else {
                System.out.println("We did not get OK response");
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("We had an error");
            return null;
        }
    }

    public List<Item> convertToJSON(String data) {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<Item>>(){}.getType();
        List<Item> itemList = gson.fromJson(data, collectionType);
        List<Item> filteredItemList = new ArrayList<>();

        for (Item item : itemList) {
            if (item.getName() != null && item.getName().length() > 0) {
                filteredItemList.add(item);
            }
        }

        Collections.sort(filteredItemList, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                if (o1.getItemId() == o2.getItemId()) {
                    return o1.getName().compareTo(o2.getName());
                }
                return o1.getItemId() - o2.getItemId();
            }
        });

        return filteredItemList;
    }

    public Map<Integer, List<Item>> groupAndSort(List<Item> items) {

        Map<Integer, List<Item>> group = new TreeMap<>();

        for (Item item : items) {
            if (group.containsKey(item.getItemId())) {
                group.get(item.getItemId()).add(item);
            } else {
                List<Item> toPut = new ArrayList<>();
                toPut.add(item);
                group.put(item.getItemId(), toPut);
            }
        }

        for (Map.Entry<Integer, List<Item>> entry : group.entrySet()) {
            Collections.sort(entry.getValue(), new Comparator<Item>() {
                @Override
                public int compare(Item o1, Item o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
        }
        return group;
    }




}
