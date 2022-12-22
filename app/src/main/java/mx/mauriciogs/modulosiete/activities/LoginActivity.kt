package mx.mauriciogs.modulosiete.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import mx.mauriciogs.modulosiete.R
import mx.mauriciogs.modulosiete.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        initButtons()
    }

    private fun initButtons() {
        with(binding) {
            signup.setOnClickListener {
                registraUsuario()
            }
        }
    }

    private fun registraUsuario() {
        with(binding) {
            signup.setOnClickListener {
                val username = username.text.toString()
                val passwd = password.text.toString()
                if (username.isNotBlank() && passwd.isNotBlank()) {
                        FirebaseAuth.getInstance().createUserWithEmailAndPassword(username, passwd)
                            .addOnCompleteListener {
                                if (it.isComplete) {
                                    try {
                                        signin(it.result?.user?.email ?: "", TipoProvedor.CORREO)
                                    } catch (e: Exception) {
                                        dialog()
                                    }
                                } else {
                                    dialog()
                                }
                            }
                }
            }
            login.setOnClickListener {
                val username = username.text.toString()
                val passwd = password.text.toString()
                if (username.isNotBlank() && passwd.isNotBlank()) {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(username, passwd)
                        .addOnCompleteListener {
                        if (it.isSuccessful) {
                            println("llegamos ---------------")
                            if (it.result?.user == null) {
                                dialog()
                            } else {
                                signin(it.result?.user?.email ?: "", TipoProvedor.CORREO)
                            }
                        } else {
                            dialog()
                        }
                    }
                }
            }
        }
    }

    private fun signin(email: String, proveedor: TipoProvedor) {
        val pasos: Intent = Intent(this, SesionActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provedor", proveedor.name)
        }
        startActivity(pasos)
        finish()
    }

    private fun dialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Ops!")
            .setMessage("Se produjo un error, contacte al provedor")
            .setPositiveButton("Aceptar", null)
            .create()
            .show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
