# RecipeCooldown
When a player click on a crafting recipe, it sends a request to the server to find that recipe and fill in the appropriate items to the crafting slots.
However, no cool down has been implemented officially, meaning a malicious player can spam packets to the server (Even with just autoclicker) to effectively lag out the server.

This is a simple Fabric mod that prevents excessive crafting recipe request from the client to lag out the server.
Tested on MC 1.17 - 1.19.

## Config
By default, the mod will only allow the player to request a recipe every 100ms (This is usually enough to prevent excessive spamming).  
If you want other value for any reason, create a file called `recipe_cooldown.cfg` in the `config` folder, with a numeric value inside representing the timeout millisecond.

## License
This project is licensed under the MIT License.