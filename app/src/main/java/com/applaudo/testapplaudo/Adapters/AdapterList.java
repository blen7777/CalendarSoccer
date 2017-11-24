package com.applaudo.testapplaudo.Adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.applaudo.testapplaudo.Detail;
import com.applaudo.testapplaudo.Models.List;
import com.applaudo.testapplaudo.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by jose.lopez04 on 23/11/2017.
 */

public class AdapterList extends RecyclerView.Adapter<AdapterList.ViewHolder> {

    private final ArrayList<List> lists;
    private final int itemLayout;

    public AdapterList(ArrayList<List> lists, int itemLayout) {
        this.lists = lists;
        this.itemLayout = itemLayout;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView team_id;
        private TextView team_name;
        private TextView adress;
        private ImageView img_logo;

        public ViewHolder(View itemView) {
            super(itemView);

            team_id = itemView.findViewById(R.id.team_id);
            team_name = itemView.findViewById(R.id.team_name);
            adress = itemView.findViewById(R.id.adress);
            img_logo = itemView.findViewById(R.id.img_logo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v)
                {
                    Intent link = new Intent(v.getContext(), Detail.class);
                    link.putExtra("datos", "Test");
                    v.getContext().startActivity(link);
                }
            });
        }
    }

    @Override
    public AdapterList.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(itemLayout, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterList.ViewHolder viewHolder, int i) {

        List list = lists.get(i);
            viewHolder.team_id.setText(list.getId());
            viewHolder.team_name.setText(list.getTeam_name());
            viewHolder.adress.setText(list.getAddress());

        Picasso
                .with(viewHolder.img_logo.getContext())
                .load(list.getTeam_logo())
                .error(R.drawable.image_found)
                .into(viewHolder.img_logo);

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }


}
