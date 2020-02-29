package com.example.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {

    //private var mCallbackManager: CallbackManager? = null
    private val G_PLUS_SCOPE = "oauth2:https://www.googleapis.com/auth/plus.me"
    private val USERINFO_SCOPE = "https://www.googleapis.com/auth/userinfo.profile"
    private val EMAIL_SCOPE = "https://www.googleapis.com/auth/userinfo.email"
    private val SCOPES = "$G_PLUS_SCOPE $USERINFO_SCOPE $EMAIL_SCOPE"
    internal var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken("923711877291-eigsid1l8ab6ttjc9dooep4dc3gjcsfq.apps.googleusercontent.com")
        .requestEmail()
        .build()
    internal lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        sign_in_button.setOnClickListener {
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, 124)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 124) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val idToken = account.idToken

            Log.e("111", idToken!!)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            // Signed in successfully, show authenticated UI.
            //updateUI(account);
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.e("123", "signInResult:failed code=" + e.statusCode)
            //updateUI(null);
        }

    }


}
