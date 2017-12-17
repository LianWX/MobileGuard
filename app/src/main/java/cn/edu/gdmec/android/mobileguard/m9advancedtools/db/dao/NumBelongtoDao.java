package cn.edu.gdmec.android.mobileguard.m9advancedtools.db.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;



public class NumBelongtoDao {

    public static String getLocation(Context context,String phonenumber) {

        String location = phonenumber;
        String dbname=context.getFilesDir()+"/address.db";
        System.out.println(dbname);
        SQLiteDatabase db = SQLiteDatabase.openDatabase(
                dbname, null,
                SQLiteDatabase.OPEN_READONLY);
      /*String dbname = context.getFilesDir()+"/address.db";
        System.out.println (dbname);
        SQLiteDatabase db = SQLiteDatabase.openDatabase ( dbname, null, SQLiteDatabase.OPEN_READONLY );*/

        if (phonenumber.matches("^1[34578]\\d{9}$")) {
            // 手机号码的查询
            Cursor cursor = db
                    .rawQuery(
                            "select location from data2 where id=(select outkey from data1 where id=?)",
                            new String[] { phonenumber.substring(0, 7) });
            if (cursor.moveToNext()) {
                location = cursor.getString(0);
            }
            cursor.close();
        } else {// 其他电话
            switch (phonenumber.length()) {// 判断电话号码的长度
                case 3: // 110 120 119 121 999
                    if ("110".equals(phonenumber)) {
                        location = "匪警";
                    } else if ("120".equals(phonenumber)) {
                        location = "急救";
                    } else {
                        location = "报警号码";
                    }
                    break;
                case 4:
                    location = "模拟器";
                    break;
                case 5:
                    location = "客服电话";
                    break;
                case 7:
                    location = "本地电话";
                    break;
                case 8:
                    location = "本地电话";
                    break;
                default:
                    if(location.length()>=9&& location.startsWith("0")){
                        String address = null;
                        //select location from data2 where area = '10'
                        Cursor cursor = db.rawQuery("select location from data2 where area = ?", new String[]{location.substring(1, 3)});
                        if(cursor.moveToNext()){
                            String str = cursor.getString(0);
                            address = str.substring(0, str.length()-2);
                        }
                        cursor.close();
                        cursor = db.rawQuery("select location from data2 where area = ?", new String[]{location.substring(1, 4)});
                        if(cursor.moveToNext()){
                            String str = cursor.getString(0);
                            address = str.substring(0, str.length()-2);
                        }
                        cursor.close();
                        if(!TextUtils.isEmpty(address)){
                            location = address;
                        }
                    }
                    break;
            }
        }
        db.close();
        return location;
    }
}
