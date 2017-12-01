package com.applaudo.testapplaudo;

import android.app.ProgressDialog;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.applaudo.testapplaudo.Adapters.AdapterList;
import com.applaudo.testapplaudo.Models.List;
import com.applaudo.testapplaudo.Models.Team;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    ArrayList<Team> dataset;
    AdapterList adapterList;
    private RelativeLayout mCLayout;
    ArrayList<String> schedule = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_teams);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        mCLayout = (RelativeLayout) findViewById(R.id.myLayout);

        String URL = "http://applaudostudios.com/external/applaudo_homework.json";
        RequestQueue queue = Volley.newRequestQueue(this);
        final ProgressDialog progressDialog = ProgressDialog.show(this, "Please Wait", "We are taking your request");

        JsonArrayRequest request = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response)
                    {

                        dataset = new ArrayList<Team>();
                        dataset = parser(response);
                        adapterList =  new AdapterList(dataset, R.layout.row_team);
                        recyclerView.setAdapter(adapterList);
                        progressDialog.cancel();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError)
            {
                progressDialog.dismiss();
                Snackbar.make(mCLayout, "Algo salió mal :(", Snackbar.LENGTH_LONG)
                                .show();
            }
        });


        queue.add(request);
    }

    private ArrayList<Team> parser(JSONArray response)
    {
        ArrayList<Team> teamAux = new ArrayList<Team>();
        for (int i=0; i<response.length();i++)
        {
            Team p = new Team();
            String texto = "";
            try
            {
                //get team data
                JSONObject jsonObject = (JSONObject) response.get(i);
                p.setID(jsonObject.getString("id"));
                p.setName(jsonObject.getString("team_name")+" -- "+jsonObject.getString("since"));
                p.setCoach(jsonObject.getString("coach"));
                p.setTeam_nickname(jsonObject.getString("team_nickname"));
                p.setStadiun(jsonObject.getString("stadium"));
                p.setImg_logo(jsonObject.getString("img_logo"));
                p.setImg_stadiun(jsonObject.getString("img_stadium"));
                p.setLatitude(jsonObject.getString("latitude"));
                p.setLongitude(jsonObject.getString("longitude"));
                p.setWebsite(jsonObject.getString("website"));
                p.setAdress(jsonObject.getString("address"));
                p.setPhone_number(jsonObject.getString("phone_number"));
                p.setDescription(jsonObject.getString("description"));
                p.setVideo_url(jsonObject.getString("video_url"));

                // get calendar data
                JSONArray dataCalendar = jsonObject.getJSONArray("schedule_games");
                for (int j=0; j<dataCalendar.length(); j++)
                {
                    JSONObject calendObjec = (JSONObject) dataCalendar.get(j);
                    texto += calendObjec.getString("date")+" -- "+calendObjec.getString("stadium");
                    texto += "\n";
                }
                p.setSince(texto);
                teamAux.add(p);

            }
            catch (JSONException e)
            {
                e.printStackTrace();
                Toast.makeText(getApplication(),"Error",Toast.LENGTH_LONG).show();
            }
        }

        return teamAux;
    }
}
