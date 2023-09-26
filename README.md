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
4. Start app with this command: ```java -jar shopping-0.0.1-SNAPSHOT.jar```
5. Go to endpoint:https://localhost:8081/doc.html Knife4j's UI will show as blow:
<img width="1531" alt="image" src="https://github.com/dawnwin1102/shopping/assets/28502900/53f958f1-87f1-461e-80b3-53a975825253">

# Run project with docker
```
- sudo docker run -it -d --restart always -p 8088:8081 --name shopping-8088 dawnwin1102/shopping:latest bash
```
when container started goto https://localhost:8088/doc.html
# API Description
There is 4 controller and 11 apis expose fo demo
- /meal/getMealList need pagable request then response with MealList.eg:
```
fetch("https://localhost:8088/meal/getMealList",
  "body": null,
  "method": "GET"
});
```
- /meal/mealDetail/{mealId} .eg:
```
fetch("https://localhost:8088/meal/mealDetail/1", {
  "body": null,
  "method": "GET"
});
```
- cart/addToCart will result with cart items .eg:
```
fetch("https://localhost:8088/cart/addToCart", {
  "headers": {
    "cookie": "auth_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2OTU3NTY5MDMsInVzZXIiOnsibW9iaWxlIjoiMTExMTExMTExMTExIiwidXNlck5hbWUiOiJtaWtlIn19.A7I3VtUD_hOgzIweuftJkBbBNQ4zSRtJpCidd1XxbbQ"
  },
  "body": "{\n  \"mobile\": \"13795341300\",\n  \"mealItemList\": [\n    {\n      \"description\": \"xxx\",\n      \"imgUrl\": \"xxx\",\n      \"mealId\": 2,\n      \"mealName\": \"7-up\",\n      \"quantity\": 3,\n      \"unitPrice\": 10\n    }\n  ]\n}",
  "method": "POST"
});
```
- /cart/getCartMealList  .eg:
```
fetch("https://localhost:8088/cart/getCartMealList", {
  "headers": {
    "cookie": "auth_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2OTU3NTY5MDMsInVzZXIiOnsibW9iaWxlIjoiMTExMTExMTExMTExIiwidXNlck5hbWUiOiJtaWtlIn19.A7I3VtUD_hOgzIweuftJkBbBNQ4zSRtJpCidd1XxbbQ"
  },
  "body": "\"\"",
  "method": "POST"
});
```
- /cart/getCartMealList/{mobile}  empty cart item.eg:
```
fetch("https://localhost:8088/cart/clearCart/1111", {
  "headers": {
    "cookie": "auth_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2OTU3NTY5MDMsInVzZXIiOnsibW9iaWxlIjoiMTExMTExMTExMTExIiwidXNlck5hbWUiOiJtaWtlIn19.A7I3VtUD_hOgzIweuftJkBbBNQ4zSRtJpCidd1XxbbQ",
  },
  "body": null,
  "method": "GET"
});
```
- /order/createOrder  create order item.eg:
```
fetch("https://localhost:8088/order/createOrder", {
  "headers": {
    "cookie": "auth_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2OTU3NTY5MDMsInVzZXIiOnsibW9iaWxlIjoiMTExMTExMTExMTExIiwidXNlck5hbWUiOiJtaWtlIn19.A7I3VtUD_hOgzIweuftJkBbBNQ4zSRtJpCidd1XxbbQ"
  },
  "body": "{\n  \"address\": \"ddd street\",\n  \"email\": \"ddd@hotmail.com\",\n  \"mealList\":  [\n   {\n      \"mealId\": 2,\n      \"mealName\": \"cola\",\n      \"unitPrice\": 20,\n      \"quantity\": 5,\n      \"description\": \"xxx\",\n      \"imgUrl\": \"xxx\"\n    }\n  ],\n  \"userName\": \"ddd\"\n}",
  "method": "POST"
});
```
- //order/orderDetail/{orderId}  query order detail.eg:
```
fetch("https://localhost:8088/order/orderDetail/13", {
  "headers": {
    "cookie": "auth_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2OTU3NTY5MDMsInVzZXIiOnsibW9iaWxlIjoiMTExMTExMTExMTExIiwidXNlck5hbWUiOiJtaWtlIn19.A7I3VtUD_hOgzIweuftJkBbBNQ4zSRtJpCidd1XxbbQ"
  },
  "body": null,
  "method": "GET"
});
```
- /order/orderList.eg:
```
fetch("https://localhost:8088/order/orderList", {
  "headers": {
    "cookie": "auth_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2OTU3NTY5MDMsInVzZXIiOnsibW9iaWxlIjoiMTExMTExMTExMTExIiwidXNlck5hbWUiOiJtaWtlIn19.A7I3VtUD_hOgzIweuftJkBbBNQ4zSRtJpCidd1XxbbQ"
  },
  "body": "\"\"",
  "method": "POST"
});
```
- /order/paymentCallback .eg:
```
fetch("https://localhost:8088/order/paymentCallback", {
"headers": {
"cookie": "auth_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2OTU3NTY5MDMsInVzZXIiOnsibW9iaWxlIjoiMTExMTExMTExMTExIiwidXNlck5hbWUiOiJtaWtlIn19.A7I3VtUD_hOgzIweuftJkBbBNQ4zSRtJpCidd1XxbbQ"
},
"body": "{\n  \"cardNo\": \"123\",\n  \"cvc\": \"333\",\n  \"orderId\": 11,\n  \"orderNumber\": \"\",\n  \"payStatus\": \"success\"\n}",
"method": "POST"
});
```

- /user/login .eg:
```
  fetch("https://localhost:8088/user/login?password=mike123&username=mike", {
  "headers": {
  "cookie": "auth_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2OTU3NTY5MDMsInVzZXIiOnsibW9iaWxlIjoiMTExMTExMTExMTExIiwidXNlck5hbWUiOiJtaWtlIn19.A7I3VtUD_hOgzIweuftJkBbBNQ4zSRtJpCidd1XxbbQ"
  },
  "body": null,
  "method": "POST"
  });
```

- /user/login this api will grant a jwt token if login success(username:leo,password:leo123/username:mike,password:mike123)
<img width="1548" alt="image" src="https://github.com/dawnwin1102/shopping/assets/28502900/3efe8404-456f-458b-92e2-eacc2d3cee80">
- /cart/addToCart same as  /cart/addToCart but this api need Authorization ,use /user/login get token first then call api with Authorization header.
<img width="1326" alt="image" src="https://github.com/dawnwin1102/shopping/assets/28502900/8aef2bc2-2f51-4ef4-8889-bcf090c37f77">


 
# Tradeoffs
With a limit time budget, I think the most import points to build a web api are:
- Unified response, global exception handle, api security, performance, traceability, simple and clear documentation
# Future improvement
- Separate models,service... into deferent modules not in a single application
- Just use synchronized to hanlde threas safe, in a distribute environment we need a distribute lock mechanism like redis
- Use standard db like Mysql/PostgreSQL/SQL Server to deal with complex data struct like Geometry
- Use redis as a centralized cache component to set up a three level cache management to improve cache abaility
- Add centralized log solution(Ali logs/ELK)
- Setup a Oauth server as a center author service to author our apis
- Setup apigateway to do the SSL termination,load balance,configure traffic limiting policy..
- Build application in a micro service way(spring cloud/k8s).

