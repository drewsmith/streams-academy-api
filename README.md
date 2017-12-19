# streams-academy-api

The backend for http://streamsacademy.herokuapp.com (http://streams.academy)

```
mvn clean install
java -jar streams-academy-api-1.0.0.jar
```

```
curl -X POST -H "Content-Type: text/plain" http://localhost:8080/submit/map -d 'map(p->p.name)'

{"status":"SUCCESS","result":"[Pikachu, Charmander, Snorlax]"}
```
