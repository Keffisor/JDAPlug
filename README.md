# JDAExpansion
**JDAExpansion** - Add a console and install/create plugins with a easy api to your bot. It's based on the Discord library [JDA](https://github.com/DV8FromTheWorld/JDA).

# Features
- Create a console on the Bot for execute commands.
- Install or create plugins for any bot that have this expansion.
- Easy API and usage.


### Start the expansion
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
				  e.getChannel().sendMessage("You don't have permissions to do this, you must have the administrator permission").queue();  
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
