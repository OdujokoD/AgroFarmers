package com.example.agrofarmers.controllers;

import android.provider.BaseColumns;

/**
 * Created by Larry on 6/16/2016.
 */
public class DBContract {
    public DBContract(){
    }

    public static abstract class Rule implements BaseColumns {
        public static final String TABLENAME = "TRANSPORT_REQUEST";
        public static final String REQUEST_ID = "request_id";
        public static final String PRODUCT = "product";
        public static final String WEIGHT = "weight";
        public static final String TRANSPORT_TYPE = "transport_type";
        public static final String STATUS = "status";
    }
}
