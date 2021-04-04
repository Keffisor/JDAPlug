[![Git Releases Version](https://img.shields.io/github/release/m/Keffisor/JDAExpansion.svg)](https://github.com/Keffisor/JDAExpansion/releases)
[![Last Release](https://img.shields.io/github/release-date/m/Keffisor/JDAExpansion.svg?logo=JDAExpansion)](https://github.com/Keffisor/JDAExpansion/releases)
[![Commit Activity](https://img.shields.io/github/commit-activity/m/Keffisor/JDAExpansion)](https://github.com/Keffisor/JDAExpansion/commits/master)
<br>
# JDAExpansion
**JDAExpansion** - Add a console and install/create plugins with a easy api to your bot. It's based on the Discord library [JDA](https://github.com/DV8FromTheWorld/JDA).

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
createCommand command = new createCommand("!", "principalcommand", "aliase1", "anotheraliase") {
			@Override
			protected void isExecutedConsole(String[] args) {
			        JDAExtension.getLogger().info(ConsoleColor.RED+"Exiting the bot..."+ConsoleColor.RESET);
				System.exit(0);
			}
			@Override
			protected void isExecuted(String[] args) {
				MessageReceivedEvent e = eventCommand();
				if(e.getMember().getPermissions().contains(Permission.ADMINISTRATOR)) {
					System.exit(0);
					} else {
				  e.getChannel().sendMessage("No permission").queue();  
					}
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
	}
```
After compiling the plugin, you have to move the jar into the plugins folder created by the Bot. You can reload the plugins with the command !reload o !rl and you can see the plugins installed with !plugins or !pl.

### Creating configs for plugins
```
public class Main extends PluginListener {
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
<br>
<br>
<br>
