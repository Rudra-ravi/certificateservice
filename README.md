# Certificate Service

This project is a Spring Boot application that provides a RESTful API for managing certificates. It allows you to create, read, update, and delete certificates, as well as retrieve certificates by student name.

## Setup Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/Rudra-ravi/certificateservice.git
   cd certificateservice
   ```

2. Build the project using Maven:
   ```bash
   ./mvnw clean install
   ```

3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

4. The application will start on `http://localhost:8080`.

## Usage Examples

### Create a Certificate
```bash
curl -X POST -H "Content-Type: application/json" -d '{
  "studentName": "John Doe",
  "courseName": "Spring Boot",
  "issueDate": "2023-09-01",
  "grade": "A"
}' http://localhost:8080/api/certificates
```

### Get All Certificates
```bash
curl http://localhost:8080/api/certificates
```

### Get Certificate by ID
```bash
curl http://localhost:8080/api/certificates/1
```

### Update a Certificate
```bash
curl -X PUT -H "Content-Type: application/json" -d '{
  "studentName": "John Doe",
  "courseName": "Spring Boot",
  "issueDate": "2023-09-01",
  "grade": "A+"
}' http://localhost:8080/api/certificates/1
```

### Delete a Certificate
```bash
curl -X DELETE http://localhost:8080/api/certificates/1
```

## API Endpoints

- `GET /api/certificates`: Retrieve all certificates
- `GET /api/certificates/{id}`: Retrieve a certificate by ID
- `POST /api/certificates`: Create a new certificate
- `PUT /api/certificates/{id}`: Update an existing certificate
- `DELETE /api/certificates/{id}`: Delete a certificate
- `GET /api/certificates/student/{studentName}`: Retrieve certificates by student name

## Contributing

Contributions are welcome! Please follow these steps to contribute:

1. Fork the repository
2. Create a new branch (`git checkout -b feature-branch`)
3. Make your changes
4. Commit your changes (`git commit -am 'Add new feature'`)
5. Push to the branch (`git push origin feature-branch`)
6. Create a new Pull Request

