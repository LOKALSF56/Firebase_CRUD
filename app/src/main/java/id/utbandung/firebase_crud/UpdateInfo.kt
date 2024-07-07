package id.utbandung.firebase_crud

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import id.utbandung.firebase_crud.databinding.ActivityInpudataBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.FirebaseApp
import id.utbandung.firebase_crud.databinding.ActivityDatainfoBinding

class UpdateInfo : AppCompatActivity() {
    private lateinit var binding: ActivityDatainfoBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDatainfoBinding.inflate(layoutInflater)
        FirebaseApp.initializeApp(this)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun updatedata(upowner: String,upcar: String,){
        databaseReference = FirebaseDatabase.getInstance().getReference("Person Information")
        val persondata =mapof 
        binding.upowner.text.clear()
        binding.upcid.text.clear()
        binding.upcar.text.clear()
        binding.upidcar.text.clear()
    }
}