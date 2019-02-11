package com.xuexiang.xftpclient.activity

import android.content.Context
import android.os.Bundle
import com.xuexiang.xftpclient.fragment.MainFragment
import com.xuexiang.xpage.base.XPageActivity
import com.xuexiang.xui.XUI
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

class MainActivity : XPageActivity() {

    override fun attachBaseContext(newBase: Context) {
        //注入字体
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        XUI.initTheme(this)
        super.onCreate(savedInstanceState)

        openPage(MainFragment::class.java)
    }
}
