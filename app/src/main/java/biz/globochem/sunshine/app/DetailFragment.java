package biz.globochem.sunshine.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.util.Log;


public class DetailFragment extends Fragment {


    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Intent intent = getActivity().getIntent();
        Log.v("DetailFragment", intent.toString());
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            String message = intent.getStringExtra(Intent.EXTRA_TEXT);
            TextView detailText = ((TextView) rootView.findViewById(R.id.detail_text));
            detailText.setText(message);
            detailText.setTextSize(40);
        }
        return rootView;
    }


}
