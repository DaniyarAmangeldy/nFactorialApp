package kz.nfactorial.nfactorialapp.registration.presentation

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import kz.nfactorial.nfactorialapp.R
import kz.nfactorial.nfactorialapp.databinding.RegistrationFragmentBinding

class RegistrationXmlFragment: Fragment() {

    private var _binding: RegistrationFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = RegistrationFragmentBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nameEditText.setText("My Custom Text")
        binding.nameEditText.doAfterTextChanged { text: Editable? ->
            Log.d("RegistrationXmlFragment", text?.toString().orEmpty())
        }
        binding.header.setOnClickListener {
            Log.d("RegistrationXmlFragment", "Header Clicked!")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}