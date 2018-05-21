package com.qdentify.mamiew.q8.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.qdentify.mamiew.q8.R;
import com.qdentify.mamiew.q8.dao.PurchaseObject;

import org.w3c.dom.Text;

public class PurchaseSuccessActivity extends AppCompatActivity {
    TextView pID, name, tagType, paymentMethod, shippingMethod, address, total;
    private Intent intent;
    private PurchaseObject purchaseObjectParcelable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_success);

        intent = getIntent();
        purchaseObjectParcelable = (PurchaseObject)
                intent.getParcelableExtra("purchaseObjParcelable");

        initInstances();
    }

    private void initInstances() {
        name = (TextView)findViewById(R.id.tv_p_name);
        tagType = (TextView)findViewById(R.id.tv_tag_type);
        paymentMethod = (TextView)findViewById(R.id.tv_payment_method);
        shippingMethod = (TextView)findViewById(R.id.tv_shipping);
        address = (TextView)findViewById(R.id.tv_address);
        total = (TextView)findViewById(R.id.tv_total);

        name.setText(purchaseObjectParcelable.getName());
        tagType.setText(purchaseObjectParcelable.getTagType());
        paymentMethod.setText(purchaseObjectParcelable.getPaymentMethod());
        shippingMethod.setText(purchaseObjectParcelable.getShippingMethod());
        address.setText(purchaseObjectParcelable.getAddress());
        total.setText(purchaseObjectParcelable.getTotal());

    }
}
