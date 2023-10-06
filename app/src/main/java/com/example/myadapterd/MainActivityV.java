package com.example.myadapterd;


import static com.android.volley.Request.Method.GET;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivityV extends AppCompatActivity {


    MyAdapter myAdapter;
    Student student;
    ArrayList<Student> arrayList = new ArrayList<>();

    RecyclerView recyclerView;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_v);
        requestQueue = Volley.newRequestQueue(this);

        recyclerView = findViewById(R.id.recyclerView);

        GridLayoutManager linearLayoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(linearLayoutManager);
        getData();
        //Main

    }


    public void getData() {
        StringRequest stringRequest = new StringRequest(GET, "https://commandee.kicklance.com/api/v3/delivery-man/get-faqs", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String name = jsonObject1.getString("question");
                        Log.e("Name", name);
                        student = new Student(name);
                        arrayList.add(student);
                        myAdapter = new MyAdapter(arrayList);
                        recyclerView.setAdapter(myAdapter);

                    }


                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("Authorization", "Bearer gW3rHo613h6z3htHas3gHws3LQAsW1Bq8CcuX1yrPXlvh4JPL5An9ZDpESvGyoovJpM37cJ9UkGG4aaR0m5V8rU1fQ0BtQy2AoIm9DwSECA25M3f1xbGoN1A");

                return hashMap;
            }
        };

        requestQueue.add(stringRequest);
    }

}