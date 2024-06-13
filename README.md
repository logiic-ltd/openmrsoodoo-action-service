# OpenMRSOdooActionService

A flexible Spring Boot service for performing configurable actions between OpenMRS and Odoo based on events and criteria. This service periodically polls OpenMRS and Odoo for new events, evaluates predefined criteria, and executes the appropriate actions.

## Features

- Poll OpenMRS and Odoo for new events (e.g., encounters, observations, admissions).
- Store and process event data.
- Evaluate criteria to determine if actions should be executed.
- Execute actions based on configurable criteria.
- Log execution details for auditing and debugging.

## Getting Started

### Prerequisites

- Java 17
- MySQL
- OpenMRS instance
- Odoo instance

### Installing

1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/OpenMRSOdooActionService.git
    ```

2. Navigate to the project directory:
    ```sh
    cd OpenMRSOdooActionService
    ```

3. Configure the application properties:
    - Open `src/main/resources/application.properties`
    - Set your database connection details:
      ```properties
      spring.datasource.url=jdbc:mysql://localhost:3306/openmrsoodooactionservice
      spring.datasource.username=yourusername
      spring.datasource.password=yourpassword
      spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

      spring.jpa.hibernate.ddl-auto=update
      spring.jpa.show-sql=true
      spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
      ```

4. Build and run the application:
    ```sh
    ./mvnw spring-boot:run
    ```

### Usage

1. **Define Actions and Criteria**:
    - Actions are defined in the `actions` table.
    - Criteria are defined in the `criteria` table and are associated with actions.

2. **Polling and Processing Events**:
    - The application periodically polls OpenMRS and Odoo for new events.
    - New events are inserted into the `events` table.
    - Criteria are evaluated to determine if actions should be executed.

3. **Executing Actions**:
    - If criteria are met, the corresponding actions are executed.
    - Execution details are logged in the `execution_logs` table.

### Contributing

Please read `CONTRIBUTING.md` for details on our code of conduct and the process for submitting pull requests.

### License

This project is licensed under the MIT License - see the `LICENSE.md` file for details.