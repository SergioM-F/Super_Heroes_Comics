package cl.samf.superheroescomics.presentation

import android.content.Intent
import android.net.Uri
import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import cl.samf.superheroescomics.R
import cl.samf.superheroescomics.databinding.FragmentDetailsBinding
import cl.samf.superheroescomics.databinding.FragmentHeroeListBinding
import coil.load

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "id"


class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val heroeViewModel: HeroeViewModel by activityViewModels()

    private var param1: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        initLoadDate()
        heroeViewModel.getDetailsHeroe(param1!!.toInt())
        return binding.root
    }

    private fun initLoadDate() {
        heroeViewModel.heroeIdLiveData(param1!!.toInt()).observe(viewLifecycleOwner) {
            if (it != null) {
                val name = it.nombre

                binding.textViewPoder.text = it.poder
                binding.imageViewdetails.load(it.imagenLink)
                binding.textViewcolor.text = it.color

                if (it.traduccion) {
                    binding.textViewtraduccion.text = "Cuenta con traduccion al español"

                } else {
                    binding.textViewtraduccion.text = "Sin traduccion"

                }
                binding.floatingActionButton.setOnClickListener {
                    sendMail(name)
                }
            }
        }
    }

    private fun sendMail(name: String) {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:")  // Indicamos que es para enviar un correo
        intent.putExtra(
            Intent.EXTRA_EMAIL,
            arrayOf("comicconchile@hotmail.com")
        )  // Destinatario
        intent.putExtra(Intent.EXTRA_SUBJECT, "Votacion " + name)  // Asunto
        intent.putExtra(
            Intent.EXTRA_TEXT,
            "Hola quiero que el siguiente super heroe " + name + " aparezca, en la nueva edicion de la biografias anmimadas, Numero de contacto: 555555, gracias"
        )  // Contenido del correo

        try {
            startActivity(
                Intent.createChooser(
                    intent,
                    "Super Heroe"
                )
            )  // Mostrar opciones de aplicaciones para enviar correo
        } catch (e: Exception) {

            // Manejar posibles errores o excepciones
        }

    }


}