package com.qainfotech.tap.training.snl.api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class NewTest {
  
	Board board;
	UUID idOne;
	JSONArray data;
		
	@Test(expectedExceptions= PlayerExistsException.class)
	public void checkPlayerExistException() throws FileNotFoundException, UnsupportedEncodingException, IOException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption {
		
		board=new Board();
		board.registerPlayer("Shikha");
		board.registerPlayer("Shikha");
		
	}
	
	@Test(expectedExceptions=MaxPlayersReachedExeption.class)
	public void checkMaxPlayerReachedException() throws FileNotFoundException, UnsupportedEncodingException, IOException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption {
		
		board=new Board();
		board.registerPlayer("Shikha1");
		board.registerPlayer("Shikha2");
		board.registerPlayer("Shikha3");
		board.registerPlayer("Shikha4");
		board.registerPlayer("Shikha4");		
		
	}
	
	@Test(expectedExceptions=NoUserWithSuchUUIDException.class)
	public void checkNoUserWithSuchUUIDException() throws FileNotFoundException, UnsupportedEncodingException, IOException, NoUserWithSuchUUIDException {
		
		board=new Board();
		UUID idOne = UUID.randomUUID();
		board.deletePlayer(idOne);
		 
	}
	
	@Test(expectedExceptions=InvalidTurnException.class)
	public void checkInvalidTurnException() throws FileNotFoundException, UnsupportedEncodingException, IOException, InvalidTurnException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption {
		
		board=new Board();
		data=board.registerPlayer("Shikha");
		data=board.registerPlayer("Barkha");
		JSONObject Player1=data.getJSONObject(0);
		JSONObject Player2=data.getJSONObject(1);
		UUID playerOneID=UUID.fromString(Player1.getString("uuid"));
		board.rollDice(playerOneID);
		board.rollDice(playerOneID);
		
	}
	
	@Test(expectedExceptions=GameInProgressException.class)
	public void checkGameInProgressException() throws FileNotFoundException, UnsupportedEncodingException, IOException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, InvalidTurnException {
		
		board=new Board();
		data=board.registerPlayer("Shikha");
		data=board.registerPlayer("Barkha");
		data=board.registerPlayer("Rishabh");
		//data=board.registerPlayer("Rohit");
		JSONObject Player1=data.getJSONObject(0);
		JSONObject Player2=data.getJSONObject(1);
		JSONObject Player3=data.getJSONObject(2);
		//JSONObject Player4=data.getJSONObject(3);
		UUID player1ID=UUID.fromString(Player1.getString("uuid"));
		UUID player2ID=UUID.fromString(Player2.getString("uuid"));
		UUID player3ID=UUID.fromString(Player3.getString("uuid"));
		//UUID player4ID=UUID.fromString(Player4.getString("uuid"));
		board.rollDice(player1ID);
		board.rollDice(player2ID);
		board.registerPlayer("Rohit1");
		board.rollDice(player3ID);
		//board.rollDice(player4ID);
		
	}

}
