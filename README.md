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
{ "id" : "82540e2a-a1e6-442f-be93-60ed6aa45e00" }
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
{ "id" : "f76299dd-424b-4fcd-8650-831249b809da" }
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
{ "id" : "f53434a6-80b7-45ca-ac9a-5e094e9e42b5" }
```

**Example 2**    
- ```GET /api/code/f53434a6-80b7-45ca-ac9a-5e094e9e42b5``` request

Response body:
```
{
    "code": "Secret code",
    "date": "2022-12-29T08:48:31.571328200Z",
    "time": 4971,
    "views": 4
}
```

- ```GET /api/code/f53434a6-80b7-45ca-ac9a-5e094e9e42b5``` request

Response body:
```
{
    "code": "Secret code",
    "date": "2022-12-29T08:48:31.571328200Z",
    "time": 4920,
    "views": 3
}
```

**Example 3**    
- ```GET /code/f53434a6-80b7-45ca-ac9a-5e094e9e42b5``` request

Response:
<img src="[http://....jpg](https://user-images.githubusercontent.com/106676944/209927033-2e10969c-476e-4d1e-beb7-cc1eaf232224.png)" width="800" height="650" />
![image](https://user-images.githubusercontent.com/106676944/209927033-2e10969c-476e-4d1e-beb7-cc1eaf232224.png)

**Example 4**    
- ```GET /api/code/latest``` request

Response body:
```
[
    {
        "code": "public static void ...",
        "date": "2022-12-29T08:48:14.518376Z",
        "time": 0,
        "views": 0
    },
    {
        "code": "class Code { ...",
        "date": "2022-12-29T08:47:09.661470800Z",
        "time": 0,
        "views": 0
    }
]
```

**Example 5**    
- ```GET /code/latest``` request

Response:
![image](https://user-images.githubusercontent.com/106676944/209927188-7713d0bd-4a54-4abd-9f2f-6dd11cf2ba24.png)

**Example 6**    
- ```GET /code/new``` request

Response:
![image](https://user-images.githubusercontent.com/106676944/209927242-50fb45ff-7b85-49d7-ab81-c14cfee4add6.png)

## Additional info
To find more about this project, please visit https://hyperskill.org/projects/130.

