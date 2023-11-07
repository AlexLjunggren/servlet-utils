## Servlet Utils

A collection of useful servlet utilities

### Request Inspector Filter

A filter used to inspect servlet requests.

**Use:** Add the following to web.xml

```xml
<filter>
	<filter-name>RequestInspectorFilter</filter-name>
	<filter-class>ljunggren.io.servletUtils.filter.RequestInspectorFilter</filter-class>
</filter>
<filter-mapping>
	<filter-name>RequestInspectorFilter</filter-name>
	<url-pattern>/*</url-pattern>
</filter-mapping>
```

Filter will capture the following:

- Requested URI
- Headers
- Parameters
- Body payload

### Response Inspector Filter

A filter used to inspect servlet responses.

**Use:** Add the following to web.xml

```xml
<filter>
	<filter-name>ResponseInspectorFilter</filter-name>
	<filter-class>ljunggren.io.servletUtils.filter.ResponseInspectorFilter</filter-class>
</filter>
<filter-mapping>
	<filter-name>ResponseInspectorFilter</filter-name>
	<url-pattern>/*</url-pattern>
</filter-mapping>
```

### Filter Logger

By default, the filters will log to System.out. A custom logger can be added by inheriting from the FilterLogger class, overridding the log() method, and adding to the init-param.

```java
public class MyCustomLogger extends FilterLogger {
	@Override
	public void log(String message) {
		// custom logging
	}
}
```

```xml
<init-param>
	<param-name>filterLoggerClass</param-name>
	<param-value>my.MyCustomLogger</param-value>
</init-param>
```

**Note:** param-name must be *filterLoggerClass*

### Sample Output

```text
Response: <html><body>Hello World!</body></html>
Request URI: /myapp/hello
Request Header: [user-agent, PostmanRuntime/7.29.2]
Request Header: [accept, */*]
Request Header: [postman-token, 4d19c129-9071-4747-ab5c-6f7a370ac8e2]
Request Header: [host, localhost:8080]
Request Header: [accept-encoding, gzip, deflate, br]
Request Header: [connection, keep-alive]
Request Header: [content-type, multipart/form-data; boundary=--------------------------459729101110615036942366]
Request Header: [content-length, 323]
Request Parameter: [Name, Alex]
Request Parameter: [Age, 40]
Request Body: ----------------------------459729101110615036942366
Content-Disposition: form-data; name="phone"

867-5309
----------------------------459729101110615036942366
Content-Disposition: form-data; name=""; filename="Test.txt"
Content-Type: text/plain

Test data
----------------------------459729101110615036942366--
```