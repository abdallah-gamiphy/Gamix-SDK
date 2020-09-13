package com.gamiphy.library.callback

/**
 * A listener for authentication events from Gamiphy bot such that you can implement your actions for login/SignUp events.
 */
interface OnAuthTrigger {

    /**
     *  Called when the bot requires login/sign up for the user or the login button inside bot clicked.
     *
     *  @param isSignUp : true for sign up redirection,false for login redirection.
     */
    fun onAuthTrigger(isSignUp: Boolean)

}