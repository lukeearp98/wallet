# wallet-microservice-api
 
 To use this API please:
 1. Download the project and import into a development environment E.g. Eclipse.
 2. Run the project (WalletApplication.Java)
 3. Go to http://localhost:8080/swagger-ui/index.html#/ to test the APIs.

This API has the following end points:

# PUT
/createplayerbyid/{id}<br />
 Example data:<br />
 {<br />
   "playerName": "eke",<br />
   "playerBalance": 0<br />
 }<br />
/createdepositbycustid/{id}<br />
Example data:<br />
{<br />
  "transactionId": 12346,<br />
  "transAmount": 5<br />
}<br />
/createwithdrawbycustid/{id}<br />
Example data:<br />
{<br />
  "transactionId": 12346,<br />
  "transAmount": 5<br />
}<br />
# POST
/updateplayerbyid/{id}<br />
Example Data: <br />
{<br />
"playerName": "string",<br />
  "playerBalance": 6.00<br />
}<br />
# GET
/players<br />
/playerbyid/{id}<br />
/alltransactions<br />
/transactionbycustid/{id}<br />
# DELETE
/deleteplayerbyid/{id}<br />

 
