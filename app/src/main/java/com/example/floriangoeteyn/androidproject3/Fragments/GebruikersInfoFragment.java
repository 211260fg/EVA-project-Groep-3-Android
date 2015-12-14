package com.example.floriangoeteyn.androidproject3.Fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.floriangoeteyn.androidproject3.R;
import com.example.floriangoeteyn.androidproject3.domein.DomeinController;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GebruikersInfoFragment extends Fragment {

    @Bind(R.id.gebruikersnaam) EditText gebruikersnaam;
    @Bind(R.id.geboortedatum) EditText geboortedatum;
    @Bind(R.id.leefsituatie) Spinner leefsituatie;
    @Bind(R.id.gezinsleden) Spinner gezinsleden;
    @Bind(R.id.ervaring) Spinner ervaring;

    private DomeinController dc;

    @Override
    public View onCreateView(LayoutInflater inflater,
                         ViewGroup container,
                         Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_gebruikers_info,
                container, false);
        //setContentView(R.layout.activity_gebruikers_info);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        dc = DomeinController.getInstance();


        //geboortedatum dag van vandaag invullen
        geboortedatum.setText("1/1/1990");

        //leefsituatie default: getrouwd (?)
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.leefsituaties, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        leefsituatie.setAdapter(adapter);

        //range van gezinsleden: 1-8 (?)
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(),
                R.array.gezinsleden, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gezinsleden.setAdapter(adapter2);

        //ervaring default: starter (?)
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(getActivity(),
                R.array.ervaringen, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ervaring.setAdapter(adapter3);

    }

    //TODO: day/month/year ipv month/day/year
    //TODO: "Cancel/Set" values
    @OnClick(R.id.geboortedatum)
    public void setGeboortedatum() {
        DialogFragment dialogFragment = new DatePickerFragment();
        dialogFragment.setTargetFragment(this, 1);
        dialogFragment.show(getFragmentManager(), "tag");
    }

    public EditText getGebruikersnaam() {
        return gebruikersnaam;
    }

    public EditText getGeboortedatum() {
        return geboortedatum;
    }

    public Spinner getLeefsituatie() {
        return leefsituatie;
    }

    public Spinner getGezinsleden() {
        return gezinsleden;
    }

    public Spinner getErvaring() {
        return ervaring;
    }

    /*
    @OnClick(R.id.leefsituatie)
    public void setLeefsituatie() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.leefsituatie)
                .setItems(R.array.leefsituaties, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        leefsituatie.setText(
                                getResources().getStringArray(R.array.leefsituaties)[which]);
                    }
                });
        builder.create().show();
    }

    @OnClick(R.id.gezinsleden)
    public void setGezinsleden() {


    }

    @OnClick(R.id.ervaring)
    public void setErvaring() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.ervaring)
                .setItems(R.array.ervaringen, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ervaring.setText(
                                getResources().getStringArray(R.array.ervaringen)[which]);
                    }
                });
        builder.create().show();
    }
    */

}
