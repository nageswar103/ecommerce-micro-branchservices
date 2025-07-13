
#!/bin/bash

# Define array of services
services=(
  user-service
  product-service
  order-service
  cart-service
  payment-service
  inventory-service
  notification-service
  review-service
  admin-service
  discount-service
  search-service
)

# Base directory (update if needed)
BASE_DIR=/root/ecommerce-microservices

# Loop through services and build each one
for service in "${services[@]}"
do
  echo "Building $service ..."
  cd "$BASE_DIR/$service" || exit
  mvn clean package -DskipTests
done

# Loop again to run each service
for service in "${services[@]}"
do
  echo "Starting $service ..."
  cd "$BASE_DIR/$service/target" || exit
  nohup java -jar "$service-0.0.1-SNAPSHOT.jar" > "$service.log" 2>&1 &
done

echo "✅ All services started successfully."
