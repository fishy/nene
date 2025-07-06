package com.yhsif.nene

import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager

public class SettingsActivity :
  AppCompatActivity(),
  PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {

  companion object {
    public const val KEY_NITTER_DOMAIN = "nitter_domain"
    public const val DEFAULT_NITTER_DOMAIN = "nitter.net"
    public const val KEY_USE_CUSTOM_TABS = "use_custom_tabs"
    public const val DEFAULT_USE_CUSTOM_TABS = true
    public const val KEY_INCOGNITO_CUSTOM_TABS = "incognito_custom_tabs"
    public const val DEFAULT_INCOGNITO_CUSTOM_TABS = false

    val prefBinder = object : Preference.OnPreferenceChangeListener {
      override fun onPreferenceChange(
        pref: Preference,
        value: Any,
      ): Boolean {
        when (pref.getKey()) {
          KEY_USE_CUSTOM_TABS -> {
            pref.setSummary(
              if (value == true) {
                R.string.pref_desc_use_custom_tabs_yes
              } else {
                R.string.pref_desc_use_custom_tabs_no
              },
            )
          }
          KEY_INCOGNITO_CUSTOM_TABS -> {
            pref.setSummary(
              if (value == true) {
                R.string.pref_desc_incognito_custom_tabs_yes
              } else {
                R.string.pref_desc_incognito_custom_tabs_no
              },
            )
          }
          KEY_NITTER_DOMAIN -> {
            if (value == "") {
              // reject empty string value
              return false
            }
            pref.setSummary(value.toString())
          }
          // For all other preferences, set the summary to the value's
          // simple string representation.
          else -> pref.setSummary(value.toString())
        }
        return true
      }
    }

    fun bindPreferenceSummaryToString(
      preference: Preference,
      defaultValue: String,
    ) {
      preference.setOnPreferenceChangeListener(prefBinder)
      prefBinder.onPreferenceChange(
        preference,
        PreferenceManager
          .getDefaultSharedPreferences(preference.getContext())
          .getString(preference.getKey(), defaultValue),
      )
    }

    fun bindPreferenceSummaryToBoolean(
      preference: Preference,
      defaultValue: Boolean,
    ) {
      preference.setOnPreferenceChangeListener(prefBinder)
      prefBinder.onPreferenceChange(
        preference,
        PreferenceManager
          .getDefaultSharedPreferences(preference.getContext())
          .getBoolean(preference.getKey(), defaultValue),
      )
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setupActionBar()
    setContentView(R.layout.settings)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
      // Add additional 46dx padding to top for api 35+,
      // as they no longer auto add the padding for the action bar.
      val dp = 46f
      val px = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        resources.displayMetrics,
      )
      findViewById<View>(R.id.fragment_container).setPadding(0, px.toInt(), 0, 0)
    }

    if (savedInstanceState == null) {
      var frag = getSupportFragmentManager().findFragmentByTag(
        PrefsFragment.FRAGMENT_TAG,
      )
      if (frag == null) {
        frag = PrefsFragment()
      }

      getSupportFragmentManager().commit {
        replace(R.id.fragment_container, frag, PrefsFragment.FRAGMENT_TAG)
      }
    }
  }

  fun setupActionBar() {
    // Show the Up button in the action bar.
    getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.getItemId() == android.R.id.home) {
      onBackPressed()
    }
    return super.onOptionsItemSelected(item)
  }

  override fun onPreferenceStartFragment(
    caller: PreferenceFragmentCompat,
    pref: Preference,
  ): Boolean {
    getSupportFragmentManager().commit {
      val key = pref.getKey()
      val args = Bundle()
      args.putString(
        PreferenceFragmentCompat.ARG_PREFERENCE_ROOT,
        key,
      )
      val frag: BasePreferenceFragment? =
        when (key) {
          getString(R.string.pref_tag_about) -> AboutPreferenceFragment()
          else -> null
        }
      if (frag != null) {
        frag.setArguments(args)
        replace(R.id.fragment_container, frag, key)
      }
      addToBackStack(key)
    }
    return true
  }
}
