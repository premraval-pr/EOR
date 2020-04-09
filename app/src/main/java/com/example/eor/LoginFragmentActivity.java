package com.example.eor;


import android.content.Intent;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;

import java.util.Arrays;


public class LoginFragmentActivity extends Fragment implements GoogleApiClient.OnConnectionFailedListener {

    UserCredentials_DAO userCredentials_dao = new UserCredentials_DAO();
    private View __view_login;
    private Button __loginBtn;
    private TextView __userName,__password,__credError;
    private Intent __nextActivity;
    private Drawable err_indiactor;
    boolean Allfill = false;
    SignInButton __button_signInWithGoogle_loginfragment;
    LoginButton __button_signInWithFacebook_loginfragment;
    public GoogleApiClient mGoogleApiClient;
    Button __button_logoutGoogle;
    GoogleSignInAccount acct;
    CallbackManager callbackManager;
    private static final int RC_SIGN_IN = 1;
    private GoogleSignInClient mGoogleSignInClient;

    public LoginFragmentActivity() {

        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .enableAutoManage(getActivity() /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .addOnConnectionFailedListener(this)
                .build();
        mGoogleApiClient.connect();

        mGoogleSignInClient = GoogleSignIn.getClient(getContext(),gso);


    }


    private void initiallizeControls() {
        callbackManager = CallbackManager.Factory.create();

        __button_signInWithFacebook_loginfragment= __view_login.findViewById(R.id.__button_loginwithfacebook);
        __button_signInWithFacebook_loginfragment.setReadPermissions(Arrays.asList("public_profile", "user_birthday"));

        __button_signInWithGoogle_loginfragment = __view_login.findViewById(R.id.__button_loginwithgoogle);


    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        __view_login = inflater.inflate(R.layout.layout_signinfragment, container, false);
        __userName = __view_login.findViewById(R.id.__edittext_username);
        __password = __view_login.findViewById(R.id.__edittext_password_signin);
        __loginBtn = __view_login.findViewById(R.id.__button_login);
        err_indiactor = getResources().getDrawable(R.drawable.__errorindicator);
        __credError = __view_login.findViewById(R.id.__textview_credentialsError_signin);

        initiallizeControls();




        __button_signInWithGoogle_loginfragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent,1);
            }
        });



        __loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(__userName.getText().toString().isEmpty())
                {
                    __userName.setCompoundDrawablesWithIntrinsicBounds(null, null, err_indiactor, null);
                    __userName.setError("Username Empty",err_indiactor);
                    __userName.requestFocus();
                    Allfill = false;

                }
                else if(__password.getText().toString().isEmpty())
                {
                    __password.setCompoundDrawablesWithIntrinsicBounds(null, null, err_indiactor, null);
                    __password.setError("Password Empty",err_indiactor);
                    __password.requestFocus();
                    __userName.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                    Allfill = false;

                }
                else
                {

                    __password.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                    Allfill = true;

                }


                if(Allfill)
                {
                    userCredentials_dao.collectData();
                    String answer = userCredentials_dao.authenticateCredetials(__userName.getText().toString(),__password.getText().toString());

                    if (answer == "-1")
                    {
                        __credError.setText("Email and Password doesn't match");
                        __userName.setFocusable(true);
                        __password.setText("");
                    }
                    else
                    {
                        __credError.setText("");
                        Intent intent = new Intent(getContext(),SlidingDrawerActivity.class);
                        intent.putExtra("user_id",answer);
                        System.out.println("User Id: "+answer);
                        startActivity(intent);
                    }
                }


            }
        });

        __button_signInWithFacebook_loginfragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               __button_signInWithFacebook_loginfragment.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                   @Override
                   public void onSuccess(LoginResult loginResult) {
                       Toast.makeText(getContext(),"Facebook Logged In with "+loginResult.getAccessToken().getUserId(),Toast.LENGTH_SHORT).show();
                   }

                   @Override
                   public void onCancel() {

                   }

                   @Override
                   public void onError(FacebookException error) {

                   }
               });

            }
        });








        return __view_login;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void updateUI(boolean signedIn) {
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

            if(acct.getAccount() != null) {
                System.out.println(acct.getEmail());
                System.out.println(acct.getDisplayName());

                GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getContext());
                if(account!=null)
                {
                    Intent nextactivity = new Intent(getActivity(), SlidingDrawerActivity.class);
                    nextactivity.putExtra("name", acct.getDisplayName());
                    nextactivity.putExtra("email", acct.getEmail());
                    startActivity(nextactivity);
                    Toast.makeText(getContext(), "Logged In: " + acct.getEmail(), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent nextactivity = new Intent(getActivity(), HomeFragment.class);
                    startActivity(nextactivity);
                }

            }

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


}