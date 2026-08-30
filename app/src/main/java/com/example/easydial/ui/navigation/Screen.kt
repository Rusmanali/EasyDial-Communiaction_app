package com.example.easydial.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Onboarding : Screen("onboarding")
    object Login : Screen("login")
    object SignUp : Screen("signup")
    
    object Main : Screen("main") // Container for bottom nav
    
    object DialPad : Screen("dialpad")
    object IncomingCall : Screen("incoming_call")
    object ActiveCall : Screen("active_call")
    object CallDetails : Screen("call_details")
    
    object Chat : Screen("chat/{contactName}") {
        fun createRoute(contactName: String) = "chat/$contactName"
    }
    
    object Profile : Screen("profile")
    object Settings : Screen("settings")
}
