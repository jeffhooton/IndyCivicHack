package com.elevenfifty.www.indycivichack;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.davidstemmer.screenplay.MutableStage;
import com.davidstemmer.screenplay.flow.Screenplay;
import com.elevenfifty.www.indycivichack.Scene.CheckScene;
import com.elevenfifty.www.indycivichack.Scene.IndexedScene;
import com.elevenfifty.www.indycivichack.Scene.ProfileScene;

import flow.Flow;


public class MainActivity extends Activity {
    private MutableStage stage;
    private Flow flow;
    private Screenplay screenplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RelativeLayout container = (RelativeLayout)findViewById(R.id.main);

        stage = CivicApplication.getStage();
        flow = CivicApplication.getMainFlow();
        screenplay = CivicApplication.getScreenplay();

        stage.bind(this, container, flow);
        screenplay.enter();

        IndexedScene currentScene = (IndexedScene) flow.getBackstack().current().getScreen();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            flow.goTo(new ProfileScene(this));
            return true;
        } else if (id == R.id.action_check) {
            flow.goTo(new CheckScene(this));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        screenplay.exit();
        stage.unbind();
    }
}
