package com.reivai.foodlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

public class DetailFoodActivity extends AppCompatActivity {

    Toolbar toolbar;

    ImageView img_detFood;
    TextView tv_nameFood, tv_descFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_food);

        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        img_detFood = findViewById(R.id.img_detFood);
        tv_nameFood = findViewById(R.id.tv_detName);
        tv_descFood = findViewById(R.id.tv_detDesc);

//        Picasso.get().load(getIntent().getStringExtra("image")).into(img_detFood);
        RequestOptions options = new RequestOptions()
                .circleCrop()
                .placeholder(R.drawable.loading)
                .error(R.drawable.ic_launcher_background)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .dontAnimate()
                .dontTransform();
        Glide.with(this)
                .load(getIntent().getStringExtra("image"))
                .apply(options)
                .into(img_detFood);
        tv_nameFood.setText(getIntent().getStringExtra("name"));
        tv_descFood.setText(getIntent().getStringExtra("desc"));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
