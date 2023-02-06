# visma-internship-task

Create a web application to manage Visma’s internal meetings using Java Spring Boot.

Requirements:

● Rest API endpoint to create a new meeting. Application should retain data between restarts. Meeting model should contain the following properties:
○ Name
○ ResponsiblePerson
○ Description
○ Category (Fixed values - CodeMonkey / Hub / Short / TeamBuilding)
○ Type (Fixed values - Live / InPerson)
○ StartDate
○ EndDate

● Rest API endpoint to delete a meeting. Only the person responsible can delete the meeting.

● Rest API endpoint to add a person to the meeting.
○ Command should specify who is being added and at what time.
○ If a person is already in a meeting which intersects with the one being added, a warning message should be given.
○ Prevent the same person from being added twice.

● Rest API endpoint to remove a person from the meeting.
○ If a person is responsible for the meeting, he can not be removed.

● Rest API endpoint to list all the meetings. Add the following parameters to filter the data:
○ Filter by description (if the description is “Jono Java meetas”, searching for java should return this entry)
○ Filter by responsible person
○ Filter by category
○ Filter by type
○ Filter by dates (e.g meetings that will happen starting from 2022-01-01 /meetings that will happen between 2022-01-01 and 2022-02-01)
○ Filter by the number of attendees (e.g show meetings that have over 10 people attending)


Used Spring boot 3.0;
JAVA 17;
Docker with PostgreSQL img.
Liquibase;
Swagger;
Criteria API for request FILTERING;

