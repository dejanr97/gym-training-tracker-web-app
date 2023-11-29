# Spring Boot Gym Training Tracker Web App 
# About 

This is a demo project for practising Spring Boot + Thymeleaf. 

Used technologies:

### Backend
- Java 17
- [Spring Boot](https://spring.io/projects/spring-boot) 3 with [Spring Security](https://spring.io/projects/spring-security) and [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- MySQL
- [Lombok](https://projectlombok.org/) **(Please install it to be able to run the project)**
- [JUnit](https://junit.org/) and [Mockito](https://site.mockito.org/) for testing
- Maven

### Frontend
- HTML
- CSS
- JavaScript
- [Bootstrap 5](https://getbootstrap.com/)

## Screenshots
![Screenshot 1](https://i.postimg.cc/gcQPS7wt/s1.png)
![Screenshot 2](https://i.postimg.cc/rwPnMDW8/s2.png)  
![Screenshot 3](https://i.postimg.cc/wvZRBd2R/s3.png)  
![Screenshot 4](https://i.postimg.cc/02rX3GMt/s4.png)  
![Screenshot 5](https://i.postimg.cc/13BH3fqg/s5.png)  
![Screenshot 6](https://i.postimg.cc/4xxcwwYd/s6.png)

## How to run
You need to go to application.properties, follow this path **/gym-training-tracker/src/main/resources/application.properties** and adjust this part to your MySQL settings.

```spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/workoutsdb         
spring.datasource.username=root
spring.datasource.password=1337
```

After configuring everything, simply open your preferred web browser and navigate to http://localhost:8080/login. From there, proceed to the Register/Signup section to complete the registration process. Once registered, you're all set to log in.

## Contributing

Feel free to submit pull requests. For significant modifications, kindly initiate a discussion by opening an issue beforehand. Your contributions and ideas are highly appreciated.
