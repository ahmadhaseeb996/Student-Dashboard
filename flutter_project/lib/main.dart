import 'package:flutter/material.dart';

void main() {
  runApp(const StudentDashboardApp());
}

/// Root Application Widget
class StudentDashboardApp extends StatelessWidget {
  const StudentDashboardApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Student Dashboard',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        useMaterial3: true,
        primaryColor: const Color(0xFF2457A7),
        scaffoldBackgroundColor: const Color(0xFFF4F6FA),
      ),
      home: const StudentDashboardHomePage(),
    );
  }
}

/// StatefulWidget for managing navigation and dashboard state
class StudentDashboardHomePage extends StatefulWidget {
  const StudentDashboardHomePage({super.key});

  @override
  State<StudentDashboardHomePage> createState() =>
      _StudentDashboardHomePageState();
}

class _StudentDashboardHomePageState extends State<StudentDashboardHomePage> {
  // Current active bottom navigation tab index
  int _currentIndex = 0;

  // Titles for each navigation destination
  final List<String> _titles = [
    'Home',
    'Courses',
    'Profile',
  ];

  // Helper method to display a SnackBar with feedback message
  void _showMessage(BuildContext context, String message) {
    ScaffoldMessenger.of(context).clearSnackBars();
    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(
        content: Text(message),
        duration: const Duration(seconds: 2),
        behavior: SnackBarBehavior.floating,
      ),
    );
  }

  // Home Screen Content Widget
  Widget _buildHomeScreen(BuildContext context) {
    return _buildCard(
      icon: Icons.school,
      title: 'Welcome, Student!',
      subtitle: 'Your learning dashboard is ready.',
      extraWidget: Column(
        children: [
          const SizedBox(height: 24),
          ElevatedButton(
            style: ElevatedButton.styleFrom(
              backgroundColor: const Color(0xFF2457A7),
              foregroundColor: Colors.white,
              padding: const EdgeInsets.symmetric(horizontal: 28, vertical: 14),
              shape: RoundedCornerShape(),
              elevation: 3,
            ),
            onPressed: () {
              // SnackBar feedback on Check Updates button press
              _showMessage(context, 'Your dashboard is up to date.');
            },
            child: const Row(
              mainAxisSize: MainAxisSize.min,
              children: [
                Icon(Icons.check_circle_outline, size: 20),
                SizedBox(width: 8),
                Text(
                  'Check Updates',
                  style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold),
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }

  // Courses Screen Content Widget
  Widget _buildCoursesScreen() {
    return _buildCard(
      icon: Icons.book,
      title: 'My Courses',
      subtitle: 'Your enrolled courses will appear here.',
      extraWidget: Padding(
        padding: const EdgeInsets.only(top: 20.0),
        child: Container(
          padding: const EdgeInsets.all(12),
          decoration: BoxDecoration(
            color: const Color(0xFFF8FAFC),
            borderRadius: BorderRadius.circular(10),
            border: Border.all(color: const Color(0xFFE2E8F0)),
          ),
          child: const Row(
            mainAxisSize: MainAxisSize.min,
            children: [
              Icon(Icons.class_, color: Color(0xFF2457A7), size: 20),
              SizedBox(width: 8),
              Text(
                'Mobile Apps Development (Assignment 2)',
                style: TextStyle(
                  fontWeight: FontWeight.w600,
                  color: Color(0xFF1E293B),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }

  // Profile Screen Content Widget
  Widget _buildProfileScreen() {
    return _buildCard(
      icon: Icons.person,
      title: 'Student Profile',
      subtitle: 'View your personal and academic information.',
      extraWidget: Padding(
        padding: const EdgeInsets.only(top: 20.0),
        child: Column(
          children: [
            _buildProfileRow('Student ID', 'STU-2026-9042'),
            const SizedBox(height: 8),
            _buildProfileRow('Program', 'Computer Science'),
            const SizedBox(height: 8),
            _buildProfileRow('Status', 'Active Student'),
          ],
        ),
      ),
    );
  }

  // Reusable helper for profile info rows
  Widget _buildProfileRow(String label, String value) {
    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
      decoration: BoxDecoration(
        color: const Color(0xFFF8FAFC),
        borderRadius: BorderRadius.circular(8),
      ),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Text(label, style: const TextStyle(color: Color(0xFF64748B))),
          Text(
            value,
            style: const TextStyle(
              fontWeight: FontWeight.bold,
              color: Color(0xFF1E293B),
            ),
          ),
        ],
      ),
    );
  }

  // White rounded Container with subtle shadow
  Widget _buildCard({
    required IconData icon,
    required String title,
    required String subtitle,
    Widget? extraWidget,
  }) {
    return Container(
      width: double.infinity,
      constraints: const BoxConstraints(maxWidth: 380),
      margin: const EdgeInsets.symmetric(horizontal: 24),
      padding: const EdgeInsets.all(28),
      decoration: BoxDecoration(
        color: Colors.white,
        borderRadius: BorderRadius.circular(20),
        boxShadow: [
          BoxShadow(
            color: Colors.black.withOpacity(0.06),
            blurRadius: 15,
            spreadRadius: 2,
            offset: const Offset(0, 4),
          ),
        ],
      ),
      child: Column(
        mainAxisSize: MainAxisSize.min,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          // Circular icon container
          Container(
            width: 80,
            height: 80,
            decoration: const BoxDecoration(
              color: Color(0xFFE5EEFC),
              shape: BoxShape.circle,
            ),
            child: Icon(
              icon,
              size: 44,
              color: const Color(0xFF2457A7),
            ),
          ),
          const SizedBox(height: 20),
          Text(
            title,
            style: const TextStyle(
              fontSize: 22,
              fontWeight: FontWeight.bold,
              color: Color(0xFF1E293B),
            ),
            textAlign: TextAlign.center,
          ),
          const SizedBox(height: 8),
          Text(
            subtitle,
            style: const TextStyle(
              fontSize: 15,
              color: Color(0xFF64748B),
              height: 1.4,
            ),
            textAlign: TextAlign.center,
          ),
          if (extraWidget != null) extraWidget,
        ],
      ),
    );
  }

  // Returns rounded border shape for buttons
  OutlinedBorder RoundedCornerShape() {
    return RoundedRectangleBorder(
      borderRadius: BorderRadius.circular(12),
    );
  }

  @override
  Widget build(BuildContext context) {
    // 1. Scaffold widget
    return Scaffold(
      // 10. Light grey background (#F4F6FA)
      backgroundColor: const Color(0xFFF4F6FA),

      // 2. AppBar with blue background (#2457A7) and dynamic title
      appBar: AppBar(
        title: Text(
          _titles[_currentIndex],
          style: const TextStyle(
            fontWeight: FontWeight.bold,
            color: Colors.white,
          ),
        ),
        backgroundColor: const Color(0xFF2457A7),
        centerTitle: true,
        elevation: 0,
      ),

      // 5. Center widget for centered content
      body: Center(
        child: IndexedStack(
          index: _currentIndex,
          alignment: Alignment.center,
          children: [
            _buildHomeScreen(context),
            _buildCoursesScreen(),
            _buildProfileScreen(),
          ],
        ),
      ),

      // 4. FloatingActionButton with Add icon
      floatingActionButton: FloatingActionButton(
        backgroundColor: const Color(0xFF2457A7),
        foregroundColor: Colors.white,
        child: const Icon(Icons.add),
        onPressed: () {
          // SnackBar for floating button feedback
          _showMessage(context, 'New activity button pressed.');
        },
      ),

      // 3. Bottom NavigationBar with three destinations: Home, Courses, Profile
      bottomNavigationBar: BottomNavigationBar(
        currentIndex: _currentIndex,
        onTap: (int index) {
          // 8. StatefulWidget and setState for navigation
          setState(() {
            _currentIndex = index;
          });
        },
        selectedItemColor: const Color(0xFF2457A7),
        unselectedItemColor: const Color(0xFF64748B),
        backgroundColor: Colors.white,
        elevation: 8,
        items: const [
          BottomNavigationBarItem(
            icon: Icon(Icons.home),
            label: 'Home',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.book),
            label: 'Courses',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.person),
            label: 'Profile',
          ),
        ],
      ),
    );
  }
}
