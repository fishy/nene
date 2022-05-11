package com.yhsif.nene

import android.os.Bundle
import androidx.preference.Preference

class PrefsFragment : BasePreferenceFragment() {
  companion object {
    const val FRAGMENT_TAG = "prefs_fragment"
  }

  override fun onCreatePreferences(
    savedInstanceState: Bundle?,
    rootKey: String?,
  ) {
    setPreferencesFromResource(R.xml.pref_headers, rootKey)
    setHasOptionsMenu(true)

    val prefDomain: Preference? = findPreference(SettingsActivity.KEY_NITTER_DOMAIN)
    if (prefDomain != null) {
      SettingsActivity.bindPreferenceSummaryToString(
        prefDomain,
        SettingsActivity.DEFAULT_NITTER_DOMAIN,
      )
    }
    val prefCustomTabs: Preference? = findPreference(SettingsActivity.KEY_USE_CUSTOM_TABS)
    if (prefCustomTabs != null) {
      SettingsActivity.bindPreferenceSummaryToBoolean(
        prefCustomTabs,
        SettingsActivity.DEFAULT_USE_CUSTOM_TABS,
      )
    }
  }
}
