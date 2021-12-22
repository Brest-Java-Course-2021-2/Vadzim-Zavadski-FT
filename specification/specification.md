# Football tournament

## Vision
Web-application for creating football league members.<br />
The application must implement the following functions:
* viewing and editing the list of football teams;
* viewing and editing the list of players;
* filtering players by date of birthday

# 1. Football teams
## 1.1 Viewing the list of football teams
This program mode is designed to view the list of teams and the number of players in a team.

Main scenario:
* The user selects the "Teams" menu item;
* The list of all teams is displayed
<br />


![Picture 1.1]()

Picture 1.1 Viewing the list of teams

The list displays the following columns:
* Name - the name of the team; 
* Number of players in team;
* Average age players in team.

The total number of players is displayed at the end of the list.

## 1.2 Adding team
This program mode is designed to add a new team in list.

  Main scenario:
  * the user is in the teams list view mode and clicks the "Add" button;
  * the form for adding a new team is displayed;
  * the user enters data and clicks the "Save" button;
  * if the data was entered incorrectly, then the warning about incorrect data is displayed;
  * if the data was correct, then the team will be added to the database;
  * if an error occurred while saving data, an error message is displayed: "Error saving data";
  * if the team was added successfully, the form for viewing the list of teams with updated data would be open. 

Cancellation scenario:
  * the user is in teams list view mode and clicks the "Add" button;
  * the form for adding a new team is displayed;
  * the user enters data and clicks the "Cancel" button;
  * data is not saved to the database and form for viewing the list of teams is opening with updated data.<br />
 
![Picture 1.2]()

Picture 1.2 Team adding

When adding a team, the following details are entered:
  * Team - Name of team.
## 1.3 Editing team
This program mode is designed to edit a team in list.

  Main scenario:
  * the user is in the teams list view mode and clicks the "Edit" icon;
  * the form for editing a team is displayed;
  * the user idits data and clicks the "Save" button;
  * if the data was entered incorrectly, then the warning about incorrect data is displayed;
  * if the data was correct, then the team will be edited to the database;
  * if an error occurred while saving data, an error message is displayed: "Error saving data";
  * if the team was added successfully, the form for viewing the list of teams with updated data would be open. 

Cancellation scenario:
  * the user is in teams list view mode and clicks the "Edit" icon;
  * the form for editing a team is displayed;
  * the user enters data and clicks the "Cancel" button;
  * data is not saved to the database and form for viewing the list of teams is opening with updated data.<br />
 
![Picture 1.3]()

Picture 1.3 Team editing

When editing a team, the following details are entered:
  * Team - Name of team.

## 1.4 Deleting team
This program mode is designed to delete a team in list.

  Main scenario:
  * the user is in the teams list view mode and clicks the "Delete" icon;
  * checking for the possibility of deleting team, for example, are there any players on this team?;
  * if there are players in the team, then a message is displayed: "cannot be deleted";
  * if the command can be deleted,the confirmation dialog for deleting is displayed "Please confirm delete team: "Name of team"?";
  * the user clicks the "Yes" button;
  * the team is deleting in the database;
  * if an error occurred while deleting data, an error message is displayed "Error data deletion";
  * if the team has been deleted successfully, the form for viewing the list of teams with updated data would be open.

Cancellation scenario:
  * the user is in the teams list view mode and clicks the "Delete" icon;
  * the confirmation dialog for deleting is displayed "Delete team?";
  * the user clicks the "No" button;
  * form for viewing the list of teams is opening with updated data.<br />
 
![Picture 1.4]()

Picture 1.4 Team deleting

***
# 2. Players
## 2.1 Viewing the list of players
This program mode is designed to view the list of players and the total number of players.

Main scenario:
* The user selects the "Players" menu item;
* The list of all players is displayed<br />

![Picture 2.1]()

Picture 2.1 Viewing the list of players

The list displays the following columns:
* Name - the name of the players; 
* Surname - the surnname of the players; 
* Birthday - the birthday of the players.

## Filter by date:
* To view a list of players born in a certain period, set the parameters in the filter.
* The start date of the filter should not be greater than the end date.
* If no data is entered, then all players are displaying.
* If the start date is not entered, then filter by end date only.
* If the end date is not entered, then filter by start date only.
* The default filter shows all players.
* Data is updated after selecting filtering data and clicking the "show" button.

![Picture 2.2]()

Picture 2.2 Filtering the list of players by birthday

## 2.2 Adding player
This program mode is designed to add a new player in list.

  Main scenario:
  * the user is in the players list view mode and clicks the "Add" button;
  * the form for adding a new player is displayed;
  * the user enters data and clicks the "Save" button;
  * if the data was entered incorrectly, then the warning about incorrect data is displayed;
  * if the data was correct, then the team will be added to the database;
  * if an error occurred while saving data, an error message is displayed: "Error saving data";
  * if the player was added successfully, the form for viewing the list of playerss with updated data would be open. 

Cancellation scenario:
  * the user is in playerss list view mode and clicks the "Add" button;
  * the form for adding a new player is displayed;
  * the user enters data and clicks the "Cancel" button;
  * data is not saved to the database and form for viewing the list of players is opening with updated data.<br />
 
![Picture 2.3]()

Picture 2.3 Player adding

When adding a team, the following details are entered:
* Name - the name of the player; 
* Surname - the surnname of the player; 
* Birthday - the birthday of the player.
* in the drop-down menu, select the team

## 2.3 Editing player
This program mode is designed to edit a player in list.

 Main scenario:
  * the user is in the players list view mode and clicks the "Edit" icon;
  * the form for editing a player is displayed;
  * the user enters data and clicks the "Save" button;
  * if the data was entered incorrectly, then the warning about incorrect data is displayed;
  * if the data was correct, then the team will be added to the database;
  * if an error occurred while saving data, an error message is displayed: "Error saving data";
  * if the player was added successfully, the form for viewing the list of players with updated data would be open. 

Cancellation scenario:
  * the user is in playerss list view mode and clicks the "Edit" icon;
  * the form for adding a new player is displayed;
  * the user enters data and clicks the "Cancel" button;
  * data is not saved to the database and form for viewing the list of players is opening with updated data.<br />

![Picture 2.4]()

Picture 2.4 Player editing

When adding a team, the following details are entered:
* Name - the name of the player; 
* Surname - the surnname of the player; 
* Birthday - the birthday of the player.
* in the drop-down menu, select the team

## 2.4 Deleting team
This program mode is designed to delete a player in list.

  Main scenario:
  * the user is in the players list view mode and clicks the "Delete" icon;
  * the user clicks the "Yes" button;
  * the player is deleting in the database;
  * if an error occurred while deleting data, an error message is displayed "Error data deletion";
  * if the player has been deleted successfully, the form for viewing the list of players with updated data would be open.

Cancellation scenario:
  * the user is in the players list view mode and clicks the "Delete" icon;
  * the confirmation dialog for deleting is displayed "Delete player?";
  * the user clicks the "No" button;
  * form for viewing the list of players is opening with updated data.<br />
 
![Picture 2.5]()

Picture 2.5 Player deleting
