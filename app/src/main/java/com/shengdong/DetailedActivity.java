package com.shengdong;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shengdong.recyclerviewtest.utils.model.ShoeCart;
import com.shengdong.recyclerviewtest.utils.model.ShoeItem;
import com.shengdong.recyclerviewtest.viewmodel.CartViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DetailedActivity extends AppCompatActivity {

    private ImageView shoeImageView, imageView;
    private TextView shoeNameTV, shoeBrandNameTV, shoePriceTV;
    private FloatingActionButton addToCartBtn;
    private ShoeItem shoe;
    private ShoeCart shoeCarts;
    private CartViewModel viewModel;
    private List<ShoeCart> shoeCartList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle2);

        shoe = getIntent().getParcelableExtra("shoeItem");
        initializeVariables();



        viewModel.getAllCartItems().observe(this, new Observer<List<ShoeCart>>() {
            @Override
            public void onChanged(List<ShoeCart> shoeCarts) {
                shoeCartList.addAll(shoeCarts);
            }
        });

        /* if (shoe != null) {
            setDataToWidgets();
        }*/

      addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertToRoom();
            }
        });


        Picasso.get().load(getIntent().getStringExtra("producto_image_detalle"))
                .placeholder(R.drawable.camisa1)
                .into(shoeImageView);

        shoeNameTV.setText(getIntent().getStringExtra("producto_name_detalle"));
        shoeBrandNameTV.setText(getIntent().getStringExtra("producto_name_descripcion"));
        shoePriceTV.setText(String.valueOf(getIntent().getStringExtra("producto_price_descripcion")));



    }



    private void insertToRoom(){
        Intent i = new Intent(this, CartActivity.class);
        startActivity(i);
    }
       /* ShoeItem shoeItem = new ShoeItem();
        ShoeCart shoeCart = new ShoeCart();
        shoeCart.setShoeName(shoeItem.getShoeName());
        shoeCart.setShoeBrandName(shoeItem.getShoeBrandName());
        shoeCart.setShoePrice(shoeItem.getShoePrice());
        shoeCart.setShoeImage(shoeItem.getShoeImage());

        final int[] quantity = {1};
        final int[] id = new int[1];

        if (!shoeCartList.isEmpty()){
            for(int i=0;i<shoeCartList.size();i++){
                if (shoeCart.getShoeName().equals(shoeCartList.get(i).getShoeName())){
                    quantity[0] = shoeCartList.get(i).getQuantity();
                    quantity[0]++;
                    id[0] = shoeCartList.get(i).getId();
                }
            }
        }

        if (quantity[0]==1){
            shoeCart.setQuantity(quantity[0]);
            shoeCart.setTotalItemPrice(quantity[0]*shoeCart.getShoePrice());
            viewModel.insertCartItem(shoeCart);
        }else{

            viewModel.updateQuantity(id[0] ,quantity[0]);
            viewModel.updatePrice(id[0] , quantity[0]*shoeCart.getShoePrice());
        }

        startActivity(new Intent(DetailedActivity.this , CartActivity.class));
    }

   /* private void setDataToWidgets() {
        shoeNameTV.setText(shoe.getShoeName());
        shoeBrandNameTV.setText(shoe.getShoeBrandName());
        shoePriceTV.setText(String.valueOf(shoe.getShoePrice()));

        Picasso.get().load(getIntent().getStringExtra("producto_image_detalle"))
                .placeholder(R.drawable.camisa1)
                .into(shoeImageView);
       // imageView.setImageResource((shoe.getShoeImage()));
    }*/


    private void initializeVariables() {

        shoeCartList = new ArrayList<>();
        shoeImageView = findViewById(R.id.producto_image_detalle);
        shoeNameTV = findViewById(R.id.producto_name_detalle);
        shoeBrandNameTV = findViewById(R.id.producto_name_descripcion);
        shoePriceTV = findViewById(R.id.producto_price_descripcion);
        addToCartBtn = findViewById(R.id.add_product_cart);
        //imageView = findViewById(R.id.producto_image_detalle);

        viewModel = new ViewModelProvider(this).get(CartViewModel.class);
    }

    public void irAtras(View view){
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }


}