package test.test.stephaneschenkelcryptomonnaies.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CryptoMonnaie implements Parcelable {

    private String code;
    private String name;
    private double change;
    private double amount;

    public CryptoMonnaie(){

    }

    public CryptoMonnaie(String code, String name, double change, double amount) {
        this.code = code;
        this.name = name;
        this.change = change;
        this.amount = amount;
    }

    protected CryptoMonnaie(Parcel in) {
        this.code = in.readString();
        this.name = in.readString();
        this.change = in.readDouble();
        this.amount = in.readDouble();
    }

    public static final Creator<CryptoMonnaie> CREATOR = new Creator<CryptoMonnaie>() {
        @Override
        public CryptoMonnaie createFromParcel(Parcel in) {
            return new CryptoMonnaie(in);
        }

        @Override
        public CryptoMonnaie[] newArray(int size) {
            return new CryptoMonnaie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(code);
        dest.writeString(name);
        dest.writeDouble(change);
        dest.writeDouble(amount);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "CryptoMonnaie{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", change=" + change +
                ", amount=" + amount +
                '}';
    }
}
