package mx.mauriciogs.modulosiete.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import mx.mauriciogs.modulosiete.MainActivity
import mx.mauriciogs.modulosiete.R
import mx.mauriciogs.modulosiete.databinding.ActivitySesionBinding

class SesionActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySesionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySesionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getArguments()
    }

    private fun getArguments() {
        var bundle: Bundle? = intent.extras
        var email: String? = bundle?.getString("email")
        var provedor: String? = bundle?.getString("provedor")
        initUI(email ?: "")
        //GUARDAR DATOS SESION
        val preferencias =
            getSharedPreferences(getString(R.string.file_preferencia), MODE_PRIVATE).edit()
        preferencias.putString("email", email)
        preferencias.putString("provedor", provedor)
        preferencias.apply()
    }

    private fun initUI(email: String) {
        binding.mail.text = email

        binding.closeSesion.setOnClickListener {
            val preferencias = getSharedPreferences(getString(R.string.file_preferencia), MODE_PRIVATE).edit()
            preferencias.clear()
            preferencias.apply()
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

}

enum class TipoProvedor {
    CORREO
}
