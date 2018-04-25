package com.softnobug.guigedialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.showbtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(v);
            }
        });
    }

    public void showDialog(View view){

        goodsChooseDialog chooseDialog = new goodsChooseDialog(MainActivity.this);
        chooseDialog.showAsDropDown(button);
    }

}
