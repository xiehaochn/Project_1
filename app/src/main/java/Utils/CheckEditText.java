package Utils;

import android.widget.EditText;

/**
 * Created by Administrator on 2016/1/13.
 */
public class CheckEditText {
    public static boolean isEmpty(EditText editText){
        if(editText.getText().toString().equals("")){
            return true;
        }else {
            return false;
        }
    }
    public static boolean isEqual(EditText editText1,EditText editText2){
        if(editText1.getText().toString().equals(editText2.getText().toString())){
            return true;
        }else {
            return false;
        }
    }
}
