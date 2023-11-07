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

By default, the filters will log to System.out. A custom logger can be added by inheriting from the FilterLogger class and adding to the init-param.

```xml
	<init-param>
		<param-name>filterLoggerClass</param-name>
		<param-value>my.CustomLogger</param-value>
	</init-param>
```

**Note:** param-name must be *filterLoggerClass*
