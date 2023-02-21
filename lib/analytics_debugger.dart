import 'package:analytics_debugger/analytics_debugger_platform_interface.dart';

import 'src/debugger_mode.dart';

export 'src/debugger_mode.dart';

class AnalyticsDebugger {
  /// To show toggleable UI. [isSystemWide] will impact for Android only.
  /// By default debugger [mode] will selected to bubble. You can change
  /// the UI with bar.
  static void show({
    bool isSystemWide = false,
    DebuggerMode mode = DebuggerMode.bubble,
  }) =>
      AnalyticsDebuggerPlatform.instance.show(
        isSystemWide: isSystemWide,
        mode: mode,
      );

  ///To hide toggleable UI
  static void hide() => AnalyticsDebuggerPlatform.instance.hide();

  ///[send] anything value to toggleable UI
  static void send({
    required String name,
    Map<String, dynamic>? values,
  }) =>
      AnalyticsDebuggerPlatform.instance.send(
        name: name,
        values: values,
      );
}
