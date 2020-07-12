package cczu.mobileorderfood;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by f on 2018-04-28.
 */

public class RegisterActivity extends Activity {
    public EditText metID, metPsword, metAffirmPsword, metPhone, metAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        metID = (EditText) findViewById(R.id.etRegisterUserId);
        metPsword = (EditText) findViewById(R.id.etRegisterUserPsword);
        metAffirmPsword = (EditText) findViewById(R.id.etRegisterUserAffirmPsword);
        metPhone = (EditText) findViewById(R.id.etRegisterUserMobilePhone);
        metAddress = (EditText) findViewById(R.id.etRegisterUserAddress);


        Button btnOK = (Button) findViewById(R.id.btnRegister);
        Button btnCancel = (Button) findViewById(R.id.btnCancel);

        Button.OnClickListener mybtnListener = new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnCancel:
                        finish();
                        break;
                    case R.id.btnRegister:
                        String strPsword = metPsword.getText().toString();
                        String strAffirmPsword = metAffirmPsword.getText().toString();
                        if (strPsword.equals(strAffirmPsword)) {
                            Uri info = Uri.parse("用户注册信息");
                            Intent intentUserInfo = new Intent(null, info);
                            intentUserInfo.putExtra("user", metID.getText().toString());
                            intentUserInfo.putExtra("password", metPsword.getText().toString());
                            intentUserInfo.putExtra("phone", metPhone.getText().toString());
                            intentUserInfo.putExtra("address", metAddress.getText().toString());
                            setResult(RESULT_OK, intentUserInfo);
                            finish();
                        } else {
                            Toast.makeText(RegisterActivity.this, "password not matched, type again", Toast.LENGTH_LONG).show();
                            metPsword.setText("");
                            metAffirmPsword.setText("");
                            metPsword.setFocusable(true);
                            metPsword.setFocusableInTouchMode(true);
                            metPsword.requestFocus();
                        }
                }

            }

        };

        btnOK.setOnClickListener(mybtnListener);
        btnCancel.setOnClickListener(mybtnListener);

    }


}
