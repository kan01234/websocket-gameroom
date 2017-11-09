# websocket-gameroom
A game room implement by Spring WebSocket, a bootstrap for group users to different room with maximum number of users for each room.

## Getting Started
Not design for run alone, see the quick start below to apply it to your project.

## Quick Start
1. add this annotation
```@EnableConfigurationProperties(WebSocketGameRoom.class)```
2. Build your own game model extends AbstractGame
3. Build your own game model builder extends AbstractGameBuilder
4. Implement GameService interface and pass the your own game model as type
5. Configure the destination prefix of the application and the message broker by the configuration

## Configuration
| name | type | default | description |
| :---- | :----: | :-------:| :---------- |
| websocket.destination.prefix.broker | String | null | prefix of the message broker |
| websocket.destination.application | String | null | prefix of application |
| websocket.stomp.endpoint | String | null | stomp client end point |
| gameroom.config.numof.player | int | 0 | max num of player for each room |

## Predefined path for player and the room
| path | descritpion | parameters |
| :---- | :-----------| :-------- |
| /join/{gameId}/{name} | send join request to the room | gameId: id of the game. name: name of the player |
| /ready/{gameId} | send ready signal | gameId: id of the game | 
Example:
1. join game without gameId and with username dotterbear
```stompClient.send('/app/join/null/dotterbear');```
2. join game with gameId 123456 and with username dotterbear
```stompClient.send('/app/join/123456/dotterbear');```
3. send ready signal to the game with gameId 123456
```stompClient.send('/app/ready/123456');```

## Required implement class explanation
1. model extends AbstractGame
Implement your own game model contain related field to handle the game logic.
2. builder extends AbstractGameBuilder
Used to build your own model when create the abstract game.
3. GameService interface
Implment the class when the game is start, player win, player leave and new game, etc game logic.

For more you can check the java doc of the method or the related class.

## Demo
A [aeroplane chess](https://github.com/kan01234/aeroplanes-chess) game built by this project.

## Prerequisites
* JAVA 8 Runtime
* Maven 3.3 or above
