import 'package:flutter/material.dart';
import '../services/iap_service.dart';

class StoreScreen extends StatefulWidget {
  const StoreScreen({super.key});

  @override
  State<StoreScreen> createState() => _StoreScreenState();
}

class _StoreScreenState extends State<StoreScreen> {
  bool _loading = false;

  Future<void> _buySample() async {
    setState(() => _loading = true);
    try {
      // Use your real product id configured in Play Console
      await IAPService.instance.buyProduct('magma_sample_product');
      if (mounted) ScaffoldMessenger.of(context).showSnackBar(const SnackBar(content: Text('Purchase started (check logs)')));
    } catch (e) {
      if (mounted) ScaffoldMessenger.of(context).showSnackBar(SnackBar(content: Text('Purchase error: $e')));
    } finally {
      setState(() => _loading = false);
    }
  }

  @override
  void dispose() {
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Magma Store')),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            const Text('Featured', style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold)),
            const SizedBox(height: 12),
            Card(
              child: ListTile(
                title: const Text('Magma Premium Sample'),
                subtitle: const Text('One-time sample purchase'),
                trailing: ElevatedButton(onPressed: _loading ? null : _buySample, child: _loading ? const CircularProgressIndicator() : const Text('Buy')),
              ),
            ),
            const SizedBox(height: 24),
            const Text('Store is a stub. Configure products in Play Console and update product ids.'),
          ],
        ),
      ),
    );
  }
}
