Please create a directory called 'resources' in the project root directory:
mkdir resources

Then put all the text files you want to be processed in the resources folder.

To build and run the project use Maven; only one command is necessary.
Issue this command from the root project directory:
```mvn -e clean compile exec:java```

The web application should run on port 8080 of the host machine.
To provide a word to the word statistics api, please follow the format below:

http://localhost:8080/wordstats/?word=wordtotest

As can be seen, word signifies the name of the parameter in the url query string, and wordtotest is the value for the word parameter. The above command would fetch the api
information for the word 'wordtotest'.

More text files can easily be processed by simply adding them to the resources folder and restarting the web server.
