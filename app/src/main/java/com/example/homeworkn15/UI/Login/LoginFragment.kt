package com.example.homeworkn15.UI.Login

import android.annotation.SuppressLint

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.homeworkn15.databinding.LoginBinding
import com.example.loginregister.UI.Login.LoginViewModel
import kotlinx.coroutines.launch
class LoginFragment : Fragment() {


    private val viewModel: LoginViewModel by viewModels()

    private var _binding: LoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(


        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etUsername.setText("eve.holt@reqres.in")
        binding.etPassword.setText("cityslicka")

        listeners()
        observe()

    }

    private fun listeners(){
        binding.btnLogin.setOnClickListener {
            viewModel.logIn(
                binding.etUsername.text.toString(),
                binding.etPassword.text.toString()
            )
        }

    }

    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.loginState.
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}