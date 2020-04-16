package com.example.eor.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.eor.R;
import com.example.eor.activity.SlidingDrawerActivity;
import com.example.eor.model.UserCredentials_Model;

public class ProfileFragment extends Fragment {
    private EditText Name,email,phone,location;
    private TextView Edit_btn,Card_Name;
    private UserCredentials_Model loggedUser;
    private ImageView profile_image;
    private View inboxView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inboxView = inflater.inflate(R.layout.fragment_profile,container,false);
        Name = inboxView.findViewById(R.id.__edittext_fname_profile);
        email = inboxView.findViewById(R.id.__edittext_email_profile);
        phone = inboxView.findViewById(R.id.edittext_phonenumber_profile);
        Edit_btn = inboxView.findViewById(R.id.edit_page_toggle);
        location = inboxView.findViewById(R.id.__edittext_location_profile);
        Card_Name = inboxView.findViewById(R.id.__textview_cardholder);
        profile_image = inboxView.findViewById(R.id.__imageview_ProfileImage_profile);

        Name.setEnabled(false);
        email.setEnabled(false);
        phone.setEnabled(false);
        loggedUser = SlidingDrawerActivity.loggedUser;


        String NameFormat = loggedUser.getUser_fname() + " " + loggedUser.getUser_lname();

        Name.setText(NameFormat);
        email.setText(loggedUser.getUser_email());

        if(loggedUser.getUser_email().equals("premraval.pr@gmail.com")) profile_image.setImageResource(R.drawable.prem_1);

        String ContactFormat = "(" + loggedUser.getUser_contact().substring(0,3) + ") "
                + loggedUser.getUser_contact().substring(3,6) + "-"
                + loggedUser.getUser_contact().substring(6);
        phone.setText(ContactFormat);
        location.setText(loggedUser.getUser_city());
        Card_Name.setText(NameFormat);

        Edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Edit_btn.getText().toString().equals("EDIT"))
                {
                    Name.setEnabled(true);
                    email.setEnabled(true);
                    phone.setEnabled(true);
                    Toast.makeText(getContext(),"Toggle checked",Toast.LENGTH_LONG).show();
                    Edit_btn.setText("SAVE");
                }
                else
                {
                    Name.setEnabled(false);
                    email.setEnabled(false);
                    phone.setEnabled(false);
                    Toast.makeText(getContext(),"Toggle unchecked",Toast.LENGTH_LONG).show();
                }
            }
        });

        return inboxView;




    }
}
