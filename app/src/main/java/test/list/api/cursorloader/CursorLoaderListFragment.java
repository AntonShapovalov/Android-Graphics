package test.list.api.cursorloader;

import android.app.ListFragment;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import test.list.api.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class CursorLoaderListFragment extends ListFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setEmptyText("No phone numbers");
        setListShown(false);

        IncomingCallAdapter adapter = new IncomingCallAdapter(getActivity());
        setListAdapter(adapter);
        getLoaderManager().initLoader(0, null, new IncomingCallLoader(getActivity(), adapter));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.i("FragmentComplexList", "Item clicked: " + id);
    }
}
