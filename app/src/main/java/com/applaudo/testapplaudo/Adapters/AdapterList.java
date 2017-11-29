package com.applaudo.testapplaudo.Adapters;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.applaudo.testapplaudo.Detail;
import com.applaudo.testapplaudo.Models.Team;
import com.applaudo.testapplaudo.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jose.lopez04 on 23/11/2017.
 */

public class AdapterList extends RecyclerView.Adapter<AdapterList.ViewHolder> {

    private final ArrayList<Team> lists;
    private final int itemLayout;

    public AdapterList(ArrayList<Team> lists, int itemLayout) {
        this.lists = lists;
        this.itemLayout = itemLayout;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView team_id;
        private TextView team_name;
        private TextView adress;
        private ImageView img_logo;

        public ViewHolder(final View itemView) {
            super(itemView);

            team_id = itemView.findViewById(R.id.team_id);
            team_name = itemView.findViewById(R.id.team_name);
            adress = itemView.findViewById(R.id.adress);
            img_logo = itemView.findViewById(R.id.img_logo);

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v)
                {

                    Intent intent = new Intent(v.getContext(),Detail.class);
                    Team myTeam = new Team();

                    intent.putExtra("myteam", myTeam);
                    v.getContext().startActivity(intent);
                }
            });*/
        }
    }

    @Override
    public AdapterList.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(itemLayout, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final AdapterList.ViewHolder viewHolder, final int i) {

        final Team list = lists.get(i);
            viewHolder.team_id.setText(list.getID());
            viewHolder.team_name.setText(list.getName());
            viewHolder.adress.setText(list.getAdress());

        Picasso
                .with(viewHolder.img_logo.getContext())
                .load(list.getImg_logo())
                .error(R.drawable.image_found)
                .into(viewHolder.img_logo);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                Intent intent = new Intent(view.getContext(),Detail.class);
                Team myTeam = new Team();
                myTeam.setVideo_url(list.getVideo_url());
                myTeam.setImg_logo(list.getImg_logo());
                myTeam.setName(list.getName());
                myTeam.setDescription(list.getDescription());
                myTeam.setSince(list.getSince());
                myTeam.setCoach(list.getCoach());
                myTeam.setStadiun(list.getStadiun());
                myTeam.setPhone_number(list.getPhone_number());
                myTeam.setTeam_nickname(list.getTeam_nickname());
                myTeam.setWebsite(list.getWebsite());
                myTeam.setAdress(list.getAdress());
                myTeam.setLatitude(list.getLatitude());
                myTeam.setLongitude(list.getLongitude());
                myTeam.setImg_stadiun(list.getImg_stadiun());

                intent.putExtra("myteam", myTeam);
                view.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }


}
