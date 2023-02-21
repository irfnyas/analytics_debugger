# Analytics Debugger
![GitHub code size](https://img.shields.io/github/languages/code-size/chandrabezzo/analytics_debugger)
![GitHub followers](https://img.shields.io/github/followers/chandrabezzo?style=social)
![GitHub contributors](https://img.shields.io/github/contributors/chandrabezzo/analytics_debugger)
[![Linkedin](https://i.stack.imgur.com/gVE0j.png) LinkedIn](https://www.linkedin.com/in/chandra-abdul-fattah/)
[![GitHub](https://i.stack.imgur.com/tskMh.png) GitHub](https://github.com/chandrabezzo/)

`analytics_debugger` allows you to create togglable UI to show list of background events, useful to check analytics events tracker, network, or anything in debug builds.

# Documentation
## Flutter
Currently analytics_debugger just have three methods, show, hide, and send.

### Show
To show toggleable UI in your app just call `AnalyticsDebugger.show` once. Then, your Toggleable UI will show in bottom right of your device screen. You can choose two different layout bubble or bar.

### Hide
To hide toggleable UI in your app just call `AnalyticsDebugger.hide` once.

### Send
To send anything values to toggleable UI in your app just call `AnalyticsDebugger.send`. Value will identified automatically by time. Every value must have a name with optional params. We can send anything value in `Map` to our toggleable UI.

## Android
For Android, in `show` method we can configure `isSystemWide` true or false. The debugger view will become a system-wide overlay. Might be useful if you want to see events while your app is in background. But, you need enable `Draw over other apps` in Settings -> Apps -> Your App.
![The example app running in Android](https://user-images.githubusercontent.com/16184998/220381035-fc21c461-f6e4-46ae-a702-e3fc430bd75d.mp4?raw=true)

## iOS
![The example app running in iOS](https://user-images.githubusercontent.com/16184998/220381048-df5a6e95-176d-45ac-a392-925a3a7ea8f1.mp4?raw=true)

## Getting involved
First of all, thank you for even considering to get involved. You are a real super :star:  and we :heart:  you!

### Reporting bugs and issues
Use the configured [Github issue report template](https://github.com/chandrabezzo/analytics_debugger/issues/new?assignees=&labels=&template=bug_report.md&title=) when reporting an issue. Make sure to state your observations and expectations
as objectively and informative as possible so that we can understand your need and be able to troubleshoot.

### Discussions and ideas
We're happy to discuss and talk about ideas in the
[repository discussions](https://github.com/chandrabezzo/analytics_debugger/discussions) and/or post your
question to [StackOverflow](https://stackoverflow.com/search?q=analytics+debugger).
