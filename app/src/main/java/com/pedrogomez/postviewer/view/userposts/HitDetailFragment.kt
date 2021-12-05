package com.pedrogomez.postviewer.view.userposts

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.pedrogomez.postviewer.databinding.FragmentHitDetailBinding
import com.pedrogomez.postviewer.view.userposts.views.HitDetailView
import com.pedrogomez.postviewer.view.viewmodel.UsersViewModel
import org.koin.android.viewmodel.ext.android.getViewModel


class HitDetailFragment : Fragment(),
    HitDetailView.OnDetailActions{

    private lateinit var binding: FragmentHitDetailBinding

    private val sharedHitsViewModel by lazy {
        requireParentFragment().getViewModel<UsersViewModel>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = FragmentHitDetailBinding.inflate(
            inflater,
            container,
            false
        )
        val view = binding.root
        binding.productoDetailView.onDetailActions = this
        sharedHitsViewModel.selectedHitLiveData.observe(
            viewLifecycleOwner,
            Observer {
                binding.productoDetailView.setData(
                    it
                )
            }
        )
        return view
    }

    private fun openOnBrowser(url:String){
        val browserIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(url)
        )
        startActivity(browserIntent)
    }

    override fun onBackPressed() {
        requireActivity().onBackPressed()
    }
}