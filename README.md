# scats
Selenium Cucumber automated testing system

#Common functions
```
1. Base function
[o|O]pen the home page
[r|R]eload the page
[c|C]lose the browser
[c|C]lear session
[w|W]ait for (\\d+) seconds
[s|S]croll down
[s|S]croll up
[n|N]avigate to "url"
[m|M]ove forward one page
[m|M]ove backward one page
[s|S]witch to "framename" frame
[s|S]witch back to parent frame
[s|S]switch back to main frame
upload on the "element" file "filePath"

2. Alert
[a|A]lert popup should be message "msg" then accept alert
[a|A]lert popup should be message "msg" then dismiss alert
[a|A]lert popup should be message "msg"
[c|C]lick to accept alert
[c|C]lick to dismiss alert

3. Main Action
[p|P]ress down arrow key (\\d+) time
[p|P]ress up arrow key (\\d+) time
[p|P]ress tab key (\\d+) times
[d|D]ouble-click on the "element"
[r|R]ight click on the "element"
[c|C]lick on the "element"
[c|C]lick on the "element" and switch to the first tab
[c|C]lick on the "element" and switch to the second tab
[h|H]over the mouse on the "element"
[f|F]ill value "text" to the field "element"
[f|F]ill value "text" to the field "element" and store value into variable "varName"
[u|U]ncheck on the "element"
[c|C]heck on the "element"
[s|S]elect value "text" from the drop down "element"
[s|S]elect text "text" from the drop down "element"
[s|S]elect the index (\\d+) from the drop down "element"

4. Verification
[t|T]he "element" should be disabled
[t|T]he "element" should be enabled
[t|T]he "element" checkbox should be unchecked
[t|T]he "element" checkbox should be checked
[t|T]he text of element "element" should be "text"
[t|T]he text of element "element" should not be "text"
[t|T]he text of element "element" should contain "text"
[t|T]he text of element"element" should not contain "text"
[t|T]he text "text" should be shown
[t|T]he text "text" should not be shown
[t|T]he element "element" should be shown
[t|T]he element "element" should not be shown
[p|P]age should be redirected to the "url" (?: page| screen)?
```
#How to run
1. Open command line and go to folder contains pom file
2. Run command: mvn clean install

#Reports
After running test, reports are exported in folder target/site by default

#Feature template
```
Feature: teracyautotestdemo
Background:
	When navigate to "demo.registurl" 
	When move backward one page
	When move forward one page
	When reload the page
	
Scenario: scenario1
	#textbox
	When fill value "{!auto,s46}" to the field "demo.firstname"
	When fill value "{!auto,S10,!test}" to the field "demo.firstname" and store value into variable "varfirstname"
	When fill value "{!auto,N46}" to the field "demo.lastname"
	When fill value "{!auto,n10,!test}" to the field "demo.lastname" and store value into variable "varfirstname"
	When fill value "password" to the field "demo.password"
	When fill value "password" to the field "demo.confirmpassword" and store value into variable "varfirstname"
	When fill value "{N10}" to the field "demo.phone"
	When fill value "{!auto,s3,N8}" to the field "demo.username"
	When fill value "{!auto,s5,!@,s4,!.,s4}" to the field "demo.email"
	
	#radio button
	When check on the "demo.single"
	When check on the "demo.divorced"
	
	#checkbox
	When click on the "demo.cricket"
	When check on the "demo.reading"
	When uncheck on the "demo.cricket"
	
	#dropdownbox
	When select text "Albania" from the drop down "demo.country"
	When select value "Bahrain" from the drop down "demo.country"
	When select the index 0 from the drop down "demo.country"
	
	#upload file
	When upload on the "demo.file" file "/src/test/resources/DataTest/jpg.jpg"
	
	#button
	When click on the "demo.submit"
```
# Properties file template
```
demo.url=http://demoqa.com
demo.registurl=http://demoqa.com/registration/

#registerform
demo.firstname=id=name_3_firstname
demo.lastname=id=name_3_lastname
demo.single=xpath=//*[@value='single']
demo.married=xpath=//*[@value='married']
demo.divorced=xpath=//*[@value='divorced']
demo.dance=xpath=//*[@value='dance']
demo.reading=xpath=//*[@value='reading']
demo.cricket=xpath=//*[@value='cricket ']
demo.country=id=dropdown_7
demo.email=id=email_1
demo.phone=id=phone_9
demo.username=name=username
demo.description=name=description
demo.password=id=password_2
demo.confirmpassword=id=confirm_password_password_2
demo.file=id=profile_pic_10
demo.submit=name=pie_submit
```
# run on mobile (run test on MacOSX)
1. Install appium: 
```
npm install -g appium
```
2. To run test on android, install adb
Install homebrew
```
ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
```
Install adb
```
brew cask install android-platform-tools
```
Start using adb
```
adb devices
```
3. Connect mobile device with computer by turning on "USB debugging" mode
```
Go to Settings -> Developer Options -> Check "USB debugging"
```
4. Update serenity.properties
```
appium.hub = http://127.0.0.1:4723/wd/hub
appium.platformName = Android
appium.platformVersion = 5.1
appium.deviceName = 0217da38
appium.browserName = Chrome
```
5. Open command line, type
```
appium
```
6. Run test by maven command or eclipse



