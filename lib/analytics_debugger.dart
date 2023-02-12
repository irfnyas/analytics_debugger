import 'package:analytics_debugger/analytics_debugger_platform_interface.dart';

import 'src/debugger_mode.dart';

export 'src/debugger_mode.dart';

class AnalyticsDebugger {
  static void show({
    bool isSystemWide = false,
    DebuggerMode mode = DebuggerMode.bubble,
  }) =>
      AnalyticsDebuggerPlatform.instance.show(
        isSystemWide: isSystemWide,
        mode: mode,
      );

  static void hide() => AnalyticsDebuggerPlatform.instance.hide();

  static void send({
    double? id,
    required String name,
    Map<String, dynamic>? values,
    Map<String, dynamic>? errors,
  }) =>
      AnalyticsDebuggerPlatform.instance.send(
        id: id,
        name: name,
        values: values,
      );
}
