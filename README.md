# Purpose
This is a demo project to build web API that returns a set of food trucks.
## Description:
- A single spring boot application load input csv files into db and provide web apis.
## Features
- Use JPA and sqlite as data persistence strategy
- All APIs have a unified corresponding format
- Global exception handling
- Use tlog for call chain tracking
- Secure the tcp conection by ssl(self-signed certificate)
- Use shiro for identity authentication and authentication
- Use CaffeineCache for local cache and response cache
- Use Knife4j as api document

# Buld project and run
1. Clone the project from git hub.
2. Use mvn build project:
```
./mvnw clean package -Dmaven.test.skip=true
```
4. Start app with this command: ```java -jar food-api-0.0.1-SNAPSHOT.jar```
5. Go to endpoint:https://localhost:8081/doc.html Knife4j's UI will show as blow:
<img width="1493" alt="image" src="https://user-images.githubusercontent.com/28502900/215373215-5f18404d-1d5e-421d-912d-86b095f9a968.png">

# Run project with docker
```
- sudo docker run -it -d --restart always -p 8088:8081 --name food-api-8088 dawnwin1102/food-api:latest bash
```
when container started goto https://localhost:8088/doc.html
# API Description
There is 3 controller and 7 apis expose fo demo
- /food/list need pagable request then response with paged result.eg:
 ```
 {
  "page": 1,
  "size": 10
  }
 ```
- /food/listByApplicant will result with specific applicant.eg:
```
{
  "applicant": "MOMO INNOVATION LLC"
  }
```
- /food/listByApplicantOrAddress will result with specific applicant or address.eg:
```
{
  "address": "1 BUSH ST",
  "applicant": "MOMO INNOVATION LLC"
  }
```
- /user/login this api will grant a jwt token if login success(username:leo,password:leo123)
<img width="1136" alt="image" src="https://user-images.githubusercontent.com/28502900/215377553-78ef32f8-eb8d-4afd-9e23-6c9cf4ab152e.png">
- /food/auth/list same as  /food/list but this api need Authorization ,use /user/login get token first then call api with Authorization header.
 <img width="1134" alt="image" src="https://user-images.githubusercontent.com/28502900/215376700-fbc5fda7-9eeb-4be7-a857-9f12ec332bda.png">
 
# Tradeoffs
With a limit time budget, I think the most import points to build a web api are:
- Unified response, global exception handle, api security, performance, traceability, simple and clear documentation
# Future improvement
- Separate models,service... into deferent modules not in a single application
- Use standard db like Mysql/PostgreSQL/SQL Server to deal with complex data struct like Geometry
- Use redis as a centralized cache component to set up a three level cache management to improve cache abaility
- Add centralized log solution(Ali logs/ELK)
- Setup a Oauth server as a center author service to author our apis
- Setup apigateway to do the SSL termination,load balance,configure traffic limiting policy..
- Build application in a micro service way(spring cloud/k8s).

