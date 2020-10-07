# hevav's RandomTools

Version: 0.0.2<br>
Spigot plugin to give random items from the command or from the kits<br>
Native Spigot Version: 1.16.1<br>
TESTED on 1.12.2 and 1.16.1<br>
[Download](https://github.com/hevav/RandomTools/releases)

## Tools
### Random Give

-   Simplest use, give items from the command:
        ``/rgive <player> <limit> <item 1 id> <item 2 id...>``
-   You can create a kit to make it simpler:
        ``/rkitadd <name> <item 1 id> <item 2 id...>``
-   This command creates a kit with all items without <item n id>:
        ``/rkitadd <name> -<item 1 id> <item 2 id...>``
-   And give that kit:
        ``/rkit <player> <kit name> <limit>``
-   ...or loop that kit:
        ``/rkitloop <player> <kit name> <limit> <interval>``
-   ...stop all loops:
        ``/rkitloop dis``
-   To remove a kit use this command:
        ``/rkitremove <kit name>``
 
 Kits are located in the config.
 
### Random teams

-   To start, you need to create teams:
        ``/rteamcreate [limit]``
-   Then you can use operations for the teams:
        ``/rteam [id] [operation]``
-   And you can remove teams...:
        ``/rteamremove``

#### Operations:
-   Teleport to executor: ``tp``

#### id:
-   Sequence number of a team, starting from 1
-   ``%n+m`` to randomly divide teams by n starting from m.
-   ``*`` for all teams.