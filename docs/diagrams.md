# UML Diagrams and Design Documentation

## Use Case Diagram

```
                    Student Grade Management System
                              │
        ┌─────────────────────┼─────────────────────┐
        │                     │                     │
        ▼                     ▼                     ▼
    ┌────────┐          ┌──────────┐          ┌─────────┐
    │ Student│          │ Teacher  │          │  Admin  │
    └───┬────┘          └────┬─────┘          └────┬────┘
        │                    │                     │
        │                    │                     │
    ┌───▼────────────────────▼─────────────────────▼───┐
    │                                                    │
    │  ┌────────────────────────────────────────────┐  │
    │  │         Login / Register                    │  │
    │  └────────────────────────────────────────────┘  │
    │                                                    │
    │  ┌────────────────────────────────────────────┐  │
    │  │  Student Use Cases:                        │  │
    │  │  • View My Grades                          │  │
    │  │  • Generate Transcript                      │  │
    │  │  • View GPA                                │  │
    │  └────────────────────────────────────────────┘  │
    │                                                    │
    │  ┌────────────────────────────────────────────┐  │
    │  │  Teacher Use Cases:                        │  │
    │  │  • Add Grade                                │  │
    │  │  • Update Grade                            │  │
    │  │  • View Subjects                           │  │
    │  │  • Generate Class Statistics               │  │
    │  └────────────────────────────────────────────┘  │
    │                                                    │
    │  ┌────────────────────────────────────────────┐  │
    │  │  Admin Use Cases:                          │  │
    │  │  • Add Subject                             │  │
    │  │  • View All Users                          │  │
    │  │  • Generate Department Report              │  │
    │  │  • View Top Students                       │  │
    │  └────────────────────────────────────────────┘  │
    │                                                    │
    └────────────────────────────────────────────────────┘
```

## Class Diagram

