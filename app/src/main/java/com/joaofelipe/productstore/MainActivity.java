package com.joaofelipe.productstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private static final Pattern PRICE_PATTERN = Pattern.compile("^\\d+(?:[\\.,]\\d{1,2})?$");
    private static final Pattern CODE_PATTERN = Pattern.compile("^[A-Za-z0-9]+$");

    private TextInputEditText editName, editCode, editPrice, editQuantity;
    private Button btnSave, btnViewList;
    private ProductDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = ProductDatabase.getInstance(this);

        editName = findViewById(R.id.edit_product_name);
        editCode = findViewById(R.id.edit_product_code);
        editPrice = findViewById(R.id.edit_product_price);
        editQuantity = findViewById(R.id.edit_product_quantity);
        btnSave = findViewById(R.id.btn_save_product);
        btnViewList = findViewById(R.id.btn_view_list);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProduct();
            }
        });

        btnViewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProductListActivity.class));
            }
        });
    }

    private void saveProduct() {
        String name = getTextValue(editName);
        String code = getTextValue(editCode);
        String priceStr = getTextValue(editPrice);
        String quantityStr = getTextValue(editQuantity);

        if (name.isEmpty() || code.isEmpty() || priceStr.isEmpty() || quantityStr.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!CODE_PATTERN.matcher(code).matches()) {
            Toast.makeText(this, "Código deve ser alfanumérico!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!PRICE_PATTERN.matcher(priceStr).matches()) {
            Toast.makeText(this, "Preço deve ser positivo com até 2 casas decimais!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!quantityStr.matches("^[1-9]\\d*$")) {
            Toast.makeText(this, "Quantidade deve ser um número inteiro positivo!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double price = Double.parseDouble(priceStr.replace(',', '.'));
            int quantity = Integer.parseInt(quantityStr);

            if (price <= 0) {
                Toast.makeText(this, "Preço deve ser positivo!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (quantity <= 0) {
                Toast.makeText(this, "Quantidade deve ser positiva!", Toast.LENGTH_SHORT).show();
                return;
            }

            Product product = new Product(name, code, price, quantity);
            db.productDao().insert(product);

            Toast.makeText(this, "Produto cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
            clearFields();

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Dados inválidos!", Toast.LENGTH_SHORT).show();
        }
    }

    private String getTextValue(TextInputEditText input) {
        return input.getText() == null ? "" : input.getText().toString().trim();
    }

    private void clearFields() {
        editName.setText("");
        editCode.setText("");
        editPrice.setText("");
        editQuantity.setText("");
        editName.requestFocus();
    }
}
