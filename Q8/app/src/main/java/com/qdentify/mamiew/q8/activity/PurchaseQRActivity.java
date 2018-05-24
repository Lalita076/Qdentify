package com.qdentify.mamiew.q8.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.qdentify.mamiew.q8.R;
import com.qdentify.mamiew.q8.dao.PurchaseObject;

public class PurchaseQRActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private Button btnPurchaseQR, btnAlert;
    private RadioButton rdPDF, rdPatch, rdPaypal, rdCredit, rdEms, rdRegister;
    private EditText quantity;
    private TextView headName, tvTotal, tvAddress;
    private String userId, name, address = "126 Pracha Uthit Rd., Bang Mod, Thung Khru, Bangkok 10140, Thailand";
    private int unit = 1, total = 0, tagTotal = 0, shippingTotal = 0;
    private PurchaseObject purchaseObject;
    private Intent intent;
    private LinearLayout loQuantity;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_qr);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Purchase QR tag");

        firebaseAuth = FirebaseAuth.getInstance();
        userId = firebaseAuth.getCurrentUser().getUid();

        intent = getIntent();

        initInstances();

    }

    private void initInstances() {
        //get bundle from PersonInfo Activity
        name = intent.getStringExtra("name");

        headName = (TextView) findViewById(R.id.tv_head_name);
        tvTotal = (TextView) findViewById(R.id.tv_total);
        rdPDF = (RadioButton) findViewById(R.id.rd_pdf);
        rdPatch = (RadioButton) findViewById(R.id.rd_patch);
        rdPaypal = (RadioButton) findViewById(R.id.rd_paypal);
        rdCredit = (RadioButton) findViewById(R.id.rd_credit);
        rdEms = (RadioButton) findViewById(R.id.rd_ems);
        rdRegister = (RadioButton) findViewById(R.id.rd_register);
        quantity = (EditText) findViewById(R.id.et_quantity);
        tvAddress = (TextView) findViewById(R.id.tv_address);

        headName.setText(name);
        total = tagTotal + shippingTotal;
        tvTotal.setText(total + " " + "Baht");

        //Create Parcelable to sent to Purchase success Activity
        purchaseObject = new PurchaseObject();
        rdPDF.setOnClickListener(purchaseOnclickListener);
        rdPatch.setOnClickListener(purchaseOnclickListener);
        rdEms.setOnClickListener(purchaseOnclickListener);
        rdRegister.setOnClickListener(purchaseOnclickListener);

        btnPurchaseQR = (Button) findViewById(R.id.btn_purchase);
        btnPurchaseQR.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnPurchaseQR) {
            Toast.makeText(this, "Purchase Success", Toast.LENGTH_SHORT).show();
            Intent purchase = new Intent(this, PurchaseSuccessActivity.class);
            purchaseObject.setpID(userId);
            purchaseObject.setName(name);
            purchaseObject.setAddress(address);
            purchaseObject.setTotal(total);
            purchase.putExtra("purchaseObjParcelable", purchaseObject);
            Log.d("Purchase Activity", "Parcelable success");
            startActivity(purchase);
        }
    }

    RadioButton.OnClickListener purchaseOnclickListener;

    {
        purchaseOnclickListener = new RadioButton.OnClickListener() {

            @Override
            public void onClick(View v) {
                /*if(rdPDF.isChecked()){
                    purchaseObject.setTagType("PDF");
                    total += 50;
                    tvTotal.setText(total + " " + "Baht");
                    purchaseObject.setShippingMethod("-");
                    purchaseObject.setTotal(total);
                }
                if(rdPatch.isChecked()){
                    purchaseObject.setTagType("Patch Embroidery");
                    unit = Integer.parseInt(quantity.getText().toString().trim());
                    total += (100 * unit);
                    tvTotal.setText(total + " " + "Baht");
                }
                if(rdEms.isChecked()){
                    purchaseObject.setShippingMethod("EMS");
                    total += 50;
                    tvTotal.setText(total + " " + "Baht");
                }
                if(rdRegister.isChecked()){
                    purchaseObject.setShippingMethod("Register");
                    total += 30;
                    tvTotal.setText(total + " " + "Baht");
                }*/
                if (rdPaypal.isChecked()) {
                    purchaseObject.setPaymentMethod("Paypal");
                }
                if (rdCredit.isChecked()) {
                    purchaseObject.setPaymentMethod("Credit Card");
                }
                if (rdPDF.isChecked() || rdPatch.isChecked()) {
                    if (rdPDF.isChecked()) {
                        purchaseObject.setTagType("PDF");
                        tagTotal = 50;
                        tvTotal.setText(total + " " + "Baht");
                        purchaseObject.setShippingMethod("-");
                        purchaseObject.setTotal(total);
                    } else if (rdPatch.isChecked()) {
                        purchaseObject.setTagType("Patch Embroidery");
                        unit = Integer.parseInt(quantity.getText().toString().trim());
                        tagTotal = (100 * unit);
                        tvTotal.setText(total + " " + "Baht");
                    }
                }
                if (rdRegister.isChecked() || rdEms.isChecked()) {
                    if (rdEms.isChecked()) {
                        purchaseObject.setShippingMethod("EMS");
                        shippingTotal = 50;
                        tvTotal.setText(total + " " + "Baht");
                    }
                    if (rdRegister.isChecked()) {
                        purchaseObject.setShippingMethod("Register");
                        shippingTotal = 30;
                        tvTotal.setText(total + " " + "Baht");
                    }
                }

                total = tagTotal + shippingTotal;
                tvTotal.setText(total + " " + "Baht");
            }

        };
    }
}
