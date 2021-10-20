package kavita.myappcompany.casierpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/*import static kavita.myappcompany.casierpro.SecretFragment.Pin;
import static kavita.myappcompany.casierpro.SecretFragment.SHARED_PREFS;*/

public class Scan_code extends AppCompatActivity {
    private Button addFingerPrint, button, button1;
    private ImageView setting,imageView;
    private FirebaseAuth mAuth;
    private static String TAG = MainActivity.class.getName();
    private DatabaseReference rootDatbaseRef,rootDatbaseRef1,rootDatbaseRef2,rootDatbaseRef4;
    public String q1,q2,q3,a1,a2,a3;
    public Fragment fragmentSecret;
    //SharedPreferences sharedPreferences;
    //SharedPreferences.Editor editor;
    public String pin;
    EditText pinTV, ans2, ans3, ans1;
    public String text, Question1, Answer1, Question2, Answer2, Question3, Answer3,email;
    TextView textview4, textview5, textview6;
    public int counter, counter1;
    BiometricPrompt myBiometricPrompt1;
    BiometricPrompt.PromptInfo promptInfo1;

    public void onStart() {

        super.onStart();
        rootDatbaseRef = FirebaseDatabase.getInstance().getReference().child("number");
        float a = 0;
        rootDatbaseRef.setValue(a);
        rootDatbaseRef1 = FirebaseDatabase.getInstance().getReference().child("forgot");
        float k = 3;
        rootDatbaseRef1.setValue(k);
        rootDatbaseRef2 = FirebaseDatabase.getInstance().getReference().child("duration");
        float l = 2;
        rootDatbaseRef2.setValue(l);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_code);
        addFingerPrint = findViewById(R.id.addFingerprint_activity);
        setting = findViewById(R.id.setings_activity);
        button = findViewById(R.id.RFID);
     imageView=findViewById(R.id.imageView7);
        Intent intent = getIntent();
        addFingerPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Scan_code.this, "Please add fingerprint manually in your device settings", Toast.LENGTH_LONG).show();
            }
        });

        setting.setOnClickListener(v -> showCustomDialog());
        button.setOnClickListener(v -> {
            RFIdLost();
        });
        Executor newExecutor = Executors.newSingleThreadExecutor();
        FragmentActivity activity = this;

        final BiometricPrompt myBiometricPrompt = new BiometricPrompt(activity, newExecutor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                if (errorCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON) {
                } else {
                    Log.d(TAG, "An unrecoverable error occurred");
                }
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Log.d(TAG, "Fingerprint recognised successfully");
                rootDatbaseRef = FirebaseDatabase.getInstance().getReference().child("number");
                float a = 1;
                rootDatbaseRef.setValue(a);
                rootDatbaseRef1 = FirebaseDatabase.getInstance().getReference().child("forgot");
                float k = 3;
                rootDatbaseRef1.setValue(k);
                rootDatbaseRef2 = FirebaseDatabase.getInstance().getReference().child("duration");
                float l = 2;
                rootDatbaseRef2.setValue(l);





            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                rootDatbaseRef = FirebaseDatabase.getInstance().getReference().child("number");
                float b = 0;
                rootDatbaseRef.setValue(b);
                Log.d(TAG, "Fingerprint not recognised");
                int count = 3;
                rootDatbaseRef1 = FirebaseDatabase.getInstance().getReference().child("forgot");
                float k = 3;
                rootDatbaseRef1.setValue(k);
                rootDatbaseRef2 = FirebaseDatabase.getInstance().getReference().child("duration");
                float l = 2;
                rootDatbaseRef2.setValue(l);
            }


        });
        int authenticators = 3;
        final BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Scan your fingerprint to unlock.")
                .setSubtitle("Casier Pro")
                .setDescription("Door Lock on your tips ")
                .setNegativeButtonText("Cancel")
                .build();

        findViewById(R.id.Unlock).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                rootDatbaseRef = FirebaseDatabase.getInstance().getReference().child("number");
                float b = 0;
                rootDatbaseRef.setValue(b);
                //String regex = "^((25[0-5])|(2[0-4]\\d)|(1\\d\\d)|([1-9]\\d)|\\d)(\\.((25[0-5])|(2[0-4]\\d)|(1\\d\\d)|([1-9]\\d)|\\d)){3}$";
                //TextView  iptxt =  (TextView)  findViewById( R.id.IPAddress_activity );
                /*AlertDialog.Builder builder = new AlertDialog.Builder(Scan_code.this);
                builder.setCancelable(false);
                builder.setMessage("Scan to Unlock");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Do something
                    }
                });*/


                myBiometricPrompt.authenticate(promptInfo);
                Log.d(TAG, "Test send data to esp32");

                /*AlertDialog dialog = builder.create();
                dialog.show();*/

            }


        });

        Executor newExecutor1 = Executors.newSingleThreadExecutor();
        FragmentActivity activity1 = Scan_code.this;
        myBiometricPrompt1 = new BiometricPrompt(activity1, newExecutor1, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                if (errorCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON) {
                } else {
                    Log.d(TAG, "An unrecoverable error occurred");
                }
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Log.d(TAG, "Fingerprint recognised successfully");

                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                i.putExtra("secret",0);
                startActivity(i);



            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(Scan_code.this, "Fingerprint not recognised", Toast.LENGTH_SHORT).show();

            }


        });

        promptInfo1 = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Scan your fingerprint to reset your secret pin")
                .setSubtitle("Casier Pro")
                .setDescription("Door Lock on your tips ")
                .setNegativeButtonText("Cancel")
                .build();

    }




    private void RFIdLost() {

        final AlertDialog.Builder alert1 = new AlertDialog.Builder(Scan_code.this);
        View mView = getLayoutInflater().inflate(R.layout.secretpin_custom_dialog_box, null);

        Button Submit = mView.findViewById(R.id.SubmitPin);


        alert1.setView(mView);

        final AlertDialog alertDialog = alert1.create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();


        mAuth = FirebaseAuth.getInstance();
        Submit.setOnClickListener(v -> {
            pinTV = mView.findViewById(R.id.editTextNumberPassword);
            pin = String.valueOf(pinTV.getText());
            Log.i("SconCode", "pin:" + pin);
getData();
           // text = getIntent().getStringExtra("Pin");
            Log.i("SconCode",String.valueOf( text));
            if (text.equals(pin)) {
                questions();
                alertDialog.dismiss();
                //Toast.makeText(this, "working", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(this, "Incorrect PIN", Toast.LENGTH_SHORT).show();

        });

    }

    private void questions() {

        final AlertDialog.Builder alert2 = new AlertDialog.Builder(Scan_code.this);
        View mView1 = getLayoutInflater().inflate(R.layout.questions_custom_dialog_box, null);
        alert2.setView(mView1);

        final AlertDialog alertDialog2 = alert2.create();
        alertDialog2.setCanceledOnTouchOutside(true);
        alertDialog2.show();

        textview4 = mView1.findViewById(R.id.textView4);
        //Log.i("Question1", getIntent().getStringExtra("Question1"));
       // textview4.setText(getIntent().getStringExtra("Question1"));
        textview5 = mView1.findViewById(R.id.textView5);
        //textview5.setText(getIntent().getStringExtra("Question2"));
        textview6 = mView1.findViewById(R.id.textView6);
        //textview6.setText(getIntent().getStringExtra("Question3"));
        ans1 = mView1.findViewById(R.id.ans1);
        textview4.setText(q1);
        textview5.setText(q2);
        textview6.setText(q3);
        Answer1 = a1;
        Answer2 = a2;
        Answer3 = a3;
       /* Answer1 = getIntent().getStringExtra("Answer1");
        Log.i("Question1", getIntent().getStringExtra("Answer1"));
        Log.i("Question1", "ans1:" + ans1.getText().toString());*/
        ans2 = mView1.findViewById(R.id.ans2);
        //Answer2 = getIntent().getStringExtra("Answer2");
        ans3 = mView1.findViewById(R.id.ans3);
        //Answer3 = getIntent().getStringExtra("Answer3");
        mView1.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((ans1.getText().toString().toLowerCase()).equals(Answer1.toLowerCase()))
                    if ((ans2.getText().toString().toLowerCase()).equals(Answer2.toLowerCase()))
                        if ((ans3.getText().toString().toLowerCase()).equals(Answer3.toLowerCase())) {
                            rootDatbaseRef = FirebaseDatabase.getInstance().getReference().child("forgot");
                            float b = 6;
                            rootDatbaseRef.setValue(b);
                            triggerCountDown();

                        } else
                            Toast.makeText(Scan_code.this, "Incorrect answer. please check it once again3", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(Scan_code.this, "Incorrect answer. please check it once again2", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Scan_code.this, "Incorrect answer. please check it once again1", Toast.LENGTH_SHORT).show();

            }

        });
