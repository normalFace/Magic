package com.sssstar.magic;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class webviewActivity extends AppCompatActivity {
    private Button load_by_url,js_interface,js_dialog;
    private Context mcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        mcontext = webviewActivity.this;

        load_by_url = (Button) findViewById(R.id.load_by_url);
        js_interface = (Button) findViewById(R.id.js_interface);
        js_dialog = (Button) findViewById(R.id.js_dialog);

        load_by_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext,load_by_url.class);
                startActivity(intent);
            }
        });

        js_interface.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext,JsWebview.class);
                startActivity(intent);
            }
        });

        js_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext,JsDialog.class);
                startActivity(intent);
            }
        });

    }


}
