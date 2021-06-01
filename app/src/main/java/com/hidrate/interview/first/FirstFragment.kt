package com.hidrate.interview.first

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import com.hidrate.interview.R
import com.hidrate.interview.main.MAIN_FRAGMENT_RESULT_REQUEST_KEY
import com.hidrate.interview.main.MAIN_RESULT_ACTION_FRAGMENT_KEY
import com.hidrate.interview.main.MainFragmentNavigationResultAction
import com.hidrate.interview.databinding.FragmentFirstBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val binding get() = _binding!!

    private val viewModel: FirstViewModel by viewModels()

    private val compositeDisposable = CompositeDisposable()

    var amountClicked = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        compositeDisposable.add(
            viewModel.action()
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .subscribe({
                    handle(it)
                }, {
                    throw it
                })
        )

        binding.buttonFirst.setOnClickListener {
            viewModel.navigateToSecondPage()
        }
    }

    private fun handle(action: FirstAction) {
        when(action) {
            is FirstAction.NavigateToSecondPage -> {
                if(amountClicked == 0) {
                    binding.buttonFirst.text = getString(R.string.click_again)
                    amountClicked++
                } else {
                    amountClicked = 0
                    binding.buttonFirst.text = getString(R.string.hello_first_fragment)

                    setFragmentResult(MAIN_FRAGMENT_RESULT_REQUEST_KEY, Bundle().apply {
                        putParcelable(
                            MAIN_RESULT_ACTION_FRAGMENT_KEY,
                            MainFragmentNavigationResultAction.NavigateToSecondFragment
                        )
                    })
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        compositeDisposable.dispose()
    }
}