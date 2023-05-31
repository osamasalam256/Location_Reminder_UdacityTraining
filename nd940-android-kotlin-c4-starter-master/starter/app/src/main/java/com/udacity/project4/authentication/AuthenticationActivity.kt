package com.udacity.project4.authentication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
<<<<<<< HEAD
import androidx.databinding.DataBindingUtil
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.udacity.project4.R
=======
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
>>>>>>> a2f1b96 (update project with testing)
import com.udacity.project4.databinding.ActivityAuthenticationBinding
import com.udacity.project4.locationreminders.RemindersActivity

/**
 * This class should be the starting point of the app, It asks the users to sign in / register, and redirects the
 * signed in users to the RemindersActivity.
 */
class AuthenticationActivity : AppCompatActivity() {
    companion object {
        const val TAG = "AuthenticationActivity"
        const val RESULT_CODE = 1001
    }
<<<<<<< HEAD
private lateinit var binding: ActivityAuthenticationBinding
=======

    private lateinit var binding: ActivityAuthenticationBinding
>>>>>>> a2f1b96 (update project with testing)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)

<<<<<<< HEAD
//         TODO: Implement the create account and sign in using FirebaseUI, use sign in using email and sign in using Google
        binding.loginButton.setOnClickListener { signInFlow() }
//          TODO: If the user was authenticated, send him to RemindersActivity

//          TODO: a bonus is to customize the sign in flow to look nice using :
=======
//          Implement the create account and sign in using FirebaseUI, use sign in using email and sign in using Google
        binding.loginButton.setOnClickListener { signInFlow() }
//           If the user was authenticated, send him to RemindersActivity

//           a bonus is to customize the sign in flow to look nice using :
>>>>>>> a2f1b96 (update project with testing)
        //https://github.com/firebase/FirebaseUI-Android/blob/master/auth/README.md#custom-layout

    }

<<<<<<< HEAD
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
            if (requestCode == RESULT_CODE){
                val response = IdpResponse.fromResultIntent(data)
                if (resultCode == Activity.RESULT_OK ){
                    // Sign in Successfully
                    Log.i(TAG, "SignIn Successfully of User: ${FirebaseAuth.getInstance().currentUser?.displayName}")
                    Toast.makeText(this,"You have Signed in Successfully with ${FirebaseAuth.getInstance().currentUser?.displayName}" +
                            " name ", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, RemindersActivity::class.java)
                    startActivity(intent)
                }else{
                    // Sign in failed. If response is null the user canceled the
                    Log.i(TAG, "Sign in failed ${response?.error?.errorCode}")
                    Toast.makeText(this,"SignIn failed with ${FirebaseAuth.getInstance().currentUser?.displayName}"
                        ,Toast.LENGTH_LONG).show()
                }
            }
=======
    override fun onStart() {
        super.onStart()
        if (FirebaseAuth.getInstance().currentUser != null) {
            // already signed in
            val intent = Intent(this ,RemindersActivity::class.java)
            startActivity(intent)
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESULT_CODE){
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK ){
                // Sign in Successfully
                Log.i(TAG, "SignIn Successfully of User: ${FirebaseAuth.getInstance().currentUser?.displayName}")
                Toast.makeText(this,"You have Signed in Successfully with ${FirebaseAuth.getInstance().currentUser?.displayName}" +
                        " name ", Toast.LENGTH_LONG).show()
                val intent = Intent(this, RemindersActivity::class.java)
                startActivity(intent)
            }else{
                // Sign in failed. If response is null the user canceled the
                Log.i(TAG, "Sign in failed ${response?.error?.errorCode}")
                Toast.makeText(this,"SignIn failed with ${FirebaseAuth.getInstance().currentUser?.displayName}"
                    ,Toast.LENGTH_LONG).show()
            }
        }
>>>>>>> a2f1b96 (update project with testing)
    }

    private fun signInFlow() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
        )

        startActivityForResult(
            AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(
                providers
            ).setIsSmartLockEnabled(false).build(), RESULT_CODE
        )
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> a2f1b96 (update project with testing)
