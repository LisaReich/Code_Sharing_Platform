# Code_Sharing_Platform
## Description
Server development on Spring Boot and some basics in Web-programming such as HTML, CSS, and JS. Code sharing platform is a web service that can help you share a piece of code with other programmers. The main feature of this service is that it is supposed to be accessible only locally, not via the Internet.
## Features
- Two types of interfaces: API and web interface
- The API interface returns data as ```JSON```
- The web interface uses ```HTML```, ```JS```, and ```CSS```
- H2 database
## Dependencies
- Spring Data JPA
- Spring MVC 
- Project Lombok
- Template engine Thymeleaf
## Running the project
To launch the service compile the file:  
Code_Sharing_Platform/task/src/platform/CodeSharingPlatform.java
## API 
- ```POST /api/code/new``` returns a UUID of the snippet;

Request body: JSON object with a field code and two other fields:  

  1. ```time``` field contains the time (in seconds) during which the snippet is accessible;
  2. ```views``` field contains a number of views allowed for this snippet (excluding the current one);

Note: 0 and negative values correspond to the absence of the restriction.

- ```GET /code/new``` returns HTML that contains:

  1. input field that contains the time restriction;
  2. input field that contains the views restriction;
  3. textarea where you can paste a code snippet;
  4. button "Submit";

- ```GET /api/code/latest``` returns a JSON array with 10 most recently uploaded code snippets sorted from the newest to the oldest. It does not return any restricted snippets.
- ```GET /code/latest``` returns HTML that contains 10 most recently uploaded code snippets. It does not return any restricted snippets.
- ```GET /api/code/UUID``` returns JSON with the appropriate UUID uploaded code snippet and restrictions applied to the code piece. It is not accessible if one of the restrictions is triggered. And returns ```404 Not Found``` in this case and all the cases when no snippet with such a UUID was found.
- ```GET /code/UUID``` returns HTML that contains info about:

  1. the time restriction;
  2. the views restriction;
  3. the code snippet;
  
It is not accessible if one of the restrictions is triggered. And returns ```404 Not Found``` in this case and all the cases when no snippet with such a UUID was found.
  
## Usage
**Example 1**    
- ```POST /api/code/new``` request

Request body:
```
{
    "code": "class Code { ...",
    "time": 0,
    "views": 0
}
```

Response body:
```
{ "id" : "7dc53df5-703e-49b3-8670-b1c468f47f1f" }
```

- ```POST /api/code/new``` request

Request body:
```
{
    "code": "public static void ...",
    "time": 0,
    "views": 0
}
```

Response body:
```
{ "id" : "e6780274-c41c-4ab4-bde6-b32c18b4c489" }
```

- ```POST /api/code/new``` request

Request body:
```
{
    "code": "Secret code",
    "time": 5000,
    "views": 5
}
```

Response body:
```
{ "id" : "2187c46e-03ba-4b3a-828b-963466ea348c" }
```

**Example 2**    
- ```GET /api/code/2187c46e-03ba-4b3a-828b-963466ea348c``` request

Response body:
```
{
    "code": "Secret code",
    "date": "2020/05/05 12:01:45",
    "time": 4995,
    "views": 4
}
```

- ```GET /api/code/2187c46e-03ba-4b3a-828b-963466ea348c``` request

Response body:
```
{
    "code": "Secret code",
    "date": "2020/05/05 12:01:45",
    "time": 4991,
    "views": 3
}
```

**Example 3**    
- ```GET /code/2187c46e-03ba-4b3a-828b-963466ea348c``` request

Response:
IMAGE?

**Example 4**    
- ```GET /api/code/latest``` request

Response body:
```
[
    {
        "code": "public static void ...",
        "date": "2020/05/05 12:00:43",
        "time": 0,
        "views": 0
    },
    {
        "code": "class Code { ...",
        "date": "2020/05/05 11:59:12",
        "time": 0,
        "views": 0
    }
]
```

**Example 5**    
- ```GET /code/latest``` request

Response:
IMAGE?

**Example 6**    
- ```GET /code/new``` request

Response:
IMAGE?

## Additional info
To find more about this project, please visit https://hyperskill.org/projects/130.

