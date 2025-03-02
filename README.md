# README #

## About the App

This app calculates the optimal ride speed for EV given known consumption vales. Based on
OPTITRIP EV v1.4 spreadsheet created by Stephane Melançon aka DOCTORBASS 2018

![Play store art for this App](https://i.ibb.co/f25dYPQ/functieafbeelding.jpg)

## Where to get the app

For stable release version see Google play store on your device or use link below

[play store link](https://play.google.com/store/apps/details?id=be.hcpl.android.optitripev)

If you like you can become a beta tester. That way you get the app release a bit sooner (and often
more frequently) so that you can evaluate the latest changes and provide feedback on discoverd issues.

[beta test link](https://play.google.com/apps/testing/be.hcpl.android.optitripev)

## Bugs & Upcoming features

* Add a correction factor (%) of the best average speed to use vs the real speed average a trip was. 
  So if there is a difference of value vs what people really had they could just adjust that way.
* allow calculation of consumption numbers from coefficient (just 2 inputs needed)
* create donation option on about screen
* input validation with error message per field

## Version History

### 0.10.0

Technical improvements

- bumped min android version from 21 to 24 (needed for linkify-text lib)
- dark mode colors fixed
- add another indication of consumption (kWh/100km)
- Update to JetPack Compose & drop view binding
- add koin and use MVVM architecture
- increase target SDK to 35

### 0.9

* update to target SDK 34

### 0.8

* update target SDK to 33

### 0.7

* update about info in app
* total time on result view now also includes charge delays

### 0.6

* show next lower and higher values on first view
* fixed speed unit for imperial on first (input) view
* show total ride & charge time on first input view directly

### 0.5

* allow for imperial units from app settings
* fixed possible deadlock on startup of app with invalid input

### 0.4

* remove obfuscation from release app
* first implementation for custom config
* fixed input types (numeric) and added hints in field
* layout bug fixes
* replace "charge" power.....  by Charger  power in kW
* format time in hours and minutes for result
* fixed charge time calculation bug
* fixed charging equivalent speed value in result
* remove calculate button completely and instead monitor changes?

### 0.3

* show detailed output on second tab (continued)
* calculate on resume of input view
* update navigation icons
* add about info (wip)
* fixed small screen support

### 0.2

* provided app icon and update theme colors
* remember last used input values
* show detailed output on second tab (wip)

### 0.1

* initial app release as an internal test with basic functionality

## Dev Notes

Everything is stored locally in preferences (private to app). Results are all stored in Metric, 
so when Imperial is selected the conversion is done both on storing input and display of output.

A default set of speed/consumption values in known in the app to recover to defaults.
This is the information based on the original excel. Note that these are motorcycle
numbers. Nothing stops you from changing the defaults to use for cars or anything else.

Moved repo from bitbucket to github, links in readme changed. 
Bitbucket was https://bitbucket.org/cappelleh/optitrip-ev-android/

App Bundle & release key v2 in use for this project

### References

App icon created with https://icon.kitchen using colors CDDC39 and 4CAF50 resulting in
https://icon.kitchen/i/H4sIAAAAAAAAA1WPOQ7CQAxF72JaCpakgDYFB4AOocizZiQzE2YBRVHujicSBY0tP%2Ft%2F2zO8kYpOcJ7B2I7ciDHXImlOoLTBQhm24GTwDATmrOPUywGjdd72phDBsgVhu0Ah8shGKiWPJ9YIe5tGzchGVE776iPs5VfwFlk1%2Bz%2FRig4VNRJNu2OE3hLbNO265zrgappexUXJDabPoArVL%2B48rGJwqp4cEsePFvBYvsq09jLoAAAA

Composable screen navigation setup
https://medium.com/@santosh_yadav321/bottom-navigation-bar-in-jetpack-compose-5b3c5f2cea9b
