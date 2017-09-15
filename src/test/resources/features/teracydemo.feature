Feature: teracyautotestdemo
#Background:
#	#textbox
#	When navigate to "demo.registurl" 
#	When move backward one page
#	When move forward one page
#	When reload the page
#	
#Scenario: scenario1
#	#textbox
#	When fill value "{!auto,s46}" to the field "demo.firstname"
#	When fill value "{!auto,S10,!test}" to the field "demo.firstname" and store value into variable "varfirstname"
#	When fill value "{!auto,N46}" to the field "demo.lastname"
#	When fill value "{!auto,n10,!test}" to the field "demo.lastname" and store value into variable "varfirstname"
#	When fill value "password" to the field "demo.password"
#	When fill value "password" to the field "demo.confirmpassword" and store value into variable "varfirstname"
#	When fill value "{N10}" to the field "demo.phone"
#	When fill value "{!auto,s3,N8}" to the field "demo.username"
#	When fill value "{!auto,s5,!@,s4,!.,s4}" to the field "demo.email"
#	
#	#radio button
#	When check on the "demo.single"
#	When check on the "demo.divorced"
#	
#	#checkbox
#	When click on the "demo.cricket"
#	When check on the "demo.reading"
#	When uncheck on the "demo.cricket"
#	
#	#dropdownbox
#	When select text "Albania" from the drop down "demo.country"
#	When select value "Bahrain" from the drop down "demo.country"
#	When select the index 0 from the drop down "demo.country"
#	
#	#upload file
#	When upload on the "demo.file" file "/src/test/resources/DataTest/jpg.jpg"
#	
#	#button
#	When click on the "demo.submit"
	
Scenario: scenario2
	When register demo step
