# HealthCareEnrollment
This is a health care enrollment application. It uses Rest APIs to enroll a person/enrollee into the application, to modify data of an existing enrollee, to remove an enrollee from the application, to get an enrollee's information based on the enrollee Id, to add dependents to an enrollee, to update dependent information of an enrollee and to delete/remove dependents.

Below are the end-points which can be used to access the application.
1. To get an existing Enrollee's data use end-point - /healthcare/enrollment/enrollee/{Id}
    
    Id - This is the Id of the Enrollee
    
    Ex: /healthcare/enrollment/enrollee/1 - gives information about Enrollee with Enrollee Id 1
    
2. To add/register an Enrollee, use end-point - /healthcare/enrollment/enroll
   New Enrollee's information needs to be passed in the form of Json
   Ex: {
          "activationStatus": true,
          "dependents": [
          {
            "dob": "2020-09-26T13:20:10.199Z",
            "name": "Johnny"
          }
          ],
          "dob": "2020-09-26T13:20:10.199Z",
          "name": "Ted",
          "phoneNumber": "123-456-7890"
       }
3. To update some information of an existing Enrollee - /healthcare/enrollment/enrollee/{Id}
   Enrollee's updated information needs to be passed in the form of Json along with Enrollee Id
   Ex: /healthcare/enrollment/enrollee/1
       {
          "activationStatus": true,
          "dependents": [
          {
            "dob": "2020-09-26T13:20:10.199Z",
            "name": "Johnny"
          }
          ],
          "dob": "2020-09-26T13:20:10.199Z",
          "name": "Ted",
          "phoneNumber": "123-456-7892"
       }
 4. To remove an Enrollee - /healthcare/enrollment/enrollee/{Id}
    Id - This is the Id of the Enrollee
    Ex: /healthcare/enrollment/enrollee/1 - deletes Enrollee with Id 1 from the application depending on the http method
 5. To add/register a Dependent, use end-point - /healthcare/enrollment/dependent/{Id}
    Enrollee's dependent information needs to be passed in the form of Json
    Ex: {
            "dob": "2020-09-26T13:20:10.199Z",
            "name": "Johnny"
          }
 6. To update some information of an existing Dependent - /healthcare/enrollment/dependent/{Id}
   Dependent's updated information needs to be passed in the form of Json along with Enrollee Id
   Ex: /healthcare/enrollment/dependent/2
         {
            "dob": "2020-04-21T13:20:10.199Z",
            "name": "Johnny"
          }   
 7. To remove a dependent - /healthcare/enrollment/dependent/{Id}
    Id - This is the Id of the Enrollee
    Ex: /healthcare/enrollment/dependent/1 - deletes dependent with Id 1 from the application depending on the http method
    
 ------Swagger----
 The application has been integrated with Swagger.
 Swagger URL - http://localhost:8081/swagger-ui.html