```
┌─────────────────────────────────────────────────────────────┐
│                         User                                │
├─────────────────────────────────────────────────────────────┤
│ - userId: String                                            │
│ - username: String                                          │
│ - password: String                                          │
│ - email: String                                             │
│ - role: String                                              │
├─────────────────────────────────────────────────────────────┤
│ + getUserId(): String                                       │
│ + setUserId(String): void                                  │
│ + getUsername(): String                                     │
│ + setUsername(String): void                                │
│ + getPassword(): String                                     │
│ + setPassword(String): void                                │
│ + getEmail(): String                                        │
│ + setEmail(String): void                                   │
│ + getRole(): String                                         │
│ + setRole(String): void                                    │
│ + toString(): String                                       │
└─────────────────────────────────────────────────────────────┘
                    ▲
                    │
        ┌───────────┼───────────┐
        │           │           │
        │           │           │
┌───────▼───┐  ┌────▼────┐  ┌──▼──────┐
│  Student  │  │ Teacher │  │  Admin  │
├───────────┤  ├─────────┤  ├─────────┤
│-studentId │  │-teacherId│ │-adminId │
│-fullName  │  │-fullName │ │-fullName│
│-department│  │-department││         │
│-year      │  │-subjects  ││         │
│-grades    │  │Taught     ││         │
├───────────┤  ├─────────┤  ├─────────┤
│+getStudentId│ │+getTeacherId│+getAdminId│
│+calculateGPA││+addSubject  ││         │
└───────────┘  └─────────┘  └─────────┘

┌─────────────────────────────────────────────────────────────┐
│                        Subject                               │
├─────────────────────────────────────────────────────────────┤
│ - subjectId: String                                         │
│ - subjectName: String                                       │
│ - department: String                                        │
│ - credits: int                                              │
│ - teacherId: String                                         │
├─────────────────────────────────────────────────────────────┤
│ + getSubjectId(): String                                    │
│ + getSubjectName(): String                                  │
│ + getCredits(): int                                         │
└─────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────┐
│                         Grade                               │
├─────────────────────────────────────────────────────────────┤
│ - gradeId: String                                           │
│ - studentId: String                                         │
│ - subject: Subject                                          │
│ - marks: double                                             │
│ - letterGrade: String                                       │
│ - dateAssigned: LocalDate                                   │
├─────────────────────────────────────────────────────────────┤
│ + getGradeId(): String                                      │
│ + getMarks(): double                                        │
│ + getLetterGrade(): String                                 │
│ + getGradePoints(): double                                  │
│ + calculateLetterGrade(double): String                      │
└─────────────────────────────────────────────────────────────┘
        │
        │ uses
        ▼
┌─────────────────────────────────────────────────────────────┐
│                      Subject                                │
└─────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────┐
│                    UserService                              │
├─────────────────────────────────────────────────────────────┤
│ - userRepository: UserRepository                           │
├─────────────────────────────────────────────────────────────┤
│ + registerStudent(...): Student                            │
│ + registerTeacher(...): Teacher                            │
│ + login(String, String): User                              │
│ + getUserById(String): User                                │
│ + updateUser(User): void                                   │
│ + deleteUser(String): void                                 │
└─────────────────────────────────────────────────────────────┘
        │
        │ uses
        ▼
┌─────────────────────────────────────────────────────────────┐
│                   UserRepository                            │
├─────────────────────────────────────────────────────────────┤
│ - users: Map<String, User>                                 │
├─────────────────────────────────────────────────────────────┤
│ + addUser(User): void                                      │
│ + getUserById(String): User                                │
│ + getUserByUsername(String): User                          │
│ + authenticate(String, String): User                       │
│ + updateUser(User): void                                   │
│ + deleteUser(String): void                                 │
│ - loadUsers(): void                                        │
│ - saveUsers(): void                                        │
└─────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────┐
│                    GradeService                             │
├─────────────────────────────────────────────────────────────┤
│ - gradeRepository: GradeRepository                         │
├─────────────────────────────────────────────────────────────┤
│ + addSubject(...): Subject                                 │
│ + addGrade(...): Grade                                     │
│ + getGradesByStudentId(String): List<Grade>                │
│ + updateGrade(String, double): void                        │
│ + deleteGrade(String): void                                │
└─────────────────────────────────────────────────────────────┘
        │
        │ uses
        ▼
┌─────────────────────────────────────────────────────────────┐
│                   GradeRepository                           │
├─────────────────────────────────────────────────────────────┤
│ - grades: Map<String, Grade>                               │
│ - subjects: Map<String, Subject>                           │
├─────────────────────────────────────────────────────────────┤
│ + addSubject(Subject): void                                │
│ + addGrade(Grade): void                                    │
│ + getGradesByStudentId(String): List<Grade>                │
│ + updateGrade(Grade): void                                 │
│ + deleteGrade(String): void                                │
│ - loadSubjects(): void                                     │
│ - loadGrades(): void                                       │
│ - saveSubjects(): void                                     │
│ - saveGrades(): void                                       │
└─────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────┐
│                   ReportService                            │
├─────────────────────────────────────────────────────────────┤
│ - gradeRepository: GradeRepository                         │
│ - userRepository: UserRepository                          │
├─────────────────────────────────────────────────────────────┤
│ + generateStudentTranscript(String): String                │
│ + generateClassStatistics(String): String                 │
│ + generateDepartmentReport(String): String                 │
│ + getTopPerformingStudents(int): List<String>             │
└─────────────────────────────────────────────────────────────┘
```

## Sequence Diagram: User Login

```
┌──────┐    ┌──────────────┐    ┌─────────────┐    ┌─────────────────┐
│ Main │    │UserController│    │ UserService │    │UserRepository  │
└───┬──┘    └──────┬───────┘    └──────┬──────┘    └────────┬────────┘
    │              │                   │                    │
    │ login()      │                   │                    │
    │─────────────>│                   │                    │
    │              │ login()            │                    │
    │              │───────────────────>│                    │
    │              │                   │ authenticate()      │
    │              │                   │───────────────────>│
    │              │                   │                    │ getUserByUsername()
    │              │                   │                    │───────────────────>│
    │              │                   │                    │<───────────────────│
    │              │                   │                    │ validate password │
    │              │                   │<───────────────────│                    │
    │              │<──────────────────│                    │                    │
    │<─────────────│                   │                    │                    │
    │              │                   │                    │                    │
```

