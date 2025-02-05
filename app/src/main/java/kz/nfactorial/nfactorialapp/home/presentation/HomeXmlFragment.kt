package kz.nfactorial.nfactorialapp.home.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kz.nfactorial.nfactorialapp.databinding.HomeFragmentBinding
import kz.nfactorial.nfactorialapp.home.presentation.models.ChipItem
import kz.nfactorial.nfactorialapp.home.presentation.recycler.HomeSimpleListAdapter
import kz.nfactorial.nfactorialapp.home.presentation.recycler.HomeSimpleSimpleAdapter

class HomeXmlFragment: Fragment() {

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = HomeSimpleSimpleAdapter()
        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(requireContext())
        adapter.submitList(
            listOf(
                ChipItem(1, "Name1"),
                ChipItem(2, "Name2"),
                ChipItem(3, "Name3"),
                ChipItem(4, "Name4"),
                ChipItem(5, "Name5"),
                ChipItem(6, "Name6"),
                ChipItem(7, "Name7"),
                ChipItem(8, "Name8"),
                ChipItem(9, "Name9"),
                ChipItem(10, "Name10"),
            )
        )

        lifecycleScope.launch {
            delay(2_000)
            adapter.submitList(
                listOf(
                    ChipItem(0, "Name1"),
                    ChipItem(1, "Name2"),
                    ChipItem(2, "Name3"),
                    ChipItem(4, "Name4"),
                    ChipItem(8, "Name8"),
                    ChipItem(9, "Name9"),
                    ChipItem(10, "Name10"),
                    ChipItem(11, "Name10"),
                    ChipItem(12, "Name10"),
                    ChipItem(13, "Name10"),
                    ChipItem(14, "Name10"),
                    ChipItem(15, "Name10"),
                    ChipItem(16, "Name10"),
                    ChipItem(17, "Name10"),
                )
            )
            adapter.notifyDataSetChanged()
        }

        binding.composeView.setContent {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text  = "Hello World!"
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}