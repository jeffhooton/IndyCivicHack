package com.elevenfifty.www.indycivichack.View;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.elevenfifty.www.indycivichack.CivicApplication;
import com.elevenfifty.www.indycivichack.R;
import com.elevenfifty.www.indycivichack.Scene.PagerScene;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;
import com.parse.SignUpCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import flow.Flow;

/**
 * Created by jeffhooton on 6/6/15.
 */
public class NavigationView extends RelativeLayout {
    private Flow flow;
    private Context context;
    private ProgressDialog authProgressDialog;
    private ProgressDialog createProgressDialog;

    @InjectView(R.id.signUpButton)
    Button signUpButton;
    @InjectView(R.id.logInButton)
    Button logInButton;
    @InjectView(R.id.forgotPass)
    TextView forgotPass;
    @InjectView(R.id.npEmailEditText)
    EditText emailET;
    @InjectView(R.id.npPasswordEditText)
    EditText passET;

    public NavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.flow = CivicApplication.getMainFlow();
        this.context = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.inject(this);

        setupDialog();

        signUpButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                authProgressDialog.show();
                String email = emailET.getText().toString();
                ParseUser user = new ParseUser();
                user.setUsername(email);
                user.setEmail(email);
                user.setPassword(passET.getText().toString());
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            authProgressDialog.dismiss();
                            flow.goTo(new PagerScene(context));
                        } else {
                            authProgressDialog.dismiss();
                            Toast.makeText(getContext(), "Account creation failed.", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

        logInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailET.getText().toString();
                String pass = passET.getText().toString();

                authProgressDialog.show();

                ParseUser.logInInBackground(email, pass, new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        if (e == null && parseUser != null) {
                            authProgressDialog.dismiss();
                            flow.goTo(new PagerScene(context));
                        } else {
                            authProgressDialog.dismiss();
                            Toast.makeText(getContext(), "Log in failed, please try again.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        forgotPass.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater li = LayoutInflater.from(context);
                View inputView = li.inflate(R.layout.prompt, null);

                final EditText userInput = (EditText) inputView.findViewById(R.id.userInputET);

                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setView(inputView);
                alert.setTitle("Reset Password.");
                alert.setNeutralButton("Cancel", null);
                alert.setPositiveButton("Reset", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ParseUser.requestPasswordResetInBackground(userInput.getText().toString(),
                                new RequestPasswordResetCallback() {
                                    @Override
                                    public void done(ParseException e) {
                                        if (e == null) {
                                            AlertDialog.Builder alert = new AlertDialog.Builder(context);
                                            alert.setTitle("Success");
                                            alert.setMessage("Check your inbox for password resetting instructions.");
                                            alert.setNeutralButton("Ok", null);
                                            alert.show();
                                        } else {
                                            AlertDialog.Builder alert = new AlertDialog.Builder(context);
                                            alert.setTitle("Failure");
                                            alert.setMessage("Oops, something went wrong. Please try again.");
                                            alert.setNeutralButton("Ok", null);
                                            alert.show();
                                        }
                                    }
                                });
                    }
                });
                AlertDialog alertDialog = alert.create();
                alertDialog.show();
            }
        });

    }

    private void setupDialog() {
        authProgressDialog = new ProgressDialog(context);
        authProgressDialog.setTitle("Authenticating");
        authProgressDialog.setCancelable(false);

        createProgressDialog = new ProgressDialog(context);
        createProgressDialog.setTitle("Creating account");
        createProgressDialog.setCancelable(false);
    }
}
