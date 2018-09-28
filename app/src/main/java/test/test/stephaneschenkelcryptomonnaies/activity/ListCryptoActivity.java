package test.test.stephaneschenkelcryptomonnaies.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import test.test.stephaneschenkelcryptomonnaies.R;
import test.test.stephaneschenkelcryptomonnaies.adapter.ListCryptoAdapter;
import test.test.stephaneschenkelcryptomonnaies.fragment.CryptoInfoFragment;
import test.test.stephaneschenkelcryptomonnaies.help.Constants;
import test.test.stephaneschenkelcryptomonnaies.model.CryptoMonnaie;

public class ListCryptoActivity extends FragmentActivity {

    private ListView listView;
    private ListCryptoAdapter adapter;
    private ArrayList<CryptoMonnaie> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_crypto_layout);

        list = new ArrayList<>();

        Intent intent = getIntent();
        if(intent.hasExtra(Constants.LIST_EXTRA))
            list = intent.getParcelableArrayListExtra(Constants.LIST_EXTRA);

        listView = (ListView)findViewById(R.id.ListView);
        adapter = new ListCryptoAdapter(ListCryptoActivity.this, R.layout.list_crypto_row, list);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CryptoMonnaie cryptoMonnaie = (CryptoMonnaie)listView.getItemAtPosition(position);

                CryptoInfoFragment fragment = CryptoInfoFragment.newInstance(cryptoMonnaie);
                fragment.show(getSupportFragmentManager(), fragment.getClass().getName());
            }
        });
    }

    public void onFragmentResult(int resultCode, CryptoMonnaie cryptoMonnaie) {
        switch (resultCode) {
            case Constants.DELETE_CRYPTO:
                list.remove(cryptoMonnaie);
                break;
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void finish() {
        Intent resultIntent = new Intent();
        resultIntent.putParcelableArrayListExtra(Constants.LIST_EXTRA, list);
        setResult(0, resultIntent);

        super.finish();
    }
}
