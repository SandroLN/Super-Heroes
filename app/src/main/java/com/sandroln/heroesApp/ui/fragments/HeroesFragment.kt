package com.sandroln.heroesApp.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sandroln.heroesApp.R
import com.sandroln.heroesApp.databinding.FragmentHeroesListBinding
import com.sandroln.heroesApp.ui.HeroesApp
import com.sandroln.heroesApp.ui.adapter.HeroesAdapter
import com.sandroln.heroesApp.ui.viewmodels.HeroesListViewModel
import com.sandroln.heroesApp.ui.viewmodels.factory.ViewModelFactory
import javax.inject.Inject

class HeroesFragment : Fragment(), MenuProvider {

    private var _binding: FragmentHeroesListBinding? = null
    private val binding: FragmentHeroesListBinding
        get() = _binding ?: throw RuntimeException("FragmentCoinDetailBinding is null")

    private lateinit var viewModel: HeroesListViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var heroesAdapter: HeroesAdapter

    private val component by lazy {
        (requireActivity().application as HeroesApp).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHeroesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        viewModel = ViewModelProvider(this, viewModelFactory)[HeroesListViewModel::class.java]
        setupToolbar()
        setupRecyclerView()
        requestHeroes()
        viewModel.uiState.observe(viewLifecycleOwner) {
            heroesAdapter.submitList(it.heroes)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        heroesAdapter = HeroesAdapter { heroId, color, imageUrl ->
            findNavController().navigate(
                HeroesFragmentDirections.actionHeroesFragmentToHeroesFragmentDetail(
                    heroId,
                    color,
                    imageUrl
                )
            )
        }
        heroesAdapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        binding.rvHeroesList.adapter = heroesAdapter
        binding.rvHeroesList.setHasFixedSize(true)
    }

    private fun requestHeroes() {
        viewModel.getHeroesList()
    }


    private fun setupToolbar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = getString(R.string.app_name)
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu, menu)
        menu.findItem(R.menu.menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        val searchView = menuItem.actionView as SearchView
        searchView.queryHint = "Search"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.updateListOnQuery(newText)
                return true
            }
        })
        return true
    }
}