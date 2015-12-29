# Introduction #

With the survey tool of gilbreth you can perform an adaptive conjoint analysis with price estimation.


# Used Tools #

The following free tools are used and should be downloaded if not already available:
  * [Mecurial](http://mercurial.selenic.com)
  * [Eclipse](http://www.eclipse.org/)
  * [Play framework](http://www.playframework.org/)

# Used libraries #
The following open source libraries are used
  * [Apache Commons Math (commons-math-2.2.jar)](http://commons.apache.org/math/download_math.cgi)
  * [Weka (weka.jar)](http://www.cs.waikato.ac.nz/ml/weka/): Download the zip file in the section "Other platform (Linux, etc.)"

# Install #

  1. Create a new directory _gilbreth\_workspace\_demo_
  1. Download the repository using "hg clone https://gilbreth.googlecode.com/hg/ gilbreth" to the newly created directory
  1. Create the folder lib in the subfolder acape and put the downloaded libraries in there
  1. Start eclipse and choose _gilbreth\_workspace\_demo_ as your workspace
  1. Goto File -> Import, Select "Existing Projects into Workspace"
  1. Under "Select root directory" choose _gilbreth\_workspace\_demo_. The package Explorer should show three projects, currently with a red exclamation mark
  1. In Eclipse Preferences: Goto Java -> Build Path -> Classpath Variables
  1. Add the following variables:

  * Name: **PLAY\_FRAMEWORK** Path Folder: **play-1.1.1/framework**
  * Name: **PLAY\_FRAMEWORK\_LIB** Path Folder: **play-1.1.1/framework/lib**
  * Name: **APACHE\_COMMONS** Path Folder: **acape/lib**

# Starting #
The gilbreth project should now compile without error though the other projects may still report problems.

Using the console goto gilbreth\_workspace\_demo/gilbreth and start acape with "play run acape".
Start a browser and go to http://localhost:9000/

Keep the console open while trying gilbreth. Stop gilbreth using Control + C. The console is used to display log messages too.