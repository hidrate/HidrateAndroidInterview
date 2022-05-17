package com.hidrate.interview.second

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hidrate.interview.R

class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {

                val list = (0..30).map {
                    it
                }

                var mutableStateList by remember { mutableStateOf(list) }

                List(
                    onFirstButtonClicked = {
                        val newValue = mutableStateList.toMutableList()
                        newValue[0] = 99
                        mutableStateList = newValue
                    },
                    list = list
                )
            }
        }
    }

    @Composable
    fun List(
        onFirstButtonClicked: () -> Unit,
        list: List<Int>
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            list.forEach {
                if (it == 0) {
                    item {
                        Button(onClick = {
                            onFirstButtonClicked()
                        }) {
                            Text(text = "Click me")
                        }
                    }
                }
                item {
                    Item(index = it)
                    Divider(color = Color.Red, thickness = 1.dp)
                }
            }

            item {
                Button(onClick = {
                    navigationBack()
                }) {
                    Text(text = stringResource(id = R.string.previous))
                }
            }
        }
    }

    @Composable
    fun Item(index: Int) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 64.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = "\uD83D\uDC4B Hello from $index")
        }
    }

    private fun navigationBack() {
        requireActivity().onBackPressed()
    }
}