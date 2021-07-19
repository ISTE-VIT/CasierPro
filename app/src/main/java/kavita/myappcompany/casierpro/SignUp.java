package kavita.myappcompany.casierpro;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class SignUp extends Fragment {

    View view;
    private EditText email_createAcc, pwd_createAcc, retype_pwd_createAcc;
    private Button signUp;
    private MainActivity mainActivity;
    private FirebaseAuth mAuth;

    public SignUp() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        email_createAcc = view.findViewById(R.id.email_signup);
        pwd_createAcc = view.findViewById(R.id.typePWD);
        retype_pwd_createAcc = view.findViewById(R.id.retypePWD);
        mAuth = FirebaseAuth.getInstance();
        mainActivity = (MainActivity) getActivity();
        signUp = view.findViewById(R.id.signup);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailText = email_createAcc.getText().toString();
                String password = pwd_createAcc.getText().toString();
                String password1 = retype_pwd_createAcc.getText().toString();


                if(email_createAcc.getText().toString().isEmpty()) {
                    email_createAcc.setError("Email Required");
                    email_createAcc.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email_createAcc.getText().toString()).matches()) {
                    email_createAcc.setError("Valid Email Required");
                    email_createAcc.requestFocus();
                    return;
                }
                if(pwd_createAcc.getText().toString().isEmpty()) {
                    pwd_createAcc.setError("Password Required");
                    pwd_createAcc.requestFocus();
                    return;
                }
                if(pwd_createAcc.getText().toString().length()<6) {
                    pwd_createAcc.setError("Min 6 char required");
                    pwd_createAcc.requestFocus();
                    return;
                }
                if(retype_pwd_createAcc.getText().toString().isEmpty() || !retype_pwd_createAcc.getText().toString().equals(pwd_createAcc.getText().toString())) {
                   retype_pwd_createAcc.setError("Password does not matches !!");
                    retype_pwd_createAcc.requestFocus();
                    return;
                }

                registerUser(emailText,password);


            }
        });


        /*public void registerUser(String email, String password){

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {

                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                            user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getContext(), "Please verify email to continue", Toast.LENGTH_SHORT).show();
                                    FragmentManager fragmentManager = mainActivity.getSupportFragmentManager();
                                    Bundle args = new Bundle();
                                    args.putString("email", email_createAcc.getText().toString());
                                    mainActivity.fragmentGettingStarted.setArguments(args);
                                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                    fragmentTransaction.replace(R.id.bodyFragment, mainActivity.fragmentGettingStarted)
                                            .addToBackStack(null)
                                            .commit();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.i("failure", "Email not sent" + e.getMessage());
                                }
                            });

//                        //SignUp success
//                        Intent intent = new Intent(SignUpActivity.this,HomeActivity.class);
//                        intent.putExtra("Email",email);
//                        startActivity(intent);
//                        finish();
                        } else {
                            Toast.makeText(getContext(), "Email ID already exists..", Toast.LENGTH_SHORT).show();
                        }
                    });*/
        }


    private void registerUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                        user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getContext(), "Please verify email to continue", Toast.LENGTH_SHORT).show();
                                FragmentManager fragmentManager = mainActivity.getSupportFragmentManager();
                                Bundle args = new Bundle();
                                args.putString("email", email_createAcc.getText().toString());
                                mainActivity.fragmentLogin.setArguments(args);
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.replace(R.id.bodyFragment, mainActivity.fragmentLogin)
                                        .addToBackStack(null)
                                        .commit();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.i("failure", "Email not sent" + e.getMessage());
                            }
                        });

//                        //SignUp success
//                        Intent intent = new Intent(SignUpActivity.this,HomeActivity.class);
//                        intent.putExtra("Email",email);
//                        startActivity(intent);
//                        finish();
                    } else {
                        Toast.makeText(getContext(), "Email ID already exists..", Toast.LENGTH_SHORT).show();
                    }
                });

    }

}




