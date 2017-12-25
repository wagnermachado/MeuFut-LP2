package br.com.meufut;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alunos on 05/10/17.
 */

public class Pessoa implements Parcelable{

    private String nome;
    private int time = 0;

    public Pessoa(String nome) {
        this.nome = nome;
    }

    public Pessoa(String nome, int time) {
        this.nome = nome;
        this.time = time;
    }

    protected Pessoa(Parcel in) {
        nome = in.readString();
    }

    public static final Creator<Pessoa> CREATOR = new Creator<Pessoa>() {
        @Override
        public Pessoa createFromParcel(Parcel in) {
            return new Pessoa(in);
        }

        @Override
        public Pessoa[] newArray(int size) {
            return new Pessoa[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nome);
    }

    public String getNome() {
        return this.nome;
    }
}
