/*
 * Copyright (C) 2018 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xuexiang.xftpclient.utils

import android.content.Context
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.xuexiang.xftpclient.R
import com.xuexiang.xutil.XUtil
import com.xuexiang.xutil.resource.ResUtils

/**
 * <pre>
 * desc   : 管理toast的类，整个app用该类来显示toast
 * author : xuexiang
 * time   : 2018/4/27 下午8:38
</pre> *
 */
object ToastUtils {

    private var mToast: Toast? = null

    /**
     * 是否是主线程
     *
     * @return
     */
    private val isMainLooper: Boolean
        get() = Looper.getMainLooper() == Looper.myLooper()

    /**
     * 显示toast在主线程中
     *
     * @param text     提示信息
     * @param duration 提示长度
     */
    @JvmOverloads
    fun toast(text: String, duration: Int = Toast.LENGTH_SHORT) {
        if (isMainLooper) {
            showToast(text, duration)
        } else {
            XUtil.runOnUiThread { showToast(text, duration) }
        }
    }

    /**
     * 显示toast在主线程中
     *
     * @param resId
     */
    fun toast(resId: Int) {
        toast(ResUtils.getString(resId))
    }

    /**
     * 显示单一的toast
     *
     * @param text     提示信息
     * @param duration 提示长度
     */
    private fun showToast(text: String, duration: Int) {
        if (mToast == null) {
            mToast = makeText(XUtil.getContext(), text, duration)
        } else {
            (mToast!!.view.findViewById<View>(R.id.tv_info) as TextView).text = text
        }
        mToast!!.show()
    }


    /**
     * 构建Toast
     *
     * @param context
     * @param msg
     * @param duration
     * @return
     */
    private fun makeText(context: Context, msg: String, duration: Int): Toast {
        val view = LayoutInflater.from(context).inflate(R.layout.xutil_layout_toast, null)
        val toast = Toast(context)
        toast.view = view
        val tv = view.findViewById<TextView>(R.id.tv_info)
        tv.background.alpha = 100
        tv.text = msg
        toast.duration = duration
        return toast
    }

    /**
     * 取消toast显示
     */
    fun cancelToast() {
        if (mToast != null) {
            mToast!!.cancel()
        }
    }
}
