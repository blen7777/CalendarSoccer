
         package com.applaudo.testapplaudo;

         import android.content.Context;
         import android.content.Intent;
         import android.content.SharedPreferences;
         import android.net.Uri;
         import android.os.Vibrator;
         import android.support.v4.view.MenuItemCompat;
         import android.support.v7.app.AppCompatActivity;
         import android.support.v7.widget.ShareActionProvider;
         import android.os.Bundle;
         import android.support.v7.widget.Toolbar;
         import android.util.Log;
         import android.view.Menu;
         import android.view.MenuItem;
         import android.view.View;
         import android.widget.ImageView;
         import android.widget.MediaController;
         import android.widget.TextView;
         import android.widget.VideoView;

         import com.applaudo.testapplaudo.Models.Team;
         import com.google.android.gms.maps.CameraUpdate;
         import com.google.android.gms.maps.CameraUpdateFactory;
         import com.google.android.gms.maps.GoogleMap;
         import com.google.android.gms.maps.MapFragment;
         import com.google.android.gms.maps.OnMapReadyCallback;
         import com.google.android.gms.maps.model.LatLng;
         import com.google.android.gms.maps.model.Marker;
         import com.google.android.gms.maps.model.MarkerOptions;
         import com.squareup.picasso.Picasso;

public class Detail extends AppCompatActivity implements OnMapReadyCallback{

    TextView myName;
    VideoView myVideo;
    ImageView myLogo;
    TextView myDescription;
    TextView myCoach;
    TextView myStadiun;
    TextView myPhone;
    TextView myNickname;
    TextView myWebSite;
    TextView myAddress;
    ImageView myStadiunImg;
    GoogleMap mapa;
    double lat ;
    double lng ;
    String calendar;
    String teamName;
    Marker marcador;
    ShareActionProvider mShareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_left_row);


        //---------Instancia de los campos  en Layout
        myName = (TextView) findViewById(R.id.name_since);
        myDescription = (TextView) findViewById(R.id.description);
        myCoach = (TextView) findViewById(R.id.coach);
        myStadiun = (TextView) findViewById(R.id.stadiun);
        myPhone = (TextView) findViewById(R.id.phone);
        myNickname = (TextView) findViewById(R.id.nickname);
        myWebSite = (TextView) findViewById(R.id.website);
        myAddress = (TextView) findViewById(R.id.adress);

        //--- Map------------------------------------
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.myMap);
        mapFragment.getMapAsync(this);
        //--------------END-----------------------

        //-------ImageView-----------------------------
        myStadiunImg = (ImageView) findViewById(R.id.img_stadiun);
        myLogo = (ImageView) findViewById(R.id.img_logo);
        //-----------END-------------------------------

        //-------VideoVew-----------------------
        myVideo = (VideoView) findViewById(R.id.video_url);
        //--------------END---------------------------

        Team team = getIntent().getParcelableExtra("myteam");

        //-- set de valor a los campos  de la vista
        myName.setText(team.getName());
        myDescription.setText(team.getDescription());
        myCoach.setText("Coach: "+team.getCoach());
        myStadiun.setText("Stadiun: "+team.getStadiun());
        myPhone.setText("Phone: "+team.getPhone_number());
        myNickname.setText("Nickname: "+team.getTeam_nickname());
        myWebSite.setText("WebSite: "+team.getWebsite());
        myAddress.setText("Address: "+team.getAdress());

        teamName = team.getName();
        lat = Double.parseDouble(team.getLatitude());
        lng = Double.parseDouble(team.getLongitude());
        calendar = team.getSince();

        //  Set Images
        Picasso
                .with(myLogo.getContext())
                .load(team.getImg_logo())
                .error(R.drawable.image_found)
                .into(myLogo);

        Picasso
                .with(myStadiunImg.getContext())
                .load(team.getImg_stadiun())
                .error(R.drawable.image_found)
                .into(myStadiunImg);

        // Set videoView
        Uri uri = Uri.parse(team.getVideo_url());
        myVideo.setMediaController((new MediaController(this)));
        myVideo.setVideoURI(uri);
        myVideo.requestFocus();
        myVideo.stopPlayback();

    }

    @Override
    public void onMapReady(GoogleMap map)
    {
        mapa = map;
        agregarMarcador(lat, lng, teamName);
    }

    private void agregarMarcador(final double latitude,final double longitude, String name)
    {
        LatLng coordenadas = new LatLng(latitude, longitude);
        CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, 16);
        marcador = mapa.addMarker(new MarkerOptions()
                .position(coordenadas)
                .snippet(name)
                .title(name));
        mapa.animateCamera(miUbicacion);
    }

    public void playVideo(View view)
    {
        if (myVideo.isPlaying())
        {
            myVideo.stopPlayback();
        }
        else
        {
            myVideo.start();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.action_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.action_share);
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        shareSchedule(calendar);
        return true;
    }

    private void shareSchedule(String schedule)
    {
        Intent  intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,schedule);
        mShareActionProvider.setShareIntent(intent);
    }
}
