package com.applaudo.testapplaudo;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.applaudo.testapplaudo.Adapters.AdapterList;
import com.applaudo.testapplaudo.Models.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    ArrayList<List> dataset;
    AdapterList adapterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_teams);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        String URL = "http://applaudostudios.com/external/applaudo_homework.json";
        RequestQueue queue = Volley.newRequestQueue(this);
        final ProgressDialog progressDialog = ProgressDialog.show(this, "Please Wait", "We are taking your request");

        JsonArrayRequest request = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response)
                    {

                        dataset = new ArrayList<List>();
                        dataset = parser(response);
                        adapterList =  new AdapterList(dataset, R.layout.row_team);
                        recyclerView.setAdapter(adapterList);
                        progressDialog.cancel();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError)
            {

            }
        });


        queue.add(request);
    }

    private ArrayList<List> parser(JSONArray response)
    {
        ArrayList<List> teamAux = new ArrayList<List>();
        for (int i=0; i<response.length();i++)
        {
            List p = new List();
            try
            {
                JSONObject jsonObject = (JSONObject) response.get(i);
                p.setId(jsonObject.getString("id"));
                p.setTeam_name(jsonObject.getString("team_name"));
                p.setTeam_logo(jsonObject.getString("img_logo"));
                p.setAddress(jsonObject.getString("address"));
                teamAux.add(p);

            }
            catch (JSONException e)
            {
                e.printStackTrace();
                Toast.makeText(getApplication(),"Refresh",Toast.LENGTH_LONG).show();
            }
        }

        return teamAux;
    }
}
