package xyz.ncvt.ifs.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import xyz.ncvt.ifs.R;
import xyz.ncvt.ifs.fragment.EnvironmentIndexFragment;
import xyz.ncvt.ifs.fragment.HistoryDataFragment;
import xyz.ncvt.ifs.fragment.ManualControlFragment;

public class MainActivity extends AppCompatActivity {
    private EnvironmentIndexFragment environmentIndexFragment;
    private ManualControlFragment manualControlFragment;
    private HistoryDataFragment historyDataFragment;
    private Fragment[] fragments;
    private int lastShowFragment = 0;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (lastShowFragment != 0) {
                        switchFrament(lastShowFragment, 0);
                        lastShowFragment = 0;
                        getSupportActionBar().setTitle("环境指标");
                    }
                    return true;
                case R.id.navigation_dashboard:
                    if (lastShowFragment != 1) {
                        switchFrament(lastShowFragment, 1);
                        lastShowFragment = 1;
                        getSupportActionBar().setTitle("手动控制");
                    }
                    return true;
                case R.id.navigation_notifications:
                    if (lastShowFragment != 2) {
                        switchFrament(lastShowFragment, 2);
                        lastShowFragment = 2;
                        getSupportActionBar().setTitle("历史数据");
                    }
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        initFragments();
    }

    /**
     * 切换Fragment
     *
     * @param lastIndex 上个显示Fragment的索引
     * @param index     需要显示的Fragment的索引
     */
    public void switchFrament(int lastIndex, int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastIndex]);
        if (!fragments[index].isAdded()) {
            transaction.add(R.id.fragment_container, fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();
    }

    private void initFragments() {
        environmentIndexFragment = new EnvironmentIndexFragment();
        manualControlFragment = new ManualControlFragment();
        historyDataFragment = new HistoryDataFragment();
        fragments = new Fragment[]{environmentIndexFragment, manualControlFragment, historyDataFragment};
        lastShowFragment = 0;
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, environmentIndexFragment)
                .show(environmentIndexFragment)
                .commit();
    }

}
