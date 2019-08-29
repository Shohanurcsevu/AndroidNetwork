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
    private  String url="https://reqres.in/api/users";

    private  RecyclerView recyclerView;
    private  LinearLayoutManager linearLayoutManager;
    private List<User> users;
    private RecyclerView.Adapter adapter;

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
        
        getData();


    }

    private void getData() {

        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        JsonArrayRequest jsonArrayRequest =new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i=0;i<response.length();i++)
                {
                    try
                    {


                       JSONObject jsonObject=response.getJSONObject(i);
                        User user=new User();
                        user.setFirstname(jsonObject.getString("first_name"));
                        user.setLastName(jsonObject.getString("last_name"));
                        user.setEmail(jsonObject.getString("email"));
                        user.setImage_url(jsonObject.getString("avatar"));
                        users.add(user);

                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                }
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Dhora khaisi",error.toString());

            }
        });
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

}
