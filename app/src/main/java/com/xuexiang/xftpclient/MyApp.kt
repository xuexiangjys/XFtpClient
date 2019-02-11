package com.xuexiang.xftpclient

import android.app.Application
import android.content.Context
import com.xuexiang.xaop.XAOP
import com.xuexiang.xaop.util.PermissionUtils
import com.xuexiang.xpage.AppPageConfig
import com.xuexiang.xpage.PageConfig
import com.xuexiang.xpage.PageConfiguration
import com.xuexiang.xpage.model.PageInfo
import com.xuexiang.xui.XUI
import com.xuexiang.xutil.XUtil
import com.xuexiang.xutil.common.StringUtils
import com.xuexiang.xutil.tip.ToastUtils

/**
 * @author xuexiang
 * @since 2019/2/11 上午11:41
 */
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initLibs()

        initUI()
    }

    private fun initUI() {
        XUI.init(this)
        XUI.debug(true)
    }

    /**
     * 初始化基础库
     */
    private fun initLibs() {
        XUtil.init(this)
        XUtil.debug(true)

        //自动注册页面
        PageConfig.getInstance()
            .setPageConfiguration { AppPageConfig.getInstance().pages }
            .debug("PageLog")
            .enableWatcher(false)
            .init(this)

        //初始化插件
        XAOP.init(this)
        //日志打印切片开启
        XAOP.debug(true)
        //设置动态申请权限切片 申请权限被拒绝的事件响应监听
        XAOP.setOnPermissionDeniedListener { permissionsDenied ->
            ToastUtils.toast(
                "权限申请被拒绝:" + StringUtils.listToString(
                    permissionsDenied,
                    ","
                )
            )
        }
    }
}
