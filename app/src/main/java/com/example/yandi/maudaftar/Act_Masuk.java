package com.example.yandi.maudaftar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Saya on 12/19/2016.
 */

public class Act_Masuk extends AppCompatActivity{

    TextView TVdaftar, TVLupaSandi;

    private String teks = "Belum punya akun? Daftar";
    private int i1 = teks.indexOf("?");
    private int i2 = teks.indexOf("r");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_masuk);

        TVdaftar = (TextView) findViewById(R.id.TVDaftar);
        TVLupaSandi = (TextView) findViewById(R.id.TVLupaSandi);

        TVdaftar.setMovementMethod(LinkMovementMethod.getInstance());
        TVdaftar.setText(teks, TextView.BufferType.SPANNABLE);
        Spannable mySpannable = (Spannable) TVdaftar.getText();
        ClickableSpan myClickSpan =  new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent i = new Intent(getApplicationContext(), Act_Daftar.class);
                startActivity(i);
            }
        };
        mySpannable.setSpan(myClickSpan, i1+2, i2+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        TVLupaSandi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast t = Toast.makeText(getApplicationContext(), "Belum Ada Konten", Toast.LENGTH_LONG);
                t.show();
            }
        });
    }
}
