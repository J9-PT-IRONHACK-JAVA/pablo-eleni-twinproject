 ````
                     _ _ _        
                   |__   __| (_)     (_)     | (_) |       
                      | |_ __ ___   ___  __ _| |_| |_ _   _
                      | | '__| \ \ / / |/ _` | | | __| | | |
                      | | |  | |\ V /| | (_| | | | |_| |_| |
                      |_|_|  |_| \_/ |_|\__,_|_|_|\__|\__, |
                                                       __/ |
                                                      |___/
````
---
# Welcome to Triviality!

A fun game to test your knowldge and find out if you really know your stuff or you're a knob!
Based on jService.io API, providing over 30,000 trivia questions to sharpen your mind.

Soft made with Java 17, SpringBoot Framework 2.7.6 and MySql Database.

For this version, the soft is managed with Console Comands.

## How to run?

First, you need to configure your Database, in file 'application.properties':

```properties
#datasource configurations
spring.datasource.url=jdbc:mysql://localhost:3306/j9_trivia
spring.datasource.username=[insert username of your local database]
spring.datasource.password=[insert password of your local database]
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

  # show SQL
spring.jpa.show-sql=false
  # DDL generation
spring.jpa.generate-ddl=true
```
Run the file TwinProjectApplication with your editor, and enjoy! 

To see all the available commands type 'help' in the 'Insert command:' line.

Available game modes:
* **Play alone**:  the player creates his persona or chooses one already created and a game of five rounds is launched where each time the player gets to choose a category
* **Two players**: both players create their personas or choose one already created
---

Developers:

    - Pablo Martinez
    - Eleni Taki
---

Links:

- Questions API: <a href="https://jservice.io/" target="jService.io"></a>


