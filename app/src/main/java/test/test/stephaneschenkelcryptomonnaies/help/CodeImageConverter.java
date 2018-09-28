package test.test.stephaneschenkelcryptomonnaies.help;

import test.test.stephaneschenkelcryptomonnaies.R;

public class CodeImageConverter {

    public static int Convert(String code){
        int result = R.drawable.imagenotfound;
        switch(code) {
            case "BTC":
                result = R.drawable.bitcoin;
                break;
            case "XRP":
                result = R.drawable.ripple;
                break;
            case "ETH":
                result = R.drawable.ethereum;
                break;
        }
        return result;
    }

}
