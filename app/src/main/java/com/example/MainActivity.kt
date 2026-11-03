package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch

// Color constants matching assignment specifications
val AppBlue = Color(0xFF2457A7)
val AppLightGrey = Color(0xFFF4F6FA)
val TextDark = Color(0xFF1E293B)
val TextMuted = Color(0xFF64748B)
val IconBlueContainer = Color(0xFFE5EEFC)

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MyApplicationTheme {
        StudentDashboardApp()
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentDashboardApp() {
  var selectedIndex by rememberSaveable { mutableIntStateOf(0) }
  val snackbarHostState = remember { SnackbarHostState() }
  val coroutineScope = rememberCoroutineScope()

  // Handle system back navigation (return to Home tab if not on Home)
  BackHandler(enabled = selectedIndex != 0) {
    selectedIndex = 0
  }

  // Titles corresponding to each navigation destination
  val screenTitle = when (selectedIndex) {
    0 -> "Home"
    1 -> "Courses"
    2 -> "Profile"
    else -> "Dashboard"
  }

  Scaffold(
    containerColor = AppLightGrey,
    topBar = {
      CenterAlignedTopAppBar(
        title = {
          Text(
            text = screenTitle,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 20.sp
          )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
          containerColor = AppBlue,
          titleContentColor = Color.White
        ),
        modifier = Modifier.testTag("top_app_bar")
      )
    },
    bottomBar = {
      NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp,
        modifier = Modifier.testTag("bottom_nav_bar")
      ) {
        NavigationBarItem(
          selected = selectedIndex == 0,
          onClick = { selectedIndex = 0 },
          icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
          label = { Text("Home", fontWeight = if (selectedIndex == 0) FontWeight.Bold else FontWeight.Normal) },
          colors = NavigationBarItemDefaults.colors(
            selectedIconColor = AppBlue,
            selectedTextColor = AppBlue,
            indicatorColor = IconBlueContainer,
            unselectedIconColor = TextMuted,
            unselectedTextColor = TextMuted
          ),
          modifier = Modifier.testTag("nav_home")
        )
        NavigationBarItem(
          selected = selectedIndex == 1,
          onClick = { selectedIndex = 1 },
          icon = { Icon(Icons.Default.MenuBook, contentDescription = "Courses") },
          label = { Text("Courses", fontWeight = if (selectedIndex == 1) FontWeight.Bold else FontWeight.Normal) },
          colors = NavigationBarItemDefaults.colors(
            selectedIconColor = AppBlue,
            selectedTextColor = AppBlue,
            indicatorColor = IconBlueContainer,
            unselectedIconColor = TextMuted,
            unselectedTextColor = TextMuted
          ),
          modifier = Modifier.testTag("nav_courses")
        )
        NavigationBarItem(
          selected = selectedIndex == 2,
          onClick = { selectedIndex = 2 },
          icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
          label = { Text("Profile", fontWeight = if (selectedIndex == 2) FontWeight.Bold else FontWeight.Normal) },
          colors = NavigationBarItemDefaults.colors(
            selectedIconColor = AppBlue,
            selectedTextColor = AppBlue,
            indicatorColor = IconBlueContainer,
            unselectedIconColor = TextMuted,
            unselectedTextColor = TextMuted
          ),
          modifier = Modifier.testTag("nav_profile")
        )
      }
    },
    floatingActionButton = {
      FloatingActionButton(
        onClick = {
          coroutineScope.launch {
            snackbarHostState.showSnackbar(
              message = "New activity button pressed."
            )
          }
        },
        containerColor = AppBlue,
        contentColor = Color.White,
        shape = CircleShape,
        modifier = Modifier.testTag("fab_add")
      ) {
        Icon(
          imageVector = Icons.Default.Add,
          contentDescription = "Add New Activity",
          modifier = Modifier.size(28.dp)
        )
      }
    },
    snackbarHost = {
      SnackbarHost(
        hostState = snackbarHostState,
        modifier = Modifier.testTag("snackbar_host")
      )
    }
  ) { innerPadding ->
    // Center widget providing centered content
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
        .padding(horizontal = 24.dp, vertical = 16.dp),
      contentAlignment = Alignment.Center
    ) {
      AnimatedContent(
        targetState = selectedIndex,
        transitionSpec = { fadeIn() togetherWith fadeOut() },
        label = "DashboardTabContent"
      ) { targetTab ->
        when (targetTab) {
          0 -> HomeScreen(
            onCheckUpdates = {
              coroutineScope.launch {
                snackbarHostState.showSnackbar("Your dashboard is up to date.")
              }
            }
          )
          1 -> CoursesScreen()
          2 -> ProfileScreen()
        }
      }
    }
  }
}

/**
 * HOME SCREEN
 * Features:
 * - School icon
 * - Welcome, Student!
 * - Your learning dashboard is ready.
 * - Check Updates button
 * - Clicking button displays: "Your dashboard is up to date."
 */
