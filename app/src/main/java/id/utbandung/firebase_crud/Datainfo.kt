package id.utbandung.firebase_crud

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import id.utbandung.firebase_crud.databinding.ActivityDatainfoBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.FirebaseApp
import id.utbandung.firebase_crud.databinding.ActivityInpudataBinding
import id.utbandung.firebase_crud.databinding.ActivityMainBinding

class Datainfo : AppCompatActivity() {

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
        binding.backbtn.setOnClickListener {
            val intent = Intent(this@Datainfo, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun readData(carsn: String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Person Information")
        databaseReference.child(carsn).get().addOnSuccessListener {
            if(it.exists()){
                val cridata = it.child("upowner").value
                val userid = it.child("userid").value
                val carid = it.child("carid").value
                Toast.makeText(this,"Data Ditemukan",Toast.LENGTH_SHORT).show()
                binding.idcarsn.text.clear()
                binding.caridata.text=cridata.toString()as Editable
                binding.idsn.text= userid.toString()as Editable
                binding.carsn.text=carid.toString()as Editable
            }else{
                Toast.makeText(this,"Nama Tidak Tersedia",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this,"Terjadi Kegagalan",Toast.LENGTH_SHORT).show()
        }
    }
}