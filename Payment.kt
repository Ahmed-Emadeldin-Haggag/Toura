import android.os.Bundle
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PaymentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val cardHolderInput = findViewById<EditText>(R.id.cardHolderInput)
        val cardNumberInput = findViewById<EditText>(R.id.cardNumberInput)
        val expiryDateInput = findViewById<EditText>(R.id.expiryDateInput)
        val payButton = findViewById<Button>(R.id.payButton)

        payButton.setOnClickListener {
            val cardHolder = cardHolderInput.text.toString()
            val cardNumber = cardNumberInput.text.toString()
            val expiryDate = expiryDateInput.text.toString()

            if (cardHolder.isNotEmpty() && cardNumber.length == 16 && expiryDate.length == 5) {
                processPayment(cardHolder, cardNumber, expiryDate)
            } else {
                Toast.makeText(this, "Please fill all fields correctly.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun processPayment(cardHolder: String, cardNumber: String, expiryDate: String) {
        // Here you would integrate the backend API to process the payment.
        Toast.makeText(this, "Payment Successful!", Toast.LENGTH_SHORT).show()
    }
}
