package test.list.api.cursorloader;

import android.app.FragmentManager;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.widget.CursorAdapter;

import test.list.api.R;

public class IncomingCallLoader implements LoaderManager.LoaderCallbacks<Cursor> {

    private Context context;
    private CursorAdapter adapter;
    public static final String[] CALLS_SUMMARY_PROJECTION = new String[]{
            CallLog.Calls._ID,
            CallLog.Calls.NUMBER,
            CallLog.Calls.DATE
    };
    public static final int COLUMN_NUMBER_INDEX = 1;
    public static final int COLUMN_DATE_INDEX = 2;

    public IncomingCallLoader(Context context, CursorAdapter adapter) {
        this.context = context;
        this.adapter = adapter;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri baseUri = CallLog.Calls.CONTENT_URI;

        String select = "(" + CallLog.Calls.TYPE + " = " + CallLog.Calls.INCOMING_TYPE + ")";
        return new CursorLoader(context, baseUri,
                CALLS_SUMMARY_PROJECTION, select, null,
                CallLog.Calls.DEFAULT_SORT_ORDER);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
        FragmentManager fragmentManager = ((CursorLoaderActivity) context).getFragmentManager();
        CursorLoaderListFragment listFragment = (CursorLoaderListFragment) fragmentManager.findFragmentById(R.id.activity_cursor_loader_list_fragment);
        if (listFragment != null) {
            if (listFragment.isResumed()) {
                listFragment.setListShown(true);
            } else {
                listFragment.setListShownNoAnimation(true);
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }

}
