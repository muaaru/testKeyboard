package com.example.testkeyboard

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.testkeyboard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var selectionStart = 0
    private var selectionEnd = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("DEBUG" , "onCreate")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        Log.d("DEBUG" , "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("DEBUG" , "onPause")
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        Log.d("DEBUG" , "onWindowFocusChanged hasFocus : $hasFocus")
        if(hasFocus) {
            binding.editText.setSelection(selectionStart,selectionEnd)
            showSoftInput(binding.editText)
        } else {
            selectionStart = binding.editText.selectionStart
            selectionEnd = binding.editText.selectionEnd
            binding.editText.clearFocus()
        }
    }

    private fun showSoftInput(view: View) {
        if(view.requestFocus()){
            val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        } else {
            Log.w("DEBUG" , "couldn't get focus")
        }
    }
}