
my-ecommerce-db
admin
s0kj7FCNAWeS53f
ecommerce_db

mysql -h my-ecommerce-db.cnsw64g0kyem.us-east-2.rds.amazonaws.com -u admin -p 

nohup java -jar user-service-0.0.1-SNAPSHOT.jar > output.log 2>&1 &
nohup java -jar product-service-0.0.1-SNAPSHOT.jar > output.log 2>&1 &
nohup java -jar order-service-0.0.1-SNAPSHOT.jar > output.log 2>&1 &
nohup java -jar cart-service-0.0.1-SNAPSHOT.jar > output.log 2>&1 &
nohup java -jar payment-service-0.0.1-SNAPSHOT.jar > output.log 2>&1 &
nohup java -jar inventory-service-0.0.1-SNAPSHOT.jar > output.log 2>&1 &
nohup java -jar notification-service-0.0.1-SNAPSHOT.jar > output.log 2>&1 &
nohup java -jar review-service-0.0.1-SNAPSHOT.jar > output.log 2>&1 &
nohup java -jar admin-service-0.0.1-SNAPSHOT.jar > output.log 2>&1 &
nohup java -jar discount-service-0.0.1-SNAPSHOT.jar > output.log 2>&1 &
nohup java -jar search-service-0.0.1-SNAPSHOT.jar > output.log 2>&1 &


http://3.14.129.187:8081/users
http://3.14.129.187:8082/products
http://3.14.129.187:8083/orders
http://3.14.129.187:8084/cart
http://3.14.129.187:8085/payments
http://3.14.129.187:8086/inventory
http://3.14.129.187:8087/notifications
http://3.14.129.187:8088/reviews
http://3.14.129.187:8089/admin
http://3.14.129.187:8090/discounts
http://3.14.129.187:8091/search


http://18.219.186.62:8081/users
http://18.219.186.62:8082/products
http://18.219.186.62:8083/orders/1
http://18.219.186.62:8084/cart/1
http://18.219.186.62:8085/payments/1
http://18.219.186.62:8086/inventory/1
http://18.219.186.62:8087/notifications
http://18.219.186.62:8088/reviews
http://18.219.186.62:8089/admin
http://18.219.186.62:8090/discounts
http://18.219.186.62:8091/products





# End User Flow (Before and After Login)
When hitting https://shopping.devopscicd.xyz

User Browser
   ↓
Route 53 (resolves shopping.devopscicd.xyz)
   ↓
Nginx (on EC2 running React frontend)
   ↓
React App Loads (Home Page)
   ↓
React calls API Gateway for products/cart/etc.

#  After Login
Login Page (React) → AWS Cognito (Login/Register)
     ↓
JWT token returned → stored in browser
     ↓
API requests sent with JWT in headers:
     ↓
API Gateway → Validates JWT with Cognito
     ↓
→ Routes to ALB based on path:
     /products → product-service
     /orders   → order-service
     /cart     → cart-service
     ...
     ↓
Spring Boot Microservice
     ↓
MySQL RDS


# Admin User Flow (Before and After Login)
When hitting https://admin.devopscicd.xyz

Admin Browser
   ↓
Route 53 (resolves admin.devopscicd.xyz)
   ↓
Nginx (on same EC2 or different, serving admin React app)
   ↓
Admin React UI Loads (Login Page)
   ↓
Calls AWS Cognito for admin login (separate user pool or role)


# After Login
Admin logs in → JWT token returned → stored
   ↓
Admin React sends API requests with token:
   ↓
API Gateway → JWT verified with Cognito
   ↓
→ Routes to ALB:
     /admin/users     → admin-service
     /admin/products  → product-service
     /admin/inventory → inventory-service
     /admin/discounts → discount-service
     ...
     ↓
Spring Boot Admin Microservices
     ↓
MySQL RDS
