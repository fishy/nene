package com.yhsif.nene

import android.os.Bundle

class AboutPreferenceFragment : BasePreferenceFragment() {
  override fun onCreatePreferences(
    savedInstanceState: Bundle?,
    rootKey: String?,
  ) {
    setPreferencesFromResource(R.xml.pref_about, rootKey)
    setHasOptionsMenu(true)
  }
}
