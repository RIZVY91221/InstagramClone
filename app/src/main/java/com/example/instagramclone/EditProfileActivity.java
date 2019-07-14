package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class EditProfileActivity extends AppCompatActivity
{
    private EditText edtProfile_Name,edtProfile_Bio,edtProfile_Profession,edtProfile_Hobbies,edtProfile_Sport;
    private Button btn_UpdateInfo;
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        edtProfile_Name=findViewById(R.id.editNameprofile);
        edtProfile_Bio=findViewById(R.id.edtprofile_Bio);
        edtProfile_Profession=findViewById(R.id.edtprofile_Profession);
        edtProfile_Hobbies=findViewById(R.id.edtprofile_Hobbies);
        edtProfile_Sport=findViewById(R.id.edtprofile_sport);

        btn_UpdateInfo=findViewById(R.id.btn_update_profile);
        final ParseUser parseUser=ParseUser.getCurrentUser();

        btn_UpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseUser.put("ProfileName",edtProfile_Name.getText().toString());
                parseUser.put("ProfileBio",edtProfile_Bio.getText().toString());
                parseUser.put("ProfileProfession",edtProfile_Profession.getText().toString());
                parseUser.put("ProfileHobbies",edtProfile_Hobbies.getText().toString());
                parseUser.put("ProfileSport",edtProfile_Sport.getText().toString());

                dialog = new ProgressDialog(EditProfileActivity.this);
                dialog.setMessage("Updating Profile...");
                dialog.show();

                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e==null){
                            FancyToast.makeText(getApplicationContext(), "Info Update Successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                            Intent intent=new Intent(EditProfileActivity.this,ProfileTab.class);
                            startActivity(intent);
                            dialog.dismiss();
                        }
                        else{
                            FancyToast.makeText(getApplicationContext(), e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }
                        dialog.dismiss();
                    }
                });
            }
        });
    }
}
