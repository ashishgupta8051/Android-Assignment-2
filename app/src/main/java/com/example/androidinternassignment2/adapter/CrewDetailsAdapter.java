package com.example.androidinternassignment2.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.androidinternassignment2.R;
import com.example.androidinternassignment2.model.CrewDetails;
import com.example.androidinternassignment2.utils.ClickListener;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class CrewDetailsAdapter extends RecyclerView.Adapter<CrewDetailsAdapter.ViewHolder>{
    private List<CrewDetails> crewDetailsList = new ArrayList<>();
    private Context context;
    private ClickListener clickListener;

    public CrewDetailsAdapter(Context context, ClickListener clickListener) {
        this.context = context;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.crew_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.linkTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClickListener(crewDetailsList.get(viewHolder.getAdapterPosition()));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CrewDetails crewDetails = crewDetailsList.get(position);

        Glide.with(context).load(crewDetails.getImage()).into(holder.imageView);

        holder.nameTxt.setText(crewDetails.getName());
        holder.agencyTxt.setText(crewDetails.getAgency());
        holder.statusTxt.setText(crewDetails.getStatus());
        holder.linkTxt.setText(crewDetails.getWikipedia());
    }

    @SuppressLint("NotifyDataSetChanged")
    public void getCrewData(List<CrewDetails> list){
        crewDetailsList.clear();
        crewDetailsList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return crewDetailsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        RoundedImageView imageView;
        TextView nameTxt,agencyTxt,statusTxt,linkTxt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image);
            nameTxt = itemView.findViewById(R.id.name);
            agencyTxt = itemView.findViewById(R.id.agency);
            statusTxt = itemView.findViewById(R.id.status);
            linkTxt = itemView.findViewById(R.id.link);
        }
    }
}
