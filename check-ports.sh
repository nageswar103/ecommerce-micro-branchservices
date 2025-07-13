
#!/bin/bash

echo "🔍 Checking ports 8081 to 8091..."

for port in {8081..8091}
do
  echo -e "\n🔹 Port $port:"
  lsof -i :$port || echo "❌ Nothing running on port $port"
done
