#!/bin/bash

# List of all microservices
services=(
  "user-service"
  "product-service"
  "order-service"
  "cart-service"
  "payment-service"
  "inventory-service"
  "notification-service"
  "review-service"
  "admin-service"
  "discount-service"
  "search-service"
)

# Loop through each service
for current_service in "${services[@]}"; do
  echo "============================================"
  echo "🔧 Creating branch for: $current_service"
  echo "============================================"

  # Go back to main branch and pull latest changes
  git checkout main
  git pull origin main

  # Create new branch for the service
  git checkout -b "$current_service"

  # Remove all other service folders
  for s in "${services[@]}"; do
    if [[ "$s" != "$current_service" ]]; then
      rm -rf "$s"
    fi
  done

  # Stage and commit the isolated service
  git add .
  git commit -m "Isolated $current_service"

  # Push to GitHub
  git push -u origin "$current_service"

  echo "✅ Branch '$current_service' created and pushed."
done

