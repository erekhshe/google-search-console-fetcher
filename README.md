Google Search Console API Data Fetcher
======================================

Overview
--------

The Google Search Console API Data Fetcher is a tool designed to automate the process of fetching data from the Google Search Console API and storing it into a MySQL database. This project uses Java and Maven, making it easy to build and deploy. It's also compatible with Amazon Lambda, allowing you to run it as a serverless application.

### Usage and Benefits

*   **Automate Data Fetching:** Automates the process of fetching search analytics data from Google Search Console.
*   **Serverless Deployment:** Can be deployed on Amazon Lambda, making it cost-effective and scalable.
*   **Customizable:** Fully documented code to help you customize or convert it to other programming languages.
*   **Comprehensive:** Includes all required files, such as `pom.xml` for Maven configuration and a test unit for local testing, ensuring a smooth setup and deployment process.
*   **Step-by-Step Guides:** Easy instructions for generating API credentials, compiling the project, and deploying it on Amazon Lambda.

Table of Contents
-----------------

1.  [Overview](#overview)
2.  [Setup Guide](#setup-guide)
    *   [Generate Google Search Console API Credentials](#generate-google-search-console-api-credentials)
    *   [Compile the Project as a Single Jar File](#compile-the-project-as-a-single-jar-file)
    *   [Create a Lambda Project on Amazon](#create-a-lambda-project-on-amazon)
    *   [Upload the Jar File to Lambda](#upload-the-jar-file-to-lambda)
    *   [Test and Increase Timeout](#test-and-increase-timeout)
3.  [Code Explanation](#code-explanation)
4.  [Crucial Variables to Replace](#crucial-variables-to-replace)
5.  [Running Tests Locally](#running-tests-locally)
6.  [Customizing the Code](#customizing-the-code)
7.  [Troubleshooting](#troubleshooting)

Setup Guide
-----------
<head>
  <link rel="stylesheet" href="src/help/css/styles.css">
</head>

### Generate Google Search Console API Credentials
1.  Go to the Google Developers Console. And click on “CREATE PROJECT“ in the top menu.
    <a href="#" onclick="openModal(this)">
      <img src="src/help/images/generate-json-file/1.png" alt="CREATE PROJECT" class="thumbnail">
    </a>

<div id="modal" class="modal">
  <span class="close-button" onclick="closeModal()">&times;</span>
  <img class="modal-content" id="modal-image">
</div>
    

2. Specify the name of the desired project and the address of your project file.

   <img src="src/help/images/generate-json-file/2.png" alt="Specify the name" class="thumbnail">

3. You can change the project ID. (Note that you can only change the ID at this stage.)

   <img src="src/help/images/generate-json-file/3.png" alt="change the project ID" class="thumbnail">

4. After changing the ID, click on the “CREATE” button to create your project.

   <img src="src/help/images/generate-json-file/4.png" alt="click on the CREATE button" class="thumbnail">

5. After registering your project, a successful registration notification will be displayed for you. Click on “Select Project“ to enter your project.

   <img src="src/help/images/generate-json-file/5.png" alt="Select Project" class="thumbnail">

6. To set up the API, click on “Go to API's overview”

   <img src="src/help/images/generate-json-file/6.png" alt="Go to API's overview" class="thumbnail">

7. Click on one of the following items, “Library”.

   <img src="src/help/images/generate-json-file/7.png" alt="Library" class="thumbnail">

8.	Search for the phrase "Google Search Console API" in the search box

  	<img src="src/help/images/generate-json-file/8.png" alt="Google Search Console API" class="thumbnail">

9. Click on the search result.

   <img src="src/help/images/generate-json-file/9.png" alt="search result" class="thumbnail">

10. Click on the “Enable“ button to activate the API.

    <img src="src/help/images/generate-json-file/10.png" alt="Enable button" class="thumbnail">

11. Go to the Credentials page. Click on the “Create credentials“ option in the top menu and select “OAuth client ID”.

    <img src="src/help/images/generate-json-file/11.png" alt="Credentials page" class="thumbnail">

12. Select the “Web application” option

    <img src="src/help/images/generate-json-file/12.png" alt="Web application" class="thumbnail">

13. After selecting Web application, some fields will be displayed for you.Fill in the fields according to your needs, then click on the “CREATE“ button

    <img src="src/help/images/generate-json-file/13.png" alt="Fill in the fields according to your needs" class="thumbnail">

14. Click on the “DOWNLOAD JSON”.

    <img src="src/help/images/generate-json-file/14.png" alt="DOWNLOAD JSON" class="thumbnail">

15. After creating the JSON file in the Google Search Console API, the generated file needs to be uploaded to an environment that can provide a direct link.
**In the next steps, you need to upload this generated JSON file to Lambda**


### Compile the Project as a Single Jar File

1.  Clone the repository:
    
        git clone https://github.com/erekhshe/gscFetcher.git
    
2.  Navigate to the project directory:
    
        cd gscFetcher
    
3.  Use Maven to compile the project:
    
        mvn clean install
    
4.  The compiled Jar file will be located in the `target` directory.


**To compile in the Windows environment, you can use free IDEs such as Eclipse**.

*Follow the steps below*

1.	Click on File > New > Maven Project.

    ![Maven Project](src/images/compile-the-project/1.png)

2. Check the "Create a simple project" box and click Next.

    ![Create a simple project](src/images/compile-the-project/2.png)
   

3. Check the "Create a simple project" box and click Next.

    ![Specify the name](src/images/compile-the-project/3.png)

4. Download the project from GitHub and replace the internal files in the eclipse-workspace.

    ![replace the internal files in the eclipse-workspace](src/images/compile-the-project/4.png)


5. Refresh the project in the software once to make the changes appear (right-click and select Refresh).

    ![Refresh the project](src/images/compile-the-project/5.png)

6. You must replace the **4** important variables in the code based on the specifications of the website Url, Database , Table Name, and your Credential URL.

1.  **Website URL:** Replace `"https://www.example.com"` with your own website URL in the `MyGSCFetcherFunction` method.
    
        String websiteUrl = "https://www.example.com";
    
2.  **Database Connection String:** Replace `"1.1.1.1:3306/database_name?user=user&password=password"` with your own MySQL database connection string in the `saveToDatabase` method.
    
        String connectionString = "1.1.1.1:3306/database_name?user=user&password=password";

3.  **Table Name:** The name of the table in the database where you want to store the information
    
        String tableName = "your_table_name";

4.  **Credential URL:** Replace `"https://example.s3.amazonaws.com/gsc-credential.json"` with the URL to your uploaded JSON credential file in the `buildWebmasters` method.
    
        String credentialUrl = "https://example.s3.amazonaws.com/gsc-credential.json";


    ![database specifications](src/images/compile-the-project/6.png)

7. After making the changes, right-click on the project name and select Run As > Maven install.

    ![Run As > Maven install](src/images/compile-the-project/7.png)

8. The console will display a report indicating the successful completion of the operation.

    ![successful completion of the operation](src/images/compile-the-project/8.png)

9. Navigate to the project directory and you will find the generated zip file (.jar) in the target folder.

    ![zip file (.jar)](src/images/compile-the-project/9.png)


### Create a Lambda Project on Amazon

1.	Log into your account and then access the console

    ![Log into your account](src/images/guid-for-project-in-lambda/1.png)

2. Find the Lambda Service and enter to its environment

    ![Lambda Service](src/images/guid-for-project-in-lambda/2.png)

3. In the Lambda dashboard, click on "Create function button"

    ![Create function button](src/images/guid-for-project-in-lambda/3.png)

4. Choose a name for your function and select the Runtime based (for our project Java 8 on Amazon Linux 2)

    ![Choose a name for your function](src/images/guid-for-project-in-lambda/4.png)

5. To finalize this new function creation, click the "Create function" button.

    ![Create function](src/images/guid-for-project-in-lambda/5.png)

6. Your project will be created.

    ![Create function](src/images/guid-for-project-in-lambda/6.png)

7. Replace the Code source with your own .jar file (upload it from your device).

    ![project will be created](src/images/guid-for-project-in-lambda/7.png)
 
8. For the correct implementation of the project, it is necessary to enter the main function name in the project in this field

    ![enter the main function name](src/images/guid-for-project-in-lambda/8.png)
 
9. In the Handler field, delete the default name and enter the desired function name

    ![Handler field](src/images/guid-for-project-in-lambda/9.png)
 
10. You can test your project to ensure it's working properly (consider next 3 steps for testing purpose).

11. Go to the Test tab, choose an arbitrary name for the event and save it. So that you can hit the Test b/utton whenever you need to test your project

    ![Go to the Test tab](src/images/guid-for-project-in-lambda/10.png)

12. If your project is healthy and you've entered the function name correctly, you will see a message indicating successful execution.

    ![successful](src/images/guid-for-project-in-lambda/11.png)

    -**___Log output___** : 

     ![Log output](src/images/guid-for-project-in-lambda/12.png)

13. If there is an issue with the project you've defined, you will encounter an error. In this case, the error will be displayed to you. If you encounter a timeout error, you should perform the next two steps that exist in this guide to set the timeout.

    ![timeout error](src/images/guid-for-project-in-lambda/13.png)

14. To configure the settings for your function, go to the Configuration tab.

    ![configure the settings](src/images/guid-for-project-in-lambda/14.png)

15. In this tab, you can customize various settings for your Lambda function, such as memory allocation, timeout, environment variables, execution role, and more.

    ![configure the settings](src/images/guid-for-project-in-lambda/15.png)

16. To save on unnecessary resource consumption, automate, and improve the performance of the project, it is necessary to add a trigger. Click the "Add Trigger" button.

    ![Add Trigger](src/images/guid-for-project-in-lambda/16.png)

17. Select the "EventBridge (CloudWatch Events)" option.

    ![EventBridge (CloudWatch Events)](src/images/guid-for-project-in-lambda/17.png)

18. In the "Existing rules" section, select the "loop_daily_run" option. Click the "Add" button to save the settings.

    ![Existing rules](src/images/guid-for-project-in-lambda/18.png)

19. Finally, your trigger has been created and is displayed in this section.

    ![Existing rules](src/images/guid-for-project-in-lambda/19.png)


Code Explanation
----------------

### Main Class: GSCFetcher

The `GSCFetcher` class implements `RequestHandler` to handle AWS Lambda requests. The main method `MyGSCFetcherFunction` fetches data from Google Search Console for the past five days and stores it in a MySQL database.

*   `handleRequest`: The entry point for AWS Lambda.
*   `MyGSCFetcherFunction`: Fetches data for the past five days.
*   `saveToDatabase`: Saves the fetched data to a MySQL database.
*   `buildWebmasters`: Builds the Webmasters client using the provided credentials.


    

Running Tests Locally
---------------------

To test the code locally, a test unit is provided. This test unit allows you to run and verify the functionality of the code before deploying it to AWS Lambda. The test unit and the `pom.xml` file can be downloaded from the [GitHub repository](https://github.com/yourusername/repositoryname).

### Test Unit

The test unit provided allows you to run and verify the functionality of the code before deploying it to AWS Lambda.

### Maven Configuration (`pom.xml`)

The `pom.xml` file includes the required plugins for compiling the project as a single JAR file and making it compatible with Amazon Lambda.

Customizing the Code
--------------------

The provided code fetches data for the past five days as a sample. However, you can customize the date range and other functionalities as per your requirements. For instance, you can modify the `MyGSCFetcherFunction` method to fetch data for different date ranges or add more dimensions and filters to the query.

Troubleshooting
---------------


*   **Invalid Credentials Error:** Ensure your Google API credentials are correctly set up and the JSON file is correctly placed.
*   **Timeout Issues:** Increase the timeout setting in the Lambda function configuration.
*   **Dependency Issues:** Ensure all dependencies are correctly specified in the `pom.xml` file and Maven has downloaded them.

For any further assistance, please refer to the official documentation or open an issue on the GitHub repository.

