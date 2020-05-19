# Coronavirus Guide App

This is the repository for the Quiz App Project required by the Udacity Nanodegree Program.

### App Description

Coronavirus Guide App is more than a quiz App that acts as an awareness guide for the user to not only test his/her knowledge about the COVID-19 Virus but also increase it. The newly identified Virus has triggered a pandemic of pneumonia-causing disease, which caused an intense strain on health systems and financial markets in addition to a global economic crisis. That's why this App not only includes the quiz section, but also more importantly includes a guide for the user on how to know if he/she is affected by the virus, how to protect himself/herself and their loved ones,  how to manage the stress that results from this crisis, and also the user may find one of his/her questions answered in the common questions section.

The App is basically divided into 5 sections. The first section includes the "Test Your Knowledge" section which is the quiz part where the user goes through 9 questions to test the degree of his/her knowledge about the COVID-19 Virus. The questions are divided into three types which are single choice, multiple choice, and free input questions. Each question also displays an explanation for the answer based on information from Bloomberg News Guide. 

The second section of the App is the COVID-19 Symptoms Guide which displays the COVID-19 Symptoms in the form of scrollable slides and the source of information used here is the World Health Organization (WHO) Website. The third section is the prevention Guide Section which displays the prevention steps needed to be taken by the user in order to protect himself/herself and the loved ones from the danger of the COVID-19 Virus. The information used in this prevention guide is from the World Health Organization (WHO) Website. 

The fourth section is the "How to Manage the Stress" section where it gives the user advice about how to handle the stress that has resulted from this crisis and how to deal with it in a healthy way based on advice from the the World Health Organization (WHO) Website. Finally, the fifth section is the "Common Questions" section which includes the most common questions that people have had about the Virus and their answers where the user can view these questions and also share the question and answer that he/she feels will be useful to friends and family through sending it via email or Social Media Networking Apps.

The content in this App is based on the information from the World Health Organization (WHO) Website and Bloomberg News Guide Website.

### App Design

The App has multiple screens and features which are as follows:

### The Splash Screen

When the user opens the Coronavirus Guide App, it starts with a Splash Screen which is the first startup screen that appears when 
the Coronavirus Guide App is opened. It is a simple constant screen that is displayed for a fixed amount of time basically 3000 seconds which is used to display the Coronavirus Guide App Logo in an animated way as shown below.

### The Splash Screen Screenshot

<img src="https://media.giphy.com/media/U7c2TesYruxCiw11qK/giphy.gif" width="300">

### The Introduction Wizard (Why the user should use the Coronavirus Guide App)

After the Splash Screen, the user is redirected to an introduction wizard that intrigues the user about the reason why he/she should use the Coronavirus Guide App in the form of
three intriguing questions with animations associated. If the user clicked on the “I want to know more” Button, the user will be then redirected to a SignUp Screen where he/she can sign up 
either by Google or Email as Sign In Providers. 

### The Introduction Wizard Screenshot

<img src="https://media.giphy.com/media/WP2hy5sLXhcthOUzdq/giphy.gif" width="300">


### The Coronavirus Guide App Sign Up Process

The Coronavirus Guide App's Sign Up Process is built using the FirebaseUI Authentication that provides the user with two Sign-In Providers’ Options which are Google and Email. 

### The Coronavirus Guide App Sign Up Screenshot

<img src="https://media.giphy.com/media/h7WFs03E84e8Zr46xN/giphy.gif" width="300">

### Signing Up using Google

If the user chooses to sign up with Google Option, the authentication process will ask the user to  sign up with his/her Google Account and enter his/her google email and password. Also, there are additional useful features in case the user forgot the password, want to sign up with another Goggle Account or create a new Google Account.

### Google Sign Up Screenshots

<img src="https://i.imgur.com/KuWLAYN.png" width="300"> <img src="https://i.imgur.com/XplZmus.png" width="300"> <img src="https://i.imgur.com/3nRB2gJ.png" width="300"> 

### Signing Up using Email

However, if the user chooses to sign up with the Email Option and the user was a new one (hasn’t signed before), the authentication process will ask the user to enter an email, first and last name, and a password. Then the user will be redirected to the main screen where he/she can test his/her knowledge and start a new quiz or navigate to other sections in the App. 

### Email Sign Up Screenshots

<img src="https://i.imgur.com/3nP7WS3.png" width="300"> <img src="https://i.imgur.com/LExuRZY.png" width="300"> 
<img src="https://i.imgur.com/3nRB2gJ.png" width="300"> 

