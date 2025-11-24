# System Architecture Documentation

## Architecture Overview

The Student Grade Management System follows a **layered architecture** pattern with clear separation of concerns. The system is organized into multiple layers, each with specific responsibilities.

## Architecture Layers

### 1. Presentation Layer (Controller)

- **Location**: `com.grademanagement.controller`
- **Components**:
  - `UserController`
  - `GradeController`
  - `ReportController`
- **Responsibilities**:
  - Handle user interactions
  - Route requests to appropriate services
  - Format responses for display
  - Input/output handling

### 2. Business Logic Layer (Service)

- **Location**: `com.grademanagement.service`
- **Components**:
  - `UserService`
  - `GradeService`
  - `ReportService`
- **Responsibilities**:
  - Implement business rules
  - Coordinate between controllers and repositories
  - Perform validations
  - Calculate GPAs and statistics
  - Generate reports

### 3. Data Access Layer (Repository)

- **Location**: `com.grademanagement.repository`
- **Components**:
  - `UserRepository`
  - `GradeRepository`
- **Responsibilities**:
  - Data persistence operations
  - File I/O operations
  - Data serialization/deserialization
  - Data retrieval and storage

### 4. Model Layer

- **Location**: `com.grademanagement.model`
- **Components**:
  - `User`, `Student`, `Teacher`, `Admin`
  - `Grade`, `Subject`
- **Responsibilities**:
  - Represent domain entities
  - Encapsulate data and behavior
  - Business logic within entities (e.g., GPA calculation)

### 5. Utility Layer

- **Location**: `com.grademanagement.util`
- **Components**:
  - `Validator`
  - `Logger`
  - `FileHandler`
- **Responsibilities**:
  - Reusable utility functions
  - Input validation
  - Logging operations
  - File operations

### 6. Exception Layer

- **Location**: `com.grademanagement.exception`
- **Components**:
  - `InvalidInputException`
  - `UserNotFoundException`
  - `GradeNotFoundException`
- **Responsibilities**:
  - Custom exception definitions
  - Error type classification

## System Architecture Diagram

```
┌─────────────────────────────────────────────────────────┐
│                    Presentation Layer                    │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐ │
│  │UserController│  │GradeController│  │ReportController││
│  └──────┬───────┘  └──────┬───────┘  └──────┬───────┘ │
└─────────┼──────────────────┼──────────────────┼─────────┘
          │                  │                  │
┌─────────┼──────────────────┼──────────────────┼─────────┐
│         │                  │                  │          │
│  ┌──────▼──────┐  ┌───────▼──────┐  ┌───────▼──────┐  │
│  │UserService  │  │GradeService   │  │ReportService │  │
│  └──────┬──────┘  └───────┬───────┘  └───────┬───────┘  │
│         │                  │                  │          │
└─────────┼──────────────────┼──────────────────┼─────────┘
          │                  │                  │
┌─────────┼──────────────────┼──────────────────┼─────────┐
│         │                  │                  │          │
│  ┌──────▼──────┐  ┌───────▼──────┐          │          │
│  │UserRepository│ │GradeRepository│          │          │
│  └──────┬───────┘ └───────┬───────┘          │          │
└─────────┼──────────────────┼──────────────────┼─────────┘
          │                  │                  │
┌─────────┼──────────────────┼──────────────────┼─────────┐
│         │                  │                  │          │
│  ┌──────▼──────┐  ┌───────▼──────┐  ┌───────▼──────┐  │
│  │   Validator │  │    Logger     │  │  FileHandler  │  │
│  └─────────────┘  └───────────────┘  └───────────────┘  │
└──────────────────────────────────────────────────────────┘
          │                  │                  │
┌─────────┼──────────────────┼──────────────────┼─────────┐
│         │                  │                  │          │
│  ┌──────▼──────┐  ┌───────▼──────┐  ┌───────▼──────┐  │
│  │   users.txt │  │  grades.txt   │  │ subjects.txt │  │
│  └─────────────┘  └───────────────┘  └───────────────┘  │
└──────────────────────────────────────────────────────────┘
```

