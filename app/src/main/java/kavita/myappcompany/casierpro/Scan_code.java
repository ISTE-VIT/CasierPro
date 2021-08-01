package kavita.myappcompany.casierpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.fragment.app.FragmentActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.biometrics.BiometricManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Scan_code extends AppCompatActivity {
    private Button addFingerPrint;
    private ImageView setting;
    private FirebaseAuth mAuth;
    private static String TAG = MainActivity.class.getName();
    private DatabaseReference rootDatbaseRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_code);
        addFingerPrint = findViewById(R.id.addFingerprint_activity);
        setting = findViewById(R.id.setings_activity);

        //rootDatbaseRef = FirebaseDatabase.getInstance().getReference().child("Auth");
        //int a=1;
        // rootDatbaseRef.setValue(a);
        addFingerPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Scan_code.this, "Please add fingerprint manually in your device settings", Toast.LENGTH_LONG).show();
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
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
                float a=1;
                rootDatbaseRef.setValue(a);


                /*TextView iptxt = (TextView) findViewById(R.id.IPAddress_activity);
                sendMessage(iptxt.getText().toString(), "MakerTutor\n");*/


            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                rootDatbaseRef = FirebaseDatabase.getInstance().getReference().child("number");
                float b=0;
                rootDatbaseRef.setValue(b);
                Log.d(TAG, "Fingerprint not recognised");
                int count =3;

            }


        });
        int authenticators=3;
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
                float b=0;
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

    }

    private void sendMessage(final String ip, final String msg) {

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

    private void showCustomDialog() {


        final AlertDialog.Builder alert = new AlertDialog.Builder(Scan_code.this);
        View mView = getLayoutInflater().inflate(R.layout.settings_custom_dialog_box, null);

        Button Reset = mView.findViewById(R.id.resetpwd);
        Button LogOut = mView.findViewById(R.id.logOut);

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
                mAuth.signOut();
                Intent intent = new Intent(Scan_code.this, MainActivity.class);
                startActivity(intent);
                Scan_code.this.finish();
                Toast.makeText(Scan_code.this, "Sign out successfully!!!", Toast.LENGTH_SHORT).show();

            }
        });


    }

}