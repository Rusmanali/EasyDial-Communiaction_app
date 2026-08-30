package com.example.easydial.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.easydial.ui.screens.auth.*
import com.example.easydial.ui.screens.call.*
import com.example.easydial.ui.screens.chat.*
import com.example.easydial.ui.screens.main.*
import com.example.easydial.ui.screens.settings.*
import com.example.easydial.viewmodel.CallViewModel

@Composable
fun AppNavigation(
    navController: NavHostController,
    callViewModel: CallViewModel = viewModel()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(onNextScreen = { isLoggedIn ->
                if (isLoggedIn) {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                } else {
                    navController.navigate(Screen.Onboarding.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            })
        }
        
        composable(Screen.Onboarding.route) {
            OnboardingScreen(onFinished = {
                navController.navigate(Screen.Login.route) {
                    popUpTo(Screen.Onboarding.route) { inclusive = true }
                }
            })
        }
        
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onNavigateToSignUp = {
                    navController.navigate(Screen.SignUp.route)
                }
            )
        }
        
        composable(Screen.SignUp.route) {
            SignUpScreen(
                onSignUpSuccess = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    navController.popBackStack()
                }
            )
        }
        
        composable(Screen.Main.route) {
            MainScreen(
                onNavigateToDialPad = { navController.navigate(Screen.DialPad.route) },
                onNavigateToChat = { name -> navController.navigate(Screen.Chat.createRoute(name)) },
                onNavigateToProfile = { navController.navigate(Screen.Profile.route) },
                onNavigateToCallDetails = { navController.navigate(Screen.CallDetails.route) },
                onNavigateToSettings = { navController.navigate(Screen.Settings.route) }
            )
        }
        
        composable(Screen.DialPad.route) {
            DialPad(onCallStarted = { number -> 
                callViewModel.makeCall(number)
            })
        }
        
        composable(Screen.IncomingCall.route) {
            IncomingCall(
                onAccept = { callViewModel.acceptCall() },
                onDecline = { callViewModel.endCall() }
            )
        }
        
        composable(Screen.ActiveCall.route) {
            ActiveCall(onEndCall = { callViewModel.endCall() })
        }
        
        composable(Screen.CallDetails.route) {
            CallDetails(onBack = { navController.popBackStack() })
        }
        
        composable(Screen.Chat.route) { backStackEntry ->
            val contactName = backStackEntry.arguments?.getString("contactName")
            ChatScreen(contactName, onBack = { navController.popBackStack() })
        }
        
        composable(Screen.Profile.route) {
            ProfileScreen(onNavigateToSettings = { navController.navigate(Screen.Settings.route) })
        }
        
        composable(Screen.Settings.route) {
            SettingsScreen(onBack = { navController.popBackStack() })
        }
    }
}
