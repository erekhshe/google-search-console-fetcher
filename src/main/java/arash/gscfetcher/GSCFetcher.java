package arash.gscfetcher;

import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.webmasters.Webmasters;
import com.google.api.services.webmasters.WebmastersScopes;
import com.google.api.services.webmasters.model.ApiDataRow;
import com.google.api.services.webmasters.model.SearchAnalyticsQueryRequest;
import com.google.api.services.webmasters.model.SearchAnalyticsQueryResponse;

public class GSCFetcher implements RequestHandler<Object, Object> {

	@Override
	public Object handleRequest(Object input, Context context) {
		MyGSCFetcherFunction();
		return null;
	}

	public static void MyGSCFetcherFunction() {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		String yesterday = LocalDate.now().minusDays(1).format(dateFormatter);
		String twoDaysAgo = LocalDate.now().minusDays(2).format(dateFormatter);
		String threeDaysAgo = LocalDate.now().minusDays(3).format(dateFormatter);
		String fourDaysAgo = LocalDate.now().minusDays(4).format(dateFormatter);
		String fiveDaysAgo = LocalDate.now().minusDays(5).format(dateFormatter);

		String[] pastDays = new String[]{fiveDaysAgo, fourDaysAgo, threeDaysAgo, twoDaysAgo, yesterday}; 

		Webmasters webmasters = buildWebmasters(); //loading credential		
		
		for (int i = 0; i < pastDays.length; i++) {
			try {
				SearchAnalyticsQueryRequest query = new SearchAnalyticsQueryRequest();

				//we want to get data day by day, so start date and end date must be identical
				query.setStartDate(pastDays[i]);
				query.setEndDate(pastDays[i]);
				//--

				query.setRowLimit(25000); //max = 25000

				query.set("dataState", "all"); //If "all", data will include fresh data. If "final", the returned data will include only finalized data.

				//You can add your desired parameters here
				//query.setAggregationType("auto");

				List<String> dimensions = new ArrayList<>();

				dimensions.add("page");
				dimensions.add("device");
				query.setDimensions(dimensions);

				//You can add some Dimension Filter Groups here by the help of getApiDimensionFilterGroups function that I commented below  
				//List<ApiDimensionFilterGroup> apiDimensionFilterGroups = getApiDimensionFilterGroups();
				//query.setDimensionFilterGroups(apiDimensionFilterGroups);

				//                    Replace your website
				//                    \/ \/ \/ \/ \/ \/ \/ 
				String websiteUrl = "https://www.example.com"; 
				//                    /\ /\ /\ /\ /\ /\ /\ 
				
				SearchAnalyticsQueryResponse response = webmasters.searchanalytics().query(websiteUrl, query).execute();
				//to check the output you can print the result like this: System.out.println(response.toPrettyString());

				if (response.getRows() == null) {
					System.out.println("Google Search Console API did not returned any data for "  + pastDays[i] + "!");
					continue;
				}
				
				System.out.println(response.getRows().size() + " records fetched from Google Search Console for "  + pastDays[i]);
				
				saveToDatabase(response, pastDays[i]);

			} catch (IOException e) {
				System.err.println("Error fetching data from Google Search Console for " + pastDays[i] + ": " + e.getMessage());
			}
		}
	}

