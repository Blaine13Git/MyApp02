package com.windsing.myapp02.module;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.windsing.myapp02.database.CrimeBaseHelper;
import com.windsing.myapp02.database.CrimeCursorWrapper;
import com.windsing.myapp02.database.CrimeDbSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.windsing.myapp02.database.CrimeDbSchema.CrimeTable.Cols.DATE;
import static com.windsing.myapp02.database.CrimeDbSchema.CrimeTable.Cols.SOLVED;
import static com.windsing.myapp02.database.CrimeDbSchema.CrimeTable.Cols.TITLE;
import static com.windsing.myapp02.database.CrimeDbSchema.CrimeTable.Cols.UUID;
import static com.windsing.myapp02.database.CrimeDbSchema.CrimeTable.NAME;

/**
 * Created by FC on 2017/5/15.
 */

public class CrimeLab {

    private static CrimeLab sCrimeLab;
//    private List<Crime> mCrimes;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    private CrimeLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();
//        mCrimes = new ArrayList<>(); // 保存crime对象
    }

    //单例模式
    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    /**
     * 负责处理数据库写入和更新操作的辅助类是ContentValues。它是个键值存储类，
     * 类似于Java的HashMap和前面用过的Bundle。
     * 不同的是， ContentValues只能用于处理SQLite数据
     *
     * @param crime
     * @return
     */
    // 将Crime记录转换为ContentValues实际就是在CrimeLab中创建ContentValues实例
    private static ContentValues getContentValues(Crime crime) {
        ContentValues values = new ContentValues();
        values.put(UUID, crime.getId().toString());
        values.put(TITLE, crime.getTitle().toString());
        values.put(DATE, crime.getDate().toString());
        values.put(SOLVED, crime.isSolved() ? 1 : 0);
        return values;
    }

    /**
     * 返回数组列表
     *
     * @return
     */
    public List<Crime> getCrimes() {
        List<Crime> crimes = new ArrayList<>();
        CrimeCursorWrapper cursor = queryCrimes(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                crimes.add(cursor.getCrime());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return crimes;
    }

    /**
     * 返回带有指定ID的Crime对象
     *
     * @param id
     * @return
     */
    public Crime getCrime(UUID id) {
        CrimeCursorWrapper cursor = queryCrimes(UUID + " = ?", new String[]{id.toString()}
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getCrime();
        } finally {
            cursor.close();
        }
    }

    /**
     * 向SQLite数据库中插入一条数据
     *
     * @param crime
     */
    public void addCrime(Crime crime) {
        ContentValues values = getContentValues(crime);
        mDatabase.insert(NAME, null, values);
    }

    /**
     * 更新SQLite数据库
     *
     * @param crime
     */
    public void updateCrime(Crime crime) {
        ContentValues values = getContentValues(crime);
        String uuidString = crime.getId().toString();
        mDatabase.update(NAME, values, UUID + " = ?", new String[]{uuidString});
    }

    private CrimeCursorWrapper queryCrimes(String WhereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                NAME, // table
                null, // columns
                WhereClause, // selection
                whereArgs, // selectionArgs
                null, // group by
                null, // having
                null  //order by
        );
        return new CrimeCursorWrapper(cursor);
    }
}

