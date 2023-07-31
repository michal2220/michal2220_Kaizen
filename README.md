# Kaizen Management System - Backend


The Kaizen Management System is a comprehensive web application designed to facilitate continuous improvement within organizations. This backend component is built using Java and the Spring Boot framework, providing a robust and efficient foundation for managing user feedback and improvement suggestions, also known as kaizens, as well as the rewarding process.



Technologies Used:
- Java: The backend is implemented using the Java programming language.
- Spring Boot: The application utilizes the Spring Boot framework for rapid development and easier configuration.
- Spring Data JPA: It integrates Spring Data JPA for convenient database interactions and data access.
- AspectJ: The AOP functionalities are implemented using AspectJ to handle cross-cutting concerns.
- MySQL: The application uses MySQL as the database to store user, kaizen, and reward information.
- JUnit: JUnit is used for writing unit tests to ensure the correctness of the application's components.
- Mockito: Mockito is used for creating mock objects to facilitate the testing process.


User Management: The backend provides functionalities for creating, updating, and deleting users. It also allows users to be retrieved based on their IDs or last names, as well as filtered by the number of kaizens they have submitted.

Kaizen Management: Users can submit kaizens, which include problem descriptions and suggested solutions. The application supports various operations such as retrieving kaizens by ID, searching for kaizens older than a specified date, and getting kaizens created by a specific user.

Reward Management: Rewards can be created, updated, and deleted through the application. Additionally, users can retrieve rewards based on their IDs or names and filter rewards by price range.

Translation Functionality: The system offers the capability to translate kaizen problem descriptions into different languages using an external translator component.

Scheduler: Application contains built in scheduler which sends reminder emails every Monday. Email contains info regarding uncompleted amount of Kaizens. It also sends a Dad Joke to brighten up Mondays :) Jokes are also translated to Polish language via built in translator.

Logging and Auditing: AspectJ is utilized to implement logging and auditing functionalities, which record relevant events and actions performed in the system. This ensures traceability and enhances security.

Frontend:
The frontend of the Kaizen Management System is built using Vaadin, a powerful Java framework for building modern web applications with ease. Vaadin allows for seamless integration between the frontend and backend, enabling a smooth and responsive user interface. The Vaadin-based frontend communicates with the backend API to provide users with an intuitive and user-friendly experience for managing kaizens, rewards, and user information.

The frontend repository for the Kaizen Management System can be found at the following link:
https://github.com/michal2220/michal2220_Kaizen_frontend

With both the backend and frontend components seamlessly integrated, the Kaizen Management System offers organizations a powerful tool to streamline continuous improvement processes, promote employee engagement, and drive positive organizational change.






Features:

User Controller - API Endpoints:
- GET /v1/users/userId/{userId}: Get user details by ID.
- GET /v1/users/userLastname/{lastname}: Get users by last name.
- GET /v1/users/kaizenQuantity/{kaizenCount}: Get users by kaizen count.
- GET /v1/users/lessThen/{kaizenCount}: Get users with less than a specific kaizen count.
- GET /v1/users/moreThen/{kaizenCount}: Get users with more than a specific kaizen count.
- GET /v1/users/brigade/{brigade}: Get users by brigade.
- GET /v1/users: Get all users.
- POST /v1/users: Create a new user.
- PUT /v1/users: Update an existing user.
- DELETE /v1/users/{userId}: Delete a user by ID.

Kaizen Controller - API Endpoints:
- GET /v1/kaizens/kaizenId/{kaizenId}: Get kaizen details by ID.
- GET /v1/kaizens: Get all kaizens.
- GET /v1/kaizens/olderThen/{date}: Get kaizens older than a specific date.
- GET /v1/kaizens/creator?name={name}&lastname={lastname}: Get kaizens created by a specific user.
- GET /v1/kaizens/translate/{kaizenId}: Translate the problem description of a kaizen by ID.
- POST /v1/kaizens: Create a new kaizen.
- PUT /v1/kaizens: Update an existing kaizen.
- PUT /v1/kaizens/markAsCompleted/{kaizenId}: Mark a kaizen as completed with a specified completion date.
- DELETE /v1/kaizens/{kaizenId}: Delete a kaizen by ID.

Reward Controller - API Endpoints:
- GET /v1/rewards/rewardId/{rewardId}: Get reward details by ID.
- GET /v1/rewards/rewardName/{name}: Get rewards by name.
- GET /v1/rewards/rewardsPrice/{price}: Get rewards by price.
- GET /v1/rewards/moreExpensiveThen/{price}: Get rewards more expensive than a specified price.
- GET /v1/rewards/lessExpensiveThen/{price}: Get rewards less expensive than a specified price.
- GET /v1/rewards: Get all rewards.
- POST /v1/rewards: Create a new reward.
- PUT /v1/rewards: Update an existing reward.
- DELETE /v1/rewards/{rewardId}: Delete a reward by ID.

The application also incorporates Aspect-Oriented Programming (AOP) to log important events and actions within the system. The following events are logged:
User-related events such as creating, updating, and deleting users.
Kaizen-related events such as creating, updating, and deleting kaizens, as well as marking them as completed.
Reward-related events such as creating, updating, and deleting rewards.



To run the Kaizen Management System application, it is required to set up a MySQL database with the following configuration:

1. Database URL:
   ```
   jdbc:mysql://localhost:3306/Kaizen_database?serverTimezone=Europe/Warsaw&useSSL=False&allowPublicKeyRetrieval=true
   ```

2. Username:
   ```
   kaizen_user
   ```

3. Password:
   ```
   kodilla_password
   ```
Port for using this app is: 8080 on localhost, for example url:

   ```
   http://localhost:8080/v1/users
   ```
will show a list of all available Users.
