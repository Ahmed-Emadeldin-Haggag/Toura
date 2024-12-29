package com.example.touraapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PaymentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)


        val cardHolderInput = findViewById<EditText>(R.id.cardHolderInput)
        val cardNumberInput = findViewById<EditText>(R.id.cardNumberInput)
        val expiryDateInput = findViewById<EditText>(R.id.expiryDateInput)
        val cvcInput = findViewById<EditText>(R.id.cvcInput)
        val payButton = findViewById<Button>(R.id.payButton)

        payButton.setOnClickListener {
            val cardHolder = cardHolderInput.text.toString().trim()
            val cardNumber = cardNumberInput.text.toString().trim()
            val expiryDate = expiryDateInput.text.toString().trim()
            val cvc = cvcInput.text.toString().trim()

            if (validateInput(cardHolder, cardNumber, expiryDate, cvc)) {
                processPayment(cardHolder, cardNumber, expiryDate, cvc)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun validateInput(cardHolder: String, cardNumber: String, expiryDate: String, cvc: String): Boolean {
        val cardNumberRegex = Regex("^[0-9]{16}$")
        val expiryDateRegex = Regex("^(0[1-9]|1[0-2])/([0-9]{2})$")
        val cvcRegex = Regex("^[0-9]{3,4}$")


        return when {
            cardHolder.isEmpty() -> {
                showToast("Card holder name is required.")
                false
            }
            !cardNumber.matches(cardNumberRegex) -> {
                showToast("Invalid card number. It must be 16 digits.")
                false
            }
            !expiryDate.matches(expiryDateRegex) -> {
                showToast("Invalid expiry date. Use MM/YY format.")
                false
            }
            !cvc.matches(cvcRegex) -> {
                showToast("Invalid CVC code. It must be 3 or 4 digits.")
                false
            }
            else -> true
        }
    }

    private fun processPayment(cardHolder: String, cardNumber: String, expiryDate: String, cvc: String) {
        // Simulate payment processing
        Toast.makeText(this, "Processing payment for $cardHolder...", Toast.LENGTH_SHORT).show()
        // Mock response
        Toast.makeText(this, "Payment Successful!", Toast.LENGTH_SHORT).show()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