@Composable
fun HomeScreen(onCheckUpdates: () -> Unit) {
  DashboardCard(
    testTag = "home_card",
    icon = Icons.Default.School,
    title = "Welcome, Student!",
    subtitle = "Your learning dashboard is ready."
  ) {
    Spacer(modifier = Modifier.height(24.dp))
    ElevatedButton(
      onClick = onCheckUpdates,
      shape = RoundedCornerShape(12.dp),
      colors = androidx.compose.material3.ButtonDefaults.elevatedButtonColors(
        containerColor = AppBlue,
        contentColor = Color.White
      ),
      elevation = androidx.compose.material3.ButtonDefaults.elevatedButtonElevation(
        defaultElevation = 3.dp,
        pressedElevation = 6.dp
      ),
      modifier = Modifier
        .fillMaxWidth(0.85f)
        .height(48.dp)
        .testTag("check_updates_button")
    ) {
      Icon(
        imageVector = Icons.Default.CheckCircle,
        contentDescription = null,
        modifier = Modifier.size(18.dp)
      )
      Spacer(modifier = Modifier.width(8.dp))
      Text(
        text = "Check Updates",
        fontSize = 15.sp,
        fontWeight = FontWeight.SemiBold
      )
    }
  }
}

/**
 * COURSES SCREEN
 * Features:
 * - Book icon
 * - My Courses
 * - Your enrolled courses will appear here.
 */
@Composable
fun CoursesScreen() {
  DashboardCard(
    testTag = "courses_card",
    icon = Icons.Default.MenuBook,
    title = "My Courses",
    subtitle = "Your enrolled courses will appear here."
  ) {
    Spacer(modifier = Modifier.height(20.dp))
    HorizontalDivider(color = Color(0xFFE2E8F0), thickness = 1.dp)
    Spacer(modifier = Modifier.height(16.dp))

    Column(
      modifier = Modifier.fillMaxWidth(),
      verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      CourseItemRow(code = "CS-302", name = "Mobile Apps Development", status = "In Progress")
      CourseItemRow(code = "CS-204", name = "Database Systems", status = "Enrolled")
      CourseItemRow(code = "SE-210", name = "Software Engineering", status = "Enrolled")
    }
  }
}

/**
 * PROFILE SCREEN
 * Features:
 * - Person avatar
 * - Student Profile
 * - View your personal and academic information.
 */
@Composable
fun ProfileScreen() {
  DashboardCard(
    testTag = "profile_card",
    icon = Icons.Default.Person,
    title = "Student Profile",
    subtitle = "View your personal and academic information."
  ) {
    Spacer(modifier = Modifier.height(20.dp))
    HorizontalDivider(color = Color(0xFFE2E8F0), thickness = 1.dp)
    Spacer(modifier = Modifier.height(16.dp))

    Column(
      modifier = Modifier.fillMaxWidth(),
      verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      ProfileDetailRow(label = "Student Name", value = "Alex Student")
      ProfileDetailRow(label = "Student ID", value = "STU-2026-9042")
      ProfileDetailRow(label = "Department", value = "Computer Science")
      ProfileDetailRow(label = "Academic Status", value = "Active / Enrolled")
    }
  }
}

/**
 * Reusable white rounded Container with subtle shadow as required by Assignment 2.
 */
@Composable
fun DashboardCard(
  testTag: String,
  icon: ImageVector,
  title: String,
  subtitle: String,
  extraContent: @Composable () -> Unit = {}
) {
  Card(
    shape = RoundedCornerShape(20.dp),
    colors = CardDefaults.cardColors(containerColor = Color.White),
    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
    modifier = Modifier
      .fillMaxWidth()
      .widthIn(max = 420.dp)
      .shadow(elevation = 6.dp, shape = RoundedCornerShape(20.dp), spotColor = Color(0x22000000))
      .testTag(testTag)
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(28.dp)
        .verticalScroll(rememberScrollState()),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      // Circular avatar / icon container with subtle blue background
      Surface(
        shape = CircleShape,
        color = IconBlueContainer,
        modifier = Modifier.size(84.dp)
      ) {
        Box(contentAlignment = Alignment.Center) {
          Icon(
            imageVector = icon,
            contentDescription = title,
            tint = AppBlue,
            modifier = Modifier.size(44.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(18.dp))

      Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        color = TextDark,
        textAlign = TextAlign.Center
      )

      Spacer(modifier = Modifier.height(8.dp))

      Text(
        text = subtitle,
        style = MaterialTheme.typography.bodyMedium,
        color = TextMuted,
        textAlign = TextAlign.Center,
        lineHeight = 20.sp
      )

      extraContent()
    }
  }
}

@Composable
fun CourseItemRow(code: String, name: String, status: String) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .background(Color(0xFFF8FAFC), RoundedCornerShape(10.dp))
      .padding(horizontal = 12.dp, vertical = 10.dp),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Column(modifier = Modifier.weight(1f)) {
      Text(
        text = name,
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp,
        color = TextDark
      )
      Text(
        text = code,
        fontSize = 11.sp,
        color = TextMuted
      )
    }
    Surface(
      color = IconBlueContainer,
      shape = RoundedCornerShape(6.dp)
    ) {
      Text(
        text = status,
        color = AppBlue,
        fontSize = 11.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
      )
    }
  }
}

@Composable
fun ProfileDetailRow(label: String, value: String) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .background(Color(0xFFF8FAFC), RoundedCornerShape(10.dp))
      .padding(horizontal = 14.dp, vertical = 10.dp),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Text(
      text = label,
      fontSize = 13.sp,
      color = TextMuted,
      fontWeight = FontWeight.Normal
    )
    Text(
      text = value,
      fontSize = 13.sp,
      color = TextDark,
      fontWeight = FontWeight.SemiBold
    )
  }
}
