# DentistVisitApplication

Java test excercise for CGI

Description of my task

0) Stage 0
  * I used IntelliJ with Java 13 in project setting and maven
  * Was very straightfoward and didn't take long at all. Initially application didn't run once I imported project to 
  IntelliJ but I managed to fix it quickly by making necessary changed in pom file
  
1) Stage 1
  * Wasn't difficult but I had no knowledge on Thymeleaf so read about topic and looked into application to understand it.
  * Took only few hours, because of studying.
  * Main page opens with options to register new appointment or view all registered appointments.
  * Solution for registering an appointment was that once I open register form page loads data from DB to dislay all registered dentist names in input options. If inputs are all
  correct then data is saved to DB
  
2) Stage 2
  * This was very straightfoward and easy, didn't have any difficulties
  * Solution was to load all registered appointments from DB and display them in table and error messages are displayed in case of invalid input
  
3) Stage 3
  * This was the most difficult part and took majority of time, specificly implementing edit. 
  * Didn't have have much difficulties with backend Java solution, only problem where I got little bit stuck was that input data and loaded data from DB was getting 
  lost once I posted something and received error message (input check). 
  So I found a solution that fixed it from stackoverflow: https://stackoverflow.com/questions/15903238/passing-bindingresult-through-redirectionattributes.
  * I had mostly difficulties with frontend since I mainly have experience with backend and it's been a while since I did something in frontend with html, css, ... etc.
  What took a lot of time was the way I wanted to implement editing since I had some ideas but because I had bit trouble with them and it took too much time I reverted those
  changed and decided to implement easiest solution which was to open data to be edited in a different page as a form.
  * This stage took around less that 2 working days because I got stuck few times with my implementation ideas and had to read a lot on the internet.
  * Solution was that all registered appointments are displayed in table where used can edit or delete them. Delete is simple just need to click a button. When clicking edit
  a new page opens where it loads data that user wants to edit and also loads all registered dentists for used to choose in case he/she wants to change dentist.
  If used tries to register/update an appointment to dentist that already has that specific time booked then error message is displayd telling user that time is already booked
  (unfortunately I didn't have time to implement overlapping time, visit time is booked only at specific time like 01/01/2021/12:00)
  
5) Stage 4
  * Took only a little time to implement search but I got the solution idea from https://www.w3schools.com/howto/howto_js_filter_table.asp
  * Solution was to add search bar where user can type dentist name and table rows are filtered to match dentist name that user wants to search
