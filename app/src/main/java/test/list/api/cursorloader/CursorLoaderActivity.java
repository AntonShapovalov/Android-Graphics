package test.list.api.cursorloader;

import android.app.ListActivity;
import android.os.Bundle;
import android.provider.CallLog;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import test.list.api.R;

public class CursorLoaderActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursor_loader);

        IncomingCallAdapter adapter = new IncomingCallAdapter(this);

        final AutoCompleteTextView tv = (AutoCompleteTextView) findViewById(R.id.activity_cursor_loader_auto_text);
        tv.setAdapter(adapter);
/*
        tv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                tv.showDropDown();
                return false;
            }
        });
*/
        setListAdapter(adapter);

        getLoaderManager().initLoader(0, null, new IncomingCallLoader(this, adapter));

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.i("FragmentComplexList", "Item clicked: " + id);
    }

}
