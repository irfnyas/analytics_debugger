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
![Android Analytics Debugger](https://user-images.githubusercontent.com/16184998/220386292-2cac400d-f6f8-4a7c-a26f-f6c76068493a.gif?raw=true)

## iOS
![IOS Analytics Debugger](https://user-images.githubusercontent.com/16184998/220386376-c4b7b52e-03c3-4592-a8fb-d82202731ccd.gif?raw=true)

## Getting involved
First of all, thank you for even considering to get involved. You are a real super :star:  and we :heart:  you!

### Reporting bugs and issues
Use the configured [Github issue report template](https://github.com/chandrabezzo/analytics_debugger/issues/new?assignees=&labels=&template=bug_report.md&title=) when reporting an issue. Make sure to state your observations and expectations
as objectively and informative as possible so that we can understand your need and be able to troubleshoot.

### Discussions and ideas
We're happy to discuss and talk about ideas in the
[repository discussions](https://github.com/chandrabezzo/analytics_debugger/discussions) and/or post your
question to [StackOverflow](https://stackoverflow.com/search?q=analytics+debugger).
