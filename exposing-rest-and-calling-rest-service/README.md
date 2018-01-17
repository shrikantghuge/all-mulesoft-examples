Example 2 :

REST POST service  is exposed with Mule and REST GET API with country as URI.

Request : 
 	URI : /rest-to-rest
 	Method : POST
 	Post : 8081
 	Content-Type : application/json
 	Payload:
	 		{
				"country":"IN"
			}
 	
 Response:
		   	{
			    "RestResponse": {
			        "messages": [
			            "Country found matching code [IN]."
			        ],
			        "result": {
			            "name": "India",
			            "alpha2_code": "IN",
			            "alpha3_code": "IND"
			        }
			    }
			}
   	
Note : If you want to change configuration please refer "mule-app.properties".
   
   