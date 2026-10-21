package com.xhy.xp.softaphelper;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;

import java.util.Map;

public class ConfigProvider extends ContentProvider {

    public static final String AUTHORITY = "com.xhy.xp.softaphelper.config";
    public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);

    public static final String METHOD_GET_ALL = "getAll";

    private SharedPreferences mPrefs;

    @Override
    public boolean onCreate() {
        mPrefs = Config.getPrefs(getContext());
        return true;
    }

    @Override
    public Bundle call(String method, String arg, Bundle extras) {
        if (METHOD_GET_ALL.equals(method)) {
            Bundle out = new Bundle();
            // 不能按 key 直接 getString：布尔值（开关）存在同一个 SharedPreferences 里，
            // 对布尔 key 调 getString 会抛 ClassCastException，导致整个配置读取失败。
            Map<String, ?> all = mPrefs.getAll();
            for (String key : Config.ALL_KEYS) {
                Object value = all.get(key);
                out.putString(key, value == null ? "" : String.valueOf(value));
            }
            return out;
        }
        return null;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public ParcelFileDescriptor openFile(Uri uri, String mode) {
        return null;
    }
}
