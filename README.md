# MIPS - Multiplayer Isometric Pacman Simulator
![MipsLogo](https://github.com/AlexJBanks/lordsofmidnight/blob/master/src/main/resources/icon.png)
![Gif of gameplay](https://github.com/AlexJBanks/lordsofmidnight/blob/master/src/main/resources/ui/preview.gif)
### Created by the Lords of Midnight
- L. Ackroyd
- [A. Banks](https://github.com/AlexJBanks)
- [T. Cheung](https://github.com/timothy-ch-cheung)
- [M. Jones](https://github.com/MMJ744)
- [A. Kona](https://github.com/Kones345)
- J. Weir 

## Game Concept
MIPS is a  game is inspired on the 1980s arcade game PacMan. The main difference being that both the main character – mipsman – and the other ghouls are user playable. Branching off from the original, when a ghoul catches mipsman they kill them, taking their place as the new reigning mipsman. Meanwhile, the old mipsman is killed, respawns elsewhere on the map as ghoul and now has to try and recapture the new mipsman.

Sabotage is encouraged through powerups and traps and incentivised with rewards, live leader boards, and specialist awards after the game times out.

The game modernises the user experience of classic arcade games with a sleek isometric viewpoint, yet blocky pixelated fonts and retro 8bit music & sound effects.

# Team Project
This game was developed as the main assessment of the 2nd year Team Project module I studied at the University of Birmingham. The fundamental outline was to create a game that exhibited 5 main properties:
- Competitive play - System must allow players to compete against each other.
- Networking - System must allow multiple players over a network on different machines.
- Artificial Intelligence - System must have computer controlled players.
- User interface - System must have a user interface with to interact with the game.
- Audio - System must have mutable sound effects and music.

## My Contribution
My main contribution to the team was the groundwork and developement of the physics engine, including entitiy movement and collision detection. I also played a large role in refactoring the project throughout, removing duplicate code and increasing efficiency. Towards the end of development, I worked on a robust consistency system that would validate the actions of all clients so the gamestate appeared consistent on each players screen.