Once the user signs up with either Google or Email option, the user data is extracted from the Firebase Auth instance and a user document is created and stored in Firebase Firestore database so that we can display the user information in different places in the App using this document and also so that when the user wants to update his/her profile, the updated data will be merged with the currently existing user data stored in this document and the new data will be the one displayed. Each user document is named after the unique User Id and stored inside a collection called “Users” as displayed in the screenshot below. So there is a collection called Users and inside this collection there are documents, one for each user named after the User Unique ID.
 
### FireStore Database Users Collection Screenshot
 
  <img src="https://i.imgur.com/grJ8YZ3.png"> 
  
### Section One: Testing The User Knowledge Process
  
Now the user has signed up successfully and logged in to the MainActivity. The MainActivity consists of a Navigation Drawer where the user can navigate between different sections of the App including the "Test Your Knowledge" or quiz section, the COVID-19 Symptoms Guide, the COVID-19 Prevention Guide, the COVID-19 Manage Stress Guide, the COVID-19 Common Questions section, and the User Profile. The first and main screen that appears to the user when he/she signs in is the "Test Your Knowledge" Screen as shown below. When the user clicks on the Start The Quiz button, he/she is redirected to the questions screen where the first question is displayed along with a score counter, a current question counter, and an explanation button. When the user chooses an answer and clicks on the "Confirm" Button, if the answer is correct, a message is displayed showing the user that the answer is correct. However, if the answer is incorrect, two messages are displayed one showing the user that the answer is incorrect and the other is displaying the correct answer. Additionally, the user can click on the "Explanation" Button to read more about the question and the answer. If the user didn't choose any answer, a message will be displayed that asks him/her to choose an answer and he/she won't be able to move to the next question unless he/she has chosen an answer. After the user has finished answering all the questions, he/she will be redirected to the result screen where he/she will earn a Coronavirus Super Fighter Badge if the correct answers were more than the incorrect ones. But if the user's incorrect answers were more than the correct ones, he/she will be advised to read more about the COVID-19 Virus. 

### Quiz Process Screenshots
  
<img src="https://media.giphy.com/media/KZvhiVe6dFgFGuDpuJ/giphy.gif" width="300"> <img src="https://media.giphy.com/media/St8Yb79pE659FSDQAm/giphy.gif" width="300"> <img src="https://media.giphy.com/media/elgMRzqjh7x9MzZw2K/giphy.gif" width="300"> 

### Quiz Results Screenshots

<img src="https://media.giphy.com/media/ZBQCMF7uMcUuguS7zg/giphy.gif" width="300"> <img src="https://media.giphy.com/media/Y0z271vB3hiEeXfi2Y/giphy.gif" width="300"> 

### FireStore Database Quiz Collection 
The questions of the quiz are stored in the Firestore database inside a collection called "Quiz" collection and each document in this collection represents a question. In order to differentiate between the different types of questions, I've give each question a category that can be used in the code to determine which fragment to display based on the question category type. 

### FireStore Database Quiz Collection Screenshot

 <img src="https://i.imgur.com/U1leYyr.png"> 

### Section Two: COVID-19 Symptoms Guide

The user can know more about the COVID-19 Symptoms through going to the Symptoms Guide Section where the Symptoms of the Coronavirus are displayed in the form of scrollable slides and the source of this content is the World Health Organization(WHO) Website. 

### Section Two: COVID-19 Symptoms Guide Screenshot

<img src="https://i.imgur.com/fuLTFD6.png" width="300"> <img src="https://i.imgur.com/pWhtNpm.png" width="300"> <img src="https://i.imgur.com/W5NNDhW.png" width="300">  <img src="https://i.imgur.com/elRhJdt.png" width="300">  <img src="https://i.imgur.com/wqRp7Q2.png" width="300">  <img src="https://i.imgur.com/iP0tl8a.png" width="300">

I've also taken into consideration configuration changes like rotating the device to the landscape mode and have designed the content to fit both configurations as shown below.

 <img src="https://i.imgur.com/JV1Ebkb.png" width="500"> <img src="https://i.imgur.com/pWhtNpm.png" width="300">
 
 ### Section Three: COVID-19 Prevention Guide 
 
