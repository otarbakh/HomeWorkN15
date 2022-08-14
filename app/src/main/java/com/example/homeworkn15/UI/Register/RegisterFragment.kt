package com.example.loginregister.UI.Register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.homeworkn15.UI.Register.RegisterViewModel
import com.example.loginregister.Resource
import com.example.loginregister.databinding.FragmentRegisterBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RegisterFragment : Fragment() {


    private val viewModel: RegisterViewModel by viewModels()

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(


        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etUsername.setText("eve.holt@reqres.in")
        binding.etPassword.setText("cityslicka")

        listeners()
        observe()

    }

    private fun listeners(){
        binding.btnRegister.setOnClickListener {
            viewModel.register(
                binding.etUsername.text.toString(),
                binding.etPassword.text.toString()
            )
        }

    }

    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.registeredState.collectLatest {
                    when (it){
                        is Resource.Success ->{
                            Toast.makeText(requireContext(),it.data.id.toString(), Snackbar.LENGTH_SHORT).show()
                        }

                        is Resource.Error ->{
                            Toast.makeText(requireContext(),it.error, Snackbar.LENGTH_SHORT).show()
                        }

                        is Resource.Loading ->{

                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}