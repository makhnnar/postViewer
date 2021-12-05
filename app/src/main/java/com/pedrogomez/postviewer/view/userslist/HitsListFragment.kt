package com.pedrogomez.postviewer.view.userslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.pedrogomez.postviewer.R
import com.pedrogomez.postviewer.databinding.FragmentHitsListBinding
import com.pedrogomez.postviewer.models.db.UserTable
import com.pedrogomez.postviewer.view.viewmodel.UsersViewModel
import com.pedrogomez.postviewer.utils.extensions.shortToast
import com.pedrogomez.postviewer.models.result.Result
import com.pedrogomez.postviewer.view.userslist.view.HitsListView
import org.koin.android.viewmodel.ext.android.getViewModel

class HitsListFragment : Fragment(),
    HitsListView.OnHitsListActions{

    private val sharedHitsViewModel by lazy {
        requireParentFragment().getViewModel<UsersViewModel>()
    }

    private lateinit var binding: FragmentHitsListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = FragmentHitsListBinding.inflate(
            inflater,
            container,
            false
        )
        val view = binding.root
        initObservers()
        binding.productsListView.hideBtnToTop()
        binding.productsListView.onHitsListActions = this
        sharedHitsViewModel.loadFirstPage()
        return view
    }

    private fun initObservers(){
        sharedHitsViewModel.hitsListLiveData.observe(
            viewLifecycleOwner,
            Observer {
                binding.productsListView.setData(
                    it
                )
                binding.productsListView.hideLoader()
            }
        )
        sharedHitsViewModel.loaderData.observe(
            viewLifecycleOwner,
            Observer {
                when (it) {
                    is Result.Success -> {
                        binding.productsListView.hideLoader()
                    }
                    is Result.Loading -> {
                        binding.productsListView.showLoader()
                    }
                    is Error -> {
                        context?.let{
                            shortToast(
                                it,
                                this.getString(R.string.search_error)
                            )
                        }
                        binding.productsListView.hideLoader()
                    }
                }
            }
        )
    }

    override fun loadMore(page: Int) {
        sharedHitsViewModel.loadMore(page)
    }

    override fun loadAgain() {
        sharedHitsViewModel.reloadContent()
    }

    override fun goToItemDetail(data: UserTable) {
        sharedHitsViewModel.saveSelected(data)
        findNavController().navigate(R.id.action_productosListFragment_to_productosDetailFragment)
    }

    override fun deleted(userItem: UserTable) {
        sharedHitsViewModel.delete(userItem)
    }
}