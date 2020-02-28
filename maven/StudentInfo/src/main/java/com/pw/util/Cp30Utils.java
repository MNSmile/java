package com.pw.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;

public class Cp30Utils {
    private static DataSource ds = new ComboPooledDataSource();

    public static DataSource getDs() {
        return ds;
    }
}

