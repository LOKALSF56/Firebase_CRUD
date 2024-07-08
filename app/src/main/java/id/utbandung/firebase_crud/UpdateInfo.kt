package id.utbandung.firebase_crud

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.FirebaseApp
import id.utbandung.firebase_crud.databinding.ActivityUpdateInfoBinding

class UpdateInfo : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateInfoBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUpdateInfoBinding.inflate(layoutInflater)
        FirebaseApp.initializeApp(this)
        setContentView(binding.root)

        binding.mainUpload.setOnClickListener {
            val upowner = binding.upowner.text.toString()
            val upcar = binding.upcar.text.toString()
            val upcid = binding.upcid.text.toString()
            val upidcar = binding.upidcar.text.toString()
            updatedata(upowner, upcar, upcid, upidcar)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.updata)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun updatedata(upowner: String, upcar: String, upcid: String, upidcar: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Person Information")
        val persondata = mapOf(
            "upowner" to upowner,
            "upcar" to upcar,
            "upcid" to upcid,
            "upidcar" to upidcar
        )
        databaseReference.child(upidcar).updateChildren(persondata).addOnSuccessListener {
            binding.upowner.text.clear()
            binding.upcar.text.clear()
            binding.upcid.text.clear()
            binding.upidcar.text.clear()
            Toast.makeText(this, "Update Berhasil", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Gagal Update", Toast.LENGTH_SHORT).show()
        }
    }
}
