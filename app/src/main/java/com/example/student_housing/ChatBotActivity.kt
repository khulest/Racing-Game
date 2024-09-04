package com.example.student_housing

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.runBlocking

class ChatBotActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chat_bot)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editTextPrompt = findViewById<EditText>(R.id.editTextPrompt)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        val txtResults = findViewById<TextView>(R.id.txtResults)
        //val backToHome = findViewById<ImageView>(R.id.backToHome)




        btnSubmit.setOnClickListener {
            val prompt= editTextPrompt.text.toString()

            val generativeModel = GenerativeModel(
                    // For text-only input, use the gemini-pro model
                    modelName = "gemini-pro",
                    apiKey = "####"
            )
            runBlocking {
                val response = generativeModel.generateContent(prompt)
                txtResults.text= response.text
            }
        }


    }
}