//alertDialog2.dismiss();

    }

    private void triggerCountDown() {

        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Toast.makeText(Scan_code.this, "Seconds remaining  " +  millisUntilFinished / 1000, Toast.LENGTH_SHORT).show();
                counter++;
            }

            @Override
            public void onFinish() {
                rootDatbaseRef = FirebaseDatabase.getInstance().getReference().child("duration");
                rootDatbaseRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Float k = Float.parseFloat(snapshot.getValue().toString());
                            Log.i("duration", k.toString());
                            if (k == 4) {
                                //rootDatbaseRef = FirebaseDatabase.getInstance().getReference().child("duration");
                                float b = 2;
                                rootDatbaseRef.setValue(b);

                            } else {
                               // Toast.makeText(Scan_code.this, "Check Mail", Toast.LENGTH_SHORT).show();
                                new SendMail().execute("");
                                triggerCountDown2();
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                //Toast.makeText(Scan_code.this, "Check mail if door unlocked", Toast.LENGTH_SHORT).show();
            }
        }.start();
    }

    private void triggerCountDown2() {
        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Toast.makeText(Scan_code.this, "Seconds remaining  " +  millisUntilFinished / 1000, Toast.LENGTH_SHORT).show();
                counter1++;
            }

            @Override
            public void onFinish() {
                rootDatbaseRef4 = FirebaseDatabase.getInstance().getReference().child("duration");
                rootDatbaseRef4.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Float k = Float.parseFloat(snapshot.getValue().toString());
                            Log.i("duration", k.toString());
                            if (k == 4) {
                                //rootDatbaseRef = FirebaseDatabase.getInstance().getReference().child("duration");
                                float b = 2;
                                rootDatbaseRef4.setValue(b);
                            } else {
                                float b = 9;
                                rootDatbaseRef4.setValue(b);
                            }


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }


                });
            }
            }.start();

        }
        private void sendMessage ( final String ip, final String msg){

            Runnable runSend = new Runnable() {
                public void run() {
                    try {
                        Socket s = new Socket(ip
                                , 80);

                        BufferedWriter out = new BufferedWriter
                                (new OutputStreamWriter(s.getOutputStream()));
                        String outgoingMsg = msg;
                        out.write(outgoingMsg);
                        out.flush();
                        Handler refresh = new Handler(Looper.getMainLooper());
                        refresh.post(new Runnable() {
                            public void run() {
                                //txtStatus.setText("Message has been sent.");
                                //etxtMessage.setText("");
                            }
                        });
                        Log.i("Sender", outgoingMsg);
                        s.close();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                        setText("No device on this IP address.");
                    } catch (Exception e) {
                        e.printStackTrace();
                        setText("Connection failed. Please try again.");
                    }
                }

                public void setText(String str) {
                    final String string = str;
                    Handler refresh = new Handler(Looper.getMainLooper());
                    refresh.post(new Runnable() {
                        public void run() {
                            //txtStatus.setText(string);
                        }
                    });
                }
            };
            new Thread(runSend).start();


        }

        private void showCustomDialog () {


            final AlertDialog.Builder alert = new AlertDialog.Builder(Scan_code.this);
            View mView = getLayoutInflater().inflate(R.layout.settings_custom_dialog_box, null);

            Button Reset = mView.findViewById(R.id.resetpwd);
            Button LogOut = mView.findViewById(R.id.LogOut);
Button secret=mView.findViewById(R.id.resetSecret);
            alert.setView(mView);

            final AlertDialog alertDialog = alert.create();
            alertDialog.setCanceledOnTouchOutside(true);
            alertDialog.show();


            mAuth = FirebaseAuth.getInstance();

            Reset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(Scan_code.this, "Reset Link has been sent to your mail", Toast.LENGTH_SHORT).show();
                }
            });


            LogOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.sp.edit().putBoolean("logged",false).apply();
                    mAuth.signOut();
                    Intent intent = new Intent(Scan_code.this, MainActivity.class);
                    //intent.putExtra("logged",false);
                    startActivity(intent);
                    Scan_code.this.finish();
                    Toast.makeText(Scan_code.this, "Sign out successfully!!!", Toast.LENGTH_SHORT).show();

                }
            });
