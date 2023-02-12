import 'package:analytics_debugger/src/method_names.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'analytics_debugger_platform_interface.dart';
import 'src/arguments.dart';
import 'src/debugger_mode.dart';

/// An implementation of [AnalyticsDebuggerPlatform] that uses method channels.
class MethodChannelAnalyticsDebugger extends AnalyticsDebuggerPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('analytics_debugger');

  @override
  void show({
    bool isSystemWide = false,
    DebuggerMode mode = DebuggerMode.bubble,
  }) {
    final arguments = <String, dynamic>{
      Arguments.debuggerMode: (mode == DebuggerMode.bubble),
      Arguments.systemWide: isSystemWide,
    };

    methodChannel.invokeMethod(MethodNames.show, arguments);
  }

  @override
  void hide() => methodChannel.invokeMethod(MethodNames.hide);

  @override
  void send({
    double? id,
    required String name,
    Map<String, dynamic>? values,
  }) {
    final arguments = <String, dynamic>{
      Arguments.id: id ?? DateTime.now().millisecond,
      Arguments.name: name,
      Arguments.values: values,
    };

    methodChannel.invokeMethod(MethodNames.send, arguments);
  }
}
