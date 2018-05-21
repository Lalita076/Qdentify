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

import com.qdentify.mamiew.q8.R;
import com.qdentify.mamiew.q8.dao.PurchaseObject;

public class PurchaseQRActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    Button btnPurchaseQR, btnAlert;
    RadioButton rdPDF, rdPatch, rdPaypal, rdCredit, rdEms, rdRegister;
    EditText quantity;
    TextView headName, tvTotal;
    String pID, name;
    int unit=0, total=0;
    PurchaseObject purchaseObject;
    Intent intent;
    LinearLayout loQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_qr);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        intent = getIntent();

        initInstances();
    }

    private void initInstances() {
        //get bundle from PersonInfo Activity
        pID = intent.getStringExtra("pID");
        name = intent.getStringExtra("name");

        headName= (TextView)findViewById(R.id.tv_head_name);
        tvTotal = (TextView) findViewById(R.id.tv_total);
        rdPDF = (RadioButton)findViewById(R.id.rd_pdf);
        rdPatch = (RadioButton)findViewById(R.id.rd_patch);
        rdPaypal = (RadioButton)findViewById(R.id.rd_paypal);
        rdCredit = (RadioButton)findViewById(R.id.rd_credit);
        rdEms = (RadioButton)findViewById(R.id.rd_ems);
        rdRegister = (RadioButton)findViewById(R.id.rd_register);
        quantity = (EditText)findViewById(R.id.et_quantity);

        headName.setText(name);
        tvTotal.setText(total + " "+"Baht");

        //Create Parcelable to sent to Purchase success Activity
        purchaseObject = new PurchaseObject();
        rdPDF.setOnClickListener(purchaseOnclickListener);
        rdPatch.setOnClickListener(purchaseOnclickListener);

        btnPurchaseQR = (Button)findViewById(R.id.btn_purchase);
        btnPurchaseQR.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==btnPurchaseQR){
            Toast.makeText(this, "Purchase Success",Toast.LENGTH_SHORT).show();
            unit = Integer.parseInt(quantity.getText().toString().trim());
            Intent purchase = new Intent(PurchaseQRActivity.this,PurchaseSuccessActivity.class);
            purchaseObject.setpID(pID);
            purchaseObject.setName(name);
            purchaseObject.setAddress("Bangkok");
            purchase.putExtra("purchaseObjParcelable", purchaseObject);
            Log.d("Purchase Activity", "Parcelable success");

        }
    }

    RadioButton.OnClickListener purchaseOnclickListener;
    {
        purchaseOnclickListener = new RadioButton.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(rdPDF.isChecked()){
                    purchaseObject.setTagType("PDF");
                    total += 50;
                    tvTotal.setText(total + " " + "Baht");
                    purchaseObject.setShippingMethod("-");
                    purchaseObject.setTotal(total);
                }
                if(rdPatch.isChecked()){
                    loQuantity.setVisibility(LinearLayout.VISIBLE);
                }
                if(rdEms.isChecked()){
                    purchaseObject.setShippingMethod("EMS");
                }
                if(rdRegister.isChecked()){
                    purchaseObject.setShippingMethod("Register");
                }
                if(rdPaypal.isChecked()){
                    purchaseObject.setPaymentMethod("Paypal");
                }
                if (rdCredit.isChecked()){
                    purchaseObject.setPaymentMethod("Credit Card");
                }
            }
        };
    }
}