## Sequence Diagram: Add Grade

```
┌──────┐    ┌──────────────┐    ┌─────────────┐    ┌─────────────────┐    ┌──────────────┐
│ Main │    │GradeController│   │GradeService │    │GradeRepository │    │  Validator   │
└───┬──┘    └──────┬───────┘    └──────┬──────┘    └────────┬────────┘    └──────┬───────┘
    │              │                   │                    │                    │
    │ addGrade()   │                   │                    │                    │
    │─────────────>│                   │                    │                    │
    │              │ addGrade()         │                    │                    │
    │              │───────────────────>│                    │                    │
    │              │                   │ validateMarks()    │                    │
    │              │                   │─────────────────────────────────────────>│
    │              │                   │<─────────────────────────────────────────│
    │              │                   │ getSubjectById()   │                    │
    │              │                   │───────────────────>│                    │
    │              │                   │<───────────────────│                    │
    │              │                   │ create Grade       │                    │
    │              │                   │ addGrade()         │                    │
    │              │                   │───────────────────>│                    │
    │              │                   │                    │ save to file       │
    │              │                   │<───────────────────│                    │
    │              │<──────────────────│                    │                    │
    │<─────────────│                   │                    │                    │
    │              │                   │                    │                    │
```

## Sequence Diagram: Generate Transcript

```
┌──────┐    ┌──────────────┐    ┌─────────────┐    ┌─────────────────┐    ┌─────────────────┐
│ Main │    │ReportController│  │ReportService│    │GradeRepository  │    │UserRepository   │
└───┬──┘    └──────┬───────┘    └──────┬──────┘    └────────┬────────┘    └────────┬────────┘
    │              │                   │                    │                    │
    │ generateTranscript()            │                    │                    │
    │─────────────>│                   │                    │                    │
    │              │ generateStudentTranscript()            │                    │
    │              │───────────────────>│                   │                    │
    │              │                   │ getUserById()      │                    │
    │              │                   │─────────────────────────────────────────>│
    │              │                   │<─────────────────────────────────────────│
    │              │                   │ getGradesByStudentId()                  │
    │              │                   │───────────────────>│                    │
    │              │                   │<───────────────────│                    │
    │              │                   │ calculate GPA      │                    │
    │              │                   │ format report       │                    │
    │              │<──────────────────│                    │                    │
    │<─────────────│                   │                    │                    │
    │              │                   │                    │                    │
```

## Process Flow Diagram: Complete Grade Entry Workflow

```
                    START
                     │
                     ▼
            ┌────────────────┐
            │ Teacher Login   │
            └────────┬───────┘
                     │
                     ▼
            ┌────────────────┐
            │ Select Subject │
            └────────┬───────┘
                     │
                     ▼
            ┌────────────────┐
            │ Enter Student  │
            │     Details    │
            └────────┬───────┘
                     │
                     ▼
            ┌────────────────┐
            │  Enter Marks   │
            │    (0-100)     │
            └────────┬───────┘
                     │
                     ▼
            ┌────────────────┐
            │   Validate     │
            │    Input       │
            └────────┬───────┘
                     │
            ┌────────┴────────┐
            │                 │
            ▼                 ▼
      ┌─────────┐      ┌──────────┐
      │ Valid   │      │ Invalid  │
      └────┬────┘      └────┬─────┘
           │                │
           │                │
           ▼                │
    ┌──────────────┐        │
    │ Calculate    │        │
    │ Letter Grade │        │
    └──────┬───────┘        │
           │                │
           ▼                │
    ┌──────────────┐        │
    │ Save to File │        │
    └──────┬───────┘        │
           │                │
           │                │
           └────────┬───────┘
                    │
                    ▼
            ┌────────────────┐
            │  Log Operation │
            └────────┬───────┘
                     │
                     ▼
            ┌────────────────┐
            │   SUCCESS      │
            └────────────────┘
                     │
                     ▼
                    END
```

