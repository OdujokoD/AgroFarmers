package com.example.agrofarmers.views;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.agrofarmers.R;
import com.example.agrofarmers.helpers.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static final String STOCK_TITLE = "STOCK";
    public static final String SHIPMENT_TITLE = "SHIPMENT";
    public static final String PROFILE_TITLE = "PROFILE";

    //@BindView(R.id.user) EditText username;
    @BindView(R.id.viewpager_main) ViewPager viewPager;
    @BindView(R.id.tabs_main) TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        initializeViewPager();
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initializeViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new StockFragment(), STOCK_TITLE);
        adapter.addFragment(new ShipmentFragment(), SHIPMENT_TITLE);
        adapter.addFragment(new ProfileFragment(), PROFILE_TITLE);

        viewPager.setAdapter(adapter);
    }
}
