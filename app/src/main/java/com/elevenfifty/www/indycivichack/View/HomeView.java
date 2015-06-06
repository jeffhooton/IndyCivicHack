package com.elevenfifty.www.indycivichack.View;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.elevenfifty.www.indycivichack.CivicApplication;

import butterknife.ButterKnife;
import flow.Flow;

/**
 * Created by jeffhooton on 6/6/15.
 */
public class HomeView extends RelativeLayout {
    private Flow flow;
    private Context context;

    public HomeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.flow = CivicApplication.getMainFlow();
        this.context = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.inject(this);

    }
}
