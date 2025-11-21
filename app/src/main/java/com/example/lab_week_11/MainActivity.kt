package com.example.lab_week_11

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lab_week_11.setting.SettingsApplication
import com.example.lab_week_11.setting.SettingsViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ambil settingsStore dari Application
        val settingsStore = (application as SettingsApplication).settingsStore

        // Buat ViewModel dengan factory
        val settingsViewModel = ViewModelProvider(
            this,
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return SettingsViewModel(settingsStore) as T
                }
            }
        )[SettingsViewModel::class.java]

        // Observe data dari DataStore
        settingsViewModel.textLiveData.observe(this) { text ->
            findViewById<TextView>(R.id.activity_main_text_view).text = text
        }

        // Tombol save
        findViewById<Button>(R.id.activity_main_button).setOnClickListener {
            val input = findViewById<EditText>(R.id.activity_main_edit_text).text.toString()
            settingsViewModel.saveText(input)
        }
    }
}
