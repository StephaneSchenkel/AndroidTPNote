package test.test.stephaneschenkelcryptomonnaies.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import test.test.stephaneschenkelcryptomonnaies.R;
import test.test.stephaneschenkelcryptomonnaies.help.Constants;
import test.test.stephaneschenkelcryptomonnaies.model.CryptoMonnaie;

public class CryptoFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crypto_form_layout);

        setResult(1);

        Button addButton = (Button)findViewById(R.id.AddCryptoFormButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText codeEdit = (EditText)findViewById(R.id.CodeEditText);
                EditText nomEdit = (EditText)findViewById(R.id.NomEditText);
                EditText tauxEdit = (EditText)findViewById(R.id.TauxEditText);
                EditText montantEdit = (EditText)findViewById(R.id.MontantEditText);

                if(!codeEdit.getText().toString().equals("") && !nomEdit.getText().toString().equals("") && !tauxEdit.getText().toString().equals("") && !montantEdit.getText().toString().equals("")){
                    CryptoMonnaie monnaie = new CryptoMonnaie(codeEdit.getText().toString(), nomEdit.getText().toString(), Double.valueOf(tauxEdit.getText().toString()), Double.valueOf(montantEdit.getText().toString()));

                    Intent resultIntent = new Intent();
                    resultIntent.putExtra(Constants.CRYPTO_EXTRA, monnaie);
                    setResult(0, resultIntent);
                    finish();
                }else{
                    Toast.makeText(CryptoFormActivity.this, "Remplissez tous les champs.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
