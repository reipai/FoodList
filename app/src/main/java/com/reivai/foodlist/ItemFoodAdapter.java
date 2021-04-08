package com.reivai.foodlist;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ItemFoodAdapter extends RecyclerView.Adapter<ItemFoodAdapter.HolderData> {

    private List<FoodModel> foodModels = new ArrayList<>();
    private Context con;

    public ItemFoodAdapter(List<FoodModel> foodModels, Context con) {
        this.foodModels = foodModels;
        this.con = con;
    }

    @NonNull
    @Override
    public ItemFoodAdapter.HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(con).inflate(R.layout.item_food, viewGroup, false);
        return new HolderData(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemFoodAdapter.HolderData holderData, final int pos) {
        holderData.tv_name.setText(foodModels.get(pos).getName());
//        Picasso.get().load(foodModels.get(pos).getImage()).into(holderData.img_food);
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.loading)
                .error(R.drawable.ic_launcher_background)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .dontAnimate()
                .dontTransform();
        Glide.with(con)
                .load(foodModels.get(pos).getImage())
                .apply(options)
                .into(holderData.img_food);

        holderData.cv_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(con, DetailFoodActivity.class);
                FoodModel foodModel = foodModels.get(pos);
                i.putExtra("name", foodModel.getName());
                i.putExtra("image", foodModel.getImage());
                i.putExtra("desc", foodModel.getDesc());
                con.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodModels.size();
    }


    public class HolderData extends RecyclerView.ViewHolder {
        TextView tv_name;
        ImageView img_food;
        CardView cv_food;

        public HolderData(@NonNull View itemView) {
            super(itemView);
            cv_food = itemView.findViewById(R.id.cv_food);
            tv_name = itemView.findViewById(R.id.tv_nameFood);
            img_food = itemView.findViewById(R.id.img_food);
        }
    }
}
