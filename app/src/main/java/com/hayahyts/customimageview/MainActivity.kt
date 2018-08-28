package com.hayahyts.customimageview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.hayahyts.customimageview2.TextDirection
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        target.alignText(TextDirection.TOP_RIGHT)
    }
}
