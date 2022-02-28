/********************************************************************************************
 * Author: 			Luke Earp
 * Class: 			Player.java
 * Description: 	Transfer object to send back to API when updating a player.
 * Creation Date: 	27/02/2022
 * Current Version: 1.0 - Created.
 * Version History: 
 *********************************************************************************************/
package com.microservice.wallet.to;

public class Player {
	int playerId;
	String playerName;
	double playerBalance;
	/*********************************************************************************************
	 * Name: 		Player
	 * Description: Default instance of constructor.
	 * Parameters:	The details of the player.
	 * Author: 		Luke Earp
	 * Created: 	27/02/2022
	 * Version: 	1.0 - Created.
	 * History: 
	 ********************************************************************************************/
	public Player() {
		
	}
	/*********************************************************************************************
	 * Name: 		Player
	 * Description: Default instance of constructor with parameters.
	 * Parameters:	The details of the player.
	 * Author: 		Luke Earp
	 * Created: 	27/02/2022
	 * Version: 	1.0 - Created.
	 * History: 
	 ********************************************************************************************/
	public Player(int playerId, String playerName, double playerBalance) {
		super();
		this.playerId = playerId;
		this.playerName = playerName;
		this.playerBalance = playerBalance;
	}
	/*********************************************************************************************
	 * Name: 		getPlayerId
	 * Description: get ID of player data object.
	 * Author: 		Luke Earp
	 * Created: 	27/02/2022
	 * Version: 	1.0 - Created.
	 * History: 
	 ********************************************************************************************/
	public int getPlayerId() {
		return playerId;
	}
	/*********************************************************************************************
	 * Name: 		setPlayerId
	 * Description: set ID of player data object.
	 * Author: 		Luke Earp
	 * Created: 	27/02/2022
	 * Version: 	1.0 - Created.
	 * History: 
	 ********************************************************************************************/
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	/*********************************************************************************************
	 * Name: 		getPlayerName
	 * Description: get name of player data object.
	 * Author: 		Luke Earp
	 * Created: 	27/02/2022
	 * Version: 	1.0 - Created.
	 * History: 
	 ********************************************************************************************/
	public String getPlayerName() {
		return playerName;
	}
	/*********************************************************************************************
	 * Name: 		setPlayerName
	 * Description: set name of player data object.
	 * Author: 		Luke Earp
	 * Created: 	27/02/2022
	 * Version: 	1.0 - Created.
	 * History: 
	 ********************************************************************************************/
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	/*********************************************************************************************
	 * Name: 		getPlayerBalance
	 * Description: get balance of player data object.
	 * Author: 		Luke Earp
	 * Created: 	27/02/2022
	 * Version: 	1.0 - Created.
	 * History: 
	 ********************************************************************************************/
	public double getPlayerBalance() {
		return playerBalance;
	}
	/*********************************************************************************************
	 * Name: 		setPlayerBalance
	 * Description: set balance of player data object.
	 * Author: 		Luke Earp
	 * Created: 	27/02/2022
	 * Version: 	1.0 - Created.
	 * History: 
	 ********************************************************************************************/
	public void setPlayerBalance(double playerBalance) {
		this.playerBalance = playerBalance;
	}
	/*********************************************************************************************
	 * Name: 		toString
	 * Description: Print string version of object.
	 * Author: 		Luke Earp
	 * Created: 	27/02/2022
	 * Version: 	1.0 - Created.
	 * History: 
	 ********************************************************************************************/
	@Override
	public String toString() {
		return "Player [playerId=" + playerId + ", playerName=" + playerName + ", playerBalance=" + playerBalance + "]";
	}
	
}
