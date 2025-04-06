#!/bin/bash

# Mapping: folder name => Docker image name
declare -A images=(
  ["calldetailsms"]="infytel_calldetailsms"
  ["customerms"]="infytel_customerms"
  ["friend-familyms"]="infytel_friend-familyms"
  ["planms"]="infytel_planms"
  ["infytel-gateway"]="infytel_gateway"
)

echo "🐳 Pushing updated Docker images to Docker Hub..."

for folder in "${!images[@]}"; do
  image_tag="eugenekwaka/${images[$folder]}:latest"

  echo ""
  echo "📁 Entering $folder directory..."
  cd "$folder" || { echo "❌ Cannot find folder: $folder"; exit 1; }

  echo "📤 Pushing $image_tag..."
  docker push "$image_tag" || { echo "❌ Failed to push $image_tag"; cd ..; exit 1; }

  echo "⬅️ Returning to root..."
  cd ..
done

echo ""
echo "✅ All images pushed successfully!"
