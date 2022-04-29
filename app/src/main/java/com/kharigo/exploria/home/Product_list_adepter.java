package com.kharigo.exploria.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.kharigo.exploria.MainActivity;
import com.kharigo.exploria.R;
import com.like.LikeButton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Product_list_adepter extends RecyclerView.Adapter<Product_list_adepter.viewholder> {

        private Context mCtx;
        private List<Product_list> productList;
        public Product_list_adepter(Context mCtx, List<Product_list> productList) {
            this.mCtx = mCtx;
            this.productList = productList;
        }

        @NonNull
        @Override
        public Product_list_adepter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater= LayoutInflater.from(mCtx);
            View v =inflater.inflate(R.layout.product_list_xml, parent, false);
            final viewholder vh = new viewholder(v);

            vh.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Product_list product = productList.get(vh.getAdapterPosition());
                    //do the page opening here

//                    Intent intent = new Intent(mCtx, MainActivity.class);
//                    intent.putExtra("sku",product.getId());
//                    mCtx.startActivity(intent);

                }
            });
            return vh;






        }



    @Override
        public void onBindViewHolder(@NonNull final Product_list_adepter.viewholder holder, final int position) {
            final Product_list product = productList.get(position);

if (product.getType().equals("ads")){
    holder.BTN_comment.setVisibility(View.GONE);
    Glide.with(mCtx)
            .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRF-NnWRe6tHRcQ3yqkPVXYdBKF41u3bLOEXQ&usqp=CAU")
            .thumbnail(0.10f)
            .thumbnail(0.40f)
            .thumbnail(0.80f)
            .into(holder.IMG_user);
holder.TV_ads.setText("AD");
}else{
    holder.BTN_comment.setVisibility(View.VISIBLE);
    holder.TV_ads.setText("");
}


        Glide.with(mCtx)
                .load(product.getImg())
                .thumbnail(0.10f)
                .thumbnail(0.40f)
                .thumbnail(0.80f)
                .into(holder.IMG_img);

            holder.TV_user.setText(String.valueOf(" "+product.getUser()));
            holder.TV_time.setText(String.valueOf(" "+product.getTime()));



        }

        @Override
        public int getItemCount() {
            return productList.size();
        }

        public class viewholder extends RecyclerView.ViewHolder{
            TextView TV_user,TV_time,TV_ads;
            ImageView IMG_user,IMG_img;
            ImageButton BTN_comment;
            LikeButton BTN_like,BTN_save;
            public viewholder(@NonNull View itemView) {
                super(itemView);
                TV_ads = itemView.findViewById(R.id.TV_ads);
                IMG_user = itemView.findViewById(R.id.IMG_user);
                IMG_img = itemView.findViewById(R.id.IMG_img);
                TV_user = itemView.findViewById(R.id.TV_user);
                TV_time = itemView.findViewById(R.id.TV_time);

                BTN_like = itemView.findViewById(R.id.BTN_like);
                BTN_comment = itemView.findViewById(R.id.BTN_comment);
                BTN_save = itemView.findViewById(R.id.BTN_save);


            }
        }

}



