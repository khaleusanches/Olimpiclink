package devsystem.olimpiclink.ui.pages

import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import devsystem.olimpiclink.databinding.ActivityEventBinding
import devsystem.olimpiclink.model.Carousel
import devsystem.olimpiclink.util.AdapterEventCarousel

class Event : AppCompatActivity() {

    private lateinit var binding: ActivityEventBinding
    private lateinit var adapterEventCarousel: AdapterEventCarousel
    private val listImage: MutableList<Carousel> = mutableListOf()

    private lateinit var selectImageLauncher: ActivityResultLauncher<String>
    private var selectedImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView() // Função para Carrossel

        //Selecionar imagem da capa

        binding.BTNAddCover.setOnClickListener {
            selectImageLauncher.launch("image/*")
        }

        binding.BTNPublishAnEvents.setOnClickListener {
            val title = binding.TVTextPublicationEvents.text.toString()
            val description = binding.TVTextDescription.text.toString()
            val startDate = binding.editTextDate.text.toString()
            val endDate = binding.editTextDate2.text.toString()

            if (title.isBlank() || description.isBlank() || startDate.isBlank() || endDate.isBlank() || selectedImageUri == null) {
                Toast.makeText(this, "Por favor, preencha todos os campos e adicione uma capa!", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Evento publicado com sucesso!", Toast.LENGTH_LONG).show()

                // Adiciona a imagem do evento à lista de Carousels
                listImage.add(Carousel(selectedImageUri.toString()))
                adapterEventCarousel.notifyItemInserted(listImage.size - 1) // Notifica o adaptador sobre a nova imagem
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerViewCarousel.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewCarousel.setHasFixedSize(true)
        adapterEventCarousel = AdapterEventCarousel(this, listImage)
        binding.recyclerViewCarousel.adapter = adapterEventCarousel
    }
}
