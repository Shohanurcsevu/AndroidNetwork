package com.example.volley.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.volley.R;
import com.example.volley.adapters.UserAdapter;
import com.example.volley.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private  RecyclerView recyclerView;
    private  LinearLayoutManager linearLayoutManager;
    private List<User> users;
    private RecyclerView.Adapter adapter;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);
        users=new ArrayList<>();
        adapter=new UserAdapter(getApplicationContext(),users);
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        requestQueue=Volley.newRequestQueue(this);


        parseJSON();


    }

    private void parseJSON() {
        String url="https://reqres.in/api/users?page=2";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);

                                User user=new User();
                                user.setFirstname(hit.getString("first_name"));
                                user.setLastName(hit.getString("last_name"));
                                user.setEmail(hit.getString("email"));
                                user.setImage_url(hit.getString("avatar"));
                                users.add(user);
                            }

                            adapter = new UserAdapter(MainActivity.this, users);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Dhora khaisi",error.toString());
            }
        });

        requestQueue.add(request);
    }

}
