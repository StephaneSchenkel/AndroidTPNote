package test.test.stephaneschenkelcryptomonnaies.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import test.test.stephaneschenkelcryptomonnaies.R;
import test.test.stephaneschenkelcryptomonnaies.help.CodeImageConverter;
import test.test.stephaneschenkelcryptomonnaies.help.Constants;
import test.test.stephaneschenkelcryptomonnaies.model.CryptoMonnaie;

public class ListCryptoAdapter extends ArrayAdapter<CryptoMonnaie> {

    private List<CryptoMonnaie> list;
    private Context context;
    private int resource;

    public ListCryptoAdapter(@NonNull Context context, int resource, @NonNull List<CryptoMonnaie> objects) {
        super(context, resource, objects);
        this.list = objects;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        CryptoMonnaie cryptoMonnaie = list.get(position);

        View v = convertView;

        if(v == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(context);
            v = vi.inflate(resource, null);
        }

        if(cryptoMonnaie != null){
            ImageView codeView = (ImageView)v.findViewById(R.id.CodeView);
            TextView montantView = (TextView)v.findViewById(R.id.MontantTextView);
            TextView nomView = (TextView)v.findViewById(R.id.NomTextView);
            TextView tauxView = (TextView)v.findViewById(R.id.TauxTextView);
            TextView montantEurView = (TextView)v.findViewById(R.id.MontantEURTextView);

            codeView.setImageResource(CodeImageConverter.Convert(cryptoMonnaie.getCode()));
            montantView.setText(Constants.DECIMAL_FORMATTER.format(cryptoMonnaie.getAmount()));
            nomView.setText(cryptoMonnaie.getName());
            tauxView.setText(Constants.DECIMAL_FORMATTER.format(cryptoMonnaie.getChange()));
            montantEurView.setText(Constants.DECIMAL_FORMATTER.format(cryptoMonnaie.getAmount() / cryptoMonnaie.getChange()));
        }

        return v;
    }
}
