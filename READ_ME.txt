Topics:
Maven, Java, BDD, Cucumber, Gherkin, Selenium, Git, Junit.

Notes:
Use cucumber filter tags to execute the full regression or the test currently in development.

You can either run the maven goal test or use a Junit runner within the IDE - use the VM variable cucumber.filter.tags
For example;
mvn test -Dcucumber.filter.tags="@develop"
mvn test -Dcucumber.filter.tags="@regression"

Use git branches during development. i.e. a feature branch for each exercise and merge these back into develop once completed.

You will need to define a path for 'webdriver.chrome.driver' using a system property passed in the IDE run configuration
and the CLI when executing via maven goal.
i.e. Use -Dwebdriver.chrome.driver=path/to/driver on the CLI as the tests shouldn't have the
driver hardcoded.

You should consider logging of some description.

It should be obvious that the main 'application' code should be generic work with examples other than specified in
the tests.

Executing 'mvn test' should succeed.
mvn test -Dwebdriver.chrome.driver=path/to/chromedriver -Dcucumber.filter.tags="@regression"

Tests run: 6, Failures: 0, Errors: 0, Skipped: 0

Exercises:

(Java, Junit, Git, Maven)
1. How Many Words: Implement the method to pass the unit test.

(Cucumber, Java, Junit, Git, Maven)
2. Account - The feature file is written and a single step definition is created, although not implemented.
Add the remaining step definitions and implement them as well as the corresponding plain Java 'application' code.

(Selenium, Cucumber, Java, Junit, Git, Maven)
3. Google - Implement the pre-written scenarios using Selenium Webdriver. The Driver is configured to use chrome.
You will need to pass in the path to a chrome driver.

(BDD, Gherkin)
4. Password Change - Complete the feature file to describe the scenarios required to test a standard 'password change'
functionality. It can be assumed there are a few rules that the new password must comply with. You must specify the
current username and password before being allowed to enter a new password and confirm the new password by re-typing
the same. You DO NOT need to implement any steps.
See example change password screen. ExampleChangePasswordScreen.PNG
You may assume that the change password has been initiated.