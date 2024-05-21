package com.engie.eea_tech_interview.ui.movie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import com.engie.domain.model.MovieData
import com.engie.eea_tech_interview.R
import com.engie.eea_tech_interview.databinding.MovieFragmentBinding
import com.engie.eea_tech_interview.ui.adapter.MovieAdapter
import com.engie.eea_tech_interview.ui.adapter.MovieDetailClick
import com.engie.eea_tech_interview.util.CustomProgressDialog
import com.engie.eea_tech_interview.util.safelyNavigate
import com.engie.eea_tech_interview.util.showSnackWithAction
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : Fragment(), MovieDetailClick {

    private lateinit var binding: MovieFragmentBinding
    private val viewModel: MovieFragmentViewModel by viewModel()

    private val progressDialog by lazy { CustomProgressDialog(requireActivity()) }
    private val movieAdapter: MovieAdapter by lazy { MovieAdapter(this) }
    private var query = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            MovieFragmentBinding.inflate(
                inflater,
                container,
                false
            )

        val animation = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = animation

        binding.moviesRecyclerview.apply {
            adapter = movieAdapter
        }

        viewModel.movies.observe(requireActivity()) { result ->
            result?.getContentIfNotHandled()?.let { value ->
                when {
                    value.isSuccess() -> {
                        binding.noMovieLayout.visibility = GONE
                        value.data?.let {
                            movieAdapter.submitList(it)
                        }
                        query = ""
                        progressDialog.stop()
                    }

                    value.isLoading() -> {
                        progressDialog.start(title = "please wait ...")
                    }

                    value.isError() -> {
                        progressDialog.stop()
                        binding.noMovieLayout.visibility = VISIBLE
                        Toast.makeText(requireContext(), value.message, Toast.LENGTH_SHORT).show()
                        showSnackWithAction(
                            message = value.message,
                            view = binding.root,
                            actionMessage = "Retry"
                        ) {
                            viewModel.fetchMovies(
                                if (query.isEmpty()) resources.getString(R.string.default_movie_search) else query
                            )
                        }
                    }

                    else -> {}
                }
            }
        }

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(queryText: String?): Boolean {
                query = queryText ?: ""
                viewModel.fetchMovies(query)
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                Log.d("onQueryTextChange", "query: $query")
                return true
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postponeEnterTransition()
        binding.moviesRecyclerview.doOnPreDraw {
            startPostponedEnterTransition()
        }
    }

    override fun clickOnItem(data: MovieData, thumb: ImageView) {
        val directions: NavDirections =
            MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(data)
        val extras = FragmentNavigatorExtras(
            thumb to (data.posterPath ?: "")
        )
        findNavController().safelyNavigate(directions,extras)
    }
}
