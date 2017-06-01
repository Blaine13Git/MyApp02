package com.windsing.myapp02.database;

/**
 * Created by FC on 2017/5/22.
 */

public class CrimeDbSchema {

    // 创建数据库名称
    public static final class CrimeTable {
        public static final String NAME = "crimes";

        // 创建表的列
        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String SOLVED = "solved";
        }
    }


}
