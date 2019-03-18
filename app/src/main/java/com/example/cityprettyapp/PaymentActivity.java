package com.example.cityprettyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.stripe.android.model.Card;
import com.stripe.android.view.CardInputWidget;

public class PaymentActivity extends AppCompatActivity {


    private CardInputWidget mCardInputWidget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        mCardInputWidget = (CardInputWidget) findViewById(R.id.card_input_widget);

        Card cardToSave = mCardInputWidget.getCard();
        if (cardToSave == null) {
            mErrorDialogHandler.showError("Invalid Card Data");
        }



    }
}
