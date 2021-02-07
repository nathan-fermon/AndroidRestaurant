package fr.isen.fermon.androidrestaurant.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import fr.isen.fermon.androidrestaurant.network.Dish
import fr.isen.fermon.androidrestaurant.databinding.FragmentDetailViewBinding


class DetailViewFragment(private val dish: Dish?) : Fragment() {

    lateinit var binding: FragmentDetailViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ingredientTextView.text = dish?.ingredients?.map { it.name }?.joinToString(", ")
        binding.dishTitleTextView.text = dish?.name
        val appCompactActivity = activity as? AppCompatActivity
        appCompactActivity?.let {
            binding.viewPager.adapter = PhotoAdapter(it, dish?.images ?: listOf())
        }
    }
}