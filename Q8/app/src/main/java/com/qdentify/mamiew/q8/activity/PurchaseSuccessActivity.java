package com.qdentify.mamiew.q8.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qdentify.mamiew.q8.R;
import com.qdentify.mamiew.q8.dao.PurchaseObject;

import org.w3c.dom.Text;

public class PurchaseSuccessActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView name;
    private TextView tagType;
    private TextView paymentMethod;
    private TextView shippingMethod;
    private TextView address;
    private TextView total;
    private PurchaseObject purchaseObjectParcelable;
    private Button btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_success);

        Intent intent = getIntent();
        purchaseObjectParcelable = (PurchaseObject)
                intent.getParcelableExtra("purchaseObjParcelable");

        initInstances();
    }

    private void initInstances() {
        name = (TextView) findViewById(R.id.tv_p_name);
        tagType = (TextView) findViewById(R.id.tv_tag_type);
        paymentMethod = (TextView) findViewById(R.id.tv_payment_method);
        shippingMethod = (TextView) findViewById(R.id.tv_shipping);
        address = (TextView) findViewById(R.id.tv_detail);
        total = (TextView) findViewById(R.id.tv_total);
        btnHome = (Button) findViewById(R.id.btn_tohome);

        btnHome.setOnClickListener(this);

        name.setText(purchaseObjectParcelable.getName());
        tagType.setText(purchaseObjectParcelable.getTagType());
        paymentMethod.setText(purchaseObjectParcelable.getPaymentMethod());
        shippingMethod.setText(purchaseObjectParcelable.getShippingMethod());
        if (purchaseObjectParcelable.getTagType().equals("Patch Embroidery")) {
            address.setText(purchaseObjectParcelable.getAddress());
        }else {
            address.setText("-");
        }

        total.setText(purchaseObjectParcelable.getTotal());
    }

    @Override
    public void onClick(View v) {
        Intent home = new Intent(this, MainActivity.class);
        startActivity(home);
    }
}
