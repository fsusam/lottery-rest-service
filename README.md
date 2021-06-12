# lottery-rest-service
Poppulo interview home project

# API doc with swagger
http://localhost:8080/swagger-ui/#/ticket-controller


# Create ticket without line parameter
### A Ticket with only one line is created
Method = POST
http://localhost:8080/tickets

# Create ticket with line parameter
### A Ticket with 10 line is created
Method = POST
http://localhost:8080/tickets?line=10

# Get all tickets
### All tickets are returned
Method = GET
http://localhost:8080/tickets

# Get the ticket by id
### The ticket is returned by id
Method = GET
http://localhost:8080/tickets/<ticket_id>

# Get the ticket status by id
### The status of the ticket is returned by id
Method = GET
http://localhost:8080/tickets/<ticket_id>/status

# Add lines to ticket as per line parameter
### The lines are generated and added to ticket
Method = PUT
http://localhost:8080/tickets/<ticket_id>?line=2
