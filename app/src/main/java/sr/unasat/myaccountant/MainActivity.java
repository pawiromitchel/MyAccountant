package sr.unasat.myaccountant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sr.unasat.myaccountant.database.FinancialDAO;
import sr.unasat.myaccountant.entity.User;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;

    private FinancialDAO financialDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
    }

    public void doLogin(View view) {
        financialDAO = new FinancialDAO(this);
        User user = financialDAO.authenticateUser(usernameEditText.getText().toString(), passwordEditText.getText().toString());

        String notification = "";
        if (usernameEditText.getText().toString().isEmpty() || passwordEditText.getText().toString().isEmpty()) {
            notification = "Het inloggen is mislukt. Vul a.u.b. eerst een gebruikersnaam en wachtwoord in.";
        } else if(!user.getUserName().isEmpty()){
            notification = "Het inloggen is succesvol.";
        } else {
            notification = "Het inloggen is mislukt. Vul a.u.b. eerst een gebruikersnaam en wachtwoord in.";
        }
        Toast.makeText(this, notification, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
        startActivity(intent);
    }
}
