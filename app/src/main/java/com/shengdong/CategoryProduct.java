package com.shengdong;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class CategoryProduct extends AppCompatActivity {
    private ImageView ropa_hombre, ropa_mujer, ropa_deporte, sapatillas;
    private ImageView disfraces, bufanda, sombreros, gafas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        ropa_hombre = (ImageView) findViewById(R.id.ropa_hombre);
        ropa_mujer = (ImageView) findViewById(R.id.ropa_mujer);
        ropa_deporte = (ImageView) findViewById(R.id.ropa_deporte);
        sapatillas = (ImageView) findViewById(R.id.sapatillas);

        disfraces = (ImageView) findViewById(R.id.disfraces);
        bufanda = (ImageView) findViewById(R.id.bufanda);
        sombreros = (ImageView) findViewById(R.id.sombreros);
        gafas = (ImageView) findViewById(R.id.gafas);



        ropa_hombre.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryProduct.this, AddProduct.class);
                intent.putExtra("category", "ropa_hombre");
                startActivity(intent);
            }
        });

        ropa_mujer.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryProduct.this, AddProduct.class);
                intent.putExtra("category", "ropa_mujer");
                startActivity(intent);
            }
        });

        ropa_deporte.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryProduct.this, AddProduct.class);
                intent.putExtra("category", "ropa_deporte");
                startActivity(intent);
            }
        });

        sapatillas.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryProduct.this, AddProduct.class);
                intent.putExtra("category", "sapatillas");
                startActivity(intent);
            }
        });

        disfraces.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryProduct.this, AddProduct.class);
                intent.putExtra("category", "disfraces");
                startActivity(intent);
            }
        });

        bufanda.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryProduct.this, AddProduct.class);
                intent.putExtra("category", "bufanda");
                startActivity(intent);
            }
        });

        sombreros.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryProduct.this, AddProduct.class);
                intent.putExtra("category", "sombreros");
                startActivity(intent);
            }
        });

        gafas.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryProduct.this, AddProduct.class);
                intent.putExtra("category", "gafas");
                startActivity(intent);
            }
        });



    }
}