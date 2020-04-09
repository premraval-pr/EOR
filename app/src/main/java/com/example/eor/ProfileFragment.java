package com.example.eor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {
    EditText Name,email,phone;
    TextView Edit_btn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inboxView = inflater.inflate(R.layout.fragment_profile,container,false);
        Name = inboxView.findViewById(R.id.__edittext_fname_profile);
        email = inboxView.findViewById(R.id.__edittext_email_profile);
        phone = inboxView.findViewById(R.id.edittext_phonenumber_profile);
        Edit_btn = inboxView.findViewById(R.id.edit_page_toggle);
        Name.setEnabled(false);
        email.setEnabled(false);
        phone.setEnabled(false);

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
