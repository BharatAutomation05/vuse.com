Feature: Login funcationality

@Reg
Scenario Outline: Login Failure
Given user enter url for Sign In
When enter login details "<user_name>" and "<pass>"
Then login status 

Examples:

|user_name|pass|
|retip4157@glumark.com|Asdfg@12345|    
|retip41527@glumark.com|Asdfg@12345|

