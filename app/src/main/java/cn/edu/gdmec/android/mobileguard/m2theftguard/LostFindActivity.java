package cn.edu.gdmec.android.mobileguard.m2theftguard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.edu.gdmec.android.mobileguard.R;

/**
 * Created by admin on 2017/10/14.
 */

public class LostFindActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_find);
        startSetUpActivity();
    }
    private void startSetUpActivity(){
        Intent intent=new Intent(LostFindActivity.this,Setup1Activity.class);
        startActivity(intent);
        finish();
    }
}
