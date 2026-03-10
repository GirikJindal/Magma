import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import '../models/user_model.dart';
import '../services/auth_service.dart';
import '../services/db_service.dart';
import '../services/notification_service.dart';
import 'store_screen.dart';

class HomeScreen extends StatefulWidget {
  final UserModel user;
  const HomeScreen({super.key, required this.user});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  int _points = 0;

  @override
  void initState() {
    super.initState();
    _loadPoints();
    NotificationService.instance.init();
  }

  Future<void> _loadPoints() async {
    // In a full app we'd query remote and local DB; here we show a stub
    setState(() => _points = 0);
  }

  Future<void> _signOut() async {
    final auth = Provider.of<AuthService>(context, listen: false);
    await auth.signOut();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Magma — Home'), actions: [IconButton(onPressed: _signOut, icon: const Icon(Icons.logout))]),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text('Welcome, ${widget.user.email ?? widget.user.uid}', style: Theme.of(context).textTheme.titleLarge),
            const SizedBox(height: 12),
            Card(child: ListTile(title: const Text('Points'), subtitle: Text('$_points'))),
            const SizedBox(height: 12),
            ElevatedButton(onPressed: () async { await DbService.instance.addPoints(widget.user.uid, 10); ScaffoldMessenger.of(context).showSnackBar(const SnackBar(content: Text('Added 10 points (local)'))); }, child: const Text('Earn 10 points (local)')),
            const SizedBox(height: 12),
            ElevatedButton(onPressed: () { Navigator.of(context).push(MaterialPageRoute(builder: (_) => const StoreScreen())); }, child: const Text('Open Store')),
            const SizedBox(height: 12),
            const Text('Core modules (stubs):'),
            const SizedBox(height: 8),
            const Text('- Clubs & Colleges\n- Tasks / Assignments\n- Virtual Store\n- E-library\n- Events\n- App Store\n- Video streaming\n'),
          ],
        ),
      ),
    );
  }
}
