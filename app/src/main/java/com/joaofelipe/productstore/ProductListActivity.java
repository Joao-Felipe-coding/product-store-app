package com.joaofelipe.productstore;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private ProductDatabase db;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        db = ProductDatabase.getInstance(this);

        recyclerView = findViewById(R.id.recycler_products);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        loadProducts();
    }

    private void loadProducts() {
        List<Product> products = db.productDao().getAllProducts();
        adapter = new ProductAdapter(products);
        recyclerView.setAdapter(adapter);
    }
}
