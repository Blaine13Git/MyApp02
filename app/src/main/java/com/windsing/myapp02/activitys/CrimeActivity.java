package com.windsing.myapp02.activitys;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.windsing.myapp02.common.SingleFragmentActivity;
import com.windsing.myapp02.fragments.CrimeFragment;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {

    private static final String EXTRA_CRIME_ID = "com.windsing.myapp02.activitys.crime_id";

    /**
     *
     * @param packageContext
     * @param crimeId
     * @return
     */
    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeId);
    }

}
