# netflix-git-cache
Netflix GitHub API Read Cache

# Setup

**Step 1:** Edit the file `src/main/resources/application.properties` with the following content:

```
# Update the GitHub OAuth2 Token
github.username=USERNAME
github.password=PASSWORD
github.token=TOKEN

# Use default or update the cache expiration time and maximum cache entries size
cache.expireAfterWrite.minutes=5
cache.maximumSize=10000

# Update the port if using new other port
server.port = 8080
```

**Step 2:** Ensure you have apache maven installed: https://maven.apache.org/install.html

**Step 3:** Compile the project: `mvn clean package`

**Step 6:** Run the project: `java -jar target/netflix-git-cache-1.0-SNAPSHOT.jar`
