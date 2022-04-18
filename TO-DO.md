## Final laboratory
Create 2 microservices that are using all Spring Cloud Contracts learned:
1. Eureka
2. API Gateway
3. Config Server
4. Circuit Breaker


### USER-SERVICE
1. User class:
   - id: String
   - firstName: String
   - lastName: String
   - email: String
   - address: String
   - type: String (Car owner/mechanic)
2. CRUD User:
   - status **200** if ok
   - status **404** if id/user not found (update/delete)
   - status **409** if id/email already exists (create)

### APPOINTMENT-SERVICE:
1. Appointment class:
   - id: String
   - car: Car/String
   - startDate: LocalDateTime
   - endDate: LocalDateTime
   - carOwner: User
2. AppointmentSummary class:
   - id: String
   - appointment: Appointment
   - mechanic: User
   - comment: String
   - totalCost: Decimal
3. CRUD Appointment:
   - status **200** if ok
   - status **404** if Appointment id not found (update/delete)
   - status **409** if Appointment startDate is occupied by another Appointment (create)

Feel free to extend the application in any way you want.
