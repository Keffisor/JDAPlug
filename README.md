[![Git Releases Version](https://img.shields.io/github/release/Keffisor/JDAExpansion)](https://github.com/Keffisor/JDAExpansion/releases)
[![Last Release](https://img.shields.io/github/release-date/Keffisor/JDAExpansion)](https://github.com/Keffisor/JDAExpansion/releases)
[![Commit Activity](https://img.shields.io/github/commit-activity/m/Keffisor/JDAExpansion)](https://github.com/Keffisor/JDAExpansion/commits/master)
<br>
# JDAExpansion
**JDAExpansion** - Add a console and install/create plugins with a easy api to your bot. It's based on the Discord library [JDA](https://github.com/DV8FromTheWorld/JDA) and inspired on [Bukkit](https://dev.bukkit.org/).

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
public class Main extends JavaPlugin {

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
You can use the api of events by the JDAExpansion without using the ListenerAdapter
```
public class EventTest implements PluginListener {
	/*
	With the new system of events in JDAExpansion you will be able to declare any event with a custom name, you can repeat in the same class
	any event and you can set the priority of execution of that event.
        */
	
	@EventHandler(priority = EventPriority.MONITOR) //set the priority of the event, if it's not set, it will be NORMAL by default.
  	public void test(MessageReceivedEvent e) {
	        System.out.println(e.getMessage().getContentRaw());
  	}

	@EventHandler
	public void test2(MessageReceivedEvent e) {
		System.out.println(e.getAuthor().getAsTag());
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
public class Main extends JavaPlugin {
	public static FileConfiguration config;
	
	@Override
	protected void onEnable() {
		JDAExpansion.registerEvent(new Event());		
		config = getConfig("config.yml");
 		
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
<h2><strong>Screenshots</strong></h2>
<img src="https://i.imgur.com/ftzRALM.png">
<img src="https://i.imgur.com/5c6wrGb.png">
<img src="https://i.imgur.com/ZyRzR6f.png">
<h2><strong>Example</strong></h2>
<p>You can download an example of a plugin <a href="https://keffisor21.com/downloads/CommandCreator.jar">here</a></p>
<br>
<br>
<br>
