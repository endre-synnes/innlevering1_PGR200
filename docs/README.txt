How to use this Application:

1.  Open the properties file called: "DatabaseProperties.properties"

2.  Open a server (eks. localhost) insert the data needed to connect to your server into the properties file.

    a) The data needed is:

        -hostName (link to server)
        -dbName (name of the database you want to use, you don't need to create the Database just write a name)
        -userName (your username)
        -password (your password
        -port (port number to your server)


3.  Open the class called "Application", and run this.

4.  You will be presented with some commands you can type in, try this out.


optional:

    If you want to add some row to a table, just add the rows you want into the csv-files already created with the
    correct formatting. Or you can create some new csv files if you want.
    Just remember to use the right formatting as described here:


Create new table in database:

    a.  Create a new CSV-file in the same directory as the other csv files

    b.  Use this formatting in this new CSV file:

            Separator for each column is: ";"

            Metadata in the first four lines:

                line 1: table name (should be the same as the name of your file)

                line 2: column names

                line 3: SQL data types for each column (ex: VARCHAR (255) NOT NULL)
                        If You write "AUTO_INCREMENT" after the data type of your first column,
                        you don't need to write values for lines in that column.

                line 4: column name of your primary key

            Content:

                lines 5 to the end: your data with separator for each column;


Insert new data into tables:

    a. Open one of the csv-files already in the directory.

    b. Add your content below the last line in the csv-file.

        Use separator for each column.

        look at the metadata to see that your formatting is correct.