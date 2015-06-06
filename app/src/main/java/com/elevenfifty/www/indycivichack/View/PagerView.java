package com.elevenfifty.www.indycivichack.View;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.elevenfifty.www.indycivichack.CivicApplication;
import com.elevenfifty.www.indycivichack.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import flow.Flow;

/**
 * Created by jeffhooton on 6/6/15.
 */
public class PagerView extends RelativeLayout {
    private Flow flow;
    private Context context;

    @InjectView(R.id.viewPager)
    ViewPager pager;


    public PagerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.flow = CivicApplication.getMainFlow();
        this.context = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.inject(this);

        CustomPagerAdapter pagerAdapter = new CustomPagerAdapter(context);
        pager.setAdapter(pagerAdapter);

        pager.setCurrentItem(0);
    }

    public class CustomPagerAdapter extends PagerAdapter {

        Context mContext;
        LayoutInflater mLayoutInflater;

        public CustomPagerAdapter(Context context) {
            mContext = context;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = new View(mContext);
            switch (position) {
                case 0:
                    view = mLayoutInflater.inflate(R.layout.page_home, null);
                    break;
                case 1:
                    view = mLayoutInflater.inflate(R.layout.page_profile, null);
                    break;
                case 2:
                    view = mLayoutInflater.inflate(R.layout.page_check, null);
                    break;

            }
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    }
}
