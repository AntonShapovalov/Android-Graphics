package test.list.api.cursorloader;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.widget.CursorAdapter;

public class IncomingCallLoader implements LoaderManager.LoaderCallbacks<Cursor> {

    private Context context;
    private CursorAdapter adapter;
    private String filter;
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
        Uri baseUri;
        if (filter != null) {
            baseUri = Uri.withAppendedPath(CallLog.Calls.CONTENT_FILTER_URI,
                    Uri.encode(filter));
        } else {
            baseUri = CallLog.Calls.CONTENT_URI;
        }

        String select = "(" + CallLog.Calls.TYPE + " = " + CallLog.Calls.INCOMING_TYPE + ")";
        return new CursorLoader(context, baseUri,
                CALLS_SUMMARY_PROJECTION, select, null,
                CallLog.Calls.DEFAULT_SORT_ORDER);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }

}
