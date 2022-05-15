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

## Coming features

* OK - There are INPUT value and OUTPUT values, very simple
* OK - have dark and light theme in app (test, should be provided by theme)
* OK - One of the input that could be on a seperate page is the efficiency ( consumption in Wh vs speed)
* I like the fact that there are colours approaching green above and below the optimum speed (see spreadsheet)
* I also thought about a correction factor based on a % of the best average speed to use vs the real
  speed average that the trip was so if there is a difference of value vs what people really had they
  could just adjust the correction factor the value for the next trips for the best average speed to use.
* allow calculation of consumption numbers from coefficient (just 2 inputs needed)

## TODO

* release iOS version of this app
* create donation option on about screen
* input validation with error message per field

## Version History

### 0.6

* fixed speed unit for imperial on first (input) view

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

## References

App icon created with https://icon.kitchen using colors CDDC39 and 4CAF50 resulting in
https://icon.kitchen/i/H4sIAAAAAAAAA1WPOQ7CQAxF72JaCpakgDYFB4AOocizZiQzE2YBRVHujicSBY0tP%2Ft%2F2zO8kYpOcJ7B2I7ciDHXImlOoLTBQhm24GTwDATmrOPUywGjdd72phDBsgVhu0Ah8shGKiWPJ9YIe5tGzchGVE776iPs5VfwFlk1%2Bz%2FRig4VNRJNu2OE3hLbNO265zrgappexUXJDabPoArVL%2B48rGJwqp4cEsePFvBYvsq09jLoAAAA

### What is this repository for? ###

* Quick summary
* Version
* [Learn Markdown](https://bitbucket.org/tutorials/markdowndemo)

### How do I get set up? ###

* Summary of set up
* Configuration
* Dependencies
* Database configuration
* How to run tests
* Deployment instructions

### Contribution guidelines ###

* Writing tests
* Code review
* Other guidelines

### Who do I talk to? ###

* Repo owner or admin
* Other community or team contact