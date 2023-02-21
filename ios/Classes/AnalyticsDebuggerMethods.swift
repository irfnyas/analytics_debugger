//
//  AnalyticsDebuggerMethods.swift
//  analytics_debugger
//
//  Created by Chandra Abdul Fattah on 12/02/23.
//

import Foundation
import Flutter
import AnalyticsDebugger

class AnalyticsDebuggerMethods {
    static func show(
        debugger: AnalyticsDebugger,
        call: FlutterMethodCall
    ){
        let args = call.arguments as! [String : Any]

        let mode = args[Arguments.debuggerMode] as! Bool
        
        if(mode){
            debugger.showBubble()
        }
        else {
            debugger.showBarDebugger()
        }
    }
    
    static func hide(
        debugger: AnalyticsDebugger
    ){
        debugger.hide()
    }
    
    static func send(
        debugger: AnalyticsDebugger,
        call: FlutterMethodCall
    ){
        let args = call.arguments as! [String : Any]
        
        let name = args[Arguments.name] as! String
        let params = args[Arguments.values] as? [String: Any]
        
        let events = params.map { param in
            var values = Array<DebuggerProp>()
            for (key, value) in param {
                values.append(DebuggerProp(id: key, withName: key, withValue: value))
            }
            return values
        }
    
        debugger.publishEvent(
            name,
            withTimestamp: NSNumber(value: NSDate().timeIntervalSince1970),
            withProperties: events ?? [DebuggerProp]()
        )
    }
}
