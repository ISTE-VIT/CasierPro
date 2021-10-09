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
/*public static  final String SHARED_PREFS="sharedPrefs";*/
public static  String Pin="PIN";
    private SharedPreferences sharedPreferences;
    private  SharedPreferences.Editor editor;
    public static  String Question1;
    public static  String answer1;
    public static  String Question2;
    public static  String answer2;
    public static  String Question3;
    public static  String answer3;

 /*  public String Question1;
    public  String answer1;
    public  String Question2;
    public  String answer2;
    public  String Question3;
    public String answer3;*/
    //public static SharedPreferences sharedPreferences;
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
           /* sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getContext());
            editor=sharedPreferences.edit();*/

//SharedPreferences.Editor editor=sharedPreferences.edit();
Log.i("SecretFragment",editText1.getText().toString());
/*editor.putString("Pin",editText1.getText().toString());
editor.putString("Question1",editText2.getText().toString());
editor.putString("answer1",editText3.getText().toString());
editor.putString("Question2",editText4.getText().toString());
editor.putString("answer2",editText5.getText().toString());
editor.putString("Question3",editText6.getText().toString());
editor.putString("answer3",editText7.getText().toString());
editor.commit();*/
            Pin=editText1.getText().toString();
            Question1=editText2.getText().toString();
            answer1=editText3.getText().toString();
            Question2=editText4.getText().toString();
            answer2=editText5.getText().toString();
            Question3=editText6.getText().toString();
            answer3=editText7.getText().toString();
          /*  MainActivity.sp.edit().putString("Pin",editText1.getText().toString()).apply();
            MainActivity.sp.edit().putString("Question1",editText2.getText().toString()).apply();
            MainActivity.sp.edit().putString("answer1",editText3.getText().toString()).apply();
            MainActivity.sp.edit().putString("Question2",editText4.getText().toString()).apply();
            MainActivity.sp.edit().putString("answer2",editText5.getText().toString()).apply();
            MainActivity.sp.edit().putString("Question3",editText6.getText().toString()).apply();
            MainActivity.sp.edit().putString("answer3",editText7.getText().toString()).apply();*/
            //getData();
            MainActivity.sp.edit().putString("Pin",Pin).apply();
            MainActivity.sp.edit().putString("Question1",Question1).apply();
            MainActivity.sp.edit().putString("answer1",answer1).apply();
            MainActivity.sp.edit().putString("Question2",Question2).apply();
            MainActivity.sp.edit().putString("answer2",answer2).apply();
            MainActivity.sp.edit().putString("Question3",Question3).apply();
            MainActivity.sp.edit().putString("answer3",answer3).apply();

            Toast.makeText(getContext(), "Data saved!!", Toast.LENGTH_LONG).show();
            Intent i = new Intent(getActivity(), Scan_code.class);
           /* i.putExtra("Pin",pin);
            i.putExtra("Question1",q1);
            i.putExtra("Question2",q2);
            i.putExtra("Question3",q3);
            i.putExtra("Answer1",a1);
            i.putExtra("Answer2",a2);
            i.putExtra("Answer3",a3);*/
            startActivity(i);
        });

    }

 /*   public void getData() {
     *//*   pin=sharedPreferences.getString("Pin","");
        q1=sharedPreferences.getString("Question1","");
        q2=sharedPreferences.getString("Question2","");
        q3=sharedPreferences.getString("Question3","");
        a1=sharedPreferences.getString("answer1","");
        a2=sharedPreferences.getString("answer2","");
        a3=sharedPreferences.getString("answer3","");*//*
        pin=MainActivity.sp.getString("Pin","");
        q1=MainActivity.sp.getString("Question1","");
        q2=MainActivity.sp.getString("Question2","");
        q3=MainActivity.sp.getString("Question3","");
        a1=MainActivity.sp.getString("answer1","");
        a2=MainActivity.sp.getString("answer2","");
        a3=MainActivity.sp.getString("answer3","");

    }*/
}