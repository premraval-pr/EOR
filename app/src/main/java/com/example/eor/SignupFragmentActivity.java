package com.example.eor;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;


public class SignupFragmentActivity extends Fragment {

    View __view_signupinflator;
    Button signupbutton;
    Drawable err_indiactor;
    EditText firstname,lastname,email,contactno,displayname,pw,cpw;
    boolean s_firstname,s_lastname,s_email,s_address,s_contactno,s_displayname,s_pw,s_cpw;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    AutoCompleteTextView autoCompleteTextViewAddress;

    public SignupFragmentActivity() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        __view_signupinflator=inflater.inflate(R.layout.layout_signupfragment, container, false);

        EditText phone = __view_signupinflator.findViewById(R.id.__edittext_contact);
        phone.addTextChangedListener(new PhoneNumberFormattingTextWatcher("CA"));
        signupbutton = __view_signupinflator.findViewById(R.id.__button_register);
        err_indiactor = getResources().getDrawable(R.drawable.__errorindicator);
        autoCompleteTextViewAddress = __view_signupinflator.findViewById(R.id.__autocomplete_address);
        firstname = __view_signupinflator.findViewById(R.id.__edittext_firstname);
        lastname = __view_signupinflator.findViewById(R.id.__edittext_lastname);
        email = __view_signupinflator.findViewById(R.id.__edittext_email);
        contactno = __view_signupinflator.findViewById(R.id.__edittext_contact);
        displayname = __view_signupinflator.findViewById(R.id.__edittext_displayname);
        autoCompleteTextViewAddress.setAdapter(new PlaceAutoSuggestAdapter(getContext(),android.R.layout.simple_list_item_1));
        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s_firstname = firstname.getText().toString().isEmpty();
                s_lastname = lastname.getText().toString().isEmpty();
                s_email = email.getText().toString().isEmpty();
                s_address = autoCompleteTextViewAddress.getText().toString().isEmpty();

                s_contactno = contactno.getText().toString().isEmpty();

                s_displayname = displayname.getText().toString().isEmpty();
                pw = __view_signupinflator.findViewById(R.id.__edittext_password_signup);
                s_pw = pw.getText().toString().isEmpty();
                cpw = __view_signupinflator.findViewById(R.id.__edittext_confirmpassword_signup);
                s_cpw = cpw.getText().toString().isEmpty();

                if(firstname.getText().toString().isEmpty())
                {
                    firstname.setCompoundDrawablesWithIntrinsicBounds(null, null, err_indiactor, null);
                    firstname.setError("First Name Empty",err_indiactor);
                    firstname.requestFocus();

                }
                else if(lastname.getText().toString().isEmpty())
                {
                    lastname.setCompoundDrawablesWithIntrinsicBounds(null, null, err_indiactor, null);
                    lastname.setError("Last Name Empty",err_indiactor);
                    firstname.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                    lastname.requestFocus();
                }
                else if(email.getText().toString().isEmpty()  ||  !email.getText().toString().matches(emailPattern))
                {
                    email.setCompoundDrawablesWithIntrinsicBounds(null, null, err_indiactor, null);
                    email.setError("Email Not Valid",err_indiactor);
                    lastname.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                    email.requestFocus();
                }
                else if(autoCompleteTextViewAddress.getText().toString().isEmpty())
                {
                    autoCompleteTextViewAddress.setCompoundDrawablesWithIntrinsicBounds(null, null, err_indiactor, null);
                    autoCompleteTextViewAddress.setError("Address Empty",err_indiactor);
                    email.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                    autoCompleteTextViewAddress.requestFocus();
                }
                else if(contactno.getText().toString().isEmpty())
                {
                    contactno.setCompoundDrawablesWithIntrinsicBounds(null, null, err_indiactor, null);
                    contactno.setError("Contact Number Empty",err_indiactor);
                    autoCompleteTextViewAddress.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                    contactno.requestFocus();
                }
                else if(displayname.getText().toString().isEmpty())
                {
                    displayname.setCompoundDrawablesWithIntrinsicBounds(null, null, err_indiactor, null);
                    displayname.setError("Display Name Empty",err_indiactor);
                    contactno.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                    displayname.requestFocus();
                }
                else if(pw.getText().toString().isEmpty())
                {
                    pw.setCompoundDrawablesWithIntrinsicBounds(null, null, err_indiactor, null);
                    pw.setError("Password Empty",err_indiactor);
                    displayname.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                    pw.requestFocus();
                }

                else if(cpw.getText().toString().isEmpty())
                {
                    cpw.setCompoundDrawablesWithIntrinsicBounds(null, null, err_indiactor, null);
                    cpw.setError("Confirm Password Empty",err_indiactor);
                    pw.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                    cpw.requestFocus();
                }
                else
                {
                    removeErrorLogoFromAll();
                }

                if(pw.getText().toString().trim().equals(cpw.getText().toString().trim())==false)
                {
                    pw.setCompoundDrawablesWithIntrinsicBounds(null, null, err_indiactor, null);
                    pw.setError("Password and Confirm Password doesn't match",err_indiactor);
                    pw.requestFocus();
                }
                else
                {

                    if(!s_firstname && !s_lastname && !s_email && !s_contactno && !s_displayname && !s_address && !s_pw && !s_cpw && email.getText().toString().matches(emailPattern))
                    {
                        removeErrorLogoFromAll();
                        Intent __nextActivity = new Intent(getContext(),SlidingDrawerActivity.class);
                        startActivity(__nextActivity);
                    }
                }



            }

            private void removeErrorLogoFromAll() {
                firstname.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                lastname.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                email.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                autoCompleteTextViewAddress.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                contactno.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                displayname.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                pw.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                cpw.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
            }
        });
        return __view_signupinflator;
    }
}