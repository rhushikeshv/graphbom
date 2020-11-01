# Use cases :
# Import BOM
![Alt text](/BOM_Import.png)

# Search and Display BOM structure
![Alt text](/BOM_Structure.png)


# Part Search
![Alt text](/Part_Search.png)

# Part Management 
![Alt text](/Part_Management.png)

# Getting Started

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing Data with Neo4j](https://spring.io/guides/gs/accessing-node-neo4j/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

Build the ng app using the command from 'web' directory 

# ng build --base-href=app
ng build --watch

Run the neo4j db using the command
neo4j start

the application context is /bom

URL to hit the application is
http://localhost:9080/bom/index.html

Find All Parts in the repository using REST Service
GET Method
http://localhost:9080/bom/services/partservice/findparts

Load EBOM
GET Method
http://localhost:9080/bom/services/partservice/loadbom?name=Bike

# Clone a specific tag name
git clone <url. --branch=<tag_name>
 
Find all children by EBOM relationship
 
@Query("MATCH (parent:Part)-[:EBOM*]->(child:Part) RETURN parent.name, collect(child.name)")

MATCH (parent:Part)-[:EBOM]->(child:Part) RETURN parent.name, collect(child.name)


# Setting up the Development Environment

Install Neo4J 3.4.14 Community Edition

Install APOC ( Awesome procedures on cypher)

APOC Late Spring Release 3.4.0.7

https://github.com/neo4j-contrib/neo4j-apoc-procedures/releases/download/3.4.0.7/apoc-3.4.0.7-all.jar


https://github.com/neo4j-contrib/neo4j-apoc-procedures/releases/tag/3.5.0.1

Set NEO4J_HOME=/usr/local/Cellar/neo4j/3.5.3/libexec/

Copy the APOC JAR from https://github.com/neo4j-contrib/neo4j-apoc-procedures/releases/tag/3.5.0.1

to the NEO4J_HOME/plugins directory

Modify the conf/neo4j.conf file for unrestricted access to apoc procedures

dbms.security.procedures.unrestricted=apoc.*


#1 Upgrade UI framework ( PrimeNG 10)

Search and Display BOM (New UI)
![Alt text](/UI_Framework.png)