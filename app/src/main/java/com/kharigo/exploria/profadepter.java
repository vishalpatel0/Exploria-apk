package com.kharigo.exploria;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.like.LikeButton;

import java.util.List;


import android.widget.Button;


public class profadepter extends RecyclerView.Adapter<profadepter.viewholder> {

    private Context mCtx;
    private List<Product_list> productList;
    public profadepter(Context mCtx, List<Product_list> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @NonNull
    @Override
    public profadepter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(mCtx);
        View v =inflater.inflate(R.layout.ps, parent, false);
        final profadepter.viewholder vh = new profadepter.viewholder(v);

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
    public void onBindViewHolder(@NonNull final profadepter.viewholder holder, final int position) {
        final Product_list product = productList.get(position);

        if (product.getType().equals("ads")){
            Glide.with(mCtx)
                    .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRF-NnWRe6tHRcQ3yqkPVXYdBKF41u3bLOEXQ&usqp=CAU")
                    .thumbnail(0.10f)
                    .thumbnail(0.40f)
                    .thumbnail(0.80f)
                    .into(holder.IMG_user);
        }else{
        }


        Glide.with(mCtx)
                .load(product.getImg())
                .thumbnail(0.10f)
                .thumbnail(0.40f)
                .thumbnail(0.80f)
                .into(holder.IMG_img);

        holder.TV_user.setText(String.valueOf(" "+product.getUser()));
        holder.TV_time.setText(String.valueOf(" "+product.getTime()));
holder.BTN_save.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        productList.remove(position);  // remove the item from list
        notifyItemRemoved(position); // notify the adapter about the removed item
        notifyItemRangeChanged(position, getItemCount());

    }
});


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        TextView TV_user,TV_time;
        ImageView IMG_user,IMG_img;
        ImageButton BTN_comment;
        LikeButton BTN_like;
        Button BTN_save;
        public viewholder(@NonNull View itemView) {
            super(itemView);
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



