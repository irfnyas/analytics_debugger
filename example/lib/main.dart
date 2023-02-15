import 'package:flutter/material.dart';

import 'package:analytics_debugger/analytics_debugger.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Analytics Debugger'),
        ),
        body: ListView(
          children: [
            ListTile(
              title: const Text('Show'),
              onTap: () => AnalyticsDebugger.show(
                isSystemWide: false,
                mode: DebuggerMode.bubble,
              ),
            ),
            ListTile(
              title: const Text('Hide'),
              onTap: () => AnalyticsDebugger.hide(),
            ),
            ListTile(
              title: const Text('Send'),
              onTap: () => AnalyticsDebugger.send(
                name: 'Test',
                values: {
                  'no': 1,
                  'name': 'Chandra',
                  'height': 50.6,
                  'gender': true,
                },
              ),
            ),
          ],
        ),
      ),
    );
  }
}
