/********************************************************************************************
 * Author: 			Luke Earp
 * Class: 			PlayerService.java
 * Description: 	The functionality to retrieve the data being requested in the controller.
 * Creation Date: 	27/02/2022
 * Current Version: 1.0 - Created.
 * Version History: 
 *********************************************************************************************/
package com.microservice.wallet.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.sql.rowset.spi.TransactionalWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.microservice.wallet.dao.PlayerDAO;
import com.microservice.wallet.dao.TransactionDAO;

@Service
public class PlayerService {
	static Map<Integer, PlayerDAO> myMap = new ConcurrentHashMap<Integer, PlayerDAO>();
	static Map<Integer, TransactionDAO> myTransactionMap = new ConcurrentHashMap<Integer, TransactionDAO>();

	
	private static Logger logger = LoggerFactory.getLogger(PlayerService.class);
	
	/*********************************************************************************************
	 * Name: 		getPlayerDetails
	 * Description: Retrieve the whole list of players.
	 * Returns:		The list of players.
	 * Author: 		Luke Earp
	 * Created: 	27/02/2022
	 * Version: 	1.0 - Created.
	 * History: 
	 ********************************************************************************************/
	public static Map<Integer, PlayerDAO> getPlayerDetails()
	{
		logger.info("***** - Getting All Player Details - *****");

		Map<Integer, PlayerDAO> playerData = myMap;
		
		logger.info("***** - Player Details - *****" + playerData);

		return playerData;
	}
	/*********************************************************************************************
	 * Name: 		getTransactionDetails
	 * Description: Retrieve the whole list of transactions.
	 * Returns:		The list of transactions.
	 * Author: 		Luke Earp
	 * Created: 	28/02/2022
	 * Version: 	1.0 - Created.
	 * History: 
	 ********************************************************************************************/
	public static Map<Integer, TransactionDAO> getTransactionDetails()
	{
		return myTransactionMap;
	}
	/*********************************************************************************************
	 * Name: 		getPlayerDetailsbyId
	 * Description: Retrieve a player based on entered ID.
	 * Parameters:	id - the player id.
	 * Returns:		The player.
	 * Author: 		Luke Earp
	 * Created: 	27/02/2022
	 * Version: 	1.0 - Created.
	 * History: 
	 ********************************************************************************************/
	public static List<Entry<Integer, PlayerDAO>> getPlayerDetailsbyId(int id)
	{
		logger.info("***** - Getting Player Details By Id - " + id);

		Map<Integer, PlayerDAO> playerData = myMap;

		List<Entry<Integer, PlayerDAO>>playerById = playerData.entrySet().stream().filter(X->X.getKey()==id).collect(Collectors.toList());		

		logger.info("***** - Player Details By Id - " + playerById);

		return playerById;
	}
	/*********************************************************************************************
	 * Name: 		getTransactionDetailsbyId
	 * Description: Retrieve a transaction based on entered ID.
	 * Parameters:	id - the player id.
	 * Returns:		The transactions.
	 * Author: 		Luke Earp
	 * Created: 	28/02/2022
	 * Version: 	1.0 - Created.
	 * History: 
	 ********************************************************************************************/
	public static List<TransactionDAO> getTransactionDetailsbyId(int id)
	{
		List<TransactionDAO> response = new ArrayList<TransactionDAO>();
		TransactionDAO transaction = new TransactionDAO();
		
		for(Map.Entry<Integer, TransactionDAO>trans : myTransactionMap.entrySet())
		{
			TransactionDAO transact = trans.getValue();
			if(transact.getCustomerId() == id)
			{
				response.add(transact);
			}
		}

		return response;

	}
	/*********************************************************************************************
	 * Name: 		createPlayerDetails
	 * Description: Create a player based on entered ID.
	 * Parameters:	id - the player id.
	 * 				dao - The new player object.
	 * Returns:		The whole list of players including new player.
	 * Author: 		Luke Earp
	 * Created: 	27/02/2022
	 * Version: 	1.0 - Created.
	 * History: 
	 ********************************************************************************************/
	public static Map<Integer, PlayerDAO> createPlayerDetails(int id, PlayerDAO dao)
	{
		logger.info("***** - Create Player - Id: " + id + " , dao: " + dao);

		Map<Integer, PlayerDAO> playerData = myMap;
		
		if(!playerData.containsKey(id))
		{
			myMap.put(id, new PlayerDAO(id, dao.getPlayerName(), dao.getPlayerBalance()));
		}
		else
		{
			logger.info("***** - Player already exists with that ID - " + id + " *****");
		}
		logger.info("***** - Player list after new player - " + myMap);
		return myMap;

	}
	/*********************************************************************************************
	 * Name: 		createDepositDetails
	 * Description: Create a deposit based on entered cust ID.
	 * Parameters:	id - the player id.
	 * 				dao - The new transaction object.
	 * Returns:		The whole list of transactions for that player.
	 * Author: 		Luke Earp
	 * Created: 	27/02/2022
	 * Version: 	1.0 - Created.
	 * History: 
	 ********************************************************************************************/
	public static Map<Integer, TransactionDAO> createDepositDetails(int id, TransactionDAO dao)
	{
		boolean transactionExists = false;
		logger.info("***** - Create Deposit - Id: " + id + " , dao: " + dao);
		for(Map.Entry<Integer, TransactionDAO>transaction : myTransactionMap.entrySet())
		{
			TransactionDAO trans = transaction.getValue();
			if(trans.getTransactionId() == dao.getTransactionId())
			{
				transactionExists = true;
				logger.info("This transaction ID already exists.");
			}
		}
		if(!transactionExists)
		{
			if(!myTransactionMap.containsKey(id))
			{
				
				myTransactionMap.put(dao.getTransactionId(), new TransactionDAO(dao.getTransactionId(), dao.getTransactionType(), id, dao.getTransAmount()));
				
				List<Entry<Integer, PlayerDAO>> playerDetail = getPlayerDetailsbyId(id);
				for (Map.Entry<Integer,PlayerDAO> entry : playerDetail)
				{
					PlayerDAO player = entry.getValue();
					player.setPlayerBalance(player.getPlayerBalance() + dao.getTransAmount());
					updatePlayerDetails( id,  player);
				}
				
				
			}
			else
			{
				logger.info("***** - Transaction already exists with that internal ID - " + id + " *****");
			}
		}
		logger.info("***** - Transaction list after new Transaction - " + myTransactionMap);
		return myTransactionMap;

	}
	/*********************************************************************************************
	 * Name: 		createWithdrawDetails
	 * Description: Create a withdrawal based on entered cust ID.
	 * Parameters:	id - the player id.
	 * 				dao - The new transaction object.
	 * Returns:		The whole list of transactions for that player.
	 * Author: 		Luke Earp
	 * Created: 	27/02/2022
	 * Version: 	1.0 - Created.
	 * History: 
	 ********************************************************************************************/
	public static Map<Integer, TransactionDAO> createWithdrawDetails(int id, TransactionDAO dao)
	{
		boolean transactionExists = false;
		logger.info("***** - Create Withdrawal - Id: " + id + " , dao: " + dao);
		for(Map.Entry<Integer, TransactionDAO>transaction : myTransactionMap.entrySet())
		{
			TransactionDAO trans = transaction.getValue();
			if(trans.getTransactionId() == dao.getTransactionId())
			{
				transactionExists = true;
				logger.info("This transaction ID already exists.");
			}
		}
		if(!transactionExists)
		{
			if(!myTransactionMap.containsKey(id))
			{
				
				myTransactionMap.put(dao.getTransactionId(), new TransactionDAO(dao.getTransactionId(), dao.getTransactionType(), id, dao.getTransAmount()));
				
				List<Entry<Integer, PlayerDAO>> playerDetail = getPlayerDetailsbyId(id);
				for (Map.Entry<Integer,PlayerDAO> entry : playerDetail)
				{
					PlayerDAO player = entry.getValue();
					if((player.getPlayerBalance() - dao.getTransAmount()) >= 0)
					{
						player.setPlayerBalance(player.getPlayerBalance() - dao.getTransAmount());
						updatePlayerDetails( id,  player);
					}
					else
					{
						logger.info("Trying to withdraw more than the balance.");
					}
					
				}
				
				
			}
			else
			{
				logger.info("***** - Transaction already exists with that internal ID - " + id + " *****");
			}
			logger.info("***** - Transaction list after new Transaction - " + myTransactionMap);
		}
		
		return myTransactionMap;

	}
	/*********************************************************************************************
	 * Name: 		updatePlayerDetails
	 * Description: Update a player based on entered ID.
	 * Parameters:	id - the player id.
	 * 				dao - The updated player object.
	 * Returns:		The whole list of players including updated player.
	 * Author: 		Luke Earp
	 * Created: 	27/02/2022
	 * Version: 	1.0 - Created.
	 * History: 
	 ********************************************************************************************/
	public static Map<Integer, PlayerDAO> updatePlayerDetails(int id, PlayerDAO dao)
	{
		logger.info("***** - Update Player - Id: " + id + " , dao: " + dao);

		Map<Integer, PlayerDAO> playerData = myMap;
		if(playerData.containsKey(id))
		{
			myMap.put(id, new PlayerDAO(dao.getPlayerId(), dao.getPlayerName(), dao.getPlayerBalance()));

		}
		else
		{
			logger.info("***** - Player does not exist with ID - " + id + " *****");
		}
		logger.info("***** - Updated player list after updated player - " + myMap);

		return myMap;
	}
	/*********************************************************************************************
	 * Name: 		deletePlayerDetails
	 * Description: Delete a player based on entered ID.
	 * Parameters:	id - the player id.
	 * Returns:		The whole list of players without deleted player.
	 * Author: 		Luke Earp
	 * Created: 	27/02/2022
	 * Version: 	1.0 - Created.
	 * History: 
	 ********************************************************************************************/
	public static Map<Integer, PlayerDAO> deletePlayerDetails(int id)
	{
		logger.info("***** - Delete Player - Id: " + id);

		Map<Integer, PlayerDAO> playerData = myMap;
		if(playerData.containsKey(id))
		{
			myMap.remove(id);
		}
		else
		{
			logger.info("***** - Player does not exist with ID - " + id + " *****");

		}
		
		logger.info("***** - Player list after deleted player - " + myMap);

		return myMap;
	}
}
