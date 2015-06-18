package biz.globochem.sunshine.app;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.IOException;
import java.net.URI;
import java.util.List;

/**
 * Created by clebens on 6/14/15.
 */
public class SettingsActivity extends PreferenceActivity
        implements Preference.OnPreferenceChangeListener {

    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.pref_general);

        bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_location_key)));
        bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_units_key)));



        ((Preference) findPreference("pref_verify_loc"))
                .setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                    @Override
                    public boolean onPreferenceClick(Preference preference) {
                        String loc = PreferenceManager.getDefaultSharedPreferences(preference.getContext())
                                .getString(
                                        getString(R.string.pref_location_key),
                                        getString(R.string.pref_location_default)
                                );
                        Uri geoLocation = Uri.parse("geo:0,0?").buildUpon()
                            .appendQueryParameter("q", loc)
                            .build();

                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(geoLocation);
                        if (intent.resolveActivity(getPackageManager()) != null)
                            startActivity(intent);
                        else
                            Log.d("SettingsActivity", "Couldn't call " + loc);
                        return true;
                    }
                });

    }

    private void bindPreferenceSummaryToValue(Preference preference) {
        preference.setOnPreferenceChangeListener(this);

        onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object value) {
        String stringValue = value.toString();

        if (preference instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(stringValue);
            if (prefIndex >= 0) {
                preference.setSummary(listPreference.getEntries()[prefIndex]);
            }
        } else {
//            Log.v("SettingsActivity", "Preference Changed (stringValue): " + stringValue);
//            String loc = PreferenceManager.getDefaultSharedPreferences(getActivity())
//                    .getString(
//                            getString(R.string.pref_location_key),
//                            getString(R.string.pref_location_default)
//                    );
//            Log.v("SettingsActivity", "Preference Changed (loc): " + loc);
//            Log.v("SettingsActivity", "Preference Changed (getKey()): " + preference.getKey());

            preference.setSummary(stringValue);
        }

        return true;
    }

}
