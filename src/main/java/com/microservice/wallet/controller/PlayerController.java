/********************************************************************************************
 * Author: 			Luke Earp
 * Class: 			PlayerController.java
 * Description: 	Setup the URLs for the API and handle the relevant functionality for each.
 * Creation Date: 	27/02/2022
 * Current Version: 1.0 - Created.
 * Version History: 
 *********************************************************************************************/
package com.microservice.wallet.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.wallet.dao.PlayerDAO;
import com.microservice.wallet.dao.TransactionDAO;
import com.microservice.wallet.service.PlayerService;
import com.microservice.wallet.to.Player;
import com.microservice.wallet.to.Transaction;
// localhost:8080
@RestController
public class PlayerController {
	private static Logger logger = LoggerFactory.getLogger(PlayerController.class);
	
	@Autowired
	PlayerService service;
	/*********************************************************************************************
	 * Name: 		getMyPlayersDetails
	 * Description: Get details of all players.
	 * Parameters: 	N/A
	 * Returns: 	Map of players with ID and data object.
	 * Author: 		Luke Earp
	 * Created: 	27/02/2022
	 * Version: 	1.0 - Created.
	 * History: 
	 ********************************************************************************************/
	@GetMapping("/players")
	public Map<Integer, PlayerDAO> getMyPlayersDetails()
	{
		return service.getPlayerDetails();
	}
	/*********************************************************************************************
	 * Name: 		getMyPlayersDetailsById
	 * Description: Get details of a single player by their ID.
	 * Parameters: 	id - the ID of the player.
	 * Returns: 	Map of player with ID and data object.
	 * Author: 		Luke Earp
	 * Created: 	27/02/2022
	 * Version: 	1.0 - Created.
	 * History: 
	 ********************************************************************************************/
	@GetMapping("/playerbyid/{id}")
	public List<Entry<Integer, PlayerDAO>> getMyPlayersDetailsById(@PathVariable int id)
	{
		return service.getPlayerDetailsbyId(id);
	}
	/*********************************************************************************************
	 * Name: 		createMyPlayersDetails
	 * Description: Create a new player with set ID and new data object.
	 * Parameters: 	id - the ID of the new player.
	 * 				to - the object transferred back with the new details.
	 * Returns: 	Map of all players with ID and data object.
	 * Author: 		Luke Earp
	 * Created: 	27/02/2022
	 * Version: 	1.0 - Created.
	 * History: 
	 ********************************************************************************************/
	@PutMapping("/createplayerbyid/{id}")
	public Map<Integer, PlayerDAO> createMyPlayersDetails(@PathVariable int id, @RequestBody Player to)
	{
		PlayerDAO dao = new PlayerDAO();
		dao.setPlayerId(to.getPlayerId());
		dao.setPlayerName(to.getPlayerName());
		dao.setPlayerBalance(to.getPlayerBalance());
		
		Map<Integer, PlayerDAO> createPlayerDetails = service.createPlayerDetails(id, dao);
		return createPlayerDetails;
	}
	/*********************************************************************************************
	 * Name: 		updateMyPlayersDetails
	 * Description: Update a player with set ID and new data object.
	 * Parameters: 	id - the ID of the player.
	 * 				to - the object transferred back with the new details.
	 * Returns: 	Map of all players with ID and data object.
	 * Author: 		Luke Earp
	 * Created: 	27/02/2022
	 * Version: 	1.0 - Created.
	 * History: 
	 ********************************************************************************************/
	@PostMapping("/updateplayerbyid/{id}")
	public Map<Integer, PlayerDAO> updateMyPlayersDetails(@PathVariable int id, @RequestBody Player to)
	{
		PlayerDAO dao = new PlayerDAO();
		dao.setPlayerId(to.getPlayerId());
		dao.setPlayerName(to.getPlayerName());
		dao.setPlayerBalance(to.getPlayerBalance());
		
		Map<Integer, PlayerDAO> updatePlayerDetails = service.updatePlayerDetails(id, dao);
		return updatePlayerDetails;
	}
	/*********************************************************************************************
	 * Name: 		deleteMyPlayersDetails
	 * Description: Delete a player with set ID.
	 * Parameters: 	id - the ID of the player.
	 * Returns: 	Map of all players with ID and data object.
	 * Author: 		Luke Earp
	 * Created: 	27/02/2022
	 * Version: 	1.0 - Created.
	 * History: 
	 ********************************************************************************************/
	@DeleteMapping("/deleteplayerbyid/{id}")
	public Map<Integer, PlayerDAO> deleteMyPlayersDetails(@PathVariable int id)
	{
		Map<Integer, PlayerDAO> afterDeleteDetails = service.deletePlayerDetails(id);
		return afterDeleteDetails;
	}
	/*********************************************************************************************
	 * Name: 		createDepositTransaction
	 * Description: Create a deposit for the player.
	 * Parameters: 	id - the id of player.
	 * 				to - the transaction transfer object.
	 * Returns: 	
	 * Author: 		Luke Earp
	 * Created: 	28/02/2022
	 * Version: 	1.0 - Created.
	 * History: 
	 * @return 
	 ********************************************************************************************/
	@PutMapping("/createdepositbycustid/{id}")
	public Map<Integer, TransactionDAO> createDepositTransaction(@PathVariable int id, @RequestBody Transaction to)
	{
		TransactionDAO dao = new TransactionDAO();
		dao.setTransactionId(to.getTransactionId());
		dao.setTransactionType("Deposit");
		dao.setCustomerId(to.getCustomerId());
		dao.setTransAmount(to.getTransAmount());
		
		Map<Integer, TransactionDAO> createDepositDetails = service.createDepositDetails(id, dao);
		return createDepositDetails;
	}
	/*********************************************************************************************
	 * Name: 		createWithdrawTransaction
	 * Description: Create a withdrawal for the player.
	 * Parameters: 	id - the id of player.
	 * 				to - the transaction transfer object.
	 * Returns: 	
	 * Author: 		Luke Earp
	 * Created: 	28/02/2022
	 * Version: 	1.0 - Created.
	 * History: 
	 * @return 
	 ********************************************************************************************/
	@PutMapping("/createwithdrawbycustid/{id}")
	public Map<Integer, TransactionDAO> createWithdrawTransaction(@PathVariable int id, @RequestBody Transaction to)
	{
		TransactionDAO dao = new TransactionDAO();
		dao.setTransactionId(to.getTransactionId());
		dao.setTransactionType("Withdraw");
		dao.setCustomerId(to.getCustomerId());
		dao.setTransAmount(to.getTransAmount());
		
		Map<Integer, TransactionDAO> createWithdrawDetails = service.createWithdrawDetails(id, dao);
		return createWithdrawDetails;
	}
	/*********************************************************************************************
	 * Name: 		getAllTransactions
	 * Description: Get details of all transactions.
	 * Parameters: 	N/A
	 * Returns: 	all transactions.
	 * Author: 		Luke Earp
	 * Created: 	28/02/2022
	 * Version: 	1.0 - Created.
	 * History: 
	 * @return 
	 ********************************************************************************************/
	@GetMapping("/alltransactions")
	public Map<Integer, TransactionDAO> getAllTransactions()
	{
		return service.getTransactionDetails();
	}
	/*********************************************************************************************
	 * Name: 		getTransactionsByCustId
	 * Description: Get details of a single players transactions by their ID.
	 * Parameters: 	id - the ID of the player.
	 * Returns: 	
	 * Author: 		Luke Earp
	 * Created: 	28/02/2022
	 * Version: 	1.0 - Created.
	 * History: 
	 * @return 
	 ********************************************************************************************/
	@GetMapping("/transactionbycustid/{id}")
	public List<TransactionDAO> getTransactionsByCustId(@PathVariable int id)
	{
		return service.getTransactionDetailsbyId(id);
	}
	
}
