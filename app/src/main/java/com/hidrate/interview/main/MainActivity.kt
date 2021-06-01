package com.hidrate.interview.main

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import com.hidrate.interview.R
import com.hidrate.interview.databinding.ActivityMainBinding
import com.hidrate.interview.first.FirstFragment
import com.hidrate.interview.second.SecondFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.mainContainerView, FirstFragment())
            }
        }
        
        supportFragmentManager.setFragmentResultListener(
            MAIN_FRAGMENT_RESULT_REQUEST_KEY,
            this
        ) { requestKey, bundle ->
            if (requestKey == MAIN_FRAGMENT_RESULT_REQUEST_KEY) {
                handleBundleFromFragmentResult(bundle)
            }
        }

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Hello from Hidrate!", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun handleBundleFromFragmentResult(bundle: Bundle) {
        if (bundle.containsKey(MAIN_RESULT_ACTION_FRAGMENT_KEY)) {
            bundle.getParcelable<MainFragmentNavigationResultAction>(MAIN_RESULT_ACTION_FRAGMENT_KEY)?.let {
                handle(it)
            }
        }
    }

    private fun handle(action: MainFragmentNavigationResultAction) {
        when (action) {
            is MainFragmentNavigationResultAction.NavigateToSecondFragment -> {
                val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                transaction.replace(
                    R.id.mainContainerView,
                    SecondFragment()
                )
                    .addToBackStack(SecondFragment::class.java.simpleName)
                    .commit()
            }
        }
    }
}