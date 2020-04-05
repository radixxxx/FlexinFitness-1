package com.example.flexinfitness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

// region DashBoard class ===========================================================================
public class DashBoard extends AppCompatActivity implements View.OnClickListener
{
    // region View & ViewGroups
    Button logout;
    Button logButton;
    Button settingsButton;
    Button workoutPlannerButton;
    Button tutorialButton;
    Button btnShare;

    TextView name;
    // endregion

    // region 0nCreate() ============================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        final FirebaseAuth mAuth = FirebaseAuth.getInstance();

        // region Connecting Views & ViewGroups
        logout = findViewById(R.id.logOutButton);
        name = findViewById(R.id.nameTextView);
        logButton = findViewById(R.id.logButton);
        settingsButton = findViewById(R.id.settingsButton);
        workoutPlannerButton = findViewById(R.id.workoutPlannerButton);
        tutorialButton = findViewById(R.id.tutorialButton);
        btnShare = findViewById(R.id.btnShare);
        // endregion

        // region Setting OnClicks;
        logButton.setOnClickListener(this);
        settingsButton.setOnClickListener(this);
        workoutPlannerButton.setOnClickListener(this);
        tutorialButton.setOnClickListener(this);
        btnShare.setOnClickListener(this);
        // endregion

        final GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);

        if (signInAccount != null)
        {
            // Retrieves user info to display.
            name.setText(signInAccount.getDisplayName());

            // Listens for a click on the sign out button.
            logout.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {// connecting Views & ViewGroups to their XML ids
                    final GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
                            .requestIdToken(getString(R.string.default_web_client_id))
                            .requestEmail()
                            .build();

                    // Creates the current google sign in client instance
                    final GoogleSignInClient signInClient = GoogleSignIn.getClient(DashBoard.this, signInOptions);

                    // Signs out if firebase client
                    mAuth.signOut();
                    // Attempts to sign out of google client and listens for a success or failure
                    signInClient.signOut().addOnSuccessListener(new OnSuccessListener<Void>()
                    {
                        @Override
                        public void onSuccess(Void aVoid)
                        {
                            Toast.makeText(DashBoard.this, signInAccount.getEmail() + " Logged out Successfull!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    }).addOnFailureListener(new OnFailureListener()
                    {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {
                            Toast.makeText(DashBoard.this, signInAccount.getEmail() + " Failed to log out!", Toast.LENGTH_SHORT).show();

                        }

                    });
                }
            });
        }
        // If they're in the application without being logged into, send them to the login screen.
        else
        {
            mAuth.signOut();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    } // endregion onCreate() ============================================================================

    // region onClick() =============================================================================
    @Override
    public void onClick(View v)
    {
        switch( v.getId())
        {
                // switch to log
            case R.id.logButton:
                Intent gotoLOGDIARYHOMEPAGE = new Intent(getApplicationContext(), log.class);
                startActivity(gotoLOGDIARYHOMEPAGE);
                break;
                // switch to workout planner
            case R.id.workoutPlannerButton:
                Intent workoutPlanner = new Intent(DashBoard.this, workoutPlanner.class);
                startActivity(workoutPlanner);
                break;
                // switch to tutorial
            case R.id.tutorialButton:
                Intent tutorial = new Intent(DashBoard.this, tutorial.class);
                startActivity(tutorial);
                break;
                // switch to settings
            case R.id.settingsButton:
                Intent goToSettings = new Intent(getApplicationContext(), settingOptions.class);
                startActivity(goToSettings);
                break;
                // switch to share
            case R.id.btnShare:
                Intent goToShare = new Intent(DashBoard.this, facebook.class);
                startActivity(goToShare);
            default:
                break;
        }
    } // endregion onClick() =============================================================================
} // endregion DashBoard class ===========================================================================
