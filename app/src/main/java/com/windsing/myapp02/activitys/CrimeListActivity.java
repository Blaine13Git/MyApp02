package com.windsing.myapp02.activitys;

import android.support.v4.app.Fragment;

import com.windsing.myapp02.common.SingleFragmentActivity;
import com.windsing.myapp02.fragments.CrimeListFragment;

/**
 * Created by FC on 2017/5/15.
 */

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
