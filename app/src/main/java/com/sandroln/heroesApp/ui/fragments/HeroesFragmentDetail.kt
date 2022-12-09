package com.sandroln.heroesApp.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.sandroln.heroesApp.R
import com.sandroln.heroesApp.databinding.FragmentHeroDetailBinding
import com.sandroln.heroesApp.ui.Action
import com.sandroln.heroesApp.ui.HeroesApp
import com.sandroln.heroesApp.ui.viewmodels.HeroDetailViewModel
import com.sandroln.heroesApp.ui.viewmodels.factory.ViewModelFactory
import javax.inject.Inject


class HeroesFragmentDetail : Fragment() {

    private val args by navArgs<HeroesFragmentDetailArgs>()

    private var _binding: FragmentHeroDetailBinding? = null
    private val binding: FragmentHeroDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentCoinDetailBinding is null")

    private lateinit var viewModel: HeroDetailViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

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
    ): View? {
        _binding = FragmentHeroDetailBinding.inflate(inflater, container, false)
        binding.header.setBackgroundColor(args.color)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[HeroDetailViewModel::class.java]
        viewModel.getHeroById(args.heroId)
        viewModel.uiState.observe(viewLifecycleOwner, ::handleActions)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun handleActions(action: Action) {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (action) {
                is Action.Success -> with(binding) {
                    heroName.text = action.item.name
                    height.text = action.item.height.get(1)
                    weight.text = action.item.weight.get(1)
                    progressCombat.labelText = action.item.combat.toString()
                    progressCombat.progress = action.item.combat.toFloat()
                    progressDurability.labelText = action.item.durability.toString()
                    progressDurability.progress = action.item.durability.toFloat()
                    progressIntelligence.labelText = action.item.durability.toString()
                    progressIntelligence.progress = action.item.intelligence.toFloat()
                    progressPower.labelText = action.item.power.toString()
                    progressPower.progress = action.item.power.toFloat()
                    progressSpeed.labelText = action.item.speed.toString()
                    progressSpeed.progress = action.item.speed.toFloat()
                    progressStrength.labelText = action.item.strength.toString()
                    progressStrength.progress = action.item.strength.toFloat()
                    setupView(binding.heroImage, args.imageUrl, binding.heroImage)
                }
                is Action.Error -> action.message
                is Action.Loading -> action.loading
            }
        }
    }

    private fun setupView(appCompatImageView: ShapeableImageView, url: String, imageView: ImageView) {
        Glide.with(appCompatImageView.context)
            .load(url)
            .error(R.drawable.ic_baseline_error_24)
            .skipMemoryCache(true)
            .into(imageView)
    }
}
