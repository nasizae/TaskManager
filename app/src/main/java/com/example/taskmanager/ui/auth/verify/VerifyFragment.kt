package com.example.taskmanager.ui.auth.verify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.ui.auth.phone.PhoneFragment.Companion.VERIFY_KEY
import com.example.taskmeneger.R
import com.example.taskmeneger.databinding.FragmentVerifyBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class VerifyFragment : Fragment() {
    private lateinit var binding: FragmentVerifyBinding

    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentVerifyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var verId = arguments?.getString(VERIFY_KEY)
        binding.btnAccept.setOnClickListener {
            val cedential = PhoneAuthProvider.getCredential(verId!!, binding.etCode.text.toString())
            signInWithCred(cedential)
        }
    }

    private fun signInWithCred(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnSuccessListener { _ ->
                findNavController().navigate(R.id.navigation_home)
            }.addOnFailureListener {
                Toast.makeText(context, R.string.error_credential, Toast.LENGTH_SHORT).show()
            }
    }

}
