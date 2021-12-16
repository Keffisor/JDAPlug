package com.Keffisor21.JDAExpansion.Event;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Nonnull;

import com.Keffisor21.JDAExpansion.Event.API.MessageConsoleReceivedEvent;

import net.dv8tion.jda.api.events.DisconnectEvent;
import net.dv8tion.jda.api.events.ExceptionEvent;
import net.dv8tion.jda.api.events.GatewayPingEvent;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.RawGatewayEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.ReconnectedEvent;
import net.dv8tion.jda.api.events.ResumedEvent;
import net.dv8tion.jda.api.events.ShutdownEvent;
import net.dv8tion.jda.api.events.StatusChangeEvent;
import net.dv8tion.jda.api.events.UpdateEvent;
import net.dv8tion.jda.api.events.channel.category.CategoryCreateEvent;
import net.dv8tion.jda.api.events.channel.category.CategoryDeleteEvent;
import net.dv8tion.jda.api.events.channel.category.GenericCategoryEvent;
import net.dv8tion.jda.api.events.channel.category.update.CategoryUpdateNameEvent;
import net.dv8tion.jda.api.events.channel.category.update.CategoryUpdatePermissionsEvent;
import net.dv8tion.jda.api.events.channel.category.update.CategoryUpdatePositionEvent;
import net.dv8tion.jda.api.events.channel.category.update.GenericCategoryUpdateEvent;
import net.dv8tion.jda.api.events.channel.priv.PrivateChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.priv.PrivateChannelDeleteEvent;
import net.dv8tion.jda.api.events.channel.store.GenericStoreChannelEvent;
import net.dv8tion.jda.api.events.channel.store.StoreChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.store.StoreChannelDeleteEvent;
import net.dv8tion.jda.api.events.channel.store.update.GenericStoreChannelUpdateEvent;
import net.dv8tion.jda.api.events.channel.store.update.StoreChannelUpdateNameEvent;
import net.dv8tion.jda.api.events.channel.store.update.StoreChannelUpdatePermissionsEvent;
import net.dv8tion.jda.api.events.channel.store.update.StoreChannelUpdatePositionEvent;
import net.dv8tion.jda.api.events.channel.text.GenericTextChannelEvent;
import net.dv8tion.jda.api.events.channel.text.TextChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.text.TextChannelDeleteEvent;
import net.dv8tion.jda.api.events.channel.text.update.GenericTextChannelUpdateEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdateNSFWEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdateNameEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdateNewsEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdateParentEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdatePermissionsEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdatePositionEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdateSlowmodeEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdateTopicEvent;
import net.dv8tion.jda.api.events.channel.voice.GenericVoiceChannelEvent;
import net.dv8tion.jda.api.events.channel.voice.VoiceChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.voice.VoiceChannelDeleteEvent;
import net.dv8tion.jda.api.events.channel.voice.update.GenericVoiceChannelUpdateEvent;
import net.dv8tion.jda.api.events.channel.voice.update.VoiceChannelUpdateBitrateEvent;
import net.dv8tion.jda.api.events.channel.voice.update.VoiceChannelUpdateNameEvent;
import net.dv8tion.jda.api.events.channel.voice.update.VoiceChannelUpdateParentEvent;
import net.dv8tion.jda.api.events.channel.voice.update.VoiceChannelUpdatePermissionsEvent;
import net.dv8tion.jda.api.events.channel.voice.update.VoiceChannelUpdatePositionEvent;
import net.dv8tion.jda.api.events.channel.voice.update.VoiceChannelUpdateUserLimitEvent;
import net.dv8tion.jda.api.events.emote.EmoteAddedEvent;
import net.dv8tion.jda.api.events.emote.EmoteRemovedEvent;
import net.dv8tion.jda.api.events.emote.GenericEmoteEvent;
import net.dv8tion.jda.api.events.emote.update.EmoteUpdateNameEvent;
import net.dv8tion.jda.api.events.emote.update.EmoteUpdateRolesEvent;
import net.dv8tion.jda.api.events.emote.update.GenericEmoteUpdateEvent;
import net.dv8tion.jda.api.events.guild.GenericGuildEvent;
import net.dv8tion.jda.api.events.guild.GuildAvailableEvent;
import net.dv8tion.jda.api.events.guild.GuildBanEvent;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.guild.GuildTimeoutEvent;
import net.dv8tion.jda.api.events.guild.GuildUnavailableEvent;
import net.dv8tion.jda.api.events.guild.GuildUnbanEvent;
import net.dv8tion.jda.api.events.guild.UnavailableGuildJoinedEvent;
import net.dv8tion.jda.api.events.guild.UnavailableGuildLeaveEvent;
import net.dv8tion.jda.api.events.guild.invite.GenericGuildInviteEvent;
import net.dv8tion.jda.api.events.guild.invite.GuildInviteCreateEvent;
import net.dv8tion.jda.api.events.guild.invite.GuildInviteDeleteEvent;
import net.dv8tion.jda.api.events.guild.member.GenericGuildMemberEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleRemoveEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberUpdateEvent;
import net.dv8tion.jda.api.events.guild.member.update.GenericGuildMemberUpdateEvent;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateBoostTimeEvent;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateNicknameEvent;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdatePendingEvent;
import net.dv8tion.jda.api.events.guild.override.GenericPermissionOverrideEvent;
import net.dv8tion.jda.api.events.guild.override.PermissionOverrideCreateEvent;
import net.dv8tion.jda.api.events.guild.override.PermissionOverrideDeleteEvent;
import net.dv8tion.jda.api.events.guild.override.PermissionOverrideUpdateEvent;
import net.dv8tion.jda.api.events.guild.update.GenericGuildUpdateEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateAfkChannelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateAfkTimeoutEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateBannerEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateBoostCountEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateBoostTierEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateCommunityUpdatesChannelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateDescriptionEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateExplicitContentLevelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateFeaturesEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateIconEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateLocaleEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateMFALevelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateMaxMembersEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateMaxPresencesEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateNameEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateNotificationLevelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateOwnerEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateRegionEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateRulesChannelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateSplashEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateSystemChannelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateVanityCodeEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateVerificationLevelEvent;
import net.dv8tion.jda.api.events.guild.voice.GenericGuildVoiceEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceDeafenEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceGuildDeafenEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceGuildMuteEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMuteEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceSelfDeafenEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceSelfMuteEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceStreamEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceSuppressEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.events.http.HttpRequestEvent;
import net.dv8tion.jda.api.events.message.GenericMessageEvent;
import net.dv8tion.jda.api.events.message.MessageBulkDeleteEvent;
import net.dv8tion.jda.api.events.message.MessageDeleteEvent;
import net.dv8tion.jda.api.events.message.MessageEmbedEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.MessageUpdateEvent;
import net.dv8tion.jda.api.events.message.guild.GenericGuildMessageEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageDeleteEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageEmbedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageUpdateEvent;
import net.dv8tion.jda.api.events.message.guild.react.GenericGuildMessageReactionEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveAllEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEmoteEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEvent;
import net.dv8tion.jda.api.events.message.priv.GenericPrivateMessageEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageDeleteEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageEmbedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageUpdateEvent;
import net.dv8tion.jda.api.events.message.priv.react.GenericPrivateMessageReactionEvent;
import net.dv8tion.jda.api.events.message.priv.react.PrivateMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.priv.react.PrivateMessageReactionRemoveEvent;
import net.dv8tion.jda.api.events.message.react.GenericMessageReactionEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveAllEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEmoteEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.events.role.GenericRoleEvent;
import net.dv8tion.jda.api.events.role.RoleCreateEvent;
import net.dv8tion.jda.api.events.role.RoleDeleteEvent;
import net.dv8tion.jda.api.events.role.update.GenericRoleUpdateEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdateColorEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdateHoistedEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdateMentionableEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdateNameEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdatePermissionsEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdatePositionEvent;
import net.dv8tion.jda.api.events.self.GenericSelfUpdateEvent;
import net.dv8tion.jda.api.events.self.SelfUpdateAvatarEvent;
import net.dv8tion.jda.api.events.self.SelfUpdateMFAEvent;
import net.dv8tion.jda.api.events.self.SelfUpdateNameEvent;
import net.dv8tion.jda.api.events.self.SelfUpdateVerifiedEvent;
import net.dv8tion.jda.api.events.user.GenericUserEvent;
import net.dv8tion.jda.api.events.user.UserActivityEndEvent;
import net.dv8tion.jda.api.events.user.UserActivityStartEvent;
import net.dv8tion.jda.api.events.user.UserTypingEvent;
import net.dv8tion.jda.api.events.user.update.GenericUserPresenceEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateActivitiesEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateActivityOrderEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateAvatarEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateDiscriminatorEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateFlagsEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateNameEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateOnlineStatusEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class EventsRegistration extends ListenerAdapter {
	public static HashMap<Class<?>, List<EventValues>> registered = new HashMap<>();
	
	public static void loadEvent(PluginListener o) {
		for(Method method : o.getClass().getDeclaredMethods()) {
			EventHandler e = method.getAnnotation(EventHandler.class);
			if(e == null || method.getParameters().length != 1) continue;
			Class<?> type = method.getParameters()[0].getType();
			setEventType(type, new EventValues(o, type, method, e));
		}
	}
	
	public static void setEventType(Class<?> o, EventValues values) {
		new EventsRegistration().getListEvents().forEach(cl -> {
			try {
				if(o.getName().equalsIgnoreCase(cl.getName())) {
					if(registered.get(cl) == null) registered.put(cl, new ArrayList<>());
					List<EventValues> list = registered.get(cl);
					if(list.stream().anyMatch(value -> value.getClass().getName().equals(values.getObject().getClass().getName()))) return;
					list.add(values);
					registered.put(cl, list);
				}
			} catch (ClassCastException e) {
				// TODO: handle exception
			}
		});	
	}
	
	public List<EventValues> getClass(Class<?> o) {
		if(registered.get(o) == null) return new ArrayList<EventValues>();
		return registered.get(o);
	}
	
	public List<Class<?>> getListEvents() {
		List<Class<?>> cls = new ArrayList<>();
		for(Method method : ListenerAdapter.class.getDeclaredMethods()) {
			if(method.getParameters().length != 1) continue;
			Nonnull nonnull = method.getParameters()[0].getAnnotation(Nonnull.class);
			if(nonnull == null) continue;
			cls.add(method.getParameters()[0].getType());
		}
		cls.add(MessageConsoleReceivedEvent.class);
		return cls;
	}
	
	public void executeClass(Class<?> cl, Object e) {
		orderPriority(getClass(cl)).forEach(value -> {
			try {
				try {
				value.getMethod().invoke(value.getObject(), e);
				} catch(IllegalArgumentException ex) {}
			} catch (Exception ex) {
			}
		});
	}
	
	public List<EventValues> orderPriority(List<EventValues> values) {
		LinkedList<EventValues> result = new LinkedList<>();
		for(EventPriority priority : EventPriority.values()) {
			values.stream().filter(v -> v.getAnnotation().priority().equals(priority)).forEach(result::add);
		}
		Collections.reverse(result);
		return result;
	}
	
	@Override public void onTextChannelUpdatePermissions(TextChannelUpdatePermissionsEvent event) {executeClass(TextChannelUpdatePermissionsEvent.class, event);}
	
	@Override public void onStoreChannelUpdatePermissions(StoreChannelUpdatePermissionsEvent event) {executeClass(StoreChannelUpdatePermissionsEvent.class, event);}

    @Override public void onVoiceChannelUpdatePermissions(VoiceChannelUpdatePermissionsEvent event) {executeClass(VoiceChannelUpdatePermissionsEvent.class, event);}

    @Override public void onCategoryUpdatePermissions(CategoryUpdatePermissionsEvent event) {executeClass(CategoryUpdatePermissionsEvent.class, event);}

    @Override public void onGuildMemberLeave(GuildMemberLeaveEvent event) {executeClass(GuildMemberLeaveEvent.class, event);}

    @Override public void onResume(ResumedEvent event) {executeClass(ResumedEvent.class, event);}

    @Override public void onReconnect(ReconnectedEvent event) {executeClass(ReconnectedEvent.class, event);}

    @Override public void onGenericEvent(GenericEvent event) {executeClass(GenericEvent.class, event);}
    @Override public void onGenericUpdate(UpdateEvent<?, ?> event) {executeClass(UpdateEvent.class, event);}
	@Override public void onRawGateway(RawGatewayEvent event) {executeClass(RawGatewayEvent.class, event);}
	@Override public void onGatewayPing(GatewayPingEvent event) {executeClass(GatewayPingEvent.class, event);}

    //JDA Events
	@Override public void onReady(ReadyEvent event) {executeClass(ReadyEvent.class, event);}
	@Override public void onResumed(ResumedEvent event) {executeClass(ResumedEvent.class, event);}
	@Override public void onReconnected(ReconnectedEvent event) {executeClass(ReconnectedEvent.class, event);}
	@Override public void onDisconnect(DisconnectEvent event) {executeClass(DisconnectEvent.class, event);}
	@Override public void onShutdown(ShutdownEvent event) {executeClass(ShutdownEvent.class, event);}
	@Override public void onStatusChange(StatusChangeEvent event) {executeClass(StatusChangeEvent.class, event);}
	@Override public void onException(ExceptionEvent event) {executeClass(ExceptionEvent.class, event);}

    //User Events
	@Override public void onUserUpdateName(UserUpdateNameEvent event) {executeClass(UserUpdateNameEvent.class, event);}
	@Override public void onUserUpdateDiscriminator(UserUpdateDiscriminatorEvent event) {executeClass(UserUpdateDiscriminatorEvent.class, event);}
	@Override public void onUserUpdateAvatar(UserUpdateAvatarEvent event) {executeClass(UserUpdateAvatarEvent.class, event);}
	@Override public void onUserUpdateOnlineStatus(UserUpdateOnlineStatusEvent event) {executeClass(UserUpdateOnlineStatusEvent.class, event);}
	@Override public void onUserUpdateActivityOrder(UserUpdateActivityOrderEvent event) {executeClass(UserUpdateActivityOrderEvent.class, event);}
	@Override public void onUserUpdateFlags(UserUpdateFlagsEvent event) {executeClass(UserUpdateFlagsEvent.class, event);}
	@Override public void onUserTyping(UserTypingEvent event) {executeClass(UserTypingEvent.class, event);}
	@Override public void onUserActivityStart(UserActivityStartEvent event) {executeClass(UserActivityStartEvent.class, event);}
	@Override public void onUserActivityEnd(UserActivityEndEvent event) {executeClass(UserActivityEndEvent.class, event);}
	@Override public void onUserUpdateActivities(UserUpdateActivitiesEvent event) {executeClass(UserUpdateActivitiesEvent.class, event);}

    //Self Events. Fires only in relation to the currently logged in account.
	@Override public void onSelfUpdateAvatar(SelfUpdateAvatarEvent event) {executeClass(SelfUpdateAvatarEvent.class, event);}
	@Override public void onSelfUpdateMFA(SelfUpdateMFAEvent event) {executeClass(SelfUpdateMFAEvent.class, event);}
	@Override public void onSelfUpdateName(SelfUpdateNameEvent event) {executeClass(SelfUpdateNameEvent.class, event);}
	@Override public void onSelfUpdateVerified(SelfUpdateVerifiedEvent event) {executeClass(SelfUpdateVerifiedEvent.class, event);}

    //Message Events
    //Guild (TextChannel) Message Events
	@Override public void onGuildMessageReceived(GuildMessageReceivedEvent event) {executeClass(GuildMessageReceivedEvent.class, event);}
	@Override public void onGuildMessageUpdate(GuildMessageUpdateEvent event) {executeClass(GuildMessageUpdateEvent.class, event);}
	@Override public void onGuildMessageDelete(GuildMessageDeleteEvent event) {executeClass(GuildMessageDeleteEvent.class, event);}
	@Override public void onGuildMessageEmbed(GuildMessageEmbedEvent event) {executeClass(GuildMessageEmbedEvent.class, event);}
	@Override public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event) {executeClass(GuildMessageReactionAddEvent.class, event);}
	@Override public void onGuildMessageReactionRemove(GuildMessageReactionRemoveEvent event) {executeClass(GuildMessageReactionRemoveEvent.class, event);}
	@Override public void onGuildMessageReactionRemoveAll(GuildMessageReactionRemoveAllEvent event) {executeClass(GuildMessageReactionRemoveAllEvent.class, event);}
	@Override public void onGuildMessageReactionRemoveEmote(GuildMessageReactionRemoveEmoteEvent event) {executeClass(GuildMessageReactionRemoveEmoteEvent.class, event);}

    //Private Message Events
	@Override public void onPrivateMessageReceived(PrivateMessageReceivedEvent event) {executeClass(PrivateMessageReceivedEvent.class, event);}
	@Override public void onPrivateMessageUpdate(PrivateMessageUpdateEvent event) {executeClass(PrivateMessageUpdateEvent.class, event);}
	@Override public void onPrivateMessageDelete(PrivateMessageDeleteEvent event) {executeClass(PrivateMessageDeleteEvent.class, event);}
	@Override public void onPrivateMessageEmbed(PrivateMessageEmbedEvent event) {executeClass(PrivateMessageEmbedEvent.class, event);}
	@Override public void onPrivateMessageReactionAdd(PrivateMessageReactionAddEvent event) {executeClass(PrivateMessageReactionAddEvent.class, event);}
	@Override public void onPrivateMessageReactionRemove(PrivateMessageReactionRemoveEvent event) {executeClass(PrivateMessageReactionRemoveEvent.class, event);}

    //Combined Message Events (Combines Guild and Private message into 1 event)
	@Override public void onMessageReceived(MessageReceivedEvent event) {executeClass(MessageReceivedEvent.class, event);}
	@Override public void onMessageUpdate(MessageUpdateEvent event) {executeClass(MessageUpdateEvent.class, event);}
	@Override public void onMessageDelete(MessageDeleteEvent event) {executeClass(MessageDeleteEvent.class, event);}
	@Override public void onMessageBulkDelete(MessageBulkDeleteEvent event) {executeClass(MessageBulkDeleteEvent.class, event);}
	@Override public void onMessageEmbed(MessageEmbedEvent event) {executeClass(MessageEmbedEvent.class, event);}
	@Override public void onMessageReactionAdd(MessageReactionAddEvent event) {executeClass(MessageReactionAddEvent.class, event);}
	@Override public void onMessageReactionRemove(MessageReactionRemoveEvent event) {executeClass(MessageReactionRemoveEvent.class, event);}
	@Override public void onMessageReactionRemoveAll(MessageReactionRemoveAllEvent event) {executeClass(MessageReactionRemoveAllEvent.class, event);}
	@Override public void onMessageReactionRemoveEmote(MessageReactionRemoveEmoteEvent event) {executeClass(MessageReactionRemoveEvent.class, event);}

    //PermissionOverride Events
	@Override public void onPermissionOverrideDelete(PermissionOverrideDeleteEvent event) {executeClass(PermissionOverrideDeleteEvent.class, event);}
	@Override public void onPermissionOverrideUpdate(PermissionOverrideUpdateEvent event) {executeClass(PermissionOverrideUpdateEvent.class, event);}
	@Override public void onPermissionOverrideCreate(PermissionOverrideCreateEvent event) {executeClass(PermissionOverrideCreateEvent.class, event);}

    //StoreChannel Events
	@Override public void onStoreChannelDelete(StoreChannelDeleteEvent event) {executeClass(StoreChannelDeleteEvent.class, event);}
	@Override public void onStoreChannelUpdateName(StoreChannelUpdateNameEvent event) {executeClass(StoreChannelUpdateNameEvent.class, event);}
	@Override public void onStoreChannelUpdatePosition(StoreChannelUpdatePositionEvent event) {executeClass(StoreChannelUpdatePositionEvent.class, event);}
	@Override public void onStoreChannelCreate(StoreChannelCreateEvent event) {executeClass(StoreChannelCreateEvent.class, event);}

    //TextChannel Events
	@Override public void onTextChannelDelete(TextChannelDeleteEvent event) {executeClass(TextChannelDeleteEvent.class, event);}
	@Override public void onTextChannelUpdateName(TextChannelUpdateNameEvent event) {executeClass(TextChannelUpdateNameEvent.class, event);}
	@Override public void onTextChannelUpdateTopic(TextChannelUpdateTopicEvent event) {executeClass(TextChannelUpdateTopicEvent.class, event);}
	@Override public void onTextChannelUpdatePosition(TextChannelUpdatePositionEvent event) {executeClass(TextChannelUpdatePositionEvent.class, event);}
	@Override public void onTextChannelUpdateNSFW(TextChannelUpdateNSFWEvent event) {executeClass(TextChannelUpdateNSFWEvent.class, event);}
	@Override public void onTextChannelUpdateParent(TextChannelUpdateParentEvent event) {executeClass(TextChannelUpdateParentEvent.class, event);}
	@Override public void onTextChannelUpdateSlowmode(TextChannelUpdateSlowmodeEvent event) {executeClass(TextChannelUpdateSlowmodeEvent.class, event);}
	@Override public void onTextChannelUpdateNews(TextChannelUpdateNewsEvent event) {executeClass(TextChannelUpdateNewsEvent.class, event);}
	@Override public void onTextChannelCreate(TextChannelCreateEvent event) {executeClass(TextChannelCreateEvent.class, event);}

    //VoiceChannel Events
	@Override public void onVoiceChannelDelete(VoiceChannelDeleteEvent event) {executeClass(VoiceChannelDeleteEvent.class, event);}
	@Override public void onVoiceChannelUpdateName(VoiceChannelUpdateNameEvent event) {executeClass(VoiceChannelUpdateNameEvent.class, event);}
	@Override public void onVoiceChannelUpdatePosition(VoiceChannelUpdatePositionEvent event) {executeClass(VoiceChannelUpdatePositionEvent.class, event);}
	@Override public void onVoiceChannelUpdateUserLimit(VoiceChannelUpdateUserLimitEvent event) {executeClass(VoiceChannelUpdateUserLimitEvent.class, event);}
	@Override public void onVoiceChannelUpdateBitrate(VoiceChannelUpdateBitrateEvent event) {executeClass(VoiceChannelUpdateBitrateEvent.class, event);}
	@Override public void onVoiceChannelUpdateParent(VoiceChannelUpdateParentEvent event) {executeClass(VoiceChannelUpdateParentEvent.class, event);}
	@Override public void onVoiceChannelCreate(VoiceChannelCreateEvent event) {executeClass(VoiceChannelCreateEvent.class, event);}

    //Category Events
	@Override public void onCategoryDelete(CategoryDeleteEvent event) {executeClass(CategoryDeleteEvent.class, event);}
    @Override public void onCategoryUpdateName(CategoryUpdateNameEvent event) {executeClass(CategoryUpdateNameEvent.class, event);}
    @Override public void onCategoryUpdatePosition(CategoryUpdatePositionEvent event) {executeClass(CategoryUpdatePositionEvent.class, event);}
    @Override public void onCategoryCreate(CategoryCreateEvent event) {executeClass(CategoryCreateEvent.class, event);}

    //PrivateChannel Events
    @Override public void onPrivateChannelCreate(PrivateChannelCreateEvent event) {executeClass(PrivateChannelCreateEvent.class, event);}
    @Override public void onPrivateChannelDelete(PrivateChannelDeleteEvent event) {executeClass(PrivateChannelDeleteEvent.class, event);}

    //Guild Events
    @Override public void onGuildReady(GuildReadyEvent event) {executeClass(GuildReadyEvent.class, event);}
    @Override public void onGuildTimeout(GuildTimeoutEvent event) {executeClass(GuildTimeoutEvent.class, event);}
    @Override public void onGuildJoin(GuildJoinEvent event) {executeClass(GuildJoinEvent.class, event);}
    @Override public void onGuildLeave(GuildLeaveEvent event) {executeClass(GuildLeaveEvent.class, event);}
    @Override public void onGuildAvailable(GuildAvailableEvent event) {executeClass(GuildAvailableEvent.class, event);}
    @Override public void onGuildUnavailable(GuildUnavailableEvent event) {executeClass(GuildUnavailableEvent.class, event);}
    @Override public void onUnavailableGuildJoined(UnavailableGuildJoinedEvent event) {executeClass(UnavailableGuildJoinedEvent.class, event);}
    @Override public void onUnavailableGuildLeave(UnavailableGuildLeaveEvent event) {executeClass(UnavailableGuildLeaveEvent.class, event);}
    @Override public void onGuildBan(GuildBanEvent event) {executeClass(GuildBanEvent.class, event);}
    @Override public void onGuildUnban(GuildUnbanEvent event) {executeClass(GuildUnbanEvent.class, event);}
    @Override public void onGuildMemberRemove(GuildMemberRemoveEvent event) {executeClass(GuildMemberRemoveEvent.class, event);}

    //Guild Update Events
    @Override public void onGuildUpdateAfkChannel(GuildUpdateAfkChannelEvent event) {executeClass(GuildUpdateAfkChannelEvent.class, event);}
    @Override public void onGuildUpdateSystemChannel(GuildUpdateSystemChannelEvent event) {executeClass(GuildUpdateSystemChannelEvent.class, event);}
    @Override public void onGuildUpdateRulesChannel(GuildUpdateRulesChannelEvent event) {executeClass(GuildUpdateRulesChannelEvent.class, event);}
    @Override public void onGuildUpdateCommunityUpdatesChannel(GuildUpdateCommunityUpdatesChannelEvent event) {executeClass(GuildUpdateCommunityUpdatesChannelEvent.class, event);}
    @Override public void onGuildUpdateAfkTimeout(GuildUpdateAfkTimeoutEvent event) {executeClass(GuildUpdateAfkTimeoutEvent.class, event);}
    @Override public void onGuildUpdateExplicitContentLevel(GuildUpdateExplicitContentLevelEvent event) {executeClass(GuildUpdateExplicitContentLevelEvent.class, event);}
    @Override public void onGuildUpdateIcon(GuildUpdateIconEvent event) {executeClass(GuildUpdateIconEvent.class, event);}
    @Override public void onGuildUpdateMFALevel(GuildUpdateMFALevelEvent event) {executeClass(GuildUpdateMFALevelEvent.class, event);}
    @Override public void onGuildUpdateName(GuildUpdateNameEvent event){executeClass(GuildUpdateNameEvent.class, event);}
    @Override public void onGuildUpdateNotificationLevel(GuildUpdateNotificationLevelEvent event) {executeClass(GuildUpdateNotificationLevelEvent.class, event);}
    @Override public void onGuildUpdateOwner(GuildUpdateOwnerEvent event) {executeClass(GuildUpdateOwnerEvent.class, event);}
    @Override public void onGuildUpdateRegion(GuildUpdateRegionEvent event) {executeClass(GuildUpdateRegionEvent.class, event);}
    @Override public void onGuildUpdateSplash(GuildUpdateSplashEvent event) {executeClass(GuildUpdateSplashEvent.class, event);}
    @Override public void onGuildUpdateVerificationLevel(GuildUpdateVerificationLevelEvent event) {executeClass(GuildUpdateVerificationLevelEvent.class, event);}
    @Override public void onGuildUpdateLocale(GuildUpdateLocaleEvent event) {executeClass(GuildUpdateLocaleEvent.class, event);}
    @Override public void onGuildUpdateFeatures(GuildUpdateFeaturesEvent event) {executeClass(GuildUpdateFeaturesEvent.class, event);}
    @Override public void onGuildUpdateVanityCode(GuildUpdateVanityCodeEvent event) {executeClass(GuildUpdateVanityCodeEvent.class, event);}
    @Override public void onGuildUpdateBanner(GuildUpdateBannerEvent event) {executeClass(GuildUpdateBannerEvent.class, event);}
    @Override public void onGuildUpdateDescription(GuildUpdateDescriptionEvent event) {executeClass(GuildUpdateDescriptionEvent.class, event);}
    @Override public void onGuildUpdateBoostTier(GuildUpdateBoostTierEvent event) {executeClass(GuildUpdateBoostTierEvent.class, event);}
    @Override public void onGuildUpdateBoostCount(GuildUpdateBoostCountEvent event) {executeClass(GuildUpdateBoostCountEvent.class, event);}
    @Override public void onGuildUpdateMaxMembers(GuildUpdateMaxMembersEvent event) {executeClass(GuildUpdateMaxMembersEvent.class, event);}
    @Override public void onGuildUpdateMaxPresences(GuildUpdateMaxPresencesEvent event) {executeClass(GuildUpdateMaxPresencesEvent.class, event);}

    //Guild Invite Events
    @Override public void onGuildInviteCreate(GuildInviteCreateEvent event) {executeClass(GuildInviteCreateEvent.class, event);}
    @Override public void onGuildInviteDelete(GuildInviteDeleteEvent event) {executeClass(GuildInviteDeleteEvent.class, event);}

    //Guild Member Events
    @Override public void onGuildMemberJoin(GuildMemberJoinEvent event) {executeClass(GuildMemberJoinEvent.class, event);}
    @Override public void onGuildMemberRoleAdd(GuildMemberRoleAddEvent event) {executeClass(GuildMemberRoleAddEvent.class, event);}
    @Override public void onGuildMemberRoleRemove(GuildMemberRoleRemoveEvent event) {executeClass(GuildMemberRoleRemoveEvent.class, event);}

    //Guild Member Update Events
    @Override public void onGuildMemberUpdate(GuildMemberUpdateEvent event) {executeClass(GuildMemberUpdateEvent.class, event);}
    @Override public void onGuildMemberUpdateNickname(GuildMemberUpdateNicknameEvent event) {executeClass(GuildMemberUpdateNicknameEvent.class, event);}
    @Override public void onGuildMemberUpdateBoostTime(GuildMemberUpdateBoostTimeEvent event) {executeClass(GuildMemberUpdateBoostTimeEvent.class, event);}
    @Override public void onGuildMemberUpdatePending(GuildMemberUpdatePendingEvent event) {executeClass(GuildMemberUpdatePendingEvent.class, event);}

    //Guild Voice Events
    @Override public void onGuildVoiceUpdate(GuildVoiceUpdateEvent event) {executeClass(GuildVoiceUpdateEvent.class, event);}
    @Override public void onGuildVoiceJoin(GuildVoiceJoinEvent event) {executeClass(GuildVoiceJoinEvent.class, event);}
    @Override public void onGuildVoiceMove(GuildVoiceMoveEvent event) {executeClass(GuildVoiceMoveEvent.class, event);}
    @Override public void onGuildVoiceLeave(GuildVoiceLeaveEvent event) {executeClass(GuildVoiceLeaveEvent.class, event);}
    @Override public void onGuildVoiceMute(GuildVoiceMuteEvent event) {executeClass(GuildVoiceMuteEvent.class, event);}
    @Override public void onGuildVoiceDeafen(GuildVoiceDeafenEvent event) {executeClass(GuildVoiceDeafenEvent.class, event);}
    @Override public void onGuildVoiceGuildMute(GuildVoiceGuildMuteEvent event) {executeClass(GuildVoiceGuildMuteEvent.class, event);}
    @Override public void onGuildVoiceGuildDeafen(GuildVoiceGuildDeafenEvent event) {executeClass(GuildVoiceGuildDeafenEvent.class, event);}
    @Override public void onGuildVoiceSelfMute(GuildVoiceSelfMuteEvent event) {executeClass(GuildVoiceSelfMuteEvent.class, event);}
    @Override public void onGuildVoiceSelfDeafen(GuildVoiceSelfDeafenEvent event) {executeClass(GuildVoiceSelfDeafenEvent.class, event);}
    @Override public void onGuildVoiceSuppress(GuildVoiceSuppressEvent event) {executeClass(GuildVoiceSuppressEvent.class, event);}
    @Override public void onGuildVoiceStream(GuildVoiceStreamEvent event) {executeClass(GuildVoiceStreamEvent.class, event);}

    //Role events
    @Override public void onRoleCreate(RoleCreateEvent event) {executeClass(RoleCreateEvent.class, event);}
    @Override public void onRoleDelete(RoleDeleteEvent event) {executeClass(RoleDeleteEvent.class, event);}

    //Role Update Events
    @Override public void onRoleUpdateColor(RoleUpdateColorEvent event) {executeClass(RoleUpdateColorEvent.class, event);}
    @Override public void onRoleUpdateHoisted(RoleUpdateHoistedEvent event) {executeClass(RoleUpdateHoistedEvent.class, event);}
    @Override public void onRoleUpdateMentionable(RoleUpdateMentionableEvent event) {executeClass(RoleUpdateMentionableEvent.class, event);}
    @Override public void onRoleUpdateName(RoleUpdateNameEvent event) {executeClass(RoleUpdateNameEvent.class, event);}
    @Override public void onRoleUpdatePermissions(RoleUpdatePermissionsEvent event) {executeClass(RoleUpdatePermissionsEvent.class, event);}
    @Override public void onRoleUpdatePosition(RoleUpdatePositionEvent event) {executeClass(RoleUpdatePositionEvent.class, event);}

    //Emote Events
    @Override public void onEmoteAdded(EmoteAddedEvent event) {executeClass(EmoteAddedEvent.class, event);}
    @Override public void onEmoteRemoved(EmoteRemovedEvent event) {executeClass(EmoteRemovedEvent.class, event);}

    //Emote Update Events
    @Override public void onEmoteUpdateName(EmoteUpdateNameEvent event) {executeClass(EmoteUpdateNameEvent.class, event);}
    @Override public void onEmoteUpdateRoles(EmoteUpdateRolesEvent event) {executeClass(EmoteUpdateRolesEvent.class, event);}

    // Debug Events
    @Override public void onHttpRequest(HttpRequestEvent event) {executeClass(HttpRequestEvent.class, event);}

    //Generic Events
    @Override public void onGenericMessage(GenericMessageEvent event) {executeClass(GenericMessageEvent.class, event);}
    @Override public void onGenericMessageReaction(GenericMessageReactionEvent event) {executeClass(GenericMessageReactionEvent.class, event);}
    @Override public void onGenericGuildMessage(GenericGuildMessageEvent event) {executeClass(GenericGuildMessageEvent.class, event);}
    @Override public void onGenericGuildMessageReaction(GenericGuildMessageReactionEvent event) {executeClass(GenericGuildMessageReactionEvent.class, event);}
    @Override public void onGenericPrivateMessage(GenericPrivateMessageEvent event) {executeClass(GenericPrivateMessageEvent.class, event);}
    @Override public void onGenericPrivateMessageReaction(GenericPrivateMessageReactionEvent event) {executeClass(GenericPrivateMessageReactionEvent.class, event);}
    @Override public void onGenericUser(GenericUserEvent event) {executeClass(GenericUserEvent.class, event);}
    @Override public void onGenericUserPresence(GenericUserPresenceEvent event) {executeClass(GenericUserPresenceEvent.class, event);}
    @Override public void onGenericSelfUpdate(GenericSelfUpdateEvent event) {executeClass(GenericSelfUpdateEvent.class, event);}
    @Override public void onGenericStoreChannel(GenericStoreChannelEvent event) {executeClass(GenericStoreChannelEvent.class, event);}
    @Override public void onGenericStoreChannelUpdate(GenericStoreChannelUpdateEvent event) {executeClass(GenericStoreChannelUpdateEvent.class, event);}
    @Override public void onGenericTextChannel(GenericTextChannelEvent event) {executeClass(GenericTextChannelEvent.class, event);}
    @Override public void onGenericTextChannelUpdate(GenericTextChannelUpdateEvent event) {executeClass(GenericTextChannelUpdateEvent.class, event);}
    @Override public void onGenericVoiceChannel(GenericVoiceChannelEvent event) {executeClass(GenericVoiceChannelEvent.class, event);}
    @Override public void onGenericVoiceChannelUpdate(GenericVoiceChannelUpdateEvent event) {executeClass(GenericVoiceChannelUpdateEvent.class, event);}
    @Override public void onGenericCategory(GenericCategoryEvent event) {executeClass(GenericCategoryEvent.class, event);}
    @Override public void onGenericCategoryUpdate(GenericCategoryUpdateEvent event) {executeClass(GenericCategoryUpdateEvent.class, event);}
    @Override public void onGenericGuild(GenericGuildEvent event) {executeClass(GenericGuildEvent.class, event);}
    @Override public void onGenericGuildUpdate(GenericGuildUpdateEvent event) {executeClass(GenericGuildUpdateEvent.class, event);}
    @Override public void onGenericGuildInvite(GenericGuildInviteEvent event) {executeClass(GenericGuildInviteEvent.class, event);}
    @Override public void onGenericGuildMember(GenericGuildMemberEvent event) {executeClass(GenericGuildMemberEvent.class, event);}
    @Override public void onGenericGuildMemberUpdate(GenericGuildMemberUpdateEvent event) {executeClass(GenericGuildMemberUpdateEvent.class, event);}
    @Override public void onGenericGuildVoice(GenericGuildVoiceEvent event) {executeClass(GenericGuildVoiceEvent.class, event);}
    @Override public void onGenericRole(GenericRoleEvent event) {executeClass(GenericRoleEvent.class, event);}
    @Override public void onGenericRoleUpdate(GenericRoleUpdateEvent event) {executeClass(GenericRoleUpdateEvent.class, event);}
    @Override public void onGenericEmote(GenericEmoteEvent event) {executeClass(GenericEmoteEvent.class, event);}
    @Override public void onGenericEmoteUpdate(GenericEmoteUpdateEvent event) {executeClass(GenericEmoteUpdateEvent.class, event);}
    @Override public void onGenericPermissionOverride(GenericPermissionOverrideEvent event) {executeClass(GenericPermissionOverrideEvent.class, event);}

    public void onMessageConsoleReceived(MessageConsoleReceivedEvent event) {
    	executeClass(MessageConsoleReceivedEvent.class, event);
    }

	
}
