# Retrieve Results as XML #
The result of ACAPE can be retieved at http://localhost:9000/application/showSurvey.

## Example ##
```
<result>
    <interviewee>
        <name>Max</name>
        <mail>max@...de</mail>
    </interviewee>
    <!-- linear function: wtp = intercept + slope * utility -->
    <price>
        <intercept>505</intercept>
        <slope>32.79</slope>
    </price>
    <attribute>
        <name>Forum</name>
        <level>
            <name>Present</name>
            <utility>1.03</utility>
        </level>
        <level>
            <name>Absent</name>
            <utility>-1.13</utility>
        </level>
    </attribute>
    <attribute>
        <name>Payment with Fraud Detection</name>
        <level>
            <name>Present</name>
            <utility>0.79</utility>
        </level>
        <level>
            <name>Absent</name>
            <utility>-0.89</utility>
        </level>
    </attribute>
    <attribute>
        <name>Payment Method</name>
        <level>
            <name>Credit Card</name>
            <utility>-0.99</utility>
        </level>
        <level>
            <name>Debit Card</name>
            <utility>-0.99</utility>
        </level>
        <level>
            <name>Purchase Order</name>
            <utility>-0.99</utility>
        </level>
        <level>
            <name>Debit Card and Credit Card</name>
            <utility>-0.01</utility>
        </level>
        <level>
            <name>Debit Card, Credit Card, and Purchase Order</name>
            <utility>1.94</utility>
        </level>
        <level>
            <name>Debit Card and Purchase Order</name>
            <utility>0.96</utility>
        </level>
        <level>
            <name>Credit Card and Purchase Order</name>
            <utility>-0.01</utility>
        </level>
    </attribute>
    <attribute>
        <name>Customer Management</name>
        <level>
            <name>Absent</name>
            <utility>-1.32</utility>
        </level>
        <level>
            <name>Present</name>
            <utility>1.23</utility>
        </level>
    </attribute>
    <attribute>
        <name>Recommender System</name>
        <level>
            <name>Absent</name>
            <utility>-1.67</utility>
        </level>
        <level>
            <name>Present</name>
            <utility>1.57</utility>
        </level>
    </attribute>
</result>
```

# Retrieve Results as CSV #

A csv-file with the result of the survey can be downloaded with the url http://localhost:9000/Application/result

Each line of the file contains the utilities and the wtp of one interviewee.