package kavita.myappcompany.casierpro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.Context.MODE_PRIVATE;
import static kavita.myappcompany.casierpro.MainActivity.sp;

/**
 * A simple {@link Fragment} subclass.
 * <p>
 * create an instance of this fragment.
 */
public class Login extends Fragment {
    View view;
    private EditText email_login, pwd_login;
    private Button login;
    private TextView createAcc, forgetPwd;
    private MainActivity mainActivity;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    Login activity;
    public boolean secret = true;
    public boolean flag = false;

    public String logged;
    public Login() {

        // Required empty public constructor
    }

   /* public void onStart() {
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {

           if(flag){
               Intent i = new Intent(getActivity(), Scan_code.class);
               startActivity(i);
           }

        }
        super.onStart();
    }*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


            view = inflater.inflate(R.layout.fragment_login, container, false);
            return view;

     }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        createAcc = view.findViewById(R.id.createNewAcc);
        email_login = view.findViewById(R.id.email_login);
        pwd_login = view.findViewById(R.id.pwd_login);
        mAuth = FirebaseAuth.getInstance();
        mainActivity = (MainActivity) getActivity();
        login = view.findViewById(R.id.login);
        progressBar = view.findViewById(R.id.load_login);
        if(sp.getBoolean("logged",false)){
            Intent i = new Intent(getActivity(), Scan_code.class);
            startActivity(i);
        }
        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = mainActivity.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.bodyFragment, mainActivity.fragmentNewAccount)
                        .addToBackStack(null)
                        .commit();

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailText = email_login.getText().toString();
                String passWord = pwd_login.getText().toString();

                if (email_login.getText().toString().isEmpty()) {
                    email_login.setError("Email Required");
                    email_login.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email_login.getText().toString()).matches()) {
                    email_login.setError("Valid Email Required");
                    email_login.requestFocus();
                    return;
                }
                if (pwd_login.getText().toString().isEmpty()) {
                    pwd_login.setError("Password Required");
                    pwd_login.requestFocus();
                    return;
                }
                if (pwd_login.getText().toString().length() < 6) {
                    pwd_login.setError("Min 6 char required");
                    pwd_login.requestFocus();
                    return;
                }

                User(emailText, passWord);


            }
        });


    }


    public void User(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {

                    if (!task.isSuccessful()) {
                        Toast.makeText(getContext(), "Invalid Email / Password", Toast.LENGTH_SHORT).show();

                    } else {

                        VerificationCheck(email);
                    }
                });
    }


    private void VerificationCheck(String email) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        assert user != null;
        if (user.isEmailVerified()) {
            sp.edit().putBoolean("logged",true).apply();
            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.putExtra("Email", email);
            //startActivity(intent);
            //mainActivity.finish();
            Toast.makeText(getContext(), "Successfully logged in", Toast.LENGTH_SHORT).show();
//flag=true;
            /*Intent i = new Intent(getActivity(), Scan_code.class);
            startActivity(i);*/
            if (sp.getBoolean("secret",false)) {
                sp.edit().putBoolean("secret",true).apply();
           // if(secret){
                FragmentManager fragmentManager = mainActivity.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.bodyFragment, new SecretFragment())
                        .addToBackStack(null)
                        .commit();
                //secret=false;
            } else {
                Intent i = new Intent(getActivity(), Scan_code.class);
                startActivity(i);
            }

        } else {
            // email is not verified, so just prompt the message to the user and restart this activity.
            // NOTE: don't forget to log out the user.
            Toast.makeText(getContext(), "Please Verify your Email to continue", Toast.LENGTH_SHORT).show();
            FirebaseAuth.getInstance().signOut();

            //restart this activity

        }
    }
}