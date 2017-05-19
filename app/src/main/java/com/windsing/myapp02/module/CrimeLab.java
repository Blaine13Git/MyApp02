package com.windsing.myapp02.module;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by FC on 2017/5/15.
 */

public class CrimeLab {

    private static CrimeLab sCrimeLab;
    private List<Crime> mCrimes;

    private CrimeLab(Context context) {
        mCrimes = new ArrayList<>(); // 保存crime对象
    }

    //单例模式
    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    /**
     * 返回数组列表
     *
     * @return
     */
    public List<Crime> getCrimes() {
        return mCrimes;
    }

    /**
     * 返回带有指定ID的Crime对象
     *
     * @param id
     * @return
     */
    public Crime getCrime(UUID id) {
        for (Crime crime : mCrimes) {
            if (crime.getId().equals(id)) {
                return crime;
            }
        }
        return null;
    }

    public void addCrime(Crime crime){
        mCrimes.add(crime);  // Crime List + 1
    }

}
