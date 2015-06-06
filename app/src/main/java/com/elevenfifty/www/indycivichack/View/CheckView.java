package com.elevenfifty.www.indycivichack.View;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.elevenfifty.www.indycivichack.CivicApplication;
import com.elevenfifty.www.indycivichack.R;
import com.parse.ParseUser;

import butterknife.ButterKnife;
import butterknife.InjectView;
import flow.Flow;

/**
 * Created by jeffhooton on 6/6/15.
 */
public class CheckView extends LinearLayout {
    private Flow flow;
    private Context context;

    @InjectView(R.id.firstChoiceStatus)
    TextView firstChoice;
    @InjectView(R.id.secondChoiceStatus)
    TextView secondChoice;
    @InjectView(R.id.thirdChoiceStatus)
    TextView thirdChoice;

    public CheckView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.flow = CivicApplication.getMainFlow();
        this.context = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.inject(this);

        ParseUser user = ParseUser.getCurrentUser();
//        firstChoice.setText(user.get("statusOne").toString());
//        secondChoice.setText(user.get("statusTwo").toString());
//        thirdChoice.setText(user.get("statusThree").toString());

    }
}
