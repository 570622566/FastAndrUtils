package com.hotapk.fastandrutils;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.hotapk.fastandrutils.bean.UserBean;

import java.io.IOException;

import cn.hotapk.fastandrutils.utils.FDatabaseUtils;
import cn.hotapk.fastandrutils.utils.FFileUtils;
import cn.hotapk.fastandrutils.utils.FLogUtils;
import cn.hotapk.fastandrutils.utils.FNetworkUtils;
import cn.hotapk.fastandrutils.utils.FSqlNetServer;
import cn.hotapk.fastandrutils.utils.FToastUtils;

public class FDatabaseDebugActivity extends FBaseActivity {
    private TextView database_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fdatabase_debug);
        database_tv = (TextView) findViewById(R.id.database_tv);
        UserBean userBean = new UserBean();
        userBean.setAge(10);
        userBean.setPassw("ddd");
        userBean.setUsername("ddd");
        userBean.setPhone("13933939");
        userBean.save();
        FLogUtils.getInstance().e(this.databaseList());
        FLogUtils.getInstance().e(this.getDatabasePath("demo.db"));
        FLogUtils.getInstance().e(FDatabaseUtils.getAllTableName("demo.db"));
        FLogUtils.getInstance().e(FDatabaseUtils.getAllTablefield(FDatabaseUtils.getDataBase("demo.db"), "userbean"));

        database_tv.setText(FNetworkUtils.getIPAddress());
//        FDatabaseUtils.getAllTableData(FDatabaseUtils.getDataBase("demo.db"), "userbean");
        FDatabaseUtils.getTableData(FDatabaseUtils.getDataBase("demo.db"), "userbean",10,0);

        FSqlNetServer fSqlNetServer = new FSqlNetServer(9999);
        try {
            fSqlNetServer.start(5000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
