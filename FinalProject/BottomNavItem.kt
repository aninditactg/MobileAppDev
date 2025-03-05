package com.teamneards.classtrack

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Upload
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val title: String, val icon: ImageVector) {
    object Home : BottomNavItem("home_screen", "Home", Icons.Default.Home)
    object Upload : BottomNavItem("upload_routine_screen", "Upload", Icons.Default.Upload)
    object Profile : BottomNavItem("profile_screen", "Profile", Icons.Default.Person)
    object StudyTip : BottomNavItem( "daily_study_tip_screen", "Study Tip", Icons.Default.Lightbulb,)
}