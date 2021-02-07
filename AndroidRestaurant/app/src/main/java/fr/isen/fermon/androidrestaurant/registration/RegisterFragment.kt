package com.example.isen_2021.registration

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import fr.isen.fermon.androidrestaurant.R
import fr.isen.fermon.androidrestaurant.databinding.FragmentDetailViewBinding
import fr.isen.fermon.androidrestaurant.databinding.FragmentRegisterBinding
import fr.isen.fermon.androidrestaurant.detail.PhotoAdapter
import fr.isen.fermon.androidrestaurant.network.NetworkConstant
import fr.isen.fermon.androidrestaurant.network.RegisterResult
import fr.isen.fermon.androidrestaurant.network.User
import com.google.gson.GsonBuilder
import fr.isen.fermon.androidrestaurant.registration.UserActivityFragmentInteraction
import org.json.JSONObject

class RegisterFragment : Fragment() {
    lateinit var binding: FragmentRegisterBinding

    var delegate: UserActivityFragmentInteraction? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonRegister.setOnClickListener {
            delegate?.makeRequest(
                binding.email.text.toString(),
                binding.password.text.toString(),
                binding.lastname.text.toString(),
                binding.firstname.text.toString(),
                false
            )
        }

        binding.loginButton.setOnClickListener {
            delegate?.showLogin()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is UserActivityFragmentInteraction) {
            delegate = context
        }
    }
}