package com.yhsif.nene

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.preference.PreferenceManager

class LauncherActivity : Activity() {
  override fun onResume() {
    var origText: String = ""
    var origin: Uri? = null
    getIntent()?.let { intent ->
      when (intent.getAction()) {
        Intent.ACTION_VIEW -> origin = intent.getData()
        Intent.ACTION_SEND -> intent.getStringExtra(Intent.EXTRA_TEXT)?.let { text ->
          origText = text
          origin = Uri.parse(text)
        }
        else -> {}
      }
    }
    if (origin == null) {
      val text = getString(R.string.toast_not_twitter_url, origText)
      val msg = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        text
      } else {
        getString(
          R.string.toast_text_template,
          getString(R.string.app_name),
          text,
        )
      }
      Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    } else {
      val pref = PreferenceManager.getDefaultSharedPreferences(this)
      val url = origin!!.buildUpon().scheme("https").authority(
        pref.getString(SettingsActivity.KEY_NITTER_DOMAIN, SettingsActivity.DEFAULT_NITTER_DOMAIN),
      ).build()
      if (pref.getBoolean(SettingsActivity.KEY_USE_CUSTOM_TABS, SettingsActivity.DEFAULT_USE_CUSTOM_TABS)) {
        CustomTabsIntent.Builder().setDefaultColorSchemeParams(
          CustomTabColorSchemeParams.Builder().setToolbarColor(
            R.color.primary,
          ).build(),
        ).build().launchUrl(this, url)
      } else {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setData(url)
        startActivity(intent)
      }
    }
    finish()
    super.onResume()
  }
}
