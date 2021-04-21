package com.dicoding.sub2github.ui.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.dicoding.sub2github.R
import com.dicoding.sub2github.data.model.Reminder
import com.dicoding.sub2github.databinding.ActivityFavoriteBinding
import com.dicoding.sub2github.databinding.ActivitySettingsBinding
import com.dicoding.sub2github.preference.ReminderPreference
import com.dicoding.sub2github.receiver.AlarmReceiver

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private lateinit var reminder: Reminder
    private lateinit var alarmReceiver: AlarmReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTitle("Setting's Alarm")

        val reminderPreference = ReminderPreference(this)
        if (reminderPreference.getReminder().isReminded) {
            binding.switch1.isChecked = true
        } else {
            binding.switch1.isChecked = false
        }
        alarmReceiver = AlarmReceiver()

        binding.switch1.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                saveReminder(true)
                alarmReceiver.setRepeatingAlarm(
                    this,
                    "RepeatingAlarm",
                    "01:56",
                    "Github Reminder!!"
                )
            } else {
                saveReminder(false)
                alarmReceiver.cancelAlarm(this)
            }
        }
    }

    private fun saveReminder(state: Boolean) {
        val reminderPreference = ReminderPreference(this)
        reminder = Reminder()
        reminder.isReminded = state
        reminderPreference.setReminder(reminder)
    }
}