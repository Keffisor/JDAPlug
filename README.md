[![Git Releases Version](https://img.shields.io/github/release/Keffisor/JDAExpansion)](https://github.com/Keffisor/JDAExpansion/releases)
[![Last Release](https://img.shields.io/github/release-date/Keffisor/JDAExpansion)](https://github.com/Keffisor/JDAExpansion/releases)
[![Commit Activity](https://img.shields.io/github/commit-activity/m/Keffisor/JDAExpansion)](https://github.com/Keffisor/JDAExpansion/commits/master)
<br>
# JDAExpansion
**JDAExpansion** - Add a console and install/create plugins with a easy api to your bot. It's based on the Discord library [JDA](https://github.com/DV8FromTheWorld/JDA). This library have been inspired by [Bukkit](https://dev.bukkit.org/).

# Features
- Create a console on the Bot for execute commands.
- Install or create plugins for any bot that have this expansion.
- Create configs files for the plugins.
- Easy API and usage.


### Get started
```
public static void main(String[] args) throws LoginException {
       JDA jda = new JDABuilder(AccountType.BOT).setToken(token).build(); //any call of jda
   	   JDAExpansion.start(jda);  
}
```


### Creating commands for Discord and the Console
```
createCommand command = new createCommand(new CommandData("principalcommand", "Some description for slash command"), "!", "principalcommand", "aliase1", "anotheraliase") {
	@Override
	protected void isExecuted(String[] args, CommandSender sender) {
		if(sender instanceof ConsoleCommand) {
			ConsoleCommand e = (ConsoleCommand)sender;
			JDAExpansion.getLogger().info("Hey! This is just a test command for the console :D");
		}
		if(sender instanceof Command) {
			Command e = (Command)sender;
			e.getChannel().sendMessage("Hey! This is just a test command without slash commands :D").queue();
		}
		if(sender instanceof SlashCommand) {
			SlashCommand e = (SlashCommand)sender;
			e.reply("Hey! This is just a test command by slash commands :D").queue();
		}
		sender.sendMessage("This message will sent without caring about the type");
	}
     }; 
}
```
You have to use the class of createCommand which needs a prefix, a principal commands, and aliases of the command (optional).
**The commands on the console doesn't have prefix, if you command is "!test" you have to execute as "test" on the console**


### Creating plugins 
You will need to setup a plugin.yml on the jar without being on a package
```
name: Name of your plugin
main: your.main.class.of.your.plugin
# optional ⬇
author: Keffisor21
version: v2.3
```
Now you will have to create a class extending the PluginListener class
```
public class Main extends PluginListener {

	@Override
	protected void onEnable() {
		JDAExtension.getLogger().info("Plugin enabled");
	}
	
	@Override
	protected void onDisable() {
		JDAExtension.getLogger().info("Plugin disabled");
	}
}
```
In case that you need to register a class with events you must to call this function
```
        @Override
	protected void onEnable() {
		JDAExpansion.registerEvent(new EventClass());
		JDAExpansion.registerEvents(new Event1(), new Event2(), new Event3());
	}
```
After compiling the plugin, you have to move the jar into the plugins folder created by the Bot. You can reload the plugins with the command !reload o !rl and you can see the plugins installed with !plugins or !pl.

### Creating configs for plugins
```
public class Main extends PluginListener {
	public static FileConfiguration config = getConfig("config.yml");
	
	@Override
	protected void onEnable() {
		JDAExpansion.registerEvent(new Event()); 		
	}
	
	@Override
	protected void onDisable() {
		
	}
```
The config file is created into a directory with the name of the plugin in the directory of plugins. The config file **must** be created inside the jar without any package. The data that contains that config will be created into the directory of the plugin.
```
e.getChannel().sendMessage(Main.getInstance().config.getString("Message.NoPermission")).queue(); 
config.set("something", 1); //set data to the config
```
<h2>Images</h2>
<img src="https://cdn.discordapp.com/attachments/628711840175620106/863177554029838416/68747470733a2f2f692e696d6775722e636f6d2f3248375146386f2e706e67.png">
<img src="https://cdn.discordapp.com/attachments/628711840175620106/863177575106740254/68747470733a2f2f692e696d6775722e636f6d2f336e4a387442532e706e67.png">
<img src="https://cdn.discordapp.com/attachments/628711840175620106/863177591308419112/68747470733a2f2f692e696d6775722e636f6d2f4f7136775930672e706e67.png">
<h2><strong>Example</strong></h2>
<p>You can download an example of a plugin <a href="https://keffisor21.com/downloads/CommandCreator.jar">here</a></p>
<br>
<br>
<br>
