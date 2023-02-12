// import 'package:flutter/services.dart';
// import 'package:flutter_test/flutter_test.dart';
// import 'package:analytics_debugger/analytics_debugger_method_channel.dart';

// void main() {
//   MethodChannelAnalyticsDebugger platform = MethodChannelAnalyticsDebugger();
//   const MethodChannel channel = MethodChannel('analytics_debugger');

//   TestWidgetsFlutterBinding.ensureInitialized();

//   setUp(() {
//     channel.setMockMethodCallHandler((MethodCall methodCall) async {
//       return '42';
//     });
//   });

//   tearDown(() {
//     channel.setMockMethodCallHandler(null);
//   });

//   test('getPlatformVersion', () async {
//     expect(await platform.getPlatformVersion(), '42');
//   });
// }
