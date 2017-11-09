# websocket-gameroom
A game room implement by Spring WebSocket, a bootstrap for group users to different room with maximum number of users for each room.

## Getting Started
Not design for run alone, see the quick start below to apply it to your project.

## Quick Start
1. add this annotation
```@EnableConfigurationProperties(WebSocketGameRoom.class)```
2. Build your own game model extends AbstractGame
3. Build your own game model builder extends AeroplaneChessBuilder
4. Implement GameService interface and pass the your own game model as type
5. Configure the destination prefix of the application and the message broker by the configuration

## Configuration
| name | type | default | description |
| ---- |:----:| :-------:| ----------:|
| websocket.destination.prefix.broker | String | null | prefix of the message broker |
| websocket.destination.application | String | null | prefix of application |
| websocket.stomp.endpoint | String | null | stomp client end point |
| gameroom.config.numof.player | int | 0 | max num of player for each room |

## Demo
A [aeroplane chess](https://github.com/kan01234/aeroplanes-chess) game built by this project.

## Prerequisites
* JAVA 8 Runtime
* Maven 3.3 or above
