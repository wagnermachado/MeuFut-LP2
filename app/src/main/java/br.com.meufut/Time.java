package br.com.meufut;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alunos on 05/10/17.
 */

public class Time implements Parcelable{

    private String nome;
    private String elenco;

    public Time(String nome, String elenco) {
        this.nome = nome;
        this.elenco = elenco;
    }

    protected Time(Parcel in) {
        nome = in.readString();
        elenco = in.readString();
    }

    public static final Creator<Time> CREATOR = new Creator<Time>() {
        @Override
        public Time createFromParcel(Parcel in) {
            return new Time(in);
        }

        @Override
        public Time[] newArray(int size) {
            return new Time[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nome);
        dest.writeString(elenco);
    }

    public String getNome() {
        return this.nome;
    }
    public String getElenco() { return this.elenco; }

}