## Design Patterns Used

### 1. Repository Pattern

- **Purpose**: Abstract data access logic
- **Implementation**: `UserRepository` and `GradeRepository` encapsulate all file operations
- **Benefits**:
  - Separation of concerns
  - Easy to switch storage mechanism
  - Testable components

### 2. Service Layer Pattern

- **Purpose**: Encapsulate business logic
- **Implementation**: Service classes contain business rules and validations
- **Benefits**:
  - Reusable business logic
  - Centralized validation
  - Easy to maintain

### 3. MVC (Model-View-Controller)

- **Purpose**: Separate presentation, business logic, and data
- **Implementation**:
  - **Model**: Entity classes (User, Grade, Subject)
  - **View**: Console output in Main.java
  - **Controller**: Controller classes
- **Benefits**:
  - Clear separation of concerns
  - Easy to modify UI without affecting business logic

### 4. Inheritance

- **Purpose**: Code reuse and polymorphism
- **Implementation**: `Student`, `Teacher`, `Admin` extend `User`
- **Benefits**:
  - Shared functionality
  - Type safety
  - Polymorphic behavior

## Data Flow

### User Registration Flow

```
User Input → UserController → UserService → Validation → UserRepository → File Storage
```

### Grade Entry Flow

```
Teacher Input → GradeController → GradeService → Validation → GradeRepository → File Storage
```

### Report Generation Flow

```
User Request → ReportController → ReportService → GradeRepository/UserRepository → Data Processing → Formatted Report
```

## Component Interactions

### User Management

1. `Main` calls `UserController.login()`
2. `UserController` calls `UserService.login()`
3. `UserService` calls `UserRepository.authenticate()`
4. `UserRepository` reads from file and validates credentials
5. Result flows back through layers

### Grade Management

1. `Main` calls `GradeController.addGrade()`
2. `GradeController` calls `GradeService.addGrade()`
3. `GradeService` validates input using `Validator`
4. `GradeService` calls `GradeRepository.addGrade()`
5. `GradeRepository` saves to file
6. `Logger` records the operation

### Report Generation

1. `Main` calls `ReportController.generateStudentTranscript()`
2. `ReportController` calls `ReportService.generateStudentTranscript()`
3. `ReportService` retrieves data from repositories
4. `ReportService` processes and formats data
5. Formatted report returned to user

## Data Storage

### File Structure

- **users.txt**: Stores all user information
- **grades.txt**: Stores all grade records
- **subjects.txt**: Stores all subject information
- **system.log**: Stores system logs

### Data Format

- **Delimiter**: Pipe character (`|`)
- **Encoding**: UTF-8
- **Format**: One record per line

Example user record:

```
user1|username|password|email@example.com|STUDENT|STU001|John Doe|CS|2
```

Example grade record:

```
GRD001|STU001|SUB001|85.5|2024-01-15
```

## Security Considerations

1. **Input Validation**: All inputs validated before processing
2. **Password Storage**: Currently stored in plain text (for simplicity; should be hashed in production)
3. **Access Control**: Role-based access enforced at controller level
4. **Error Handling**: Exceptions caught and logged without exposing system details

## Scalability Considerations

### Current Limitations

- File-based storage limits concurrent access
- In-memory data structures for all records
- Single-threaded execution

### Future Enhancements

- Database integration for better scalability
- Caching mechanisms for frequently accessed data
- Multi-threaded support for concurrent users
- Connection pooling for database operations

## Testing Strategy

1. **Unit Tests**: Test individual components in isolation
2. **Integration Tests**: Test component interactions
3. **System Tests**: Test complete workflows
4. **Validation Tests**: Test input validation logic

## Deployment

The system is designed as a standalone Java application:

- No external dependencies (except JUnit for testing)
- File-based storage (no database setup required)
- Console-based interface (no web server needed)
- Can run on any system with Java installed
