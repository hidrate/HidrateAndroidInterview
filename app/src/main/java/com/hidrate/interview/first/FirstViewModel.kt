package com.hidrate.interview.first

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class FirstViewModel : ViewModel() {

    private val action: PublishSubject<FirstAction> = PublishSubject.create()
    fun action(): Observable<FirstAction> = action.hide()

    fun navigateToSecondPage() {
        action.onNext(FirstAction.NavigateToSecondPage)
    }

    private var amountClicked = 0

    fun resetAmountClicked() {
        amountClicked = 0
    }

    fun incrementAmountClicked() {
        amountClicked++
    }

    fun hasBeenClicked() = amountClicked > 0

}