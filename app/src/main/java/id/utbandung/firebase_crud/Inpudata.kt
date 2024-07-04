package id.utbandung.firebase_crud

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import id.utbandung.firebase_crud.databinding.ActivityInpudataBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.FirebaseApp

class Inpudata : AppCompatActivity() {

    private lateinit var binding: ActivityInpudataBinding
    private lateinit var databaseref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInpudataBinding.inflate(layoutInflater)
        FirebaseApp.initializeApp(this)
        setContentView(binding.root)

        binding.mainUpload.setOnClickListener {
            val user = binding.upowner.text.toString()
            val userid = binding.upcid.text.toString()
            val car = binding.upcar.text.toString()
            val carid = binding.upidcar.text.toString()

            databaseref = FirebaseDatabase.getInstance().getReference("Person Information")
            val UserDataInfo = User(user, userid, car, carid)
            databaseref.child(carid).setValue(UserDataInfo).addOnSuccessListener {
                binding.upowner.text.clear()
                binding.upcid.text.clear()
                binding.upcar.text.clear()
                binding.upidcar.text.clear()

                Toast.makeText(this, "Data Tersimpan!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@Inpudata, MainActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                Toast.makeText(this, "Data Gagal Tersimpan!", Toast.LENGTH_SHORT).show()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.updata)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

data class User(val user: String, val userid: String, val car: String, val carid: String)