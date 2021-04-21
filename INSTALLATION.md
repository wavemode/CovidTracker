# Installation

Visit the [releases page](https://github.com/WSU-4110/CovidTracker/releases) to install pre-built binaries of the Android app and data server. Below are the instructions to build the application from scratch.

## Client (Android App)

### Requirements

- [node.js](https://nodejs.org/en/)
- Apache Cordova (`npm install -g cordova`)
- [gradle](https://gradle.org/) (5.0 or newer)
- JDK 1.8 (Newer versions of JDK will NOT work. This is a restriction imposed by the Cordova build process.)

### Steps

1. `cd /path/to/CovidTracker/client/`
2. `cordova platform add android`
3. `cordova build android`
4. (optional - requires Android SDK and build tools) To emulate the app on a PC, launch an Android emulator and execute `cordova emulate android`

## Server (Spring Boot Application)

### Requirements

- JDK 11+ (Yes, building the server requires a different JDK than building the client.)

### Steps

1. `cd /path/to/CovidTracker/server/`
2. `./gradlew run`

## F.A.Q. (Frequently Asked Questions)

### Why did the client fail to build?

Usually this is due to a missing requirement, and usually cordova will tell you exactly what is missing. If you have a build issue you can't figure out, [open an issue](https://github.com/WSU-4110/CovidTracker/issues).

### Why did the server fail to build?

You probably still have JDK 1.8 in your PATH from when you were building the client. Make sure you are using JDK 11 or newer. You have to update your PATH and JAVA_HOME and launch a new terminal.

