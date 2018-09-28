package test.test.stephaneschenkelcryptomonnaies.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import test.test.stephaneschenkelcryptomonnaies.R;
import test.test.stephaneschenkelcryptomonnaies.help.Constants;
import test.test.stephaneschenkelcryptomonnaies.model.CryptoMonnaie;

public class MainActivity extends AppCompatActivity {

    private ArrayList<CryptoMonnaie> listCrypto = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addCryptoButton = (Button)findViewById(R.id.AddCryptoButton);
        Button listCryptoButton = (Button)findViewById(R.id.ListCryptoButton);

        addCryptoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CryptoFormActivity.class);
                startActivityForResult(intent, Constants.ADD_CRYPTO);
            }
        });

        listCryptoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listCrypto.isEmpty())
                    Toast.makeText(MainActivity.this, "Liste vide", Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = new Intent(MainActivity.this, ListCryptoActivity.class);
                    intent.putParcelableArrayListExtra(Constants.LIST_EXTRA, listCrypto);
                    startActivityForResult(intent, Constants.LIST_ACTIVITY);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch(requestCode){
            case Constants.ADD_CRYPTO:
                if(resultCode == 0 && data.hasExtra(Constants.CRYPTO_EXTRA)) {
                    CryptoMonnaie cryptoMonnaie = (CryptoMonnaie) data.getParcelableExtra(Constants.CRYPTO_EXTRA);
                    if(cryptoMonnaie != null) {
                        listCrypto.add(cryptoMonnaie);
                        Log.i(MainActivity.class.getName(),"Crypto monnaie ajoutée : " + cryptoMonnaie.toString());
                        Toast.makeText(MainActivity.this, cryptoMonnaie.getCode() + " ajoutée", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case Constants.LIST_ACTIVITY:
                if(data.hasExtra(Constants.LIST_EXTRA))
                    listCrypto = data.getParcelableArrayListExtra(Constants.LIST_EXTRA);
                break;
        }
    }
}
