# Kayako Java API Wrapper
Easily interact with your [Kayako](http://kayako.com) API without messing about with XML.


```java
// Initialize our client
KayakoClient client = new KayakoClient("2395ed87-14d4-ce44-59c2-20e37c2ced1a",
				"OGExNTg1NjUtYjJjYi1iZTY0LWNkNjMtMDk4MDdiYjJmNTA1OTg5NzUwMGItNjc4Ni1iMTM0LTFkMDUtZDY4NWE2ZjQ5YTc4",
                "my.kayako.com");

// Suddenly, departments
List<Department> departments = client.departments().list();

// Maybe you just want one?
Department d = client.departments().get(5);

// Fetch the open tickets in our department
List<BasicTicket> tickets = client.tickets().forDepartment(5);

// Be a little more specific with your request
List<BasicTicket> filteredTickets = client.tickets().forDepartments(Sets.newHashSet(1, 2, 3), 
				DepartmentTicketRequest.where()
					.ticketStatusId(1)
					.ownerStaffId(9)
					.userId(7907, 65));

```

## Kayako Prerequisites
To start using this API wrapper you will need to enable the Rest API on your installation.

From the Kayako documentation:
> To use Kayako REST API, first you need to enable it in your Kayako installation. To enable the API, login to Admin Control Panel and in Options panel on the right choose REST API, and then Settings. Next, set Enable API Interface to Yes and confirm the change by clicking Update button.

*Please note: The REST API doesn't have any fine-grain access control. If a client has the API key, they can do almost anything with your installation. Please do be careful.*

## Including this project in your code
As this project is still young, there aren't any prebuilt jars available. You will need to clone this repo and build it yourself.

You will need:

 * [Gradle](http://www.gradle.org/)
 * A working internet connection
 
To build the project, open your terminal in the project directory and run:

	gradle jar
	
The build jar will be location in `build/libs/kayako-api.jar`. Go nuts and include this in your own project.

In the future a maven repository will be added.

## What's done

| API | Fetch | Edit | Delete |
| --- | ----- | ---- | ------ |
| Department | Yes | - | - |
| Ticket | Yes | Yes | Yes |
| CustomField | - | - | - |
| KnowledgebaseArticle | - | - | -  |
| KnowledgebaseAttachment | - | - | - |
| KnowledgebaseCategory | - | - | - |
| KnowledgebaseComment | - | - | - |
| NewsCategory | - | - | - |
| NewsComment | - | - | - |
| NewsItem | - | - | - |
| NewsSubscriber | - | - | - |
| Staff | - | - | - |
| StaffGroup | - | - | - |
| TicketAttachment | Yes | Yes | Yes |
| TicketCount | - | - | - |
| TicketCustomField | Yes | Yes | Yes |
| TicketNote | Yes | Yes | Yes |
| TicketPost | - | - | - |
| TicketPriority | - | - | - |
| TicketSearch | - | - | - |
| TicketStatus | Yes | NA | NA |
| TicketTimeTrack | - | - | - |
| TicketType | - | - | - |
| TroubleshooterAttachment | - | - | - |
| TroubleshooterCategory | - | - | - |
| TroubleshooterComment | - | - | - |
| TroubleshooterStep | - | - | - |
| User | - | - | - |
| UserGroup | - | - | - |
| UserOrganization | - | - | - |
| UserSearch | - | - | - |

If you're a developer looking to integrate with Kayako, consider forking a extending this library. Refer to the [`DepartmentConnector`](https://github.com/penguinboy/kayako-api/blob/master/src/main/java/org/penguin/kayako/DepartmentConnector.java) as a reference. Pull requests will be accepted, provided they also include unit tests (see [existing unit tests](https://github.com/penguinboy/kayako-api/tree/master/src/test/java/org/penguin/kayako)) and fit with the current project structure.
