package com.test.android.w11.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.test.android.w11.R;
import com.test.android.w11.SettingsSingleton;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    TextView text1;
    EditText text2;
    TextView nimimerkki;
    TextView kieli;

    final Handler h = new Handler();
    final int delay = 100;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        text1 = root.findViewById(R.id.text_1);
        text2 = root.findViewById(R.id.text_2);
        nimimerkki = root.findViewById(R.id.nimimerkki);
        kieli = root.findViewById(R.id.kieli);

        //Annetaan text1:lle sisältöä ja oikea fontti singletonista
        text1.setText(R.string.filler_txt);
        text1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, SettingsSingleton.getInstance().getFonttikoko());

        //Määritetään tekstikentän leveys ja korkeus arvojen kohde
        ViewGroup.LayoutParams textbox = (RelativeLayout.LayoutParams) text1.getLayoutParams();

        //Tarkastetaan sallittu - estetty -tila ja jatketaan sen mukaan
        if (SettingsSingleton.getInstance().getEstetty()) {
            text1.setText(SettingsSingleton.getInstance().getAnnettu_syote());
            text2.setFocusable(false);
        } else if (!SettingsSingleton.getInstance().getEstetty()) {
            text2.setFocusableInTouchMode(true);
            //Käynnistää käyttäjän syötteeen tallennuksen
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    String enteredData=text2.getText().toString();
                    SettingsSingleton.getInstance().setAnnettu_syote(enteredData);
                    h.postDelayed(this, delay);
                }
            }, delay);
        }

        //Otetaan arvot singletonista
        nimimerkki.setText(SettingsSingleton.getInstance().getNimimerkki());
        kieli.setText(SettingsSingleton.getInstance().getKieli());
        textbox.width = SettingsSingleton.getInstance().getLeveys();
        textbox.height = SettingsSingleton.getInstance().getKorkeus();
        text1.setMinLines(SettingsSingleton.getInstance().getRivimaara());
        text1.setMaxLines(SettingsSingleton.getInstance().getRivimaara());
        text1.setLayoutParams(textbox);

        return root;
    }
}