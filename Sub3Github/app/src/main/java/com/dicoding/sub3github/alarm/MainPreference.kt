package com.dicoding.sub3github.alarm

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.dicoding.sub3github.R

class MainPreference : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {
    private lateinit var NAME: String

    private lateinit var alarmReceiver: AlarmReceiver
    private lateinit var switchAlaPreference: SwitchPreference
    companion object {
        private const val DEFAULT_VALUE = "Tidak Ada"
    }
    override fun onCreatePreferences(savedInstanceState: Bundle?, s: String?) {
        addPreferencesFromResource(R.xml.preferences)
        init()
        setSummaries()
        alarmReceiver = AlarmReceiver()
    }
    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }
    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    private fun setSummaries() {
        val sh = preferenceManager.sharedPreferences
        switchAlaPreference.isChecked = sh.getBoolean(NAME, false)

    }

    private fun init() {
        NAME = resources.getString(R.string.key_name)

        switchAlaPreference = findPreference<SwitchPreference>(NAME) as SwitchPreference
    }

    override fun onSharedPreferenceChanged( sharedPreferences: SharedPreferences?, key: String?) {
        if (key == NAME) {
            var a : Boolean = true
            switchAlaPreference.isChecked = sharedPreferences!!.getBoolean(NAME, false)
            if (switchAlaPreference.isChecked){
                alarmReceiver.setRepeatingAlarm(requireContext(), AlarmReceiver.TYPE_REPEATING,
                    "09:00", "Buka Aplikasi sekarang!!").also {
                    alarmReceiver.showAlarmNotification(requireContext(),"Reminder","Buka Aplikasi Sekarang",101)
                }
            }else{
                alarmReceiver.cancelAlarm(requireContext(), AlarmReceiver.TYPE_REPEATING)
            }

        }
    }

}

