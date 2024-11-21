package com.cucumbercraft.framework;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.aeonbits.owner.ConfigCache;
import org.testng.Assert;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.sql.DriverManager;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class APIReusuableLibrary {

    public static void main(String[] args) throws FileNotFoundException {
        APIReusuableLibrary api = new APIReusuableLibrary();

        System.out.println(api.getUserDetails("UATAUTO-WF-User39@mailinator.com"));
    }

    /**
     * Function to read input file from given Path
     *
     * @param inputFilePath The Path of the given File
     * @return The String of the input File
     */
    public String readInput(String inputFilePath) {
        String intputFileContent = "";
        BufferedReader bufferReader = null;
        try {
            String line;
            //File inputFile = new File(Util.getAbsolutePath()+Util.getFileSeparator()+inputFilePath);
            FileReader fileReader = new FileReader("src/test/resources/" + inputFilePath);
            bufferReader = new BufferedReader(fileReader);
            while ((line = bufferReader.readLine()) != null) {
                intputFileContent += line.trim();
            }
        } catch (FileNotFoundException x) {
            System.out.println(x.getMessage() + "first");
        } catch (IOException ex) {

            System.out.println(ex.getMessage() + "second");
        } finally {
            try {
                if (bufferReader != null) {
                    bufferReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return intputFileContent;

    }

    /**
     * Function to get value from given XML Tag
     *
     * @param response      The Response of the API
     * @param tagToValidate The XML Tag/Node from which value has to be fetched
     * @return The String value
     */
    public String extractValue(ValidatableResponse response, String tagToValidate) {

        if (!tagToValidate.contains("//")) {
            return jsonParser(response, tagToValidate);
        } else {
            return xmlParser(response, tagToValidate);
        }
    }

    /**
     * Function to get the response of an API
     *
     * @param url        The URL of the Application
     * @param methodType The Service Method GET,POST, PUT ,DELETE {@link SERVICEMETHOD}
     * @param headersMap The headers passed as Map object , refer to
     *                   {@link HeadersForAPI} to create custom Headers
     * @param statusCode The Expected Status Code
     * @return The Response {@link ValidatableResponse}
     */
    public ValidatableResponse sendNReceive(String url, SERVICEMETHOD methodType, Map<String, String> headersMap,
                                            int statusCode) throws Exception {
        ValidatableResponse response = null;
        try {
            switch (methodType) {
                case GET:
                    if (headersMap != null) {
                        response = RestAssured.given().log().all().relaxedHTTPSValidation().headers(headersMap).get(url).then().log().all()
                                .assertThat().statusCode(statusCode);
                        System.out.println(response.extract().body().toString());
                        reportItPASS(url, Integer.toString(statusCode), Integer.toString(statusCode), "PASS");

                    } else {
                        response = RestAssured.given().log().all().relaxedHTTPSValidation().get(url).then().assertThat().log().all()
                                .statusCode(statusCode);
                        reportItPASS(url, Integer.toString(statusCode), Integer.toString(statusCode), "PASS");
                    }

                    break;

                case DELETE:
                    if (headersMap != null) {
                        response = RestAssured.given().relaxedHTTPSValidation().headers(headersMap).delete(url).then()
                                .assertThat().statusCode(statusCode);
                        reportItPASS(url, Integer.toString(statusCode), Integer.toString(statusCode), "PASS");
                    } else {
                        response = RestAssured.given().relaxedHTTPSValidation().headers(headersMap).delete(url).then()
                                .assertThat().statusCode(statusCode);
                        reportItPASS(url, Integer.toString(statusCode), Integer.toString(statusCode), "PASS");
                    }
                    break;

                default:
                    break;
            }
        } catch (AssertionError x) {
            reportItFAIL(url, Integer.toString(statusCode), x.getMessage(), "FAIL");
            Assert.fail(x.getMessage());
        } catch (Exception ex) {
            reportItFAIL(url, Integer.toString(statusCode), ex.getMessage(), "FAIL");
            Assert.fail(ex.getMessage());
        }
        return response;
    }

    /**
     * Function to get the response of an API
     *
     * @param url             The URL of the Application
     * @param methodType      The Service Method GET,POST, PUT ,DELETE {@link SERVICEMETHOD}
     * @param postBodyType    The Format of Post Body {@link SERVICEFORMAT}
     * @param postBodyContent The Post Body Content
     * @param headersMap      The headers passed as Map object , refer to
     *                        {@link HeadersForAPI} to create custom Headers
     * @param statusCode      The Expected Status Code
     * @return The Response {@link ValidatableResponse}
     */
    public ValidatableResponse sendNReceive(String url, SERVICEMETHOD methodType, SERVICEFORMAT postBodyType,
                                            String postBodyContent, Map<String, String> headersMap, int statusCode) throws Exception {
        ValidatableResponse response = null;
        try {
            Object postBody = getPostBodyContent(postBodyContent, postBodyType);
            ContentType contentType = getPostContentType(postBodyType);
            switch (methodType) {
                case POST:

                    if (postBody instanceof File) {/* File */
                        if (headersMap != null) {
                            response = RestAssured.given().contentType(contentType).relaxedHTTPSValidation()
                                    .body((File) postBody).headers(headersMap).post(url).then().assertThat()
                                    .statusCode(statusCode);
                            reportItPASS(url, Integer.toString(statusCode), Integer.toString(statusCode), "PASS");
                        } else {
                            response = RestAssured.given().contentType(contentType).relaxedHTTPSValidation()
                                    .body((File) postBody).post(url).then().assertThat().statusCode(statusCode);
                            reportItPASS(url, Integer.toString(statusCode), Integer.toString(statusCode), "PASS");
                        }
                    } else if (postBody instanceof String) {/* String */
                        if (headersMap != null) {
                            response = RestAssured.given().contentType(contentType).relaxedHTTPSValidation().body(postBody)
                                    .headers(headersMap).post(url).then().assertThat().statusCode(statusCode);
                            reportItPASS(url, Integer.toString(statusCode), Integer.toString(statusCode), "PASS");
                        } else {
                            response = RestAssured.given().contentType(contentType).relaxedHTTPSValidation().body(postBody)
                                    .post(url).then().assertThat().statusCode(statusCode);
                            reportItPASS(url, Integer.toString(statusCode), Integer.toString(statusCode), "PASS");
                        }
                    }
                    break;

                case PUT:

                    if (postBody instanceof File) {/* File */
                        if (headersMap != null) {
                            response = RestAssured.given().contentType(contentType).relaxedHTTPSValidation()
                                    .body((File) postBody).headers(headersMap).put(url).then().assertThat()
                                    .statusCode(statusCode);
                            reportItPASS(url, Integer.toString(statusCode), Integer.toString(statusCode), "PASS");
                        } else {
                            response = RestAssured.given().contentType(contentType).relaxedHTTPSValidation()
                                    .body((File) postBody).put(url).then().assertThat().statusCode(statusCode);
                            reportItPASS(url, Integer.toString(statusCode), Integer.toString(statusCode), "PASS");
                        }
                    } else if (postBody instanceof String) {/* String */
                        if (headersMap != null) {
                            response = RestAssured.given().contentType(contentType).relaxedHTTPSValidation().body(postBody)
                                    .headers(headersMap).put(url).then().assertThat().statusCode(statusCode);
                            reportItPASS(url, Integer.toString(statusCode), Integer.toString(statusCode), "PASS");
                        } else {
                            response = RestAssured.given().contentType(contentType).relaxedHTTPSValidation().body(postBody)
                                    .put(url).then().assertThat().statusCode(statusCode);
                            reportItPASS(url, Integer.toString(statusCode), Integer.toString(statusCode), "PASS");
                        }
                    }
                    break;

                default:
                    break;
            }
        } catch (AssertionError x) {
            reportItFAIL(url, Integer.toString(statusCode), x.getMessage(), "FAIL");
            Assert.fail(x.getMessage());
        } catch (Exception ex) {
            reportItFAIL(url, Integer.toString(statusCode), ex.getMessage(), "FAIL");
            Assert.fail(ex.getMessage());
        }
        return response;
    }

    /**
     * Function to get updated String value
     *
     * @param inputString The input String
     * @param paramVar    The Variable
     * @param paramValue  The Variable Value to be updated
     * @return The updated String
     */
    public String updateContent(String inputString, String paramVar, String paramValue) {
        String updatedString = null;
        try {
            updatedString = inputString.replaceAll("%%" + paramVar + "%%", paramValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updatedString;
    }

    /**
     * Function to get the response of an API
     * <p>
     * //	 * @param url
     * The URL of the Application
     *
     * @param assertResponse   The Type of Assertion required for Response
     *                         {@link ASSERT_RESPONSE}
     * @param tagToValidate    The Tag to be validated
     * @param expectedResponse The Expected Response
     * @param comparison       The Type of Comparison to be done {@link COMPARISON }
     */
    public void assertIt(String uri, ValidatableResponse response, ASSERT_RESPONSE assertResponse, String tagToValidate,
                         Object expectedResponse, COMPARISON comparison) throws Exception {
        Object actualResponse = null;
        switch (assertResponse) {

            case BODY:
                actualResponse = response.extract().body().asString();
                if (compareIt(comparison, actualResponse, expectedResponse)) {
                    reportItPASS(uri, expectedResponse, actualResponse, "PASS");
                    Assert.assertTrue(true);
                } else {
                    reportItFAIL(uri, expectedResponse, actualResponse, "FAIL");
                    Assert.fail();
                }
                break;

            case TAG:
                if (!tagToValidate.contains("//"))
                    actualResponse = jsonParser(response, tagToValidate);
                else
                    actualResponse = xmlParser(response, tagToValidate);
                if (compareIt(comparison, actualResponse, expectedResponse)) {
                    reportItPASS(uri, expectedResponse, actualResponse, "PASS");
                    Assert.assertTrue(true);
                } else {
                    reportItFAIL(uri, expectedResponse, actualResponse, "FAIL");
                    Assert.fail();
                }
                break;

            case HEADER:
                actualResponse = response.extract().contentType();
                if (compareIt(comparison, actualResponse, expectedResponse)) {
                    reportItPASS(uri, expectedResponse, actualResponse, "PASS");
                    Assert.assertTrue(true);
                } else {
                    reportItFAIL(uri, expectedResponse, actualResponse, "FAIL");
                    Assert.fail();
                }
                break;

            case LIST:
                if (!tagToValidate.contains("//"))
                    actualResponse = jsonParserAsList(response, tagToValidate);
                else
                    actualResponse = xmlParserAsList(response, tagToValidate);
                if (compareIt(comparison, actualResponse, expectedResponse)) {
                    reportItPASS(uri, expectedResponse, actualResponse, "PASS");
                    Assert.assertTrue(true);
                } else {
                    reportItFAIL(uri, expectedResponse, actualResponse, "FAIL");
                    Assert.fail();
                }
                break;

            default:
                break;

        }
    }

    /**
     * Function to estabilish Connection to access DB
     */
    public String establishConnectionNExecute(String dbConnection, String dbUname, String dbPassword, String sqlQuery) {
        String queryOutput = null;
        Connection con = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(dbConnection, dbUname, dbPassword);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            while (rs.next()) {
                queryOutput = rs.getString(1);
            }
        } catch (SQLException sq) {

        } catch (ClassNotFoundException cs) {

        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return queryOutput;

    }

    /**
     * Function to estabilish Connection to access DB
     */
    public List<String> establishConnectionNExecuteNGetList(String dbConnection, String dbUname, String dbPassword,
                                                            String sqlQuery) {
        List<String> queryOutput = new ArrayList<String>();
        Connection con = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(dbConnection, dbUname, dbPassword);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            ResultSetMetaData rsmt = rs.getMetaData();
            int columnCount = rsmt.getColumnCount();
            List<List<String>> rowList = new LinkedList<List<String>>();
            while (rs.next()) {
                rowList.add(queryOutput);
                for (int column = 1; column <= columnCount; column++) {
                    final Object value = rs.getObject(column);
                    String valueAsString = String.valueOf(value);
                    if (!valueAsString.contains("."))
                        valueAsString = valueAsString + ".0";
                    queryOutput.add(valueAsString.toUpperCase());
                }
            }
        } catch (SQLException sq) {
            sq.printStackTrace();
        } catch (ClassNotFoundException cf) {
            cf.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return queryOutput;
    }

    private Object getPostBodyContent(String postBodyContent, SERVICEFORMAT postBodyType) {

        if (postBodyType.equals(SERVICEFORMAT.FILE)) {
            File file = new File(postBodyContent);
            return file;
        } else {
            return postBodyContent;
        }
    }

    private ContentType getPostContentType(SERVICEFORMAT contentTypes) {
        ContentType contentType = null;
        switch (contentTypes) {
            case FILE:
                contentType = ContentType.BINARY;
                break;
            case JSON:
                contentType = ContentType.JSON;
                break;
            case XML:
                contentType = ContentType.XML;
                break;
            case TEXT:
                contentType = ContentType.TEXT;
                break;
            default:
                break;
        }
        return contentType;
    }

    private String xmlParser(ValidatableResponse response, String tagToValidate) {
        return response.extract().xmlPath().getString(tagToValidate);
    }

    private String jsonParser(ValidatableResponse response, String tagToValidate) {

        return response.extract().jsonPath().getString(tagToValidate);
    }

    private List<String> xmlParserAsList(ValidatableResponse response, String tagToValidate) {

        return response.extract().xmlPath().getList(tagToValidate);
    }

    private Object jsonParserAsList(ValidatableResponse response, String tagToValidate) {

        return response.extract().jsonPath().getList(tagToValidate);
    }

    private Boolean compareIt(COMPARISON comparator, Object actualResponse, Object expectedResponse) {
        switch (comparator) {
            case IS_EQUALS:
                return (actualResponse).equals(expectedResponse);

            case IS_CONTAINS:
                return ((String) actualResponse).contains((String) expectedResponse);

            case IS_EXISTS:
                return ((String) actualResponse).contains((String) expectedResponse);
            default:
                break;
        }

        return null;
    }

    public String xmlParserByTag(ValidatableResponse response, String tagToValidate) throws SAXException {

        String elementValue = null;
        String inputXml = response.extract().asString();
        DocumentBuilder builder;
        Document doc;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(false);
            InputStream in = new ByteArrayInputStream(inputXml.getBytes(StandardCharsets.UTF_8));
            builder = factory.newDocumentBuilder();
            doc = builder.parse(in);
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr = xpath.compile(tagToValidate);
            elementValue = (String) expr.evaluate(doc, XPathConstants.STRING);
        } catch (Exception e) {

        } finally {
        }
        return elementValue;

    }

    private void reportItPASS(String uri, Object expectedValue, Object actualValue, String status) {
        expectedValue = expectedValue.toString().replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        actualValue = actualValue.toString().replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        ExtentCucumberAdapter.addTestStepLog(
                "<style>table {border-collapse !important  }td {border: 1px solid black !important;  white-space: normal; !important;}</style>"
                        + "<table><tr><td><b>ENDPOINT</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td>"
                        + uri + "</td></tr>" + "<tr><td><b>EXPECTED RESULT</b></td><td>" + expectedValue + "</td></tr>"
                        + "<tr><td><b>ACTUAL RESULT</b>&nbsp;&nbsp;&nbsp;&nbsp;</td><td>" + actualValue + "</td></tr>"
                        + "<tr><td><b>STATUS</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td><font color=green>"
                        + status + "</font></td></tr></table>");
    }

    private void reportItFAIL(String uri, Object expectedValue, Object actualValue, String status) {
        expectedValue = expectedValue.toString().replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        actualValue = actualValue.toString().replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        ExtentCucumberAdapter.addTestStepLog(
                "<style>table {border-collapse !important  }td {border: 1px solid black !important;  white-space: normal; !important;}</style>"
                        + "<table><tr><td><b>ENDPOINT</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td>"
                        + uri + "</td></tr>" + "<tr><td><b>EXPECTED RESULT</b></td><td>" + expectedValue + "</td></tr>"
                        + "<tr><td><b>ACTUAL RESULT</b>&nbsp;&nbsp;&nbsp;&nbsp;</td><td>" + actualValue + "</td></tr>"
                        + "<tr><td><b>STATUS</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td><font color=red>"
                        + status + "</font></td></tr></table>");
    }

    public String getOTP(String url, int statusCode, String number) throws Exception {
        String response = null;
        Response response1;
        String Num = null;
//        Map<String,String>
        try {
            for (int i = 0; i < 5; i++) {
                try {
                    response1 = RestAssured.given().relaxedHTTPSValidation()
//                            .header("Ocp-Apim-Subscription-Key", "0f5f781034934a198e78704fbc5a5d44")
                            .baseUri(url).pathParam("Num", number).get("{Num}");
                    if (response1.getStatusCode() == 200) {
                        response = response1.asString();
                        System.out.println("OTP is " + response);
                        break;
                    }
//                    reportItPASS(url, Integer.toString(statusCode), Integer.toString(statusCode), "PASS");
                } catch (Exception e) {
                    System.out.println("WE are getting OTP - Reset Error");
                }
                System.out.println("WE are getting OTP ");
            }

        } catch (Exception ex) {
            reportItFAIL(url, Integer.toString(statusCode), ex.getMessage(), "FAIL");
            Assert.fail(ex.getMessage());
        }
        return response;
    }

    public Map<String, String> getUserDetails(String username) throws FileNotFoundException {

        PropertyConfig config = ConfigCache.getOrCreate(PropertyConfig.class);
        Type type = TypeToken.getParameterized(Map.class, String.class, String.class).getType();
        Map<String, String> headerMap = new Gson().fromJson(new FileReader(config.getHeaderPath()), type);
        Map<String, String> respMap = null;
        for (int i = 0; i < 5; i++) {

            Response response = RestAssured.given()
                    .relaxedHTTPSValidation()
                    .headers(headerMap)
                    .baseUri(config.getbaseURI())
                    .queryParam("username", username)
                    .when()
                    .get("/account/get-user-details").then().extract().response();
            if (response.getStatusCode() == 200) {
                respMap = new Gson().fromJson(response.getBody().asString(), type);
                break;
            } else {
                throw new ExceptionUtils("Get user details api failed :" + response.getStatusCode());
            }

        }
        return respMap;
    }

    public Map<String, String> getFormattedUserDetailsByDate(String mailId) throws FileNotFoundException {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        DateTimeFormatter inputFormatter2 = DateTimeFormatter.ISO_ZONED_DATE_TIME;
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Map<String, String> detailsMap = getUserDetails(mailId);
        System.out.println(detailsMap);

        detailsMap.compute("dateOfBirth", (key, oldValue) -> {
            try {
                oldValue = LocalDate.parse(detailsMap.get("dateOfBirth"), inputFormatter).format(outputFormatter);
            } catch (Exception e) {
                oldValue = LocalDate.parse(detailsMap.get("dateOfBirth"), inputFormatter2).format(outputFormatter);
            }
            return oldValue;
        });

        return detailsMap;
    }

    public enum SERVICEMETHOD {
        GET, POST, PUT, DELETE
    }

    public enum SERVICEFORMAT {
        JSON, XML, TEXT, FILE
    }

    public enum ASSERT_RESPONSE {
        BODY, TAG, HEADER, LIST

    }

    public enum COMPARISON {
        IS_EXISTS, IS_EQUALS, IS_CONTAINS
    }


}