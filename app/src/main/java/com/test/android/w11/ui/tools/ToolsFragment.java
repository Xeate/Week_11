package com.test.android.w11.ui.tools;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.test.android.w11.R;
import com.test.android.w11.SettingsSingleton;

import java.util.ArrayList;
import java.util.List;

public class ToolsFragment extends Fragment {

    private ToolsViewModel toolsViewModel;

    EditText fontti;
    EditText leveys;
    EditText korkeus;
    EditText rivimaara;
    EditText nimimerkki;

    Spinner spinner;

    Button tila_btn;
    Button kieli_btn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tools, container, false);
        final TextView textView = root.findViewById(R.id.text_tools);
        toolsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        fontti = root.findViewById(R.id.fontti);
        leveys = root.findViewById(R.id.leveys);
        korkeus = root.findViewById(R.id.korkeus);
        rivimaara = root.findViewById(R.id.rivimaara);
        nimimerkki = root.findViewById(R.id.nimimerkki);
        tila_btn = (Button) root.findViewById(R.id.tila_btn);
        kieli_btn = (Button) root.findViewById(R.id.kieli_btn);
        spinner = (Spinner) root.findViewById(R.id.spinner);

        addItemsOnSpinner();

        //Asetetaan spinner oikeaan asentoon
        String kieli = SettingsSingleton.getInstance().getKieli();
        if (kieli.equals("Suomi")) {
            spinner.setSelection(0);
        } else if (kieli.equals("Englanti")) {
            spinner.setSelection(1);
        } else {
            spinner.setSelection(2);
        }

        Button button = (Button) root.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aktivoidaan käyttäjän antamat syötteet
                if (!fontti.getText().toString().equals("")) {
                    Integer fontti_value = Integer.parseInt(fontti.getText().toString());
                    SettingsSingleton.getInstance().setFonttikoko(fontti_value);
                    System.out.println("############## " + fontti_value + " ################");
                }
                if (!leveys.getText().toString().equals("")) {
                    Integer leveys_value = Integer.parseInt(leveys.getText().toString());
                    SettingsSingleton.getInstance().setLeveys(leveys_value);
                    System.out.println("############## " + leveys_value + " ################");
                }
                if (!korkeus.getText().toString().equals("")) {
                    Integer korkeus_value = Integer.parseInt(korkeus.getText().toString());
                    SettingsSingleton.getInstance().setKorkeus(korkeus_value);
                }
                if (!rivimaara.getText().toString().equals("")) {
                    Integer rivimaara_value = Integer.parseInt(rivimaara.getText().toString());
                    SettingsSingleton.getInstance().setRivimaara(rivimaara_value);
                }
                if (!nimimerkki.getText().toString().equals("")) {
                    String nimimerkki_value = nimimerkki.getText().toString();
                    SettingsSingleton.getInstance().setNimimerkki(nimimerkki_value);
                }

                SettingsSingleton.getInstance().setKieli(String.valueOf(spinner.getSelectedItem()));

            }
        });

        //Asetetaan nimimerkille arvo, jos annettu aikaisemmin
        nimimerkki.setText(SettingsSingleton.getInstance().getNimimerkki());

        //Asetetaan muokkaus -napille oikea teksti
        if (SettingsSingleton.getInstance().getEstetty()) {
            tila_btn.setText("SALLI MUOKKAUS");
        } else {
            tila_btn.setText("ESTÄ MUOKKAUS");
        }

        tila_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tekstikentän muokkaus (sallittu - estetty)
                String tag = tila_btn.getText().toString();
                if (tag.equals("ESTÄ MUOKKAUS")) {
                    tila_btn.setText("SALLI MUOKKAUS");
                    SettingsSingleton.getInstance().setEstetty(true);
                    System.out.println("############## ESTÄ ################");

                } else if (tag.equals("SALLI MUOKKAUS")) {
                    tila_btn.setText("ESTÄ MUOKKAUS");
                    SettingsSingleton.getInstance().setEstetty(false);
                    System.out.println("############## SALLI ################");
                }
            }
        });

        return root;
    }

    public void addItemsOnSpinner() {

        List<String> list = new ArrayList<String>();

        list.add("Suomi");
        list.add("Englanti");
        list.add("Ruotsi");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

    }
}