<h1>Application Setup Guide</h1>
<h2>Running the application locally</h2>

To set up local database uncomment variables in `application.yml` file and fill with your local database server data
<br>
<img width="450" alt="image" src="https://github.com/YuliaSwr/LingoCards-Spring/assets/77783002/f087cf32-0a68-47c2-a17a-844217409307">

<br>
Database schema will be created on init due to `schema.sql` in resources folder

<h2>Deploying the application to Heroku</h2>
To set up database from heroku you need to set up on global enviroment of Heroku itself required variables

```
PROD_DB_HOST
PROD_DB_PORT
PROD_DB_NAME
PROD_DB_USERNAME
PROD_DB_PASSWORD
```
<h1>Application Description</h1>

Used:
<li>Spring Web</li>
<li>Spring JPA</li>
<li>Spring Security</li>
<li>Spring Logging</li>
<li>Spring Thymeleaf</li>
<li>Apache Poi</li>
