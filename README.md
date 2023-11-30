# Spring Boot Gym Training Tracker Web App 
# About 

This is a demo project for practising CRUD with Spring Boot + Thymeleaf. 

Used technologies:

### Backend
- Java 17
- Spring Boot 3 with Spring Security and Spring Data JPA
- Spring MVC
- [Lombok](https://projectlombok.org/) **(Please install it to be able to run the project)**
- JUnit and Mockito for testing
- Maven

### Frontend
- HTML
- CSS
- JavaScript
- Bootstrap 5

- ### Database
- MySQL

## Screenshots
![Screenshot 1](https://i.postimg.cc/gcQPS7wt/s1.png)
![Screenshot 2](https://i.postimg.cc/rwPnMDW8/s2.png)  
![Screenshot 3](https://i.postimg.cc/wvZRBd2R/s3.png)  
![Screenshot 4](https://i.postimg.cc/02rX3GMt/s4.png)  
![Screenshot 5](https://i.postimg.cc/13BH3fqg/s5.png)  
![Screenshot 6](https://i.postimg.cc/4xxcwwYd/s6.png)

## How to run

After importing the files, you need to create a database with the name 'workoutsdb' or any preferred name of your choice. If you opt for a different name, make sure to adjust the first line of the code shown below. Additionally, you need to modify the username and password in the application.properties file, following this path: **/src/main/resources/application.properties.**

```
spring.datasource.url=jdbc:mysql://localhost:3306/workoutsdb         
spring.datasource.username=root
spring.datasource.password=1337
```
Now you need to run the server.


![Screenshot 7](https://i.postimg.cc/bwrzqV5b/Capture1.png)

After configuring everything, simply open your preferred web browser and navigate to http://localhost:8080/login. From there, proceed to the Register/Signup section to complete the registration process. Once registered, you're all set to log in.

## Contributing

Feel free to submit pull requests. For significant modifications, kindly initiate a discussion by opening an issue beforehand. Your contributions and ideas are highly appreciated.
