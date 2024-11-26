[![Git Releases Version](https://img.shields.io/github/release/Keffisor/JDAPlug)](https://github.com/Keffisor/JDAPlug/releases)
[![Last Release](https://img.shields.io/github/release-date/Keffisor/JDAPlug)](https://github.com/Keffisor/JDAPlug/releases)
[![Commit Activity](https://img.shields.io/github/commit-activity/m/Keffisor/JDAPlug)](https://github.com/Keffisor/JDAPlug/commits/master)
<br>
# JDAPlug
**JDAPlug** - Add a console and install/create plugins with a easy api to your bot. It's based on the Discord library [JDA](https://github.com/DV8FromTheWorld/JDA) and inspired on [Bukkit](https://dev.bukkit.org/).

# Features
- Console for executing commands directly on the bot.
- Install or create plugins for any bot using this expansion.
- Generate configuration yml files for plugins.
- Simple and easy-to-use API.
- Enhanced event management, improving upon the original JDA system.
- Integrated logging system.
- Reload plugin source code while the bot is running.
- Compatible with Java 8 through Java 21.

### Getting Started
You can start the jar file in the regular way for build your project with plugins:
```
@echo OFF
java -Xms512M -Xmx512M -jar JDAPlug.jar
PAUSE
```
For integration with an existing bot project, wrap the JDA instance in JDAPlug:
```
public static void main(String[] args) throws LoginException {
       JDA jda = new JDABuilder(AccountType.BOT).setToken(token).build(); //any call of jda
       JDAPlug.start(jda);  
}
```

### Creating plugins 
First, add a ``plugin.yml`` in the root of your plugin jar (with no packages):
```
name: Name of your plugin
main: your.main.class.of.your.plugin
# optional ⬇
author: Keffisor21
version: v2.3
depend: [Vault, AnyOtherPlugin]
```
Then, create a class extending ``JavaPlugin``:
```
public class Main extends JavaPlugin {

	@Override
	protected void onEnable() {
		JDAPlug.getLogger().info("My plugin enabled");
	}
	
	@Override
	protected void onDisable() {
		JDAPlug.getLogger().info("My plugin disabled");
	}
}
```
You can define events using JDAPlug’s API implementing ``PluginListener``
```
public class EventTest implements PluginListener {
	/*
	With the new system of events in JDAPlug you will be able to declare any event with a custom name, you can repeat in the same class
	any event and you can set the priority of execution of that event.
        */
	
	@EventHandler(priority = EventPriority.MONITOR) //set the priority of the event, if it's not set, it will be NORMAL by default.
  	public void test(MessageReceivedEvent e) {
            JDAPlug.getLogger().info(e.getMessage().getContentRaw());
  	}

	@EventHandler
	public void test2(MessageReceivedEvent e) {
            JDAPlug.getLogger().info(e.getAuthor().getAsTag());
	}

}
```
To register events:
```
    @Override
	protected void onEnable() {
		JDAPlug.registerEvent(new EventClass());
		JDAPlug.registerEvents(new Event1(), new Event2(), new Event3());
	}
```
After compiling, move the jar into the ``plugins`` folder created by the bot. Reload plugins using !reload or !rl, and list installed plugins with !plugins or !pl.

### Creating commands for Discord and the Console
```
CommandExecutor command = new CommandExecutor(new CommandData("principalcommand", "Some description for slash command"), "!", "principalcommand", "aliase1", "anotheraliase") {
	@Override
	protected void isExecuted(String[] args, CommandSender sender) {
		if(sender instanceof ConsoleCommand) {
			ConsoleCommand e = (ConsoleCommand)sender;
			JDAPlug.getLogger().info("Hey! This is just a test command for the console :D");
		}
		if(sender instanceof TextCommand) {
			TextCommand e = (TextCommand)sender;
			e.getChannel().sendMessage("Hey! This is just a test command without slash commands :D").queue();
		}
		if(sender instanceof SlashCommand) {
			SlashCommand e = (SlashCommand)sender;
			e.reply("Hey! This is just a test command by slash commands :D").queue();
		}
		sender.sendSenderMessage("This message will sent without caring about the type");
	}
  }; 
}

JDAPlug.registerEvent(command);
```
You have to use the class of CommandExecutor which you can set a slash command, prefix, a principal command or command aliases.
**The commands on the console doesn't have prefix, if you command is "!test" you have to execute as "test" on the console**

### Creating configs for Plugins
```
public class Main extends JavaPlugin {
	public static FileConfiguration config;
	
	@Override
	protected void onEnable() {
		JDAPlug.registerEvent(new Event());		
		config = getConfig("config.yml");
 		
	}
	
	@Override
	protected void onDisable() {
		
	}
}
```
The config file is stored in the plugin's directory. You can access or modify data as needed:
```
e.getChannel().sendMessage(Main.getInstance().config.getString("Message.NoPermission")).queue(); 
config.set("something", 1); //set data to the config
```
Works exactly the same as Bukkit. In addition, the yml files can have comments, will be not removed if the comment has a unique line only for them.
<h2><strong>Screenshots</strong></h2>
<img src="https://i.imgur.com/ftzRALM.png">
<img src="https://i.imgur.com/SCTW9Cu.png">
<img src="https://i.imgur.com/ZyRzR6f.png">
<h2><strong>Plugin examples</strong></h2>

- <strong>BotRPC</strong> - Set custom RPC for your bot with a customizable config for rotating activities. <a href="https://keffisor21.com/jdaplug/BotRPC/BotRPC.jar" rel="nofollow">Download here</a>  
``` Config.yml file
#COMPETING, PLAYING, LISTENING, WATCHING, STREAMING
RPCs:
- LISTENING:Test
- LISTENING:Message 2
```

- <strong>Vault</strong> - An economy library with a simple API, inspired by Bukkit’s original Vault plugin. <a href="https://keffisor21.com/jdaplug/Vault/Vault.jar" rel="nofollow">Download here</a>
```java
// Code usage:
VaultAPI.addCoins(Member, Coins);
VaultAPI.removeCoins(Member, Coins);
VaultAPI.setCoins(Member, Coins);
VaultAPI.getCoins(Member, Coins);

/*
For using it, just add the jar to the library path of your project and install the plugin in your plugins folder.
That's all you have to do!   
 */
```
```yaml
# Config file. The plugin also adds slash commands for the members to check their coins or the 
  # staff with admin permissions to manage the coins of other member. Uses SQLite as a db.
SlashCommands:
  Coins:
    Command: coins
    Description: Check your coins on the server
    Argument:
      Member:
        Name: member
        Description: Check the coins of a member
  CoinsAdd:
    Command: coinsadd
    Description: Add coins to a member
    Permission: ADMINISTRATOR
    Argument:
      Member:
        Name: member
        Description: Member to add the coins
      Coins:
        Name: coins
        Description: The coins to add
  CoinsRemove:
    Command: coinsremove
    Description: Remove coins to a member
    Permission: ADMINISTRATOR
    Argument:
      Member:
        Name: member
        Description: Member to remove the coins
      Coins:
        Name: coins
        Description: The coins to remove
Messages:
  Coins:
    Self:
      Title: ℹ️  Your coins  🪙
      Description: You have **%coins coins**
      Color: '#0076D7'
      FooterText: Vault
      FooterImage: ''
    Member:
      Title: ℹ️  %name's coins  🪙
      Description: The member %name has **%coins coins**
      Color: '#0076D7'
      FooterText: Vault
      FooterImage: ''
  CoinsAdd: Added %quantity coins to %member
  CoinsRemove: Added %quantity coins to %member
  
```
- <strong>Casino</strong> - A fun and interactive horse racing game. Customize betting options and manage game settings easily. Requires Vault plugin installed. <a href="https://keffisor21.com/jdaplug/Casino/Casino.jar" rel="nofollow">Download here</a>
``` Config.yml file
HorseRace:
  Settings:
    MaxBet: 100000
    MinBet: 100
    WinMultiplier: 8
SlashCommands:
  HorseRace:
    Command: horserace
    Description: Bet coins in a horse race game!
    Argument:
      Coins:
        Name: coins
        Description: The coins to bet
      Horses:
        Name: horse
        Description: The number of the horse that you bet to win
Messages:
  HorseRace:
    Error:
      InvalidArguments:
        Title: ℹ️  Incorrect format
        Description: 'Please enter a valid bet. Usage: `!horserace <bet> <number of
          horse>`'
        Color: '#0078d7'
        FooterText: Casino
      AlreadyInGame:
        Title: ❌  Error
        Description: You're already in a race horse game!
        Color: '#ff0000'
        FooterText: Casino
      InvalidBetNumber:
        Title: ℹ️  Incorrect format
        Description: 'Please enter a number valid bet. Usage: `!horserace <bet> <number
          of horse>`'
        Color: '#0078d7'
        FooterText: Casino
      MaxBetSurpassed:
        Title: ❌  Error
        Description: The maximum bet of this game is %max_bet%!
        Color: '#ff0000'
        FooterText: Casino
      MinBet:
        Title: ℹ️  Invalid bet
        Description: Please enter a bet equal or higher than %min_bet%!
        Color: '#0078d7'
        FooterText: Casino
      InvalidHorseNumber:
        Title: ℹ️  Horse race
        Description: Please enter a number valid horse between 1 and 8!
        Color: '#0078d7'
        FooterText: Casino
      NotEnoughCoins:
        Title: ❌  Error
        Description: You don't have enough coins to make that bet!
        Color: '#ff0000'
        FooterText: Casino
    Game:
      Start:
        Title: Horse race 🏇
        Description: |-
          **Your bet:** %coins% coins

          **Your racer:** `%horse%`
        Color: '#ffa500'
        FooterText: Casino
      Won:
        Title: Horse race 🏇 - You won!
        Description: |2-


          **RACER  `%winner%` WON!**

          **Profit:** %coins_earned% coins

          **Your coins:** %coins% coins
        Color: '#00FF00'
        FooterText: Casino
      Lost:
        Title: Horse race 🏇 - You lost!
        Description: |2-


          **RACER  `%winner%` LOST!**

          **Lost:** %coins_lost% coins

          **Your coins:** %coins% coins
        Color: '#FF0000'
        FooterText: Casino
# Options: EU or US
General:
  NumberFormat: US
```

<br>
<br>
<br>
