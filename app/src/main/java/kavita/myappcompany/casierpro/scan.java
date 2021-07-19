package kavita.myappcompany.casierpro;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.biometric.BiometricPrompt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class scan extends Fragment {

    private Button addFingerPrint;
    private ImageView setting;
    private FirebaseAuth mAuth;
    private MainActivity mainActivity;
    private static String TAG = MainActivity.class.getName();
    FragmentActivity scan;

    public scan() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_scan, container, false);
        return  v;
       /* //mainActivity = (MainActivity) getActivity();

        Fragment activity=  fragmentScan.getActivity();
        Executor newExecutor = Executors.newSingleThreadExecutor();



       //final  BiometricPrompt myBioPrompt= new BiometricPrompt(getActivity(), newExecutor, new BiometricPrompt.AuthenticationCallback(){
        final BiometricPrompt myBiometricPrompt = new BiometricPrompt(activity, newExecutor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
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

                TextView iptxt =  (TextView)  v.findViewById( R.id.IPAddress );
                sendMessage( iptxt.getText().toString(), "MakerTutor\n");


            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Log.d(TAG, "Fingerprint not recognised");
            }



        });

        final BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Scan your fingerprint to unlock.")
                .setSubtitle("If you use face unlock Can be done as well")
                .setDescription("Maker Tutor Arduino/ESP32 Project Unlock door with Fingerprint sensor from Android ")
                .setNegativeButtonText("Cancel")
                .build();

        v.findViewById(R.id.Launch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String regex = "^((25[0-5])|(2[0-4]\\d)|(1\\d\\d)|([1-9]\\d)|\\d)(\\.((25[0-5])|(2[0-4]\\d)|(1\\d\\d)|([1-9]\\d)|\\d)){3}$";
                TextView  iptxt =  (TextView)  v.findViewById( R.id.IPAddress );
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setCancelable(false);
                builder.setMessage("Enter esp32 ip address." );
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Do something
                    }
                });

                if( iptxt.getText().toString().matches(regex)  ){

                    myBiometricPrompt.authenticate(promptInfo);
                    Log.d(TAG, "Test send data to esp32");
                }else {
                    AlertDialog dialog = builder.create();
                    dialog.show();

                };

            }
        });


    }
    private void sendMessage(final String ip , final String msg) {

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
                        public void run()
                        {
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
                    public void run()
                    {
                        //txtStatus.setText(string);
                    }
                });
            }
        };
        new Thread(runSend).start();*/


    }

            public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
                //super.onViewCreated(view, savedInstanceState);
                addFingerPrint = view.findViewById(R.id.addFingerprint);
                setting = view.findViewById(R.id.setings);
                mainActivity = (MainActivity) getActivity();

                addFingerPrint.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "Please add fingerprint manually in your device settings", Toast.LENGTH_LONG).show();
                    }
                });

                setting.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showCustomDialog();
                    }
                });


            }

            private void showCustomDialog() {


                final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
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
                        Toast.makeText(getActivity(), "Reset Link has been sent to your mail", Toast.LENGTH_SHORT).show();
                    }
                });


                LogOut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mAuth.signOut();
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                        Toast.makeText(getActivity(), "Sign out successfully!!!", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }

