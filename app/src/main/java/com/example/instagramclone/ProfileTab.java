package com.example.instagramclone;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment implements View.OnClickListener{
    private Button editProfile;
    private TextView txt_name,txt_bio,txt_professio,txt_hobbies,txt_sport;


    public ProfileTab() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_profile_tab, container, false);

       editProfile=view.findViewById(R.id.btnEditProfile);
       txt_name=view.findViewById(R.id.text_name);
       txt_bio=view.findViewById(R.id.text_bio);
       txt_professio=view.findViewById(R.id.text_profession);
       txt_hobbies=view.findViewById(R.id.text_hobbies);
       txt_sport=view.findViewById(R.id.text_sport);

       ParseUser user=ParseUser.getCurrentUser();

        txt_name.setText("Name: "+ user.get("ProfileName")+"");
        txt_bio.setText("Bio: "+ user.get("ProfileBio")+"");
        txt_professio.setText("Profession: "+user.get("ProfileProfession")+"");
        txt_hobbies.setText("Hobbies: "+user.get("ProfileHobbies")+"");
        txt_sport.setText("Sport: "+user.get("ProfileSport")+"");

        editProfile.setOnClickListener(this);



       return view;

    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getActivity(),EditProfileActivity.class);
        startActivity(intent);
    }
}
