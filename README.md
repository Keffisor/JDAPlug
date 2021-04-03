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

