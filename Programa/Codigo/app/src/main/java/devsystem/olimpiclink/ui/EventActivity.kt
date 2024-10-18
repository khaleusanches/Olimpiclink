package devsystem.olimpiclink

import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import devsystem.olimpiclink.databinding.ActivityEventBinding
import devsystem.olimpiclink.model.Carousel
import devsystem.olimpiclink.util.AdapterEvent

class Event : AppCompatActivity() {

    private lateinit var binding: ActivityEventBinding
    private lateinit var adapterEvent: AdapterEvent
    private val listImage: MutableList<Carousel> = mutableListOf()

    private lateinit var selectImageLauncher: ActivityResultLauncher<String>
    private var selectedImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerViewEvent = binding.recyclerViewCarousel
        recyclerViewEvent.layoutManager = LinearLayoutManager(this)
        recyclerViewEvent.setHasFixedSize(true)

        adapterEvent = AdapterEvent(this, listImage)
        recyclerViewEvent.adapter = adapterEvent

        val imageView = findViewById<ImageView>(R.id.imageView2)
        val btnAddCover = findViewById<Button>(R.id.BTN_add_cover)
        val btnPublish = findViewById<Button>(R.id.BTN_publish_an_events)
        val titleEditText = findViewById<EditText>(R.id.TV_text_publication_events)
        val descriptionEditText = findViewById<EditText>(R.id.TV_text_description)
        val startDateEditText = findViewById<EditText>(R.id.editTextDate)
        val endDateEditText = findViewById<EditText>(R.id.editTextDate2)

        selectImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                selectedImageUri = it
                imageView.setImageURI(it)
            }
        }

        btnAddCover.setOnClickListener {
            selectImageLauncher.launch("image/*")
        }

        btnPublish.setOnClickListener {
            val title = titleEditText.text.toString()
            val description = descriptionEditText.text.toString()
            val startDate = startDateEditText.text.toString()
            val endDate = endDateEditText.text.toString()

            if (title.isBlank() || description.isBlank() || startDate.isBlank() || endDate.isBlank() || selectedImageUri == null) {
                Toast.makeText(this, "Por favor, preencha todos os campos e adicione uma capa!", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Evento publicado com sucesso!", Toast.LENGTH_LONG).show()

                // Adiciona o evento à lista de Carousels
                val event = EventData(
                    title = title,
                    description = description,
                    startDate = startDate,
                    endDate = endDate,
                    imageUri = selectedImageUri.toString()
                )
                publishEvent(event)

                // Adiciona a imagem do evento à lista de Carousels
                listImage.add(Carousel(event.imageUri))
                adapterEvent.notifyItemInserted(listImage.size - 1) // Notifica o adaptador sobre a nova imagem
            }
        }
    }

    private fun publishEvent(event: EventData) {
        println("Evento publicado: ${event.title}, ${event.description}, de ${event.startDate} até ${event.endDate}")
    }
}

data class EventData(
    val title: String,
    val description: String,
    val startDate: String,
    val endDate: String,
    val imageUri: String
)
