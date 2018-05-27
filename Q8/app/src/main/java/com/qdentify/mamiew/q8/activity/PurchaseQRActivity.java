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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.qdentify.mamiew.q8.R;
import com.qdentify.mamiew.q8.dao.PurchaseObject;

import java.util.HashMap;
import java.util.Map;
import java.util.SimpleTimeZone;

public class PurchaseQRActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private Button btnPurchaseQR, btnAlert;
    private RadioButton rdPDF, rdPatch, rdPaypal, rdCredit, rdEms, rdRegister;
    private EditText quantity;
    private TextView headName, tvTotal, tvAddress;
    private String userId, patientId, name, address = "126 Pracha Uthit Rd., Bang Mod, Thung Khru, Bangkok 10140, Thailand";
    private int unit = 1, total = 0, tagTotal = 0, shippingTotal = 0;
    private PurchaseObject purchaseObject;
    private Intent intent;
    private LinearLayout loQuantity;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_qr);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Purchase QR tag");

        firebaseAuth = FirebaseAuth.getInstance();
        userId = firebaseAuth.getCurrentUser().getUid();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("purchaseQR");

        intent = getIntent();
        name = intent.getStringExtra("name");
        patientId = intent.getStringExtra("patientId");

        initInstances();

    }

    private void initInstances() {
        //get bundle from PersonInfo Activity

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

        tvAddress.setText(address);

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
            save();
            Toast.makeText(this, "Purchase Success", Toast.LENGTH_SHORT).show();
            Intent purchase = new Intent(this, PurchaseSuccessActivity.class);
            purchaseObject.setpID(userId);
            purchaseObject.setName(name);
            purchaseObject.setAddress(address);
            purchaseObject.setTotal(tvTotal.getText().toString());
            purchase.putExtra("purchaseObjParcelable", purchaseObject);
            Log.d("Purchase Activity", "Parcelable success");
            startActivity(purchase);
        }
    }

    private void save() {
        String tagType = purchaseObject.getTagType();
        String shipMethod = purchaseObject.getShippingMethod();
        String payMethod = purchaseObject.getPaymentMethod();
        String totalSuccess = purchaseObject.getTotal();

        String newPurchase = databaseReference.push().getKey();
        //Toast.makeText(this,"Patient key :"+newPurchase,Toast.LENGTH_SHORT).show();

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("name", name);
        map.put("patientId", patientId);
        map.put("tagType", tagType);
        map.put("shipMethod", shipMethod);
        map.put("payMethod", payMethod);
        map.put("total", totalSuccess);

        databaseReference.child(newPurchase).setValue(map);

    }

    RadioButton.OnClickListener purchaseOnclickListener;

    {
        purchaseOnclickListener = new RadioButton.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (rdPDF.isChecked() || rdPatch.isChecked()) {
                    if (rdPDF.isChecked()) {
                        purchaseObject.setTagType("PDF");
                        tagTotal = 50;
                        tvTotal.setText(total + " " + "Baht");
                        purchaseObject.setShippingMethod("-");
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
                if (rdPaypal.isChecked() || rdCredit.isChecked()) {
                    if (rdPaypal.isChecked()) {
                        purchaseObject.setPaymentMethod("Paypal");
                    }
                    if (rdCredit.isChecked()) {
                        purchaseObject.setPaymentMethod("Credit Card");
                    }
                }

                total = tagTotal + shippingTotal;
                tvTotal.setText(total + " " + "Baht");
                purchaseObject.setTotal(tvTotal.getText().toString());
            }

        };
    }
}
