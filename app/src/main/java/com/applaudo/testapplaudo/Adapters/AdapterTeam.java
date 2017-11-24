package com.applaudo.testapplaudo.Adapters;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.applaudo.testapplaudo.Models.Team;
import com.applaudo.testapplaudo.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by jose.lopez04 on 23/11/2017.
 */

public class AdapterTeam extends RecyclerView.Adapter<AdapterTeam.ViewHolder> {

    private final ArrayList<Team> teams;
    private final int itemLayout;

    public AdapterTeam(ArrayList<Team> teams, int itemLayout) {
        this.teams = teams;
        this.itemLayout = itemLayout;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private VideoView video_url;
        private ImageView img_logo;
        private ImageView img_stadiun;
        private TextView name_since;
        private TextView description;
        private TextView nickname;
        private TextView phone;
        private TextView coach;
        private TextView web;
        private TextView adress;
        private TextView stadiun;

        public ViewHolder(View itemView) {
            super(itemView);

            video_url =  itemView.findViewById(R.id.video_url);
            img_logo = itemView.findViewById(R.id.img_logo);
            img_stadiun = itemView.findViewById(R.id.img_stadiun);
            name_since = itemView.findViewById(R.id.name_since);
            description = itemView.findViewById(R.id.description);
            nickname = itemView.findViewById(R.id.nickname);
            phone = itemView.findViewById(R.id.phone);
            coach = itemView.findViewById(R.id.coach);
            web = itemView.findViewById(R.id.website);
            adress = itemView.findViewById(R.id.adress);
            stadiun = itemView.findViewById(R.id.stadiun);

        }
    }

    @Override
    public AdapterTeam.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(itemLayout, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterTeam.ViewHolder viewHolder, int i) {

        Team team = teams.get(i);
            viewHolder.video_url.setVideoURI(Uri.parse(team.getVideo_url()));
            viewHolder.name_since.setText(team.getName()+" "+team.getSince());
            viewHolder.description.setText(team.getDescription());
            viewHolder.nickname.setText(team.getTeam_nickname());
            viewHolder.phone.setText(team.getPhone_number());
            viewHolder.coach.setText(team.getCoach());
            viewHolder.web.setText(team.getWebsite());
            viewHolder.adress.setText(team.getAdress());
            viewHolder.stadiun.setText(team.getStadiun());
        Picasso
                .with(viewHolder.img_logo.getContext())
                .load(team.getImg_logo())
                .error(R.drawable.image_found)
                .into(viewHolder.img_logo);

        Picasso
                .with(viewHolder.img_stadiun.getContext())
                .load(team.getImg_stadiun())
                .error(R.drawable.image_found)
                .into(viewHolder.img_stadiun);

    }

    @Override
    public int getItemCount() {
        return teams.size();
    }


}
