package io.jakec.fourteenerchecklist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import io.jakec.fourteenerchecklist.fourteeners.Fourteener;
import io.jakec.fourteenerchecklist.fourteeners.FourteenerDao;
import io.jakec.fourteenerchecklist.fourteeners.XmlFourteenerDaoImpl;

public class MainActivity extends AppCompatActivity {

    private ListView fourteenerListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fourteenerListView = (ListView) findViewById(R.id.fourteener_list_view);

        // Load arrayList with fourteener names
        FourteenerDao fourteenerDao = new XmlFourteenerDaoImpl(this);
        List<Fourteener> fourteeners = fourteenerDao.getAllFourteeners();

        String[] listItems = new String[fourteeners.size()];

        int i = 0;
        for (Fourteener fourteener: fourteeners) {
            listItems[i] = fourteener.getName();
            i++;
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        fourteenerListView.setAdapter(adapter);
    }
}
