import 'dart:async';
import 'package:in_app_purchase/in_app_purchase.dart';

class IAPService {
  IAPService._();
  static final IAPService instance = IAPService._();

  final InAppPurchase _iap = InAppPurchase.instance;
  late final StreamSubscription<List<PurchaseDetails>> _sub;

  Future<void> init() async {
    final available = await _iap.isAvailable();
    // ignore: avoid_print
    print('IAP available: $available');
    _sub = _iap.purchaseStream.listen((purchases) {
      for (final p in purchases) {
        // In a full app verify and deliver
        // ignore: avoid_print
        print('Purchase update: ${p.productID} status=${p.status}');
      }
    }, onDone: () {}, onError: (e) {
      // ignore: avoid_print
      print('IAP stream error: $e');
    });
  }

  Future<void> dispose() async {
    await _sub.cancel();
  }

  Future<void> buyProduct(String productId) async {
    final detailsResponse = await _iap.queryProductDetails({productId});
    if (detailsResponse.notFoundIDs.isNotEmpty) return;
    final details = detailsResponse.productDetails.first;
    final purchaseParam = PurchaseParam(productDetails: details);
    await _iap.buyConsumable(purchaseParam: purchaseParam);
  }
}