secret.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        myBiometricPrompt1.authenticate(promptInfo1);

    }
});

        }




    public void getData() {
     /*   pin=sharedPreferences.getString("Pin","");
        q1=sharedPreferences.getString("Question1","");
        q2=sharedPreferences.getString("Question2","");
        q3=sharedPreferences.getString("Question3","");
        a1=sharedPreferences.getString("answer1","");
        a2=sharedPreferences.getString("answer2","");
        a3=sharedPreferences.getString("answer3","");*/
        text=MainActivity.sp.getString("Pin","");
        q1=MainActivity.sp.getString("Question1","");
        q2=MainActivity.sp.getString("Question2","");
        q3=MainActivity.sp.getString("Question3","");
        a1=MainActivity.sp.getString("answer1","");
        a2=MainActivity.sp.getString("answer2","");
        a3=MainActivity.sp.getString("answer3","");

    }
    private class SendMail extends AsyncTask<String, Integer, Void> {

       // private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           // progressDialog = ProgressDialog.show(getApplicationContext(), "Please wait", "Sending mail", true, false);
            Toast.makeText(Scan_code.this, "Please wait", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(Scan_code.this, "Chcek Mail", Toast.LENGTH_SHORT).show();
        }

        protected Void doInBackground(String... params) {
            Mail m = new Mail("CasierProOfficial@gmail.com", "CasierPro@123");
email=getIntent().getStringExtra("Email");
            String[] toArr = {"kavipn13@gmail.com","CasierProOfficial@gmail.com"};
            m.setTo(toArr);
            m.setFrom("CasierProOfficial@gmail.com");
            m.setSubject("Someone is trying to enter your house");
            m.setBody(" Hi , \n This is mail is sent to notify you that someone is trying to enter your house. If its you, please press the button. \n Regards, Team Casier Pro");


            try {
                if(m.send()) {
                    Toast.makeText(getApplicationContext(), "Email was sent successfully.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Email was not sent.", Toast.LENGTH_LONG).show();
                }
            } catch(Exception e) {
                Log.e("MailApp", "Could not send email", e);
            }
            return null;
        }
    }
    }