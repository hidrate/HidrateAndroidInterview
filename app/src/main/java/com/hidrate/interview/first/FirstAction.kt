package com.hidrate.interview.first

/**
 * Actions taken place on the [FirstFragment]
 */
sealed class FirstAction {
    object NavigateToSecondPage : FirstAction()
}