	private static void saveToDatabase(SearchAnalyticsQueryResponse response, String date) {
		Connection conn = null;
		try {
			
			//                                     Replace your connection string			
			//                          \/ \/ \/ \/ \/ \/ \/ \/ \/ \/ \/ \/ \/ \/ \/ \/ \/ \/ 
			String connectionString = "1.1.1.1:3306/database_name?user=user&password=password";
			//                          /\ /\ /\ /\ /\ /\ /\ /\ /\ /\ /\ /\ /\ /\ /\ /\ /\ /\ 
			
			conn = DriverManager.getConnection("jdbc:mysql://"+connectionString+"&characterEncoding=UTF-8");
			if (conn != null) {
				System.out.println("Connected to the database successfully. Deleting all existing records for " + date);
				 //      Replace your table name			
				//   \/ \/ \/ \/ \/ \/ \/ \/ \/ \/ \/
				String tableName = "your table name";
				//   /\ /\ /\ /\ /\ /\ /\ /\ /\ /\ /\
				conn.prepareStatement("DELETE FROM "+ tableName +" WHERE date='" + date + "'").execute();

				String insertQuery = "INSERT INTO "+ tableName +" (date, query, page, device, country, clicks, impressions, ctr, position) VALUES ";
				int rowCounter = 0;
				int totalRows = response.getRows().size();

				for (ApiDataRow row : response.getRows()) {
					rowCounter++;
					insertQuery += "("
							+"\""+
							date
							+"\",\""+
							"query"
							//row.getKeys().get(0).toString().replace('\"', '*').replace('\\', '*')
							+"\",\""+
							row.getKeys().get(0).toString()
							+"\",\""+
							row.getKeys().get(1).toString()
							+"\",\""+
							"country"
							//row.getKeys().get(3).toString()
							+"\","+
							String.valueOf(row.getClicks().intValue())
							+","+
							String.valueOf(row.getImpressions().intValue())
							+","+
							row.getCtr().toString()
							+","+
							row.getPosition().toString()
							+")";
					if (rowCounter == totalRows || rowCounter % 5000 == 0) {
						conn.prepareStatement(insertQuery).execute();
						insertQuery = "INSERT INTO google_search_console (date, query, page, device, country, clicks, impressions, ctr, position) VALUES ";
					} else {
						insertQuery += ",";
					}
				}
				System.out.println("Data successfully inserted for " + date);
			}
		} catch (SQLException ex) {
			System.err.println("Database error: " + ex.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					System.err.println("Error closing database connection: " + ex.getMessage());
				}
			}
		}
	}

	private static Webmasters buildWebmasters() {
		HttpTransport httpTransport;
		try {
			httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		} catch (GeneralSecurityException | IOException e) {
			throw new IllegalStateException("Error creating HTTP transport: " + e.getMessage(), e);
		}

		JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
		GoogleCredential credential;
		try {
			//                         Replace your uploaded .json credential file url 			
			//                       \/ \/ \/ \/ \/ \/ \/ \/ \/ \/ \/ \/ \/ \/ \/ \/ \/ 
			String credentialUrl = "https://example.s3.amazonaws.com/gsc-credential.json";
			//                       /\ /\ /\ /\ /\ /\ /\ /\ /\ /\ /\ /\ /\ /\ /\ /\ /\
			
			credential = GoogleCredential.fromStream(new URL(credentialUrl).openStream())
					.createScoped(Collections.singleton(WebmastersScopes.WEBMASTERS));
		} catch (IOException e) {
			throw new IllegalStateException("Error loading credentials: " + e.getMessage(), e);
		}

		return new Webmasters.Builder(httpTransport, jsonFactory, credential)
				.setApplicationName("Search Console CLI")
				.build();
	}

	/*
    private static List<ApiDimensionFilterGroup> getApiDimensionFilterGroups() {
        ApiDimensionFilter javaFilter = new ApiDimensionFilter();
        javaFilter.setDimension("page");
        javaFilter.setExpression("java");
        javaFilter.setOperator("contains");
        ArrayList<ApiDimensionFilter> javaFilters = new ArrayList<>();
        javaFilters.add(javaFilter);
        ApiDimensionFilterGroup apiDimensionJavaFilterGroup = new ApiDimensionFilterGroup();
        apiDimensionJavaFilterGroup.setFilters(javaFilters);
        apiDimensionJavaFilterGroup.setGroupType("and");
        ApiDimensionFilter codecFilter = new ApiDimensionFilter();
        codecFilter.setDimension("page");
        codecFilter.setExpression("codec");
        codecFilter.setOperator("contains");
        ArrayList<ApiDimensionFilter> pythonFilters = new ArrayList<>();
        pythonFilters.add(codecFilter);
        ApiDimensionFilterGroup apiDimensionPythonFilterGroup = new ApiDimensionFilterGroup();
        apiDimensionPythonFilterGroup.setFilters(pythonFilters);
        apiDimensionPythonFilterGroup.setGroupType("and");
        List<ApiDimensionFilterGroup> apiDimensionFilterGroups = new ArrayList<>();
        apiDimensionFilterGroups.add(apiDimensionJavaFilterGroup);
        apiDimensionFilterGroups.add(apiDimensionPythonFilterGroup);
        return apiDimensionFilterGroups;
    }
	 */
}
