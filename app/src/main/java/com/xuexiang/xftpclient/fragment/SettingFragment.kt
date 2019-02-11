package com.xuexiang.xftpclient.fragment

import com.xuexiang.xftpclient.R
import com.xuexiang.xpage.annotation.Page
import com.xuexiang.xpage.base.XPageFragment

/**
 * @author xuexiang
 * @since 2019/2/11 下午7:25
 */
@Page(name = "设置")
class SettingFragment : XPageFragment() {
    /**
     * 布局的资源id
     *
     * @return
     */
    override fun getLayoutId(): Int {
        return R.layout.fragment_setting
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
}
