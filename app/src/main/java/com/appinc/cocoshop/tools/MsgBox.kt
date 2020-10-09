package com.appinc.cocoshop.tools

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AlertDialog

class MsgBox(private val context: Context) {

    fun Msg(msg: String?, title: String = "", isFinish: Boolean = false) {
        AlertDialog.Builder(context).setTitle(title)
            .setMessage(msg).setPositiveButton(android.R.string.yes) { _, _ ->
                if (isFinish) (context as Activity).finish()
            }.show()
    }
}