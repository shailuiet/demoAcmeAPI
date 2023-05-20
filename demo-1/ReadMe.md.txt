commands to run this project

1. maven clean install (will also run unit and integration test)
2. docker compose up
3. check through postman or curl command curl -v  http://localhost:9090/demoApp/api/customers for non secure api access, you will get 403 forbidden error.
4. Then try with -H "api-key: api123" then you will get list of customers