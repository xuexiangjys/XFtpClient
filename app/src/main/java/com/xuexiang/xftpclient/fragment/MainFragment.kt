package com.xuexiang.xftpclient.fragment

import android.view.KeyEvent
import android.view.View
import butterknife.OnClick
import com.xuexiang.xaop.annotation.SingleClick
import com.xuexiang.xftpclient.R
import com.xuexiang.xpage.annotation.Page
import com.xuexiang.xpage.base.XPageFragment
import com.xuexiang.xpage.utils.TitleBar
import com.xuexiang.xutil.common.ClickUtils

/**
 * FTP客户端
 * @author xuexiang
 * @since 2019/2/11 下午1:56
 */
@Page(name = "FTP客户端")
class MainFragment : XPageFragment() {

    /**
     * 布局的资源id
     *
     * @return
     */
    override fun getLayoutId(): Int {
        return R.layout.fragment_main
    }

    /**
     * 初始化控件
     */
    override fun initViews() {

    }

    /**
     * 初始化监听
     */
    override fun initListeners() {

    }


    override fun initTitleBar(): TitleBar {
        val titleBar = super.initTitleBar()
        titleBar.setLeftClickListener {ClickUtils.exitBy2Click()}
        titleBar.addAction(object :
            TitleBar.ImageAction(R.drawable.ic_setting) {
            @SingleClick
            override fun performAction(view: View) {
                openPage(SettingFragment::class.java)
            }
        })
        return titleBar
    }

    /**
     * 菜单、返回键响应
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
           ClickUtils.exitBy2Click()
        }
        return true
    }

    @OnClick(R.id.tv_ftp_c, R.id.tv_ftp_java)
    internal fun onClick(v: View) {
        when (v.id) {
            R.id.tv_ftp_c -> openPage(CFTPFragment::class.java)
            R.id.tv_ftp_java -> openPage(JavaFTPFragment::class.java)
            else -> {
            }
        }
    }


}
