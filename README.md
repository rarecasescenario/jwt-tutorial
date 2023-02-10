### Spring Security JWT: How to secure your Spring Boot REST APIs with JSON Web Tokens
```
https://www.youtube.com/watch?v=KYNR5js2cXE&ab_channel=DanVega
https://github.com/danvega/jwt

git@github.com:rarecasescenario/jwt-tutorial.git


```

### Create a project using Spring Starter
```
Project: Gradle
Language: Java
Spring Boot: 3.0.2
1. Spring Web
2. OAuth2 Resource Server
3. Spring Configuration Processor
Java: 19
```


### Generating RSA Keys
```
1. Create a new folder /certs under /resources
2. Open terminal and generate private key
   openssl genrsa -out keypair.pem 2048
 
   Or, you can use putty to generate ssh keys
3. Generate a public key form the private key file
   openssl rsa -in keypair.pem -pubout public.pem
4. Generated encrypted
   openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out private.pem
   
```
