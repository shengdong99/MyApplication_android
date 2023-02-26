package com.shengdong.recyclerviewtest.utils.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shengdong.DetailedActivity;
import com.shengdong.R;
import com.shengdong.recyclerviewtest.Model.Products;
import com.shengdong.recyclerviewtest.utils.model.ShoeItem;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProductItemAdapter extends RecyclerView.Adapter<ProductItemAdapter.ProductViewHolder>{
    private List<ShoeItem> shoeItemList;
    private Context context;

    private ShoeClickedListeners shoeClickedListeners;

    public ProductItemAdapter(ShoeClickedListeners shoeClickedListeners, List<ShoeItem> shoeItemList){
        this.shoeClickedListeners = shoeClickedListeners;
        this.shoeItemList = shoeItemList;
    }

    public ProductItemAdapter(List<ShoeItem> shoeItemList, Context context) {
        this.shoeItemList = shoeItemList;
        this.context = context;
    }

    public void setShoeItemList(List<ShoeItem> shoeItemList){
        this.shoeItemList = shoeItemList;
    }



    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.each_ropa, parent,false));
    }


    public Context getContext() {
        return context;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ShoeItem shoeItem = shoeItemList.get(position);

        /*Glide.with(context)
                        .load(shoeItemList.get(position).getShoeImage())
                                .into(holder.shoeImageView);*/
        Picasso.get().load(shoeItemList.get(position).getImage()).into(holder.shoeImageView);
        holder.shoeNameTv.setText(shoeItem.getShoeName());
        holder.shoeBrandNameTv.setText(shoeItem.getShoeBrandName());
        holder.shoePriceTv.setText(String.valueOf(shoeItem.getShoePrice()));



        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //shoeClickedListeners.onCardClicked(shoeItem);
                Intent intent = new Intent(view.getContext(), DetailedActivity.class);
                intent.putExtra("producto_name_detalle", shoeItem.getShoeName());
                intent.putExtra("producto_name_descripcion", shoeItem.getShoeBrandName());
                intent.putExtra("producto_price_descripcion", String.valueOf(shoeItem.getShoePrice()));
                intent.putExtra("producto_image_detalle", shoeItem.getImage());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                view.getContext().startActivity(intent);
            }
        });

        holder.addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shoeClickedListeners.onAddToCartBtnClicked(shoeItem);
              /* Intent intent = new Intent(view.getContext(), DetailedActivity.class);
                intent.putExtra("eachCartItemName", shoeItem.getShoeName());
                intent.putExtra("eachCartItemBrandNameTv", shoeItem.getShoeBrandName());
                intent.putExtra("eachCartItemPriceTv", String.valueOf(shoeItem.getShoePrice()));
                intent.putExtra("eachCartItemIV", shoeItem.getImage());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                view.getContext().startActivity(intent);*/
            }
        });
    }

    @Override
    public int getItemCount() {
        if (shoeItemList == null){
            return 0;
        }else{
            return shoeItemList.size();
        }
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.eachShoeIv)
        ImageView imageView;
        @BindView(R.id.eachShoeName)
        TextView txtName;
        @BindView(R.id.eachShoeBrandNameTv)
        TextView txtDescription;
        @BindView(R.id.eachShoePriceTv)
        TextView txtPrice;

        public ImageView shoeImageView , addToCartBtn;
        public TextView shoeNameTv, shoeBrandNameTv, shoePriceTv;
        public CardView cardView;

        private Unbinder unbinder;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this,itemView);
            cardView = itemView.findViewById(R.id.eachShoeCardView);
            addToCartBtn = itemView.findViewById(R.id.eachShoeAddToCartBtn);
            shoeNameTv =  itemView.findViewById(R.id.eachShoeName);
            shoeImageView = itemView.findViewById(R.id.eachShoeIv);
            shoeBrandNameTv =  itemView.findViewById(R.id.eachShoeBrandNameTv);
            shoePriceTv = itemView.findViewById(R.id.eachShoePriceTv);
        }

    }


    public interface ShoeClickedListeners{
        void onCardClicked(ShoeItem shoe);


        void onAddToCartBtnClicked(ShoeItem shoeItem);
    }



}

