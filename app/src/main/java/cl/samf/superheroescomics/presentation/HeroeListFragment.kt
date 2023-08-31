package cl.samf.superheroescomics.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import cl.samf.superheroescomics.R
import cl.samf.superheroescomics.databinding.FragmentHeroeListBinding
import cl.samf.superheroescomics.databinding.ItemHeroeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HeroeListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HeroeListFragment : Fragment() {

    lateinit var binding: FragmentHeroeListBinding
    private val heroeViewModel: HeroeViewModel by activityViewModels()

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHeroeListBinding.inflate(layoutInflater, container, false)
        initAdapter()
        heroeViewModel.getHeroe()
        return binding.root
    }

    private fun initAdapter() {
        val adapter = AdapterHeroe()
        binding.recyclerView.adapter = adapter
        heroeViewModel.heroeLiveData().observe(viewLifecycleOwner){
            adapter.setDataHeroe(it)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HeroeListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HeroeListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}