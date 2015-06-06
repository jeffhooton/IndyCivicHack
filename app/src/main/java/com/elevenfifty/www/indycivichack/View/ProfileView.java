package com.elevenfifty.www.indycivichack.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.elevenfifty.www.indycivichack.CivicApplication;
import com.elevenfifty.www.indycivichack.Model.Singleton;
import com.elevenfifty.www.indycivichack.R;
import com.elevenfifty.www.indycivichack.Scene.NavigationScene;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import flow.Flow;

/**
 * Created by jeffhooton on 6/6/15.
 */
public class ProfileView extends ScrollView {
    private Flow flow;
    private Context context;
    private ParseUser parseUser;

    @InjectView(R.id.displayEmailProf)
    TextView displayEmail;

    @InjectView(R.id.lastNameETProf)
    EditText lastNameET;

    @InjectView(R.id.firstNameETProf)
    EditText firstNameET;

    @InjectView(R.id.streetETProf)
    EditText streetET;

    @InjectView(R.id.cityETProf)
    EditText cityET;

    @InjectView(R.id.stateSpinner)
    Spinner stateSpinner;

    @InjectView(R.id.zipETPRof)
    EditText zipET;

    @InjectView(R.id.bdayETPRof)
    EditText bdayET;

    @InjectView(R.id.ageSpinner)
    Spinner ageSpinner;

    @InjectView(R.id.genderSpinner)
    Spinner genderSpinner;

    @InjectView(R.id.iepSpinner)
    Spinner iepSpinner;

    @InjectView(R.id.saveButton)
    Button saveButton;

    @InjectView(R.id.logOutProf)
    Button logOut;

    public ProfileView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.flow = CivicApplication.getMainFlow();
        this.context = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.inject(this);

        parseUser = ParseUser.getCurrentUser();
        displayEmail.setText(parseUser.getEmail());
        setupSingleton();

//        if (parseUser.get("demographicComplete").equals("YES")) {
//            saveButton.setBackground(getResources().getDrawable(R.drawable.round_button_yellow));
//            logOut.setBackground(getResources().getDrawable(R.drawable.round_button_yellow));
//            saveButton.setTextColor(getResources().getColor(android.R.color.black));
//            logOut.setTextColor(getResources().getColor(android.R.color.black));
//        } else if (parseUser.get("applicationComplete").equals("YES") &&
//                parseUser.get("demographicComplete").equals("YES")) {
//            saveButton.setBackground(getResources().getDrawable(R.drawable.round_button_green));
//            logOut.setBackground(getResources().getDrawable(R.drawable.round_button_green));
//            saveButton.setTextColor(getResources().getColor(android.R.color.black));
//            logOut.setTextColor(getResources().getColor(android.R.color.black));
//        }


        // AGE SPINNER SETUP
        if (Singleton.getInstance().getAge() != null) {
            ageSpinner.setSelection(getSpinnerIndex(ageSpinner, (Singleton.getInstance().getAge())));
        } else {
            ageSpinner.setSelection(0);
        }
        // NAME ET SETUP
        if (Singleton.getInstance().getLastName() != null) {
            lastNameET.setText(Singleton.getInstance().getLastName());
        }
        if (Singleton.getInstance().getFirstName() != null) {
            firstNameET.setText(Singleton.getInstance().getFirstName());
        }

        // BDAY ET SETUP
        if (Singleton.getInstance().getBday() != null) {
            bdayET.setText(Singleton.getInstance().getBday());
        }

        // IEP SPINNER SETUP
        if (Singleton.getInstance().getIep() != null) {
            iepSpinner.setSelection(getSpinnerIndex(iepSpinner, Singleton.getInstance().getIep()));
        } else {
            iepSpinner.setSelection(0);
        }

        // STREET ET SETUP
        if (Singleton.getInstance().getStreet() != null) {
            streetET.setText(Singleton.getInstance().getStreet());
        }

        // STATE SPINNER SETUP
        if (Singleton.getInstance().getState() != null) {
            stateSpinner.setSelection(getSpinnerIndex(stateSpinner, Singleton.getInstance().getState()));
        } else {
            stateSpinner.setSelection(0);
        }

        // ZIP ET SETUP
        if (Singleton.getInstance().getZip() != null) {
            zipET.setText(Singleton.getInstance().getZip());
        }

        // CITY ET SETUP
        if (Singleton.getInstance().getCity() != null) {
            cityET.setText(Singleton.getInstance().getCity());
        }

        // GENDER SPINNER SETUP
        if (Singleton.getInstance().getGender() != null) {
            genderSpinner.setSelection(getSpinnerIndex(genderSpinner, Singleton.getInstance().getGender()));
        } else {
            genderSpinner.setSelection(0);
        }

        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Singleton.getInstance().setState(stateSpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Singleton.getInstance().setAge(ageSpinner.getItemAtPosition(position).toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Singleton.getInstance().setGender(genderSpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        iepSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Singleton.getInstance().setIep(iepSpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        logOut.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                flow.goTo(new NavigationScene(context));
            }
        });

        saveButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                grabETValues();

                parseUser.put("age", Singleton.getInstance().getAge());
                parseUser.put("lastName", Singleton.getInstance().getLastName());
                parseUser.put("firstName", Singleton.getInstance().getFirstName());
                parseUser.put("birthday", Singleton.getInstance().getBday());
                parseUser.put("city", Singleton.getInstance().getCity());
                parseUser.put("state", Singleton.getInstance().getState());
                parseUser.put("street", Singleton.getInstance().getStreet());
                parseUser.put("zip", Singleton.getInstance().getZip());
                parseUser.put("gender", Singleton.getInstance().getGender());
                parseUser.put("iep", Singleton.getInstance().getIep());
                parseUser.put("demographicComplete", "YES");
                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(context, "Information saved!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Information save failed... Please try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }

    private void grabETValues() {
        final Singleton singleton = Singleton.getInstance();
        singleton.setLastName(lastNameET.getText().toString());
        singleton.setFirstName(firstNameET.getText().toString());
        singleton.setBday(bdayET.getText().toString());
        singleton.setZip(zipET.getText().toString());
        singleton.setStreet(streetET.getText().toString());
        singleton.setCity(cityET.getText().toString());
    }

    private void setupSingleton() {
        Singleton singleton = Singleton.getInstance();
        if (parseUser.get("age") != null) {
            singleton.setAge(parseUser.get("age").toString());
        }
        if (parseUser.get("lastName") != null) {
            singleton.setLastName(parseUser.get("lastName").toString());
        }
        if (parseUser.get("firstName") != null) {
            singleton.setFirstName(parseUser.get("firstName").toString());
        }
        if (parseUser.get("birthday") != null) {
            singleton.setBday(parseUser.get("birthday").toString());
        }
        if (parseUser.get("city") != null) {
            singleton.setCity(parseUser.get("city").toString());
        }
        if (parseUser.get("state") != null) {
            singleton.setState(parseUser.get("state").toString());
        }
        if (parseUser.get("zip") != null) {
            singleton.setZip(parseUser.get("zip").toString());
        }
        if (parseUser.get("gender") != null) {
            singleton.setGender(parseUser.get("gender").toString());
        }
        if (parseUser.get("iep") != null) {
            singleton.setIep(parseUser.get("iep").toString());
        }
    }

    private int getSpinnerIndex(Spinner spinner, String myString) {
        int index = 0;

        for (int i=0;i<spinner.getCount();i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)) {
                index = i;
                break;
            }
        }
        return index;
    }
}
