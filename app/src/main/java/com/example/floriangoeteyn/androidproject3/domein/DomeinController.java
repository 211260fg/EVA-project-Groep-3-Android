package com.example.floriangoeteyn.androidproject3.domein;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.floriangoeteyn.androidproject3.persistentie.PersistentieController;

import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;

import retrofit.Call;

/**
 * Created by daan on 14/10/2015.
 */
public class DomeinController {

    private Gebruiker gebruiker;
    private PersistentieController pc;

    private static DomeinController dc;

    private DomeinController() {
        pc = new PersistentieController();
    }

    public static DomeinController getInstance() {
        if (dc == null) {
            dc = new DomeinController();
        }
        return dc;
    }

    public String getFacebookInfo(String req) {
        return pc.getFacebookInfo(req);
    }

    public Call<JSONObject> login(String email, String wachtwoord) throws IOException {
        return pc.login(email, wachtwoord);
    }

    public Call<JSONObject> registreer(String email, String wachtwoord) throws IOException {
        return pc.registreer(email, wachtwoord);
    }

    public Gebruiker getGebruiker() {
        return gebruiker;
    }

    public void setGebruiker(Gebruiker gebruiker) {
        this.gebruiker = gebruiker;
    }

    public PersistentieController getPc() {
        return pc;
    }

    public void setPc(PersistentieController pc) {
        this.pc = pc;
    }
}
