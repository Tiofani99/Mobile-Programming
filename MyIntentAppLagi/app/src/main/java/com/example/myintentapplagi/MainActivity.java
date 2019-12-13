package com.example.myintentapplagi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnMoveActivity,btnMoveWithData,btnMoveWithObject,btnDialNumber,btnMoveForResult;
    private TextView tvResult;
    private int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMoveActivity = findViewById(R.id.btn_move_activity);
        btnMoveWithData = findViewById(R.id.btn_move_with_data);
        btnMoveWithObject = findViewById(R.id.btn_move_with_object);
        btnDialNumber = findViewById(R.id.btn_dial_number);
        btnMoveForResult = findViewById(R.id.btn_move_for_result);
        tvResult = findViewById(R.id.tv_result);


        btnMoveWithData.setOnClickListener(this);
        btnMoveActivity.setOnClickListener(this);
        btnMoveWithObject.setOnClickListener(this);
        btnDialNumber.setOnClickListener(this);
        btnMoveForResult.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_move_activity:
                Intent intent = new Intent(this,MoveActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_move_with_data:
                Intent moveWithData = new Intent(this,MoveWithDataActivity.class);
                moveWithData.putExtra(MoveWithDataActivity.EXTRA_NAME,"Tio Fani");
                moveWithData.putExtra(MoveWithDataActivity.EXTRA_AGE,20);
                startActivity(moveWithData);
                break;

            case R.id.btn_move_with_object:
                Person person = new Person();
                person.setName("Tio Fani");
                person.setAge(20);
                person.setCity("Purwokerto");
                person.setEmail("tiofani03@gmail.com");

                Intent moveWithObject  = new Intent(this,MoveWithObjectActivity.class);
                moveWithObject.putExtra(MoveWithObjectActivity.EXTRA_PERSON,person);
                startActivity(moveWithObject);
                break;

            case R.id.btn_dial_number:
                String phoneNumber = "082137138344";
                Intent dial = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneNumber));
                startActivity(dial);
                break;

            case R.id.btn_move_for_result:
                Intent moveResult = new Intent(this,MoveForResultActivity.class);
                startActivityForResult(moveResult,REQUEST_CODE);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE){
            if(resultCode == MoveForResultActivity.RESULT_CODE){
                int selectedValue = data.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE,0);
                tvResult.setText(String.format("Hasil : %s",selectedValue));
            }
        }
    }
}
