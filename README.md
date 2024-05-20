# Ultimate Robot to Kill Everyone
This code is our design for a Robocode tank. Our concept is to make a tank that can try to lead its aim to take out tanks that are moving, yet also be able to start doing so from an advantageous position.

# By
Burin Intachuen 6480250

Possathorn Sujipisut 6480274

Tanakorn Mankhetwit 6480282

Mhadhanagual Charoenphon 6391199


# How it works
## Concept
When testing out different Robocode tank designs, we discovered that the built-in tank “Corner Bot” survived much longer than other designs that are centered around fighting around the middle of the arena. However, this design would not fully win many battles, as being a sitting target would make it easy for other designs to shoot and destroy “Corner Bot”.

The reason for “Corner Bot”'s success is that it has only 2 sides to guard. We decided to use this concept by having the bot start from the closest corner before moving out to find a target. That way, the bot still somewhat has 2 walls behind it, making it only have to focus on the front. First part of our code is just that, finding the closest corner.

![Alt text](images/tank1.png)

Then make a beeline for that corner using the function goCorner()

![Alt text](images/tank3.png)

As for how to attack, our bot is designed similar to “Ram Bot”, where it follows and tries to ram the opponent while shooting at it at the same time. We also added a simple “lead” to our shooting. Essentially shooting to the left and right of where the robot was at that location. By the time that the bullet travels, the opponent in theory would have reached that position.

![Alt text](images/tank2.png)

## Weaknesses
Our Robocode bot works relatively well with robots that move around in general or those that try to escape by moving to the side. This is thanks to the alternating shooting of the bot, where it swaps between shooting slightly to the left and right of where the enemy actually is, to catch them while they are moving. However, the tradeoff is that bots that are standing still or moving directly towards our bot would have a massive advantage. Which ironically includes the “Ram Bot” that we have tried to imitate.
