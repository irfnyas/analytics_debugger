// import 'package:flutter_test/flutter_test.dart';
// import 'package:analytics_debugger/analytics_debugger.dart';
// import 'package:analytics_debugger/analytics_debugger_platform_interface.dart';
// import 'package:analytics_debugger/analytics_debugger_method_channel.dart';
// import 'package:plugin_platform_interface/plugin_platform_interface.dart';

// class MockAnalyticsDebuggerPlatform
//     with MockPlatformInterfaceMixin
//     implements AnalyticsDebuggerPlatform {

//   @override
//   Future<String?> getPlatformVersion() => Future.value('42');
// }

// void main() {
//   final AnalyticsDebuggerPlatform initialPlatform = AnalyticsDebuggerPlatform.instance;

//   test('$MethodChannelAnalyticsDebugger is the default instance', () {
//     expect(initialPlatform, isInstanceOf<MethodChannelAnalyticsDebugger>());
//   });

//   test('getPlatformVersion', () async {
//     AnalyticsDebugger analyticsDebuggerPlugin = AnalyticsDebugger();
//     MockAnalyticsDebuggerPlatform fakePlatform = MockAnalyticsDebuggerPlatform();
//     AnalyticsDebuggerPlatform.instance = fakePlatform;

//     expect(await analyticsDebuggerPlugin.getPlatformVersion(), '42');
//   });
// }
