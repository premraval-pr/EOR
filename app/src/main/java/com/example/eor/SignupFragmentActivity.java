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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;

import org.json.JSONObject;

import java.util.Arrays;


public class SignupFragmentActivity extends Fragment implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks{

    View __view_signupinflator;
    Button signupbutton;
    Drawable err_indiactor;
    EditText firstname,lastname,email,contactno,displayname,pw,cpw;
    boolean s_firstname,s_lastname,s_email,s_address,s_contactno,s_displayname,s_pw,s_cpw;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    AutoCompleteTextView autoCompleteTextViewAddress;
    SignInButton __button_signInWithGoogle_loginfragment;
    LoginButton __button_signInWithFacebook_loginfragment;
    public GoogleApiClient mGoogleApiClient;
    Button __button_logoutGoogle;
    GoogleSignInAccount acct;
    public static CallbackManager callbackManager;
    private static final int RC_SIGN_IN = 1;

    public SignupFragmentActivity() {

        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .addOnConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) this)
                .build();
        mGoogleApiClient.connect();
    }

    private void initiallizeControls() {
        callbackManager = CallbackManager.Factory.create();
        // txtStatus = (TextView)findViewById(R.id.txtstatus);
        __button_signInWithFacebook_loginfragment= __view_signupinflator.findViewById(R.id.__button_loginwithfacebook);
        __button_signInWithFacebook_loginfragment.setReadPermissions(Arrays.asList("public_profile", "user_birthday"));
        __button_signInWithFacebook_loginfragment.setFragment(this);


    }

    private void loginWithFb() {
        __button_signInWithFacebook_loginfragment.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {



                Toast.makeText(getContext(), "Login Success with facebook", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {


            }

            @Override
            public void onError(FacebookException error) {

            }
        });

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        __view_signupinflator=inflater.inflate(R.layout.layout_signupfragment, container, false);

        __button_signInWithGoogle_loginfragment = __view_signupinflator.findViewById(R.id.__button_loginwithgoogle);

        __button_signInWithGoogle_loginfragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent,1);
            }
        });

        initiallizeControls();

        EditText phone = __view_signupinflator.findViewById(R.id.__edittext_contact);
        phone.addTextChangedListener(new PhoneNumberFormattingTextWatcher("CA"));
        signupbutton = __view_signupinflator.findViewById(R.id.__button_next);
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
                else
                {
                    removeErrorLogoFromAll();
                }


                    if(!s_firstname && !s_lastname && !s_email && !s_contactno && !s_displayname && !s_address  && email.getText().toString().matches(emailPattern))
                    {
                        removeErrorLogoFromAll();
                        Intent i = new Intent(getContext(),ExtraCredentialsRegisterPage.class);
                        startActivity(i);
                    }




            }

            private void removeErrorLogoFromAll() {
                firstname.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                lastname.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                email.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                autoCompleteTextViewAddress.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                contactno.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
            }
        });



        __button_signInWithFacebook_loginfragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginWithFb();

            }
        });


        return __view_signupinflator;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
            mGoogleApiClient.connect();
        }

    }
    private void handleSignInResult(GoogleSignInResult result) {


        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            acct = result.getSignInAccount();
            //  mStatusTextView.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));
            //Similarly you can get the email and photourl using acct.getEmail() and  acct.getPhotoUrl()

            if(acct.getAccount() != null)
                System.out.println(acct.getEmail());
            System.out.println(acct.getDisplayName());
            Intent nextactivity = new Intent(getActivity(),ExtraCredentialsRegisterPage.class);
            nextactivity.putExtra("name",acct.getDisplayName());
            nextactivity.putExtra("email",acct.getEmail());
            startActivity(nextactivity);
            Toast.makeText(getContext(),"Logged In: "+acct.getEmail(),Toast.LENGTH_SHORT).show();

            // updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
            //updateUI(false);
            Toast.makeText(getContext(),"No Accounts Signed in",Toast.LENGTH_LONG).show();
        }
    }




    @Override
    public void onStart() {
        super.onStart();
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {


            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {

                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    private void updateUI(boolean signedIn) {
        if (signedIn) {
            __button_signInWithGoogle_loginfragment.setVisibility(View.GONE);
            __button_logoutGoogle.setVisibility(View.VISIBLE);
        } else {
            //mStatusTextView.setText(R.string.signed_out);
            //   Bitmap icon =                  BitmapFactory.decodeResource(getContext().getResources(),R.drawable.user_defaolt);
            //  imgProfilePic.setImageBitmap(ImageHelper.getRoundedCornerBitmap(getContext(),icon, 200, 200, 200, false, false, false, false));
            __button_signInWithGoogle_loginfragment.setVisibility(View.VISIBLE);
            __button_logoutGoogle.setVisibility(View.GONE);
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}