package com.example.loginui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import android.view.View
import androidx.core.text.set
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_product.*
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.login_index.*
import kotlinx.android.synthetic.main.user_registration.*

class MainActivity : AppCompatActivity() {

    lateinit var handler: DatabaseHelper
    lateinit var handler2: DatabaseHelper2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handler=DatabaseHelper(this)
        handler2= DatabaseHelper2(this)

        showHome()

        btnLogin.setOnClickListener {
            showLogin()
        }
        btnRegister.setOnClickListener {
            showRegistration()
        }
        btnHome.setOnClickListener {
            showHome()
            clearFields()
        }
        btnRegHome.setOnClickListener {
            showHome()
            clearFields()
        }
btnAddProduct.setOnClickListener {
    showAddProducts()
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
                if (handler.userPresent(login_email.text.toString(), login_password.text.toString())) {
                    Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                    showLoginIndex()
                }
                else
                    Toast.makeText(this, "Login Failed, Check your credentials", Toast.LENGTH_SHORT).show()
                login_email.setText("")
                login_password.setText("")

            }
        else
                Toast.makeText(this, "Enter both your Username & Password", Toast.LENGTH_SHORT).show()
        }
        btnCancelProduct.setOnClickListener {
            showRegMain()
            clearFields()
        }
        btnInventory.setOnClickListener {
            val data=handler2.viewProducts()
            txtResult.setText("")
            for (i in 0..(data.size-1)) {
                txtResult.append(
                    data.get(i).pId.toString() + " " + data.get(i).PName.toString() + " " +
                            "" + data.get(i).pDesc.toString() + " " + data.get(i).pQty.toString().toInt()
                )
                txtResult.append("\n")
            }
           /* for (i in 0..(data.size-1)) {
                tv_Results.append(data.get(i).pId.toString() + " " + data.get(i).PName.toString() + "" + data.get(i).pDesc.toString()  + " " + data.get(i).pQty.toString())
            }*/
        }
        btnDeleteProduct.setOnClickListener {
            Toast.makeText(this,"Work in Progress by Simon",Toast.LENGTH_LONG).show()
        }

        btnSaveProduct.setOnClickListener()
        {
            if(txtProductName.text.toString() !="" && txtProductId.text.toString() !="" && txtProductQty.text.toString() !="" && txtProductDescription.text.toString() !="" )
            {
                handler2.insertProducts(
                    txtProductId.text.toString().toInt(),txtProductName.text.toString(),
                    txtProductDescription.text.toString(), txtProductQty.text.toString().toInt()
                )
                Toast.makeText(this,"Product Added Successfully ",Toast.LENGTH_LONG).show()
                clearFields()

            }
            else
            {
                Toast.makeText(this,"Enter all product details before proceeding",Toast.LENGTH_SHORT).show()
            }

        }
    }
    private fun showRegistration(){
        registration_layout.visibility=View.VISIBLE
        login_layout.visibility=View.GONE
        home_ly.visibility=View.GONE
        imageView1.visibility=View.GONE

    }
    private fun clearFields()
    {
        txtProductId.setText("")
        txtProductName .setText("")
        txtProductQty.setText("")
        txtProductDescription.setText("")
        txtPassword.setText("")
        txtEmail.setText("")
        txtPassword2.setText("")
        txtName.setText("")
        login_email.setText("")
        login_password.setText("")

    }
    private fun showRegMain()
    {
        registration_layout.visibility=View.GONE
        login_layout.visibility=View.GONE
        home_ly.visibility=View.GONE
        imageView1.visibility=View.GONE
        login_idx.visibility=View.VISIBLE
        products_add.visibility=View.GONE
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
        products_add.visibility=View.GONE
        login_idx.visibility=View.GONE
    }
    private fun showLoginIndex()
    {
        login_idx.visibility=View.VISIBLE
        login_layout.visibility=View.GONE
    }
    private fun showAddProducts()
    {
        products_add.visibility=View.VISIBLE
        login_idx.visibility=View.GONE
    }

}
