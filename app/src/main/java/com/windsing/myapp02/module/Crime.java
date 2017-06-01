package com.windsing.myapp02.module;

import java.util.Date;
import java.util.UUID;

/**
 * Created by FC on 2017/5/8.
 */

public class Crime {

    /**
     * UUID一个代表不可变的通用唯一标识符的类
     */
    private UUID mId; //只读，设置获取方法
    private String mTitle;

    private Date mDate;
    private boolean mSolved;

    public Crime() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }
    public Crime(UUID id) {
        mId = id;
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }
}
