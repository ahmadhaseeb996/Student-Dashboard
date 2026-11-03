import 'package:flutter/material.dart';

void main() {
  runApp(const StudentDashboardApp());
}

class StudentDashboardApp extends StatelessWidget {
  const StudentDashboardApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Student Dashboard',
      theme: ThemeData(
        useMaterial3: true,
        colorScheme: ColorScheme.fromSeed(
          seedColor: const Color(0xFF2457A7),
        ),
        scaffoldBackgroundColor: const Color(0xFFF4F6FA),
      ),
      home: const DashboardScreen(),
    );
  }
}

class DashboardScreen extends StatefulWidget {
  const DashboardScreen({super.key});

  @override
  State<DashboardScreen> createState() => _DashboardScreenState();
}

class _DashboardScreenState extends State<DashboardScreen> {
  int selectedIndex = 0;

  final List<String> pageTitles = [
    'Home',
    'Courses',
    'Profile',
  ];

  final List<IconData> pageIcons = [
    Icons.home_rounded,
    Icons.menu_book_rounded,
    Icons.person_rounded,
  ];

  void showMessage(String message) {
    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(
        content: Text(message),
        behavior: SnackBarBehavior.floating,
      ),
    );
  }

  void selectPage(int index) {
    setState(() {
      selectedIndex = index;
    });
  }

  Widget buildDashboardContent() {
    if (selectedIndex == 1) {
      return const Column(
        mainAxisSize: MainAxisSize.min,
        children: [
          Icon(Icons.menu_book_rounded, size: 58, color: Color(0xFF2457A7)),
          SizedBox(height: 14),
          Text(
            'My Courses',
            style: TextStyle(fontSize: 23, fontWeight: FontWeight.bold),
          ),
          SizedBox(height: 8),
          Text(
            'Your enrolled courses will appear here.',
            textAlign: TextAlign.center,
            style: TextStyle(color: Colors.black54),
          ),
        ],
      );
    }

    if (selectedIndex == 2) {
      return const Column(
        mainAxisSize: MainAxisSize.min,
        children: [
          CircleAvatar(
            radius: 35,
            backgroundColor: Color(0xFFDCE7F8),
            child: Icon(Icons.person, size: 42, color: Color(0xFF2457A7)),
          ),
          SizedBox(height: 14),
          Text(
            'Student Profile',
            style: TextStyle(fontSize: 23, fontWeight: FontWeight.bold),
          ),
          SizedBox(height: 8),
          Text(
            'View your personal and academic information.',
            textAlign: TextAlign.center,
            style: TextStyle(color: Colors.black54),
          ),
        ],
      );
    }

    return Column(
      mainAxisSize: MainAxisSize.min,
      children: [
        const Icon(
          Icons.school_rounded,
          size: 62,
          color: Color(0xFF2457A7),
        ),
        const SizedBox(height: 14),
        const Text(
          'Welcome, Student!',
          style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
        ),
        const SizedBox(height: 8),
        const Text(
          'Your learning dashboard is ready.',
          textAlign: TextAlign.center,
          style: TextStyle(color: Colors.black54, fontSize: 15),
        ),
        const SizedBox(height: 22),
        ElevatedButton.icon(
          onPressed: () {
            showMessage('Your dashboard is up to date.');
          },
          icon: const Icon(Icons.check_circle_outline),
          label: const Text('Check Updates'),
          style: ElevatedButton.styleFrom(
            padding: const EdgeInsets.symmetric(
              horizontal: 22,
              vertical: 13,
            ),
          ),
        ),
      ],
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Student ${pageTitles[selectedIndex]}'),
        centerTitle: true,
        backgroundColor: const Color(0xFF2457A7),
        foregroundColor: Colors.white,
      ),
      body: Center(
        child: Padding(
          padding: const EdgeInsets.all(24),
          child: Container(
            width: double.infinity,
            padding: const EdgeInsets.all(24),
            decoration: BoxDecoration(
              color: Colors.white,
              borderRadius: BorderRadius.circular(18),
              boxShadow: [
                BoxShadow(
                  color: Colors.black.withOpacity(0.06),
                  blurRadius: 12,
                  offset: const Offset(0, 4),
                ),
              ],
            ),
            child: buildDashboardContent(),
          ),
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          showMessage('New activity button pressed.');
        },
        backgroundColor: const Color(0xFF2457A7),
        foregroundColor: Colors.white,
        child: const Icon(Icons.add),
      ),
      bottomNavigationBar: NavigationBar(
        selectedIndex: selectedIndex,
        onDestinationSelected: selectPage,
        destinations: const [
          NavigationDestination(
            icon: Icon(Icons.home_outlined),
            selectedIcon: Icon(Icons.home),
            label: 'Home',
          ),
          NavigationDestination(
            icon: Icon(Icons.menu_book_outlined),
            selectedIcon: Icon(Icons.menu_book),
            label: 'Courses',
          ),
          NavigationDestination(
            icon: Icon(Icons.person_outline),
            selectedIcon: Icon(Icons.person),
            label: 'Profile',
          ),
        ],
      ),
    );
  }
}
