package com.engie.eea_tech_interview.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.engie.domain.model.GenreData
import com.engie.eea_tech_interview.R
import com.engie.eea_tech_interview.databinding.MovieDetailsBinding
import com.engie.eea_tech_interview.ui.genre.GenreViewModel
import com.engie.eea_tech_interview.util.CustomProgressDialog
import com.engie.eea_tech_interview.util.showSnackWithAction
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit
import kotlin.math.ceil


class MovieDetailFragment: Fragment() {
    private lateinit var binding: MovieDetailsBinding
    private val viewModel: GenreViewModel by viewModel()

    private val progressDialog by lazy { CustomProgressDialog(requireActivity()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            MovieDetailsBinding.inflate(
                inflater,
                container,
                false
            )

        val animation = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = animation
        postponeEnterTransition(200, TimeUnit.MILLISECONDS)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.fetchGenre()

        setTransition()

        val poster = "https://image.tmdb.org/t/p/w500" + args.movieDetail.posterPath

        Glide.with(this)
            .load(poster)
            .placeholder(R.drawable.load)
            .into(binding.thumbnailImageHeader)

        binding.toolbarTitle.text = args.movieDetail.title
        binding.detail.title.text = args.movieDetail.title
        binding.detail.userrating.text = ceil(args.movieDetail.voteAverage ?: 0.0).toString()
        binding.detail.releasedate.text = args.movieDetail.releaseDate
        binding.detail.plotsynopsis.text = args.movieDetail.overview

        viewModel.genre.observe(requireActivity()) { result ->
            result?.getContentIfNotHandled()?.let { value ->
                when {
                    value.isSuccess() -> {
                        value.data?.let { genreList ->
                            val genreGen = mutableListOf<String>()
                            for (genre in args.movieDetail.genreIds ?: emptyList()) {
                                val gen = genreList.filter { it.id == genre }
                                genreGen.add(gen[0].name)
                            }

                            binding.detail.genre.text = genreGen.joinToString()
                        }
                        progressDialog.stop()
                    }

                    value.isLoading() -> {
                        progressDialog.start(title = "please wait ...")
                    }

                    value.isError() -> {
                        progressDialog.stop()
                        Toast.makeText(requireContext(), value.message, Toast.LENGTH_SHORT).show()
                        showSnackWithAction(
                            message = value.message,
                            view = binding.root,
                            actionMessage = "Retry"
                        ) {
                            viewModel.fetchGenre()
                        }
                    }

                    else -> {}
                }
            }
        }
    }

    private val args : MovieDetailFragmentArgs by navArgs()

    private fun setTransition(){
        binding.thumbnailImageHeader.transitionName = args.movieDetail.posterPath
    }
}