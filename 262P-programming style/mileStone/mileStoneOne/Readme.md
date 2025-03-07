Milestone 1
1. import the libray into my workspace from maven, the version of java and json are in pom.xml
2. XML files are in src/main/resources, XX small, XX medium,XX large, XX verylarge(since github limit the size of each file, we only upload files whose size smaller than 100MB)
3. Outcome from each task are in src/main/java/Result
4. Division of labor: 
5. Tasks:
   i. Read an XML file (given as command line argument) into a JSON object and write the JSON object back on disk as a JSON file.
   ii. Read an XML file into a JSON object, and extract some smaller sub-object inside, given a certain path (use JSONPointer). Write that smaller object to disk as a JSON file.
   iii. Read an XML file into a JSON object, check if it has a certain key path (given in the command line too). If so, save the JSON object to disk; if not, discard it.
   iv. Read an XML file into a JSON object, and add the prefix "swe262_" to all of its keys.
   v. Read an XML file into a JSON object, replace a sub-object on a certain key path with another JSON object that you construct, then write the result on disk as a JSON file.
6. Verify application: every files would be used in every task, command line and results are on the beginning of each .java
