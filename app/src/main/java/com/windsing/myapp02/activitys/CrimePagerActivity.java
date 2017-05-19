package com.windsing.myapp02.activitys;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.windsing.myapp02.R;
import com.windsing.myapp02.fragments.CrimeFragment;
import com.windsing.myapp02.module.Crime;
import com.windsing.myapp02.module.CrimeLab;

import java.util.List;
import java.util.UUID;

public class CrimePagerActivity extends AppCompatActivity {

    private static final String EXTRA_CRIME_ID = "com.windsing.myapp02.activitys.crime_id";

    private ViewPager mViewPager;
    private List<Crime> mCrimes;

    public static Intent newIntent(Context pageContext, UUID id) {
        Intent intent = new Intent(pageContext, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        //获取FragmentManager的管理实例
        FragmentManager fragmentManager = getSupportFragmentManager();

        //获取数据集
        mCrimes = CrimeLab.get(this).getCrimes();

        mViewPager = (ViewPager) findViewById(R.id.activity_crime_pager_view_pager);

        /**
         * FragmentStatePagerAdapter是我们的代理，负责管理与ViewPager的对话并协同工作，
         * 然后才能使用fragment完成自己的工作。这也就是创建代理实例时，需要FragmentManager的原因。
         * 代理究竟做了哪些工作呢？
         * 简单来说，就是将返回的fragment添加给托管activity，
         * 并帮助Viewpager找到fragment的视图并一一对应。
         */
        //使用FragmentManager设置adapter为FragmentStatePagerAdapter的一个匿名实例
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {

            /*
            getItem(int)方法返回的fragment添加给activity。
            获取数据集中指定位置的Crime实例，然后利用该Crime实例的ID创建并返回一个有效配置的CrimeFragment
             */
            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }

            //getCount()方法返回数组列表中包含的列表项数目
            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });

        for (int i = 0; i < mCrimes.size(); i++) {
            if (mCrimes.get(i).getId().equals(crimeId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }

    }
}
