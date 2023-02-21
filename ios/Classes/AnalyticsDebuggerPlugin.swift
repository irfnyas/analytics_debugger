import Flutter
import UIKit
import AnalyticsDebugger

public class AnalyticsDebuggerPlugin: NSObject, FlutterPlugin {
    
  let debugger = AnalyticsDebugger()
    
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "analytics_debugger", binaryMessenger: registrar.messenger())
    let instance = AnalyticsDebuggerPlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
  }

    public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
        switch call.method {
            case MethodNames.show:
                AnalyticsDebuggerMethods.show(debugger: debugger, call: call)
                break
            case MethodNames.hide:
                AnalyticsDebuggerMethods.hide(debugger: debugger)
                break
            case MethodNames.send:
                AnalyticsDebuggerMethods.send(debugger: debugger, call: call)
                break
            default:
                result(FlutterMethodNotImplemented)
        }
    }
}
