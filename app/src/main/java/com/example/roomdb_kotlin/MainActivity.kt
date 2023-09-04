package com.example.roomdb_kotlin

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.roomdb_kotlin.ui.theme.RoomdbKotlinTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoomdbKotlinTheme {
                //
            }
        }
    }
}
