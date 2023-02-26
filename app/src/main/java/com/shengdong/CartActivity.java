package com.shengdong;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shengdong.recyclerviewtest.utils.adapter.CartAdapter;
import com.shengdong.recyclerviewtest.utils.model.ShoeCart;
import com.shengdong.recyclerviewtest.utils.model.ShoeItem;
import com.shengdong.recyclerviewtest.viewmodel.CartViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity implements CartAdapter.CartClickedListeners {

    private RecyclerView recyclerView;
    private CartViewModel cartViewModel;
    private TextView totalCartPriceTv, textView;
    private AppCompatButton checkoutBtn;
    private CardView cardView;
    private CartAdapter cartAdapter;

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
        setContentView(R.layout.activity_cart);

        initializeVariables();

       /* Picasso.get().load(getIntent().getStringExtra("eachCartItemIV"))
                .placeholder(R.drawable.camisa1)
                .into(shoeImageView);

        shoeNameTV.setText(getIntent().getStringExtra("eachCartItemName"));
        shoeBrandNameTV.setText(getIntent().getStringExtra("eachCartItemBrandNameTv"));
        shoePriceTV.setText(String.valueOf(getIntent().getStringExtra("eachCartItemPriceTv")));
*/
        cartViewModel.getAllCartItems().observe(this, new Observer<List<ShoeCart>>() {
            @Override
            public void onChanged(List<ShoeCart> shoeCarts) {
                double price = 0;
                cartAdapter.setShoeCartList(shoeCarts);
                for (int i=0;i<shoeCarts.size();i++){
                    price = price + shoeCarts.get(i).getTotalItemPrice();
                }
                totalCartPriceTv.setText(String.valueOf(price));
            }
        });

        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartViewModel.deleteAllCartItems();
                textView.setVisibility(View.INVISIBLE);
                checkoutBtn.setVisibility(View.INVISIBLE);
                totalCartPriceTv.setVisibility(View.INVISIBLE);
                cardView.setVisibility(View.VISIBLE);
            }
        });
    }



    private void initializeVariables() {

        cartAdapter = new CartAdapter(this);
        textView = findViewById(R.id.textView2);
        cardView = findViewById(R.id.cartActivityCardView);
        totalCartPriceTv = findViewById(R.id.cartActivityTotalPriceTv);
        checkoutBtn = findViewById(R.id.cartActivityCheckoutBtn);
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        recyclerView = findViewById(R.id.cartRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(cartAdapter);

        shoeCartList = new ArrayList<>();
        shoeImageView = findViewById(R.id.eachCartItemIV);
        shoeNameTV = findViewById(R.id.eachCartItemName);
        shoeBrandNameTV = findViewById(R.id.eachCartItemBrandNameTv);
        shoePriceTV = findViewById(R.id.eachCartItemPriceTv);
        //imageView = findViewById(R.id.producto_image_detalle);




    }

    @Override
    public void onDeleteClicked(ShoeCart shoeCart) {
        cartViewModel.deleteCartItem(shoeCart);
    }

    @Override
    public void onPlusClicked(ShoeCart shoeCart) {
        int quantity = shoeCart.getQuantity() + 1;
        cartViewModel.updateQuantity(shoeCart.getId() , quantity);
        cartViewModel.updatePrice(shoeCart.getId() , quantity*shoeCart.getShoePrice());
        cartAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMinusClicked(ShoeCart shoeCart) {
        int quantity = shoeCart.getQuantity() - 1;
        if (quantity != 0){
            cartViewModel.updateQuantity(shoeCart.getId() , quantity);
            cartViewModel.updatePrice(shoeCart.getId() , quantity*shoeCart.getShoePrice());
            cartAdapter.notifyDataSetChanged();
        }else{
            cartViewModel.deleteCartItem(shoeCart);
        }

    }

    public void irAtras1(View view){
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }
}