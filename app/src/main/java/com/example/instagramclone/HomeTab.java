package com.example.instagramclone;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.shashank.sony.fancytoastlib.FancyToast;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeTab extends Fragment implements View.OnClickListener
{
    private ImageView imageView;
    private EditText edt_Description;
    private Button btn_Share;


    public HomeTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home_tab, container, false);
        imageView=view.findViewById(R.id.imgShare);
        edt_Description=view.findViewById(R.id.edt_description);
        btn_Share=view.findViewById(R.id.btnShare);
        imageView.setOnClickListener(this);
        btn_Share.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgShare:
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED){
                    requestPermissions(new String[]
                            {Manifest.permission.READ_EXTERNAL_STORAGE},1000);
                }else {
                    getChosePicture();
                }
                break;
            case R.id.btnShare:
                break;
        }
    }

    private void getChosePicture() {

        FancyToast.makeText(getContext(),"Allow to Access", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==1000){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getChosePicture();
            }
        }
    }
}
