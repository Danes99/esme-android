package org.libreapps.thinkit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.libreapps.thinkit.R;
import org.libreapps.thinkit.listeners.ItemClickListener;
import org.libreapps.thinkit.models.UserPostModel;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<UserPostModel> dataList;
    private ItemClickListener mListener;

    public PostAdapter(Context mContext, ArrayList<UserPostModel> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post, parent, false);

        return new PostAdapter.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        UserPostModel model = dataList.get(position);

        holder.tvTitle.setText(model.getTitle());
        holder.tvContent.setText(model.getContent());

        if(model.getIsFavorite() == 0){
            holder.ivFav.setImageResource(R.drawable.heart__1_);
        }
        else{
            holder.ivFav.setImageResource(R.drawable.heart);
        }

        holder.ivFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClickListener(v,position);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClickListener(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvContent;
        private ImageView ivFav;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvContent = itemView.findViewById(R.id.tvContent);
            ivFav = itemView.findViewById(R.id.ivFav);
        }
    }

    public void setOnItemClickListener(ItemClickListener mListener){
        if(mListener != null)
            this.mListener = mListener;
    }
}
