 package com.example.howtopickcontactanddisplayit

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.EditText

 class MainActivity : AppCompatActivity() {
     private lateinit var userPhoneNumber: EditText
     private lateinit var userPhoneNumber2: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userPhoneNumber = findViewById<EditText>(R.id.editTextTextPersonNumber)
        userPhoneNumber2 = findViewById<EditText>(R.id.editTextTextPersonName2)
        userPhoneNumber.setOnClickListener {
            var intent = Intent(Intent.ACTION_PICK)
            intent.type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
            Log.d("MQ", "onActivityResult: ${userPhoneNumber.text.toString()}")
            startActivityForResult(intent, 111)
        }
    }

     @Deprecated("Deprecated in Java")
     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
         Log.d("MQ", "onActivityResult1: ${userPhoneNumber.text.toString()}")
         super.onActivityResult(requestCode, resultCode, data)
         Log.d("MQ", "onActivityResult11: ${userPhoneNumber.text.toString()}")
         if (requestCode == 111 && resultCode == Activity.RESULT_OK) {
             Log.d("MQ", "onActivityResult2: ${userPhoneNumber.text.toString()}")
             var contactUri: Uri = data?.data ?: return
             var cols: Array<String> = arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
             val rs = contentResolver.query(contactUri, cols, null, null, null)
             if(rs?.moveToFirst()!!){
                 Log.d("MQ", "onActivityResult3: ${userPhoneNumber.text.toString()}")
                 userPhoneNumber.setText(rs.getString(0))
                 Log.d("MQ", "onActivityResult4: ${userPhoneNumber.text.toString()}")
                 userPhoneNumber2.setText(rs.getString(1))
             }
         }
     }
}