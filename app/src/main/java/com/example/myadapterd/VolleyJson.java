package com.example.myadapterd;

import static com.android.volley.Request.Method.GET;
import static com.android.volley.Request.Method.POST;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.PointerIcon;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VolleyJson extends AppCompatActivity {


    String answer, question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_json);

        getData();
    }


    public void getData() {

        StringRequest stringRequest = new StringRequest(GET, "https://commandee.kicklance.com/api/v3/delivery-man/get-faqs", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    JSONObject jsonObject1;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObject1 = jsonArray.getJSONObject(i);
                        question = jsonObject1.getString("question");
                        answer = jsonObject1.getString("answer");
                        Log.e("My Data", "" + question + answer);

                    }


                } catch (JSONException e) {
                    Toast.makeText(VolleyJson.this, "Uas Error", Toast.LENGTH_SHORT).show();
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
                hashMap.put("Authorization", "gW3rHo613h6z3htHas3gHws3LQAsW1Bq8CcuX1yrPXlvh4JPL5An9ZDpESvGyoovJpM37cJ9UkGG4aaR0m5V8rU1fQ0BtQy2AoIm9DwSECA25M3f1xbGoN1A");
                return super.getHeaders();
            }
        };
    }
}