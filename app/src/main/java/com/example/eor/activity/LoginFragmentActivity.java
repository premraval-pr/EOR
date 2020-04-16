package com.example.eor.activity;


import android.Manifest;
import android.app.Dialog;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.eor.api.FingerprintHandler;
import com.example.eor.fragment.HomeFragment;
import com.example.eor.R;
import com.example.eor.dao.UserCredentials_DAO;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
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

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.Objects;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;


public class LoginFragmentActivity extends Fragment implements GoogleApiClient.OnConnectionFailedListener {

    UserCredentials_DAO userCredentials_dao = new UserCredentials_DAO();
    private View __view_login;
    private Button __loginBtn;
    private EditText __userName, __password;
    private TextView __credError;
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
    private Dialog biometricDialog;
    private static final String KEY_NAME = "yourKey";
    private Cipher cipher;
    private KeyStore keyStore;
    private KeyGenerator keyGenerator;
    private FingerprintManager.CryptoObject cryptoObject;
    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;

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
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .addOnConnectionFailedListener(this)
                .build();
        mGoogleApiClient.connect();

        mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);


    }


    private void initiallizeControls() {
        callbackManager = CallbackManager.Factory.create();

        __button_signInWithFacebook_loginfragment = __view_login.findViewById(R.id.__button_loginwithfacebook);
        __button_signInWithFacebook_loginfragment.setReadPermissions(Arrays.asList("public_profile", "user_birthday"));
        __button_signInWithFacebook_loginfragment.setFragment(this);

        __button_signInWithGoogle_loginfragment = __view_login.findViewById(R.id.__button_loginwithgoogle);


    }


    @Override
    public void onPause() {
        super.onPause();
        biometricDialog.dismiss();
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

        biometricDialog = new Dialog(Objects.requireNonNull(getContext()));
        Objects.requireNonNull(biometricDialog.getWindow()).requestFeature(Window.FEATURE_NO_TITLE);
        biometricDialog.setContentView(R.layout.biometric);
        initiallizeControls();
        __button_signInWithGoogle_loginfragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, 1);
            }
        });

        __userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (__userName.getText().toString().isEmpty() && __password.getText().toString().isEmpty()) {
                    __loginBtn.setText("Biometric");
                } else {
                    __loginBtn.setText("Login");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        __password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (__userName.getText().toString().isEmpty() && __password.getText().toString().isEmpty()) {
                    __loginBtn.setText("Biometric");
                } else {
                    __loginBtn.setText("Login");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        __loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (__userName.getText().toString().isEmpty() && __password.getText().toString().isEmpty()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        keyguardManager =
                                (KeyguardManager) getActivity().getSystemService(Context.KEYGUARD_SERVICE);
                        fingerprintManager =
                                (FingerprintManager) getActivity().getSystemService(Context.FINGERPRINT_SERVICE);

                        if (!fingerprintManager.isHardwareDetected()) {
                            Toast.makeText(getContext(), "Your device doesn't support fingerprint authentication", Toast.LENGTH_SHORT).show();
                        }

                        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(getContext(), "Please enable the fingerprint permission", Toast.LENGTH_SHORT).show();
                        }

                        if (!fingerprintManager.hasEnrolledFingerprints()) {
                            Toast.makeText(getContext(), "No fingerprint configured. Please register at least one fingerprint in your device's Settings", Toast.LENGTH_SHORT).show();
                        }

                        if (!keyguardManager.isKeyguardSecure()) {
                            Toast.makeText(getContext(), "Please enable lockscreen security in your device's Settings", Toast.LENGTH_SHORT).show();
                        } else {
                            try {
                                generateKey();
                            } catch (LoginFragmentActivity.FingerprintException e) {
                                e.printStackTrace();
                            }

                            if (initCipher()) {
                                biometricDialog.show();
                                cryptoObject = new FingerprintManager.CryptoObject(cipher);
                                FingerprintHandler helper = new FingerprintHandler(getContext());
                                helper.startAuth(fingerprintManager, cryptoObject);
                            }
                        }
                    }
                }
                if (__userName.getText().toString().isEmpty()) {
                    __userName.setCompoundDrawablesWithIntrinsicBounds(null, null, err_indiactor, null);
                    __userName.setError("Username Empty", err_indiactor);
                    __userName.requestFocus();
                    Allfill = false;

                } else if (__password.getText().toString().isEmpty()) {
                    __password.setCompoundDrawablesWithIntrinsicBounds(null, null, err_indiactor, null);
                    __password.setError("Password Empty", err_indiactor);
                    __password.requestFocus();
                    __userName.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                    Allfill = false;

                } else {

                    __password.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                    Allfill = true;

                }


                if (Allfill) {
                    userCredentials_dao.collectData();
                    String answer = userCredentials_dao.authenticateCredetials(__userName.getText().toString(), __password.getText().toString());

                    if (answer == "-1") {
                        __credError.setText("Email and Password doesn't match");
                        __userName.setFocusable(true);
                        __password.setText("");
                    } else {
                        __credError.setText("");
                        Intent intent = new Intent(getContext(), SlidingDrawerActivity.class);
                        intent.putExtra("user_id", answer);

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
                        Toast.makeText(getContext(), "Facebook Logged In with " + loginResult.getAccessToken().getUserId(), Toast.LENGTH_SHORT).show();

                        Intent nextactivity = new Intent(getActivity(), SlidingDrawerActivity.class);
                        startActivity(nextactivity);
                        Toast.makeText(getContext(), "Logged In: " +loginResult.getAccessToken().getUserId(), Toast.LENGTH_SHORT).show();
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

            if (acct.getAccount() != null) {
                GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getContext());
                if (account != null) {
                    Intent nextactivity = new Intent(getActivity(), SlidingDrawerActivity.class);
                    nextactivity.putExtra("name", acct.getDisplayName());
                    nextactivity.putExtra("email", acct.getEmail());
                    startActivity(nextactivity);
                    Toast.makeText(getContext(), "Logged In: " + acct.getEmail(), Toast.LENGTH_SHORT).show();
                } else {
                    Intent nextactivity = new Intent(getActivity(), HomeFragment.class);
                    startActivity(nextactivity);
                }

            }
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void generateKey() throws FingerprintException {
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");

            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");

            keyStore.load(null);

            keyGenerator.init(new

                    KeyGenParameterSpec.Builder(KEY_NAME,
                    KeyProperties.PURPOSE_ENCRYPT |
                            KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)

                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(
                            KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());

            keyGenerator.generateKey();

        } catch (KeyStoreException
                | NoSuchAlgorithmException
                | NoSuchProviderException
                | InvalidAlgorithmParameterException
                | CertificateException
                | IOException exc) {
            exc.printStackTrace();
            throw new FingerprintException(exc);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean initCipher() {
        try {
            cipher = Cipher.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES + "/"
                            + KeyProperties.BLOCK_MODE_CBC + "/"
                            + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        } catch (NoSuchAlgorithmException |
                NoSuchPaddingException e) {
            throw new RuntimeException("Failed to get Cipher", e);
        }

        try {
            keyStore.load(null);
            SecretKey key = (SecretKey) keyStore.getKey(KEY_NAME,
                    null);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return true;
        } catch (KeyPermanentlyInvalidatedException e) {

            //Return false if cipher initialization failed//
            return false;
        } catch (KeyStoreException | CertificateException
                | UnrecoverableKeyException | IOException
                | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Failed to init Cipher", e);
        }
    }

    private static class FingerprintException extends Exception {
        FingerprintException(Exception e) {
            super(e);
        }
    }

}