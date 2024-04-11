Deployed application on Heroku - <a href='https://lingocards-e5878581f5d7.herokuapp.com'>LINK</a>
---------------------
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

Used dependencies:
<li>Spring Web</li>
<li>Spring JPA</li>
<li>Spring Security</li>
<li>Spring Logging</li>
<li>Spring Thymeleaf</li>
<li>Apache Poi</li>
<li>Jsoup</li>

<h1>Work examples:</h1>

<h2>Registration Page</h2>
User can create new account by filling required information:
<br><br>
<img width="750" alt="image" src="https://github.com/YuliaSwr/LingoCards-Spring/assets/77783002/dfcad73e-8364-4162-8eef-187d49372ddf">

<h2>Login Page</h2>
User must login to his account:
<br><br>
<img width="750" alt="image" src="https://github.com/YuliaSwr/LingoCards-Spring/assets/77783002/299691c0-21e7-4825-9b25-e79f49b2aeeb">

<h2>Looking for translation</h2>
User can search for translation for specific words:
<br><br>
<img width="750" alt="image" src="https://github.com/YuliaSwr/LingoCards-Spring/assets/77783002/2f3f0ff4-361e-4ecb-a03c-e4b7037fe1ef">

They can choose some of translations and add to their sets:
<br><br>
<img width="750" alt="image" src="https://github.com/YuliaSwr/LingoCards-Spring/assets/77783002/1f91229d-73c0-4026-85cd-e62880d9d8f7">

<h2>Set list</h2>
User can see all his set's and edit/delete it or download excel file with all flashcards from this set inside:
<img width="750" alt="image" src="https://github.com/YuliaSwr/LingoCards-Spring/assets/77783002/73a52d39-068c-4b03-9edc-5bc82f402aca">

<h2>Editing set</h2>
User can edit set's name, update flashcards, add new one and delete old one:
<br><br>
<img width="750" alt="image" src="https://github.com/YuliaSwr/LingoCards-Spring/assets/77783002/5316fcca-00ba-4a9a-8ad3-a6ff47afd3bc">

<h2>Set view</h2>
<img width="750" alt="image" src="https://github.com/YuliaSwr/LingoCards-Spring/assets/77783002/2d2fe759-7db0-468a-a997-2822e5111ff1">





