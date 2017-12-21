package com.example.hakutosuzuki.visiblepassword

import android.support.design.widget.TextInputEditText
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Pattern 1
        val password = findViewById<View>(R.id.password) as TextInputEditText
        password.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        val passwordVisibilityCheckBox = findViewById<View>(R.id.password_visibility) as CheckBox


        // パスワード切り替えチェックボックス
        passwordVisibilityCheckBox.setOnClickListener { v: View ->

            // パスワードのカーソル位置保存
            val cursorPos = password.selectionStart

            val checked = passwordVisibilityCheckBox.isChecked
            if (checked) {
                // パスワード可視化
                password.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                // カーソル位置セット
                password.setSelection(cursorPos)
            } else {
                // パスワード不可視化
                password.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                password.setSelection(cursorPos)
            }
        }

        // Pattern 2
        val password2 = findViewById<View>(R.id.password2) as TextInputEditText
        password2.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        val password3 = findViewById<View>(R.id.password3) as TextView

        // パスワード入力listener
        password2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                Log.d("beforeTextChanged","beforeTextChanged : " + s)
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                password3.text = password2.text
            }

            override fun afterTextChanged(s: Editable) {
                Log.d("afterTextChanged","afterTextChanged : " + s)
            }
        })
    }
}
