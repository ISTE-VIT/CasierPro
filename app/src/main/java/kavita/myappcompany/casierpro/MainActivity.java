package kavita.myappcompany.casierpro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public Fragment fragmentLogin;
    public Fragment fragmentNewAccount;
    public FragmentActivity fragmentScan;
    public static SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentLogin = new Login();
        fragmentNewAccount = new SignUp();
        sp = getSharedPreferences("login",MODE_PRIVATE);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(getIntent().getIntExtra("secret",1)==0) {
            fragmentTransaction.replace(R.id.bodyFragment, new SecretFragment()).commit();
            getIntent().putExtra("secret",1);
        }
        else{
            fragmentTransaction.replace(R.id.bodyFragment, fragmentLogin).commit();
        }
    }
}