### Web and Mobile Automated Testing Repository

This repository contains automated tests for both web and mobile applications. With the use of environment variables in IntelliJ IDEA, you can easily switch between running tests for web or mobile platforms. Also is integrated with axe-core tools to easily check web accessibility violations while running an automated web script.

### Getting Started
To set up your local environment and install dependencies for running the tests, follow these steps:

### Prerequisites
Ensure you have IntelliJ IDEA installed on your system.
Make sure you have Maven installed.

### Installation Steps
Clone the Repository:

git clone https://github.com/raunelgarcia/mobile-framework.git

Open Project in IntelliJ IDEA:

Open IntelliJ IDEA and select File > Open.

Navigate to the directory where you cloned the repository and select it.

### Set Environment Variables:

For running web tests, set the environment variable PLATFORM to web.
For running mobile tests, set the environment variable PLATFORM to mobile.
You can set environment variables in IntelliJ IDEA by going to Run > Edit Configurations > Configuration > Environment.

### Install Dependencies:

Open the pom.xml file in IntelliJ IDEA. Click on the 'Maven' tab, then click on the 'Reimport' button to install all the required dependencies.

### Configure WebDriver (For Web Tests):

If you're running web tests, make sure you have the appropriate WebDriver installed and configured. You may need to download the WebDriver for your preferred browser and set the path accordingly.

### Configure Mobile Testing Environment (For Mobile Tests):

If you're running mobile tests, ensure you have the necessary emulators or devices set up and configured for testing. You may need to configure the mobile platform-specific settings accordingly.

Run Tests:

You can now run the automated tests either for web or mobile platforms based on the environment variable you've set.

### Test Structure
src/test/java contains all the test classes.

src/test/resources contains apks.

### Contributors

Raunel Garcia Quintana

Raul Galera Sancho

### Acknowledgments

Special thanks to our contributors and the open-source community for their valuable contributions and support.