Here the user can know more about the necessary steps that need to be taken to protect himself/herself and their loved ones from the danger of the COVID-19 Virus. In this Prevention Guide Section, the steps are displayed in the form of scrollable slides and the source of this content is again the World Health Organization(WHO) Website. I've also taken into consideration configuration changes like rotating the device to the landscape mode and have designed the content to fit both configurations.

 ### Section Three: COVID-19 Prevention Guide Screenshot

<img src="https://i.imgur.com/FbFtBWJ.png" width="300"> <img src="https://i.imgur.com/XuF6PvQ.png" width="300"> <img src="https://i.imgur.com/pwTCgTn.png" width="300">  <img src="https://i.imgur.com/y29wvTq.png" width="300">  <img src="https://i.imgur.com/xUKeu0N.png" width="300">  <img src="https://i.imgur.com/iss4Ae6.png" width="300"> <img src="https://i.imgur.com/WlQliNf.png" width="300"> <img src="https://i.imgur.com/nFFSf89.png" width="300"> 

 
  ### Section Four: COVID-19 Manage Stress Guide 
 
Here the user can know more about how to manage the stress that results from this crisis and how to deal with it in a healthy way based on advice from from the World Health Organization(WHO) Website. In this Manage Stress Guide Section, the steps are displayed in the form of scrollable slides as shown below. 

  ### Section Four: COVID-19 Manage Stress Guide Screenshot

<img src="https://i.imgur.com/DGl9vXN.png" width="300"> <img src="https://i.imgur.com/pHw0AKo.png" width="300"> <img src="https://i.imgur.com/J84Bgav.png" width="300">  <img src="https://i.imgur.com/k4ZYqy7.png" width="300">  <img src="https://i.imgur.com/i3IEsx5.png" width="300">  <img src="https://i.imgur.com/SEWvq7K.png" width="300">

 ### Section Five: COVID-19 Common Questions
 
If the user wants to view the common questions that the people have asked regarding the COVID-19 Virus , he/she should click on the hamburger icon at the top in the toolbar and the navigation drawer will open, then the user can select common questions option and all the common questions will be displayed in the form of cards where the user can expand the card to view the answer and share the question.
  
<img src="https://i.imgur.com/AzZ05Pf.png" width="300"> <img src="https://i.imgur.com/38pMorW.png" width="300"> 
 
### Share Option
  
The user has the option to share the question and the answer that he/she feels might be helpful with friends and family by clicking on the share button, the Android Sharesheet will open with a wide variety of different apps to share the question and answer on and the user can choose from these Apps that range from Email Apps to Social Networking Apps. The share message will be as shown below a text message including the question and a full explanation of it.
  
### Share option Screenshot
  
   <img src="https://i.imgur.com/Sqd5ZqK.png" width="300">  <img src="https://i.imgur.com/ruB2vGw.png" width="300"> 
   
 ### FireStore Database Common Questions Collection 

The common questions that are displayed are stored in a collection in Firestore Database named "Common Questions" and inside this collection each document represents a question as shown below and they are displayed ordered by their index which is a field in each document representing the question number so question one has an index of 1 and question two has an index of 2, etc. So they are displayed in an ascending order from question one till question twenty one or the last question.

 ### FireStore Database Common Questions Collection Screenshot
 
  <img src="https://i.imgur.com/aDnbqje.png"> 
  
###  Section Six: User Profile
  
Every user has a profile where the name and the email are displayed along with the profile photo if the user has signed up with the Google option. However, if the user has signed up with the Email option then the displayed photo in the user profile will be the default avatar. Also, every user can have a small self introduction about himself/herself and if the user is new meaning has signed up for the first time, the self introduction displayed will be the default one as shown below. Every user can choose to update the profile by clicking on the update button and now he/she can choose a profile photo, a display name, and a self introduction that he/she likes. The new updates will be saved in Firestore database and displayed in the user profile in addition to the navigation view header as shown below.

### User Profile Screenshots
  
<img src="https://i.imgur.com/gl9kkZf.png" width="300"> <img src="https://i.imgur.com/qT0M7UF.png" width="300">  <img src="https://i.imgur.com/lpB7HAg.png" width="300"> <img src="https://i.imgur.com/LwBZuql.png" width="300"> <img src="https://i.imgur.com/EwLDtfy.png" width="300">

### Creative Assets Copyrights
All the drawable resources used in this project are all MIT Licensed which are free of Copyrights and can be used without attributions.

### Dowload the App
To use this repository, fork/clone it, or download a zip using the green "Clone or download" button at the top of the file list.  

### License

Copyright 2020 Doha Nabil Saad Kash

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