## Database/Storage Design

### Entity Relationship Diagram (Conceptual)

```
┌─────────────┐         ┌─────────────┐
│    User     │         │   Subject   │
├─────────────┤         ├─────────────┤
│ userId (PK) │         │subjectId(PK)│
│ username    │         │subjectName  │
│ password    │         │ department  │
│ email       │         │ credits     │
│ role        │         │ teacherId   │
└──────┬──────┘         └──────┬──────┘
       │                       │
       │                       │
       │              ┌─────────▼─────────┐
       │              │      Grade        │
       │              ├───────────────────┤
       │              │ gradeId (PK)      │
       │              │ studentId (FK)    │
       │              │ subjectId (FK)    │
       │              │ marks             │
       │              │ letterGrade       │
       │              │ dateAssigned      │
       │              └───────────────────┘
       │
       │
┌──────▼──────┐
│   Student   │
├─────────────┤
│ studentId   │
│ fullName    │
│ department  │
│ year        │
└─────────────┘

┌─────────────┐
│   Teacher   │
├─────────────┤
│ teacherId   │
│ fullName    │
│ department  │
└─────────────┘

┌─────────────┐
│    Admin    │
├─────────────┤
│ adminId     │
│ fullName    │
└─────────────┘
```

### File Storage Schema

**users.txt Format:**

```
userId|username|password|email|role|specificId|fullName|department|year(if student)
```

**subjects.txt Format:**

```
subjectId|subjectName|department|credits|teacherId
```

**grades.txt Format:**

```
gradeId|studentId|subjectId|marks|dateAssigned
```

## Component Diagram

```
┌─────────────────────────────────────────────────────────────┐
│                    Application Layer                         │
│  ┌──────────────────────────────────────────────────────┐   │
│  │                      Main.java                        │   │
│  │  (User Interface & Menu Navigation)                   │   │
│  └──────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────┘
                            │
                            ▼
┌─────────────────────────────────────────────────────────────┐
│                    Controller Layer                          │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐     │
│  │UserController│  │GradeController│  │ReportController│    │
│  └──────────────┘  └──────────────┘  └──────────────┘     │
└─────────────────────────────────────────────────────────────┘
                            │
                            ▼
┌─────────────────────────────────────────────────────────────┐
│                     Service Layer                            │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐     │
│  │ UserService  │  │ GradeService │  │ReportService │     │
│  └──────────────┘  └──────────────┘  └──────────────┘     │
└─────────────────────────────────────────────────────────────┘
                            │
                            ▼
┌─────────────────────────────────────────────────────────────┐
│                    Repository Layer                          │
│  ┌──────────────┐  ┌──────────────┐                        │
│  │UserRepository│  │GradeRepository│                        │
│  └──────────────┘  └──────────────┘                        │
└─────────────────────────────────────────────────────────────┘
                            │
                            ▼
┌─────────────────────────────────────────────────────────────┐
│                    Utility Layer                             │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐     │
│  │  Validator   │  │   Logger     │  │ FileHandler  │     │
│  └──────────────┘  └──────────────┘  └──────────────┘     │
└─────────────────────────────────────────────────────────────┘
                            │
                            ▼
┌─────────────────────────────────────────────────────────────┐
│                    Data Storage                              │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐     │
│  │  users.txt   │  │  grades.txt  │  │ subjects.txt │     │
│  └──────────────┘  └──────────────┘  └──────────────┘     │
│  ┌──────────────┐                                          │
│  │ system.log   │                                          │
│  └──────────────┘                                          │
└─────────────────────────────────────────────────────────────┘
```
