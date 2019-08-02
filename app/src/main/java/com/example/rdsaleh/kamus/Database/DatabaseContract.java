package com.example.rdsaleh.kamus.Database;

import android.provider.BaseColumns;

public class DatabaseContract {

    static String TABLE_INEN = "tb_inen";
    static String TABLE_ENIN = "tb_enin";

    static final class KamusColumns implements BaseColumns{

        static String WORD      = "word";
        static String TRANSLATE = "translate";

    }

}
