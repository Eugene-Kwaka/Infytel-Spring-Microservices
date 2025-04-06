#!/bin/bash
# Mapping: folder name => image suffix
declare -A services=(
  ["calldetailsms"]="calldetailsms"
  ["customerms"]="customerms"
  ["friend-familyms"]="friend-familyms"
  ["planms"]="planms"
  ["infytel-gateway"]="gateway"
)

echo "🧹 Cleaning old builds and rebuilding Docker images..."

echo "🛑 Stopping existing containers..."
docker compose down --remove-orphans

for folder in "${!services[@]}"; do
  image_suffix="${services[$folder]}"
  echo "📦 Building $folder → infytel_$image_suffix..."

  cd "$folder" || { echo "❌ Folder $folder not found"; exit 1; }

  echo "🧪 Running tests and packaging..."
  ./mvnw clean package || { echo "❌ Maven build failed in $folder"; exit 1; }

  echo "🐳 Building Docker image eugenekwaka/infytel_${image_suffix}:latest"
  ./mvnw spring-boot:build-image \
    -Dspring-boot.build-image.imageName=eugenekwaka/infytel_${image_suffix}:latest \
    || { echo "❌ Docker build-image failed for $folder"; exit 1; }

  cd ..
done

echo "🚀 Starting containers with Docker Compose..."
docker compose up --build -d

echo "🔍 Checking container status..."
docker ps

