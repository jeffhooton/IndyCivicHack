package com.elevenfifty.www.indycivichack;

import android.app.Application;

import com.davidstemmer.screenplay.MutableStage;
import com.davidstemmer.screenplay.flow.Screenplay;
import com.elevenfifty.www.indycivichack.Model.RequestMagnet;
import com.elevenfifty.www.indycivichack.Scene.NavigationScene;
import com.elevenfifty.www.indycivichack.Scene.PagerScene;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;

import flow.Backstack;
import flow.Flow;

/**
 * Created by jeffhooton on 6/6/15.
 */
public class CivicApplication extends Application {

    public final MutableStage stage = new MutableStage();
    public final Screenplay screenplay = new Screenplay(stage);
    public Flow mainFlow;
    private static CivicApplication application;

    public void onCreate() {
        application = this;

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        ParseObject
                .registerSubclass(RequestMagnet.class);
        Parse.initialize(this, "e8LQ6OxGgTCS9b1RBib7DsqNptgcKK53Y29EIb7M", "amglj1CYzPkkAQuuNLGAupmN6tL48qUpVTlG8GuS");
        ParseUser.enableRevocableSessionInBackground();

        if (ParseUser.getCurrentUser() == null) {
            mainFlow = new Flow(Backstack.single(new NavigationScene(this)), screenplay);
        } else {
            mainFlow = new Flow(Backstack.single(new PagerScene(this)), screenplay);
        }
    }

    public static CivicApplication getInstance()        { return application; }
    public static MutableStage getStage()               { return getInstance().stage; }
    public static Screenplay getScreenplay()            { return getInstance().screenplay; }
    public static Flow getMainFlow()                    { return getInstance().mainFlow; }

}
