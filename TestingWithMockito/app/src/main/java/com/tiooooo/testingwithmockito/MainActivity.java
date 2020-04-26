package com.tiooooo.testingwithmockito;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MainViewModel mainViewModel;
    @BindView(R.id.edt_width)
    EditText edtWidth;
    @BindView(R.id.edt_height)
    EditText edtHeight;
    @BindView(R.id.edt_length)
    EditText edtLength;
    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.btn_calculate_volume)
    Button btnVolume;
    @BindView(R.id.btn_calculate_surface_area)
    Button btnSurfaceArea;
    @BindView(R.id.btn_calculate_circumference)
    Button btnCircumference;
    @BindView(R.id.btn_save)
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainViewModel = new MainViewModel(new CuboidModel());

        btnVolume.setOnClickListener(this);
        btnCircumference.setOnClickListener(this);
        btnSurfaceArea.setOnClickListener(this);
        btnSave.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        String length = edtLength.getText().toString().trim();
        String width = edtWidth.getText().toString().trim();
        String height = edtHeight.getText().toString().trim();
        if (TextUtils.isEmpty(length)) {
            edtLength.setError("Field ini tidak boleh kosong");
        } else if (TextUtils.isEmpty(width)) {
            edtWidth.setError("Field ini tidak boleh kosong");
        } else if (TextUtils.isEmpty(height)) {
            edtHeight.setError("Field ini tidak boleh kosong");
        } else {
            double l = Double.parseDouble(length);
            double w = Double.parseDouble(width);
            double h = Double.parseDouble(height);
            if (v.getId() == R.id.btn_save) {
                mainViewModel.save(l, w, h);
                visible();
            } else if (v.getId() == R.id.btn_calculate_circumference) {
                tvResult.setText(String.valueOf(mainViewModel.getCircumference()));
                gone();
            } else if (v.getId() == R.id.btn_calculate_surface_area) {
                tvResult.setText(String.valueOf(mainViewModel.getSurfaceArea()));
                gone();
            } else if (v.getId() == R.id.btn_calculate_volume) {
                tvResult.setText(String.valueOf(mainViewModel.getVolume()));
                gone();
            }
        }
    }


    private void visible() {
        btnVolume.setVisibility(View.VISIBLE);
        btnCircumference.setVisibility(View.VISIBLE);
        btnSurfaceArea.setVisibility(View.VISIBLE);
        btnSave.setVisibility(View.GONE);
    }

    private void gone() {
        btnVolume.setVisibility(View.GONE);
        btnCircumference.setVisibility(View.GONE);
        btnSurfaceArea.setVisibility(View.GONE);
        btnSave.setVisibility(View.VISIBLE);
    }
}
