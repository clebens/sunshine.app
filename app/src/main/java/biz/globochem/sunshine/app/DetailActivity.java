package biz.globochem.sunshine.app;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.ShareActionProvider;
import android.widget.TextView;
import android.content.Intent;

public class DetailActivity extends ActionBarActivity {

    private ShareActionProvider mShareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.container, new DetailFragment())
//                    .commit();
//        }
    }

    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);

        getMenuInflater().inflate(R.menu.share_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.menu_item_share);

        mShareActionProvider = new ShareActionProvider(getApplicationContext());
        mShareActionProvider.setShareIntent(createShareIntent());
        MenuItemCompat.setActionProvider(menuItem, mShareActionProvider);

        return true;
    }

    private Intent createShareIntent() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setType("text/plain");

        TextView tv = (TextView) findViewById(R.id.detail_text);
        String forecast = tv.getText().toString();
        intent.putExtra(Intent.EXTRA_TEXT, forecast + " #SunshineApp");
        return intent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
