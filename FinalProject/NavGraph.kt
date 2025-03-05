package com.teamneards.classtrack

import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import com.teamneards.classtrack.ApiWork.DailyStudyTipScreen
import com.teamneards.classtrack.Screens.Main.HomeScreen
import com.teamneards.classtrack.Screens.AuthScreens.LoginScreen
import com.teamneards.classtrack.Screens.UserProfile.ProfileScreen
import com.teamneards.classtrack.Screens.RoutineScreens.ShowRoutineScreen
import com.teamneards.classtrack.Screens.AuthScreens.SignupScreen
import com.teamneards.classtrack.Screens.Main.SplashScreen
import com.teamneards.classtrack.Screens.RoutineScreens.UploadRoutineScreen


@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash_screen") {

        composable("splash_screen") { SplashScreen(navController) }
        composable("login_screen") { LoginScreen(navController) }
        composable("signup_screen") { SignupScreen(navController) }
        composable("home_screen") { HomeScreen(navController) }
        composable("upload_routine_screen") { UploadRoutineScreen(navController) }
        composable("show_routine_screen") { ShowRoutineScreen(navController) }
        composable("profile_screen") { ProfileScreen(navController) }
        composable("daily_study_tip_screen") {
            DailyStudyTipScreen(navController)
        }
    }
}