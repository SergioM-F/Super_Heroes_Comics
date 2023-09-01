package cl.samf.superheroescomics.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import cl.samf.superheroescomics.R
import cl.samf.superheroescomics.data.local.HeroeEntity
import cl.samf.superheroescomics.databinding.ItemHeroeBinding
import coil.load

class AdapterHeroe: RecyclerView.Adapter<AdapterHeroe.HeroeViewHolder>() {

    lateinit var binding: ItemHeroeBinding
    private val listItemHeroe = mutableListOf<HeroeEntity>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroeViewHolder {
        binding = ItemHeroeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HeroeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listItemHeroe.size
    }

    override fun onBindViewHolder(holder: HeroeViewHolder, position: Int) {
        val heroe = listItemHeroe[position]
        holder.bind(heroe)
    }

    class HeroeViewHolder(val binding: ItemHeroeBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(heroe: HeroeEntity) {
            binding.imageViewTerreno.load(heroe.imagenLink)
            binding.textViewName.text = heroe.nombre
            binding.textViewOrigen.text = heroe.origen



            binding.imageViewTerreno.setOnClickListener{
                val bundle = Bundle()
                bundle.putInt("id", heroe.id)
                Navigation.findNavController(binding.root).navigate(R.id.action_heroeListFragment_to_detailsFragment, bundle)
            }

        }

    }

    fun setDataHeroe(heroe: List<HeroeEntity>){
        this.listItemHeroe.clear()
        this.listItemHeroe.addAll(heroe)
        notifyDataSetChanged()
    }

}