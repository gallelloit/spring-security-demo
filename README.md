# spring-security-samples
Simple Spring MVC Web App for Spring Security trainning pupposes.

## Configuration

* Spring Web MVC 5.8.0
* Spring Security 5.7.0
* Servlet API 3.1.0
* Servlet JSP API 2.3.1
* Servlet JSTL 1.2
* MySQL Java Connector 8.0.12
* C3P0 0.9.5.2

## Use case

Simple Spring MVC Web App for Spring Security trainning pupposes.

The following Spring Security features can be found in this project:

* Spring Java Configuration. No XML configuration is used in this project at all. But, just to clarify: I'm not against; it's just the chosen strategy.
* Ugly login form using plain JSP 
* Enhanced login form using Bootstrap
* Customized "Access Denied" page
* CSRF support
* Static (hardcoded) user for authentication. Only for trainning purposes and for quicky setup.
* JDBC for authentication.
* Role based access control
* Role based page displaying

## Getting started

To get this Maven project working:

* Database
  * Install latest MySQL version
  * Execute script `setup-spring-security-bcrypt-demo-database.sql`
  * Note that this scripts creates three users with the following access details:
    * john/fun123/[ROLE_EMPLOYEE]
    * mary/fun123/[ROLE_EMPLOYEE, ROLE_MANAGER]
    * susan/fun123/[ROLE_EMPLOYEE, ROLE_ADMIN]
  * Note that passwords are stored using bscrypt algorithm.

This should create this database:
[Database Diagram]https://github.com/pgbonino/spring-security-demo/blob/master/sql-scripts/spring-security-demo-database.png

* Java
  * Clone this repo
  * Build using Maven
  * In your IDE, run the application on a Server (I use Tomcat 9.0)
  * Play around