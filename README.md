**Homework**

This project is a test automation suite for testing the website www.saucedemo.com. It utilizes the Allure, Selenium, and TestNG frameworks and is implemented in the Java programming language.

**Prerequisites**

Before running the tests, make sure you have the following software installed on your machine:
Java JDK (Oracle OpenJDK version 20.0.1)
Maven (version 3.9.3)
Selenium WebDriver (version 4.10.0)
TestNG (version 7.8.0)
Allure Command Line (version 2.22.1)

**Setup and Installation**

Clone the repository to your local machine using the following command: git clone git@github.com:davitebralidze/Homework.git

Install the required dependencies by navigating to the pom.xml file and updating the Maven project

**Running the tests with TestNG**

You can invoke TestNG with a testng.xml file (right click on it and select the option Run)

Here is an example testng.xml file:


<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd" >

<suite name="Suite1" verbose="1" >
  <test name="Nopackage" >
    <classes>
       <class name="NoPackageTest" />
    </classes>
  </test>

  <test name="Regression1">
    <classes>
      <class name="test.sample.ParameterSample"/>
      <class name="test.sample.ParameterTest"/>
    </classes>
  </test>
</suite>
You can specify package names instead of class names:


<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd" >

<suite name="Suite1" verbose="1" >
  <test name="Regression1"   >
    <packages>
      <package name="test.sample" />
   </packages>
 </test>
</suite>
In this example, TestNG will look at all the classes in the package test.sample and will retain only classes that have TestNG annotations.

You can also specify groups and methods to be included and excluded:


<test name="Regression1">
  <groups>
    <run>
      <exclude name="brokenTests"  />
      <include name="checkinTests"  />
    </run>
  </groups>

  <classes>
    <class name="test.IndividualMethodsTest">
      <methods>
        <include name="testMethod" />
      </methods>
    </class>
  </classes>
</test>
You can also define new groups inside testng.xml and specify additional details in attributes, such as whether to run the tests in parallel, how many threads to use, whether you are running JUnit tests, etc...

By default, TestNG will run your tests in the order they are found in the XML file. If you want the classes and methods listed in this file to be run in an unpredictable order, set the preserve-order attribute to false


<test name="Regression1" preserve-order="false">
  <classes>

    <class name="test.Test1">
      <methods>
        <include name="m1" />
        <include name="m2" />
      </methods>
    </class>

    <class name="test.Test2" />

  </classes>
</test>


**Generating Allure Report**

After running the tests, navigate to the project root directory and execute the following command to generate the Allure report: allure serve

**Test Structure**

The test automation suite is organized as follows:
src/test/java: Contains the test classes and test data.
src/main/java: Contains utility classes and common functions.
src/main/pom.xml: Contains the general configuration

**Contact**

For any inquiries or issues, please contact: 
                                            email - dav.ebralidze@gmail.com
                                            phone - (+995) 579 04 28 72
