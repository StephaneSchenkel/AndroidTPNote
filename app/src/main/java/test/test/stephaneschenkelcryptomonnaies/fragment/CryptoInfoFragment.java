package test.test.stephaneschenkelcryptomonnaies.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import test.test.stephaneschenkelcryptomonnaies.R;
import test.test.stephaneschenkelcryptomonnaies.activity.CryptoFormActivity;
import test.test.stephaneschenkelcryptomonnaies.activity.ListCryptoActivity;
import test.test.stephaneschenkelcryptomonnaies.help.Constants;
import test.test.stephaneschenkelcryptomonnaies.model.CryptoMonnaie;

public class CryptoInfoFragment extends DialogFragment {

    public static CryptoInfoFragment newInstance(CryptoMonnaie cryptoMonnaie) {
        CryptoInfoFragment fragment = new CryptoInfoFragment();

        Bundle args = new Bundle();
        args.putParcelable(Constants.CRYPTO_EXTRA, cryptoMonnaie);
        fragment.setArguments(args);

        return fragment;
    }

    private CryptoMonnaie cryptoMonnaie;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        cryptoMonnaie = (CryptoMonnaie)getArguments().getParcelable(Constants.CRYPTO_EXTRA);

        View view = inflater.inflate(R.layout.info_crypto_layout, container, false);

        SetView(view);

        return view;
    }


    private EditText codeEdit;
    private EditText nomEdit;
    private EditText tauxEdit;
    private EditText montantEdit;

    private void SetView(View view){
        Button addButton = (Button)view.findViewById(R.id.ModifyCryptoInfoButton);
        Button deleteButton = (Button)view.findViewById(R.id.DeleteCryptoInfoButton);

        codeEdit = (EditText)view.findViewById(R.id.CodeEditTextInfo);
        nomEdit = (EditText)view.findViewById(R.id.NomEditTextInfo);
        tauxEdit = (EditText)view.findViewById(R.id.TauxEditTextInfo);
        montantEdit = (EditText)view.findViewById(R.id.MontantEditTextInfo);

        codeEdit.setText(cryptoMonnaie.getCode());
        nomEdit.setText(cryptoMonnaie.getName());
        tauxEdit.setText(String.valueOf(cryptoMonnaie.getChange()));
        montantEdit.setText(String.valueOf(cryptoMonnaie.getAmount()));

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!codeEdit.getText().toString().equals("") && !nomEdit.getText().toString().equals("") && !tauxEdit.getText().toString().equals("") && !montantEdit.getText().toString().equals("")){
                    cryptoMonnaie.setCode(codeEdit.getText().toString());
                    cryptoMonnaie.setName(nomEdit.getText().toString());
                    cryptoMonnaie.setAmount(Double.valueOf(montantEdit.getText().toString()));
                    cryptoMonnaie.setChange(Double.valueOf(tauxEdit.getText().toString()));

                    CloseFragment(Constants.MODIFY_CRYPTO);
                }else{
                    Toast.makeText(getContext(), "Remplissez tous les champs.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Supprimer la cryptomonnaie")
                        .setMessage("Voulez-vous vraiment supprimer cette cryptomonnaie ?")
                        .setPositiveButton("Oui", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                CloseFragment(Constants.DELETE_CRYPTO);
                            }

                        })
                        .setNegativeButton("Non", null)
                        .show();
            }
        });
    }

    private void CloseFragment(int resultCode){
        ListCryptoActivity activity = (ListCryptoActivity)getActivity();
        activity.onFragmentResult(resultCode, cryptoMonnaie);
        dismissAllowingStateLoss();
    }
}
