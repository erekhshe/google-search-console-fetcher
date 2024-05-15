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

1.  Go to the [Google Developers Console](https://console.developers.google.com/).
2.  Create a new project.
    *   Click on "Select a project" and then "New Project".
3.  Enable the Google Search Console API.
    *   Search for "Google Search Console API" in the API library and click on it.
    *   Click "Enable".
4.  Go to the credentials page and create a new OAuth 2.0 Client ID.
    *   Click "Create credentials" and select "OAuth client ID".
    *   Configure the consent screen if prompted.
    *   Choose "Web application" and fill in the necessary details.
5.  Download the JSON credential file.
    *   Click "Create" and then "Download" the JSON file.

### Compile the Project as a Single Jar File

1.  Clone the repository:
    
        git clone https://github.com/yourusername/repositoryname.git
    
2.  Navigate to the project directory:
    
        cd repositoryname
    
3.  Use Maven to compile the project:
    
        mvn clean package
    
4.  The compiled Jar file will be located in the `target` directory.

### Create a Lambda Project on Amazon

1.  Log in to the [AWS Management Console](https://aws.amazon.com/console/).
2.  Navigate to the Lambda service.
3.  Click "Create function".
4.  Choose "Author from scratch".
5.  Fill in the details and click "Create function".

### Upload the Jar File to Lambda

1.  In the Lambda function, go to the "Code" tab and click "Upload from" and select "Upload a .zip or .jar file".
2.  Upload the compiled Jar file.
3.  Set the handler to the appropriate Java handler (e.g., `arash.gscfetcher.GSCFetcher::handleRequest`).

### Test and Increase Timeout

1.  Click "Test" to configure a test event.
2.  Go to the "Configuration" tab and select "General configuration".
3.  Increase the timeout setting as needed.

Code Explanation
----------------

### Main Class: GSCFetcher

The `GSCFetcher` class implements `RequestHandler` to handle AWS Lambda requests. The main method `MyGSCFetcherFunction` fetches data from Google Search Console for the past five days and stores it in a MySQL database.

*   `handleRequest`: The entry point for AWS Lambda.
*   `MyGSCFetcherFunction`: Fetches data for the past five days.
*   `saveToDatabase`: Saves the fetched data to a MySQL database.
*   `buildWebmasters`: Builds the Webmasters client using the provided credentials.

Crucial Variables to Replace
----------------------------

There are three crucial variables in the code that users need to replace with their own values:

1.  **Website URL:** Replace `"https://www.example.com"` with your own website URL in the `MyGSCFetcherFunction` method.
    
        String websiteUrl = "https://www.example.com";
    
2.  **Database Connection String:** Replace `"1.1.1.1:3306/database_name?user=user&password=password"` with your own MySQL database connection string in the `saveToDatabase` method.
    
        String connectionString = "1.1.1.1:3306/database_name?user=user&password=password";
    
3.  **Credential URL:** Replace `"https://example.s3.amazonaws.com/gsc-credential.json"` with the URL to your uploaded JSON credential file in the `buildWebmasters` method.
    
        String credentialUrl = "https://example.s3.amazonaws.com/gsc-credential.json";
    

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
