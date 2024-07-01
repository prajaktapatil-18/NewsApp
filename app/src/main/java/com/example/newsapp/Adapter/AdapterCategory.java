package com.example.newsapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.Model.RvModel;
import com.example.newsapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.ViewHolder> {
    private ArrayList<RvModel> cateModel;
    private Context context;
    private categoryClickInterface categoryClickInterface;

    public AdapterCategory(ArrayList<RvModel> cateModel, Context context, AdapterCategory.categoryClickInterface categoryClickInterface) {
        this.cateModel = cateModel;
        this.context = context;
        this.categoryClickInterface = categoryClickInterface;
    }

    @NonNull
    @Override
    public AdapterCategory.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_lyt, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCategory.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        RvModel rvModel = cateModel.get(position);
        holder.cateTitle.setText(cateModel.get(position).getCategory());
        Picasso.get().load(cateModel.get(position).getCategoryImgUrl()).into(holder.cateImg);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryClickInterface.onCategoryClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return cateModel.size();
    }


    public interface categoryClickInterface {
        void onCategoryClick(int position);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView cateImg;
        TextView cateTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cateImg = itemView.findViewById(R.id.cateImg);
            cateTitle = itemView.findViewById(R.id.cateTitle);

        }
    }


}
