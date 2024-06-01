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

### Generate Google Search Console API Credentials
1.  Go to the [Google Developers Console](https://console.cloud.google.com/cloud-resource-manager).And click on “CREATE PROJECT“ in the top menu.
   (If you are not logged in to your Google account, you will need to log in first before you can generate the Google Search Console API credentials. This is because the Google Search Console API requires authentication with a valid Google account in order to access the data and perform actions.)

    <a href="https://uploadkon.ir/uploads/458e30_241.png" target="_blank">
       <img src="https://uploadkon.ir/uploads/thumbs/458e30_241.png" alt="CREATE PROJECT" class="thumbnail">
    </a> 

2. Specify the name of the desired project and the address of your project file. click on the “CREATE” button to create your project.

   <a href="https://uploadkon.ir/uploads/dcac01_242.png" target="_blank">
       <img src="https://uploadkon.ir/uploads/thumbs/dcac01_242.png" alt="Specify the name" class="thumbnail">
   </a> 

4. After registering your project, a successful registration notification will be displayed for you. Select your project to enter its environment

   <a href="https://uploadkon.ir/uploads/111230_245.png" target="_blank">
       <img src="https://uploadkon.ir/uploads/thumbs/111230_245.png" alt="Select Project" class="thumbnail">
   </a>

5. To set up the API, click on “Go to APIs overview”

   <a href="https://uploadkon.ir/uploads/111230_246.png" target="_blank">
       <img src="https://uploadkon.ir/uploads/thumbs/111230_246.png" alt="Go to API's overview" class="thumbnail">
   </a>

6. Click on  the “Library”.

   <a href="https://uploadkon.ir/uploads/111230_247.png" target="_blank">
       <img src="https://uploadkon.ir/uploads/thumbs/111230_247.png" alt="Library" class="thumbnail">
   </a>

7.	Search for the phrase "Google Search Console API" in the search box

      <a href="https://uploadkon.ir/uploads/111230_248.png" target="_blank">
          <img src="https://uploadkon.ir/uploads/thumbs/111230_248.png" alt="Google Search Console API" class="thumbnail">
      </a>

8. On the search result click on the google search console

   <a href="https://uploadkon.ir/uploads/a16c30_249.png" target="_blank">
       <img src="https://uploadkon.ir/uploads/thumbs/a16c30_249.png" alt="search result" class="thumbnail">
   </a>

9. Click on the “Enable“ button to activate the API.

    <a href="https://uploadkon.ir/uploads/a16c30_2410.png" target="_blank">
       <img src="https://uploadkon.ir/uploads/thumbs/a16c30_2410.png" alt="Enable button" class="thumbnail">
    </a>

10. Go to the Credentials page. Click on the “Create credentials“ option in the top menu and select “OAuth client ID”.

    <a href="https://uploadkon.ir/uploads/111230_2411.png" target="_blank">
       <img src="https://uploadkon.ir/uploads/thumbs/111230_2411.png" alt="Credentials page" class="thumbnail">
    </a>

11. Select the “Web application” option

    <a href="https://uploadkon.ir/uploads/9e9930_2412.png" target="_blank">
       <img src="https://uploadkon.ir/uploads/thumbs/9e9930_2412.png" alt="Web application" class="thumbnail">
    </a>

12. After selecting Web application, some fields will be displayed for you. Fill in the fields according to your needs, then click on the “CREATE“ button

    <a href="https://uploadkon.ir/uploads/9e9930_2413.png" target="_blank">
       <img src="https://uploadkon.ir/uploads/thumbs/9e9930_2413.png" alt="Fill in the fields according to your needs" class="thumbnail">
    </a>

13. Click on the “DOWNLOAD JSON”.

    <a href="https://uploadkon.ir/uploads/111230_2414.png" target="_blank">
       <img src="https://uploadkon.ir/uploads/thumbs/111230_2414.png" alt="DOWNLOAD JSON" class="thumbnail">
    </a>


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

      <a href="https://uploadkon.ir/uploads/645c30_241.png" target="_blank">
          <img src="https://uploadkon.ir/uploads/thumbs/645c30_241.png" alt="Maven Project" class="thumbnail">
      </a>

2. Check the "Create a simple project" box and click Next.

   <a href="https://uploadkon.ir/uploads/625930_242.png" target="_blank">
       <img src="https://uploadkon.ir/uploads/thumbs/625930_242.png" alt="Create a simple project" class="thumbnail">
   </a>

3. Fill out the first two fields as shown in the image, then click Finish.

   <a href="https://uploadkon.ir/uploads/601930_243.png" target="_blank">
       <img src="https://uploadkon.ir/uploads/thumbs/601930_243.png" alt="Specify the name" class="thumbnail">
   </a>

4. Download the project from GitHub and replace the internal files in the eclipse-workspace.

   <a href="https://uploadkon.ir/uploads/601930_244.png" target="_blank">
       <img src="https://uploadkon.ir/uploads/thumbs/601930_244.png" alt="eplace the internal files in the eclipse-workspace" class="thumbnail">
   </a>

5. Refresh the project in the software once to make the changes appear (right-click and select Refresh).

   <a href="https://uploadkon.ir/uploads/22b930_245.png" target="_blank">
       <img src="https://uploadkon.ir/uploads/thumbs/22b930_245.png" alt="Refresh the project" class="thumbnail">
   </a>

6. You must replace the **4** important variables in the code based on the specifications of the website Url, Database , Table Name, and your Credential URL.

   +  **Website URL:** Replace `"https://www.example.com"` with your own website URL in the `MyGSCFetcherFunction` method.
    
        String websiteUrl = "https://www.example.com";
    
   +  **Database Connection String:** Replace `"1.1.1.1:3306/database_name?user=user&password=password"` with your own MySQL database connection string in the             `saveToDatabase` method.
    
        String connectionString = "1.1.1.1:3306/database_name?user=user&password=password";

   +  **Table Name:** The name of the table in the database where you want to store the information
    
        String tableName = "your_table_name";

   +  **Credential URL:** Replace `"https://example.s3.amazonaws.com/gsc-credential.json"` with the URL to your uploaded JSON credential file in the                       `buildWebmasters` method.
    
        String credentialUrl = "https://example.s3.amazonaws.com/gsc-credential.json";


   <a href="https://uploadkon.ir/uploads/1bee30_246.png" target="_blank">
       <img src="https://uploadkon.ir/uploads/thumbs/1bee30_246.png" alt="database specifications" class="thumbnail">
   </a>

7. After making the changes, right-click on the project name and select Run As > Maven install.

   <a href="https://uploadkon.ir/uploads/1bee30_247.png" target="_blank">
       <img src="https://uploadkon.ir/uploads/thumbs/1bee30_247.png" alt="Run As > Maven install" class="thumbnail">
   </a>

8. The console will display a report indicating the successful completion of the operation.

   <a href="https://uploadkon.ir/uploads/1bee30_248.png" target="_blank">
       <img src="https://uploadkon.ir/uploads/thumbs/1bee30_248.png" alt="successful completion of the operation" class="thumbnail">
   </a>

9. Navigate to the project directory and you will find the generated zip file (.jar) in the target folder.

   <a href="https://uploadkon.ir/uploads/625930_249.png" target="_blank">
       <img src="https://uploadkon.ir/uploads/thumbs/625930_249.png" alt="zip file jar" class="thumbnail">
   </a>


### Create a Lambda Project on Amazon

1.	Log into your account and then access the console

      <a href="https://uploadkon.ir/uploads/6f9a01_241.png" target="_blank">
          <img src="https://uploadkon.ir/uploads/thumbs/6f9a01_241.png" alt="Log into your account" class="thumbnail">
      </a>

2. Find the Lambda Service and enter to its environment

   <a href="https://uploadkon.ir/uploads/6f9a01_242.png" target="_blank">
       <img src="https://uploadkon.ir/uploads/thumbs/6f9a01_242.png" alt="Lambda Service" class="thumbnail">
   </a>

3. In the Lambda dashboard, click on "Create function button"

   <a href="https://uploadkon.ir/uploads/6f9a01_243.png" target="_blank">
       <img src="https://uploadkon.ir/uploads/thumbs/6f9a01_243.png" alt="Create function button" class="thumbnail">
   </a>

4. Choose a name for your function and select the Runtime based (for our project Java 8 on Amazon Linux 2)

   <a href="https://uploadkon.ir/uploads/6f9a01_244.png" target="_blank">
       <img src="https://uploadkon.ir/uploads/thumbs/6f9a01_244.png" alt="Choose a name for your function" class="thumbnail">
   </a>

5. To finalize this new function creation, click the "Create function" button.

   <a href="https://uploadkon.ir/uploads/6f9a01_245.png" target="_blank">
       <img src="https://uploadkon.ir/uploads/thumbs/6f9a01_245.png" alt="Create function" class="thumbnail">
   </a>

6. Your project will be created.

   <a href="https://uploadkon.ir/uploads/6f9a01_246.png" target="_blank">
       <img src="https://uploadkon.ir/uploads/thumbs/6f9a01_246.png" alt="Create function" class="thumbnail">
   </a>

7. Replace the Code source with your own .jar file (upload it from your device).

   <a href="https://uploadkon.ir/uploads/6f9a01_247.png" target="_blank">
       <img src="https://uploadkon.ir/uploads/thumbs/6f9a01_247.png" alt="project will be created" class="thumbnail">
   </a>
 
8. For the correct implementation of the project, it is necessary to enter the main function name in the project in this field

   <a href="https://uploadkon.ir/uploads/6f9a01_248.png" target="_blank">
       <img src="https://uploadkon.ir/uploads/thumbs/6f9a01_248.png" alt="enter the main function name" class="thumbnail">
   </a>
 
9. In the Handler field, delete the default name and enter the desired function name

   <a href="https://uploadkon.ir/uploads/6f9a01_249.png" target="_blank">
       <img src="https://uploadkon.ir/uploads/thumbs/6f9a01_249.png" alt="Handler field" class="thumbnail">
   </a>
 
10. You can test your project to ensure it's working properly (consider next 3 steps for testing purpose).

11. Go to the Test tab, choose an arbitrary name for the event and save it. So that you can hit the Test b/utton whenever you need to test your project

      <a href="https://uploadkon.ir/uploads/6f9a01_2410.png" target="_blank">
          <img src="https://uploadkon.ir/uploads/thumbs/6f9a01_2410.png" alt="Go to the Test tab" class="thumbnail">
      </a>

12. If your project is healthy and you've entered the function name correctly, you will see a message indicating successful execution.

      <a href="https://uploadkon.ir/uploads/6f9a01_2411.png" target="_blank">
          <img src="https://uploadkon.ir/uploads/thumbs/6f9a01_2411.png" alt="successful" class="thumbnail">
      </a>

      -**___Log output___** : 

      <a href="https://uploadkon.ir/uploads/6f9a01_2412.png" target="_blank">
          <img src="https://uploadkon.ir/uploads/thumbs/6f9a01_2412.png" alt="timeout error" class="thumbnail">
      </a>
     

13. If there is an issue with the project you've defined, you will encounter an error. In this case, the error will be displayed to you. If you encounter a timeout error, you should perform the next two steps that exist in this guide to set the timeout.

      <a href="https://uploadkon.ir/uploads/6f9a01_2413.png" target="_blank">
          <img src="https://uploadkon.ir/uploads/thumbs/6f9a01_2413.png" alt="configure the settings" class="thumbnail">
      </a>

14. To configure the settings for your function, go to the Configuration tab.

      <a href="https://uploadkon.ir/uploads/6f9a01_2414.png" target="_blank">
          <img src="https://uploadkon.ir/uploads/thumbs/6f9a01_2414.png" alt="configure the settings" class="thumbnail">
      </a>

15. In this tab, you can customize various settings for your Lambda function, such as memory allocation, timeout, environment variables, execution role, and more.

      <a href="https://uploadkon.ir/uploads/6f9a01_2415.png" target="_blank">
          <img src="https://uploadkon.ir/uploads/thumbs/6f9a01_2415.png" alt="Add Trigger" class="thumbnail">
      </a>

16. To save on unnecessary resource consumption, automate, and improve the performance of the project, it is necessary to add a trigger. Click the "Add Trigger" button.

      <a href="https://uploadkon.ir/uploads/6f9a01_2416.png" target="_blank">
          <img src="https://uploadkon.ir/uploads/thumbs/6f9a01_2416.png" alt="EventBridge (CloudWatch Events)" class="thumbnail">
      </a>

17. Select the "EventBridge (CloudWatch Events)" option.

      <a href="https://uploadkon.ir/uploads/6f9a01_2417.png" target="_blank">
          <img src="https://uploadkon.ir/uploads/thumbs/6f9a01_2417.png" alt="Existing rules" class="thumbnail">
      </a>

18. In the "Existing rules" section, select the "loop_daily_run" option. Click the "Add" button to save the settings.

      <a href="https://uploadkon.ir/uploads/6f9a01_2417.png" target="_blank">
          <img src="https://uploadkon.ir/uploads/thumbs/6f9a01_2418.png" alt="Existing rules" class="thumbnail">
      </a>

19. Finally, your trigger has been created and is displayed in this section.

      <a href="https://uploadkon.ir/uploads/6f9a01_2419.png" target="_blank">
          <img src="https://uploadkon.ir/uploads/thumbs/6f9a01_2419.png" alt="your trigger has been created" class="thumbnail">
      </a>


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

