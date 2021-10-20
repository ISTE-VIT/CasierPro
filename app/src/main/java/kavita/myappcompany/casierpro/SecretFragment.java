package kavita.myappcompany.casierpro;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;


public class SecretFragment extends Fragment {

    Button button;
EditText editText1,editText2,editText3,editText4,editText5,editText6,editText7;
public String pin,q1,q2,q3,a1,a2,a3;

public static  String Pin="PIN";
    private SharedPreferences sharedPreferences;
    private  SharedPreferences.Editor editor;
    public static  String Question1;
    public static  String answer1;
    public static  String Question2;
    public static  String answer2;
    public static  String Question3;
    public static  String answer3;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_secret, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button = view.findViewById(R.id.Save);

        editText1=view.findViewById(R.id.SecretPin);
        editText2=view.findViewById(R.id.question1);
        editText3=view.findViewById(R.id.answer1);
        editText4=view.findViewById(R.id.question2);
        editText5=view.findViewById(R.id.answer2);
        editText6=view.findViewById(R.id.question3);
        editText7=view.findViewById(R.id.answer3);
        button.setOnClickListener(v -> {

Log.i("SecretFragment",editText1.getText().toString());

            Pin=editText1.getText().toString();
            Question1=editText2.getText().toString();
            answer1=editText3.getText().toString();
            Question2=editText4.getText().toString();
            answer2=editText5.getText().toString();
            Question3=editText6.getText().toString();
            answer3=editText7.getText().toString();

            MainActivity.sp.edit().putString("Pin",Pin).apply();
            MainActivity.sp.edit().putString("Question1",Question1).apply();
            MainActivity.sp.edit().putString("answer1",answer1).apply();
            MainActivity.sp.edit().putString("Question2",Question2).apply();
            MainActivity.sp.edit().putString("answer2",answer2).apply();
            MainActivity.sp.edit().putString("Question3",Question3).apply();
            MainActivity.sp.edit().putString("answer3",answer3).apply();

            Toast.makeText(getContext(), "Data saved!!", Toast.LENGTH_LONG).show();
            Intent i = new Intent(getActivity(), Scan_code.class);

            startActivity(i);
        });

    }


}