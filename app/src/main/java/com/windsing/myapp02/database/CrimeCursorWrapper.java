package com.windsing.myapp02.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.windsing.myapp02.module.Crime;

import java.util.Date;
import java.util.UUID;

import static com.windsing.myapp02.database.CrimeDbSchema.CrimeTable.Cols.DATE;
import static com.windsing.myapp02.database.CrimeDbSchema.CrimeTable.Cols.SOLVED;
import static com.windsing.myapp02.database.CrimeDbSchema.CrimeTable.Cols.TITLE;
import static com.windsing.myapp02.database.CrimeDbSchema.CrimeTable.Cols.UUID;

/**
 * Created by FC on 2017/5/22.
 */

public class CrimeCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public CrimeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Crime getCrime() {
        String uuidString = getString(getColumnIndex(UUID));
        String title = getString(getColumnIndex(TITLE));
        long date = getLong(getColumnIndex(DATE));
        int isSolved = getInt(getColumnIndex(SOLVED));

        Crime crime = new Crime(java.util.UUID.fromString(uuidString));
        crime.setTitle(title);
        crime.setDate(new Date(date));
        crime.setSolved(isSolved != 0);

        return crime;
    }
}
