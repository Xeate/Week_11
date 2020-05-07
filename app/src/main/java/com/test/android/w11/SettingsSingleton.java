package com.test.android.w11;

public class SettingsSingleton {

    private static SettingsSingleton settingsSingleton = new SettingsSingleton();
    private int fonttikoko = 14;
    private int leveys = 1000;
    private int korkeus = 1000;
    private int rivimaara = 3;
    private boolean estetty = false;
    private String annettu_syote = "";
    private String nimimerkki = "";
    private String kieli = "English";

    public static SettingsSingleton getInstance() {return settingsSingleton;}

    public void setFonttikoko(int fonttikoko) {
        this.fonttikoko = fonttikoko;
    }

    public void setLeveys(int leveys) {
        this.leveys = leveys;
    }

    public void setKorkeus(int korkeus) {
        this.korkeus = korkeus;
    }

    public void setRivimaara(int rivimaara) {
        this.rivimaara = rivimaara;
    }

    public void setEstetty(boolean estetty) {
        this.estetty = estetty;
    }

    public void setAnnettu_syote(String annettu_syote) {
        this.annettu_syote = annettu_syote;
    }

    public void setNimimerkki(String nimimerkki) {
        this.nimimerkki = nimimerkki;
    }

    public void setKieli(String kieli) {
        this.kieli = kieli;
    }

    public int getFonttikoko() {
        return fonttikoko;
    }

    public int getLeveys() {
        return leveys;
    }

    public int getKorkeus() {
        return korkeus;
    }

    public int getRivimaara() {
        return rivimaara;
    }

    public boolean getEstetty() {
        return  estetty;
    }

    public String getAnnettu_syote() {
        return annettu_syote;
    }

    public String getNimimerkki() {
        return nimimerkki;
    }

    public String getKieli() {
        return kieli;
    }
}
