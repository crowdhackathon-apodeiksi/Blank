package com.my.crowd.crowd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Leuteris on 14/6/2015.
 */

public class Login extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button btnsnd = (Button) findViewById(R.id.btnlogin);



        btnsnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Login.this, MainActivity.class);
                Login.this.startActivity(myIntent);
                Login.this.finish();
            }
        });

    }


}
