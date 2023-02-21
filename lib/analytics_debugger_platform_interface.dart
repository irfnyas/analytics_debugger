import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'analytics_debugger_method_channel.dart';
import 'src/debugger_mode.dart';

abstract class AnalyticsDebuggerPlatform extends PlatformInterface {
  /// Constructs a AnalyticsDebuggerPlatform.
  AnalyticsDebuggerPlatform() : super(token: _token);

  static final Object _token = Object();

  static AnalyticsDebuggerPlatform _instance = MethodChannelAnalyticsDebugger();

  /// The default instance of [AnalyticsDebuggerPlatform] to use.
  ///
  /// Defaults to [MethodChannelAnalyticsDebugger].
  static AnalyticsDebuggerPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [AnalyticsDebuggerPlatform] when
  /// they register themselves.
  static set instance(AnalyticsDebuggerPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  void show({
    bool isSystemWide = false,
    DebuggerMode mode = DebuggerMode.bubble,
  }) =>
      throw UnimplementedError('show() has not been implemented.');

  void hide() => throw UnimplementedError('hide() has not been implemented.');

  void send({
    required String name,
    Map<String, dynamic>? values,
  }) =>
      throw UnimplementedError('send() has not been implemented.');
}
