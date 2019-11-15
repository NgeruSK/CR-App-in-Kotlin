package com.example.loginui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.user_registration.*

class MainActivity : AppCompatActivity() {

    lateinit var handler: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handler=DatabaseHelper(this)

        showHome()

        btnLogin.setOnClickListener {
            showLogin()
        }
        btnRegister.setOnClickListener {
            showRegistration()
        }
        btnHome.setOnClickListener {
            showHome()
        }
        btnRegHome.setOnClickListener {
            showHome()
        }

        btnSave.setOnClickListener {

            if (txtName.text.toString()!="" && txtEmail.text.toString()!="" && txtPassword.text.toString()!="" && txtPassword2.text.toString()!="") {
                if (txtPassword.text.toString() == txtPassword2.text.toString()) {
                    handler.insertUserData(
                        txtName.text.toString(),
                        txtEmail.text.toString(),
                        txtPassword.text.toString()
                    )
                    Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
                    showLogin()
                }
                    else
                {
                    Toast.makeText(this, "Both passwords should be the same", Toast.LENGTH_SHORT).show()
                txtPassword.setText("")
                txtPassword2.setText("")}
            }
            else
                    Toast.makeText(this, "Fill all fields before pressing Save", Toast.LENGTH_SHORT).show()
            }
        btnMainLogin.setOnClickListener {
            if(login_email.text.toString()!="" && login_password.text.toString()!="") {
                if (handler.userPresent(login_email.text.toString(), login_password.text.toString()))
                    Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(this, "Login Failed, Check your credentials", Toast.LENGTH_SHORT).show()
                login_email.setText("")
                login_password.setText("")

            }
        else
                Toast.makeText(this, "Enter both your Username & Password", Toast.LENGTH_SHORT).show()
        }
    }
    private fun showRegistration(){
        registration_layout.visibility=View.VISIBLE
        login_layout.visibility=View.GONE
        home_ly.visibility=View.GONE
        imageView1.visibility=View.GONE

    }
    private fun showLogin()
    {
        registration_layout.visibility=View.GONE
        login_layout.visibility=View.VISIBLE
        home_ly.visibility=View.GONE
        imageView1.visibility=View.GONE
    }
    private fun showHome()
    {
        registration_layout.visibility=View.GONE
        login_layout.visibility=View.GONE
        home_ly.visibility=View.VISIBLE
        imageView1.visibility=View .VISIBLE
    }
}
