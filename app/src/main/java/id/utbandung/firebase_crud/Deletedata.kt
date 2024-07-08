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
import id.utbandung.firebase_crud.databinding.ActivityDeletedataBinding

class Deletedata : AppCompatActivity() {
    private lateinit var binding: ActivityDeletedataBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeletedataBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        binding.delbtn.setOnClickListener {
            val phone = binding.namedel.text.toString()
            if (phone.isNotEmpty())
                deleteData(phone)
            else
                Toast.makeText(this, "Masukan Nama", Toast.LENGTH_SHORT).show()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.deldata)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun deleteData(phone: String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Person Information")
        databaseReference.child(phone).removeValue().addOnSuccessListener {
            binding.namedel.text.clear()
            Toast.makeText(this, "Terhapus", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Gak Bisa Dihapus", Toast.LENGTH_SHORT).show()
        }
    }
}