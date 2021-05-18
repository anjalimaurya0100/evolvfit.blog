# Evolvfit.blog
This project consist of API's on:

#### Blog Entity APIs:
* Create a blog - `POST /blogs`
* List of all blogs - `GET /blogs`
* Get a specific blog by code - `GET /blogs/{blogId}`
* Update a blog - `PUT /blogs/{blogId}`
* Delete a blog - `DELETE /blogs/{blogId}`

#### Comments Entity APIs:
* Create a comment for a blog- `POST /blogs/{blogId}/comments`
* List of all comments for blog- `GET /blogs/{blogId}/comments`

#### Reply Entity APIs:
* List of all replies for a comment- `GET /comments/{commentId}/replies`
* Create a reply for a comment- `POST /comments/{commentId}/replies`

### Dependencies:
* Java 8
* Gradle 6.8.3 - dependency manager
* Git - for version control

### Libraries:
* Spring WEB
* Spring Data JPA
* Spring Validation
* H2 - Using SQL database(H2) as we have pre-defined schema[check `blog_db_model.svg` file for DB models]
* Lombok

#### Command to run the application - `./gradlew bootRun`

#### Link to Postman API Documentation: https://documenter.getpostman.com/view/15580212/TzRYcjfA#be9cb83f-3198-deb6-88d3-7279f1c6ef8a